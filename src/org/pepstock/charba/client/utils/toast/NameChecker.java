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
* @return builder instance */
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.RegExp;
import org.pepstock.charba.client.utils.RegExpResult;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Checks if the name passed to create {@link IsToastType} or {@link IsProgressBarType} must be acceptable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class NameChecker {

	// regexp pattern to have a correct CSS tag
	private static final String REGEXP_NAME_PATTERN = "[a-zA-Z]+[_a-zA-Z0-9-]*";
	// Regular expression to check if a string can be used as CSS tag
	private static final RegExp REGEXP_NAME = new RegExp(REGEXP_NAME_PATTERN);
	// exception template instance
	private static final String EXCEPTION_TEMPLATE = "Unable to create a custom toast object because the name '{0}' is invalid";

	/**
	 * To avoid any instantiation
	 */
	private NameChecker() {
		// do nothing
	}

	/**
	 * Checks if the name passed as argument is consistent to be a toast object.
	 * 
	 * @param name the name to be checked if consistent to be a toast object
	 */
	static void checkName(Key name) {
		// check if key is consistent
		Key.checkIfValid(name);
		final String exception = Utilities.applyTemplate(EXCEPTION_TEMPLATE, name.value());
		// checks name by regexp
		RegExpResult result = REGEXP_NAME.exec(name.value());
		Checker.assertCheck(result.length() == 1, exception);
		Checker.assertCheck(name.value().equals(result.get(0)), exception);
	}

}
