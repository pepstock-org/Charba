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
package org.pepstock.charba.client.impl.plugins.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Property to set the selection cleaner element alignment.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Align implements Key
{
	/**
	 * the property sets the left alignment.
	 */
	LEFT("left"),
	/**
	 * the property sets the left chart area alignment.
	 */
	LEFT_CHART_AREA("left_chartArea"),
	/**
	 * the property sets the center alignment.
	 */
	CENTER("center"),
	/**
	 * the property sets the center of chart area alignment.
	 */
	CENTER_CHART_AREA("center_chartArea"),
	/**
	 * the property sets the right chart area alignment.
	 */
	RIGHT_CHART_AREA("right_chartArea"),
	/**
	 * the property sets the right text alignment.
	 */
	RIGHT("right");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Align(String value) {
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