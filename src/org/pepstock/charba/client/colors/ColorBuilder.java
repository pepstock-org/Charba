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
 */
public final class ColorBuilder {
	
	// REGX for RGB
	private static final String RGB_PATTERN = "rgb\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)";
	// REGX for RGBA
	private static final String RGBA_PATTERN = "rgba\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d*\\.?\\d*)\\s*\\)";
	
	// char to identify if is a HEX color
	static final String HEX_STARTING_CHAR = "#";
	// chars to identify if is a RGB color
	static final String RGB_STARTING_CHARS = "rgb";
	// char to identify if is a RGBA color
	static final String RGBA_STARTING_CHARS = "rgba";
	
	/**
	 * To avoid any instantiation
	 */
	private ColorBuilder() {
		// nothing
	}
	
	/**
	 * Builds a list of colors starting from a list of strings which represent colors.
	 * @param colorsAsString list of strings which represent colors
	 * @return a list of colors
	 */
	public static List<IsColor> parse(List<String> colorsAsString){
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
	 * @param r red channel
	 * @param g green  channel
	 * @param b blue  channel
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
	 * Creates a color by red, greeen, blue and alpha
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
		// creates a new color
		return new Color(r, g, b, alpha);
	}
	
	/**
	 * Parses HEX value translating into a color. HEX format: <code>#rrggbb</code>.
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
}