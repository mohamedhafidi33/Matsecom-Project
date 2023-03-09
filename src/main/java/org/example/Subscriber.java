package org.example;

import java.io.Serializable;

public class Subscriber {
	private double dataVolumeMb;
	private double chargingTotalEur;
	private double remainingFreeMinutes;
	private String forename;
	private String surname;
	private String MCC;
	private String MNC;
	private String MSIN;
	private Terminal terminal;
	private Subscription subscription;
	public Subscriber(double dataVolumeMb, double chargingTotalEur, double remainingFreeMinutes, String forename,
			String surname, String mCC, String mNC, String mSIN, Terminal terminal, Subscription subscription) {
		super();
		this.dataVolumeMb = dataVolumeMb;
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
