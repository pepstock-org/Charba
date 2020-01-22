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

import org.pepstock.charba.client.adapters.DateAdapterOptions;
import org.pepstock.charba.client.adapters.DateAdaptersOptionsFactory;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.NoDefaults;

/**
 * The following adapters element is  used to configure a date adapter, injecting to support time series into CAHRT.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Adapters extends AbstractModel<Scale, NoDefaults> {
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATE("date");

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
	 * Creates the object with the parent, the key of this element and native object to map java script properties.<br>
	 * This element does not have any default values.
	 * 
	 * @param time scale element as parent of this node.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	Adapters(Scale time, Key childKey, NativeObject nativeObject) {
		// no default values
		super(time, childKey, NoDefaults.INSTANCE, nativeObject);
	}

	/**
	 * Sets the date adapters options.
	 * 
	 * @param options date adapters options used to configure the adapter
	 * @param <T> type of date adapters options to store
	 */
	public <T extends DateAdapterOptions> void setDate(T options) {
		// stores configuration
		setValue(Property.DATE, options);
		// checks if all parents are attached
		checkAndAddToParent();
	}
	
	/**
	 * Returns the date adapter options, if exist.<br>
	 * It uses a factory instance to create a date adapter options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a date adapter options
	 * @param <T> type of date adapter options to return
	 * @return date adapter options used to configure the date adapter or an empty object if not exist.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public <T extends DateAdapterOptions> T getDate(DateAdaptersOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates and returns the object 
			return factory.create(getValue(Property.DATE));
		}
		// if here factory is not consistent
		return null;
	}
	
}