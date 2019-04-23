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
 * Defines the single item of data for time series dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsTimeSeriesItem {

	/**
	 * Returns the value to be represented into Y axis.
	 * 
	 * @return the value to be represented into Y axis
	 */
	double getValue();

	/**
	 * Returns the time to be represented into X axis.
	 * 
	 * @return the time to be represented into X axis
	 */
	Date getTime();

	/**
	 * Returns the time series item as a data point.
	 * 
	 * @return the time series item as a data point
	 */
	default DataPoint toDataPoint() {
		// creates a data point
		DataPoint dataPoint = new DataPoint();
		// sets time
		dataPoint.setT(getTime());
		// sets value
		dataPoint.setY(getValue());
		// returns data point
		return dataPoint;
	}

}
