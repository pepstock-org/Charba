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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The font-style property specifies the font style for a text.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum FontStyle implements Key
{
	/**
	 * The browser displays a normal font style. This is default
	 */
	NORMAL("normal"),
	/**
	 * The browser displays thick characters.
	 */
	BOLD("bold"),
	/**
	 * The browser displays an oblique font style
	 */
	OBLIQUE("oblique"),
	/**
	 * The browser displays an italic font style
	 */
	ITALIC("italic");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private FontStyle(String value) {
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