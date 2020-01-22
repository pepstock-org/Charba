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

import java.util.Date;

import org.pepstock.charba.client.GlobalAdapters;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.DefaultDateAdapter;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Maps a date adapter provided by CHART.JS to manage time series.<br>
 * It provides a set of methods to manage, parse and format dates and times.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DateAdapter {
	// constant for ISO week
	private static final String ISO_WEEK_UNIT = "isoWeek";

	private final NativeDateAdapter nativeAdapter;

	private final DateAdapterFormats formats;

	private final DateAdapterOptions options;

	private final DefaultDateAdapter id;

	/**
	 * Creates a date adapter without any options.
	 */
	public DateAdapter() {
		this(null);
	}

	/**
	 * Creates a date adapter using the options passed as argument.<br>
	 * At the moment ONLY LUXON is enabled to using options to act on dates actions.
	 * 
	 * @param options date adapter options
	 */
	public DateAdapter(DateAdapterOptions options) {
		// checks if date adapter in CHART.JS is consistent
		GlobalAdapters.get().checkIfHasDate();
		// checks if argument is consistent
		this.options = options != null ? options : new DateAdapterOptions();
		// creates a native date adapter
		this.nativeAdapter = new NativeDateAdapter(this.options.nativeObject());
		// PAY ATTENTION:
		// Luxon chart.js adapter has implemented the formats by Intl.DateTimeFormat
		// instead of strings. To implement Intl.DateTimeFormat is quite complex and maybe useless
		// Therefore for Luxon, we use a predefined formats as string but equals to defaults of the adapter
		// native object instance
		NativeObject nativeObject = null;
		// stores the ID
		this.id = nativeAdapter.getId();
		// checks if the adapter is luxon one
		if (DefaultDateAdapter.LUXON.equals(id)) {
			// uses the native object of special Luxon defaults
			nativeObject = LuxonFormats.get().nativeObject();
		} else {
			// gets formats
			// in order to store them once
			nativeObject = nativeAdapter.formats();
			// checks if formats are consistent
			if (nativeObject == null) {
				// if no, exception
				throw new IllegalArgumentException("Default formats is null");
			}
		}
		// creates and stores the formats
		this.formats = new DateAdapterFormats(nativeObject);
	}

	/**
	 * Returns the date adapter id.
	 * 
	 * @return the date adapter id
	 */
	public DefaultDateAdapter getId() {
		return nativeAdapter.getId();
	}

	/**
	 * Returns the provided default formats.
	 * 
	 * @return the provided default formats
	 */
	public DateAdapterFormats getFormats() {
		return formats;
	}

	/**
	 * Returns the options used to configure the date adapter.
	 * 
	 * @return the options used to configure the date adapter
	 */
	public DateAdapterOptions getOptions() {
		return options;
	}

	/**
	 * Returns the options used to configure the date adapter, using a factory to have the same object.<br>
	 * If the factory instance is <code>null</code>, returns <code>null</code>.
	 * 
	 * @param factory factory instance to create date adapter options
	 * @param <T> type of date adapter options
	 * @return the options used to configure the date adapter, using a factory
	 */
	public <T extends DateAdapterOptions> T getOptions(DateAdaptersOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// invokes the factory to wrap the native object
			// which are represnting the option
			return factory.create(options.nativeObject());
		}
		// if here, the factory is not consistent
		// then return null.
		return null;
	}

	/**
	 * Parses the given value and return the associated date.
	 * 
	 * @param time the value to parse (usually comes from the data)
	 * @param format the expected data format
	 * @return number date representation or <code>null</code>
	 */
	public Date parse(String time, String format) {
		// checks if arguments are consistent
		if (time != null && format != null) {
			// invokes the date adapter to parse the time
			double value = nativeAdapter.parse(time, format);
			// creates and returns new date
			return new Date((long) value);
		}
		// if here, the arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the formatted date in the specified format for a given timestamp.
	 * 
	 * @param time the timestamp to format
	 * @param format the date/time token
	 * @return a date time string representation
	 */
	public String format(long time, String format) {
		// checks if arguments are consistent
		if (time >= 0 && format != null) {
			// invokes the date adapter to format the time
			return nativeAdapter.format(time, format);
		}
		// if here, the arguments are not consistent
		// then returns null
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns the formatted date in the specified format for a given timestamp, using the default format related to time unit.
	 * 
	 * @param time the timestamp to format
	 * @param unit the time unit to use
	 * @return a date time string representation
	 */
	public String format(long time, TimeUnit unit) {
		// checks if arguments are consistent
		if (time >= 0 && Key.isValid(unit)) {
			// invokes the date adapter to format the time
			return format(time, formats.getFormat(unit));
		}
		// if here, the arguments are not consistent
		// then returns null
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns the formatted date in the specified format for a given date.
	 * 
	 * @param time the date to format
	 * @param format the date/time token
	 * @return a date time string representation
	 */
	public String format(Date time, String format) {
		// checks if argument is consistent
		if (time != null) {
			// invoke the date adapter to format the time
			return format(time.getTime(), format);
		}
		// if here, the argument is not consistent
		// then returns null
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns the formatted date in the specified format for a given date, using the default format related to time unit.
	 * 
	 * @param time the date to format
	 * @param unit the time unit instance
	 * @return a date time string representation
	 */
	public String format(Date time, TimeUnit unit) {
		// checks if argument is consistent
		if (time != null) {
			// invoke the date adapter to format the time
			return format(time.getTime(), unit);
		}
		// if here, the argument is not consistent
		// then returns null
		return Constants.EMPTY_STRING;
	}

	/**
	 * Adds the specified amount of unit to the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param amount the amount to add
	 * @param unit the time unit instance
	 * @return new date with added units
	 */
	public Date add(long time, long amount, TimeUnit unit) {
		// checks if arguments are consistent
		if (time >= 0 && Key.isValid(unit)) {
			// invoke the date adapter to add an amount of time units
			double value = nativeAdapter.add(time, amount, unit.value());
			// creates and returns new date
			return new Date((long) value);
		}
		// if here, the arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Adds the specified amount of unit to the given date.
	 * 
	 * @param time the input date
	 * @param amount the amount to add
	 * @param unit the time unit instance
	 * @return new date with added units
	 */
	public Date add(Date time, long amount, TimeUnit unit) {
		// checks if argument is consistent
		if (time != null) {
			// invoke the date adapter to add an amount of time units
			return add(time.getTime(), amount, unit);
		}
		// if here, the argument is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the number of unit between the given timestamps.
	 * 
	 * @param maxTime the input timestamp (reference)
	 * @param minTime the timestamp to subtract
	 * @param unit the time unit instance
	 * @return the number of unit between the given timestamps
	 */
	public double diff(long maxTime, long minTime, TimeUnit unit) {
		// checks if arguments are consistent
		if (maxTime >= 0 && minTime >= 0 && maxTime >= minTime && Key.isValid(unit)) {
			// invoke the date adapter to get the difference in time units
			return nativeAdapter.diff(maxTime, minTime, unit.value());
		}
		// if here, the arguments are not consistent
		// then returns NaN
		return Double.NaN;
	}

	/**
	 * Returns the number of unit between the given dates.
	 * 
	 * @param maxTime the input date (reference)
	 * @param minTime the date to subtract
	 * @param unit the time unit instance
	 * @return the number of unit between the given dates
	 */
	public double diff(Date maxTime, Date minTime, TimeUnit unit) {
		// checks if arguments are consistent
		if (maxTime != null && minTime != null) {
			// invoke the date adapter to get the difference in time units
			return diff(maxTime.getTime(), minTime.getTime(), unit);
		}
		// if here, the arguments are not consistent
		// then returns NaN
		return Double.NaN;
	}

	/**
	 * Returns the start of unit for the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param unit the time unit instance
	 * @return the start of unit for the given timestamp
	 */
	public Date startOf(long time, TimeUnit unit) {
		// checks if arguments are consistent
		if (time >= 0 && Key.isValid(unit)) {
			// invoke the date adapter to get the date start of by time unit
			double value = nativeAdapter.startOf(time, unit.value(), Integer.MAX_VALUE);
			// creates and returns new date
			return new Date((long) value);
		}
		// if here, the arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the start of unit for the given date.
	 * 
	 * @param time the input date
	 * @param unit the time unit instance
	 * @return the start of unit for the given date
	 */
	public Date startOf(Date time, TimeUnit unit) {
		// checks if argument is consistent
		if (time != null) {
			// invoke the date adapter to get the date start of by time unit
			return startOf(time.getTime(), unit);
		}
		// if here, the arguments is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the start by weekday for the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param weekday the ISO day of the week with 1 being Monday and 7 being Sunday (only needed if parameter "unit" is
	 *            isoWeek).
	 * @return the start by weekday for the given date
	 */
	public Date startOf(long time, int weekday) {
		// checks if arguments are consistent
		if (time >= 0 && weekday >= 0 && weekday <= 7) {
			// invoke the date adapter to get the date start of by time unit
			double value = nativeAdapter.startOf(time, ISO_WEEK_UNIT, weekday);
			// creates and returns new date
			return new Date((long) value);
		}
		// if here, the arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the start by weekday for the given date.
	 * 
	 * @param time the input date
	 * @param weekday the ISO day of the week with 1 being Monday and 7 being Sunday (only needed if parameter "unit" is
	 *            isoWeek).
	 * @return the start by weekday for the given date
	 */
	public Date startOf(Date time, int weekday) {
		// checks if argument is consistent
		if (time != null) {
			// invoke the date adapter to get the date start of by time unit
			return startOf(time.getTime(), weekday);
		}
		// if here, the arguments is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns end of unit for the given timestamp.
	 * 
	 * @param time the input timestamp
	 * @param unit the time unit instance
	 * @return the end of unit for the given timestamp
	 */
	public Date endOf(long time, TimeUnit unit) {
		// checks if arguments are consistent
		if (time >= 0 && Key.isValid(unit)) {
			// invoke the date adapter to get the date end of by time unit
			double value = nativeAdapter.endOf(time, unit.value());
			// creates and returns new date
			return new Date((long) value);
		}
		// if here, the arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns end of unit for the given date.
	 * 
	 * @param time the input date
	 * @param unit the time unit instance
	 * @return the end of unit for the given date
	 */
	public Date endOf(Date time, TimeUnit unit) {
		// checks if argument is consistent
		if (time != null) {
			// invoke the date adapter to get the date end of by time unit
			return endOf(time.getTime(), unit);
		}
		// if here, the arguments is not consistent
		// then returns null
		return null;
	}
}
