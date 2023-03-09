package de.deltacorp;

import java.util.HashMap;

public enum RanTechnology {
	GSM("2G(GSM)",0.0,new HashMap<SignalStrength,Double>(),true),
	HSPA("3G(HSPA)",20,new HashMap<SignalStrength,Double>(),false),
	LTE("4G(LTE)",300,new HashMap<SignalStrength,Double>(),false);
	
	private String name;
	private double maxThroughputMbits;
	private HashMap<SignalStrength,Double>throughputFactors ;
	private boolean isSupportingVoiceCalls;
	
	
	
	private RanTechnology(String name, double maxThroughputMbits, HashMap<SignalStrength, Double> throughputFactors,
			 boolean isSupportingVoiceCalls) {
		this.name = name;
		this.maxThroughputMbits = maxThroughputMbits;
		this.throughputFactors = throughputFactors;
		this.isSupportingVoiceCalls = isSupportingVoiceCalls;
	}

	
}
