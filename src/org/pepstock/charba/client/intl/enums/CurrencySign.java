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
 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
 * You can enable this formatting you can use "accounting".<br>
 * The default value is "standard".
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum CurrencySign implements Key
{
	/**
	 * To use for wrapping the number with parentheses instead of appending a minus sign.
	 */
	ACCOUNTING("accounting"),
	/**
	 * To use a standard accounting formatting.
	 */
	STANDARD("standard");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private CurrencySign(String value) {
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
