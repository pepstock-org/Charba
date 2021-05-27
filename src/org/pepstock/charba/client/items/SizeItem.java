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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;

/**
 * This item contains the new size of the chart after it has been resized.<br>
 * This object has been created ONLY when a resize event occurs.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class SizeItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		WIDTH("width"),
		HEIGHT("height");

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
	 * Creates the item using an envelop (from <code>configuration</code> package) of native java script object which contains all properties.
	 * 
	 * @param envelop envelop of native java script object which contains all properties.
	 */
	public SizeItem(ConfigurationEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	SizeItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the width of the chart item in pixel.
	 * 
	 * @return the width of the chart item in pixel.
	 */
	public final double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height of the chart item in pixel.
	 * 
	 * @return the height of the chart item in pixel.
	 */
	public final double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

}