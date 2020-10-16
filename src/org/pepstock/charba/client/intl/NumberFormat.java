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

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * The object is a constructor for objects that enable language sensitive number formatting.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NumberFormat {

	// empty options constants
	private static final NativeObject EMPTY_OPTIONS = new NumberFormatOptions().nativeObject();
	// locale instance
	private final CLocale locale;
	// native number format instance
	private final NativeNumberFormat nativeNumberFormat;

	/**
	 * Creates object that enable language sensitive number formatting, using the locale options.
	 * 
	 * @param locale a locale instance
	 */
	public NumberFormat(CLocale locale) {
		this(locale, null);
	}

	/**
	 * Creates object that enables language sensitive number formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the number format
	 */
	public NumberFormat(CLocale locale, NumberFormatOptions options) {
		// checks if locale is consistent
		if (locale == null) {
			throw new IllegalArgumentException("Locale argument is null");
		}
		// stores locale
		this.locale = locale;
		// checks if the locale is supported
		ArrayString supportedLocales = NativeNumberFormat.supportedLocalesOf(ArrayString.fromOrEmpty(locale.getIdentifier()), BaseFormatOptions.LOOKUP.nativeObject());
		// checks if is supported
		if (supportedLocales.length() == 0) {
			// if not, exception
			throw new IllegalArgumentException("Locale '" + locale.getIdentifier() + "' is not supported");
		}
		// create the native number format
		this.nativeNumberFormat = new NativeNumberFormat(locale.getIdentifier(), options != null ? options.nativeObject() : EMPTY_OPTIONS);
	}

	/**
	 * Returns the locale which has initialized the number format.
	 * 
	 * @return the locale which has initialized the number format
	 */
	public CLocale getLocale() {
		return locale;
	}

	/**
	 * Formats a number according to the locale and formatting options of this object.
	 * 
	 * @param value the number to format
	 * @return the number into a string according to the locale and formatting options
	 */
	public String format(double value) {
		return nativeNumberFormat.format(value);
	}

}
