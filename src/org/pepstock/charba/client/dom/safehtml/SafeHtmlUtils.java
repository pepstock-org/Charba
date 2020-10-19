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
package org.pepstock.charba.client.dom.safehtml;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.utils.RegExp;

/**
 * Internal utility to escape strings in HTML.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SafeHtmlUtils {

	/**
	 * Enumerates the set of chars which must be changed inside a string.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private enum CharacterToEscape
	{
		AMPERSAND(Constants.AMPERSAND, "&amp;"),
		GREATER_THAN(Constants.GT, "&gt;"),
		LESS_THANT(Constants.LT, "&lt;"),
		SINGLE_QUOTE("\'", "&#39;"),
		DOUBLE_QUOTE("\"", "&quot;");

		// value to find into the string
		private final String value;
		// replacement of the value if found
		private final String replacement;

		/**
		 * Creates the object by the value to find and the replacement.
		 * 
		 * @param value string value to find into the string
		 * @param replacement replacement when the value has been found
		 */
		private CharacterToEscape(String value, String replacement) {
			this.value = value;
			this.replacement = replacement;
		}

		/**
		 * Returns the value to find into the string.
		 * 
		 * @return the value to find into the string
		 */
		String getValue() {
			return value;
		}

		/**
		 * Returns the replacement string to apply every time the value is found into the string.
		 * 
		 * @return the replacement string to apply every time the value is found into the string
		 */
		String getReplacement() {
			return replacement;
		}

		/**
		 * Escapes the char passed as argument.
		 * 
		 * @param input char to be escaped
		 * @return a string of escaped char
		 */
		static String escape(char input) {
			// scans all the escapable chars
			for (CharacterToEscape charToEscape : values()) {
				// gets the first char
				char charToTest = charToEscape.getValue().charAt(0);
				// checks if equals to the argument
				if (input == charToTest) {
					// if yes, returns the replacement
					return charToEscape.getReplacement();
				}
			}
			// if here the char must not be escaped
			// then returns the argument as string
			return Constants.EMPTY_STRING + input;
		}

		/**
		 * Escapes the string passed as argument.
		 * 
		 * @param input string to be escaped
		 * @return an escaped string
		 */
		static String escapeAll(String input) {
			// stores the argument
			String result = input;
			// checks if argument is consistent
			// if not, returns the argument
			if (input != null) {
				// scans all the escapable chars
				for (CharacterToEscape charToEscape : values()) {
					// checks if the argument contains the value
					if (result.contains(charToEscape.getValue())) {
						// if yes, replace all occurences of the value with the replacement
						result = result.replaceAll(charToEscape.getValue(), charToEscape.getReplacement());
					}
				}
			}
			// returns the result
			return result;
		}

	}

	// regular expession with all HTML chars to escape
	private static final RegExp HTML_CHARS_RE = new RegExp("[&<>'\"]");

	/**
	 * To avoid any instantiation
	 */
	private SafeHtmlUtils() {
		// do nothing
	}

	/**
	 * HTML escapes a character.
	 *
	 * @param input the character to be escaped
	 * @return a string containing either the input character or html escaped value
	 */
	static String htmlEscape(char input) {
		return CharacterToEscape.escape(input);
	}

	/**
	 * HTML escapes a string.
	 *
	 * @param input the string to be escaped
	 * @return the escaped string
	 */
	static String htmlEscape(String input) {
		// checks if the string contains at least 1 char to escape
		if (!HTML_CHARS_RE.test(input)) {
			// if not, the argument is returned
			// without any changes
			return input;
		}
		// applies the replacements and returns the result
		return CharacterToEscape.escapeAll(input);
	}

}