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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Interface to define if a dataset can contains time series items.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasTimeSeriesItems extends HasDataPoints {

	/**
	 * Returns <code>true</code> if the data point, passed as argument, has got the properties to be a time series item (time and Y value).
	 * 
	 * @param datapoint data point instance to be checked
	 * @return <code>true</code> if the data point, passed as argument, has got the properties to be a time series item (time and Y value)
	 */
	static boolean isTimeSeriesItem(DataPoint datapoint) {
		// checks if there is data point
		return datapoint != null && datapoint.getXAsDate() != null && Undefined.is(datapoint.getR());
	}

	/**
	 * Checks if the list of data points, passed as argument, have got the properties to be time series items (time and Y value).<br>
	 * If not, a {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param datapoints list of data points to be checked
	 */
	static void checkTimeSeriesItems(List<DataPoint> datapoints) {
		// scans all data points
		for (DataPoint dp : datapoints) {
			// checks if data point are time series item
			Checker.assertCheck(isTimeSeriesItem(dp), "Datapoint is not a time series item");
		}
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	default void setData(double... values) {
		throw new UnsupportedOperationException(Dataset.TIME_SERIES_DATA_USAGE_MESSAGE);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	default void setData(List<Double> values) {
		throw new UnsupportedOperationException(Dataset.TIME_SERIES_DATA_USAGE_MESSAGE);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @return will throw an exception
	 */
	default List<Double> getData() {
		throw new UnsupportedOperationException(Dataset.TIME_SERIES_DATA_USAGE_MESSAGE);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param binding ignored because will throw an exception
	 * @return will throw an exception
	 */
	default List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(Dataset.TIME_SERIES_DATA_USAGE_MESSAGE);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	@Override
	default void setDataPoints(DataPoint... datapoints) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			// checks if data points are time series items
			checkTimeSeriesItems(Arrays.asList(datapoints));
			getDataset().setInternalDataPoints(datapoints);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	@Override
	default void setDataPoints(List<DataPoint> datapoints) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			// checks if data points are time series items
			checkTimeSeriesItems(datapoints);
			getDataset().setInternalDataPoints(datapoints);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @param items an array of time series items
	 */
	default void setTimeSeriesData(TimeSeriesItem... items) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			getDataset().setInternalTimeSeriesItems(items);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @param items a list of time series items
	 */
	default void setTimeSeriesData(List<TimeSeriesItem> items) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			getDataset().setInternalTimeSeriesItems(items);

		}
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @return a list of time series items or an empty list of time series items if the data type is not {@link DataType#POINTS}.
	 */
	default List<TimeSeriesItem> getTimeSeriesData() {
		return getTimeSeriesData(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of time series items
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return a list of time series items or an empty list of time series data if the data type is not {@link DataType#POINTS}.
	 */
	default List<TimeSeriesItem> getTimeSeriesData(boolean binding) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			return getDataset().getTimeSeriesItems(Dataset.TIMESERIES_ITEMS_FACTORY, binding);
		}
		// if here, dataset is not consistent
		// returns an empty list
		return Collections.emptyList();
	}

}