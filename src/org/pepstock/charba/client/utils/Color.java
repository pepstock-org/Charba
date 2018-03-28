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
package org.pepstock.charba.client.utils;

/**
 * This class contains information about a color.<br>
 * You can requests a specific alpha (starting form source color and cloning it).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Color implements IsColor{

	/**
	 * Default alpha
	 */
	public static final double DEFAULT_ALPHA = 1F;

	// colors
	private final int red;
	private final int green;
	private final int blue;

	// alpha
	private double alpha = DEFAULT_ALPHA;

	/**
	 * Creates the color with RGB values.
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 */
	public Color(int r, int g, int b) {
		this(r, g, b, DEFAULT_ALPHA);
	}
	/**
	 * Creates the color with RGB values.
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 * @param a alpha value
	 */
	Color(int r, int g, int b, double a) {
		// checks if values are consistent
		checkChannelWithinBounds(r);
		checkChannelWithinBounds(g);
		checkChannelWithinBounds(b);
		checkAlphaWithinBounds(a);

		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
	}
	
	/**
	 * @return the red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * Clones the color applying the alpha value.
	 * 
	 * @param alpha the alpha to set
	 * @return the color with the alpha value
	 */
	public IsColor alpha(double alpha) {
		checkAlphaWithinBounds(alpha);
		// clones the color
		return new Color(getRed(), getGreen(), getBlue(), alpha);
	}

	/**
	 * Returns RGBA string value which represents the color.
	 * @return RGBA string value which represents the color
	 */
	public String toRGBA() {
		return ColorBuilder.RGBA_STARTING_CHARS+"("+red+","+green+","+blue+","+alpha+")";
	}

	/**
	 * Returns RGB string value which represents the color.
	 * @return RGB string value which represents the color
	 */
	public String toRGB() {
		return ColorBuilder.RGB_STARTING_CHARS+"("+red+","+green+","+blue+")";
	}

	/**
	 * Returns HEX string value which represents the color.
	 * @return HEX string value which represents the color.
	 */
	public String toHex() {
		return ColorBuilder.HEX_STARTING_CHAR + pad(Integer.toHexString(red)) + pad(Integer.toHexString(green)) + pad(Integer.toHexString(blue));
	}

	/**
	 * Applies the padding to a string to 2 chars.
	 * @param in string to be pad
	 * @return result string
	 */
	private static String pad(String in) {
		// string is 0 (should never happen)
		if (in.length() == 0) {
			return "00";
		}
		// if len is 1, adds a ZERO
		if (in.length() == 1) {
			return "0" + in;
		}
		// returns
		return in;
	}

	/**
	 * Any integer between 0 and 255 (inclusive) is valid.
	 * 
	 * @param channel channel to check
	 * @exception if the channel is nor within bounds
	 */
	private static void checkChannelWithinBounds(int channel) {
		if (channel < 0 || channel > 255) {
			throw new IllegalArgumentException("One argument is not within bounds (0-255)");
		}
	}
	
	/**
	 * Any double between 0.0d and 1.0d (inclusive) is valid.
	 * 
	 * @param alpha alpha value
	 * @exception if the channel is nor within bounds
	 */
	private static void checkAlphaWithinBounds(double alpha) {
		if (alpha < 0D || alpha > 1D) {
			throw new IllegalArgumentException("Alpha argument is not within bounds (0D-1D)");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + ", hex="+ toHex() + "]";
	}
}