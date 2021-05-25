package com.atul.dh;

import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {
        MainWindowGUI gui = new MainWindowGUI("Diffie - Hellman Key Exchange Algorithm");
        gui.setSize(600, 550);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
    }
}
