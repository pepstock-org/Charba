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

import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The time series line chart allows a number of properties to be specified for each dataset. These are used to set display
 * properties for a specific dataset.<br>
 * In time series charts, the data string and data (as double) are not allowed. You must use {@link DataPoint} or
 * {@link TimeSeriesItem}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesLineDataset extends LineDataset implements HasTimeSeriesItems {

	// exception string message for setting or getting data string
	private static final String INVALID_DATA_STRING_CALL = "setDataString and getDataString methods are not invokable by a time series chart.";

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public TimeSeriesLineDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public TimeSeriesLineDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LineDataset#setDataString(java.lang.String[])
	 */
	@Override
	public final void setDataString(String... data) {
		throw new UnsupportedOperationException(INVALID_DATA_STRING_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LineDataset#setDataString(java.util.List)
	 */
	@Override
	public final void setDataString(List<String> data) {
		throw new UnsupportedOperationException(INVALID_DATA_STRING_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LineDataset#getDataString()
	 */
	@Override
	public final List<String> getDataString() {
		throw new UnsupportedOperationException(INVALID_DATA_STRING_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.LineDataset#getDataString(boolean)
	 */
	@Override
	public final List<String> getDataString(boolean binding) {
		throw new UnsupportedOperationException(INVALID_DATA_STRING_CALL);
	}
}
