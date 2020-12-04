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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;

/**
 * The object is a constructor for objects that enable language sensitive number formatting.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NumberFormat extends AbstractFormat<NumberFormatWrapper, NumberFormatOptions, Double, IsDefaultNumberFormatOptions> {
	
	/**
	 * Creates object that enable language sensitive number formatting, using the defualt locale.
	 */
	public NumberFormat() {
		this(CLocale.getDefault());
	}

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
		super(locale, options);
	}

	/**
	 * Formats a number according to the locale and formatting options of this object.
	 * 
	 * @param value the number to format
	 * @return the number into a string according to the locale and formatting options
	 */
	public final String format(double value) {
		return super.format(value);
	}

	/**
	 * Returns an array of objects containing the locale-specific tokens from which it possible to build custom strings while preserving the locale-specific parts.<br>
	 * It is useful for custom formatting of number strings.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer.
	 * 
	 * @param value number to format
	 * @return an array of objects containing the formatted number in parts.
	 */
	public final List<FormatPart> formatToParts(double value) {
		return super.formatToParts(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbstractFormat#supportedLocalesOf(org.pepstock.charba.client.intl.CLocale)
	 */
	@Override
	ArrayString supportedLocalesOf(CLocale locale) {
		return NativeNumberFormat.supportedLocalesOf(ArrayString.fromOrEmpty(locale.getIdentifier()), BaseFormatOptions.LOOKUP.nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbstractFormat#createFormat(org.pepstock.charba.client.intl.CLocale, java.lang.Object)
	 */
	@Override
	NumberFormatWrapper createFormat(CLocale locale, NumberFormatOptions options) {
		return new NumberFormatWrapper(locale, options);
	}

}
