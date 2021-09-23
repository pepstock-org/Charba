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

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This is the standard implementation of a custom toast type.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class StandardToastType extends AbstractStandardType implements IsToastType {

	// type color instance
	private final IsColor color;
	// type pattern instance
	private final Pattern pattern;

	/**
	 * Builds the object with the custom key value
	 * 
	 * @param name value to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 * @param pattern pattern instance as background
	 * @param gradient gradient instance as background
	 */
	StandardToastType(Key name, IsColor color, IsColor backgroundColor, Pattern pattern, Gradient gradient) {
		super(name, backgroundColor, gradient);
		// stores color and pattern
		this.color = color;
		this.pattern = pattern;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsToastType#getBackgroundAsPattern()
	 */
	@Override
	public Pattern getBackgroundAsPattern() {
		return pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.AbstractStandardType#toCSSBackground()
	 */
	@Override
	String toCSSBackground() {
		// checks if gradient
		if (getBackgroundAsGradient() != null) {
			// returns gradient CSS
			return Utilities.toCSSBackgroundProperty(getBackgroundAsGradient());
		} else if (getBackgroundAsPattern() != null) {
			// checks if pattern
			// returns pattern CSS
			return Utilities.toCSSBackgroundProperty(getBackgroundAsPattern());
		}
		// is a color
		// the returns RGBA
		return getBackgroundColor().toRGBA();
	}

}
