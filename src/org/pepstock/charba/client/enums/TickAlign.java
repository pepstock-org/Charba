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
 * Property to set alignment of the ticks.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum TickAlign implements Key
{
	/**
	 * The property sets the start alignment.
	 */
	START("start"),
	/**
	 * The property sets the center alignment.
	 */
	CENTER("center"),
	/**
	 * The property sets the center alignment.
	 */
	INNER("innner"),
	/**
	 * The property sets the end alignment.
	 */
	END("end");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private TickAlign(String value) {
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