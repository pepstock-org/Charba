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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;

/**
 * This utility checks if the plug ID is acceptable or not.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class PluginIdChecker {

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
		Checker.checkIfValid(id, "Plugin id");
		// checks if is starting with DOT or underscore
		Checker.assertCheck(!id.startsWith(Constants.DOT) && !id.startsWith(Constants.UNDERSCORE), buildMessage(id, "Plugin id can not start with a dot or an underscore "));
		// checks if is a consistent ID
		Checker.assertCheck(Constants.REGEXP_ID.exec(id) != null, buildMessage(id, "Plugin id can not contain any invalid characters "));
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
