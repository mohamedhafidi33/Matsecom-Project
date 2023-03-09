package org.example;

public enum Subscription {
	GREEN_MOBIL_S("GreenMobil S",8,0,0.08,500),
	GREEN_MOBIL_M("GreenMobil M",22,100,0.06,2),
	GREEN_MOBIL_L("GreenMobil L",42,150,0.04,5);
	
	private String name;
	private double basicfee;
	private double minutesIncluded;
	private double pricePerMinuteEuro;
	private double dataVolumeMB;
	
	private Subscription(String name, double basicfee, double minutesIncluded, double pricePerMinuteEuro,
			double dataVolumeMB) {
		this.name = name;
		this.basicfee = basicfee;
		this.minutesIncluded = minutesIncluded;
		this.pricePerMinuteEuro = pricePerMinuteEuro;
		this.dataVolumeMB = dataVolumeMB;
	}
	
	public String getName() {
		return name;
	}
	public double getBasicfee() {
		return basicfee;
	}
	public double getMinutesIncluded() {
		return minutesIncluded;
	}
	public double getPricePerMinuteEuro() {
		return pricePerMinuteEuro;
	}
	public double getDataVolumeMB() {
		return dataVolumeMB;
	}
	
}
