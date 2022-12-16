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
package org.pepstock.charba.client.intl;

import java.util.Date;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions;
import org.pepstock.charba.client.intl.enums.Calendar;
import org.pepstock.charba.client.intl.enums.DateTimeStyle;
import org.pepstock.charba.client.intl.enums.FormatMatcher;
import org.pepstock.charba.client.intl.enums.HourCycle;
import org.pepstock.charba.client.intl.enums.LocaleMatcher;
import org.pepstock.charba.client.intl.enums.MixedItemStyle;
import org.pepstock.charba.client.intl.enums.NumberItemStyle;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.intl.enums.StringItemStyle;
import org.pepstock.charba.client.intl.enums.TimeZone;
import org.pepstock.charba.client.intl.enums.TimeZoneName;

/**
 * It wraps the {@link NativeDateTimeFormat} JavaScript object in order to format dates.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
final class DateTimeFormatWrapper extends AbtsractFormatWrapper<NativeDateTimeFormat, Date, IsDefaultDateTimeFormatOptions> {

	// empty options constants
	private static final NativeObject EMPTY_OPTIONS = new DateTimeFormatOptions().nativeObject();

	/**
	 * Creates object that enables language sensitive date time formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the date time format
	 */
	DateTimeFormatWrapper(CLocale locale, DateTimeFormatOptions options) {
		// create the native number format
		super(new NativeDateTimeFormat(locale.getIdentifier(), options != null ? options.nativeObject() : EMPTY_OPTIONS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbtsractFormatWrapper#format(java.lang.Object)
	 */
	@Override
	String format(Date value) {
		return getNativeFormat().format(NativeDate.create(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbtsractFormatWrapper#resolvedOptions()
	 */
	@Override
	IsDefaultDateTimeFormatOptions resolvedOptions() {
		// creates a number format options by the native object
		// returned by resolved option
		DateTimeFormatOptions formatOptions = new DateTimeFormatOptions(getNativeFormat().resolvedOptions());
		// creates the resolved options
		return new ResolvedOptions(formatOptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbtsractFormatWrapper#formatToParts(java.lang.Object)
	 */
	@Override
	ArrayObject formatToParts(Date value) {
		return getNativeFormat().formatToParts(NativeDate.create(value));
	}

	/**
	 * Wrapper of a date time format options in order to be consumed by its interface, when the resolve options methods has been invoked.<br>
	 * Wrapper is need in order to return only the interface with "get" methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ResolvedOptions implements IsDefaultDateTimeFormatOptions {
		// format options instance
		private final DateTimeFormatOptions delegated;

		/**
		 * Creates the resolved options wrapping a normal number format options.
		 * 
		 * @param delegated number format options to o wrap
		 */
		private ResolvedOptions(DateTimeFormatOptions delegated) {
			this.delegated = delegated;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions#getLocaleMatcher()
		 */
		@Override
		public LocaleMatcher getLocaleMatcher() {
			return delegated.getLocaleMatcher();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions#getNumberingSystem()
		 */
		@Override
		public NumberingSystem getNumberingSystem() {
			return delegated.getNumberingSystem();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getDateStyle()
		 */
		@Override
		public DateTimeStyle getDateStyle() {
			return delegated.getDateStyle();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getTimeStyle()
		 */
		@Override
		public DateTimeStyle getTimeStyle() {
			return delegated.getTimeStyle();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getCalendar()
		 */
		@Override
		public Calendar getCalendar() {
			return delegated.getCalendar();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getDayPeriod()
		 */
		@Override
		public StringItemStyle getDayPeriod() {
			return delegated.getDayPeriod();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getTimeZone()
		 */
		@Override
		public TimeZone getTimeZone() {
			return delegated.getTimeZone();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#isHour12()
		 */
		@Override
		public boolean isHour12() {
			return delegated.isHour12();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getHourCycle()
		 */
		@Override
		public HourCycle getHourCycle() {
			return delegated.getHourCycle();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getFormatMatcher()
		 */
		@Override
		public FormatMatcher getFormatMatcher() {
			return delegated.getFormatMatcher();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getWeekDay()
		 */
		@Override
		public StringItemStyle getWeekDay() {
			return delegated.getWeekDay();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getEra()
		 */
		@Override
		public StringItemStyle getEra() {
			return delegated.getEra();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getYear()
		 */
		@Override
		public NumberItemStyle getYear() {
			return delegated.getYear();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getMonth()
		 */
		@Override
		public MixedItemStyle getMonth() {
			return delegated.getMonth();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getDay()
		 */
		@Override
		public NumberItemStyle getDay() {
			return delegated.getDay();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getHour()
		 */
		@Override
		public NumberItemStyle getHour() {
			return delegated.getHour();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getMinute()
		 */
		@Override
		public NumberItemStyle getMinute() {
			return delegated.getMinute();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getSecond()
		 */
		@Override
		public NumberItemStyle getSecond() {
			return delegated.getSecond();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions#getTimeZoneName()
		 */
		@Override
		public TimeZoneName getTimeZoneName() {
			return delegated.getTimeZoneName();
		}

	}
}