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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultTime;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Time extends AbstractModel<AbstractScale, IsDefaultTime> implements IsDefaultTime {

	private final DisplayFormats displayFormats;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY_FORMATS("displayFormats"),
		ISO_WEEKDAY("isoWeekday"),
		PARSER("parser"),
		ROUND("round"),
		TOOLTIP_FORMAT("tooltipFormat"),
		UNIT("unit"),
		STEP_SIZE("stepSize"),
		MIN_UNIT("minUnit");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Time(AbstractScale scale, Key childKey, IsDefaultTime defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		// gets sub elements
		displayFormats = new DisplayFormats(this, Property.DISPLAY_FORMATS, getValue(Property.DISPLAY_FORMATS));
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
	 * If <code>true</code> and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @param isoWeekday if <code>true</code> and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 */
	public void setIsoWeekday(boolean isoWeekday) {
		setValue(Property.ISO_WEEKDAY, isoWeekday);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code> and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @return if <code>true</code> and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 */
	@Override
	public boolean isIsoWeekday() {
		return getValue(Property.ISO_WEEKDAY, getDefaultValues().isIsoWeekday());
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round if defined, this will override the data minimum.
	 */
	public void setRound(boolean round) {
		// if is setting false
		if (!round) {
			// removes property, using default
			remove(Property.ROUND);
		}
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round if defined, this will override the data minimum.
	 */
	public void setRound(TimeUnit round) {
		setValue(Property.ROUND, round);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return if defined, dates will be rounded to the start of this unit.
	 */
	@Override
	public TimeUnit getRound() {
		// checks if value is a boolean
		if (ObjectType.BOOLEAN.equals(type(Property.ROUND))) {
			// if is a boolean FALSE value
			// returns no unit
			return getDefaultValues().getRound();
		}
		// gets the value
		return getValue(Property.ROUND, TimeUnit.values(), getDefaultValues().getRound());
	}

	/**
	 * The format string to use for the tooltip.
	 * 
	 * @param tooltipFormat format string to use for the tooltip.
	 */
	public void setTooltipFormat(String tooltipFormat) {
		setValue(Property.TOOLTIP_FORMAT, tooltipFormat);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The format string to use for the tooltip.
	 * 
	 * @return the format string to use for the tooltip.
	 */
	@Override
	public String getTooltipFormat() {
		return getValue(Property.TOOLTIP_FORMAT, getDefaultValues().getTooltipFormat());
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type.
	 */
	public void setUnit(boolean unit) {
		// if is setting false
		if (!unit) {
			// removes property, using default
			remove(Property.UNIT);
		}
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit if defined, will force the unit to be a certain type.
	 */
	public void setUnit(TimeUnit unit) {
		setValue(Property.UNIT, unit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return if defined, will force the unit to be a certain type.
	 */
	@Override
	public TimeUnit getUnit() {
		// checks if value is a boolean
		if (ObjectType.BOOLEAN.equals(type(Property.UNIT))) {
			// if is a boolean FALSE value
			// returns no unit
			return getDefaultValues().getUnit();
		}
		// returns the value
		return getValue(Property.UNIT, TimeUnit.values(), getDefaultValues().getUnit());
	}

	/**
	 * The number of units between grid lines.
	 * 
	 * @param stepSize number of units between grid lines.
	 */
	public void setStepSize(double stepSize) {
		setValue(Property.STEP_SIZE, stepSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The number of units between grid lines.
	 * 
	 * @return number of units between grid lines.
	 */
	@Override
	public double getStepSize() {
		return getValue(Property.STEP_SIZE, getDefaultValues().getStepSize());
	}

	/**
	 * Sets the minimum display format to be used for a time unit.
	 * 
	 * @param unit minimum display format to be used for a time unit.
	 */
	public void setMinUnit(TimeUnit unit) {
		setValue(Property.MIN_UNIT, unit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the minimum display format to be used for a time unit.
	 * 
	 * @return minimum display format to be used for a time unit.
	 */
	@Override
	public TimeUnit getMinUnit() {
		return getValue(Property.MIN_UNIT, TimeUnit.values(), getDefaultValues().getMinUnit());
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @param parser defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	public void setParser(String parser) {
		setValue(Property.PARSER, parser);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @return defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	@Override
	public String getParser() {
		return getValue(Property.PARSER, getDefaultValues().getParser());
	}

}