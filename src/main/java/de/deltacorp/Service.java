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
	
	public final RanTechnology requiredRanTechnologies[];
	public final double demandedDatarateMbits;
	
	private Service(RanTechnology[] requiredRanTechnologies, double demandedDatarateMbits) {
		this.requiredRanTechnologies = requiredRanTechnologies;
		this.demandedDatarateMbits = demandedDatarateMbits;
	}
	
}
