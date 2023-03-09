/**
 * 
 */
package de.deltacorp;

/**
 * @author Hafidi
 *
 */
enum Terminal {
	PHAIR_PHONE("Phair Phone",new RanTechnology[] {RanTechnology.GSM,RanTechnology.HSPA}),
	PEAR_APHONE_4S("Pear aphone 4s",new RanTechnology[] {RanTechnology.GSM,RanTechnology.HSPA}),
	SAMSUNG_S42PLUS("Samsung S42plus",new RanTechnology[] {RanTechnology.GSM,RanTechnology.HSPA,RanTechnology.LTE});
	
	public final String name;
	public final RanTechnology[] supportedRanTechnologies;
	
	private Terminal(String name,RanTechnology[] supportedRanTechnologies) {
		this.name = name;
		this.supportedRanTechnologies = supportedRanTechnologies;
	}
	
	
}
