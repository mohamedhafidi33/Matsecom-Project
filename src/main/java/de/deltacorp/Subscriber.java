package de.deltacorp;

public class Subscriber {
	public double remainingDataVolumeMb;
	public double remainingFreeMinutes;
	public double chargingTotalEur;
	public String forename;
	public String surname;
	private String MCC;
	private String MNC;
	private String MSIN;
	public Terminal terminal;
	public Subscription subscription;

	public Subscriber(double remainingDataVolumeMb, double chargingTotalEur, double remainingFreeMinutes, String forename,
			String surname, String mCC, String mNC, String mSIN, Terminal terminal, Subscription subscription) {
		this.remainingDataVolumeMb = remainingDataVolumeMb;
		this.chargingTotalEur = chargingTotalEur;
		this.remainingFreeMinutes = remainingFreeMinutes;
		this.forename = forename;
		this.surname = surname;
		MCC = mCC;
		MNC = mNC;
		MSIN = mSIN;
		this.terminal = terminal;
		this.subscription = subscription;
	}

	public double getRemainingDataVolumeMb() {
		return this.remainingDataVolumeMb;
	}

	public void setRemainingDataVolumeMb(double remainingDataVolumeMb) {
		this.remainingDataVolumeMb = remainingDataVolumeMb;
	}

	public double getRemainingFreeMinutes() {
		return this.remainingFreeMinutes;
	}

	public void setRemainingFreeMinutes(double remainingFreeMinutes) {
		this.remainingFreeMinutes = remainingFreeMinutes;
	}

	public double getChargingTotalEur() {
		return this.chargingTotalEur;
	}

	public void setChargingTotalEur(double chargingTotalEur) {
		this.chargingTotalEur = chargingTotalEur;
	}

	public String getForename() {
		return this.forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMCC() {
		return this.MCC;
	}

	public void setMCC(String MCC) {
		this.MCC = MCC;
	}

	public String getMNC() {
		return this.MNC;
	}

	public void setMNC(String MNC) {
		this.MNC = MNC;
	}

	public String getMSIN() {
		return this.MSIN;
	}

	public void setMSIN(String MSIN) {
		this.MSIN = MSIN;
	}

	public Terminal getTerminal() {
		return this.terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}


}
