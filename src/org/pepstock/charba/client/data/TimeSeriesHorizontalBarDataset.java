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
 * The time series horizontal bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * In time series charts, the data (as double) is not allowed. You must use {@link DataPoint} or {@link TimeSeriesItem}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesHorizontalBarDataset extends HorizontalBarDataset implements HasTimeSeriesItems, HasBarStackHandler {

	// bar stack handler instance
	private final BarStackHandler barStackHandler;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public TimeSeriesHorizontalBarDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public TimeSeriesHorizontalBarDataset(boolean hidden) {
		this((IsDefaultOptions) null, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public TimeSeriesHorizontalBarDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public TimeSeriesHorizontalBarDataset(IsDefaultOptions defaultValues, boolean hidden) {
		super(defaultValues, hidden);
		// creates bar stack handler instance
		this.barStackHandler = new BarStackHandler(getNativeObject());
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden
	 */
	protected TimeSeriesHorizontalBarDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected TimeSeriesHorizontalBarDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// creates bar stack handler instance
		this.barStackHandler = new BarStackHandler(getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasBarStackHandler#getBarStackHandler()
	 */
	@Override
	public final BarStackHandler getBarStackHandler() {
		return barStackHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(TimeSeriesBarDataset.INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(TimeSeriesBarDataset.INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 */
	@Override
	public final List<Double> getData() {
		throw new UnsupportedOperationException(TimeSeriesBarDataset.INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData(boolean)
	 */
	@Override
	public final List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(TimeSeriesBarDataset.INVALID_DATA_CALL);
	}

}
