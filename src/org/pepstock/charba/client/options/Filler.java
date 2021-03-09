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
import org.pepstock.charba.client.defaults.IsDefaultFiller;

/**
 * The filler plugin can be used to fill the datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Filler extends AbstractModel<Plugins, IsDefaultFiller> implements IsDefaultFiller {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PROPAGATE("propagate");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Filler(Plugins options, Key childKey, IsDefaultFiller defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets <code>true</code> if the plugin is propagating the filling.
	 * 
	 * @param propagate <code>true</code> if the plugin is propagating the filling
	 */
	public void setPropagate(boolean propagate) {
		setValueAndAddToParent(Property.PROPAGATE, propagate);
	}

	/**
	 * Returns <code>true</code> if the plugin is propagating the filling.
	 * 
	 * @return <code>true</code> if the plugin is propagating the filling
	 */
	@Override
	public boolean isPropagate() {
		return getValue(Property.PROPAGATE, getDefaultValues().isPropagate());
	}

}