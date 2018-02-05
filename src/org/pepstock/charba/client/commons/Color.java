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
package org.pepstock.charba.client.commons;

/**
 * This class contains RGB information about a color.<br>
 * You can requests a specific alpha (starting form source color and cloning it).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Color {

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
		this.red = r;
		this.green = g;
		this.blue = b;
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
	public Color alpha(double alpha) {
		// clones the color
		Color color = new Color(getRed(), getGreen(), getBlue());
		// sets alpha
		color.alpha = alpha;
		return color;
	}

	/**
	 * Returns RGBA string value which represents the color.
	 * @return RGBA string value which represents the color
	 */
	public String toRGBA() {
		return "rgba("+red+","+green+","+blue+","+alpha+")";
	}

	/**
	 * Returns HEX string value which represents the color.
	 * @return HEX string value which represents the color.
	 */
	public String toHex() {
		return "#" + pad(Integer.toHexString(red)) + pad(Integer.toHexString(green)) + pad(Integer.toHexString(blue));
	}

	/**
	 * Applies the padding to a string to 2 chars.
	 * @param in string to be pad
	 * @return result string
	 */
	private String pad(String in) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + "]";
	}
}