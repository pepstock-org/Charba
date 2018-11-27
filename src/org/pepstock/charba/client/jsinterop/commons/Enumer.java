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
package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.commons.Key;

/**
 * Utility to deserialize enumeration values, passed by string.
 *  
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 * @see org.pepstock.charba.client.commons.Key
 */
public final class Enumer {

	/**
	 * To avoid any instantiation
	 */
	private Enumer() {
		// do nothing
	}

	/**
	 * Returns a key instance which represents a value of an enumeration by string.
	 * @param value string representation of enumeration value
	 * @param enumClass java class which defines all enumeration values
	 * @param defaultValue default value
	 * @return a key instance which represents a value of an enumeration or the default value
	 */
	public static <T extends Key> T deserialize(String value, Class<T> enumClass, T defaultValue) {
		// checks if the class is enum
		if (enumClass.isEnum()) {
			// scans all enumeration values
			for (T key : enumClass.getEnumConstants()) {
				// checks if enumeration value is equals to passed string value
				if (key.name().equalsIgnoreCase(value)) {
					// FOUND! return it
					return key;
				}
			}
		}
		// if here returns the default
		return defaultValue;
	}	

	/**
	 * Returns a key instance which represents a value of an enumeration by string. A default value as passed as string (double level of defaults).
	 * @param value string representation of enumeration value
	 * @param defaultValueAsString default value as string
	 * @param enumClass java class which defines all enumeration values
	 * @param defaultValue default value
	 * @return a key instance which represents a value of an enumeration or the default value
	 */
	public static <T extends Key> T deserialize(String value, String defaultValueAsString, Class<T> enumClass, T defaultValue) {
		// checks the string value with string default one
		return deserialize(Checker.check(value, defaultValueAsString), enumClass, defaultValue);
	}	

}
