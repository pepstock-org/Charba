/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.impl.charts;

import org.pepstock.charba.client.colors.IsColor;

/**
 * Is the threshold to use for gauge chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.GaugeDataset#setThresholds(Threshold...)
 *
 */
public final class Threshold implements IsThreshold{
	
	/**
	 * Default color value.
	 * @see GaugeThreshold#normal
	 */
	public static final IsColor DEFAULT_VALUE_COLOR = GaugeThreshold.normal.getColor();
	
	/**
	 * Default value (see {@link java.lang.Double#MAX_VALUE}).
	 */
	private static final double DEFAULT_VALUE = Double.MAX_VALUE;
	
	private final String name;
	
	private double value = DEFAULT_VALUE;
	
	private IsColor color = DEFAULT_VALUE_COLOR;

	/**
	 * Creates a threshold with standard color ({@link org.pepstock.charba.client.enums.Threshold#DEFAULT_VALUE_COLOR}) and value ({@link java.lang.Double#MAX_VALUE}).
	 * @param name name of threshold
	 */
	public Threshold(String name) {
		this(name, DEFAULT_VALUE, DEFAULT_VALUE_COLOR);
	}
	
	/**
	 * Creates a threshold with standard value ({@link java.lang.Double#MAX_VALUE}).
	 * @param name name of threshold
	 * @param color color of threshold
	 */
	public Threshold(String name, IsColor color) {
		this(name, DEFAULT_VALUE, color);
	}

	/**
	 * Creates a threshold with standard color ({@link org.pepstock.charba.client.enums.Threshold#DEFAULT_VALUE_COLOR}).
	 * @param name name of threshold
	 * @param value value of threshold
	 */
	public Threshold(String name, double value) {
		this(name, value, DEFAULT_VALUE_COLOR);
	}

	/**
	 * Creates a threshold
	 * @param name name of threshold
	 * @param value value of threshold
	 * @param color color of threshold
	 */
	public Threshold(String name, double value, IsColor color) {
		this.name = name;
		this.value = value;
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.enums.IsThreshold#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.enums.IsThreshold#getValue()
	 */
	@Override
	public double getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.enums.IsThreshold#getColor()
	 */
	@Override
	public IsColor getColor() {
		return color;
	}

	/**
	 * @param value the value to set
	 * @return the threshold instance
	 */
	public Threshold setValue(double value) {
		this.value = value;
		return this;
	}

	/**
	 * @param color the color to set
	 * @return the threshold instance
	 */
	public Threshold setColor(IsColor color) {
		this.color = color;
		return this;
	}
	
	/**
	 * Checks if the value passed is into threshold.
	 * @param valueToCheck value to check
	 * @param lowLimit the low limit
	 * @return <code>true</code> is the value is inside the range, otherwise <code>false</code>.
	 */
	public boolean isInRange(double valueToCheck, double lowLimit){
		return valueToCheck >= lowLimit && valueToCheck < getValue(); 
	}
	
}
