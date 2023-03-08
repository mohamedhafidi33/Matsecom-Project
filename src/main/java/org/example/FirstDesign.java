package org.example;

import javax.swing.*;
import java.awt.event.*;

public class FirstDesign {
    private JPanel DesignPanel;
    private JButton managementOfSubscribersButton;
    private JList list1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JList list2;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JSpinner spinner1;
    private JButton addSessionButton;
    private JButton removeSubscriberButton;
    private JComboBox comboBox3;
    private JComboBox comboBox5;
    private JList list3;
    private JButton callInvoiceButton;

    public FirstDesign() {


        managementOfSubscribersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox1.getSelectedItem()=="Yannik"){
                    textField1.setText("Yannik");
                    textField2.setText("Schreiter");
                    textField3.setText("0123456789");
                    textField4.setText("Samsung S42plus");
                    textField5.setText("GreenMobile M");
                    managementOfSubscribersButton.setText("Edit Subscriber");
                    removeSubscriberButton.setVisible(true);
                } else {
                    managementOfSubscribersButton.setText("Add Subscriber");
                    removeSubscriberButton.setVisible(false);
                    textField5.setText("");
                    textField4.setText("");
                    textField3.setText("");
                    textField2.setText("");
                    textField1.setText("");
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FirstDesign");
        frame.setContentPane(new FirstDesign().DesignPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
