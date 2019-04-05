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
package org.pepstock.charba.client.commons;

/**
 * Represents the property key of a JavaScript object.<br>
 * Used for enum.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface Key {

	/**
	 * Returns the name value of property
	 * 
	 * @return the name value of property
	 */
	String value();

	/**
	 * Returns <code>true</code> if a key into an enumeration is related to the value, otherwise <code>false</code>.
	 * 
	 * @param clazz enumeration of keys
	 * @param value value to search into key
	 * @param <T> type of key
	 * @return <code>true</code> if a key into an enumeration is related to the value, otherwise <code>false</code>
	 */
	public static <T extends Key> boolean hasKeyByValue(Class<T> clazz, String value) {
		return getKeyByValue(clazz, value, null) != null;
	}

	/**
	 * Returns a key into an enumeration by the value.
	 * 
	 * @param clazz enumeration of keys
	 * @param value value to search into key
	 * @param <T> type of key
	 * @return the found key by value or <code>null</code>
	 */
	public static <T extends Key> T getKeyByValue(Class<T> clazz, String value) {
		return getKeyByValue(clazz, value, null);
	}

	/**
	 * Returns a key into an enumeration by the value, or the default key passed as argument.
	 * 
	 * @param clazz enumeration of keys
	 * @param value value to search into key
	 * @param defaultKey default key instance if the value is not found
	 * @param <T> type of key
	 * @return the found key by value or the default one
	 */
	public static <T extends Key> T getKeyByValue(Class<T> clazz, String value, T defaultKey) {
		// checks if arguments are consistent
		if (value != null && clazz != null && clazz.isEnum()) {
			// scans enumeration
			for (T enumValue : clazz.getEnumConstants()) {
				// checks if Enum value name is equals to value
				if (enumValue.value().equalsIgnoreCase(value)) {
					// returns EnumValue
					return enumValue;
				}
			}
		}
		// if here the arguments are not consistent or the
		// value has not found
		// then returns default
		return defaultKey;
	}

}