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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.enums.IsoWeekDay;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * The time configuration is used to configure how time and time series axes must configure the tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Time extends AxisContainer {

	private final DisplayFormats displayFormats;

	/**
	 * Builds the object storing the axis which this time belongs to.
	 * 
	 * @param axis axis which this this belongs to.
	 */
	Time(Axis axis) {
		super(axis);
		// creates sub element
		displayFormats = new DisplayFormats(axis);
	}

	/**
	 * Returns the displayFormats element.
	 * 
	 * @return the displayFormats
	 */
	public DisplayFormats getDisplayFormats() {
		return displayFormats;
	}

	/**
	 * Sets the ISO day of the week.
	 * 
	 * @param isoWeekday ISO day of the week
	 */
	public void setIsoWeekday(IsoWeekDay isoWeekday) {
		getAxis().getScale().getTime().setIsoWeekday(isoWeekday);
	}

	/**
	 * Returns the ISO day of the week with 0 being Sunday and 6 being Saturday.
	 * 
	 * @return ISO day of the week with 0 being Sunday and 6 being Saturday
	 */
	public IsoWeekDay getIsoWeekday() {
		return getAxis().getScale().getTime().getIsoWeekday();
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 */
	public void setRound(boolean round) {
		getAxis().getScale().getTime().setRound(round);
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 */
	public void setRound(TimeUnit round) {
		getAxis().getScale().getTime().setRound(round);
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return If defined, dates will be rounded to the start of this unit.
	 */
	public TimeUnit getRound() {
		return getAxis().getScale().getTime().getRound();
	}

	/**
	 * The LUXON format string to use for the tooltip.
	 * 
	 * @param tooltipFormat The LUXON format string to use for the tooltip.
	 */
	public void setTooltipFormat(String tooltipFormat) {
		getAxis().getScale().getTime().setTooltipFormat(tooltipFormat);
	}

	/**
	 * The LUXON format string to use for the tooltip.
	 * 
	 * @return The LUXON format string to use for the tooltip.
	 */
	public String getTooltipFormat() {
		return getAxis().getScale().getTime().getTooltipFormat();
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type.
	 */
	public void setUnit(boolean unit) {
		getAxis().getScale().getTime().setUnit(unit);
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type.
	 */
	public void setUnit(TimeUnit unit) {
		getAxis().getScale().getTime().setUnit(unit);
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return If defined, will force the unit to be a certain type.
	 */
	public TimeUnit getUnit() {
		return getAxis().getScale().getTime().getUnit();
	}

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @param unit The minimum display format to be used for a time unit.
	 */
	public void setMinUnit(TimeUnit unit) {
		getAxis().getScale().getTime().setMinUnit(unit);
	}

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @return The minimum display format to be used for a time unit.
	 */
	public TimeUnit getMinUnit() {
		return getAxis().getScale().getTime().getMinUnit();
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 * 
	 * @param parser Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 */
	public void setParser(String parser) {
		getAxis().getScale().getTime().setParser(parser);
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 */
	public String getParser() {
		return getAxis().getScale().getTime().getParser();
	}
}