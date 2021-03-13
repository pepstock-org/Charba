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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAdapters;

/**
 * The following adapters element is used to configure a date adapter, injecting to support time series in the CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Adapters extends AbstractModel<AbstractScale, IsDefaultAdapters> implements IsDefaultAdapters {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATE("date");

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

	private final ScaleDateAdapter dateOptions;

	/**
	 * Creates the object with the parent, the key of this element and native object to map java script properties.<br>
	 * This element does not have any default values.
	 * 
	 * @param scale scale element as parent of this node.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Adapters(AbstractScale scale, Key childKey, IsDefaultAdapters defaultValues, NativeObject nativeObject) {
		// no default values
		super(scale, childKey, defaultValues, nativeObject);
		// gets and stores the options
		this.dateOptions = new ScaleDateAdapter(this, Property.DATE, getDefaultValues().getDate(), getValue(Property.DATE));
	}

	/**
	 * Returns the date adapter options.
	 * 
	 * @return date adapter options used to configure the date adapter
	 */
	@Override
	public ScaleDateAdapter getDate() {
		return dateOptions;
	}

}