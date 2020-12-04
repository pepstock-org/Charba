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
package org.pepstock.charba.client.intl;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions;
import org.pepstock.charba.client.defaults.globals.DefaultDateTimeFormatOptions;
import org.pepstock.charba.client.intl.enums.Calendar;
import org.pepstock.charba.client.intl.enums.DateTimeStyle;
import org.pepstock.charba.client.intl.enums.FormatMatcher;
import org.pepstock.charba.client.intl.enums.HourCycle;
import org.pepstock.charba.client.intl.enums.MixedItemStyle;
import org.pepstock.charba.client.intl.enums.NumberItemStyle;
import org.pepstock.charba.client.intl.enums.StringItemStyle;
import org.pepstock.charba.client.intl.enums.TimeZone;
import org.pepstock.charba.client.intl.enums.TimeZoneName;

/**
 * The object configures a date time formatter.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat/DateTimeFormat">MDN</a> for more details.<br>
 * <br>
 * <b style="font-size: 16px">PAY ATTENTION</b><br>
 * The following methods are not supported on Edge and Safari.<br>
 * <ul>
 * <li>{@link DateTimeFormatOptions#setDateStyle(DateTimeStyle)} and {@link DateTimeFormatOptions#getDateStyle()}
 * <li>{@link DateTimeFormatOptions#setTimeStyle(DateTimeStyle)} and {@link DateTimeFormatOptions#getTimeStyle()}
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DateTimeFormatOptions extends BaseFormatOptions<IsDefaultDateTimeFormatOptions> implements IsDefaultDateTimeFormatOptions {

	/**
	 * Public factory to create a date time format options from a native object.
	 */
	public static final DateTimeFormatOptionsFactory FACTORY = new DateTimeFormatOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATE_STYLE("dateStyle"),
		TIME_STYLE("timeStyle"),
		CALENDAR("calendar"),
		DAY_PERIOD("dayPeriod"),
		TIME_ZONE("timeZone"),
		HOUR_12("hour12"),
		HOUR_CYCLE("hourCycle"),
		FORMAT_MATCHER("formatMatcher"),
		WEEK_DAY("weekday"),
		ERA("era"),
		YEAR("year"),
		MONTH("month"),
		DAY("day"),
		HOUR("hour"),
		MINUTE("minute"),
		SECOND("second"),
		TIME_ZONE_NAME("timeZoneName");

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
	 * Creates new number format options object.
	 */
	public DateTimeFormatOptions() {
		this(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	DateTimeFormatOptions(NativeObject nativeObject) {
		this(nativeObject, DefaultDateTimeFormatOptions.INSTANCE);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues default values for the options
	 */
	DateTimeFormatOptions(NativeObject nativeObject, IsDefaultDateTimeFormatOptions defaultValues) {
		super(nativeObject, defaultValues);
	}

	/**
	 * Sets the date formatting style to use when calling {@link DateTimeFormat#format(java.lang.Object)}.<br>
	 * <b>dateStyle</b> can be used with <b>timeStyle</b>, but not with other options (e.g. weekday, hour, month, etc.).
	 * 
	 * @param style the date formatting style
	 */
	public void setDateStyle(DateTimeStyle style) {
		setValue(Property.DATE_STYLE, style);
	}

	/**
	 * Returns the date formatting style to use when calling {@link DateTimeFormat#format(java.lang.Object)}.
	 * 
	 * @return the date formatting style
	 */
	@Override
	public DateTimeStyle getDateStyle() {
		return getValue(Property.DATE_STYLE, DateTimeStyle.values(), getDefaultValues().getDateStyle());
	}

	/**
	 * Sets the time formatting style to use when calling {@link DateTimeFormat#format(java.lang.Object)}. <b>timeStyle</b> can be used with <b>dateStyle</b>, but not with other
	 * options (e.g. weekday, hour, month, etc.).
	 * 
	 * @param style the time formatting style
	 */
	public void setTimeStyle(DateTimeStyle style) {
		setValue(Property.TIME_STYLE, style);
	}

	/**
	 * Returns the time formatting style to use when calling {@link DateTimeFormat#format(java.lang.Object)}.
	 * 
	 * @return the time formatting style
	 */
	@Override
	public DateTimeStyle getTimeStyle() {
		return getValue(Property.TIME_STYLE, DateTimeStyle.values(), getDefaultValues().getTimeStyle());
	}

	/**
	 * Sets the calendar to use.
	 * 
	 * @param calendar the calendar to use
	 */
	public void setCalendar(Calendar calendar) {
		setValue(Property.CALENDAR, calendar);
	}

	/**
	 * Returns the calendar to use.
	 * 
	 * @return the calendar to use
	 */
	@Override
	public Calendar getCalendar() {
		return getValue(Property.CALENDAR, Calendar.values(), getDefaultValues().getCalendar());
	}

	/**
	 * Sets the way day periods should be expressed.
	 * 
	 * @param dayPeriod the way day periods should be expressed
	 */
	public void setDayPeriod(StringItemStyle dayPeriod) {
		setValue(Property.DAY_PERIOD, dayPeriod);
	}

	/**
	 * Returns the way day periods should be expressed.
	 * 
	 * @return the way day periods should be expressed
	 */
	@Override
	public StringItemStyle getDayPeriod() {
		return getValue(Property.CALENDAR, StringItemStyle.values(), getDefaultValues().getDayPeriod());
	}

	/**
	 * Sets the time zone to use.
	 * 
	 * @param zone the time zone to use
	 */
	public void setTimeZone(TimeZone zone) {
		setValue(Property.TIME_ZONE, zone);
	}

	/**
	 * Returns the time zone to use.
	 * 
	 * @return the time zone to use
	 */
	@Override
	public TimeZone getTimeZone() {
		return getValue(Property.TIME_ZONE, TimeZone.values(), getDefaultValues().getTimeZone());
	}

	/**
	 * Sets whether to use 12-hour time (as opposed to 24-hour time).
	 * 
	 * @param hour12 whether to use 12-hour time (as opposed to 24-hour time)
	 */
	public void setHour12(boolean hour12) {
		setValue(Property.HOUR_12, hour12);
	}

	/**
	 * Returns whether to use 12-hour time (as opposed to 24-hour time).
	 * 
	 * @return whether to use 12-hour time (as opposed to 24-hour time)
	 */
	@Override
	public boolean isHour12() {
		return getValue(Property.HOUR_12, getDefaultValues().isHour12());
	}

	/**
	 * Sets the hour cycle to use.<br>
	 * The {@link DateTimeFormatOptions#setHour12(boolean)} option takes precedence in case both options have been specified.
	 * 
	 * @param hourCycle the hour cycle to use
	 */
	public void setHourCycle(HourCycle hourCycle) {
		setValue(Property.HOUR_CYCLE, hourCycle);
	}

	/**
	 * Returns the hour cycle to use.
	 * 
	 * @return the hour cycle to use
	 */
	@Override
	public HourCycle getHourCycle() {
		return getValue(Property.HOUR_CYCLE, HourCycle.values(), getDefaultValues().getHourCycle());
	}

	/**
	 * Sets the format matching algorithm to use.
	 * 
	 * @param format the format matching algorithm to use
	 */
	public void setFormatMatcher(FormatMatcher format) {
		setValue(Property.FORMAT_MATCHER, format);
	}

	/**
	 * Returns the format matching algorithm to use.
	 * 
	 * @return the format matching algorithm to use
	 */
	@Override
	public FormatMatcher getFormatMatcher() {
		return getValue(Property.FORMAT_MATCHER, FormatMatcher.values(), getDefaultValues().getFormatMatcher());
	}

	/**
	 * Sets the representation of the WeekDay.
	 * 
	 * @param style the representation of the WeekDay
	 */
	public void setWeekDay(StringItemStyle style) {
		setValue(Property.WEEK_DAY, style);
	}

	/**
	 * Returns the representation of the WeekDay.
	 * 
	 * @return the representation of the WeekDay
	 */
	@Override
	public StringItemStyle getWeekDay() {
		return getValue(Property.WEEK_DAY, StringItemStyle.values(), getDefaultValues().getWeekDay());
	}

	/**
	 * Sets the representation of the Era.
	 * 
	 * @param style the representation of the Era
	 */
	public void setEra(StringItemStyle style) {
		setValue(Property.ERA, style);
	}

	/**
	 * Returns the representation of the Era.
	 * 
	 * @return the representation of the Era
	 */
	@Override
	public StringItemStyle getEra() {
		return getValue(Property.ERA, StringItemStyle.values(), getDefaultValues().getEra());
	}

	/**
	 * Sets the representation of the Year.
	 * 
	 * @param style the representation of the Year
	 */
	public void setYear(NumberItemStyle style) {
		setValue(Property.YEAR, style);
	}

	/**
	 * Returns the representation of the Year.
	 * 
	 * @return the representation of the Year
	 */
	@Override
	public NumberItemStyle getYear() {
		return getValue(Property.YEAR, NumberItemStyle.values(), getDefaultValues().getYear());
	}

	/**
	 * Sets the representation of the Month.
	 * 
	 * @param style the representation of the Month
	 */
	public void setMonth(MixedItemStyle style) {
		setValue(Property.MONTH, style);
	}

	/**
	 * Returns the representation of the Month.
	 * 
	 * @return the representation of the Month
	 */
	@Override
	public MixedItemStyle getMonth() {
		return getValue(Property.MONTH, MixedItemStyle.values(), getDefaultValues().getMonth());
	}

	/**
	 * Sets the representation of the Day.
	 * 
	 * @param style the representation of the Day
	 */
	public void setDay(NumberItemStyle style) {
		setValue(Property.DAY, style);
	}

	/**
	 * Returns the representation of the Day.
	 * 
	 * @return the representation of the Day
	 */
	@Override
	public NumberItemStyle getDay() {
		return getValue(Property.DAY, NumberItemStyle.values(), getDefaultValues().getDay());
	}

	/**
	 * Sets the representation of the Hour.
	 * 
	 * @param style the representation of the Hour
	 */
	public void setHour(NumberItemStyle style) {
		setValue(Property.HOUR, style);
	}

	/**
	 * Returns the representation of the Hour.
	 * 
	 * @return the representation of the Hour
	 */
	@Override
	public NumberItemStyle getHour() {
		return getValue(Property.HOUR, NumberItemStyle.values(), getDefaultValues().getHour());
	}

	/**
	 * Sets the representation of the Minute.
	 * 
	 * @param style the representation of the Minute
	 */
	public void setMinute(NumberItemStyle style) {
		setValue(Property.MINUTE, style);
	}

	/**
	 * Returns the representation of the Minute.
	 * 
	 * @return the representation of the Minute
	 */
	@Override
	public NumberItemStyle getMinute() {
		return getValue(Property.MINUTE, NumberItemStyle.values(), getDefaultValues().getMinute());
	}

	/**
	 * Sets the representation of the Second.
	 * 
	 * @param style the representation of the Second
	 */
	public void setSecond(NumberItemStyle style) {
		setValue(Property.SECOND, style);
	}

	/**
	 * Returns the representation of the Second.
	 * 
	 * @return the representation of the Second
	 */
	@Override
	public NumberItemStyle getSecond() {
		return getValue(Property.SECOND, NumberItemStyle.values(), getDefaultValues().getSecond());
	}

	/**
	 * Sets the representation of the time zone name.
	 * 
	 * @param timeZoneName the representation of the time zone name
	 */
	public void setTimeZoneName(TimeZoneName timeZoneName) {
		setValue(Property.TIME_ZONE_NAME, timeZoneName);
	}

	/**
	 * Returns the representation of the time zone name.
	 * 
	 * @return the representation of the time zone name
	 */
	@Override
	public TimeZoneName getTimeZoneName() {
		return getValue(Property.TIME_ZONE_NAME, TimeZoneName.values(), getDefaultValues().getTimeZoneName());
	}

	/**
	 * Creates a date time format options by a native object and a default values instance.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static class DateTimeFormatOptionsFactory implements NativeObjectContainerFactory<DateTimeFormatOptions> {

		/**
		 * To avoid any instantiation
		 */
		private DateTimeFormatOptionsFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public DateTimeFormatOptions create(NativeObject nativeObject) {
			return create(nativeObject, null);
		}

		/**
		 * Creates a date time format options by a native object and a default values instance.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 * @param defaultValues default values for the options
		 * @return a number format options instance
		 */
		public DateTimeFormatOptions create(NativeObject nativeObject, IsDefaultDateTimeFormatOptions defaultValues) {
			return new DateTimeFormatOptions(nativeObject, defaultValues == null ? DefaultDateTimeFormatOptions.INSTANCE : defaultValues);
		}

	}

}
