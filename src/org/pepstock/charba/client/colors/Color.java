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

import org.pepstock.charba.client.commons.Constants;

/**
 * This class contains information about a color.<br>
 * You can requests a specific alpha (starting form source color and cloning it).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Color implements IsColor {

	/**
	 * Default transparency is <b>{@value DEFAULT_ALPHA}</b>, (no transparency).
	 */
	public static final double DEFAULT_ALPHA = 1D;
	// darker factor
	private static final double FACTOR = 0.7D;
	// zero for padding
	private static final String PADDING_ZERO = "0";
	// double zero for padding
	private static final String PADDING_DOUBLE_ZERO = "00";

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
		return ColorBuilder.RGBA_STARTING_CHARS + Constants.OPEN_ROUND_BRACKET + red + Constants.COMMA + green + Constants.COMMA + blue + Constants.COMMA + alpha + Constants.CLOSE_ROUND_BRACKET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toRGB()
	 */
	@Override
	public String toRGB() {
		return ColorBuilder.RGB_STARTING_CHARS + Constants.OPEN_ROUND_BRACKET + red + Constants.COMMA + green + Constants.COMMA + blue + Constants.CLOSE_ROUND_BRACKET;
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
	 * @see org.pepstock.charba.client.colors.IsColor#toHSLA()
	 */
	@Override
	public String toHSLA() {
		return ColorBuilder.HSLA_STARTING_CHARS + Constants.OPEN_ROUND_BRACKET + createHSLAsString(red, green, blue) + Constants.COMMA + alpha + Constants.CLOSE_ROUND_BRACKET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#toHSL()
	 */
	@Override
	public String toHSL() {
		return ColorBuilder.HSL_STARTING_CHARS + Constants.OPEN_ROUND_BRACKET + createHSLAsString(red, green, blue) + Constants.CLOSE_ROUND_BRACKET;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#brighter()
	 */
	@Override
	public IsColor brighter() {
		return brighter(Double.NaN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#brighter(double)
	 */
	@Override
	public IsColor brighter(double alpha) {
		// checks which alpha must be applied
		// if NaN uses the alpha of current color
		double alphaToUse = Double.isNaN(alpha) ? getAlpha() : alpha;
		checkAlphaWithinBounds(alphaToUse);
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
		int defaultRGBItem = (int) (1.0 / (1.0 - FACTOR));
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
		int newRed = Math.min((int) (r / FACTOR), 255);
		int newGreen = Math.min((int) (g / FACTOR), 255);
		int newBlue = Math.min((int) (b / FACTOR), 255);
		return new Color(newRed, newGreen, newBlue, alphaToUse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#darker()
	 */
	@Override
	public IsColor darker() {
		return darker(Double.NaN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#darker(double)
	 */
	@Override
	public IsColor darker(double alpha) {
		// checks which alpha must be applied
		// if NaN uses the alpha of current color
		double alphaToUse = Double.isNaN(alpha) ? getAlpha() : alpha;
		checkAlphaWithinBounds(alphaToUse);
		// calculates the new RGB using the factor
		int newRed = Math.max((int) (getRed() * FACTOR), 0);
		int newGreen = Math.max((int) (getGreen() * FACTOR), 0);
		int newBlue = Math.max((int) (getBlue() * FACTOR), 0);
		return new Color(newRed, newGreen, newBlue, alphaToUse);
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
			return PADDING_DOUBLE_ZERO;
		}
		// if len is 1, adds a ZERO
		if (in.length() == 1) {
			return PADDING_ZERO + in;
		}
		// returns
		return in;
	}

	/**
	 * Convert a RGB Color to it corresponding HSL values.
	 *
	 * @return a string representation of HSL color, only <code>h,s%,l%</code>
	 * @see See explanation <a href="http://www.niwa.nu/2013/05/math-behind-colorspace-conversions-rgb-hsl/">Math behind
	 *      colorspace conversions, RGB-HSL</a>
	 */
	private static String createHSLAsString(int red, int green, int blue) {
		// creates string builder
		StringBuilder sb = new StringBuilder();
		// converts the RGB values to the range 0-1, this can be done by dividing the value by 255 for 8-bit
		// color depth
		double r = red / 255D;
		double g = green / 255D;
		double b = blue / 255D;
		// finds the minimum and maximum values of R, G and B
		double min = Math.min(r, Math.min(g, b));
		double max = Math.max(r, Math.max(g, b));
		// ------------------
		// HUE calculation
		// ------------------
		// the Hue formula is depending on what RGB color channel is the max value. The three different formulas are:
		// if Red is max, then Hue = (G-B)/(max-min)
		// if Green is max, then Hue = 2.0 + (B-R)/(max-min)
		// if Blue is max, then Hue = 4.0 + (R-G)/(max-min)
		// the Hue value you get needs to be multiplied by 60 to convert it to degrees on the color circle
		// if Hue becomes negative you need to add 360 to, because a circle has 360 degrees.
		double hue = 0;
		if (Double.compare(max, min) == 0) {
			hue = 0;
		} else if (Double.compare(max, r) == 0) {
			hue = ((60 * (g - b) / (max - min)) + 360) % 360;
		} else if (Double.compare(max, g) == 0) {
			hue = (60 * (b - r) / (max - min)) + 120;
		} else if (Double.compare(max, b) == 0) {
			hue = (60 * (r - g) / (max - min)) + 240;
		}
		int hueInt = (int) Math.round(hue);
		sb.append(hueInt).append(Constants.COMMA);
		// ------------------
		// LIGHTNESS calculation
		// ------------------
		// now calculate the lightness value by adding the max and min values and
		// divide by 2.
		double lightness = (max + min) / 2;
		// stores as integer
		int lightnessInt = (int) Math.round(lightness * 100D);
		// ------------------
		// SATURATION calculation
		// ------------------
		double saturation = 0;
		// if the min and max value are the same, it means that there is no saturation.
		// but in our case min and max are not equal which means there is saturation.
		// if there is saturation we need to do check the level of the lightness in order to select the correct formula.
		// if lightness is smaller then 0.5, then saturation = (max-min)/(max+min)
		// if lightness is bigger then 0.5. then saturation = (max-min)/(2.0-max-min)
		if (Double.compare(max, min) == 0) {
			saturation = 0;
		} else if (lightness <= 0.5D) {
			saturation = (max - min) / (max + min);
		} else {
			saturation = (max - min) / (2 - max - min);
		}
		// stores as integer
		int saturationInt = (int) Math.round(saturation * 100);
		// returns the string HSL
		return sb.append(saturationInt).append(Constants.PERCENT).append(Constants.COMMA).append(lightnessInt).append(Constants.PERCENT).toString();
	}

	/**
	 * Any integer between 0 and 255 (inclusive) is valid.
	 * 
	 * @param channel channel to check, exception if the channel is nor within bounds
	 */
	private static void checkChannelWithinBounds(int channel) {
		if (channel < 0 || channel > 255) {
			throw new IllegalArgumentException("One argument is not within bounds (0-255)");
		}
	}

	/**
	 * Any double between 0.0d and 1.0d (inclusive) is valid.
	 * 
	 * @param alpha alpha value, exception if the channel is nor within bounds
	 */
	public static void checkAlphaWithinBounds(double alpha) {
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