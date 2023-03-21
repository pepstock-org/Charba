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
package org.pepstock.charba.client.data;

import java.util.Date;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.data.AbstractXYDataPoint.XYProperty;
import org.pepstock.charba.client.enums.DataPointType;

/**
 * Default implementation for time series item interface in order to manage time series item (time and value).
 * 
 * @author Andrea "Stock" Stocchero
 * @see DataPoint
 */
public final class TimeSeriesItem extends AbstractDataPoint {

	/**
	 * Creates a time series item setting the time passed as argument. By default the value is {@link Double#NaN}.
	 * 
	 * @param time time of item
	 */
	public TimeSeriesItem(Date time) {
		this(time, AbstractXYDataPoint.DEFAULT_Y);
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
		// sets time
		setTime(Checker.checkAndGetIfValid(time, "Time argument"));
		// sets value
		setValue(value);
	}

	/**
	 * Internal constructor which is used when time series items are requested.
	 * 
	 * @param nativeObject native object that this class is wrapping
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
		setValue(XYProperty.Y, value);
		// sets type
		checkAndSetType(XYProperty.Y, CharbaProperty.CHARBA_Y_TYPE, DataPointType.NUMBER);
	}

	/**
	 * Returns the value of time series item.
	 * 
	 * @return the value of time series item
	 */
	public double getValue() {
		return getValue(XYProperty.Y, AbstractXYDataPoint.DEFAULT_Y);
	}

	/**
	 * Sets the time of time series item.
	 * 
	 * @param time the time of time series item
	 */
	private void setTime(Date time) {
		setValue(XYProperty.X, time);
		// sets type
		checkAndSetType(XYProperty.X, CharbaProperty.CHARBA_X_TYPE, DataPointType.DATE);
	}

	/**
	 * Returns the time of time series item.
	 * 
	 * @return the time of time series item
	 */
	public Date getTime() {
		return getValue(XYProperty.X, (Date) null);
	}

}