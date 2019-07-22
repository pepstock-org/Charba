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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Interface to define time object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultTime {

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @return If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be
	 *         Sunday.
	 */
	boolean isIsoWeekday();

//	/**
//	 * If defined, this will override the data maximum.
//	 * 
//	 * @return If defined, this will override the data maximum.
//	 */
//	Date getMax();
//
//	/**
//	 * If defined, this will override the data minimum.
//	 * 
//	 * @return If defined, this will override the data minimum.
//	 */
//	Date getMin();

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return If defined, dates will be rounded to the start of this unit.
	 */
	TimeUnit getRound();

	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @return The moment js format string to use for the tooltip.
	 */
	String getTooltipFormat();

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return If defined, will force the unit to be a certain type.
	 */
	TimeUnit getUnit();

	/**
	 * The number of units between grid lines.
	 * 
	 * @return The number of units between grid lines.
	 */
	double getStepSize();

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @return The minimum display format to be used for a time unit.
	 */
	TimeUnit getMinUnit();

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	String getParser();
}