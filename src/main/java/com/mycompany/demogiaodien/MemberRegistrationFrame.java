package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberRegistrationFrame extends JFrame {

    public MemberRegistrationFrame() {
        setTitle("Đăng ký thành viên - QLST Điện Máy (Desktop)");
        setSize(480, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addRow(formPanel, gbc, 0, "Họ và tên", "Nguyễn Văn A");
        addRow(formPanel, gbc, 1, "Email", "a.nguyen@example.com");
        addRow(formPanel, gbc, 2, "Số điện thoại", "0901234567");
        addRow(formPanel, gbc, 3, "Địa chỉ", "123 Trần Hưng Đạo, Q1, TP.HCM");

        add(formPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("Đăng ký");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int rowIndex, String label, String value) {
        gbc.gridx = 0;
        gbc.gridy = rowIndex;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField field = new JTextField(value);
        panel.add(field, gbc);
    }
}
