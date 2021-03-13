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
package org.pepstock.charba.client.datalabels.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The align option defines the position of the label relative to the anchor point position and orientation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Align implements Key
{
	/**
	 * is the default: the label is centered on the anchor point.
	 */
	CENTER("center"),
	/**
	 * The label is positioned before the anchor point, following the same direction
	 */
	START("start"),
	/**
	 * The label is positioned after the anchor point, following the same direction
	 */
	END("end"),
	/**
	 * The label is positioned to the right of the anchor point (0 degrees)
	 */
	RIGHT("right"),
	/**
	 * The label is positioned to the bottom of the anchor point (90 degrees)
	 */
	BOTTOM("bottom"),
	/**
	 * The label is positioned to the left of the anchor point (180 degrees)
	 */
	LEFT("left"),
	/**
	 * The label is positioned to the top of the anchor point (270 degrees)
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
