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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;

/**
 * Default gauge thresholds.
 * 
 * @author Andrea "Stock" Stocchero
 * @see IsThreshold
 *
 */
public enum GaugeThreshold implements IsThreshold
{
	/**
	 * the normal threshold
	 */
	normal(75D, new Color(140, 214, 16)),
	/**
	 * the warning threshold.
	 */
	warning(90D, new Color(239, 198, 0)),
	/**
	 * the critical threshold.
	 */
	critical(Double.MAX_VALUE, new Color(231, 24, 49));

	// value
	private final double value;
	// color
	private final IsColor color;

	/**
	 * Creates the threshold value using value and color
	 * 
	 * @param value threshold value
	 * @param color threshold color
	 */
	private GaugeThreshold(double value, IsColor color) {
		this.value = value;
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.enums.IsThreshold#getValue()
	 */
	@Override
	public double getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.enums.IsThreshold#getColor()
	 */
	@Override
	public IsColor getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.enums.IsThreshold#getName()
	 */
	@Override
	public String getName() {
		return name();
	}

	/**
	 * Creates a new threshold cloning the constant one. This is helpful to change standard color or value.
	 * 
	 * @return the threshold
	 */
	public Threshold getThreshold() {
		// clones threshold
		Threshold threshold = new Threshold(name());
		// sets values
		return threshold.setValue(value).setColor(color);
	}

}