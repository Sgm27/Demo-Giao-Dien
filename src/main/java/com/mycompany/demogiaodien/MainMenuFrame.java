package com.mycompany.demogiaodien;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Chọn chức năng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 180);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Vui lòng chọn chức năng", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton registerButton = new JButton("Đăng ký thành viên");
        registerButton.addActionListener(e -> {
            MemberRegistrationFrame frame = new MemberRegistrationFrame();
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);
        });

        JButton statisticButton = new JButton("Xem thống kê khách hàng theo doanh thu");
        statisticButton.addActionListener(e -> {
            StatisticsSelectionFrame frame = new StatisticsSelectionFrame();
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(statisticButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
