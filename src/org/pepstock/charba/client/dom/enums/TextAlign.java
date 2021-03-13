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
 * Enumerates the horizontal alignment of a block element or table-cell box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum TextAlign implements Key
{
	/**
	 * The inline contents are centered within the line box.
	 */
	CENTER("center"),
	/**
	 * The same as right if direction is left-to-right and left if direction is right-to-left.
	 */
	END("end"),
	/**
	 * The inline contents are aligned to the left edge of the line box.
	 */
	LEFT("left"),
	/**
	 * The inline contents are aligned to the right edge of the line box.
	 */
	RIGHT("right"),
	/**
	 * The same as left if direction is left-to-right and right if direction is right-to-left.
	 */
	START("start");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private TextAlign(String value) {
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
