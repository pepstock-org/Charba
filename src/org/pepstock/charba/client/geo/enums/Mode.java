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
 * Enumerates the operation modes for the scale, area means that the area is linearly increasing whereas radius the radius is.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Mode implements Key
{
	/**
	 * The area is linearly increasing.
	 */
	AREA("area"),
	/**
	 * Uses the radius as is.
	 */
	RADIUS("radius");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Mode(String value) {
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
