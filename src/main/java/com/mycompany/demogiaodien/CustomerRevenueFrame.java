package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class CustomerRevenueFrame extends JFrame {

    private final JTable table;
    private final DefaultTableModel model;
    private final String fromDate;
    private final String toDate;
    private final List<CustomerInfo> customers;

    public CustomerRevenueFrame(String fromDate, String toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.customers = DataRepository.getCustomerInfos();

        setTitle("Thống kê khách hàng theo doanh thu");
        setSize(900, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Từ ngày"));
        filterPanel.add(new JLabel(this.fromDate));
        filterPanel.add(new JLabel("Đến ngày"));
        filterPanel.add(new JLabel(this.toDate));
        add(filterPanel, BorderLayout.NORTH);

        String[] columns = {"Mã KH", "Họ tên", "Số điện thoại", "Email", "Hạng", "Số giao dịch", "Tổng doanh thu", ""};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        table = new JTable(model);
        table.setRowHeight(24);

        table.getColumnModel().getColumn(7).setPreferredWidth(120);
        table.getColumnModel().getColumn(7).setCellRenderer(new DetailButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new DetailButtonEditor(new JButton("Xem chi tiết")));

        populateTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Trở về");
        backButton.addActionListener(e -> dispose());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(backButton);
        add(actionPanel, BorderLayout.SOUTH);
    }

    private void populateTable() {
        model.setRowCount(0);
        for (CustomerInfo customer : customers) {
            Object[] row = {
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getMembershipTier(),
                customer.getTransactionCount(),
                DataRepository.formatCurrency(customer.getTotalRevenue()),
                "Xem chi tiết"
            };
            model.addRow(row);
        }
    }

    private void openDetailDialog(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= customers.size()) {
            JOptionPane.showMessageDialog(this, "Dữ liệu khách hàng không hợp lệ.");
            return;
        }

        CustomerInfo customer = customers.get(rowIndex);
        TransactionDetailFrame frame = new TransactionDetailFrame(customer, fromDate, toDate);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private class DetailButtonRenderer extends JButton implements TableCellRenderer {

        DetailButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

    private class DetailButtonEditor extends AbstractCellEditor implements TableCellEditor, java.awt.event.ActionListener {

        private final JButton button;
        private int currentRow = -1;

        DetailButtonEditor(JButton button) {
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
        public void actionPerformed(java.awt.event.ActionEvent e) {
            openDetailDialog(currentRow);
            fireEditingStopped();
        }
    }
}
