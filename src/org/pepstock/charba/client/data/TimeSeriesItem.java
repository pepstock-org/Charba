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

/**
 * Default implementation for time series item interface in order to manage time series item (time and value).<br>
 * It wraps a {@link DataPoint}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TimeSeriesItem implements IsTimeSeriesItem {
	// data point instance
	private final DataPoint dataPoint;

	/**
	 * Creates a time series item with an empty {@link DataPoint}.
	 */
	public TimeSeriesItem() {
		// create with new data point
		this(new DataPoint());
	}

	/**
	 * Creates a time series item setting the time and the value apssed as argument.
	 * 
	 * @param time time of item
	 * @param value value of item
	 */
	public TimeSeriesItem(Date time, double value) {
		// create with new data point
		this(new DataPoint());
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
	TimeSeriesItem(DataPoint dataPoint) {
		this.dataPoint = dataPoint;
	}

	/**
	 * Sets the value of time series item.
	 * 
	 * @param value the value of time series item
	 */
	public void setValue(double value) {
		dataPoint.setY(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.IsTimeSeriesData#getValue()
	 */
	@Override
	public double getValue() {
		return dataPoint.getY();
	}

	/**
	 * Sets the time of time series item.
	 * 
	 * @param time the time of time series item
	 */
	public void setTime(Date time) {
		// checks i argument is consistent
		if (time == null) {
			// if not, exception
			throw new IllegalArgumentException("Time is null");
		}
		dataPoint.setT(time);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.IsTimeSeriesData#getTime()
	 */
	@Override
	public Date getTime() {
		return dataPoint.getT();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.IsTimeSeriesData#toDataPoint()
	 */
	@Override
	public DataPoint toDataPoint() {
		return dataPoint;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeSeriesItem [" + dataPoint.toJSON() + "]";
	}

}
