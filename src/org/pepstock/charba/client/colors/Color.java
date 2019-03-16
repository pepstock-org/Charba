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
 * This class contains information about a color.<br>
 * You can requests a specific alpha (starting form source color and cloning it).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Color implements IsColor {

	/**
	 * Default transparency is {@value DEFAULT_ALPHA} (no transparency).
	 */
	public static final double DEFAULT_ALPHA = 1F;

	// colors
	private final int red;
	private final int green;
	private final int blue;
	// alpha
	private double alpha = DEFAULT_ALPHA;
	// srgb
	private final int srgb;

	/**
	 * Creates the color with RGB values.
	 * 
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 */
	public Color(int r, int g, int b) {
		this(r, g, b, DEFAULT_ALPHA);
	}

	/**
	 * Creates the color with RGB values.
	 * 
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
		// sets values
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
		this.srgb = (int) Math.round(alpha * 255D) << 24 | red << 16 | green << 8 | blue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getRed()
	 */
	@Override
	public int getRed() {
		return red;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getGreen()
	 */
	@Override
	public int getGreen() {
		return green;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getBlue()
	 */
	@Override
	public int getBlue() {
		return blue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getAlpha()
	 */
	@Override
	public double getAlpha() {
		return alpha;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#alpha(double)
	 */
	@Override
	public IsColor alpha(double alpha) {
		checkAlphaWithinBounds(alpha);
		// clones the color
		return new Color(getRed(), getGreen(), getBlue(), alpha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGBA()
	 */
	@Override
	public String toRGBA() {
		return ColorBuilder.RGBA_STARTING_CHARS + "(" + red + "," + green + "," + blue + "," + alpha + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGB()
	 */
	@Override
	public String toRGB() {
		return ColorBuilder.RGB_STARTING_CHARS + "(" + red + "," + green + "," + blue + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHex()
	 */
	@Override
	public String toHex() {
		return ColorBuilder.HEX_STARTING_CHAR + pad(Integer.toHexString(red)) + pad(Integer.toHexString(green)) + pad(Integer.toHexString(blue));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGBs()
	 */
	@Override
	public int toRGBs() {
		return srgb;
	}

	/**
	 * Applies the padding to a string to 2 chars.
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + ", hex=" + toHex() + "]";
	}
}