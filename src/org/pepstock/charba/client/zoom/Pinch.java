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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Base object to map wheel options for {@link ZoomPlugin#ID} plugin configuration.<br>
 * It represents the container for WHEEL options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Pinch extends AbstractNode implements IsDefaultPinch {
	
	/**
	 * Default enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;


	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled");

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

	// default values instance
	private final IsDefaultPinch defaultOptions;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultOptions default pinch options to returns the default when required.
	 * @param nativeObject native object to map java script properties
	 */
	Pinch(AbstractNode parent, Key childKey, IsDefaultPinch defaultOptions, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Sets <code>true</code> to enable element for pinch zooming.
	 * 
	 * @param enabled <code>true</code> to enable element for pinch zooming
	 */
	public void setEnabled(boolean enabled) {
		setValueAndAddToParent(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> to enable element for pinch zooming.
	 * 
	 * @return <code>true</code> to enable element for pinch zooming
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, defaultOptions.isEnabled());
	}

}