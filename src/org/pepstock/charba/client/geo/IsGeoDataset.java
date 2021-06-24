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
package org.pepstock.charba.client.geo;

import java.util.Collections;
import java.util.List;

/**
 * This is the base data set implementation for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of data point for the specific chart
 */
interface IsGeoDataset<T extends GeoDataPoint> extends HasCommonOptions {

	/**
	 * Returns the data set options handler.
	 * 
	 * @return the data set options handler
	 */
	@Override
	GeoDatasetHandler<T> getHandler();

	/**
	 * Sets the data property of a data set for a chart is specified as an array of {@link GeoDataPoint}.
	 * 
	 * @param values an array of {@link GeoDataPoint}
	 */
	default void setValues(T[] values) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setValues(values);
		}
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of {@link GeoDataPoint}.
	 * 
	 * @param values an array of {@link GeoDataPoint}
	 */
	default void setValues(List<T> values) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setValues(values);
		}
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of {@link GeoDataPoint}.
	 * 
	 * @return list of {@link GeoDataPoint}.
	 */
	default List<T> getValues() {
		return getValues(false);
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of {@link GeoDataPoint}.
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return list of {@link GeoDataPoint}.
	 */
	default List<T> getValues(boolean binding) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// returns value
			return getHandler().getValues(binding);
		}
		// if here, handler is not consistent
		// then returns default
		return Collections.emptyList();
	}

}