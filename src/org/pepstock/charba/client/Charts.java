package org.pepstock.charba.client;

import java.util.HashMap;
import java.util.Map;

public final class Charts {

	private static final Map<String, AbstractChart<?, ?>> CHARTS = new HashMap<String, AbstractChart<?, ?>>();
	
	/**
	 * To avoid any instantiation
	 */
	private Charts() {
	}

	static void add(AbstractChart<?, ?> chart){
		CHARTS.put(chart.getId(), chart);
	}
	
	public static AbstractChart<?, ?> get(String chartId){
		return CHARTS.get(chartId);
	}
	
	
	static void remove(String chartId){
		CHARTS.remove(chartId);
	}
	
}
