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
import org.pepstock.charba.client.enums.FontStyle;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Sets of methods used as common utilities.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Utilities {

	// string format of font style
	private static final String FONT_TEMPLATE = "{0} {1}px {2}";

	/**
	 * To avoid any instantiation
	 */
	private Utilities() {
		// do nothing
	}

	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * The format is [fontStyle] [fontSize] [fontFamily].
	 * 
	 * @param style font style to use
	 * @param fontSize font size
	 * @param fontFamily font family
	 * @return the font string to use in the canvas object.
	 */
	public static String toFont(FontStyle style, int fontSize, String fontFamily) {
		// gets template
		final String result = FONT_TEMPLATE;
		// formats
		return result.replaceAll("\\{0\\}", style.value()).replaceAll("\\{1\\}", String.valueOf(fontSize)).replaceAll("\\{2\\}", fontFamily);
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
	public static Cursor getCursorOfChart(AbstractChart<?, ?> chart) {
		// checks if argument is consistent
		if (chart != null) {
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
