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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Contains all date time formats that a date adapter can manage.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DateAdapterFormats extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		DATETIME("datetime");

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
		public final String value() {
			return value;
		}

	}

	/**
	 * Creates the object with an empty native object instance.
	 */
	protected DateAdapterFormats() {
		this(null);
	}

	/**
	 * Creates the object with the native object to map java script properties.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	protected DateAdapterFormats(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the date time format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the date time format provided by adapter.
	 */
	public final String getDateTime() {
		return getValue(Property.DATETIME, Constants.EMPTY_STRING);
	}

	/**
	 * Returns the millisecond format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the millisecond format provided by adapter.
	 */
	public final String getMillisecond() {
		return getFormat(TimeUnit.MILLISECOND);
	}

	/**
	 * Returns the second format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the second format provided by adapter.
	 */
	public final String getSecond() {
		return getFormat(TimeUnit.SECOND);
	}

	/**
	 * Returns the minute format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the minute format provided by adapter.
	 */
	public final String getMinute() {
		return getFormat(TimeUnit.MINUTE);
	}

	/**
	 * Returns the hour format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the hour format provided by adapter.
	 */
	public final String getHour() {
		return getFormat(TimeUnit.HOUR);
	}

	/**
	 * Returns the day format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the day format provided by adapter.
	 */
	public final String getDay() {
		return getFormat(TimeUnit.DAY);
	}

	/**
	 * Returns the week format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the week format provided by adapter.
	 */
	public final String getWeek() {
		return getFormat(TimeUnit.WEEK);
	}

	/**
	 * Returns the month format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the month format provided by adapter.
	 */
	public final String getMonth() {
		return getFormat(TimeUnit.MONTH);
	}

	/**
	 * Returns the quarter format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the quarter format provided by adapter.
	 */
	public final String getQuarter() {
		return getFormat(TimeUnit.QUARTER);
	}

	/**
	 * Returns the year format provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @return the year format provided by adapter.
	 */
	public final String getYear() {
		return getFormat(TimeUnit.YEAR);
	}

	/**
	 * Returns the format by {@link TimeUnit} provided by adapter.<br>
	 * If not set, returns {@link Constants#EMPTY_STRING}.
	 * 
	 * @param unit time unit to use to get the default.
	 * @return the year format provided by adapter.
	 */
	public final String getFormat(TimeUnit unit) {
		// check if time unit is consistent and there is into the object
		if (Key.isValid(unit) && has(unit)) {
			return getValue(unit, Constants.EMPTY_STRING);
		}
		// if here the tine unit is not consistent
		// then returns an undefined values
		return Constants.EMPTY_STRING;
	}

}