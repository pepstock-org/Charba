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
	CHARBA_ID("charbaId");

	// name value of property
	private final String value;

	/**
	 * Creates a property with the value to use into native object.
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
	 * @return the property value or {@link UndefinedValues#STRING} if not exist
	 */
	public static String get(NativeObjectContainer nativeObjectContainer) {
		// checks if argument is consistent
		if (nativeObjectContainer != null) {
			return get(nativeObjectContainer.getNativeObject());
		}
		// if here the argument is not consistent
		// then returns null
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the property value from java script object.
	 * 
	 * @param nativeObject java script object
	 * @return the property value or {@link UndefinedValues#STRING} if not exist
	 */
	public static String get(NativeObject nativeObject) {
		// checks if argument is consistent and property exists
		if (nativeObject != null && nativeObject.hasProperty(CHARBA_ID.value())) {
			// gets descriptor
			NativeStringDescriptor descriptor = nativeObject.getStringProperty(CHARBA_ID.value());
			// if descriptor is consistent, return value
			return descriptor != null ? descriptor.getValue() : UndefinedValues.STRING;
		}
		// property doesn't exist
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the property value from java script object, when it is stored as integer.
	 * 
	 * @param key the key to search inside the object
	 * @param nativeObject java script object
	 * @return the property value or {@link UndefinedValues#INTEGER} if not exist
	 */
	public static int getIntegerProperty(Key key, NativeObject nativeObject) {
		// checks if arguments are consistent and property exists
		if (Key.isValid(key) && nativeObject != null && nativeObject.hasProperty(key.value())) {
			// gets descriptor
			NativeIntegerDescriptor descriptor = nativeObject.getIntProperty(key.value());
			// if descriptor is consistent, return value
			return descriptor != null ? descriptor.getValue() : UndefinedValues.INTEGER;
		}
		// property doesn't exist
		return UndefinedValues.INTEGER;
	}
	
	/**
	 * Returns the property value from java script object, when the ID is stored as string.
	 * 
	 * @param key the key to search inside the object
	 * @param nativeObject java script object
	 * @return the property value or {@link UndefinedValues#STRING} if not exist
	 */
	public static String getStringProperty(Key key, NativeObject nativeObject) {
		// checks if arguments are consistent and property exists
		if (Key.isValid(key) && nativeObject != null && nativeObject.hasProperty(key.value())) {
			// gets descriptor
			NativeStringDescriptor descriptor = nativeObject.getStringProperty(key.value());
			// if descriptor is consistent, return value
			return descriptor != null ? descriptor.getValue() : UndefinedValues.STRING;
		}
		// property doesn't exist
		return UndefinedValues.STRING;
	}
}
