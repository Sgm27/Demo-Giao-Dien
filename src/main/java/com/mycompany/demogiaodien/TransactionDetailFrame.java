package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
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
        setSize(560, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(new JLabel("Khách hàng: " + customer.getName() + " (" + customer.getId() + ")"), BorderLayout.NORTH);
        headerPanel.add(new JLabel("Khoảng thời gian: " + fromDate + " - " + toDate), BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        orders = customer.getOrders();

        String[] columns = {"Mã đơn hàng", "Tổng tiền", ""};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        long total = 0;
        for (Order order : orders) {
            long amount = order.getTotalAmount();
            total += amount;
            model.addRow(new Object[]{order.getId(), DataRepository.formatCurrency(amount), "Xem chi tiết"});
        }

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JButton("Xem chi tiết")));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(new JLabel("Tổng doanh thu: " + DataRepository.formatCurrency(total)));
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
