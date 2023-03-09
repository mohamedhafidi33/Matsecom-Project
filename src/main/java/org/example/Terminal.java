/**
 * 
 */
package org.example;

/**
 * @author Hafidi
 *
 */
public enum Terminal {
	PHAIR_PHONE("PhairPhone",new RanTechnology[] {RanTechnology.GSM,RanTechnology.HSPA}),
	PEAR_APHONE_4s("Pear aphone 4s",new RanTechnology[] {RanTechnology.GSM,RanTechnology.HSPA}),
	SAMSUNG_S42PLUS("Samsung S42plus",new RanTechnology[] {RanTechnology.GSM,RanTechnology.HSPA,RanTechnology.LTE});
	
	private String name;
	private RanTechnology[] supportedRanTechnologies;
	
	private Terminal(String name,RanTechnology[] supportedRanTechnologies) {
		this.name = name;
		this.supportedRanTechnologies = supportedRanTechnologies;
	}

	public String getName() {
		return name;
	}

	public RanTechnology[] getSupportedRanTechnologies() {
		return supportedRanTechnologies;
	}
	
	
}
