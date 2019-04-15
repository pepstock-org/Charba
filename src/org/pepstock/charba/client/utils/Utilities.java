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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.enums.FontStyle;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Sets of methods used as common utilities.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Utilities {

	/**
	 * Constant for EMPTY string
	 */
	public static final String EMPTY_STRING = "";
	/**
	 * Constant for EMPTY ARRAY string.
	 */
	public static final String[] EMPTY_ARRAY_STRING = new String[0];
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
	 * @param fontSize font size
	 * @param fontFamily font family
	 * @return the font string to use in the canvas object.
	 */
	public static String toFont(FontStyle style, int fontSize, String fontFamily) {
		// gets template
		final String result = FONT_TEMPLATE;
		// checks font style. If bold is always NORMAl
		final FontStyle fontStyle = FontStyle.BOLD.equals(style) ? FontStyle.NORMAL : style;
		// checks font weight. If not bold is always NORMAl
		final FontStyle fontWeight = FontStyle.BOLD.equals(style) ? FontStyle.BOLD : FontStyle.NORMAL;
		// by regex changes the value of format
		return REGEXP_FONT_FAMILY.replace(REGEXP_FONT_SIZE.replace(REGEXP_FONT_WEIGHT.replace(REGEXP_FONT_STYLE.replace(result, fontStyle.value()), fontWeight.value()), String.valueOf(fontSize)), fontFamily);
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
	 * @param isChart chart instance
	 * @return the cursor currently set into chart. Default is {@link Cursor#DEFAULT}.
	 */
	public static Cursor getCursorOfChart(IsChart isChart) {
		// checks if argument is consistent
		if (isChart instanceof AbstractChart<?>) {
			// cast to abstract chart to get element of GWT object
			AbstractChart<?> chart = (AbstractChart<?>) isChart;
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

}
