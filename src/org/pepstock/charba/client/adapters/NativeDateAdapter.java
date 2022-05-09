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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * It maps a date adapter of CHART.JS.<br>
 * An adapter is the reference to an external date library.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.CHART_ADAPTERS_DATE, namespace = JsPackage.GLOBAL)
final class NativeDateAdapter {

	/**
	 * Returns a map of time formats for the supported formatting units defined in unit.
	 * 
	 * @return a map of time formats for the supported formatting units defined in unit
	 */
	@JsMethod
	native NativeObject formats();

	/**
	 * Parses the given value and return the associated timestamp.
	 * 
	 * @param time the value to parse (usually comes from the data)
	 * @param format the expected data format
	 * @return number date representation or <code>null</code>
	 */
	@JsMethod
	native double parse(String time, String format);

	/**
	 * Returns the formatted date in the specified format for a given timestamp.
	 * 
	 * @param time the timestamp to format
	 * @param format the date/time token
	 * @return a date time string representation
	 */
	@JsMethod
	native String format(double time, String format);

	/**
	 * Adds the specified amount of unit to the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param amount the amount to add
	 * @param unit the unit as string
	 * @return new timestamp with added units
	 */
	@JsMethod
	native double add(double time, double amount, String unit);

	/**
	 * Returns the number of unit between the given timestamps.
	 * 
	 * @param maxTime the input timestamp (reference)
	 * @param minTime the timestamp to subtract
	 * @param unit the unit as string
	 * @return the number of unit between the given timestamps
	 */
	@JsMethod
	native double diff(double maxTime, double minTime, String unit);

	/**
	 * Returns the start of unit for the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param unit the unit as string
	 * @param weekday the ISO day of the week with 1 being Monday and 7 being Sunday (only needed if parameter "unit" is isoWeek).
	 * @return the start of unit for the given timestamp
	 */
	@JsMethod
	native double startOf(double time, String unit, int weekday);

	/**
	 * Returns end of unit for the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param unit the unit as string
	 * @return the end of unit for the given timestamp
	 */
	@JsMethod
	native double endOf(double time, String unit);

}
