/**
 * Licensed Materials - Property of Value Transformation Services
 * Restricted Materials of Value Transformation Services
 * C) COPYRIGHT V-TS ScPA. 2017, 2017   All Rights Reserved
 * Use, duplication or disclosure restricted by MSA Schedule Contract with Value Transformation Services.
 */
package org.pepstock.charba.client.gwt;

import org.pepstock.charba.client.dom.elements.Img;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import jsinterop.base.Js;

/**
 * Utility to transform image GWT resources into {@link Img} elements used by Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ImagesHelper {

	/**
	 * To avoid any instantiation
	 */
	private ImagesHelper() {
		// do nothing
	}

	/**
	 * Creates a image element by a data URL which is a URI scheme that provides a way to in-line data in a document, and it's commonly used to embed images in HTML and CSS.
	 * 
	 * @param url a URI scheme that provides a way to in-line data
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static Img toImg(String url) {
		return toImg(url, Integer.MIN_VALUE, Integer.MIN_VALUE);
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
	public static Img toImg(String url, int width, int height) {
		return (url != null) ? toImg(new Image(url), width, height) : null;
	}

	/**
	 * Creates a image element by image resource which provides access to image data at runtime.
	 * 
	 * @param image image resource instance
	 * @return a image element instance or <code>null</code> if argument is not consistent
	 */
	public static Img toImg(ImageResource image) {
		return toImg(image, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Creates a image element by image resource which provides access to image data at runtime, forcing the size.
	 * 
	 * @param image image resource instance
	 * @param width width of image
	 * @param height height of image
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static Img toImg(ImageResource image, int width, int height) {
		return (image != null) ? toImg(new Image(image), width, height) : null;
	}

	/**
	 * Creates a image element by image widget that displays the image at a given URL.
	 * 
	 * @param image image widget instance
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static Img toImg(Image image) {
		return toImg(image, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * Creates a image element by image widget that displays the image at a given URL, forcing the size.
	 * 
	 * @param image image widget instance
	 * @param width width of image
	 * @param height height of image
	 * @return a image element or <code>null</code> if argument is not consistent
	 */
	public static Img toImg(Image image, int width, int height) {
		// checks if argument is consistent
		if (image != null) {
			// checks if size is consistent
			if (width > 0 && height > 0) {
				// forces size to image
				image.setPixelSize(width, height);
			}
			// transform into image element
			ImageElement imageElement = ImageElement.as(image.getElement());
			// casts and returns the img
			return Js.cast(imageElement);
		}
		// if here, image argument is not consistent
		// then returns null
		return null;
	}

}
