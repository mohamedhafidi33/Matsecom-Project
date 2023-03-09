package de.deltacorp;

import java.util.ArrayList;

public class GuiViewModel {

    private storage storage = new storage();
    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
    private ArrayList<Session> sessions = new ArrayList<Session>();
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();
    private String mCC = "262";
    private String mNC = "42";

    public static void main(String[] args) {
        GuiViewModel viewModel = new GuiViewModel();
        System.out.println(viewModel.addSubscriber("Hannah", "Klein", "220968", "PhairPhone", "GreenMobil S").toString());
    }

    public ArrayList<Subscriber> addSubscriber(String forename, String surname, String mSIN, String TerminalType, String SubscribtionType){
        Subscriber sub = this.creatSubscriber(forename, surname, mSIN, TerminalType, SubscribtionType);
        subscribers.add(sub);
        storage.storeSubscribers(subscribers);
        return subscribers;
    }

    // public ArrayList<Session> addSession(String forename, String surname, String time, String service){
    //     Service usedService = null;
    //     Subscriber sub = null;
    //     for (Subscriber subscriber : subscribers) {
    //         if(subscriber.forename == forename && subscriber.surname == surname) {
    //             sub = subscriber;
    //         }
    //     }
    //     switch (service) {
    //         case "Voice call":
    //             usedService = Service.VOICE_CALL;
    //             break;
    //         case "Browsing and social networking":
    //             usedService = Service.BASN;
    //             break;
    //         case "App download":
    //             usedService = Service.APL;
    //             break;
    //         case "Adaptive HD video":
    //             usedService = Service.AHDV;
    //             break;
    //     };
        
    //     // Calculate COSTS

    // }

    // public ArrayList<Invoice> addInvoice(){
        
    // }

    public ArrayList<Subscriber> getSubscribers() {
        this.subscribers = storage.getSubscribers();
        return this.subscribers;
    }

    public ArrayList<Session> getSessions() {
        this.sessions = storage.getSessions();
        return this.sessions;
    }

    public ArrayList<Invoice> getInvoices() {
        this.invoices = storage.getInvoices();
        return this.invoices;
    }

    public ArrayList<Subscriber> editSubscriber(String forename, String surname, String mSIN, String TerminalType, String SubscribtionType) {
        String iMSI = mCC + mNC + mSIN;
        for (int i = 0; i < subscribers.size(); i++) {
            if((mCC + mNC + subscribers.get(i).getMSIN()).equals(iMSI)) {
                subscribers.set(i, creatSubscriber(forename, surname, mSIN, TerminalType, SubscribtionType));
            }
        }
        storage.storeSubscribers(subscribers);
        return subscribers;
    }

    public ArrayList<Subscriber> deleteSubscriber(String mSIN) {
        for (int i = 0; i < subscribers.size(); i++) {
            if(mSIN.equals(subscribers.get(i).getMSIN())) {
                subscribers.remove(i);
            }
        }
        storage.storeSubscribers(subscribers);
        return subscribers;
    }

    private Subscriber creatSubscriber(String forename, String surname, String mSIN, String TerminalType, String SubscribtionType) {
        String mCC = "262";
        String mNC = "42";
        Terminal terminal = null;
        Subscription subscribtionType = null;
        double remainingDataVolumeMb = 0;
        double chargingTotalEur = 0;
        double remainingFreeMinutes = 0;

        switch (TerminalType) {
            case "PhairPhone":
                terminal = Terminal.PHAIR_PHONE;
                break;
            case "Pear aphone 4s":
                terminal = Terminal.PEAR_APHONE_4S;
                break;
            case "Samsung S42plus":
                terminal = Terminal.SAMSUNG_S42PLUS;
                break;
        }

        switch (SubscribtionType) {
            case "GreenMobil S":
                remainingDataVolumeMb = 0;
                chargingTotalEur = 8;
                remainingFreeMinutes = 0;
                subscribtionType = Subscription.GREEN_MOBIL_S;
                break;
            case "GreenMobile M":
                remainingDataVolumeMb = 100;
                chargingTotalEur = 22;
                remainingFreeMinutes = 100;
                subscribtionType = Subscription.GREEN_MOBIL_M;
                break;
            case "GreenMobile L":
                remainingDataVolumeMb = 150;
                chargingTotalEur = 42;
                remainingFreeMinutes = 150;
                subscribtionType = Subscription.GREEN_MOBIL_L;
        }
        return new Subscriber(remainingDataVolumeMb, chargingTotalEur, remainingFreeMinutes, forename, surname, mCC, mNC, mSIN, terminal, subscribtionType);
    }
}
