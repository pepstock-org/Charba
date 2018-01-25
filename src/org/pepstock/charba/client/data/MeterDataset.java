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

import org.pepstock.charba.client.commons.Color;

/**
 * The Meter chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Is equals of Doughnut dataset.
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.data.DoughnutDataset
 */
public class MeterDataset extends DoughnutDataset{
	
	private static final String INVALID_SET_DATA_CALL = "setData method is not invokable by a Meter chart. Use setValue method.";
	
	public static final String DEFAULT_VALUE_COLOR = new Color(140, 214, 16).toRGBA();
	
	public static final String DEFAULT_EMPTY_VALUE_COLOR = new Color(234, 234, 234).toRGBA();
	
	public static final double DEFAULT_MAXIMUM_VALUE = 100D;

	public static final double MINIMUM_VALUE = 0D;

	private final double max;
	
	private double value = MINIMUM_VALUE;

	public MeterDataset(double max) {
		super();
		this.max = Math.max(max, MINIMUM_VALUE);
		super.setBorderWidth(0, 0);
		super.setHoverBorderWidth(0, 0);
		super.setBackgroundColor(DEFAULT_VALUE_COLOR, DEFAULT_EMPTY_VALUE_COLOR);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.HovingDataset#setBorderWidth(int[])
	 */
	@Override
	public void setBorderWidth(int... borderWidth) {
		// ignore passed value
		super.setBorderWidth(0, 0);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.HovingDataset#setHoverBorderWidth(int[])
	 */
	@Override
	public void setHoverBorderWidth(int... widths) {
		// ignore passed value
		super.setHoverBorderWidth(0, 0);
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = Math.max(Math.min(max, value), MINIMUM_VALUE);
		super.setData(this.value, Math.max(MINIMUM_VALUE, max - value));
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

}