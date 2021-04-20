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
 * Determines which information must be rendered in the meter or gauge chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Render implements Key
{
	/**
	 * Shows only the value
	 */
	VALUE("value"),
	/**
	 * Shows the percentage
	 */
	PERCENTAGE("percentage"),
	/**
	 * Shows value and label
	 */
	VALUE_AND_LABEL("valueAndLabel"),
	/**
	 * Shows percentage and label
	 */
	PERCENTAGE_AND_LABEL("percentageAndLabel");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Render(String value) {
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
}
