package de.deltacorp;

import de.deltacorp.Terminal;
import de.deltacorp.Subscription;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Gui {
    public static JFrame frame; // Instanced on main
    public static Gui instance; // Instanced on main

    public boolean isEditing = false;
    public static storage storage = new storage();
    public static ArrayList<Subscriber> Subscriber;

    private JPanel DesignPanel;
    private JButton managementOfSubscribersButton;
    private JList list1;
    private JTextField ForenameText;
    private JTextField SurnameText;
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

    public Gui() {
        ChooseMinutesOfSession.setModel(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) ChooseMinutesOfSession.getEditor()).getTextField();
        tf.setEditable(false);

        ChooseEditSubscriber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ChooseEditSubscriber.getSelectedItem() == "Yannik") {
                    ForenameText.setText("Yannik");
                    SurnameText.setText("Schreiter");
                    IMSIText.setText("0123456789");
                    managementOfSubscribersButton.setText("Edit Subscriber");
                    removeSubscriberButton.setVisible(true);
                    TerminalTypeDropDown.setSelectedIndex(2);
                    SubscriptionTypeDropDown.setSelectedIndex(2);
                    IMSIText.setEditable(false);
                    isEditing=true;
                } else {
                    isEditing = false;
                    managementOfSubscribersButton.setText("Add Subscriber");
                    removeSubscriberButton.setVisible(false);
                    IMSIText.setText("");
                    SurnameText.setText("");
                    ForenameText.setText("");
                    IMSIText.setEditable(true);
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
                if(isEditing){

                } else {
                    if(ForenameText.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"You have forgot to write the Forename of the Subscriber.");
                    } else if ( SurnameText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"You have forgot to write the Surname of the Subscriber.");
                    } else if (IMSIText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"You have forgot to give a IMSI to the Subscriber.");
                    } else if (IMSIText.getText().length() != 10){
                        JOptionPane.showMessageDialog(null,"The IMSI is to short.");
                    } else {
                        Terminal terminal = Terminal.valueOf(
                                TerminalTypeDropDown.getItemAt(TerminalTypeDropDown.getSelectedIndex()).toString()
                        );
                        Subscription subscription = Subscription.valueOf(
                                SubscriptionTypeDropDown.getItemAt(SubscriberListDropDown.getSelectedIndex()).toString()
                        );
                        double remainingDataVolumeMb = 0;
                        double chargingTotalEur = 0;
                        double remainingFreeMinutes = 0;
                        String mCC = "262";
                        String mNC = "42";
                        String mSIN;

                        switch (subscription) {
                            case GREEN_MOBIL_S:
                                remainingDataVolumeMb = 0;
                                chargingTotalEur = 8;
                                remainingFreeMinutes = 0;
                                subscription = Subscription.GREEN_MOBIL_S;
                                break;
                            case GREEN_MOBIL_M:
                                remainingDataVolumeMb = 100;
                                chargingTotalEur = 22;
                                remainingFreeMinutes = 100;
                                subscription = Subscription.GREEN_MOBIL_M;
                                break;
                            case GREEN_MOBIL_L:
                                remainingDataVolumeMb = 150;
                                chargingTotalEur = 42;
                                remainingFreeMinutes = 150;
                                subscription = Subscription.GREEN_MOBIL_L;
                        }
                        mSIN = IMSIText.getText();
                        Subscriber.add(new Subscriber(remainingDataVolumeMb,
                                chargingTotalEur,
                                remainingFreeMinutes,
                                ForenameText.getText(),
                                SurnameText.getText(),
                                mCC,
                                mNC,
                                mSIN,
                                terminal,
                                subscription));
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("GUI");
        instance = new Gui();

        frame.setContentPane(Gui.instance.DesignPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Subscriber = storage.getSubscribers();
    }

}
