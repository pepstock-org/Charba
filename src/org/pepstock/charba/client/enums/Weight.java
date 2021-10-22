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
import org.pepstock.charba.client.items.Undefined;

/**
 * The weight sets how thick or thin characters in text should be displayed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Weight implements Key
{
	/**
	 * Defines normal characters. This is default.
	 */
	NORMAL("normal", Undefined.INTEGER),
	/**
	 * Defines thick characters.
	 */
	BOLD("bold", Undefined.INTEGER),
	/**
	 * Defines thicker characters.
	 */
	BOLDER("bolder", Undefined.INTEGER),
	/**
	 * Defines lighter characters.
	 */
	LIGHTER("lighter", Undefined.INTEGER),
	/**
	 * Sets this property to its default value.
	 */
	INITIAL("initial", Undefined.INTEGER),
	/**
	 * Inherits this property from its parent element.
	 */
	INHERIT("inherit", Undefined.INTEGER),
	/**
	 * Defines from thin to thick characters, value 100.
	 */
	W100("100", 100),
	/**
	 * Defines from thin to thick characters, value 200.
	 */
	W200("200", 200),
	/**
	 * Defines from thin to thick characters, value 300.
	 */
	W300("300", 300),
	/**
	 * Defines from thin to thick characters, value 400.
	 */
	W400("400", 400),
	/**
	 * Defines from thin to thick characters, value 500.
	 */
	W500("500", 500),
	/**
	 * Defines from thin to thick characters, value 600.
	 */
	W600("600", 600),
	/**
	 * Defines from thin to thick characters, value 700.
	 */
	W700("700", 700),
	/**
	 * Defines from thin to thick characters, value 800.
	 */
	W800("800", 800),
	/**
	 * Defines from thin to thick characters, value 900.
	 */
	W900("900", 900);

	// name value of property
	private final String value;
	// value as number
	private final int intValue;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param intValue value of the property as integer, where weight must be set as integer.
	 */
	private Weight(String value, int intValue) {
		this.intValue = intValue;
		this.value = value == null ? String.valueOf(intValue) : value;
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

	/**
	 * Returns the value definition from thin to thick characters.
	 * 
	 * @return the value definition from thin to thick characters
	 */
	public int getValueAsInt() {
		return intValue;
	}

	/**
	 * Returns <code>true</code> if the weight has been defined by a number.
	 * 
	 * @return <code>true</code> if the weight has been defined by a number
	 */
	public boolean isValueAsInt() {
		return Undefined.isNot(intValue);
	}

	/**
	 * Returns the weight instance searched by the integer value.
	 * 
	 * @param value integer value to use for searching
	 * @param defaultValue default value if the value does not match
	 * @return the weight instance searched by the integer value
	 */
	public static Weight getByIntValue(int value, Weight defaultValue) {
		// if here is real integer
		// then scans all weights
		for (Weight w : values()) {
			if (w.isValueAsInt() && value == w.getValueAsInt()) {
				// found!
				return w;
			}
		}
		// returns default value
		return defaultValue;
	}

}
