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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientColor;
import org.pepstock.charba.client.colors.GradientOrientation;
import org.pepstock.charba.client.colors.GradientType;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.enums.Repetition;
import org.pepstock.charba.client.dom.enums.Unit;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.BorderRadiusItem;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.Font;

/**
 * Sets of methods used as common utilities.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Utilities {

	/**
	 * Template to create CSS value of gradient, to use for <code>background-image</code> CSS property.<br>
	 * The template for a {@link GradientType#LINEAR} is: <br>
	 * <code>
	 * linear-gradient(direction, color-stop1, color-stop2, ...)
	 * </code> <br>
	 * The template for a {@link GradientType#RADIAL} is: <br>
	 * <code>
	 * radial-gradient(shape size at position, start-color, ..., last-color)
	 * </code> <br>
	 * See the following links how CSS gradient has been created:<br>
	 * <ul>
	 * <li>{@link GradientType#LINEAR}:
	 * <a href= "https://developer.mozilla.org/en-US/docs/Web/CSS/linear-gradient">https://developer.mozilla.org/en-US/docs/Web/CSS/linear-gradient</a><br>
	 * <li>{@link GradientType#RADIAL}:
	 * <a href= "https://developer.mozilla.org/en-US/docs/Web/CSS/radial-gradient">https://developer.mozilla.org/en-US/docs/Web/CSS/radial-gradient</a><br>
	 * </ul>
	 */
	private static final String GRADIENT_TEMPLATE = "{0}({1},{2})";

	/**
	 * Template to create CSS value of pattern, to use for <code>background</code> CSS shorthand property.<br>
	 * The template for a pattern is: <br>
	 * <br>
	 * <code>
	 * url(image-url) repetition
	 * </code> <br>
	 * See the following link how CSS pattern has been created:<br>
	 * <br>
	 * <a href= "https://developer.mozilla.org/en-US/docs/Web/CSS/background-image">https://developer.mozilla.org/en-US/docs/Web/CSS/background-image</a> <br>
	 */
	private static final String PATTERN_TEMPLATE = "url({0}) {1}";
	/**
	 * The CSS font template is the following:<br>
	 * <br>
	 * <code>
	 * font-style  font-variant  font-weight  font-size/line-height  font-family
	 * optional    optional      optional     mandatory optional     mandatory
	 * </code>
	 * <br>
	 * See <a href= "https://developer.mozilla.org/en-US/docs/Web/CSS/font">https://developer.mozilla.org/en-US/docs/Web/CSS/font</a><br>
	 * font-variant is not implemented, therefore, the template is:<br>
	 * <br>
	 * <code>
	 * font-style  "normal "    font-weight  font-size/line-height  font-family
	 * optional    font-variant optional     mandatory optional     mandatory
	 * </code>
	 * <br>
	 */
	// minimun font size in pixels
	private static final int MINIMUM_FONT_SIZE = 4;
	// string format of font CSS style
	private static final String FONT_TEMPLATE = "{0} normal {1} {2}px{3} {4}";
	// string format of font style
	private static final String REGEXP_FONT_STYLE_PATTERN = "{0}";
	// string format of font size
	private static final String REGEXP_FONT_WEIGHT_PATTERN = "{1}";
	// string format of font size
	private static final String REGEXP_FONT_SIZE_PATTERN = "{2}";
	// string format of font line height
	private static final String REGEXP_FONT_LINE_HEIGHT_PATTERN = "{3}";
	// string format of font family
	private static final String REGEXP_FONT_FAMILY_PATTERN = "{4}";
	// canvas element to draw
	private static final Canvas WORKING_CANVAS = DOMBuilder.get().isCanvasSupported() ? DOMBuilder.get().createCanvasElement() : null;
	// internal comparator to sort colors by own offset
	private static final Comparator<GradientColor> COMPARATOR = (GradientColor o1, GradientColor o2) -> Double.compare(o1.getOffset(), o2.getOffset());
	// internal comparator to sort colors by own offset, descendant
	// needed for CSS value for radial and out-in
	private static final Comparator<GradientColor> REVERSE_COMPARATOR = (GradientColor o1, GradientColor o2) -> Double.compare(o2.getOffset(), o1.getOffset());

	/**
	 * To avoid any instantiation
	 */
	private Utilities() {
		// do nothing
	}

	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * The format is [font-style] [font-variant] [font-weight] [font-size] [font-family].<br>
	 * See <a href="https://www.w3schools.com/tags/canvas_font.asp">here</a> CSS specification.
	 * 
	 * @param font font element to use
	 * @return the font string to use in the canvas object.
	 */
	public static String toCSSFontProperty(IsDefaultFont font) {
		// checks if argument is consistent
		if (font != null) {
			// checks the type of line height
			if (font.getLineHeightAsString() == null) {
				// line height as double
				return toCSSFontProperty(font.getStyle(), font.getWeight(), font.getSize(), font.getLineHeight(), font.getFamily());
			}
			// if here, line height as string
			return toCSSFontProperty(font.getStyle(), font.getWeight(), font.getSize(), font.getLineHeightAsString(), font.getFamily());
		}
		// if here the font is not consistent
		// and the returns the default font
		Font globalFont = Defaults.get().getGlobal().getFont();
		return toCSSFontProperty(globalFont);
	}
	
	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * The format is [font-style] [font-variant] [font-weight] [font-size] [font-family].<br>
	 * See <a href="https://www.w3schools.com/tags/canvas_font.asp">here</a> CSS specification.
	 * 
	 * @param style font style to use
	 * @param weight font weight
	 * @param size font size
	 * @param lineHeight line height
	 * @param family font family
	 * @return the font string to use in the canvas object.
	 */
	public static String toCSSFontProperty(FontStyle style, Weight weight, int size, double lineHeight, String family) {
		// gets string reference of line height
		String lineHeightAsString = null;
		// checks if line height is consistent
		if (Undefined.isNot(lineHeight)) {
			// then sets the double as string
			lineHeightAsString = applyPrecision(lineHeight, 1);
		}
		return toCSSFontProperty(style, weight, size, lineHeightAsString, family);
	}

	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * The format is [font-style] [font-variant] [font-weight] [font-size] [font-family].<br>
	 * See <a href="https://www.w3schools.com/tags/canvas_font.asp">here</a> CSS specification.
	 * 
	 * @param style font style to use
	 * @param weight font weight
	 * @param size font size
	 * @param lineHeight line height
	 * @param family font family
	 * @return the font string to use in the canvas object.
	 */
	public static String toCSSFontProperty(FontStyle style, Weight weight, int size, String lineHeight, String family) {
		// gets template
		final String result = FONT_TEMPLATE;
		// checks font size
		final int fontSize = Math.max(size, MINIMUM_FONT_SIZE);
		// checks if font style is consistent
		// setting style and weight CSS
		final FontStyle fontStyle = style == null ? Defaults.get().getGlobal().getFont().getStyle() : style;
		final Weight fontWeight = weight == null ? Defaults.get().getGlobal().getFont().getWeight() : weight;
		// checks if font family is consistent
		final String fontFamily = family == null ? Defaults.get().getGlobal().getFont().getFamily() : family;
		// gets line height
		final String fontLineHeight = lineHeight == null || lineHeight.trim().length() == 0 ? Constants.EMPTY_STRING : Constants.SLASH + lineHeight;
		// by regex changes the value of format
		return result.replace(REGEXP_FONT_STYLE_PATTERN, fontStyle.value())
				.replace(REGEXP_FONT_WEIGHT_PATTERN, fontWeight.value())
				.replace(REGEXP_FONT_SIZE_PATTERN, String.valueOf(fontSize))
				.replace(REGEXP_FONT_LINE_HEIGHT_PATTERN, String.valueOf(fontLineHeight))
				.replace(REGEXP_FONT_FAMILY_PATTERN, fontFamily);
	}

	/**
	 * Returns the CSS syntax to represent the pattern.<br>
	 * The dimension of canvas pattern image will be the dimension of pattern.
	 * 
	 * @return the CSS syntax to represent the pattern
	 * @param pattern object to export in the CSS
	 */
	public static String toCSSBackgroundProperty(Pattern pattern) {
		return toCSSBackgroundProperty(pattern, Integer.MIN_VALUE);
	}

	/**
	 * Returns the CSS syntax to represent the pattern.<br>
	 * The dimension of canvas pattern image is unique then the image of pattern is a square.
	 * 
	 * @return the CSS syntax to represent the pattern
	 * @param pattern object to export in the CSS
	 * @param squareSize size of image applied to canvasPattern to be a square
	 */
	public static String toCSSBackgroundProperty(Pattern pattern, int squareSize) {
		return toCSSBackgroundProperty(pattern, squareSize, squareSize);
	}

	/**
	 * Returns the CSS syntax to represent the pattern.
	 * 
	 * @return the CSS syntax to represent the pattern
	 * @param pattern object to export in the CSS
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 */
	public static String toCSSBackgroundProperty(Pattern pattern, int width, int height) {
		// Math.max(width, TilesFactoryDefaults.DEFAULT_SIZE)
		// checks if pattern argument is consistent
		if (pattern != null) {
			// gets image and canvas instance
			Img image = pattern.getImage();
			CanvasPatternItem canvasPattern = pattern.getCanvasPattern();
			// checks if pattern has been created by image
			if (image != null) {
				// using the template, returns the CSS value of pattern
				return applyTemplate(PATTERN_TEMPLATE, pattern.getImage().getSrc(), pattern.getRepetition().value());
			} else if (canvasPattern != null) {
				// sets a consistent width
				int widthToUse = Math.max(width, pattern.getWidth());
				// sets a consistent height
				int heightToUse = Math.max(height, pattern.getHeight());
				// using the template, returns the CSS value of pattern
				return applyTemplate(PATTERN_TEMPLATE, getImageURLFromCanvasPattern(canvasPattern, widthToUse, heightToUse), Repetition.REPEAT.value());
			}
		}
		// if here pattern is not consistent
		// returns empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns a URL CSS property for the data URL for the current content of the canvas element.
	 * 
	 * @param dataUrl the data URL for the current content of the canvas element
	 * @return a URL CSS property for the current content of the canvas element
	 */
	public static String toCSSBackgroundProperty(String dataUrl) {
		return toCSSBackgroundProperty(dataUrl, Repetition.REPEAT);
	}

	/**
	 * Returns a URL CSS property for the data URL for the current content of the canvas element.
	 * 
	 * @param dataUrl the data URL for the current content of the canvas element
	 * @param repetition repetition of image
	 * @return a URL CSS property for the current content of the canvas element
	 */
	public static String toCSSBackgroundProperty(String dataUrl, Repetition repetition) {
		// checks if data url is consistent
		if (dataUrl != null && dataUrl.trim().length() > 0) {
			// checks the value of repetition
			// if not consistent is ignored using the default
			String repetitionToApply = Key.isValid(repetition) ? repetition.value() : Constants.EMPTY_STRING;
			return applyTemplate(PATTERN_TEMPLATE, dataUrl, repetitionToApply);
		}
		// if here data url is not consistent
		// returns empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns a URL CSS property for the current content of an image element instance.
	 * 
	 * @param image image element instance to fet as CSS property
	 * @return a URL CSS property for the current content of the image
	 */
	public static String toCSSBackgroundProperty(Img image) {
		// checks if consistent
		if (image != null) {
			// transform in the CSS property
			return applyTemplate(PATTERN_TEMPLATE, image.getSrc(), Constants.EMPTY_STRING);
		}
		// if here, image not consistent
		// then returns an empty string
		return Constants.EMPTY_STRING;
	}
	
	/**
	 * Builds the border radius string (shorthand property of CSS font) to use in the canvas object or in DOM element.<br>
	 * The format is [top-left | top-right | bottom-right | bottom-left].
	 * 
	 * @param borderRadius border radius item to use
	 * @return the border radius string to use in the canvas object or in DOM element.
	 */
	public static String toCSSBorderRadiusProperty(BorderRadiusItem borderRadius) {
		// checks if argument is consistent
		if (borderRadius != null) {
			// creates string builder
			StringBuilder result = new StringBuilder();
			// adds top left
			result.append(Unit.PX.format(borderRadius.getTopLeft())).append(Constants.BLANK);
			// adds top right
			result.append(Unit.PX.format(borderRadius.getTopRight())).append(Constants.BLANK);
			// adds bottom right
			result.append(Unit.PX.format(borderRadius.getBottomRight())).append(Constants.BLANK);
			// adds bottom left
			result.append(Unit.PX.format(borderRadius.getBottomLeft()));
			return result.toString();
		}
		// if here, item not consistent
		// then returns an empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns a data URL for the current content of a canvas pattern instance.
	 * 
	 * @param pattern canvas pattern instance
	 * @param width width of canvas applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 * @return a data URL for the current content of the canvas pattern
	 */
	private static String getImageURLFromCanvasPattern(CanvasPatternItem pattern, int width, int height) {
		// checks if argument is consistent
		// checks if canvas is created
		// if not returns an empty string
		if (pattern == null || WORKING_CANVAS == null) {
			// returns empty string
			return Constants.EMPTY_STRING;
		}
		// sets dimensions of canvas, always a square
		WORKING_CANVAS.setWidth(width);
		WORKING_CANVAS.setHeight(height);
		// gets the context
		Context2dItem context = WORKING_CANVAS.getContext2d();
		// save context
		context.save();
		// clears the canvas for new design
		context.clearRect(0D, 0D, WORKING_CANVAS.getWidth(), WORKING_CANVAS.getHeight());
		// sets the background color
		context.setFillPattern(pattern);
		// begins a new path.
		context.beginPath();
		// strokes
		context.fillRect(0, 0, width, height);
		// closes the path
		context.closePath();
		// gets data from context
		String dataUrl = WORKING_CANVAS.toDataURL();
		// restore context
		context.restore();
		// returns
		return dataUrl;
	}

	/**
	 * Returns the CSS syntax to represent the gradient.<br>
	 * The dimension of canvas pattern image will be the dimension of pattern.
	 * 
	 * @param gradient object to export in the CSS
	 * @return the CSS syntax to represent the gradient
	 */
	public static String toCSSBackgroundProperty(Gradient gradient) {
		// checks if argument is consistent
		if (gradient != null) {
			// gets gradient type and orientation instance
			GradientType type = gradient.getType();
			GradientOrientation orientation = gradient.getOrientation();
			// got new list of colors to sort
			// without touching the current one
			List<GradientColor> sortableColors = new LinkedList<>();
			// copies all colors in the new list to sort
			sortableColors.addAll(gradient.getColors());
			// the radial gradient with orientation out in needs
			// to have the list of color
			if (GradientType.RADIAL.equals(gradient.getType()) && GradientOrientation.OUT_IN.equals(orientation)) {
				// sorts the color in order to have the list from greater to less
				Collections.sort(sortableColors, REVERSE_COMPARATOR);
			} else {
				// sorts the color in order to have the list from less to greater
				Collections.sort(sortableColors, COMPARATOR);
			}
			// builder to store all colors, comma separated
			StringBuilder builder = new StringBuilder();
			// scans all colors
			for (GradientColor color : sortableColors) {
				// if builder is not empty means that at least 1 color is
				// already put in the buildr
				// then add the comma
				if (builder.length() > 0) {
					builder.append(Constants.COMMA);
				}
				// adds color RGBA in the strign builder
				builder.append(color.getColor().toRGBA());
				// gets offset to set as percentage
				int percentage = (int) Math.round(color.getOffset() * 100);
				builder.append(Constants.BLANK).append(percentage).append(Constants.PERCENT);
			}
			// using the template, returns the CSS value of gradient
			return applyTemplate(GRADIENT_TEMPLATE, type.getCssStatement(), orientation.getCssStatement(), builder);
		}
		// if here gradient is not consistent
		// returns empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Creates a image element by a data URL which is a URI scheme that provides a way to in-line data in a document, and it's commonly used to embed images in HTML and CSS.
	 * 
	 * @param url a URI scheme that provides a way to in-line data
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static Img toImageElement(String url) {
		return toImageElement(url, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Creates a image element by a data URL which is a URI scheme that provides a way to in-line data in a document, and it's commonly used to embed images in HTML and CSS,
	 * forcing the size.
	 * 
	 * @param url a URI scheme that provides a way to in-line data
	 * @param width width of image
	 * @param height height of image
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static Img toImageElement(String url, int width, int height) {
		// checks if argument is consistent
		if (url != null) {
			Img image = DOMBuilder.get().createImageElement();
			// set source
			image.setSrc(url);
			// checks if size is consistent
			if (width > 0 && height > 0) {
				// forces size to image
				image.setWidth(width);
				image.setHeight(height);
			}
			// return image element
			return image;
		}
		// returns null
		return null;
	}

	/**
	 * Returns the cursor currently set in the chart.
	 * 
	 * @param chart chart instance
	 * @return the cursor currently set in the chart.
	 */
	public static CursorType getCursorOfChart(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isConsistent(chart)) {
			// if the value does not match any element of enumeration
			// return default
			return chart.getChartElement().getStyle().getCursorType();
		}
		// if here, not found
		return CursorType.DEFAULT;
	}

	/**
	 * Creates a string in order to change a template filling with the replacements.<br>
	 * The replacement must be set as following:<br>
	 * 
	 * <pre>
	 * {0} ... {1} ... {n}
	 * </pre>
	 * 
	 * where the value in the the brackets represents the index of the obecjt in the the array, passed as argument.
	 * 
	 * @param template template of message
	 * @param values values to apply in the template
	 * @return a string with the template filled by values
	 */
	public static String applyTemplate(String template, Object... values) {
		// creates result instance, at the beginning as the template
		String result = template;
		// checks if template and array of values are consistent
		if (template != null && template.length() > 0 && values != null && values.length > 0) {
			// scans all values to apply to template
			for (int i = 0; i < values.length; i++) {
				// creates a instance to apply in the template
				String replacement = Constants.NULL_STRING;
				// checks if values is consistent
				if (values[i] != null) {
					// gets the to string value
					replacement = values[i].toString();
				}
				// applies the value in the template
				result = result.replaceAll("\\{" + i + "\\}", replacement);
			}
		}
		// checks if result is null
		if (result == null) {
			return Constants.NULL_STRING;
		}
		// returns the result
		return result;
	}

	/**
	 * Converts a double in the a string setting the decimals places to maintain.
	 * 
	 * @param value value to convert. If is {@link Double#NaN}, return {@link Constants#NULL_STRING}
	 * @param precision decimals places to apply. If less than 0, uses 0
	 * @return a string which represents the double value
	 */
	public static String applyPrecision(double value, int precision) {
		// checks if the value is consistent
		if (Undefined.isNot(value)) {
			// sets the max value because the precision must be greater than 0
			int checkedPrecision = Math.max(precision, 0);
			// creates a big decimal with value
			BigDecimal decimalValue = BigDecimal.valueOf(value);
			// sets the scale to the precision and returns the string
			return decimalValue.setScale(checkedPrecision, RoundingMode.DOWN).toPlainString();
		}
		// if here the value is not consistent
		// then returns an empty string
		return Constants.NULL_STRING;
	}

}
