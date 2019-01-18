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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.Type;

import com.google.gwt.safehtml.shared.UriUtils;

/**
 * This utility checks if the controller type is acceptable or not.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
final class ControllerTypeChecker {
	// exception text when controller type is null
	private static final String INVALID_CONTROLLER_TYPE_NULL = "Controller type can not be null ";
	// exception text when controller type starts with dot or underscore
	private static final String INVALID_CONTROLLER_TYPE_INVALID_FIRST_CHAR = "Controller type can not start with a dot or an underscore ";
	// exception text when controller type is not URL safe
	private static final String INVALID_CONTROLLER_TYPE_NOT_URL_SAFE = "Controller type can not contain any non-URL-safe characters ";
	// DOT constant
	private static final char DOT = '.';
	// underscore constant
	private static final char UNDERSCORE = '_';

	/**
	 * To avoid any instantiation
	 */
	private ControllerTypeChecker() {
		// do nothing
	}

	/**
	 * Checks if the controller is compliant with the constraints of controller type.<br>
	 * A controller type <br>
	 * <ul>
	 * <li>can not start with a dot or an underscore
	 * <li>can not contain any non-URL-safe characters
	 * </ul>
	 * 
	 * @param type controller type to be checked.
	 * @throws InvalidControllerTypeException if the controller is not compliant
	 */
	public static void check(Type type) throws InvalidControllerTypeException {
		// checks if is null
		if (type == null) {
			throw new InvalidControllerTypeException(INVALID_CONTROLLER_TYPE_NULL);
		} else if (type.name() == null) {
			// checks if value is null
			throw new InvalidControllerTypeException(INVALID_CONTROLLER_TYPE_NULL);
		} else if (type.name().charAt(0) == DOT || type.name().charAt(0) == UNDERSCORE) {
			// checks if is starting with DOT or underscore
			throw new InvalidControllerTypeException(buildMessage(type.name(), INVALID_CONTROLLER_TYPE_INVALID_FIRST_CHAR));
		} else if (!UriUtils.isSafeUri(type.name())) {
			// checks if is not safe URL
			throw new InvalidControllerTypeException(buildMessage(type.name(), INVALID_CONTROLLER_TYPE_NOT_URL_SAFE));
		}
	}

	/**
	 * Creates the message for the exception.
	 * 
	 * @param type controller type
	 * @param message message
	 * @return message for exception
	 */
	private static String buildMessage(String type, String message) {
		StringBuilder sb = new StringBuilder(message);
		sb.append("[").append(type).append("]");
		return sb.toString();
	}

}
