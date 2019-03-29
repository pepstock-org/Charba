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

import org.pepstock.charba.client.commons.Key;

/**
 * Determines the interpolation mode of lines.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum CubicInterpolationMode implements Key
{

	/**
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of
	 * datasets.<br>
	 * Default.
	 */
	defaults("default"),
	/**
	 * The 'monotone' algorithm is more suited to datasets: it preserves monotonicity (or piecewise monotonicity) of the dataset
	 * being interpolated, and ensures local extremums (if any) stay at input data points.
	 */
	monotone("monotone");

	// value to assign into configuration
	private final String value;

	/**
	 * Creates the object with the value to assign into configuration.
	 * 
	 * @param value value to assign into configuration
	 */
	private CubicInterpolationMode(String value) {
		this.value = value;
	}

	/**
	 * Returns the value to assign into configuration.
	 * 
	 * @return the value to assign into configuration
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * Returns the interpolation mode by the string value.
	 * 
	 * @param value value of interpolation mode
	 * @param defaultValue default interpolation mode
	 * @return if value is <code>null</code>, returns the default value
	 */
	public static CubicInterpolationMode getModeByValue(String value, CubicInterpolationMode defaultValue) {
		// checks if value is consistent
		if (value != null) {
			// scans all modes to search the one which has got that value
			for (CubicInterpolationMode mode : values()) {
				// the value is equals to the argument
				if (mode.getValue().equalsIgnoreCase(value)) {
					// returns the interpolation mode
					return mode;
				}
			}
		}
		// if here, not found
		// then returns the defaults
		return defaultValue != null ? defaultValue : defaults;
	}

}