package de.deltacorp;

import java.util.HashMap;

public enum RanTechnology {
	GSM("2G(GSM)",0.0,new HashMap<SignalStrength,Double>(){{
		put(SignalStrength.NOT_AVAILABLE,0.0);
	}},true),
	HSPA("3G(HSPA)",20,new HashMap<SignalStrength,Double>(){{
		put(SignalStrength.GOOD,0.5);
		put(SignalStrength.MIDDLE,0.25);
		put(SignalStrength.BAD,0.1);
		put(SignalStrength.NOT_AVAILABLE,0.0);
	}},false),
	LTE("4G(LTE)",300,new HashMap<SignalStrength,Double>(){{
		put(SignalStrength.GOOD,0.5);
		put(SignalStrength.MIDDLE,0.25);
		put(SignalStrength.BAD,0.1);
		put(SignalStrength.NOT_AVAILABLE,0.0);
	}},false);
	
	public String name;
	public double maxThroughputMbits;
	public HashMap<SignalStrength,Double>throughputFactors ;
	public boolean isSupportingVoiceCalls;
	
	
	
	private RanTechnology(String name, double maxThroughputMbits, HashMap<SignalStrength, Double> throughputFactors,
			 boolean isSupportingVoiceCalls) {
		this.name = name;
		this.maxThroughputMbits = maxThroughputMbits;
		this.throughputFactors = throughputFactors;
		this.isSupportingVoiceCalls = isSupportingVoiceCalls;
	}

}
