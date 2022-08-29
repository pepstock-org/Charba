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
	 * Returns a key instance by its string value.
	 * 
	 * @param key string value to use
	 * @return a standard key instance
	 */
	static Key create(String key) {
		return new StandardKey(key);
	}

	/**
	 * Returns <code>true</code> if key passed as argument is not <code>null</code> and its value is not <code>null</code> as well.
	 * 
	 * @param key key to be checked
	 * @return <code>true</code> if key passed as argument is not <code>null</code> and its value is not <code>null</code> as well.
	 */
	static boolean isValid(Key key) {
		return key != null && key.value() != null && key.value().trim().length() > 0;
	}

	/**
	 * Checks if key passed as argument is not <code>null</code> and its value is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param key key to be checked
	 */
	static void checkIfValid(Key key) {
		if (!isValid(key)) {
			throw new IllegalArgumentException("Key is null or not consistent");
		}
	}

	/**
	 * Checks if key passed as argument is not <code>null</code> and its value is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the key.
	 * 
	 * @param key key to be checked
	 * @param <T> type of key
	 * @return the same key passed as argument
	 */
	static <T extends Key> T checkAndGetIfValid(T key) {
		// checks if key is consistent
		checkIfValid(key);
		// if here, is consistent
		// then returns the argument
		return key;
	}

	/**
	 * Returns <code>true</code> if a key in the an enumeration is related to the value, otherwise <code>false</code>.
	 * 
	 * @param enumValues enumeration values of keys
	 * @param value value to search in the key
	 * @param <T> type of key
	 * @return <code>true</code> if a key in the an enumeration is related to the value, otherwise <code>false</code>
	 */
	static <T extends Key> boolean hasKeyByValue(T[] enumValues, String value) {
		return getKeyByValue(enumValues, value, null) != null;
	}

	/**
	 * Returns a key in the an enumeration by the value.
	 * 
	 * @param enumValues enumeration values of keys
	 * @param value value to search in the key
	 * @param <T> type of key
	 * @return the found key by value or <code>null</code>
	 */
	static <T extends Key> T getKeyByValue(T[] enumValues, String value) {
		return getKeyByValue(enumValues, value, null);
	}

	/**
	 * Returns a key in the an enumeration by the value, or the default key passed as argument.
	 * 
	 * @param enumValues enumeration values of keys
	 * @param value value to search in the key
	 * @param defaultKey default key instance if the value is not found
	 * @param <T> type of key
	 * @return the found key by value or the default one
	 */
	static <T extends Key> T getKeyByValue(T[] enumValues, String value, T defaultKey) {
		// checks if arguments are consistent
		if (value != null && ArrayUtil.isNotEmpty(enumValues)) {
			// scans enumeration
			for (T enumValue : enumValues) {
				// checks if enumeration value name is equals to value
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

	/**
	 * Compares the two specified key values.
	 * 
	 * @param k1 the first key to compare
	 * @param k2 the second key to compare
	 * @return the value 0 if k1 value is equal to k2; a value less than 0 if k1 value is less than k2; and a value greater than 0 if k1 value is greater than k2.
	 */
	static int compare(Key k1, Key k2) {
		// checks if k1 argument is consistent
		if (!Key.isValid(k1)) {
			// checks if k2 argument is consistent
			if (!Key.isValid(k2)) {
				// both are null then equals
				return 0;
			}
			// k2 is greater being not null
			return -1;
		} else {
			// checks if k2 argument is consistent
			if (!Key.isValid(k2)) {
				// k2 is less being not null
				return 1;
			}
			// compares values
			return k1.value().compareToIgnoreCase(k2.value());
		}
	}

	/**
	 * Returns <code>true</code> if the keys have got the same value.
	 * 
	 * @param k1 the first key to compare
	 * @param k2 the second key to compare
	 * @return <code>true</code> if the keys have got the same value
	 */
	static boolean equals(Key k1, Key k2) {
		return compare(k1, k2) == 0;
	}

	/**
	 * Returns a hash code value for the a key.<br>
	 * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
	 * 
	 * @param key the key to use for hash code calculation
	 * @return hash code value for the a key
	 */
	static int getHashCode(Key key) {
		// checks argument if consistent
		if (Key.isValid(key)) {
			// calculates hash code
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key.value() == null) ? 0 : key.value().hashCode());
			return result;
		}
		// if here, key is not valid tehn returns zero
		return 0;
	}

	/**
	 * Returns the name value of property
	 * 
	 * @return the name value of property
	 */
	String value();

}