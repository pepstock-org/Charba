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

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/**
 * Utility to create colors by red, green and blue values, HEX value and RGB and RGBA strings.<br>
 * It checks if the requested color is already dfined int HTML colors.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @see HtmlColor
 * @see GwtMaterialColor
 */
public final class ColorBuilder {

	// REGX for RGB
	private static final String RGB_PATTERN = "rgb\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)";
	// REGX for RGBA
	private static final String RGBA_PATTERN = "rgba\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d*\\.?\\d*)\\s*\\)";
	// REGX for HSL
	private static final String HSL_PATTERN = "hsl\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})%\\s*,\\s*(\\d{1,3})%\\s*\\)";
	// REGX for HSLA
	private static final String HSLA_PATTERN = "hsla\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})%\\s*,\\s*(\\d{1,3})%\\s*,\\s*(\\d*\\.?\\d*)\\s*\\)";

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

	/**
	 * To avoid any instantiation
	 */
	private ColorBuilder() {
		// nothing
	}

	/**
	 * Builds a list of colors starting from a list of strings which represent colors.
	 * 
	 * @param colorsAsString list of strings which represent colors
	 * @return a list of colors
	 */
	public static List<IsColor> parse(List<String> colorsAsString) {
		// creates result
		final List<IsColor> colors = new LinkedList<>();
		// scans all colors as strings
		for (String colorAsString : colorsAsString) {
			// parses and add colors
			colors.add(parse(colorAsString));
		}
		return colors;
	}

	/**
	 * Creates a color using red, green and blue channels.
	 * 
	 * @param r red channel
	 * @param g green channel
	 * @param b blue channel
	 * @return a color instance
	 */
	public static IsColor build(int r, int g, int b) {
		// checks if already exists the required color
		// into enum HTML color
		for (HtmlColor color : HtmlColor.values()) {
			// if equals returns the enum item
			if (color.getRed() == r && color.getGreen() == g && color.getBlue() == b) {
				return color;
			}
		}
		for (GwtMaterialColor color : GwtMaterialColor.values()) {
			// if equals returns the enum item
			if (color.getRed() == r && color.getGreen() == g && color.getBlue() == b) {
				return color;
			}
		}
		// if here, the required color doesn't exist
		// creates new color
		return new Color(r, g, b);
	}

	/**
	 * Returns a color parsing the string value passed as argument.<br>
	 * It's able to parse HEX, RGB, RGBA and named color representation.
	 * 
	 * @param value value to parse
	 * @return color instance
	 */
	public static IsColor parse(String value) {
		// checks if the value is consistent
		if (value != null) {
			// removes blanks to be able to check
			// if starting with a specific label
			String newValue = value.trim();
			// checks which type of color is passed
			if (newValue.startsWith(HEX_STARTING_CHAR)) {
				// is a HEX
				return buildByHexValue(newValue);
			} else if (value.startsWith(RGBA_STARTING_CHARS)) {
				// is a RGBA. It must be checked before RGB
				return buildByRGBAValue(newValue);
			} else if (value.startsWith(RGB_STARTING_CHARS)) {
				// is a RGB
				return buildByRGBValue(newValue);
			} else if (value.startsWith(HSLA_STARTING_CHARS)) {
				// is a HSLA. It must be checked before HSL
				return buildByHSLAValue(newValue);
			} else if (value.startsWith(HSL_STARTING_CHARS)) {
				// is a HSL
				return buildByHSLValue(newValue);
			} else {
				// search by color name
				for (HtmlColor color : HtmlColor.values()) {
					if (color.name().equalsIgnoreCase(newValue)) {
						return color;
					}
				}
			}
		}
		// if here, the string argument is not valid
		throw new IllegalArgumentException("Value is invalid");
	}

	/**
	 * Creates a color by red, green, blue and alpha
	 * 
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 * @param alpha alpha value
	 * @return color instance
	 */
	private static IsColor build(int r, int g, int b, double alpha) {
		// checks if already exists the required color
		// into enum HTML color with alpha
		for (HtmlColor color : HtmlColor.values()) {
			if (color.getRed() == r && color.getGreen() == g && color.getBlue() == b) {
				return color.alpha(alpha);
			}
		}
		for (GwtMaterialColor color : GwtMaterialColor.values()) {
			// if equals returns the enum item
			if (color.getRed() == r && color.getGreen() == g && color.getBlue() == b) {
				return color.alpha(alpha);
			}
		}
		// creates a new color
		return new Color(r, g, b, alpha);
	}

	/**
	 * Parses HEX value translating into a color. HEX format: <code>#rrggbb</code>.
	 * 
	 * @param hexvalue hex color.
	 * @return color instance
	 */
	static IsColor buildByHexValue(String hexvalue) {
		return buildByHexValue(hexvalue, true);
	}

	/**
	 * Parses HEX value translating into a color. HEX format: <code>#rrggbb</code>.
	 * 
	 * @param hexvalue hex color.
	 * @param searchOnEnum if <code>true</code> scans the html and GWT material color
	 * @return color instance
	 */
	static IsColor buildByHexValue(String hexvalue, boolean searchOnEnum) {
		// removes the pound
		String newHexvalue = hexvalue.substring(1);
		// checks if the HEX value is the short one
		if (newHexvalue.length() == 3) {
			// reads colors
			String redValue = newHexvalue.substring(0, 1) + newHexvalue.substring(0, 1);
			int red = Integer.parseInt(redValue, 16);
			String greenValue = newHexvalue.substring(1, 2) + newHexvalue.substring(1, 2);
			int green = Integer.parseInt(greenValue, 16);
			String blueValue = newHexvalue.substring(2) + newHexvalue.substring(2);
			int blue = Integer.parseInt(blueValue, 16);
			// builds color
			return build(red, green, blue);
		} else if (newHexvalue.length() == 6) {
			// checks if the HEX value
			// reads colors
			String redValue = newHexvalue.substring(0, 2);
			int red = Integer.parseInt(redValue, 16);
			String greenValue = newHexvalue.substring(2, 4);
			int green = Integer.parseInt(greenValue, 16);
			String blueValue = newHexvalue.substring(4);
			int blue = Integer.parseInt(blueValue, 16);
			// builds color
			return searchOnEnum ? build(red, green, blue) : new Color(red, green, blue, Color.DEFAULT_ALPHA);
		} else {
			// if here the hex value is not valid
			throw new IllegalArgumentException("Hex value is invalid. Must be length 3 or 6");
		}
	}

	/**
	 * Parses RGB value translating into a color. RGB format: <code>rgb(r, g, b)</code>
	 * 
	 * @param rgbvalue rgb value
	 * @return color instance
	 */
	private static IsColor buildByRGBValue(String rgbvalue) {
		// creates regular expression
		RegExp regExp = RegExp.compile(RGB_PATTERN);
		MatchResult matcher = regExp.exec(rgbvalue);
		boolean matchFound = matcher != null;
		// checks if matches
		if (matchFound && matcher.getGroupCount() == 4) {
			// init int values
			int red = 0;
			int green = 0;
			int blue = 0;
			// scans all token. Starts by 1
			for (int i = 1; i < matcher.getGroupCount(); i++) {
				String groupStr = matcher.getGroup(i);
				switch (i) {
				case 1:
					red = Integer.parseInt(groupStr);
					break;
				case 2:
					green = Integer.parseInt(groupStr);
					break;
				case 3:
					blue = Integer.parseInt(groupStr);
					break;
				default:
					break;
				}
			}
			// builds color
			return build(red, green, blue);
		} else {
			// if here the rgb value is not valid
			throw new IllegalArgumentException("RGB value is invalid");
		}
	}

	/**
	 * Parses RGBA value translating into a color. RGB format: <code>rgba(r, g, b, a)</code>
	 * 
	 * @param rgbavalue rgba value
	 * @return color instance
	 */
	private static IsColor buildByRGBAValue(String rgbavalue) {
		// creates regular expression
		RegExp regExp = RegExp.compile(RGBA_PATTERN);
		MatchResult matcher = regExp.exec(rgbavalue);
		boolean matchFound = matcher != null;
		// checks if matches
		if (matchFound && matcher.getGroupCount() == 5) {
			// init int values
			int red = 0;
			int green = 0;
			int blue = 0;
			double alpha = Color.DEFAULT_ALPHA;
			// scans all token. Starts by 1
			for (int i = 1; i < matcher.getGroupCount(); i++) {
				String groupStr = matcher.getGroup(i);
				switch (i) {
				case 1:
					red = Integer.parseInt(groupStr);
					break;
				case 2:
					green = Integer.parseInt(groupStr);
					break;
				case 3:
					blue = Integer.parseInt(groupStr);
					break;
				case 4:
					alpha = Double.parseDouble(groupStr);
					break;
				default:
					break;
				}
			}
			// builds color
			return build(red, green, blue, alpha);
		} else {
			// if here the rgba value is not valid
			throw new IllegalArgumentException("RGBA value is invalid");
		}
	}

	/**
	 * Parses HSL value translating into a color. HSL format: <code>hsl(h, s, l)</code>
	 * 
	 * @param hslvalue hsl value
	 * @return color instance
	 */
	private static IsColor buildByHSLValue(String hslvalue) {
		// creates regular expression
		RegExp regExp = RegExp.compile(HSL_PATTERN);
		MatchResult matcher = regExp.exec(hslvalue);
		boolean matchFound = matcher != null;
		// checks if matches
		if (matchFound && matcher.getGroupCount() == 4) {
			// init int values
			int hue = 0;
			int saturation = 0;
			int lightness = 0;
			// scans all token. Starts by 1
			for (int i = 1; i < matcher.getGroupCount(); i++) {
				String groupStr = matcher.getGroup(i);
				switch (i) {
				case 1:
					hue = Integer.parseInt(groupStr);
					break;
				case 2:
					saturation = Integer.parseInt(groupStr);
					break;
				case 3:
					lightness = Integer.parseInt(groupStr);
					break;
				default:
					break;
				}
			}
			// builds color
			return convertHSL2RGB(hue, saturation, lightness, Double.NaN);
		} else {
			// if here the rgb value is not valid
			throw new IllegalArgumentException("HSL value is invalid");
		}
	}

	/**
	 * Parses HSL value translating into a color. HSL format: <code>hsla(h, s, l, a)</code>
	 * 
	 * @param hslavalue hsla value
	 * @return color instance
	 */
	private static IsColor buildByHSLAValue(String hslavalue) {
		// creates regular expression
		RegExp regExp = RegExp.compile(HSLA_PATTERN);
		MatchResult matcher = regExp.exec(hslavalue);
		boolean matchFound = matcher != null;
		// checks if matches
		if (matchFound && matcher.getGroupCount() == 5) {
			// init int values
			int hue = 0;
			int saturation = 0;
			int lightness = 0;
			double alpha = Color.DEFAULT_ALPHA;
			// scans all token. Starts by 1
			for (int i = 1; i < matcher.getGroupCount(); i++) {
				String groupStr = matcher.getGroup(i);
				switch (i) {
				case 1:
					hue = Integer.parseInt(groupStr);
					break;
				case 2:
					saturation = Integer.parseInt(groupStr);
					break;
				case 3:
					lightness = Integer.parseInt(groupStr);
					break;
				case 4:
					alpha = Double.parseDouble(groupStr);
					break;
				default:
					break;
				}
			}
			// builds color
			return convertHSL2RGB(hue, saturation, lightness, alpha);
		} else {
			// if here the rgb value is not valid
			throw new IllegalArgumentException("HSL value is invalid");
		}
	}

	/**
	 * Convert HSL(A) values to a RGB(A) Color.
	 *
	 * @param hue hue is a degree on the color wheel from 0 to 360. 0 is red, 120 is green, 240 is blue.
	 * @param saturation saturation is a percentage value; 0% means a shade of gray and 100% is the full color.
	 * @param lightness lightness is a percentage; 0% is black, 100% is white.
	 * @param alpha the alpha
	 *
	 * @returns the RGB/RGBA Color object
	 * @see See explanation <a href="http://www.niwa.nu/2013/05/math-behind-colorspace-conversions-rgb-hsl/">Math behind
	 *      colorspace conversions, RGB-HSL</a>
	 */
	private static IsColor convertHSL2RGB(int hue, int saturation, int lightness, double alpha) {
		// checks if hue is in range
		if (hue < 0 || saturation > 360) {
			throw new IllegalArgumentException("Hue argument is not within bounds (0-360)");
		}
		// saturation if saturation is in range
		if (saturation < 0 || saturation > 100) {
			throw new IllegalArgumentException("Saturation argument is not within bounds (0-100)");
		}
		// lightness if saturation is in range
		if (lightness < 0 || lightness > 100) {
			throw new IllegalArgumentException("Lightness argument is not within bounds (0-100)");
		}
		// transforms all values into values between 0 and 1
		// with maximum value of range
		double transientHue = hue % 360D;
		transientHue /= 360D;
		double transientSaturation = saturation / 100D;
		double transientLightness = lightness / 100D;
		// we need to create some temporary variables
		// the variables are used to store temporary values which makes the formulas easier to read
		double temporary1;
		// There are two formulas to choose from in the first step.
		// if Lightness is smaller then 0.5 (50%) then temporary1 = Lightness x (1.0 + Saturation)
		// If Lightness is equal or larger then 0.5 (50%) then temporary1 = Lightness + Saturation - Lightness x Saturation
		if (transientLightness < 0.5D) {
			temporary1 = transientLightness * (1 + transientSaturation);
		} else {
			temporary1 = (transientLightness + transientSaturation) - (transientSaturation * transientLightness);
		}
		// we need one more temporary variable, temporary2
		double temporary2 = 2 * transientLightness - temporary1;
		// // And now we need another temporary variable for each color channel, temporary_R, temporary_G and temporary_B.
		// calculate RED, GREEN and BLUE as Double
		double temporaryRed = Math.max(0, hueToRGB(temporary2, temporary1, transientHue + (1D / 3D)));
		double temporaryGreen = Math.max(0, hueToRGB(temporary2, temporary1, transientHue));
		double temporaryBlue = Math.max(0, hueToRGB(temporary2, temporary1, transientHue - (1D / 3D)));
		// calculate RED, GREEN and BLUE as Integer
		int red = (int) Math.round(Math.min(temporaryRed, 1) * 255F);
		int green = (int) Math.round(Math.min(temporaryGreen, 1) * 255);
		int blue = (int) Math.round(Math.min(temporaryBlue, 1) * 255);
		// checks if alpha is NaN
		// builds the RGB color without alpha
		// otherwise with alpha
		return Double.isNaN(alpha) ? build(red, green, blue) : build(red, green, blue, alpha);
	}

	/**
	 * Transforms Hue value into a color value for RGB
	 * 
	 * @param temporary2 lightness and saturation temporary variable
	 * @param temporary1 lightness and saturation temporary variable
	 * @param temporaryChannelValue temporary channel value
	 * @return the channel color value to use for RGB color
	 */
	private static double hueToRGB(double temporary2, double temporary1, double temporaryChannelValue) {
		// all values need to be between 0 and 1.
		// if you get a negative value you need to add 1 to it.
		// if you get a value above 1 you need to subtract 1 from it.
		if (temporaryChannelValue < 0) {
			temporaryChannelValue += 1;
		}
		if (temporaryChannelValue > 1) {
			temporaryChannelValue -= 1;
		}
		// now we need to do up to 3 tests to select the correct formula for each color channel.
		// test 1 - If 6 x CHANNEL temporary color is smaller then 1, CHANNEL temporary color = temporary_2 + (temporary_1 -
		// temporary_2) x 6 x CHANNEL temporary color
		if (6 * temporaryChannelValue < 1) {
			return temporary2 + ((temporary1 - temporary2) * 6 * temporaryChannelValue);
		}
		// test 2 - If 2 x CHANNEL temporary color is smaller then 1, CHANNEL temporary color = temporary_1
		if (2 * temporaryChannelValue < 1) {
			return temporary1;
		}
		// test 3 - If 3 x CHANNEL temporary color is smaller then 2, CHANNEL temporary color = temporary_2 + (temporary_1 -
		// temporary_2) x (0.666 - CHANNEL temporary color) x 6
		if (3 * temporaryChannelValue < 2) {
			return temporary2 + ((temporary1 - temporary2) * 6 * ((2.0D / 3.0D) - temporaryChannelValue));
		}
		return temporary2;
	}
}