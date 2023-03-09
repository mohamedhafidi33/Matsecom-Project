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
    public static GuiViewModel guiViewModel = new GuiViewModel();
    public static ArrayList<Subscriber> subscribers;

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

    public void updateSubscriberDropDown(){
        ChooseSubscriberSession.removeAllItems();
        SubscriberListDropDown.removeAllItems();
        ChooseEditSubscriber.removeAllItems();
        ChooseEditSubscriber.addItem("*new Subscriber");
        if(subscribers==null){
            SubscriberListDropDown.setEnabled(false);
            ChooseSubscriberSession.setEnabled(false);
        } else {
            SubscriberListDropDown.setEnabled(true);
            ChooseSubscriberSession.setEnabled(true);
            for(Subscriber x: subscribers){
                ChooseSubscriberSession.addItem(x.forename);
                ChooseEditSubscriber.addItem(x.forename);
                SubscriberListDropDown.addItem(x.forename);
            }
        }
    }
    public Gui() {
        ChooseMinutesOfSession.setModel(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) ChooseMinutesOfSession.getEditor()).getTextField();
        tf.setEditable(false);
        updateSubscriberDropDown();
        ChooseEditSubscriber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    if (ChooseEditSubscriber.getSelectedItem().toString() == "*new Subscriber") {
                        isEditing = false;
                        managementOfSubscribersButton.setText("Add Subscriber");
                        removeSubscriberButton.setVisible(false);
                        IMSIText.setText("");
                        SurnameText.setText("");
                        ForenameText.setText("");
                        IMSIText.setEditable(true);
                    } else {
                        for(Subscriber x: subscribers){
                            if(x.forename==ChooseEditSubscriber.getSelectedItem().toString()){
                                ForenameText.setText(x.forename);
                                SurnameText.setText(x.surname);
                                IMSIText.setText(x.getMSIN());
                                managementOfSubscribersButton.setText("Edit Subscriber");
                                removeSubscriberButton.setVisible(true);
                                TerminalTypeDropDown.setSelectedIndex(x.terminal.ordinal());
                                SubscriptionTypeDropDown.setSelectedIndex(x.subscription.ordinal());
                                IMSIText.setEditable(false);
                                isEditing=true;
                            }
                        }
                    }
                } catch (NullPointerException exc){}
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

                if (ForenameText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "You have forgot to write the Forename of the Subscriber.");
                } else if (SurnameText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "You have forgot to write the Surname of the Subscriber.");
                } else if (IMSIText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "You have forgot to give a IMSI to the Subscriber.");
                } else if (IMSIText.getText().length() != 10) {
                    JOptionPane.showMessageDialog(null, "The IMSI is to short.");
                } else {
                    String terminal = TerminalTypeDropDown.getSelectedItem().toString();
                    String subscription = SubscriptionTypeDropDown.getSelectedItem().toString();
                    if(isEditing){
                        subscribers = guiViewModel.editSubscriber(ForenameText.getText(), SurnameText.getText(), IMSIText.getText(), terminal, subscription);
                    } else {
                        boolean isIMSI=false;
                        if(subscribers!=null){
                            for(Subscriber x: subscribers){
                                if(x.getMSIN().equals(IMSIText.getText())){
                                    isIMSI=true;
                                }
                            }
                        }

                        if(isIMSI){
                            JOptionPane.showMessageDialog(null, "This MSIN is already assigned.");
                        } else {
                            subscribers = guiViewModel.addSubscriber(ForenameText.getText(), SurnameText.getText(), IMSIText.getText(), terminal, subscription);
                        }
                    }
                    updateSubscriberDropDown();
                }
            }
        });
        removeSubscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subscribers = guiViewModel.deleteSubscriber(IMSIText.getText());
                updateSubscriberDropDown();
            }
        });
        SubscriberListDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DefaultListModel listModel = new DefaultListModel<>();
                listModel.add(0, "Info about " + SubscriberListDropDown.getSelectedItem());
                for(Subscriber x : subscribers){
                    if(x.forename==SubscriberListDropDown.getSelectedItem()){
                        listModel.add(1, x.terminal.name);
                        listModel.add(2,x.subscription.name);
                    }
                }
                list1.setModel(listModel);
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
        subscribers = guiViewModel.getSubscribers();

    }

}
