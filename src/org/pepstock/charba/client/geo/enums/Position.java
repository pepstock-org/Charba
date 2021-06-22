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
package org.pepstock.charba.client.geo.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Property to set the position of the legend on the chart area.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Position implements Key
{
	/**
	 * The top property sets the top edge of an element.
	 */
	TOP("top"),
	/**
	 * the left property sets the left edge of an element.
	 */
	LEFT("left"),
	/**
	 * the bottom property sets the bottom edge of an element.
	 */
	BOTTOM("bottom"),
	/**
	 * the right property sets the right edge of an element.
	 */
	RIGHT("right"),
	/**
	 * The top-left property sets the top-left edge of an element.
	 */
	TOP_LEFT("top-left"),
	/**
	 * The top-right property sets the top-right edge of an element.
	 */
	TOP_RIGHT("top-right"),
	/**
	 * The bottom-left property sets the bottom-left edge of an element.
	 */
	BOTTOM_LEFT("bottom-left"),
	/**
	 * The bottom-right property sets the bottom-right edge of an element.
	 */
	BOTTOM_RIGHT("bottom-right");

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