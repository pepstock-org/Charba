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
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;

import com.google.gwt.core.client.JsDate;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Time extends BaseModel<Scale, IsDefaultTime, NativeTime>{

	private final DisplayFormats displayFormats;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		round,
		unit,
	}

	/**
	 * Builds the object storing the chart instance and the axis which this grid lines belongs to.
	 * 
	 * @param chart chart instance.
	 * @param axis axis which this grid lines belongs to.
	 */
	Time(Scale scale, IsDefaultTime defaultValues, NativeTime delegated) {
		super(scale, defaultValues, delegated == null ? new NativeTime(): delegated);
		displayFormats = new DisplayFormats(this, getDelegated().getDisplayFormats());
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
		getDelegated().setIsoWeekday(isoWeekday);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @return If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday. Default is <code>true</code>.
	 */
	public boolean isIsoWeekday() {
		return Checker.check(getDelegated().isIsoWeekday(), getDefaultValues().isIsoWeekday());
	}
	
	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max If defined, this will override the data maximum.
	 */
	public void setMax(Date max) {
		getDelegated().setMax(fromDate(max));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return If defined, this will override the data maximum. Default is <code>null</code>.
	 */
	public Date getMax() {
		return toDate(Checker.check(getDelegated().getMax(), getDefaultValues().getMax()));
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public void setMin(Date min) {
		getDelegated().setMin(fromDate(min));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum. Default is <code>null</code>.
	 */
	public Date getMin() {
		return toDate(Checker.check(getDelegated().getMin(), getDefaultValues().getMin()));
	}
	
	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 */
	public void setRound(boolean round) {
		if (!round){
			getDelegated().removeRound();
		}
	}
	
	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public void setRound(TimeUnit round) {
		getDelegated().setRound(round.name());
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
		// gets value
		String value = Checker.check(getDelegated().getRound(), getDefaultValues().getRound());
		if (value == null){
			// returns no unit
			return null;
		} else if (value.equalsIgnoreCase(Boolean.FALSE.toString())){
			// if is a boolean FALSE value
			// returns no unit
			return null;
		}
		return Enumer.deserialize(value, TimeUnit.class, TimeUnit.millisecond);
	}
	
	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @param tooltipFormat The moment js format string to use for the tooltip.
	 */
	public void setTooltipFormat(String tooltipFormat) {
		getDelegated().setTooltipFormat(tooltipFormat);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @return The moment js format string to use for the tooltip. Default is <code>null</code>.
	 */
	public String getTooltipFormat() {
		return Checker.check(getDelegated().getTooltipFormat(), getDefaultValues().getTooltipFormat());
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type..
	 */
	public void setUnit(boolean unit) {
		if (!unit){
			getDelegated().removeUnit();
		}
	}
	
	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public void setUnit(TimeUnit unit) {
		getDelegated().setUnit(unit.name());
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
		// gets value
		String value = Checker.check(getDelegated().getUnit(), getDefaultValues().getUnit());
		if (value == null){
			// returns no unit
			return null;
		} else if (value.equalsIgnoreCase(Boolean.FALSE.toString())){
			// if is a boolean FALSE value
			// returns no unit
			return null;
		}
		return Enumer.deserialize(value, TimeUnit.class, TimeUnit.millisecond);
	}
	
	/**
	 * The number of units between grid lines.
	 * 
	 * @param stepSize The number of units between grid lines.
	 */
	public void setStepSize(int stepSize) {
		getDelegated().setStepSize(stepSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The number of units between grid lines.
	 * 
	 * @return The number of units between grid lines. Default is <code>1</code>.
	 */
	public int getStepSize() {
		return Checker.check(getDelegated().getStepSize(), getDefaultValues().getStepSize());
	}
	
	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @param unit The minimum display format to be used for a time unit.
	 * @see org.pepstock.charba.client.enums.TimeUnit
	 */
	public void setMinUnit(TimeUnit unit) {
		getDelegated().setMinUnit(unit.name());
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
		return Enumer.deserialize(getDelegated().getMinUnit(), getDefaultValues().getMinUnit(), TimeUnit.class, TimeUnit.millisecond);
	}
	
	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @param parser Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	public void setParser(String parser) {
		getDelegated().setParser(parser);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.  Default is <code>null</code>.
	 */
	public String getParser() {
		return Checker.check(getDelegated().getParser(), getDefaultValues().getParser());
	}
	
	/**
	 * FIXME
	 * @param date
	 * @return
	 */
	private JsDate fromDate(Date date) {
		return date != null ? JsDate.create((double) date.getTime()) : null;
	}
	
	/**
	 * FIXME
	 * @param date
	 * @return
	 */
	private Date toDate(JsDate date) {
		return date != null ? new Date((long)date.getTime()) : null;
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getTime() == null) {
			getParent().getDelegated().setTime(getDelegated());
		}
	}

}