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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;

/**
 * FIXME Used for sparse datasets, such as those in scatter charts. Each data point is specified using an object containing x and y properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GeoData extends NativeObjectContainer {

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	private enum Property implements Key
	{
		VALUE("value"),
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
	public GeoData() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	GeoData(NativeObject nativeObject) {
		super(nativeObject);
	}

	// -------------
	// X
	// -------------

	/**
	 * Sets the value as double.
	 * 
	 * @param value the value as double
	 */
	public void setValue(double value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns the value as double.
	 * 
	 * @return the value as double
	 */
	public double getValue() {
		return getValue(Property.VALUE, Undefined.DOUBLE);
	}

	/**
	 * Sets the feature object to identify the area.
	 * 
	 * @param feature the feature object to identify the area
	 */
	public void setFeature(Feature feature) {
		setValue(Property.FEATURE, feature);
	}

	/**
	 * Returns the feature object to identify the area.
	 * 
	 * @return the feature object to identify the area
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

}