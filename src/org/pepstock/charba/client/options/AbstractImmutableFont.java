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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Base class to map a font element in read-only mode, normalized by CHART.JS by {@link Helpers#toFont(org.pepstock.charba.client.items.FontItem)}, providing also the CSS string of
 * font itself.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractImmutableFont extends AbstractReadOnlyFont implements IsImmutableFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		STRING("string");

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
	 * Creates a immutable font to use, wrapping a native object instance, and providing a CSS string.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractImmutableFont(NativeObject nativeObject) {
		super(Defaults.get().getGlobal().getFont(), nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractReadOnlyFont#getLineHeightAsString()
	 */
	@Override
	public final String getLineHeightAsString() {
		return String.valueOf(getLineHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsImmutableFont#toCSSString()
	 */
	@Override
	public final String toCSSString() {
		return JsHelper.get().getStringProperty(Property.STRING, getNativeObject());
	}

}