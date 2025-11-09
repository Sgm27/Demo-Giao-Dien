package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        setSize(640, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Từ ngày"));
        filterPanel.add(new JLabel(this.fromDate));
        filterPanel.add(new JLabel("Đến ngày"));
        filterPanel.add(new JLabel(this.toDate));
        add(filterPanel, BorderLayout.NORTH);

        String[] columns = {"Mã KH", "Họ tên", "Số giao dịch", "Tổng doanh thu"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(24);

        populateTable();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton detailButton = new JButton("Xem chi tiết");
        detailButton.addActionListener(e -> openDetailDialog());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(detailButton);
        add(actionPanel, BorderLayout.SOUTH);
    }

    private void populateTable() {
        model.setRowCount(0);
        for (CustomerInfo customer : customers) {
            Object[] row = {
                customer.getId(),
                customer.getName(),
                customer.getTransactionCount(),
                DataRepository.formatCurrency(customer.getTotalRevenue())
            };
            model.addRow(row);
        }
    }

    private void openDetailDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng trong bảng.");
            return;
        }

        CustomerInfo customer = customers.get(selectedRow);
        TransactionDetailFrame frame = new TransactionDetailFrame(customer, fromDate, toDate);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }
}
