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

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.geo.enums.ClipMap;

/**
 * This is the options handler for data set implementation for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of data point for the specific chart
 */
final class GeoDatasetHandler<T extends GeoDataPoint> extends CommonOptionsHandler {

	// exception string message for setting data
	static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a GEO chart. Use 'setValues' method";
	// exception string message for getting data
	static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a GEO chart. Use 'getValues' method";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATA("data");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// GEO data factory instance
	private final NativeObjectContainerFactory<T> factory;

	/**
	 * Creates a data set.
	 * 
	 * @param nativeObject native object of data set
	 * @param defaultClipMap default clip map instance, different between choropleth and bubblemap.
	 * @param factory factory instance to retrieve the data points
	 */
	GeoDatasetHandler(NativeObject nativeObject, ClipMap defaultClipMap, NativeObjectContainerFactory<T> factory) {
		super(nativeObject, defaultClipMap);
		// stores factory
		this.factory = factory;
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of GEO data point, specific for the chart type..
	 * 
	 * @param values an array of GEO data point, specific for the chart type.
	 */
	void setValues(T[] values) {
		setArrayValue(Property.DATA, ArrayObject.fromOrNull(values));
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of GEO data point, specific for the chart type..
	 * 
	 * @param values an array of GEO data point, specific for the chart type.
	 */
	void setValues(List<T> values) {
		setArrayValue(Property.DATA, ArrayObject.fromOrNull(values));
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of GEO data point, specific for the chart type..
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return list of GEO data point, specific for the chart type..
	 */
	List<T> getValues(boolean binding) {
		// checks if there is the data
		if (has(Property.DATA)) {
			// returns data objects
			ArrayObject array = getArrayValue(Property.DATA);
			// returns array
			return ArrayListHelper.list(array, factory);
		}
		// checks if wants to bind the array
		if (binding) {
			List<T> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(Property.DATA, ArrayObject.fromOrEmpty(result));
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

}