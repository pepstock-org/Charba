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
package org.pepstock.charba.client.jsinterop.impl.charts;

import java.util.List;

import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.data.DoughnutDataset;

/**
 * The Meter chart allows a number of properties to be specified for each dataset. These are used to set display properties for
 * a specific dataset.<br>
 * Is equals of Doughnut dataset.<br>
 * The minimum value of data is 0 (see {@link org.pepstock.charba.client.data.MeterDataset#MINIMUM_VALUE}).<br>
 * The dataset will have always 2 data and setting the color of data, the first is the value color and the second is the empty
 * one.<br>
 * To set the data, is mandatory to use {@link org.pepstock.charba.client.data.MeterDataset#setValue(double)}) method instead of
 * {@link org.pepstock.charba.client.data.Dataset#setData(double...)}) one.
 * 
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.DoughnutDataset
 */
public class MeterDataset extends DoughnutDataset {

	// exception string message
	private static final String INVALID_SET_DATA_CALL = "setData method is not invokable by a Meter chart. Use setValue method.";

	/**
	 * Default value color
	 */
	public static final IsColor DEFAULT_VALUE_COLOR = new Color(140, 214, 16);

	/**
	 * Default empty color
	 */
	public static final IsColor DEFAULT_EMPTY_VALUE_COLOR = new Color(234, 234, 234);

	/**
	 * Default maximum value.
	 */
	public static final double DEFAULT_MAXIMUM_VALUE = 100D;

	/**
	 * Minimum value.
	 */
	public static final double MINIMUM_VALUE = 0D;

	private final double max;

	private double value = MINIMUM_VALUE;

	/**
	 * Creates a dataset setting the maximum value of dataset.
	 * 
	 * @param max maximum value of dataset.
	 */
	public MeterDataset(double max) {
		super();
		// sets the max value between the max and minimum value
		// max value must be higher than 0
		this.max = Math.max(max, MINIMUM_VALUE);
		// sets default dataset values
		// removing borders
		super.setBorderWidth(0, 0);
		super.setHoverBorderWidth(0, 0);
		// sets the color of datasets.
		super.setBackgroundColor(DEFAULT_VALUE_COLOR, DEFAULT_EMPTY_VALUE_COLOR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingDataset#setBorderWidth(int[])
	 */
	@Override
	public final void setBorderWidth(int... borderWidth) {
		// ignore passed value
		super.setBorderWidth(0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingDataset#setHoverBorderWidth(int[])
	 */
	@Override
	public final void setHoverBorderWidth(int... widths) {
		// ignore passed value
		super.setHoverBorderWidth(0, 0);
	}

	/**
	 * @return the max
	 */
	public final double getMax() {
		return max;
	}

	/**
	 * @return the value
	 */
	public final double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		// checks the value is greater than minimum and less than maximum
		this.value = Math.max(Math.min(max, value), MINIMUM_VALUE);
		// sets the data
		super.setData(this.value, Math.max(MINIMUM_VALUE, max - value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

}