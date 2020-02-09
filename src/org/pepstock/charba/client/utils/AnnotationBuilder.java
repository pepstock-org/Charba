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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.BaseNode;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.Img;

/**
 * Utility to have an image to apply to canvas of chart in order to add HTML custom information on chart.<br>
 * The utility is leveraging on <code>svg+mxl</code> and <code>foreignObject</code> elements.<br>
 * The HTML content MUST be XML well-formed, following the <a href="http://www.w3.org/1999/xhtml">xHTML specification</a>, when
 * passed as string.<br>
 * This is the SVG XML tree, used:<br>
 * <br>
 * 
 * <pre>
 * &lt;svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}"&gt;
 *    &lt;foreignObject width="100%" height="100%"&gt;
 *       &lt;div xmlns="http://www.w3.org/1999/xhtml"&gt;
 *       ... HTML XML well-formed content ....
 *       &lt;/div&gt;
 *    &lt;/foreignObject&gt;
 * &lt;/svg&gt;
 * </pre>
 * 
 * Drawing the image on canvas, you could get the java script error <code>NS_ERROR_NOT_AVAILABLE</code> which means that if even
 * the content is well-formed, it contains some invalid characters, not allowed into xHTML.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationBuilder {

	// cache of the image created
	// K = svg string argument (with width and height), V = image element
	private static final Map<String, Img> IMAGES = new HashMap<>();
	// K = element, V = inner HTML
	private static final Map<BaseHtmlElement, String> ELEMENTS = new HashMap<>();
	// template of data image URL to create the image from HTML content
	private static final String TEMPLATE_IMAGE_URL = "data:image/svg+xml;charset=utf-8,<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"{1}\" height=\"{2}\">"
			+ "<foreignObject width=\"100%\" height=\"100%\"><div xmlns=\"http://www.w3.org/1999/xhtml\">{0}</div></foreignObject></svg>";

	/**
	 * To avoid any instantiation
	 */
	private AnnotationBuilder() {
		// do nothing
	}

	/**
	 * Creates an image to apply to canvas with the HTML content (passed as GWT element) and width and height of the resulted
	 * image. Drawing the image on canvas, you could get the java script error <code>NS_ERROR_NOT_AVAILABLE</code> which means
	 * that if even the content is well-formed, it contains some invalid characters, not allowed into xHTML.
	 * 
	 * @param htmlXmlContent GWT element which represents the XML content to show
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return an image to apply to canvas
	 */
	public static Img build(BaseHtmlElement htmlXmlContent, double width, double height) {
		// checks if argument element is consistent
		if (htmlXmlContent == null) {
			// if not exception
			throw new IllegalArgumentException("Element argument is null");
		}
		// inner html reference
		final String innerHtml;
		// checks if cached
		if (ELEMENTS.containsKey(htmlXmlContent)) {
			// gets the key
			innerHtml = ELEMENTS.get(htmlXmlContent);
		} else {
			// creates a DIV wrapper, needed ONLY to get the inner HTML
			// this element don't need for further computation
			Div wrapper = DOMBuilder.get().createDivElement();
			// checks if the elements has got a parent
			// because adding to div element
			// the element will loose the parent and removed from UI
			if (htmlXmlContent.getParentNode() != null) {
				// clones node
				BaseNode clonedNode = htmlXmlContent.cloneNode(true);
				// checks if is an element
				if (clonedNode instanceof BaseElement) {
					// casts to element
					BaseElement clonedElement = (BaseElement) clonedNode;
					// wraps the XML content
					// adding the element
					wrapper.appendChild(clonedElement);
				} else {
					// if here, the clone node is not a element
					throw new IllegalArgumentException("Element passed as argument is not an element. Class: " + clonedNode.getClass().getName());
				}				// wraps the XML content
			} else {
				// wraps the XML content
				// adding the element
				wrapper.appendChild(htmlXmlContent);
			}
			// stores innerHTML
			innerHtml = wrapper.getInnerHTML();
			// removes all children to have a clean situation
			// for the element passed as argument
			wrapper.removeAllChildren();
			// caches the elements and html
			ELEMENTS.put(htmlXmlContent, innerHtml);
		}
		// creates key
		String key = getKey(innerHtml, width, height);
		// the result is a key of images created
		// if already built
		if (IMAGES.containsKey(key)) {
			// returns the existing one
			return IMAGES.get(key);
		}
		// builds the image and returns it
		return buildWithValidatedContent(key, innerHtml, width, height);
	}

	/**
	 * Creates an image to apply to canvas with the HTML content (MUST BE XML well-formed) and width and height of the resulted
	 * image. Drawing the image on canvas, you could get the java script error <code>NS_ERROR_NOT_AVAILABLE</code> which means
	 * that if even the content is well-formed, it contains some invalid characters, not allowed into xHTML.
	 * 
	 * @param htmlXmlContent HTML content to apply on canvas, must be XML well-formed
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return an image to apply to canvas
	 */
	public static Img build(String htmlXmlContent, double width, double height) {
		// checks if argument element is consistent
		if (htmlXmlContent == null) {
			// if not exception
			throw new IllegalArgumentException("Element argument is null");
		}
		// creates key
		String key = getKey(htmlXmlContent, width, height);
		// the result is a key of images created
		// if already built
		if (IMAGES.containsKey(key)) {
			// returns the existing one
			return IMAGES.get(key);
		}
		// if here, new annotation
		// returns it
		return buildWithValidatedContent(key, htmlXmlContent, width, height);
	}

	/**
	 * Creates a key to store the image in the cache. The key is created using the html passed as argument, appending width and
	 * height of image.
	 * 
	 * @param htmlXmlContent HTML content to apply on canvas
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return key to store the image as string
	 */
	private static String getKey(String htmlXmlContent, double width, double height) {
		StringBuilder builder = new StringBuilder(htmlXmlContent);
		return builder.append(width).append(height).toString();
	}

	/**
	 * Creates the images to be returned, managing the cache of them.
	 * 
	 * @param key unique key to store the element into the cache
	 * @param validatedhtmlXmlContent HTML content, already checked and well-formed
	 * @param width width of image to be created
	 * @param height height of image to be created
	 * @return an image to apply to canvas
	 */
	private static Img buildWithValidatedContent(String key, String validatedhtmlXmlContent, double width, double height) {
		// copies the template string
		String result = Utilities.applyTemplate(TEMPLATE_IMAGE_URL, validatedhtmlXmlContent, width, height);
		// transforms it into an element
		Img element = Utilities.toImageElement(result);
		// stores into cache
		IMAGES.put(key, element);
		// returns it
		return element;
	}
}
