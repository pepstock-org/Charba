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
 * Enumerates the unit of measure for sizes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Unit implements Key
{
	/**
	 * A percentage value. It is often used to define a size as relative to an element's parent object.
	 */
	PCT("%"),
	/**
	 * One pixel. For screen displays, it traditionally represents one device pixel (dot).
	 */
	PX("px");

	// suffix value of size
	private final String value;

	/**
	 * Creates with the suffix value to use into size.
	 * 
	 * @param value suffix value of unit
	 */
	private Unit(String value) {
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

	/**
	 * Creates a size adding the suffix of unit to the argument.
	 * 
	 * @param numValue number which represents the size.
	 * @return a size as string
	 */
	public String format(double numValue) {
		return numValue + value;
	}

	/**
	 * Creates a size adding the suffix of unit to the argument.
	 * 
	 * @param numValue number which represents the size.
	 * @return a size as string
	 */
	public String format(int numValue) {
		return numValue + value;
	}
}
