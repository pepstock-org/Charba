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

import org.pepstock.charba.client.options.Time;

/**
 * Enumeration with ISO day of the week with 0 being Sunday and 6 being Saturday.
 * 
 * @author Andrea "Stock" Stocchero
 * @see Time
 */
public enum IsoWeekDay
{
	/**
	 * The day of the week is <b>SUNDAY</b>.
	 */
	SUNDAY(0),
	/**
	 * The day of the week is <b>MONDAY</b>.
	 */
	MONDAY(1),
	/**
	 * The day of the week is <b>TUESDAY</b>.
	 */
	TUESDAY(2),
	/**
	 * The day of the week is <b>WEDNESDAY</b>.
	 */
	WEDNESDAY(3),
	/**
	 * The day of the week is <b>THURSDAY</b>.
	 */
	THURSDAY(4),
	/**
	 * The day of the week is <b>FRIDAY</b>.
	 */
	FRIDAY(5),
	/**
	 * The day of the week is <b>SATURDAY</b>.
	 */
	SATURDAY(6);

	// name value of property
	private final int value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private IsoWeekDay(int value) {
		this.value = value;
	}

	/**
	 * Returns the ISO day of the week.
	 * 
	 * @return the ISO day of the week
	 */
	public int value() {
		return value;
	}

	/**
	 * Returns a ISO week day item by the passed value.
	 * 
	 * @param value ISO day of the week with 0 being Sunday and 6 being Saturday
	 * @return a ISO week day
	 */
	public static IsoWeekDay getIsoWeekDayByValue(int value) {
		// checks if argument s consistent
		if (value >= SUNDAY.value() && value <= SATURDAY.value()) {
			// scans all items
			for (IsoWeekDay item : values()) {
				// checks if equals
				if (item.value() == value) {
					return item;
				}
			}
		}
		// if here the value is not consistent
		// then returns the default
		return SUNDAY;
	}

}