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

import java.util.List;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The time series bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * In time series charts, the data (as double) is not allowed. You must use {@link DataPoint} or {@link TimeSeriesItem}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesBarDataset extends BarDataset implements HasTimeSeriesItems, HasBarStacker {

	// exception string message for setting ore getting data
	static final String INVALID_DATA_CALL = "setData and getData methods are not invokable by a time series chart";

	// bar stacker instance
	private final BarStacker barStacker;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public TimeSeriesBarDataset() {
		this((IsDefaultOptions) null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public TimeSeriesBarDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// creates bar stacker instance
		this.barStacker = new BarStacker(getNativeObject());
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 */
	protected TimeSeriesBarDataset(Type type) {
		this(type, null);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 */
	protected TimeSeriesBarDataset(Type type, IsDefaultOptions defaultValues) {
		super(type, defaultValues);
		// creates bar stacker instance
		this.barStacker = new BarStacker(getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasBarStacker#getBarStacker()
	 */
	@Override
	public final BarStacker getBarStacker() {
		return barStacker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 */
	@Override
	public final List<Double> getData() {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData(boolean)
	 */
	@Override
	public final List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

}
