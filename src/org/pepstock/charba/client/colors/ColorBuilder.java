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
		final List<IsColor> colors = new LinkedList<IsColor>();
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
	private static IsColor buildByHexValue(String hexvalue) {
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
			return build(red, green, blue);
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
		// transient value to calculate lightness
		double q = 0D;
		// checks value of lightness
		if (transientLightness < 0.5D) {
			q = transientLightness * (1 + transientSaturation);
		} else {
			q = (transientLightness + transientSaturation) - (transientSaturation * transientLightness);
		}
		// transient value of factor
		double p = 2 * transientLightness - q;
		// calculate RED, GREEN and BLUE as Double
		double redDbl = Math.max(0, hueToRGB(p, q, transientHue + (1D / 3D)));
		double greenDbl = Math.max(0, hueToRGB(p, q, transientHue));
		double blueDbl = Math.max(0, hueToRGB(p, q, transientHue - (1D / 3D)));
		// transforms RED, GREEN and BLUE into Integer
		int red = (int) Math.round(Math.min(redDbl, 1) * 255F);
		int green = (int) Math.round(Math.min(greenDbl, 1) * 255);
		int blue = (int) Math.round(Math.min(blueDbl, 1) * 255);
		// checks if alpha is NaN
		// builds the RGB color without alpha
		// otherwise with alpha
		return Double.isNaN(alpha) ? build(red, green, blue) : build(red, green, blue, alpha);
	}

	/**
	 * Transforms Hue value into a color value for RGB
	 * 
	 * @param p lightness and saturation value
	 * @param q lightness and saturation value
	 * @param h hue value
	 * @return a color value for RGB
	 */
	private static double hueToRGB(double p, double q, double h) {
		// checks and fix hue
		if (h < 0) {
			h += 1;
		}
		// checks and fix hue
		if (h > 1) {
			h -= 1;
		}
		// calculate the hue factor
		if (6 * h < 1) {
			return p + ((q - p) * 6 * h);
		}
		// calculate the hue factor
		if (2 * h < 1) {
			return q;
		}
		// calculate the hue factor
		if (3 * h < 2) {
			return p + ((q - p) * 6 * ((2.0D / 3.0D) - h));
		}
		return p;
	}
}