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

import org.pepstock.charba.client.utils.RegExp;

/**
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SafeHtmlUtils {
	
	private enum CharacterToEscape {
		
		AMPERSAND("&", "&amp;"),
		GREATER_THAN(">", "&gt;"),
		LESS_THANT("<", "&lt;"),
		SINGLE_QUOTE("\'", "&#39;"),
		DOUBLE_QUOTE("\"", "&quot;");
		
		private final String value;
		
		private final String replacement;

		private CharacterToEscape(String value, String replacement) {
			this.value = value;
			this.replacement = replacement;
		}

		/**
		 * @return the value
		 */
		String getValue() {
			return value;
		}

		/**
		 * @return the replacement
		 */
		String getReplacement() {
			return replacement;
		}

		static String escape(char input) {
			for (CharacterToEscape charToEscape : values()) {
				char charToTest = charToEscape.getValue().charAt(0); 
				if (input == charToTest) {
					return charToEscape.getReplacement();
				}
			}
			return ""+input;
		}

		static String escapeAll(String input) {
			String result = input;
			if (input != null) {
				for (CharacterToEscape charToEscape : values()) {
					if (result.contains(charToEscape.getValue())) {
						result = result.replaceAll(charToEscape.getValue(), charToEscape.getReplacement());
					}
				}
			}
			return result;
		}
		
	}

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
		if (!HTML_CHARS_RE.test(input)) {
			return input;
		}
		return CharacterToEscape.escapeAll(input);
	}

}