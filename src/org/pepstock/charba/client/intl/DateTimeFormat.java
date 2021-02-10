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

import java.util.Date;

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.defaults.IsDefaultDateTimeFormatOptions;

/**
 * The object is a constructor for objects that enable language-sensitive date and time formatting.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DateTimeFormat extends AbstractFormat<DateTimeFormatWrapper, DateTimeFormatOptions, Date, IsDefaultDateTimeFormatOptions> {

	/**
	 * Creates object that enable language sensitive date time formatting, using the default locale.
	 */
	public DateTimeFormat() {
		this(CLocale.getDefault());
	}

	/**
	 * Creates object that enable language sensitive date time formatting, using the locale options.
	 * 
	 * @param locale a locale instance
	 */
	public DateTimeFormat(CLocale locale) {
		this(locale, null);
	}

	/**
	 * Creates object that enables language sensitive date time formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the date time format
	 */
	public DateTimeFormat(CLocale locale, DateTimeFormatOptions options) {
		super(locale, options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbstractFormat#supportedLocalesOf(org.pepstock.charba.client.intl.CLocale)
	 */
	@Override
	ArrayString supportedLocalesOf(CLocale locale) {
		return NativeDateTimeFormat.supportedLocalesOf(ArrayString.fromOrEmpty(locale.getIdentifier()), BaseFormatOptions.LOOKUP.nativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbstractFormat#createFormat(org.pepstock.charba.client.intl.CLocale, java.lang.Object)
	 */
	@Override
	DateTimeFormatWrapper createFormat(CLocale locale, DateTimeFormatOptions options) {
		return new DateTimeFormatWrapper(locale, options);
	}

}
