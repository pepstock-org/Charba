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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;

/**
 * Utility to check alpha value.<br>
 * Internally is checking red, green, blue and alpha values and creates string representation of a color, to HEX, RGB, RGBA, HSL or HSLA strings.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorUtil {

	// char to identify if is a HEX color
	static final String HEX_STARTING_CHAR = "#";
	// chars to identify if is a RGB color
	static final String RGB_STARTING_CHARS = "rgb";
	// char to identify if is a RGBA color
	static final String RGBA_STARTING_CHARS = "rgba";
	// chars to identify if is a HSL color
	static final String HSL_STARTING_CHARS = "hsl";
	// chars to identify if is a HSLA color
	static final String HSLA_STARTING_CHARS = "hsla";
	// darker factor
	static final double FACTOR = 0.7D;
	// zero for padding
	static final String PADDING_ZERO = "0";
	// double zero for padding
	static final String PADDING_DOUBLE_ZERO = "00";

	/**
	 * To avoid any instantiation
	 */
	private ColorUtil() {
		// nothing
	}

	/**
	 * Returns RGB or RGBA string value which represents the color.
	 * 
	 * @param color color instance to use to get RGB string
	 * @param withAlpha if <code>true</code>, creates a RGBA color instead of RGB
	 * @return RGB or RGBA string value which represents the color
	 */
	static String toRGB(IsColor color, boolean withAlpha) {
		// checks if color is consistent
		IsColor.checkIfValid(color);
		// creates a builder
		StringBuilder sb = new StringBuilder();
		// checks if must be added alpha value
		if (withAlpha) {
			// adds RGBA
			sb.append(RGBA_STARTING_CHARS);
		} else {
			// adds RGB
			sb.append(RGB_STARTING_CHARS);
		}
		// appends all separators and red, green and blue values
		sb.append(Constants.OPEN_ROUND_BRACKET).append(color.getRed()).append(Constants.COMMA).append(color.getGreen()).append(Constants.COMMA).append(color.getBlue());
		// checks if must be added alpha value
		if (withAlpha) {
			// adds separator and alpha value
			sb.append(Constants.COMMA).append(color.getAlpha());
		}
		// closes bracket
		sb.append(Constants.CLOSE_ROUND_BRACKET);
		// returns RGB/RGBA value
		return sb.toString();
	}

	/**
	 * Returns HEX string value which represents the color.
	 * 
	 * @param color color instance to use
	 * @return HEX string value which represents the color.
	 */
	static String toHex(IsColor color) {
		// checks if color is consistent
		IsColor.checkIfValid(color);
		// returns HEX value
		return HEX_STARTING_CHAR + pad(Integer.toHexString(color.getRed())) + pad(Integer.toHexString(color.getGreen())) + pad(Integer.toHexString(color.getBlue()));
	}

	/**
	 * Returns HSL or HSLA string value which represents the color.
	 * 
	 * @param color color instance to use to get HSL string
	 * @param withAlpha if <code>true</code>, creates a HSLA color instead of HSL
	 * @return HSL or HSLA string value which represents the color
	 */
	static String toHSL(IsColor color, boolean withAlpha) {
		// checks if color is consistent
		IsColor.checkIfValid(color);
		// creates a builder
		StringBuilder sb = new StringBuilder();
		// checks if must be added alpha value
		if (withAlpha) {
			// adds HSLA
			sb.append(HSLA_STARTING_CHARS);
		} else {
			// adds HSL
			sb.append(HSL_STARTING_CHARS);
		}
		// appends all separators and red, green and blue values
		sb.append(Constants.OPEN_ROUND_BRACKET).append(createHSLAsString(color.getRed(), color.getGreen(), color.getBlue()));
		// checks if must be added alpha value
		if (withAlpha) {
			// adds separator and alpha value
			sb.append(Constants.COMMA).append(color.getAlpha());
		}
		// closes bracket
		sb.append(Constants.CLOSE_ROUND_BRACKET);
		// returns RGB/RGBA value
		return sb.toString();
	}

	/**
	 * Returns <code>true</code> if any integer is between 0 and 255 (inclusive).
	 * 
	 * @param channel channel to check
	 * @return <code>true</code> if any integer is between 0 and 255 (inclusive)
	 */
	static boolean isChannelWithinBounds(int channel) {
		return Checker.isBetween(channel, 0, 255);
	}

	/**
	 * Any integer between 0 and 255 (inclusive) is valid.
	 * 
	 * @param channel channel to check, exception if the channel is nor within bounds
	 */
	static void checkChannelWithinBounds(int channel) {
		Checker.checkIfBetween(channel, 0, 255, "Channel value");
	}

	/**
	 * Returns <code>true</code> if any double is between 0.0d and 1.0d (inclusive).
	 * 
	 * @param alpha alpha value, exception if the channel is nor within bounds
	 * @return <code>true</code> if any double is between 0.0d and 1.0d (inclusive)
	 */
	public static boolean isAlphaWithinBounds(double alpha) {
		return Checker.isBetween(alpha, 0D, 1D);
	}

	/**
	 * Any double between 0.0d and 1.0d (inclusive) is valid.
	 * 
	 * @param alpha alpha value, exception if the channel is nor within bounds
	 */
	public static void checkAlphaWithinBounds(double alpha) {
		Checker.checkIfBetween(alpha, 0D, 1D, "Alpha value");
	}

	/**
	 * Convert a RGB Color to it corresponding HSL values.<br>
	 * See explanation <a href="http://www.niwa.nu/2013/05/math-behind-colorspace-conversions-rgb-hsl/">Math behind color space conversions, RGB-HSL</a>.
	 * 
	 * @param red red value
	 * @param green green value
	 * @param blue blue value
	 * @return a string representation of HSL color, only <code>h,s%,l%</code>
	 */
	static String createHSLAsString(int red, int green, int blue) {
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
	 * Applies the padding to a string to 2 chars.
	 * 
	 * @param in string to be pad
	 * @return result string
	 */
	static String pad(String in) {
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
}