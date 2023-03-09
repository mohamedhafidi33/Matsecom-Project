package de.deltacorp;

public class GuiViewModel {
    public void addSubscriber(String forename, String surname, String mSIN, String TerminalType, String SubscribtionType){
        String mCC = "262";
        String mNC = "42";
        Terminal terminal;
        terminal = Terminal.PHAIR_PHONE;
        double remainingDataVolumeMb = 0;
        double chargingTotalEur = 0;
        double remainingFreeMinutes = 0;
        Subscription subscribtionType;
        subscribtionType = Subscription.GREEN_MOBIL_S;
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
        mSIN = IMSIText.getText();
        Subscriber.add(new Subscriber(remainingDataVolumeMb,
                chargingTotalEur,
                remainingFreeMinutes,
                ForenameText.getText(),
                SurenameText.getText(),
                mCC,
                mNC,
                mSIN,
                terminal,
                subscribtionType));
    }
}
