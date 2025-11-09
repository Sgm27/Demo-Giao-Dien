package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StatisticsSelectionFrame extends JFrame {

    public StatisticsSelectionFrame() {
        setTitle("Chọn kiểu thống kê");
        setSize(420, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel typeLabel = new JLabel("Kiểu thống kê");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(typeLabel, gbc);

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Khách hàng theo doanh thu"});
        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(typeCombo, gbc);

        JLabel fromLabel = new JLabel("Từ ngày");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(fromLabel, gbc);

        JTextField fromField = new JTextField("02/10/2025");
        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(fromField, gbc);

        JLabel toLabel = new JLabel("Đến ngày");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(toLabel, gbc);

        JTextField toField = new JTextField("02/10/2025");
        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(toField, gbc);

        add(formPanel, BorderLayout.CENTER);

        JButton showButton = new JButton("Xem thống kê");
        showButton.addActionListener(e -> {
            CustomerRevenueFrame frame = new CustomerRevenueFrame(fromField.getText(), toField.getText());
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
