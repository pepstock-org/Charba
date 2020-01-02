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
 * Defines the methods that all colors must have and many of them have got already an implementation that usually does not need
 * to be changed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsColor {

	/**
	 * Returns <code>true</code> if the passed color instance is consistent, that means with a right red, green, blue and alpha
	 * values and if the RGBA string representation is consistent as well.
	 * 
	 * @param color color instance to check
	 * @return <code>true</code> if the passed color instance is consistent
	 */
	static boolean isConsistent(IsColor color) {
		return isValid(color) && color.toRGBA() != null;
	}

	/**
	 * Returns <code>true</code> if the passed color instance is consistent, that means with a right red, green, blue and alpha
	 * values.
	 * 
	 * @param color color instance to check
	 * @return <code>true</code> if the passed color instance is consistent
	 */
	static boolean isValid(IsColor color) {
		return color != null && ColorUtil.isChannelWithinBounds(color.getRed()) && ColorUtil.isChannelWithinBounds(color.getGreen()) && ColorUtil.isChannelWithinBounds(color.getBlue()) && ColorUtil.isAlphaWithinBounds(color.getAlpha());
	}

	/**
	 * Checks if the passed color is consistent, that means with a right red, green, blue and alpha values.<br>
	 * If not consistent, an {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param color color instance to check
	 */
	static void checkIfValid(IsColor color) {
		// check if color is consistent
		if (color == null) {
			throw new IllegalArgumentException("Color instance is null");
		}
		// checks if values are consistent
		ColorUtil.checkChannelWithinBounds(color.getRed());
		ColorUtil.checkChannelWithinBounds(color.getGreen());
		ColorUtil.checkChannelWithinBounds(color.getBlue());
		ColorUtil.checkAlphaWithinBounds(color.getAlpha());
	}

	/**
	 * Returns the red value.
	 * 
	 * @return the red
	 */
	int getRed();

	/**
	 * Returns the green value.
	 * 
	 * @return the green
	 */
	int getGreen();

	/**
	 * Returns the blue value.
	 * 
	 * @return the blue
	 */
	int getBlue();

	/**
	 * Returns the alpha rate.
	 * 
	 * @return the alpha
	 */
	double getAlpha();

	/**
	 * Clones the color applying the alpha value.
	 * 
	 * @param alpha the alpha to set
	 * @return new color with the alpha value
	 */
	default IsColor alpha(double alpha) {
		// checks if color is consistent
		ColorUtil.checkAlphaWithinBounds(alpha);
		// clones the color
		return new Color(getRed(), getGreen(), getBlue(), alpha);
	}

	/**
	 * Returns RGBA string value which represents the color.
	 * 
	 * @return RGBA string value which represents the color
	 */
	default String toRGBA() {
		return ColorUtil.toRGB(IsColor.this, true);
	}

	/**
	 * Returns RGB string value which represents the color.
	 * 
	 * @return RGB string value which represents the color
	 */
	default String toRGB() {
		return ColorUtil.toRGB(IsColor.this, false);
	}

	/**
	 * Returns HEX string value which represents the color.
	 * 
	 * @return HEX string value which represents the color.
	 */
	default String toHex() {
		return ColorUtil.toHex(IsColor.this);
	}

	/**
	 * Returns HSLA string value which represents the color.
	 * 
	 * @return HSLA string value which represents the color
	 */
	default String toHSLA() {
		return ColorUtil.toHSL(IsColor.this, true);
	}

	/**
	 * Returns HSL string value which represents the color.
	 * 
	 * @return HSL string value which represents the color
	 */
	default String toHSL() {
		return ColorUtil.toHSL(IsColor.this, false);
	}

	/**
	 * As <a href="https://webstore.iec.ch/publication/6169">IEC 61966-2-1:1999</a>, applies to the encoding and communication
	 * of RGB colors used in computer systems and similar applications, by defining encoding transformations for use in defined
	 * reference conditions.<br>
	 * <br>
	 * 
	 * <pre>
	 *    byte     byte     byte     byte
	 * |        |        |        |        |
	 * +--------+--------+--------+--------+
	 *   alpha      red     green    blue
	 * </pre>
	 * 
	 * @return a integer value which represents the RGB color
	 */
	default int toRGBs() {
		// checks if color is consistent
		checkIfValid(IsColor.this);
		// returns RGBs value
		return (int) Math.round(getAlpha() * 255D) << 24 | getRed() << 16 | getGreen() << 8 | getBlue();
	}

	/**
	 * Creates a new color that is a brighter version of this color. <br>
	 * This method applies an arbitrary scale factor to each of the three RGB components of this color to create a brighter
	 * version of this color.<br>
	 * Although <code>brighter</code> and <code>darker</code> are inverse operations, the results of a series of invocations of
	 * these two methods might be inconsistent because of rounding errors.
	 * 
	 * @return a new color object that is a brighter version of this color with the same alpha value.
	 */
	default IsColor brighter() {
		return brighter(Double.NaN);
	}

	/**
	 * Creates a new color that is a brighter version of this color. <br>
	 * This method applies an arbitrary scale factor to each of the three RGB components of this color to create a brighter
	 * version of this color.<br>
	 * Although <code>brighter</code> and <code>darker</code> are inverse operations, the results of a series of invocations of
	 * these two methods might be inconsistent because of rounding errors.
	 * 
	 * @param alpha the alpha to set
	 * @return a new color object that is a brighter version of this color with the passed alpha value.
	 */
	default IsColor brighter(double alpha) {
		// checks if color is consistent
		checkIfValid(IsColor.this);
		// checks which alpha must be applied
		// if NaN uses the alpha of current color
		double alphaToUse = Double.isNaN(alpha) ? getAlpha() : alpha;
		ColorUtil.checkAlphaWithinBounds(alphaToUse);
		// gets colors
		int r = getRed();
		int g = getGreen();
		int b = getBlue();
		// notes
		// black.brighter() should return grey
		// applying brighter to blue will always return blue, brighter
		// non pure color (non zero rgb) will eventually return white
		// calculates the default RGB value factor
		// to compare
		int defaultRGBItem = (int) (1.0 / (1.0 - ColorUtil.FACTOR));
		// if RGB is 000
		if (r == 0 && g == 0 && b == 0) {
			// applies the calculated default RGB value factor
			return new Color(defaultRGBItem, defaultRGBItem, defaultRGBItem, alphaToUse);
		}
		// if red item is between 0 and calculated factor
		if (r > 0 && r < defaultRGBItem) {
			// uses calculated factor
			r = defaultRGBItem;
		}
		// if green item is between 0 and calculated factor
		if (g > 0 && g < defaultRGBItem) {
			// uses calculated factor
			g = defaultRGBItem;
		}
		// if blue item is between 0 and calculated factor
		if (b > 0 && b < defaultRGBItem) {
			// uses calculated factor
			b = defaultRGBItem;
		}
		// calculates the new RGB using the factor
		int newRed = Math.min((int) (r / ColorUtil.FACTOR), 255);
		int newGreen = Math.min((int) (g / ColorUtil.FACTOR), 255);
		int newBlue = Math.min((int) (b / ColorUtil.FACTOR), 255);
		return new Color(newRed, newGreen, newBlue, alphaToUse);
	}

	/**
	 * Creates a new <code>Color</code> that is a darker version of this <code>Color</code>.<br>
	 * This method applies an arbitrary scale factor to each of the three RGB components of this <code>Color</code> to create a
	 * darker version of this <code>Color</code>.<br>
	 * Although <code>brighter</code> and <code>darker</code> are inverse operations, the results of a series of invocations of
	 * these two methods might be inconsistent because of rounding errors.
	 * 
	 * @return a new color object that is a darker version of this color with the same alpha value.
	 */
	default IsColor darker() {
		return darker(Double.NaN);
	}

	/**
	 * Creates a new <code>Color</code> that is a darker version of this <code>Color</code>.<br>
	 * This method applies an arbitrary scale factor to each of the three RGB components of this <code>Color</code> to create a
	 * darker version of this <code>Color</code>.<br>
	 * Although <code>brighter</code> and <code>darker</code> are inverse operations, the results of a series of invocations of
	 * these two methods might be inconsistent because of rounding errors.
	 * 
	 * @param alpha the alpha to set
	 * @return a new color object that is a darker version of this color with the passed alpha value.
	 */
	default IsColor darker(double alpha) {
		// checks if color is consistent
		checkIfValid(IsColor.this);
		// checks which alpha must be applied
		// if NaN uses the alpha of current color
		double alphaToUse = Double.isNaN(alpha) ? getAlpha() : alpha;
		ColorUtil.checkAlphaWithinBounds(alphaToUse);
		// calculates the new RGB using the factor
		int newRed = Math.max((int) (getRed() * ColorUtil.FACTOR), 0);
		int newGreen = Math.max((int) (getGreen() * ColorUtil.FACTOR), 0);
		int newBlue = Math.max((int) (getBlue() * ColorUtil.FACTOR), 0);
		return new Color(newRed, newGreen, newBlue, alphaToUse);
	}

}
