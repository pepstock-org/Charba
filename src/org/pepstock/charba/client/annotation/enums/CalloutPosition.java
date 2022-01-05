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
package org.pepstock.charba.client.annotation.enums;

import org.pepstock.charba.client.annotation.Callout;
import org.pepstock.charba.client.commons.Key;

/**
 * Property to set the position of a {@link Callout} with respect to the label.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum CalloutPosition implements Key
{
	/**
	 * This property allow to the plugin to calculate the best position of the callout. Is default.
	 */
	AUTO("auto"),
	/**
	 * This property sets the callout on top of the label.
	 */
	TOP("top"),
	/**
	 * This property sets the callout on left of the label.
	 */
	LEFT("left"),
	/**
	 * This property sets the callout on bottom of the label.
	 */
	BOTTOM("bottom"),
	/**
	 * This property sets the callout on right of the label.
	 */
	RIGHT("right");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private CalloutPosition(String value) {
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