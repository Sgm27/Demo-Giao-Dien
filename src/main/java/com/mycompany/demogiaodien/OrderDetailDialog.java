package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderDetailDialog extends JDialog {

    public OrderDetailDialog(JFrame owner, Order order) {
        super(owner, "Chi tiết đơn hàng - " + order.getId(), true);
        setSize(520, 320);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Chi tiết sản phẩm của đơn hàng " + order.getId());
        add(header, BorderLayout.NORTH);

        String[] columns = {"Sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        long total = 0;
        for (OrderItem item : order.getItems()) {
            long subtotal = item.getSubtotal();
            total += subtotal;
            model.addRow(new Object[]{
                item.getProductName(),
                item.getQuantity(),
                DataRepository.formatCurrency(item.getUnitPrice()),
                DataRepository.formatCurrency(subtotal)
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(new JLabel("Tổng đơn hàng: " + DataRepository.formatCurrency(total)));
        add(footer, BorderLayout.SOUTH);
    }
}
