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
 * Enumerates the calendar to manage the dates.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Calendar implements Key
{
	/**
	 * International key, to use for localization, for <b>buddhist</b> calendar.
	 */
	 BUDDHIST("buddhist"),

	/**
	 * International key, to use for localization, for <b>chinese</b> calendar.
	 */
	 CHINESE("chinese"),

	/**
	 * International key, to use for localization, for <b>coptic</b> calendar.
	 */
	 COPTIC("coptic"),

	/**
	 * International key, to use for localization, for <b>ethiopia</b> calendar.
	 */
	 ETHIOPIA("ethiopia"),

	/**
	 * International key, to use for localization, for <b>ethiopic</b> calendar.
	 */
	 ETHIOPIC("ethiopic"),

	/**
	 * International key, to use for localization, for <b>gregory</b> calendar.
	 */
	 GREGORY("gregory"),

	/**
	 * International key, to use for localization, for <b>hebrew</b> calendar.
	 */
	 HEBREW("hebrew"),

	/**
	 * International key, to use for localization, for <b>indian</b> calendar.
	 */
	 INDIAN("indian"),

	/**
	 * International key, to use for localization, for <b>islamic</b> calendar.
	 */
	 ISLAMIC("islamic"),

	/**
	 * International key, to use for localization, for <b>iso8601</b> calendar.
	 */
	 ISO8601("iso8601"),

	/**
	 * International key, to use for localization, for <b>japanese</b> calendar.
	 */
	 JAPANESE("japanese"),

	/**
	 * International key, to use for localization, for <b>persian</b> calendar.
	 */
	 PERSIAN("persian"),

	/**
	 * International key, to use for localization, for <b>roc</b> calendar.
	 */
	 ROC("roc");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private Calendar(String value) {
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
