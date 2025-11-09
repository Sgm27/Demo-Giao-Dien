package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class OrderDetailDialog extends JDialog {

    public OrderDetailDialog(JFrame owner, Order order) {
        super(owner, "Chi tiết đơn hàng - " + order.getId(), true);
        setSize(760, 360);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.add(new JLabel("Đơn hàng: " + order.getId()));
        headerPanel.add(new JLabel("Ngày đặt: " + order.getOrderDate() + " | Trạng thái: " + order.getStatus()));
        headerPanel.add(new JLabel("Thanh toán: " + order.getPaymentMethod() + " | Giao tới: " + order.getShippingAddress()));
        add(headerPanel, BorderLayout.NORTH);

        String[] columns = {"Mã SP", "Sản phẩm", "Danh mục", "Số lượng", "Đơn giá", "Thành tiền"};
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
                item.getProductCode(),
                item.getProductName(),
                item.getCategory(),
                item.getQuantity(),
                DataRepository.formatCurrency(item.getUnitPrice()),
                DataRepository.formatCurrency(subtotal)
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Trở về");
        backButton.addActionListener(e -> dispose());
        footer.add(backButton, BorderLayout.WEST);
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("Tổng đơn hàng: " + DataRepository.formatCurrency(total)));
        footer.add(totalPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
}
