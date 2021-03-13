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
package org.pepstock.charba.client.intl.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the possible values how to display the currency in currency formatting.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum CurrencyDisplay implements Key
{
	/**
	 * To use a localized currency symbol such as €, this is the default value.
	 */
	SYMBOL("symbol"),
	/**
	 * To use a narrow format symbol ("$100" rather than "US$100").
	 */
	NARROW_SYMBOL("narrowSymbol"),
	/**
	 * To use the ISO currency code.
	 */
	CODE("code"),
	/**
	 * To use a localized currency name such as "dollar".
	 */
	NAME("name");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private CurrencyDisplay(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}
