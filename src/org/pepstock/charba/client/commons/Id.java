/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Enums the property ID used by CHARBA to identify the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Id implements Key
{

	/**
	 * Name of java script property
	 */
	charbaId;

	/**
	 * Returns the property value from java script object.
	 * 
	 * @param nativeObjectContainer java script object container
	 * @return the property value or {@link org.pepstock.charba.client.items.UndefinedValues#STRING} if not exist
	 */
	public static String get(NativeObjectContainer nativeObjectContainer) {
		return get(nativeObjectContainer.getNativeObject());
	}

	/**
	 * Returns the property value from java script object.
	 * 
	 * @param nativeObject java script object
	 * @return the property value or {@link org.pepstock.charba.client.items.UndefinedValues#STRING} if not exist
	 */
	public static String get(NativeObject nativeObject) {
		// checks if property exists
		if (nativeObject.hasProperty(charbaId.name())) {
			// gets descriptor
			NativeStringDescriptor descriptor = nativeObject.getStringProperty(charbaId.name());
			// if descriptor is consistent, return value
			return descriptor != null ? descriptor.getValue() : UndefinedValues.STRING;
		}
		// property doesn't exist
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the property value from java script object, when the ID is stored as integer
	 * 
	 * @param key the key to search inside the object
	 * @param nativeObject java script object
	 * @return the property value or {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER} if not exist
	 */
	public static int get(Key key, NativeObject nativeObject) {
		// checks if property exists
		if (nativeObject.hasProperty(key.name())) {
			// gets descriptor
			NativeIntegerDescriptor descriptor = nativeObject.getIntProperty(key.name());
			// if descriptor is consistent, return value
			return descriptor != null ? descriptor.getValue() : UndefinedValues.INTEGER;
		}
		// property doesn't exist
		return UndefinedValues.INTEGER;
	}
}
