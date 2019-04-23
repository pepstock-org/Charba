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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.enums.DataType;

/**
 * Interface to define if a dataset can contains time series items.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasTimeSeriesItems extends HasDataPoints {
	

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
	 * Sets the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @param items an array of time series items
	 */
	default void setTimeSeriesData(IsTimeSeriesItem... items) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			// creates instance of list of time series items
			List<IsTimeSeriesItem> dataAsList  = new LinkedList<>();
			// checks if items are consistent
			if (items != null && items.length > 0) {
				// adds the items to the list
				dataAsList.addAll(Arrays.asList(items));
			}
			// invoke the set by list
			setTimeSeriesData(dataAsList);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @param items a list of time series items
	 */
	default void setTimeSeriesData(List<IsTimeSeriesItem> items) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			// creates instance of list of data points
			List<DataPoint> dataPointAsList  = new LinkedList<>();
			// checks if items are consistent
			if (items != null && !items.isEmpty()) {
				// scans all time series items
				for (IsTimeSeriesItem dataItem : items) {
					// checks if time is consistent
					if (dataItem.getTime() == null) {
						// if not exception
						throw new IllegalArgumentException("Time is null");
					}
					// gets data point from item
					DataPoint dp = dataItem.toDataPoint();
					// if consistent
					if (dp != null) {
						// adds to the list
						dataPointAsList.add(dp);
					}
				}
			}
			getDataset().setInternalDataPoints(dataPointAsList);
		}
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of time series items
	 * 
	 * @return a list of time series items or an empty list of time series data if the data type is not {@link DataType#POINTS}.
	 */
	default List<TimeSeriesItem> getTimeSeriesData() {
		// creates instance of list of time series items
		List<TimeSeriesItem> dataAsList  = new LinkedList<>();
		// checks if dataset is consistent
		if (getDataset() != null) {
			// gets list of stored data points
			List<DataPoint> dataPointAsList = getDataset().getDataPoints(Dataset.DATAPOINTS_FACTORY, false);
			// scans all data points
			for (DataPoint dp : dataPointAsList) {
				// creating the time series item
				TimeSeriesItem data = new TimeSeriesItem(dp);
				// adds data
				dataAsList.add(data);
			}
		}
		// if here, dataset is not consistent
		// returns an empty list
		return dataAsList;
	}

}
