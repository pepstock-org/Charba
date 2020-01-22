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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This class wraps the native object of plugins registered into CHART.JS.<br>
 * Exposes ONLY the ID of plugin, anything else.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class PluginReference extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ID("id");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	PluginReference(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id if exists or an {@link UndefinedValues#STRING} if missing.
	 */
	String getId() {
		return getValue(Property.ID, UndefinedValues.STRING);
	}

	/**
	 * Returns the plugin as native object.
	 * 
	 * @return the plugin as native object.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}
