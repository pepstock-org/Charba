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
package org.pepstock.charba.client.data;

import java.util.Date;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Default implementation for time series item interface in order to manage time series item (time and value).
 * 
 * @author Andrea "Stock" Stocchero
 * @see DataPoint
 */
public final class TimeSeriesItem extends NativeObjectContainer {

	/**
	 * Creates a time series item setting the time passed as argument. By default the value is {@link Double#NaN}.
	 * 
	 * @param time time of item
	 */
	public TimeSeriesItem(Date time) {
		this(time, DataPoint.DEFAULT_Y);
	}

	/**
	 * Creates a time series item setting the time and the value passed as argument.
	 * 
	 * @param time time of item
	 * @param value value of item
	 */
	public TimeSeriesItem(Date time, double value) {
		// create with an empty native object
		super(null);
		// checks if time is consistent
		if (time == null) {
			// if not, exception
			throw new IllegalArgumentException("Time is null");
		}
		// sets time
		setTime(time);
		// sets value
		setValue(value);
	}

	/**
	 * Internal constructor which is used when time series items are requested.
	 * 
	 * @param dataPoint data point which is wrapped
	 */
	TimeSeriesItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the value of time series item.
	 * 
	 * @param value the value of time series item
	 */
	public void setValue(double value) {
		setValue(DataPoint.Property.Y, value);
	}

	/**
	 * Returns the value of time series item.
	 * 
	 * @return the value of time series item
	 */
	public double getValue() {
		return getValue(DataPoint.Property.Y, DataPoint.DEFAULT_Y);
	}

	/**
	 * Sets the time of time series item.
	 * 
	 * @param time the time of time series item
	 */
	private void setTime(Date time) {
		setValue(DataPoint.Property.T, time);
	}

	/**
	 * Returns the time of time series item.
	 * 
	 * @return the time of time series item
	 */
	public Date getTime() {
		return getValue(DataPoint.Property.T, UndefinedValues.DATE);
	}

}
