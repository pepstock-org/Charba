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
import org.pepstock.charba.client.items.Undefined;

/**
 * Used for GEO data sets to set the data to render.<br>
 * A GEO data has to have a <code>value</code> property containing the value for the coloring.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class GeoDataPoint extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		VALUE("value");

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
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	GeoDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the value for the coloring.
	 * 
	 * @param value the value for the coloring
	 */
	public final void setValue(double value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns the value for the coloring.
	 * 
	 * @return the value for the coloring
	 */
	public final double getValue() {
		return getValue(Property.VALUE, Undefined.DOUBLE);
	}

}