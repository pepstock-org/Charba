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
	millisecond("h:mm:ss.SSS a"),
	/**
	 * Second time unit 
	 */
	second("h:mm:ss a"),
	/**
	 * Minute time unit 
	 */
	minute("h:mm a"),
	/**
	 * Hour time unit 
	 */
	hour("hA"),
	/**
	 * Day time unit 
	 */
	day("MMM D"),
	/**
	 * Week time unit 
	 */
	week("ll"),
	/**
	 * Month time unit 
	 */
	month("MMM YYYY"),
	/**
	 * Quarter time unit 
	 */
	quarter("[Q]Q - YYYY"),
	/**
	 * Year time unit 
	 */
	year("YYYY"),
	/**
	 * Unknown 
	 */
	unknown("");
	
	// default format based on time unit
	private final String defaultFormat;

	/**
	 * Creates a time unit with its default format.
	 * 
	 * @param format default format based on time unit 
	 */
	private TimeUnit(String format) {
		this.defaultFormat = format;
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