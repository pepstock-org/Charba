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
 * Enumerates the align options to define the position of the scale.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Align implements Key
{
	/**
	 * The scale is positioned to the right of the chart (vertical).
	 */
	RIGHT("right"),
	/**
	 * The scale is positioned to the bottom of the chart (horizontal).
	 */
	BOTTOM("bottom"),
	/**
	 * The scale is positioned to the left of the chart (vertical).
	 */
	LEFT("left"),
	/**
	 * The scale is positioned to the top of the chart (horizontal).
	 */
	TOP("top");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Align(String value) {
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
