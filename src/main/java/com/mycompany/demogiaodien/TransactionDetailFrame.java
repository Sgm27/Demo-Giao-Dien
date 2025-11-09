package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TransactionDetailFrame extends JFrame {

    private final List<Order> orders;

    public TransactionDetailFrame(CustomerInfo customer, String fromDate, String toDate) {
        setTitle("Chi tiết giao dịch - " + customer.getName() + " (" + customer.getId() + ")");
        setSize(900, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.add(new JLabel("Khách hàng: " + customer.getName() + " (" + customer.getId() + ")"));
        headerPanel.add(new JLabel("Liên hệ: " + customer.getPhoneNumber() + " | " + customer.getEmail()));
        headerPanel.add(new JLabel("Hạng thành viên: " + customer.getMembershipTier() + " | Khoảng thời gian: " + fromDate + " - " + toDate));
        add(headerPanel, BorderLayout.NORTH);

        orders = customer.getOrders();

        String[] columns = {"Mã đơn hàng", "Ngày đặt", "Trạng thái", "Thanh toán", "Địa chỉ giao hàng", "Tổng tiền", ""};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        long total = 0;
        for (Order order : orders) {
            long amount = order.getTotalAmount();
            total += amount;
            model.addRow(new Object[]{
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getShippingAddress(),
                DataRepository.formatCurrency(amount),
                "Xem chi tiết"
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JButton("Xem chi tiết")));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Trở về");
        backButton.addActionListener(e -> dispose());
        footerPanel.add(backButton, BorderLayout.WEST);
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("Tổng doanh thu: " + DataRepository.formatCurrency(total)));
        footerPanel.add(totalPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void showOrderDetail(Order order) {
        OrderDetailDialog dialog = new OrderDetailDialog(this, order);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        private final JButton button;
        private int currentRow = -1;

        ButtonEditor(JButton button) {
            this.button = button;
            this.button.addActionListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentRow = row;
            button.setText(value == null ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentRow >= 0 && currentRow < orders.size()) {
                showOrderDetail(orders.get(currentRow));
            }
            fireEditingStopped();
        }
    }
}
