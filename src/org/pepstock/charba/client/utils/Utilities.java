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
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.enums.FontStyle;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.Repetition;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Image;

/**
 * Sets of methods used as common utilities.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Utilities {

	/**
	 * Template interface to create CSS value of gradient, to use for <code>background-image</code> CSS property.<br>
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
	 * <li>{@link GradientType#LINEAR}: <a href=
	 * "https://developer.mozilla.org/en-US/docs/Web/CSS/linear-gradient">https://developer.mozilla.org/en-US/docs/Web/CSS/linear-gradient</a><br>
	 * <li>{@link GradientType#RADIAL}: <a href=
	 * "https://developer.mozilla.org/en-US/docs/Web/CSS/radial-gradient">https://developer.mozilla.org/en-US/docs/Web/CSS/radial-gradient</a><br>
	 * </ul>
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	interface GradientCssTemplate extends SafeHtmlTemplates {

		/**
		 * Uses the declared template to create a CSS value for gradient.
		 * 
		 * @param type type of gradient.
		 * @param orientation the orientation of gradient
		 * @param colors list of colors (comma separated) as string.
		 * @return the CSS value of gradient
		 */
		@Template("{0}({1},{2})")
		SafeHtml css(String type, String orientation, StringBuilder colors);
	}

	/**
	 * Template interface to create CSS value of pattern, to use for <code>background</code> CSS shorthand property.<br>
	 * The template for a pattern is: <br>
	 * <code>
	 * url(image-url) repetition
	 * </code> <br>
	 * See the following link how CSS pattern has been created:<br>
	 * <br>
	 * <a href=
	 * "https://developer.mozilla.org/en-US/docs/Web/CSS/background-image">https://developer.mozilla.org/en-US/docs/Web/CSS/background-image</a>
	 * <br>
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	interface PatternCssTemplate extends SafeHtmlTemplates {

		/**
		 * Uses the declared template to create a CSS value for pattern.
		 * 
		 * @param imageSrc URI designating the source of pattern image.
		 * @param repetition how background images are repeated.<br>
		 *            A background image can be repeated along the horizontal and vertical axes, or not repeated at all.
		 * @return the CSS value of pattern
		 */
		@Template("url({0}) {1}")
		SafeHtml css(String imageSrc, String repetition);
	}

	/**
	 * Constant for CSS property for font.
	 */
	public static final String CSS_FONT_PROPERTY = "font";

	/**
	 * Constant for CSS property for {@link Pattern}.
	 */
	public static final String CSS_BACKGROUND_PROPERTY = "background";

	/**
	 * Constant for CSS property for {@link Pattern} for border.
	 */
	public static final String CSS_BORDER_PROPERTY = "border";

	/**
	 * Constant for CSS property for {@link Gradient}.
	 */
	public static final String CSS_BACKGROUND_IMAGE_PROPERTY = "backgroundImage";

	/**
	 * Constant for CSS property for {@link Gradient}.
	 */
	public static final String CSS_BACKGROUND_SIZE_PROPERTY = "backgroundSize";

	/**
	 * Constant for CSS property for {@link Gradient}, for border.
	 */
	public static final String CSS_BORDER_IMAGE_PROPERTY = "borderImage";

	/**
	 * Constant for CSS property for {@link IsColor}.
	 */
	public static final String CSS_BACKGROUND_COLOR_PROPERTY = "backgroundColor";

	/**
	 * Constant for CSS property for {@link IsColor}, for border.
	 */
	public static final String CSS_BORDER_COLOR_PROPERTY = "borderColor";

	// string format of font CSS style
	private static final String FONT_TEMPLATE = "{0} normal {1} {2}px {3}";
	// string format of font style
	private static final String REGEXP_FONT_STYLE_PATTERN = "\\{0\\}";
	// string format of font size
	private static final String REGEXP_FONT_WEIGHT_PATTERN = "\\{1\\}";
	// string format of font size
	private static final String REGEXP_FONT_SIZE_PATTERN = "\\{2\\}";
	// string format of font family
	private static final String REGEXP_FONT_FAMILY_PATTERN = "\\{3\\}";
	// regex instance for font style
	private static final RegExp REGEXP_FONT_STYLE = RegExp.compile(REGEXP_FONT_STYLE_PATTERN);
	// regex instance for font style
	private static final RegExp REGEXP_FONT_WEIGHT = RegExp.compile(REGEXP_FONT_WEIGHT_PATTERN);
	// regex instance for font style
	private static final RegExp REGEXP_FONT_SIZE = RegExp.compile(REGEXP_FONT_SIZE_PATTERN);
	// regex instance for font style
	private static final RegExp REGEXP_FONT_FAMILY = RegExp.compile(REGEXP_FONT_FAMILY_PATTERN);
	// canvas element to draw
	private static final CanvasElement WORKING_CANVAS = Canvas.isSupported() ? Document.get().createCanvasElement() : null;
	// instance of template
	private static final PatternCssTemplate PATTERN_TEMPLATE = GWT.create(PatternCssTemplate.class);
	// instance of template
	private static final GradientCssTemplate GRADIENT_TEMPLATE = GWT.create(GradientCssTemplate.class);
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
	 * @param style font style to use
	 * @param size font size
	 * @param family font family
	 * @return the font string to use in the canvas object.
	 */
	public static String toCSSFontProperty(FontStyle style, int size, String family) {
		// gets template
		final String result = FONT_TEMPLATE;
		final FontStyle fontStyle;
		final FontStyle fontWeight;
		// checks if font style is consistent
		// setting style and weight CSS
		if (style != null) {
			// checks font style. If bold is always NORMAl
			fontStyle = FontStyle.BOLD.equals(style) ? FontStyle.NORMAL : style;
			// checks font weight. If not bold is always NORMAl
			fontWeight = FontStyle.BOLD.equals(style) ? FontStyle.BOLD : FontStyle.NORMAL;
		} else {
			// not consistent sets normal
			fontStyle = FontStyle.NORMAL;
			fontWeight = FontStyle.NORMAL;
		}
		// checks if font family is consistent
		final String fontFamily = family == null ? Defaults.get().getGlobal().getFontFamily() : family;
		// by regex changes the value of format
		return REGEXP_FONT_FAMILY.replace(REGEXP_FONT_SIZE.replace(REGEXP_FONT_WEIGHT.replace(REGEXP_FONT_STYLE.replace(result, fontStyle.value()), fontWeight.value()), String.valueOf(size)), fontFamily);
	}

	/**
	 * Returns the CSS syntax to represent the pattern.<br>
	 * The dimension of canvas pattern image will be the dimension of pattern.
	 * 
	 * @return the CSS syntax to represent the pattern
	 * @param pattern object to export into CSS
	 */
	public static String toCSSBackgroundProperty(Pattern pattern) {
		return toCSSBackgroundProperty(pattern, Integer.MIN_VALUE);
	}

	/**
	 * Returns the CSS syntax to represent the pattern.<br>
	 * The dimension of canvas pattern image is unique then the image of pattern is a square.
	 * 
	 * @return the CSS syntax to represent the pattern
	 * @param pattern object to export into CSS
	 * @param squareSize size of image applied to canvasPattern to be a square
	 */
	public static String toCSSBackgroundProperty(Pattern pattern, int squareSize) {
		return toCSSBackgroundProperty(pattern, squareSize, squareSize);
	}

	/**
	 * Returns the CSS syntax to represent the pattern.
	 * 
	 * @return the CSS syntax to represent the pattern
	 * @param pattern object to export into CSS
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 */
	public static String toCSSBackgroundProperty(Pattern pattern, int width, int height) {
		// Math.max(width, TilesFactoryDefaults.DEFAULT_SIZE)
		// checks if pattern argument is consistent
		if (pattern != null) {
			// gets image and canvas instance
			ImageElement image = pattern.getImage();
			CanvasPattern canvasPattern = pattern.getCanvasPattern();
			// checks if pattern has been created by image
			if (image != null) {
				// using the template, returns the CSS value of pattern
				return PATTERN_TEMPLATE.css(pattern.getImage().getSrc(), pattern.getRepetition().getValue()).asString();
			} else if (canvasPattern != null) {
				// sets a consistent width
				int widthToUse = Math.max(width, pattern.getWidth());
				// sets a consistent height
				int heightToUse = Math.max(height, pattern.getHeight());
				// using the template, returns the CSS value of pattern
				return PATTERN_TEMPLATE.css(getImageURLFromFillStrokeStyle(canvasPattern, widthToUse, heightToUse), Context2d.Repetition.REPEAT.getValue()).asString();
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
			String repetitionToApply = repetition != null ? repetition.getValue() : Constants.EMPTY_STRING;
			return PATTERN_TEMPLATE.css(dataUrl, repetitionToApply).asString();
		}
		// if here data url is not consistent
		// returns empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns a URL CSS property for the current content of a fill or stroke canvas style instance.
	 * 
	 * @param style fill or stroke canvas style instance
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 * @return a URL CSS property for the current content of the canvas pattern
	 */
	public static String toCSSBackgroundProperty(FillStrokeStyle style, int width, int height) {
		return PATTERN_TEMPLATE.css(getImageURLFromFillStrokeStyle(style, width, height), Constants.EMPTY_STRING).asString();
	}

	/**
	 * Returns a URL CSS property for the current content of an image resource instance.
	 * 
	 * @param image image resource instance to get as CSS property
	 * @return a URL CSS property for the current content of the image
	 */
	public static String toCSSBackgroundProperty(ImageResource image) {
		return toCSSBackgroundProperty(toImageElement(image));
	}

	/**
	 * Returns a URL CSS property for the current content of an image instance.
	 * 
	 * @param image image instance to get as CSS property
	 * @return a URL CSS property for the current content of the image
	 */
	public static String toCSSBackgroundProperty(Image image) {
		return toCSSBackgroundProperty(toImageElement(image));
	}

	/**
	 * Returns a URL CSS property for the current content of an image element instance.
	 * 
	 * @param image image element instance to fet as CSS property
	 * @return a URL CSS property for the current content of the image
	 */
	public static String toCSSBackgroundProperty(ImageElement image) {
		// checks if consistent
		if (image != null) {
			// transform into CSS property
			return PATTERN_TEMPLATE.css(image.getSrc(), Constants.EMPTY_STRING).asString();
		}
		// if here, image not consistent
		// then returns an empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Returns a data URL for the current content of a fill or stroke canvas style instance.
	 * 
	 * @param style fill or stroke canvas style instance
	 * @param width width of canvas styapplied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 * @return a data URL for the current content of the canvas pattern
	 */
	private static String getImageURLFromFillStrokeStyle(FillStrokeStyle style, int width, int height) {
		// checks if argument is consistent
		// checks if canvas is created
		// if not returns an empty string
		if (style == null || WORKING_CANVAS == null) {
			// returns empty string
			return Constants.EMPTY_STRING;
		}
		// sets dimensions of canvas, always a square
		WORKING_CANVAS.setWidth(width);
		WORKING_CANVAS.setHeight(height);
		// gets the context
		Context2d context = WORKING_CANVAS.getContext2d();
		// clears the canvas for new design
		context.clearRect(0D, 0D, WORKING_CANVAS.getWidth(), WORKING_CANVAS.getHeight());
		// sets the background color
		context.setFillStyle(style);
		// begins a new path.
		context.beginPath();
		// strokes
		context.fillRect(0, 0, width, height);
		// closes the path
		context.closePath();
		// returns
		return WORKING_CANVAS.toDataUrl();
	}

	/**
	 * Returns the CSS syntax to represent the gradient.<br>
	 * The dimension of canvas pattern image will be the dimension of pattern.
	 * 
	 * @param gradient object to export into CSS
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
			// copies all colors into new list to sort
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
				// already put into buildr
				// then add the comma
				if (builder.length() > 0) {
					builder.append(Constants.COMMA);
				}
				// adds color RGBA into strign builder
				builder.append(color.getColor().toRGBA());
				// gets offset to set as percentage
				int percentage = (int) Math.round(color.getOffset() * 100);
				builder.append(Constants.BLANK).append(percentage).append(Constants.PERCENT);
			}
			// using the template, returns the CSS value of gradient
			return GRADIENT_TEMPLATE.css(type.getCssStatement(), orientation.getCssStatement(), builder).asString();
		}
		// if here gradient is not consistent
		// returns empty string
		return Constants.EMPTY_STRING;
	}

	/**
	 * Creates a image element by a data URL which is a URI scheme that provides a way to in-line data in a document, and it's
	 * commonly used to embed images in HTML and CSS.
	 * 
	 * @param url a URI scheme that provides a way to in-line data
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static ImageElement toImageElement(String url) {
		return toImageElement(url, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Creates a image element by a data URL which is a URI scheme that provides a way to in-line data in a document, and it's
	 * commonly used to embed images in HTML and CSS, forcing the size.
	 * 
	 * @param url a URI scheme that provides a way to in-line data
	 * @param width width of image
	 * @param height height of image
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static ImageElement toImageElement(String url, int width, int height) {
		return (url != null) ? toImageElement(new Image(url), width, height) : null;
	}

	/**
	 * Creates a image element by image resource which provides access to image data at runtime.
	 * 
	 * @param image image resource instance
	 * @return a image element instance or <code>null</code> if argument is not consistent
	 */
	public static ImageElement toImageElement(ImageResource image) {
		return toImageElement(image, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Creates a image element by image resource which provides access to image data at runtime, forcing the size.
	 * 
	 * @param image image resource instance
	 * @param width width of image
	 * @param height height of image
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static ImageElement toImageElement(ImageResource image, int width, int height) {
		return (image != null) ? toImageElement(new Image(image), width, height) : null;
	}

	/**
	 * Creates a image element by image widget that displays the image at a given URL.
	 * 
	 * @param image image widget instance
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static ImageElement toImageElement(Image image) {
		return toImageElement(image, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Creates a image element by image widget that displays the image at a given URL, forcing the size.
	 * 
	 * @param image image widget instance
	 * @param width width of image
	 * @param height height of image
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static ImageElement toImageElement(Image image, int width, int height) {
		// checks if argument is consistent
		if (image != null) {
			// checks if size is consistent
			if (width > 0 && height > 0) {
				// forces size to image
				image.setPixelSize(width, height);
			}
			// transform into image element
			return ImageElement.as(image.getElement());
		}
		// returns null
		return null;
	}

	/**
	 * Returns the cursor currently set into chart.
	 * 
	 * @param chart chart instance
	 * @return the cursor currently set into chart. Default is {@link Cursor#DEFAULT}.
	 */
	public static Cursor getCursorOfChart(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isConsistent(chart)) {
			// scans all cursors to check if any cursor is already set
			// needs to scan them because with valueOf there is an exception
			// if the value does not match any element of enumeration
			for (Cursor cursor : Cursor.values()) {
				if (cursor.name().equalsIgnoreCase(chart.getElement().getStyle().getCursor())) {
					// stores the current cursor
					return cursor;
				}
			}
		}
		// if here, not found
		return Cursor.DEFAULT;
	}

	/**
	 * Creates a string in order to change a template filling with the replacements.<br>
	 * The replacement must be set as following:<br>
	 * 
	 * <pre>
	 * {0} ... {1} ... {n}
	 * </pre>
	 * 
	 * where the value into the brackets represents the index of the obecjt into the array, passed as argument.
	 * 
	 * @param template template of message
	 * @param values values to apply into template
	 * @return a string with the template filled by values
	 */
	public static String applyTemplate(String template, Object... values) {
		// creates result instance, at the beginning as the template
		String result = template;
		// checks if template and array of values are consistent
		if (template != null && template.length() > 0 && values != null && values.length > 0) {
			// scans all values to apply to template
			for (int i = 0; i < values.length; i++) {
				// creates a instance to apply into template
				String replacement = Constants.NULL_STRING;
				// checks if values is consistent
				if (values[i] != null) {
					// gets the to string value
					replacement = values[i].toString();
				}
				// applies the value into template
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

}
