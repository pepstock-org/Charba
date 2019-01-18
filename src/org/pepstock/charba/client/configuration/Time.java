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

import java.util.Date;

import org.pepstock.charba.client.enums.TimeUnit;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class Time extends AxisContainer {

	private final DisplayFormats displayFormats;

	/**
	 * Builds the object storing the axis which this grid lines belongs to.
	 * 
	 * @param axis axis which this grid lines belongs to.
	 */
	Time(Axis axis) {
		super(axis);
		// creates sub element
		displayFormats = new DisplayFormats(axis);
	}

	/**
	 * @return the displayFormats
	 */
	public DisplayFormats getDisplayFormats() {
		return displayFormats;
	}

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @param isoWeekday If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it
	 *            will be Sunday.
	 */
	public void setIsoWeekday(boolean isoWeekday) {
		getAxis().getScale().getTime().setIsoWeekday(isoWeekday);
	}

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @return If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be
	 *         Sunday.
	 */
	public boolean isIsoWeekday() {
		return getAxis().getScale().getTime().isIsoWeekday();
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max If defined, this will override the data maximum.
	 */
	public void setMax(Date max) {
		getAxis().getScale().getTime().setMax(max);
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return If defined, this will override the data maximum.
	 */
	public Date getMax() {
		return getAxis().getScale().getTime().getMax();
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public void setMin(Date min) {
		getAxis().getScale().getTime().setMin(min);
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum.
	 */
	public Date getMin() {
		return getAxis().getScale().getTime().getMin();
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
	 * The moment js format string to use for the tooltip.
	 * 
	 * @param tooltipFormat The moment js format string to use for the tooltip.
	 */
	public void setTooltipFormat(String tooltipFormat) {
		getAxis().getScale().getTime().setTooltipFormat(tooltipFormat);
	}

	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @return The moment js format string to use for the tooltip.
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
	 * The number of units between grid lines.
	 * 
	 * @param stepSize The number of units between grid lines.
	 */
	public void setStepSize(int stepSize) {
		getAxis().getScale().getTime().setStepSize(stepSize);
	}

	/**
	 * The number of units between grid lines.
	 * 
	 * @return The number of units between grid lines.
	 */
	public int getStepSize() {
		return getAxis().getScale().getTime().getStepSize();
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
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @param parser Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	public void setParser(String parser) {
		getAxis().getScale().getTime().setParser(parser);
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	public String getParser() {
		return getAxis().getScale().getTime().getParser();
	}
}