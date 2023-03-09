package de.deltacorp;

import de.deltacorp.Terminal;
import de.deltacorp.Subscription;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Gui {
    public boolean isEditing = false;
    public static storage storage = new storage();
    public static ArrayList<de.deltacorp.Subscriber> Subscriber;
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

    public Gui() {
        ChooseMinutesOfSession.setModel(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) ChooseMinutesOfSession.getEditor()).getTextField();
        tf.setEditable(false);
        ChooseEditSubscriber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ChooseEditSubscriber.getSelectedItem() == "*new Subscriber") {
                    isEditing = false;
                    managementOfSubscribersButton.setText("Add Subscriber");
                    removeSubscriberButton.setVisible(false);
                    IMSIText.setText("");
                    SurenameText.setText("");
                    ForenameText.setText("");
                    IMSIText.setEditable(true);
                } else {
                    for(Subscriber x : Subscriber){
                        if(x.forename==ChooseEditSubscriber.getSelectedItem()){
                            ForenameText.setText(x.forename);
                            SurenameText.setText(x.surname);
                            IMSIText.setText("");
                            managementOfSubscribersButton.setText("Edit Subscriber");
                            removeSubscriberButton.setVisible(true);
                            TerminalTypeDropDown.setSelectedIndex(x.terminal.ordinal());
                            SubscriptionTypeDropDown.setSelectedIndex(x.subscription.ordinal());
                            IMSIText.setEditable(false);
                            isEditing=true;
                        }
                    }

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
                    if (ForenameText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "You have forgot to write the Forename of the Subscriber.");
                    } else if (SurenameText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "You have forgot to write the Surename of the Subscriber.");
                    } else if (IMSIText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "You have forgot to give a IMSI to the Subscriber.");
                    } else if (IMSIText.getText().length() != 10) {
                        JOptionPane.showMessageDialog(null, "The IMSI is to short.");
                    } else {
                        String TerminalType = TerminalTypeDropDown.getItemAt(TerminalTypeDropDown.getSelectedIndex()).toString();
                        String SubscribtionType = SubscriptionTypeDropDown.getItemAt(SubscriberListDropDown.getSelectedIndex()).toString();
                        GuiViewModel a =null;
                        a.addSubscriber(ForenameText.getText(), SurenameText.getText(), IMSIText.getText(), TerminalType, SubscribtionType);
                        ChooseEditSubscriber.addItem(ForenameText.getText());
                    }
                }
            }
        });
        removeSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subscriber.remove(ChooseEditSubscriber.getSelectedIndex());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new Gui().DesignPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Subscriber =  storage.getSubscribers();
    }

}
