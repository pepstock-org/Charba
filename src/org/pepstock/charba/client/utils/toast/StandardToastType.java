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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * This is the standard implementation of a custom toast type.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class StandardToastType extends AbstractStandardType implements IsToastType {

	// type color instance
	private final IsColor color;

	/**
	 * Builds the object with the custom key value
	 * 
	 * @param name value to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 */
	StandardToastType(Key name, IsColor color, IsColor backgroundColor) {
		super(name, backgroundColor);
		// stores colors
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsToastType#getColor()
	 */
	@Override
	public IsColor getColor() {
		return color;
	}

}
