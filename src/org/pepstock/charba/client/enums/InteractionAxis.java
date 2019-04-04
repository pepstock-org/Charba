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
 * Can be set to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum InteractionAxis implements Key
{

	/**
	 * X directions are used in calculating distances.
	 */
	X("x"),
	/**
	 * Y directions are used in calculating distances.
	 */
	Y("y"),
	/**
	 * XY directions are used in calculating distances.
	 */
	XY("xy");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private InteractionAxis(String value) {
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