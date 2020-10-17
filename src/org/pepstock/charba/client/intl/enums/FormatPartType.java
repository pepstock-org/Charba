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
import org.pepstock.charba.client.intl.FormatPart;

/**
 * Enumerates the possible types of a {@link FormatPart} can have.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum FormatPartType implements Key
{
	/**
	 * The currency string, such as the symbols "$" and "€" or the name "Dollar", "Euro" depending on how currencyDisplay is specified.
	 */
	CURRENCY("currency"),
	/**
	 * The decimal separator string (".").
	 */
	DECIMAL("decimal"),
	/**
	 * The fraction number.
	 */
	FRACTION("fraction"),
	/**
	 * The group separator string (",").
	 */
	GROUP("group"),
	/**
	 * The Infinity string ("∞").
	 */
	INFINITY("infinity"),
	/**
	 * The integer number.
	 */
	INTEGER("integer"),
	/**
	 * Any literal strings or whitespace in the formatted number.
	 */
	LITERAL("literal"),
	/**
	 * The minus sign string ("-").
	 */
	MINUS_SIGN("minusSign"),
	/**
	 * The NaN string ("NaN").
	 */
	NAN("nan"),
	/**
	 * The plus sign string ("+").
	 */
	PLUS_SIGN("plusSign"),
	/**
	 * The percent sign string ("%").
	 */
	PERCENT_SIGN("percentSign"),
	/**
	 * The unit string.
	 */
	UNIT("unit"),
	/**
	 * Unknown token.<br>
	 * THis is an add-on of <b>Charba</b> implementation.
	 */
	UNKNOWN("unknown");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private FormatPartType(String value) {
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
