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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions;
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
 * INTL default values for number format options for internationalization.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultDateTimeFormatOptions extends DefaultBaseFormatOptions implements IsDefaultDateTimeFormatOptions {

	/**
	 * Immutable instance with the number format defaults.
	 */
	public static final IsDefaultDateTimeFormatOptions INSTANCE = new DefaultDateTimeFormatOptions();

	/**
	 * To avoid any instantiation
	 */
	DefaultDateTimeFormatOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getDateStyle()
	 */
	@Override
	public DateTimeStyle getDateStyle() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getTimeStyle()
	 */
	@Override
	public DateTimeStyle getTimeStyle() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getCalendar()
	 */
	@Override
	public Calendar getCalendar() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getDayPeriod()
	 */
	@Override
	public StringItemStyle getDayPeriod() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getTimeZone()
	 */
	@Override
	public TimeZone getTimeZone() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#isHour12()
	 */
	@Override
	public boolean isHour12() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getHourCycle()
	 */
	@Override
	public HourCycle getHourCycle() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getFormatMatcher()
	 */
	@Override
	public FormatMatcher getFormatMatcher() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getWeekDay()
	 */
	@Override
	public StringItemStyle getWeekDay() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getEra()
	 */
	@Override
	public StringItemStyle getEra() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getYear()
	 */
	@Override
	public NumberItemStyle getYear() {
		return NumberItemStyle.NUMERIC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getMonth()
	 */
	@Override
	public MixedItemStyle getMonth() {
		return MixedItemStyle.NUMERIC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getDay()
	 */
	@Override
	public NumberItemStyle getDay() {
		return NumberItemStyle.NUMERIC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getHour()
	 */
	@Override
	public NumberItemStyle getHour() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getMinute()
	 */
	@Override
	public NumberItemStyle getMinute() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getSecond()
	 */
	@Override
	public NumberItemStyle getSecond() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getTimeZoneName()
	 */
	@Override
	public TimeZoneName getTimeZoneName() {
		return null;
	}

}