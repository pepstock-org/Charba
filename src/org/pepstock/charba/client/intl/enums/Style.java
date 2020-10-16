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
 * Enumerated the options in order to set the formatting style to use.<br>
 * The default is "decimal".
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Style implements Key
{
	/**
	 * For plain number formatting.
	 */
	DECIMAL("decimal"),
	/**
	 * For currency formatting.
	 */
	CURRENCY("currency"),
	/**
	 * For percent formatting.
	 */
	PERCENT("percent"),
	/**
	 * For unit formatting.
	 */
	UNIT("unit");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private Style(String value) {
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
