package de.deltacorp;

import javax.swing.*;
import java.awt.event.*;

public class FirstDesign {
    private JPanel DesignPanel;
    private JButton managementOfSubscribersButton;
    private JList list1;
    private JTextField ForenameText;
    private JTextField SurenameText;
    private JTextField IMSIText;
    private JComboBox ChooseEditSubscriber;
    private JList list2;
    private JComboBox ChooseSubscriberSession;
    private JComboBox ChooseServiceSession;
    private JSpinner ChooseMinutesOfSession;
    private JButton addSessionButton;
    private JButton removeSubscriberButton;
    private JComboBox TerminalTypeDropDown;
    private JComboBox SubscriptionTypeDropDown;
    private JList list3;
    private JButton callInvoiceButton;
    private JComboBox SubscriberListDropDown;
    private JComboBox InvoiceDropDownList;
    private JComboBox SessionDropDownList;

    public FirstDesign() {


        managementOfSubscribersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ChooseEditSubscriber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ChooseEditSubscriber.getSelectedItem() == "Yannik") {
                    ForenameText.setText("Yannik");
                    SurenameText.setText("Schreiter");
                    IMSIText.setText("0123456789");
                    managementOfSubscribersButton.setText("Edit Subscriber");
                    removeSubscriberButton.setVisible(true);
                    TerminalTypeDropDown.setSelectedIndex(2);
                    SubscriptionTypeDropDown.setSelectedIndex(2);
                } else {
                    managementOfSubscribersButton.setText("Add Subscriber");
                    removeSubscriberButton.setVisible(false);
                    IMSIText.setText("");
                    SurenameText.setText("");
                    ForenameText.setText("");
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
