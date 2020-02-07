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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.RegExp;

/**
 * This utility checks if the plug ID is acceptable or not.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class PluginIdChecker {

	// regexp to check if there is an uppercase
	private static final String REGEXP_HAS_UPPERCASE_PATTERN = "^.*[A-Z].*";
	// regex instance for font style
	private static final RegExp REGEXP_HAS_UPPERCASE = new RegExp(REGEXP_HAS_UPPERCASE_PATTERN);
	// regexp pattern to have only letters and number
	private static final String REGEXP_ID_PATTERN = "^[a-z0-9_]+$";
	// regxp objetc to perform check
	private static final RegExp REGEXP_ID = new RegExp(REGEXP_ID_PATTERN);

	/**
	 * To avoid any instantiation
	 */
	private PluginIdChecker() {
		// do nothing
	}

	/**
	 * Checks if the plugin is compliant with the constraints of plugin id.<br>
	 * A plugin id <br>
	 * <ul>
	 * <li>can not start with a dot or an underscore
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper case letters
	 * <li>should be something short, but also reasonably descriptive
	 * </ul>
	 * 
	 * @param id plugin id to be checked.
	 */
	public static void check(String id) {
		// checks if is null
		if (id == null) {
			throw new IllegalArgumentException("Plugin id can not be null");
		} else if (id.startsWith(Constants.DOT) || id.startsWith(Constants.UNDERSCORE)) {
			// checks if is starting with DOT or underscore
			throw new IllegalArgumentException(buildMessage(id, "Plugin id can not start with a dot or an underscore "));
		} else if (REGEXP_HAS_UPPERCASE.exec(id) != null) {
			// checks if contains uppercase letters
			throw new IllegalArgumentException(buildMessage(id, "Plugin id can not contain uppercase letters "));
		} else if (REGEXP_ID.exec(id) == null){
			// checks if is not safe URL
			throw new IllegalArgumentException(buildMessage(id, "Plugin id can not contain any invalid characters "));
		}
	}

	/**
	 * Creates a key by the plugin id as string
	 * 
	 * @param id the plugin id as string
	 * @return a key by the plugin id as string
	 */
	public static Key key(String id) {
		// checks
		check(id);
		return Key.create(id);
	}

	/**
	 * Creates the message for the exception.
	 * 
	 * @param pluginId plugin id
	 * @param message message
	 * @return message for exception
	 */
	private static String buildMessage(String pluginId, String message) {
		StringBuilder sb = new StringBuilder(message);
		sb.append(Constants.OPEN_SQUARE_BRACKET).append(pluginId).append(Constants.CLOSE_SQUARE_BRACKET);
		return sb.toString();
	}

}
