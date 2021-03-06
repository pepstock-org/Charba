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

import org.pepstock.charba.client.items.Undefined;

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
	CHARBA_ID("charbaId");

	// name value of property
	private final String value;

	/**
	 * Creates a property with the value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Id(String value) {
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

	/**
	 * Returns the property value from java script object.
	 * 
	 * @param nativeObjectContainer java script object container
	 * @return the property value or {@link Undefined#STRING} if not exist
	 */
	public static String get(NativeObjectContainer nativeObjectContainer) {
		// checks if argument is consistent
		if (nativeObjectContainer != null) {
			return get(nativeObjectContainer.getNativeObject());
		}
		// if here the argument is not consistent
		// then returns null
		return Undefined.STRING;
	}

	/**
	 * Returns the property value from java script object.
	 * 
	 * @param nativeObject java script object
	 * @return the property value or {@link Undefined#STRING} if not exist
	 */
	public static String get(NativeObject nativeObject) {
		// checks if argument is consistent and property exists
		if (nativeObject != null && NativeObjectUtils.hasProperty(nativeObject, CHARBA_ID.value())) {
			// returns value
			return NativeObjectUtils.getStringProperty(nativeObject, CHARBA_ID.value(), Undefined.STRING);
		}
		// property doesn't exist
		return Undefined.STRING;
	}

	/**
	 * Returns the property value from java script object, when it is stored as integer.
	 * 
	 * @param key the key to search inside the object
	 * @param nativeObject java script object
	 * @return the property value or {@link Undefined#INTEGER} if not exist
	 */
	public static int getIntegerProperty(Key key, NativeObject nativeObject) {
		// checks if arguments are consistent and property exists
		if (Key.isValid(key) && nativeObject != null && NativeObjectUtils.hasProperty(nativeObject, key.value())) {
			// returns value
			return NativeObjectUtils.getIntProperty(nativeObject, key.value(), Undefined.INTEGER);
		}
		// property doesn't exist
		return Undefined.INTEGER;
	}

	/**
	 * Returns the property value from java script object, when the ID is stored as string.
	 * 
	 * @param key the key to search inside the object
	 * @param nativeObject java script object
	 * @return the property value or {@link Undefined#STRING} if not exist
	 */
	public static String getStringProperty(Key key, NativeObject nativeObject) {
		// checks if arguments are consistent and property exists
		if (Key.isValid(key) && nativeObject != null && NativeObjectUtils.hasProperty(nativeObject, key.value())) {
			// if descriptor is consistent, return value
			return NativeObjectUtils.getStringProperty(nativeObject, key.value(), Undefined.STRING);
		}
		// property doesn't exist
		return Undefined.STRING;
	}
}
