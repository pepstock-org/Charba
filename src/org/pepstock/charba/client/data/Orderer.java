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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Utility to manage the order option on chart datasets.
 * 
 * @author Andrea "Stock" Stocchero
 */

public final class Orderer extends NativeObjectContainer {

	// default for order property
	static final int DEFAULT_ORDER = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ORDER("order");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Creates the utility using dataset native object.
	 * 
	 * @param nativeObject native object to update with options
	 */
	Orderer(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the drawing order of dataset.<br>
	 * Also affects order for stacking, tooltip, and legend.
	 * 
	 * @param order the drawing order of dataset.
	 */
	void setOrder(int order) {
		// then sets it
		setValue(Property.ORDER, order);
	}

	/**
	 * Returns the drawing order of dataset.<br>
	 * Also affects order for stacking, tooltip, and legend.
	 * 
	 * @return the drawing order of dataset
	 */
	int getOrder() {
		return getValue(Property.ORDER, DEFAULT_ORDER);
	}
}