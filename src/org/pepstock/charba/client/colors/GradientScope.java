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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.commons.Key;

/**
 * A gradient needs to have coordinates and/or radius to be created.<br>
 * The dimension to create a gradient are decided by Charba, automatically based on canvas or chart area element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum GradientScope implements Key
{
	/**
	 * The dimension to create a gradient is the chart area.
	 */
	CHART("chart"),
	/**
	 * The dimension to create a gradient is the canvas.
	 */
	CANVAS("canvas");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private GradientScope(String value) {
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
