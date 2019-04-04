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
 * Controls the data distribution along the scale.<br>
 * About available formats, see <a href="http://momentjs.com/docs/#/displaying/format/">moment.js</a>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum TimeUnit implements Key
{

	/**
	 * Millisecond time unit
	 */
	MILLISECOND("millisecond", "h:mm:ss.SSS a"),
	/**
	 * Second time unit
	 */
	SECOND("second", "h:mm:ss a"),
	/**
	 * Minute time unit
	 */
	MINUTE("minute", "h:mm a"),
	/**
	 * Hour time unit
	 */
	HOUR("hour", "hA"),
	/**
	 * Day time unit
	 */
	DAY("day", "MMM D"),
	/**
	 * Week time unit
	 */
	WEEK("week", "ll"),
	/**
	 * Month time unit
	 */
	MONTH("month", "MMM YYYY"),
	/**
	 * Quarter time unit
	 */
	QUARTER("quarter", "[Q]Q - YYYY"),
	/**
	 * Year time unit
	 */
	YEAR("year", "YYYY"),
	/**
	 * Unknown
	 */
	UNKNOWN("unknown", "");

	// name value of property
	private final String value;
	// default format based on time unit
	private final String defaultFormat;

	/**
	 * Creates a time unit with its default format.
	 * 
	 * @param format default format based on time unit
	 */
	private TimeUnit(String value, String format) {
		this.value = value;
		this.defaultFormat = format;
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
	 * returns the default format for the time unit.
	 * 
	 * @return the format
	 */
	public String getDefaultFormat() {
		return defaultFormat;
	}

}