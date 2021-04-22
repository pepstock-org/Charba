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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * The object is a constructor for objects that enable language sensitive formatting.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <F> type of formatter
 * @param <O> type of formatter options
 * @param <T> type of object to be formatted
 * @param <R> type of resolved options
 */
abstract class AbstractFormat<F extends AbtsractFormatWrapper<?, T, R>, O, T, R> {
	// format parts factory instance
	private static final FormatPartsFactory FACTORY = new FormatPartsFactory();
	// locale instance
	private final CLocale locale;
	// native format instance
	private final F formatter;
	// resolver instance instance
	// at the beginning is not set but it will be only once
	private R resolvedOptions = null;

	/**
	 * Creates object that enables language sensitive formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the format
	 */
	AbstractFormat(CLocale locale, O options) {
		// checks if locale is consistent
		// stores locale
		this.locale = Checker.checkAndGetIfValid(locale, "Locale argument");
		// checks if the locale is supported
		ArrayString supportedLocales = supportedLocalesOf(locale);
		// checks if is supported
		Checker.checkIfNotEqualTo(supportedLocales.length(), 0, "Locale '" + locale.getIdentifier() + "' is not supported. Locale array size");
		// create the native format
		this.formatter = createFormat(locale, options);
	}

	/**
	 * Returns an array with a subset of the language tags provided in locales.<br>
	 * The language tags returned are those for which the runtime supports a locale in formatting that the locale matching algorithm used considers a match, so that it wouldn't
	 * have to fall back to the default locale.
	 * 
	 * @param locale a string with a BCP 47 language tag.
	 * @return an array of strings representing a subset of the given locale tags that are supported in formatting without having to fall back to the runtime's default locale.
	 */
	abstract ArrayString supportedLocalesOf(CLocale locale);

	/**
	 * Creates a wrapper object that enables language sensitive formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the format
	 * @return a wrapper object that enables language sensitive formatting
	 */
	abstract F createFormat(CLocale locale, O options);

	/**
	 * Returns the locale which has initialized the format.
	 * 
	 * @return the locale which has initialized the format
	 */
	public final CLocale getLocale() {
		return locale;
	}

	/**
	 * Formats a value according to the locale and formatting options of this object.
	 * 
	 * @param value the vale to format
	 * @return the value in the a string according to the locale and formatting options
	 */
	public final String format(T value) {
		// checks if value is consistent
		if (value != null) {
			return formatter.format(value);
		}
		// if here, the argument is not consistent
		// then returns an empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns a new object with properties reflecting the locale and value formatting options computed during initialization of this object.
	 * 
	 * @return new object with properties reflecting the locale and value formatting options computed during initialization of this object
	 */
	public final R resolvedOptions() {
		// checks if already called
		if (resolvedOptions == null) {
			// creates the resolved options
			resolvedOptions = formatter.resolvedOptions();
		}
		// returns the resolved options
		return resolvedOptions;
	}

	/**
	 * Returns an array of objects containing the locale-specific tokens from which it possible to build custom strings while preserving the locale-specific parts.<br>
	 * It is useful for custom formatting of value strings.
	 * 
	 * @param value value to format
	 * @return an array of objects containing the formatted value in parts.
	 */
	public final List<FormatPart> formatToParts(T value) {
		// checks if value is consistent
		if (value != null) {
			// gets array of object
			ArrayObject array = formatter.formatToParts(value);
			// and transforms it in a list
			return ArrayListHelper.unmodifiableList(array, FACTORY);
		}
		// if here, the argument is not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Internal format part factory in order to be used when {@link AbstractFormat#formatToParts(java.lang.Object)} is invoked.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class FormatPartsFactory implements NativeObjectContainerFactory<FormatPart> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public FormatPart create(NativeObject nativeObject) {
			return new FormatPart(nativeObject);
		}

	}
}
