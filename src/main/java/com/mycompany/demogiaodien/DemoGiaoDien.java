package com.mycompany.demogiaodien;

import javax.swing.SwingUtilities;

public class DemoGiaoDien {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuFrame frame = new MainMenuFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
