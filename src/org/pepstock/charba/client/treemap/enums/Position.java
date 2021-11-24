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
package org.pepstock.charba.client.treemap.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The position property specifies the text vertical alignment used when drawing the label.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Position implements Key
{
	/**
	 * The text is in the top of the rectangle.
	 */
	TOP("top"),
	/**
	 * The text is in the bottom of the rectangle.
	 */
	BOTTOM("bottom"),
	/**
	 * The text is in the middle of the rectangle.
	 */
	MIDDLE("middle");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Position(String value) {
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