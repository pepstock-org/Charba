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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TooltipPosition;

/**
 * Custom tooltip position to use into chart options to configure charts.<br>
 * It must not be equals of the out of the box ones.
 * 
 * @author Andrea "Stock" Stocchero
 * @see TooltipPosition
 */
public final class CustomTooltipPosition implements IsTooltipPosition {
	// name of tooltip position
	private final String name;

	/**
	 * Creates the object using the name of tooltip position passes as argument
	 * 
	 * @param name of tooltip position. It must not be equals of the out of the box ones
	 */
	public CustomTooltipPosition(String name) {
		// checks if consistent
		if (name == null) {
			throw new IllegalArgumentException("The tooltip position argument is null");
		}
		// check is the name is the same of already out of the box ones
		if (Key.hasKeyByValue(TooltipPosition.class, name)) {
			throw new IllegalArgumentException("The tooltip position '" + name + "' is already defined");
		}
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return name;
	}

}
