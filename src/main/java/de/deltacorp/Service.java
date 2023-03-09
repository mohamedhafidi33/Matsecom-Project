/**
 * 
 */
package de.deltacorp;

/**
 * @author Hafidi
 *
 */
public enum Service {
	VOICE_CALL(new RanTechnology[] {RanTechnology.GSM},0),
	BASN(new RanTechnology[] {RanTechnology.HSPA,RanTechnology.LTE},2),
	APL(new RanTechnology[] {RanTechnology.HSPA,RanTechnology.LTE},10),
	AHDV(new RanTechnology[] {RanTechnology.HSPA,RanTechnology.LTE},100);
	
	private RanTechnology requiredRanTechnologies[];
	private double demandedDatarateMbits;
	
	private Service(RanTechnology[] requiredRanTechnologies, double demandedDatarateMbits) {
		this.requiredRanTechnologies = requiredRanTechnologies;
		this.demandedDatarateMbits = demandedDatarateMbits;
	}
	
	public RanTechnology[] getRequiredRanTechnologies() {
		return requiredRanTechnologies;
	}
	public double getDemandedDatarateMbits() {
		return demandedDatarateMbits;
	}
	
}
