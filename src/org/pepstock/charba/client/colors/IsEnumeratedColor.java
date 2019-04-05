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

/**
 * Internal interface of enumerated color, like {@link HtmlColor} and {@link GwtMaterialColor}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsEnumeratedColor extends IsColor {

	IsColor getColor();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getRed()
	 */
	@Override
	default int getRed() {
		return getColor().getRed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getGreen()
	 */
	@Override
	default int getGreen() {
		return getColor().getGreen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getBlue()
	 */
	@Override
	default int getBlue() {
		return getColor().getBlue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getAlpha()
	 */
	@Override
	default double getAlpha() {
		return getColor().getAlpha();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#alpha(double)
	 */
	@Override
	default IsColor alpha(double alpha) {
		return getColor().alpha(alpha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHex()
	 */
	@Override
	default String toHex() {
		return getColor().toHex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGBA()
	 */
	@Override
	default String toRGBA() {
		return getColor().toRGBA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGB()
	 */
	@Override
	default String toRGB() {
		return getColor().toRGB();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHSLA()
	 */
	@Override
	default String toHSLA() {
		return getColor().toHSLA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHSL()
	 */
	@Override
	default String toHSL() {
		return getColor().toHSL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGBs()
	 */
	@Override
	default int toRGBs() {
		return getColor().toRGBs();
	}

}
