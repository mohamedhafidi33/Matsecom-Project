package de.deltacorp;

import org.example.Terminal;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI {
    public static storage storage = new storage();
    public static ArrayList<org.example.Subscriber> Subscriber;
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

    public GUI() {
        ChooseMinutesOfSession.setModel(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) ChooseMinutesOfSession.getEditor()).getTextField();
        tf.setEditable(false);
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
        IMSIText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) || IMSIText.getText().length()>=10){
                    e.consume();
                }
            }
        });
        ChooseMinutesOfSession.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0)
                {
                    ChooseMinutesOfSession.setValue(ChooseMinutesOfSession.getNextValue());
                }
                else {
                    ChooseMinutesOfSession.setValue(ChooseMinutesOfSession.getPreviousValue());
                }
            }
        });
        managementOfSubscribersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ForenameText.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"You have forgot to write the Forename of the Subscriber.");
                } else if ( SurenameText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"You have forgot to write the Surename of the Subscriber.");
                } else if (IMSIText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"You have forgot to give a IMSI to the Subscriber.");
                } else if (IMSIText.getText().length() != 10){
                    JOptionPane.showMessageDialog(null,"The IMSI is to short.");
                } else {
                    String TerminalType = TerminalTypeDropDown.getItemAt(TerminalTypeDropDown.getSelectedIndex()).toString();
                    String SubscribtionType = SubscriptionTypeDropDown.getItemAt(SubscriberListDropDown.getSelectedIndex()).toString();
                    Terminal terminal;
                    double remainingDataVolumeMb;
                    double chargingTotalEur;
                    double remainingFreeMinutes;
                    String mCC = "42";
                    String mNC="";
                    String mSIN;

                    switch (TerminalType) {
                        case "PhairPhone":
                            terminal=Terminal.PHAIR_PHONE;
                            break;
                        case "Pear aphone 4s":
                            terminal=Terminal.PEAR_APHONE_4s;
                            break;
                        case "Samsung S42plus":
                            terminal =Terminal.SAMSUNG_S42PLUS;
                            break;
                    }

                    switch (SubscribtionType) {
                        case "GreenMobil S":
                            remainingDataVolumeMb = 0;
                            chargingTotalEur = 8;
                            remainingFreeMinutes = 0;
                            break;
                        case "GreenMobile M":
                            remainingDataVolumeMb = 100;
                            chargingTotalEur = 22;
                            remainingFreeMinutes = 100;
                            break;
                        case "GreenMonile L":
                            remainingDataVolumeMb = 150;
                            chargingTotalEur = 42;
                            remainingFreeMinutes = 150;
                    }
                    mSIN = IMSIText.getText().substring(5);
                    JOptionPane.showMessageDialog(null, mCC + "|" + mNC + "|" + mSIN);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().DesignPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Subscriber =  storage.getSubscribers();
    }

}
