/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.utils;

/**
 * Internal utility to calculate the hash of java script resources content.<br>
 * Pay attention, you must use the chars and NOT the bytes because a byte variable can hold any value from -128 to 127 but a char variable can hold any value between 0 and 255.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Hasher {

	/**
	 * To avoid any instantiation
	 */
	private Hasher() {
		// nothing
	}

	/**
	 * Returns the hash of a {@link StringBuilder} argument.
	 * 
	 * @param value string builder to use to get the hash
	 * @return the hash of a {@link StringBuilder}
	 */
	public static int hash(StringBuilder value) {
		// checks if argument is consistent
		if (value != null) {
			// returns hash of string builder string representation
			return hash(value.toString());
		}
		// if here, argument i not consistent
		// then returns zero
		return 0;
	}

	/**
	 * Returns the hash of a strings array argument.
	 * 
	 * @param values strings array to use to get the hash
	 * @return the hash of a strings array
	 */
	public static int hash(String... values) {
		// initializes the result to zero
		int result = 0;
		// checks if argument is consistent
		if (values != null) {
			// scans all arguments
			for (String value : values) {
				// calculates the hash for each value
				// using the char
				result += hash(value.toCharArray());
			}
		}
		return result;
	}

	/**
	 * Returns the hash of a chars array argument.
	 * 
	 * @param values chars array to use to get the hash
	 * @return the hash of a chars array
	 */
	public static int hash(char... values) {
		// initializes the result to zero
		int result = 0;
		// checks if argument is consistent
		if (values != null) {
			// scans all arguments
			for (char value : values) {
				// calculates the hash for each value
				result += value;
			}
		}
		return result;
	}

}