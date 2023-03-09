package org.example;

public class Invoice {
	private String subscriberImsi;
	private String subscriberFullName;
	private double usedDataMb;
	private double usedMinutes;
	private double appliedChargesEur;
	
	
	
	public Invoice(String subscriberImsi, String subscriberFullName, double usedDataMb, double usedMinutes,
			double appliedChargesEur) {
		super();
		this.subscriberImsi = subscriberImsi;
		this.subscriberFullName = subscriberFullName;
		this.usedDataMb = usedDataMb;
		this.usedMinutes = usedMinutes;
		this.appliedChargesEur = appliedChargesEur;
	}
	public String getSubscriberImsi() {
		return subscriberImsi;
	}
	public void setSubscriberImsi(String subscriberImsi) {
		this.subscriberImsi = subscriberImsi;
	}
	public String getSubscriberFullName() {
		return subscriberFullName;
	}
	public void setSubscriberFullName(String subscriberFullName) {
		this.subscriberFullName = subscriberFullName;
	}
	public double getUsedDataMb() {
		return usedDataMb;
	}
	public void setUsedDataMb(double usedDataMb) {
		this.usedDataMb = usedDataMb;
	}
	public double getUsedMinutes() {
		return usedMinutes;
	}
	public void setUsedMinutes(double usedMinutes) {
		this.usedMinutes = usedMinutes;
	}
	public double getAppliedChargesEur() {
		return appliedChargesEur;
	}
	public void setAppliedChargesEur(double appliedChargesEur) {
		this.appliedChargesEur = appliedChargesEur;
	}
	
}
