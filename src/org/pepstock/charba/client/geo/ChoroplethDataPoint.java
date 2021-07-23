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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.DataItem;

/**
 * Used for GEO data sets to set the data to render.<br>
 * A GEO data has to have a <code>feature</code> property containing the feature to render and a <code>value</code> property containing the value for the coloring.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChoroplethDataPoint extends GeoDataPoint {

	/**
	 * Factory instance to create data points. To use by {@link DataItem} to get the data point for choropleth chart.
	 */
	public static final ChoroplethDataPointFactory FACTORY = new ChoroplethDataPointFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FEATURE("feature");

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

	/**
	 * Creates the object with an empty native object.
	 */
	public ChoroplethDataPoint() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with the assigned feature.
	 * 
	 * @param feature the feature object to identify the region.
	 */
	public ChoroplethDataPoint(Feature feature) {
		this();
		// stores feature
		setFeature(feature);
	}

	/**
	 * Creates the object with the assigned feature and value.
	 * 
	 * @param feature the feature object to identify the region.
	 * @param value the value for the coloring
	 */
	public ChoroplethDataPoint(Feature feature, double value) {
		this(feature);
		// stores value
		setValue(value);
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	ChoroplethDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the feature object to identify the region.
	 * 
	 * @param feature the feature object to identify the region
	 */
	public void setFeature(Feature feature) {
		setValue(Property.FEATURE, feature);
	}

	/**
	 * Returns the feature object to identify the region.
	 * 
	 * @return the feature object to identify the region
	 */
	public Feature getFeature() {
		// checks if the stored data is a object
		if (isType(Property.FEATURE, ObjectType.OBJECT)) {
			return new Feature(getValue(Property.FEATURE));
		}
		// if here the feature is missing or not an object
		// then returns null
		return null;
	}

	/**
	 * Creates {@link ChoroplethDataPoint} form a {@link NativeObject}. This can be used by {@link DataItem}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static final class ChoroplethDataPointFactory implements NativeObjectContainerFactory<ChoroplethDataPoint> {

		/**
		 * To avoid any instantiation
		 */
		private ChoroplethDataPointFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChoroplethDataPoint create(NativeObject nativeObject) {
			return new ChoroplethDataPoint(nativeObject);
		}

	}

}