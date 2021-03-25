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

import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.data.BarDataset;

/**
 * Property which map the border width and border radius types on {@link BarDataset}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum BorderItemType implements Key
{
	/**
	 * When border width or radius are stored as an array of integers.
	 */
	INTEGERS("integers"),
	/**
	 * When border width or radius are stored as an object, {@link BarBorderRadius} or {@link BarBorderWidth}.
	 */
	OBJECTS("objects"),
	/**
	 * When border width or radius are missing or set by a callback, {@link WidthCallback} or {@link RadiusCallback}.
	 */
	UNKNOWN("unknown");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private BorderItemType(String value) {
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