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
package org.pepstock.charba.client.intl.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the possible value to set the representation of the month.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MixedItemStyle implements Key
{
	/**
	 * Numeric setting for month (e.g., 2).
	 */
	NUMERIC("numeric"),
	/**
	 * 2 digits setting for month (e.g., 02).
	 */
	TWO_DIGITS("2-digit"),
	/**
	 * Narrow setting for month (e.g., M).<br>
	 * Two months may have the same narrow style for some locales (e.g. May's narrow style is also M).
	 */
	NARROW("narrow"),
	/**
	 * Long setting for month (e.g., March).
	 */
	LONG("long"),
	/**
	 * Short setting for month (e.g., Mar).
	 */
	SHORT("short");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private MixedItemStyle(String value) {
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
