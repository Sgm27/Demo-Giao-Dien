package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TransactionDetailFrame extends JFrame {

    public TransactionDetailFrame(CustomerInfo customer, String fromDate, String toDate) {
        setTitle("Chi tiết giao dịch - " + customer.getName() + " (" + customer.getId() + ")");
        setSize(520, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(new JLabel("Khách hàng: " + customer.getName() + " (" + customer.getId() + ")"), BorderLayout.NORTH);
        headerPanel.add(new JLabel("Khoảng thời gian: " + fromDate + " - " + toDate), BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        String[] columns = {"STT", "Số tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Long> transactions = customer.getTransactionAmounts();
        long total = 0;
        for (int i = 0; i < transactions.size(); i++) {
            long amount = transactions.get(i);
            total += amount;
            model.addRow(new Object[]{i + 1, DataRepository.formatCurrency(amount)});
        }

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(new JLabel("Tổng: " + DataRepository.formatCurrency(total)));
        add(footerPanel, BorderLayout.SOUTH);
    }
}
