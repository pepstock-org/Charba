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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.utils.RegExp;

/**
 * This utility checks if the property key is acceptable or not.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class PropertyKeyChecker {

	// regexp pattern to have only letters and number
	private static final String REGEXP_ID_PATTERN = "^[a-zA-Z0-9_-]+$";
	// regxp object to perform check
	private static final RegExp REGEXP_ID = new RegExp(REGEXP_ID_PATTERN);

	/**
	 * To avoid any instantiation
	 */
	private PropertyKeyChecker() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if the passed id is compliant with the constraints of property key.<br>
	 * A scale id <br>
	 * <ul>
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper case letters
	 * </ul>
	 * 
	 * @param id property key to be checked.
	 * @return <code>true</code> if the passed id is compliant with the constraints of property key
	 */
	static boolean isValid(String id) {
		return (id != null && REGEXP_ID.exec(id) != null);
	}

	/**
	 * Checks if the passed id is compliant with the constraints of property key.<br>
	 * A scale id <br>
	 * <ul>
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper case letters
	 * </ul>
	 * 
	 * @param id property key to be checked.
	 */
	static void check(String id) {
		// checks if is null
		Checker.checkIfValid(id, "Property key");
		// checks if is not safe URL
		Checker.checkIfValid(REGEXP_ID.exec(id), buildMessage(id, "Property key can not contain any invalid characters"));
	}

	/**
	 * Checks if the passed id is compliant with the constraints of property key.<br>
	 * A scale id <br>
	 * <ul>
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper case letters
	 * </ul>
	 * 
	 * @param id property key to be checked.
	 */
	static void check(PropertyKey id) {
		check(Key.checkAndGetIfValid(id).value());
	}

	/**
	 * Creates the message for the exception.
	 * 
	 * @param propertyKey property key instance
	 * @param message message
	 * @return message for exception
	 */
	private static String buildMessage(String propertyKey, String message) {
		StringBuilder sb = new StringBuilder(message);
		sb.append(Constants.OPEN_SQUARE_BRACKET).append(propertyKey).append(Constants.CLOSE_SQUARE_BRACKET);
		return sb.toString();
	}

}