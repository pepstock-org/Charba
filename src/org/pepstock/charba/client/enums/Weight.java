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
	NORMAL("normal"),
	/**
	 * Defines thick characters.
	 */
	BOLD("bold"),
	/**
	 * Defines thicker characters.
	 */
	BOLDER("bolder"),
	/**
	 * Defines lighter characters.
	 */
	LIGHTER("lighter"),
	/**
	 * Sets this property to its default value.
	 */
	INITIAL("initial"),
	/**
	 * Inherits this property from its parent element.
	 */
	INHERIT("inherit");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Weight(String value) {
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
