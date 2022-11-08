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
 * The overflow property controls what happens to a label that is too big to fit into a rectangle.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Overflow implements Key
{
	/**
	 * If the label is too big, it will be cut to stay inside the rectangle. It is the default.
	 */
	CUT("cut"),
	/**
	 * The label is removed altogether if the rectangle is too small for it.
	 */
	HIDDEN("hidden");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Overflow(String value) {
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