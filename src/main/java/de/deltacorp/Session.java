package de.deltacorp;

import java.util.Random;

public class Session {
	private RanTechnology ranTechnology;
	public double chargingEur;
	private double achievableDatarateMbits;
	private double demandedDatarateMbits;
	private double UsedDataMb ;
	private String username;
	private double durationSeconds;
	private Service service;
	
	public Session(String username, double durationSeconds, Service service) {
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	private double determineSignalStrength() {
		Random rd = new Random(); 
		double random = rd.nextDouble();
		double signalStrength = 0;
		if(random>=0.5) {
			signalStrength=0.5;
		}
		if(random>=0.25) {
			signalStrength=0.25;
		}
		if(random>=0.1) {
			signalStrength=0.1;
		}
		if(random<0.1) {
			signalStrength=0.0;
		}
		return signalStrength;
	}
	
}
