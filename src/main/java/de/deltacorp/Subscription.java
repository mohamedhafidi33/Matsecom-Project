package de.deltacorp;

public enum Subscription {
	GREEN_MOBIL_S("GreenMobil S",8,0,0.08,500),
	GREEN_MOBIL_M("GreenMobil M",22,100,0.06,2000),
	GREEN_MOBIL_L("GreenMobil L",42,150,0.04,5000);
	
	public final String name;
	public final double basicfee;
	public final double minutesIncluded;
	public final double pricePerMinuteEuro;
	public final double dataVolumeMB;
	
	private Subscription(String name, double basicfee, double minutesIncluded, double pricePerMinuteEuro,
			double dataVolumeMB) {
		this.name = name;
		this.basicfee = basicfee;
		this.minutesIncluded = minutesIncluded;
		this.pricePerMinuteEuro = pricePerMinuteEuro;
		this.dataVolumeMB = dataVolumeMB;
	}
	
}
