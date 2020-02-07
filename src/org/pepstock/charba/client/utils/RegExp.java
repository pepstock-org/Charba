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
package org.pepstock.charba.client.utils;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Regular expressions are patterns used to match character combinations in strings.<br>
 * In JavaScript, regular expressions are also objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class RegExp {

	/**
	 * Creates a regular expression. See
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp">MDN RegExp</a>.
	 * 
	 * @param pattern the text of the regular expression. Patterns can include special characters so they can match a wider
	 *            range of values than would a literal string.
	 */
	public RegExp(String pattern) {
		// do nothing
	}

	/**
	 * Creates a regular expression. See
	 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp">MDN RegExp</a>.
	 * 
	 * @param pattern the text of the regular expression. Patterns can include special characters so they can match a wider
	 *            range of values than would a literal string.
	 * @param flags If specified, flags indicates the flags to add. If flags is not specified and a regular expressions object
	 *            is supplied, that object's flags (and lastIndex value) will be copied over.
	 */
	public RegExp(String pattern, String flags) {
		// do nothing
	}

	/**
	 * Executes a search for a match in a string.<br>
	 * It returns an array of information or null on a mismatch.
	 * 
	 * @param s string to use for matching
	 * @return an array of information or null on a mismatch.
	 */
	public native RegExpResult exec(String s);
	
	/**
	 * Executes a search for a match between a regular expression and a specified string.
	 * 
	 * @param value the string against which to match the regular expression.
	 * @return <code>true</code> if there is a match between the regular expression and the string. Otherwise, <code>false</code>.
	 */
	public native boolean test(String value);
}