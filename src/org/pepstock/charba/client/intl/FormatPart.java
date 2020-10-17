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
package org.pepstock.charba.client.intl;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.intl.enums.FormatPartType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Result of {@link NumberFormat#formatToParts(double)} which is containing the locale-specific tokens from which it possible to build custom strings while preserving the
 * locale-specific parts.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat/formatToParts#Description">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class FormatPart extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		VALUE("value");
		
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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	FormatPart(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the type of the part.
	 * 
	 * @return the type of the part
	 */
	public FormatPartType getType() {
		return getValue(Property.TYPE, FormatPartType.values(), FormatPartType.UNKNOWN);
	}

	/**
	 * Returns the value of the part.
	 * 
	 * @return the value of the part
	 */
	public final String getValue() {
		return getValue(Property.VALUE, UndefinedValues.STRING);
	}

}
