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

import org.pepstock.charba.client.intl.DateTimeFormat;
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
 * Interface to define the date time format options for internationalization.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultDateTimeFormatOptions extends IsDefaultBaseFormatOptions {

	/**
	 * Returns the date formatting style to use when calling {@link DateTimeFormat#format(java.lang.Object)}.
	 * 
	 * @return the date formatting style
	 */
	DateTimeStyle getDateStyle();

	/**
	 * Returns the time formatting style to use when calling {@link DateTimeFormat#format(java.lang.Object)}.
	 * 
	 * @return the time formatting style
	 */
	DateTimeStyle getTimeStyle();
	/**
	 * Returns the calendar to use.
	 * 
	 * @return the calendar to use
	 */
	Calendar getCalendar();

	/**
	 * Returns the way day periods should be expressed.
	 * 
	 * @return the way day periods should be expressed
	 */
	StringItemStyle getDayPeriod();

	/**
	 * Returns the time zone to use.
	 * 
	 * @return the time zone to use
	 */
	TimeZone getTimeZone();

	/**
	 * Returns whether to use 12-hour time (as opposed to 24-hour time).
	 * 
	 * @return whether to use 12-hour time (as opposed to 24-hour time)
	 */
	boolean isHour12();

	/**
	 * Returns the hour cycle to use.
	 * 
	 * @return the hour cycle to use
	 */
	HourCycle getHourCycle();

	/**
	 * Returns the format matching algorithm to use.
	 * 
	 * @return the format matching algorithm to use
	 */
	FormatMatcher getFormatMatcher();

	/**
	 * Returns the representation of the WeekDay.
	 * 
	 * @return the representation of the WeekDay
	 */
	StringItemStyle getWeekDay();

	/**
	 * Returns the representation of the Era.
	 * 
	 * @return the representation of the Era
	 */
	StringItemStyle getEra();

	/**
	 * Returns the representation of the Year.
	 * 
	 * @return the representation of the Year
	 */
	NumberItemStyle getYear();

	/**
	 * Returns the representation of the Month.
	 * 
	 * @return the representation of the Month
	 */
	MixedItemStyle getMonth();

	/**
	 * Returns the representation of the Day.
	 * 
	 * @return the representation of the Day
	 */
	NumberItemStyle getDay();

	/**
	 * Returns the representation of the Hour.
	 * 
	 * @return the representation of the Hour
	 */
	NumberItemStyle getHour();

	/**
	 * Returns the representation of the Minute.
	 * 
	 * @return the representation of the Minute
	 */
	NumberItemStyle getMinute();

	/**
	 * Returns the representation of the Second.
	 * 
	 * @return the representation of the Second
	 */
	NumberItemStyle getSecond();

	/**
	 * Returns the representation of the time zone name.
	 * @return the representation of the time zone name
	 */
	TimeZoneName getTimeZoneName();

}
