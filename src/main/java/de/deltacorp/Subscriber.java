package de.deltacorp;

public class Subscriber {
	private double remainingDataVolumeMb;
	private double remainingFreeMinutes;
	private double chargingTotalEur;
	private String forename;
	private String surname;
	private String MCC;
	private String MNC;
	private String MSIN;
	private Terminal terminal;
	private Subscription subscription;

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
	
}
