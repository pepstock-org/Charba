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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Color;

/**
 * Property to set the text alignment
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum GaugeThreshold implements IsThreshold
{
	/**
	 * the left property sets the left text alignment.
	 */
	normal(75D, new Color(140, 214, 16).toRGBA()),
	/**
	 * the bottom property sets the left text alignment.
	 */
	warning(90D, new Color(239, 198, 0).toRGBA()),
	/**
	 * the right property sets the left text alignment.
	 */
	critical(Double.MAX_VALUE, new Color(231, 24, 49).toRGBA());
	
	private final double value;
	
	private final String color;
	
	private GaugeThreshold(double value, String color) {
		this.value = value;
		this.color = color;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}



	@Override
	public String getName() {
		return name();
	}

	/**
	 * @return the threshold
	 */
	public Threshold getThreshold() {
		Threshold threshold = new Threshold(name());
		return threshold.setValue(value).setColor(color);
	}

}