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
package org.pepstock.charba.client.jsinterop.options;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Time extends AbstractModel<Scale, IsDefaultTime> implements IsDefaultTime{

	private final DisplayFormats displayFormats;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		// sub elements,
		displayFormats,
		// properties
		isoWeekday,
		max,
		min,
		parser,
		round,
		tooltipFormat,
		unit,
		stepSize,
		minUnit
	}


	/**
	 * Builds the object storing the chart instance and the axis which this grid lines belongs to.
	 * 
	 * @param chart chart instance.
	 * @param axis axis which this grid lines belongs to.
	 */
	Time(Scale scale, Key childKey, IsDefaultTime defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		displayFormats = new DisplayFormats(this, Property.displayFormats, getValue(Property.displayFormats));
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
	 * @param isoWeekday If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 */
	public void setIsoWeekday(boolean isoWeekday) {
		setValue(Property.isoWeekday, isoWeekday);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @return If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday. Default is <code>true</code>.
	 */
	public boolean isIsoWeekday() {
		return getValue(Property.isoWeekday, getDefaultValues().isIsoWeekday());
	}
	
	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max If defined, this will override the data maximum.
	 */
	public void setMax(Date max) {
		setValue(Property.max, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return If defined, this will override the data maximum. Default is <code>null</code>.
	 */
	public Date getMax() {
		return getValue(Property.max, getDefaultValues().getMax());
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public void setMin(Date min) {
		setValue(Property.min, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum. Default is <code>null</code>.
	 */
	public Date getMin() {
		return getValue(Property.min, getDefaultValues().getMin());
	}
	
	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 */
	public void setRound(boolean round) {
		if (!round){
			remove(Property.round);
		}
	}
	
	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public void setRound(TimeUnit round) {
		setValue(Property.round, round);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return If defined, dates will be rounded to the start of this unit. Default is <code>null</code>.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public TimeUnit getRound() {
		if (ObjectType.Boolean.equals(type(Property.round))){
			// if is a boolean FALSE value
			// returns no unit
			return getDefaultValues().getRound();
		}
		return getValue(Property.round, TimeUnit.class, getDefaultValues().getRound());
	}
	
	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @param tooltipFormat The moment js format string to use for the tooltip.
	 */
	public void setTooltipFormat(String tooltipFormat) {
		setValue(Property.tooltipFormat, tooltipFormat);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @return The moment js format string to use for the tooltip. Default is <code>null</code>.
	 */
	public String getTooltipFormat() {
		return getValue(Property.tooltipFormat, getDefaultValues().getTooltipFormat());
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type..
	 */
	public void setUnit(boolean unit) {
		if (!unit){
			remove(Property.unit);
		}
	}
	
	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public void setUnit(TimeUnit unit) {
		setValue(Property.unit, unit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return If defined, will force the unit to be a certain type. Default is <code>null</code>.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public TimeUnit getUnit() {
		if (ObjectType.Boolean.equals(type(Property.unit))){
			// if is a boolean FALSE value
			// returns no unit
			return getDefaultValues().getUnit();
		}
		return getValue(Property.unit, TimeUnit.class, getDefaultValues().getUnit());
	}
	
	/**
	 * The number of units between grid lines.
	 * 
	 * @param stepSize The number of units between grid lines.
	 */
	public void setStepSize(int stepSize) {
		setValue(Property.stepSize, stepSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The number of units between grid lines.
	 * 
	 * @return The number of units between grid lines. Default is <code>1</code>.
	 */
	public int getStepSize() {
		return getValue(Property.stepSize, getDefaultValues().getStepSize());
	}
	
	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @param unit The minimum display format to be used for a time unit.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public void setMinUnit(TimeUnit unit) {
		setValue(Property.minUnit, unit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @return The minimum display format to be used for a time unit. Default is {@link org.pepstock.charba.client.enums.TimeUnit#millisecond}.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public TimeUnit getMinUnit() {
		return getValue(Property.minUnit, TimeUnit.class, getDefaultValues().getMinUnit());
	}
	
	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @param parser Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	public void setParser(String parser) {
		setValue(Property.parser, parser);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.  Default is <code>null</code>.
	 */
	public String getParser() {
		return getValue(Property.parser, getDefaultValues().getParser());
	}
	
}