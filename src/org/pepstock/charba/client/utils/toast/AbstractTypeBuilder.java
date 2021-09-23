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

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.RegExp;
import org.pepstock.charba.client.utils.RegExpResult;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Common builder methods of toast types.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractTypeBuilder {

	// regexp pattern to have a correct CSS tag
	private static final String REGEXP_NAME_PATTERN = "[a-zA-Z]+[_a-zA-Z0-9-]*";
	// Regular expression to check if a string can be used as CSS tag
	private static final RegExp REGEXP_NAME = new RegExp(REGEXP_NAME_PATTERN);
	// exception template instance
	private static final String EXCEPTION_TEMPLATE = "Unable to create a custom toast type because the name '{0}' is invalid";

	// name name of property
	private final Key name;
	// type color instance
	private final IsColor backgroundColor;
	// type gradient instance
	private final Gradient gradient;

	/**
	 * To avoid any instantiation.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @param gradient gradient instance as background
	 */
	AbstractTypeBuilder(Key name, IsColor backgroundColor, Gradient gradient) {
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.gradient = gradient;
		// imports the toast java script and CSS code
		// adding CSS at runtime it's better
		// that the common one is already injected
		Toaster.get();
	}

	/**
	 * Returns the name of new toast type.
	 * 
	 * @return the name of new toast type
	 */
	final Key getName() {
		return name;
	}

	/**
	 * Returns the background {@link IsColor} of the toast.
	 * 
	 * @return the background {@link IsColor} of the toast
	 */
	final IsColor getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Returns the background {@link Gradient} of the toast.
	 * 
	 * @return the background {@link Gradient} of the toast
	 */
	final Gradient getBackgroundAsGradient() {
		return gradient;
	}

	/**
	 * Checks if the name passed as argument is consistent to be a toast type.
	 * 
	 * @param name the name to be checked if consistent to be a toast type
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
