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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the set of values to specify the current text baseline used when drawing text.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum TextBaseline implements Key
{
	/**
	 * The text baseline is the normal alphabetic baseline. Default value.
	 */
	ALPHABETIC("alphabetic"),
	/**
	 * The text baseline is the bottom of the bounding box. This differs from the ideographic baseline in that the ideographic baseline doesn't consider descenders.
	 */
	BOTTOM("bottom"),
	/**
	 * The text baseline is the hanging baseline.
	 */
	HANGING("hanging"),
	/**
	 * The text baseline is the ideographic baseline; this is the bottom of the body of the characters, if the main body of characters protrudes beneath the alphabetic baseline.
	 */
	IDEOGRAPHIC("ideographic"),
	/**
	 * The text baseline is the middle of the em square.
	 */
	MIDDLE("middle"),
	/**
	 * The text baseline is the top of the em square.
	 */
	TOP("top");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private TextBaseline(String value) {
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
