package de.deltacorp;

import java.util.ArrayList;

public class GuiViewModel {

    private storage storage = new storage();
    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
    private ArrayList<Session> sessions = new ArrayList<Session>();
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();
    private String mCC = "262";
    private String mNC = "42";

    public ArrayList<Subscriber> addSubscriber(String forename, String surname, String mSIN, String TerminalType, String SubscribtionType){
        Subscriber sub = this.creatSubscriber(forename, surname, mSIN, TerminalType, SubscribtionType);
        subscribers.add(sub);
        storage.storeSubscribers(subscribers);
        return subscribers;
    }

    public ArrayList<Session> addSession(String forename, String surname, String time, String service){
        Service usedService = null;
        Subscriber sub = null;
        Session session = null;
        Double doubleTime = Double.parseDouble(time);
        for (Subscriber subscriber : subscribers) {
            if(subscriber.forename.equals(forename) && subscriber.surname.equals(surname)) {
                sub = subscriber;
            }
        }
        Terminal terminal = sub.terminal;

        switch (service) {
            case "Voice call":
                usedService = Service.VOICE_CALL;
                sub.usedMinutes += doubleTime;
                if(sub.remainingFreeMinutes - sub.usedMinutes <= 0) {
                    sub.remainingFreeMinutes = 0;
                } else {
                    sub.remainingFreeMinutes -= sub.usedMinutes;
                }
                if (sub.remainingFreeMinutes <= 0) {
                    sub.chargingTotalEur += (sub.usedMinutes - sub.subscription.minutesIncluded) * sub.subscription.pricePerMinuteEuro;
                }
                session = new Session(forename, surname, doubleTime, usedService);
                this.sessions.add(session);
                break;
            case "Browsing and social networking":
                usedService = Service.BASN;
                session = new Session(forename ,surname, doubleTime, usedService);
                session.setDemandedDatarateMbits(usedService.demandedDatarateMbits);
                session.setAchievableDatarateMbits(terminal.supportedRanTechnologies[terminal.supportedRanTechnologies.length - 1].maxThroughputMbits * session.determineSignalStrength()); // Times random signal strength
                if(session.getAchievableDatarateMbits() >= usedService.demandedDatarateMbits) {
                    doubleTime = doubleTime * 60;
                    if (sub.remainingDataVolumeMb - ((session.getAchievableDatarateMbits() * doubleTime) / 8) < 0) {
                        sub.remainingDataVolumeMb = 0;
                        throw new IllegalArgumentException("Service interrupted due to no remaining Data volume");
                    } else {
                        sub.remainingDataVolumeMb -= (session.getAchievableDatarateMbits() * doubleTime) / 8;
                    }
                    if (sub.usedDataVolume + ((session.getAchievableDatarateMbits() * doubleTime) / 8) > sub.subscription.dataVolumeMB) {
                        sub.usedDataVolume = sub.subscription.dataVolumeMB;
                    } else {
                        sub.usedDataVolume += (session.getAchievableDatarateMbits() * doubleTime) / 8;
                    }
                    session.setUsedDataMb((session.getAchievableDatarateMbits() * doubleTime) / 8);
                    if (sub.remainingDataVolumeMb < 0) {
                        throw new IllegalArgumentException("Datavolume is empty");
                    }
                } else {
                    throw new IllegalArgumentException("Achievable Data rate is lower than the data rate needed for this service");
                }
                this.sessions.add(session);
                break;
            case "App download":
                usedService = Service.APL;
                session = new Session(forename ,surname, doubleTime, usedService);
                session.setDemandedDatarateMbits(usedService.demandedDatarateMbits);
                session.setAchievableDatarateMbits(terminal.supportedRanTechnologies[terminal.supportedRanTechnologies.length - 1].maxThroughputMbits * session.determineSignalStrength()); // Times random signal strength
                if(session.getAchievableDatarateMbits() >= usedService.demandedDatarateMbits) {
                    doubleTime = doubleTime * 60;
                    if (sub.remainingDataVolumeMb - ((session.getAchievableDatarateMbits() * doubleTime) / 8) < 0) {
                        sub.remainingDataVolumeMb = 0;
                        throw new IllegalArgumentException("Service interrupted due to no remaining Data volume");
                    } else {
                        sub.remainingDataVolumeMb -= (session.getAchievableDatarateMbits() * doubleTime) / 8;
                    }
                    if (sub.usedDataVolume + ((session.getAchievableDatarateMbits() * doubleTime) / 8) > sub.subscription.dataVolumeMB) {
                        sub.usedDataVolume = sub.subscription.dataVolumeMB;
                    } else {
                        sub.usedDataVolume += (session.getAchievableDatarateMbits() * doubleTime) / 8;
                    }
                    if (sub.remainingDataVolumeMb < 0) {
                        throw new IllegalArgumentException("Datavolume is empty");
                    }
                } else {
                    throw new IllegalArgumentException("Achievable Data rate is lower than the data rate needed for this service");
                }
                this.sessions.add(session);
                break;
            case "Adaptive HD video":
                usedService = Service.AHDV;
                session = new Session(forename ,surname, doubleTime, usedService);
                session.setDemandedDatarateMbits(usedService.demandedDatarateMbits);
                session.setAchievableDatarateMbits(terminal.supportedRanTechnologies[terminal.supportedRanTechnologies.length - 1].maxThroughputMbits * session.determineSignalStrength()); // Times random signal strength
                if(session.getAchievableDatarateMbits() >= usedService.demandedDatarateMbits) {
                    doubleTime = doubleTime * 60;
                    if (sub.remainingDataVolumeMb - ((session.getAchievableDatarateMbits() * doubleTime) / 8) < 0) {
                        sub.remainingDataVolumeMb = 0;
                        throw new IllegalArgumentException("Service interrupted due to no remaining Data volume");
                    } else {
                        sub.remainingDataVolumeMb -= (session.getAchievableDatarateMbits() * doubleTime) / 8;
                    }
                    if (sub.usedDataVolume + ((session.getAchievableDatarateMbits() * doubleTime) / 8) > sub.subscription.dataVolumeMB) {
                        sub.usedDataVolume = sub.subscription.dataVolumeMB;
                    } else {
                        sub.usedDataVolume += (session.getAchievableDatarateMbits() * doubleTime) / 8;
                    }
                    if (sub.remainingDataVolumeMb < 0) {
                        throw new IllegalArgumentException("Datavolume is empty");
                    }
                } else {
                    throw new IllegalArgumentException("Achievable Data rate (" + session.getAchievableDatarateMbits() + ") is lower than the data rate needed for this service (" + usedService.demandedDatarateMbits + ")");
                }
                this.sessions.add(session);
                break;
        };
        
        storage.storeSessions(sessions);
        return this.sessions;
    }

    public static void main(String[] args) {
        GuiViewModel vm = new GuiViewModel();
        vm.addSubscriber("Hannah", "Klein", "9829872689", "Samsung S42plus", "GreenMobil L");
        vm.addSession("Hannah", "Klein", "9", "Adaptive HD video");
    }

    public ArrayList<Invoice> addInvoice(){
        invoices.clear();
        for (Subscriber sub : subscribers) {
            Invoice invoice = new Invoice(mCC + mNC + sub.getMSIN(), sub.forename + " " + sub.surname, sub.usedDataVolume, sub.usedMinutes, sub.chargingTotalEur);
            sub.usedDataVolume = 0;
            sub.usedDataVolume = 0;
            sub.usedMinutes = 0;
            sub.chargingTotalEur = sub.subscription.basicfee;
            sub.remainingFreeMinutes = sub.subscription.minutesIncluded;
            sub.remainingDataVolumeMb = sub.subscription.dataVolumeMB;
            invoices.add(invoice);
        }
        // storage.storeInvoices(invoices);
        return invoices;
    }

    public ArrayList<Subscriber> getSubscribers() {
        this.subscribers = storage.getSubscribers();
        return this.subscribers;
    }

    public ArrayList<Session> getSessions() {
        this.sessions = storage.getSessions();
        return this.sessions;
    }

    public ArrayList<Invoice> getInvoices() {
        // this.invoices = storage.getInvoices();
        return this.invoices;
    }

    public ArrayList<Subscriber> editSubscriber(String forename, String surname, String mSIN, String TerminalType, String SubscribtionType) {
        for (int i = 0; i < subscribers.size(); i++) {
            if((subscribers.get(i).getMSIN()).equals(mSIN)) {
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
            case "Phair Phone":
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
                remainingDataVolumeMb = 500;
                chargingTotalEur = 8;
                remainingFreeMinutes = 0;
                subscribtionType = Subscription.GREEN_MOBIL_S;
                break;
            case "GreenMobil M":
                remainingDataVolumeMb = 2000;
                chargingTotalEur = 22;
                remainingFreeMinutes = 100;
                subscribtionType = Subscription.GREEN_MOBIL_M;
                break;
            case "GreenMobil L":
                remainingDataVolumeMb = 5000;
                chargingTotalEur = 42;
                remainingFreeMinutes = 150;
                subscribtionType = Subscription.GREEN_MOBIL_L;
        }
        return new Subscriber(remainingDataVolumeMb, chargingTotalEur, remainingFreeMinutes, forename, surname, mCC, mNC, mSIN, terminal, subscribtionType);
    }
}
