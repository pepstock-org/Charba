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
package org.pepstock.charba.client.annotation.enums;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Property to set the position's line label to its normal position.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum LabelPosition implements Key
{
	/**
	 * The "center" property sets the line label at center of line. Default.
	 */
	CENTER("center"),
	/**
	 * The "start" property sets the line label at starting of line.
	 */
	START("start"),
	/**
	 * The "end" property sets the line label at ending of line.
	 */
	END("end");

	// percentage position
	private static final String PERCENTAGE_TEMPLATE = "{0}%";

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private LabelPosition(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Transforms the percentage value (between 0 and 1) in a string with format <code>{number}%</code>.
	 * 
	 * @param value the percentage value (between 0 and 1)
	 * @return the string representation of the percentage.
	 */
	public static String getAsPercentage(double value) {
		return Utilities.applyTemplate(PERCENTAGE_TEMPLATE, Checker.betweenOrDefault(value, 0, 1, 0.5) * 100);
	}

	/**
	 * Transforms the percentage value as string with format <code>{number}%</code> in a number (between 0 and 1).
	 * 
	 * @param value the string representation of the percentage.
	 * @param defaultValue the default value to use when the string format is not correct
	 * @return a percentage number (between 0 and 1)
	 */
	public static double getAsPercentage(String value, double defaultValue) {
		// checks if stored as percentage
		if (value != null && value.endsWith(Constants.PERCENT)) {
			// reads the percentage
			String doubleValue = value.substring(0, value.indexOf(Constants.PERCENT));
			// returns as double divided by 100
			return Double.parseDouble(doubleValue) / 100;
		}
		return defaultValue;
	}

}