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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.enums.DataType;

/**
 * Interface to define if a dataset can contains data points.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasDataPoints extends HasDataset {

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	default void setDataPoints(DataPoint... datapoints) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			getDataset().setInternalDataPoints(datapoints);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	default void setDataPoints(List<DataPoint> datapoints) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			getDataset().setInternalDataPoints(datapoints);
		}
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	default List<DataPoint> getDataPoints() {
		return getDataPoints(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	default List<DataPoint> getDataPoints(boolean binding) {
		// checks if dataset is consistent
		if (getDataset() != null) {
			return getDataset().getDataPoints(Dataset.DATAPOINTS_FACTORY, binding);
		}
		// if here, dataset is not consistent
		// returns an empty list
		return Collections.emptyList();
	}

}