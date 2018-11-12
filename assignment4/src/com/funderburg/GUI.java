package com.funderburg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener {

    private void createAndShowGUI() {
        // Create and set up the window
        JFrame frame = new JFrame("GUITitle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Hello world!");
        frame.getContentPane().add(label1);

        JTextField textField = new JTextField(10);
        textField.setActionCommand("textfield");
        textField.addActionListener(this);

        // Display window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this app's GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
