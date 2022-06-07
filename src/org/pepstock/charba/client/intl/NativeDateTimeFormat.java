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
package org.pepstock.charba.client.intl;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * The object is a constructor for objects that enable language-sensitive date and time formatting.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, name = NativeName.DATETIME_FORMAT, namespace = JsPackage.GLOBAL)
final class NativeDateTimeFormat {

	/**
	 * Creates object that enables language sensitive date time formatting.
	 * 
	 * @param locale a string with a BCP 47 language tag
	 * @param options options to configure the date time format
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativeDateTimeFormat(String locale, NativeObject options) {
		// must be empty
	}

	/**
	 * Returns an array with a subset of the language tags provided in locales.<br>
	 * The language tags returned are those for which the runtime supports a locale in number formatting that the locale matching algorithm used considers a match, so that it
	 * wouldn't have to fall back to the default locale.
	 * 
	 * @param locales a string with a BCP 47 language tag, or an array of such strings.
	 * @param options options to configure the number format
	 * @return an array of strings representing a subset of the given locale tags that are supported in number formatting without having to fall back to the runtime's default
	 *         locale.
	 */
	static native ArrayString supportedLocalesOf(ArrayString locales, NativeObject options);

	/**
	 * Formats a date time according to the locale and formatting options of this object.
	 * 
	 * @param value the date to format
	 * @return the date time in the a string according to the locale and formatting options
	 */
	native String format(NativeDate value);

	/**
	 * Returns a new object with properties reflecting the locale and date time formatting options computed during initialization of this object.
	 * 
	 * @return new object with properties reflecting the locale and date time formatting options computed during initialization of this object
	 */
	native NativeObject resolvedOptions();

	/**
	 * Returns an array of objects containing the locale-specific tokens from which it possible to build custom strings while preserving the locale-specific parts.<br>
	 * It is useful for custom formatting of date time strings.
	 * 
	 * @param value date to format
	 * @return an array of objects containing the formatted date time in parts.
	 */
	native ArrayObject formatToParts(NativeDate value);

}
