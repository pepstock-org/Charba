/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultTime;
import org.pepstock.charba.client.enums.IsoWeekDay;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * The time configuration is used to configure how time and time series axes must configure the tick marks.
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
		MIN_UNIT("minUnit");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * Sets the ISO day of the week.
	 * 
	 * @param isoWeekday ISO day of the week
	 */
	public void setIsoWeekday(IsoWeekDay isoWeekday) {
		// checks if argument consistent
		if (isoWeekday != null) {
			setValueAndAddToParent(Property.ISO_WEEKDAY, isoWeekday.value());
		} else {
			// if here argument not consistent
			// then removes the key
			remove(Property.ISO_WEEKDAY);
		}
	}

	/**
	 * Returns the ISO day of the week with 0 being Sunday and 6 being Saturday.
	 * 
	 * @return ISO day of the week with 0 being Sunday and 6 being Saturday
	 */
	@Override
	public IsoWeekDay getIsoWeekday() {
		// gets week day as number
		int isoWeekday = getValue(Property.ISO_WEEKDAY, IsoWeekDay.SUNDAY.value());
		// searches the value in the enumeration
		return IsoWeekDay.getIsoWeekDayByValue(isoWeekday);
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
		setValueAndAddToParent(Property.ROUND, round);
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return if defined, dates will be rounded to the start of this unit.
	 */
	@Override
	public TimeUnit getRound() {
		// checks if value is a boolean
		if (isType(Property.ROUND, ObjectType.BOOLEAN)) {
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
		setValueAndAddToParent(Property.TOOLTIP_FORMAT, tooltipFormat);
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
		setValueAndAddToParent(Property.UNIT, unit);
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return if defined, will force the unit to be a certain type.
	 */
	@Override
	public TimeUnit getUnit() {
		// checks if value is a boolean
		if (isType(Property.UNIT, ObjectType.BOOLEAN)) {
			// if is a boolean FALSE value
			// returns no unit
			return getDefaultValues().getUnit();
		}
		// returns the value
		return getValue(Property.UNIT, TimeUnit.values(), getDefaultValues().getUnit());
	}

	/**
	 * Sets the minimum display format to be used for a time unit.
	 * 
	 * @param unit minimum display format to be used for a time unit.
	 */
	public void setMinUnit(TimeUnit unit) {
		setValueAndAddToParent(Property.MIN_UNIT, unit);
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
	 * Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 * 
	 * @param parser defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 */
	public void setParser(String parser) {
		setValueAndAddToParent(Property.PARSER, parser);
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 * 
	 * @return defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 */
	@Override
	public String getParser() {
		return getValue(Property.PARSER, getDefaultValues().getParser());
	}

}