package de.deltacorp;

import java.util.Random;

public class Session {
	private RanTechnology ranTechnology;
	public double chargingEur;
	public double signalStrength = 1;
	private double achievableDatarateMbits;
	private double demandedDatarateMbits;
	private double UsedDataMb = Math.min(achievableDatarateMbits, demandedDatarateMbits);
	private String surname;
	private double durationSeconds;
	private Service service;
	
	public Session(String surname, double durationSeconds, Service service) {
		this.surname = surname;
		this.durationSeconds = durationSeconds;
		this.service = service;
	}

	public RanTechnology getRanTechnology() {
		return ranTechnology;
	}

	public void setRanTechnology(RanTechnology ranTechnology) {
		this.ranTechnology = ranTechnology;
	}

	public double getChargingEur() {
		return chargingEur;
	}

	public void setChargingEur(double chargingEur) {
		this.chargingEur = chargingEur;
	}

	public double getAchievableDatarateMbits() {
		return achievableDatarateMbits;
	}

	public void setAchievableDatarateMbits(double achievableDatarateMbits) {
		this.achievableDatarateMbits = achievableDatarateMbits;
	}

	public double getDemandedDatarateMbits() {
		return Math.min(achievableDatarateMbits,demandedDatarateMbits);
	}

	public void setDemandedDatarateMbits(double demandedDatarateMbits) {
		this.demandedDatarateMbits = demandedDatarateMbits;
	}

	public double getUsedDataMb() {
		return UsedDataMb;
	}

	public void setUsedDataMb(double usedDataMb) {
		UsedDataMb = usedDataMb;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double getDurationSeconds() {
		return durationSeconds;
	}

	public void setDurationSeconds(double durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public double determineSignalStrength() {
		double random = Math.random();
		if(random>=0.5) {
			signalStrength=0.5;
		}
		else if(random>=0.25) {
			signalStrength=0.25;
		}
		else if(random>=0.1) {
			signalStrength=0.1;
		}
		else if(random<0.1) {
			signalStrength=0.0;
		}
		return signalStrength;
	}
	
}
