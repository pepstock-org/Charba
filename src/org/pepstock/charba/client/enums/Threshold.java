package org.pepstock.charba.client.enums;

public class Threshold implements IsThreshold{
	
	public static final String DEFAULT_VALUE_COLOR = GaugeThreshold.normal.getColor();
	
	private static final double DEFAULT_VALUE = Double.MAX_VALUE;
	
	private final String name;
	
	private double value = DEFAULT_VALUE;
	
	private String color = DEFAULT_VALUE_COLOR;

	public Threshold(String name) {
		this(name, DEFAULT_VALUE, DEFAULT_VALUE_COLOR);
	}
	
	public Threshold(String name, String color) {
		this(name, DEFAULT_VALUE, color);
	}

	public Threshold(String name, double value) {
		this(name, value, DEFAULT_VALUE_COLOR);
	}

	public Threshold(String name, double value, String color) {
		this.name = name;
		this.value = value;
		this.color = color;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String getColor() {
		return color;
	}

	/**
	 * @param value the value to set
	 */
	public Threshold setValue(double value) {
		this.value = value;
		return this;
	}

	/**
	 * @param color the color to set
	 */
	public Threshold setColor(String color) {
		this.color = color;
		return this;
	}
	
	public boolean isInRange(double valueToCheck, double lowLimit){
		return valueToCheck >= lowLimit && valueToCheck < getValue(); 
	}
	
}
