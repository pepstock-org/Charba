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
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * This is the base data set implementation for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class BaseGeoDataset extends Dataset implements HasCommonOptions{

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a choropleth chart. Use 'setValues' method";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a choropleth chart. Use 'getValues' method";
	// GEO data factory instance
	private static final GeoDataFactory FACTORY = new GeoDataFactory();
	
	
	/**
	 * Creates a data set.
	 * 
	 * @param type controller type related to the data set
	 * @param defaultValues default options
	 */
	BaseGeoDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}
	
	/**
	 * Throws an exception because not available.
	 * 
	 * @returns nothing because will throw an exception
	 */
	@Override
	public List<Double> getData() {
		throw new UnsupportedOperationException(INVALID_GET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @returns nothing because will throw an exception
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(INVALID_GET_DATA_CALL);
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of {@link GeoData}.
	 * 
	 * @param values an array of {@link GeoData}
	 */
	public void setValues(GeoData... values) {
		setArrayValue(CommonProperty.DATA, ArrayObject.fromOrNull(values));
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of {@link GeoData}.
	 * 
	 * @param values an array of {@link GeoData}
	 */
	public void setValues(List<GeoData> values) {
		setArrayValue(CommonProperty.DATA, ArrayObject.fromOrNull(values));
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of {@link GeoData}.
	 * 
	 * @return list of {@link GeoData}.
	 */
	public List<GeoData> getValues() {
		return getValues(false);
	}

	/**
	 * Returns the data property of a data set for a chart is specified as an array of {@link GeoData}.
	 * 
	 * @return list of {@link GeoData}.
	 */
	public List<GeoData> getValues(boolean binding) {
		// checks if there is the data
		if (has(CommonProperty.DATA)) {
			// returns data objects
			ArrayObject array = getArrayValue(CommonProperty.DATA);
			// returns array
			return ArrayListHelper.list(array, FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			List<GeoData> result = new LinkedList<>();
			// set value
			setArrayValue(CommonProperty.DATA, ArrayObject.fromOrEmpty(result));
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}
	
	/**
	 * Factory to create a data point from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class GeoDataFactory implements NativeObjectContainerFactory<GeoData> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public GeoData create(NativeObject nativeObject) {
			return new GeoData(nativeObject);
		}

	}


}