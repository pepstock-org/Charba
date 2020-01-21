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
 * Controls the data distribution along the scale.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum TimeUnit implements Key
{
	/**
	 * Millisecond time unit
	 */
	MILLISECOND("millisecond"),
	/**
	 * Second time unit
	 */
	SECOND("second"),
	/**
	 * Minute time unit
	 */
	MINUTE("minute"),
	/**
	 * Hour time unit
	 */
	HOUR("hour"),
	/**
	 * Day time unit
	 */
	DAY("day"),
	/**
	 * Week time unit
	 */
	WEEK("week"),
	/**
	 * Month time unit
	 */
	MONTH("month"),
	/**
	 * Quarter time unit
	 */
	QUARTER("quarter"),
	/**
	 * Year time unit
	 */
	YEAR("year");

	// name value of property
	private final String value;

	/**
	 * Creates a time unit with its property name to use into options.
	 * 
	 * @param value time unit as string
	 */
	private TimeUnit(String value) {
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