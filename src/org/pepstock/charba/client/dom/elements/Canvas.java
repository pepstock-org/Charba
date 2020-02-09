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
package org.pepstock.charba.client.dom.elements;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseHtmlElement;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents a DOM element that enables drawing graphics and animations with own the canvas scripting API.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_CANVAS_ELEMENT, namespace = JsPackage.GLOBAL)
public final class Canvas extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "canvas";
	@JsOverlay
	private static final String CONTEXT_2D = "2d";

	/**
	 * To avoid any instantiation
	 */
	private Canvas() {
		// do nothing
	}

	/**
	 * Returns a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 150 is
	 * used.
	 *
	 * @return a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native int getHeight();

	/**
	 * Sets a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 150 is
	 * used.
	 *
	 * @param height a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native void setHeight(int height);

	/**
	 * Returns a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 300 is
	 * used.
	 *
	 * @return a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native int getWidth();

	/**
	 * Sets a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 300 is
	 * used.
	 *
	 * @param width a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native void setWidth(int width);

	/**
	 * Returns a data-URL containing a representation of the image in the PNG format.<br>
	 * The returned image is in a resolution of 96dpi.
	 * 
	 * @return a data-URL containing a representation of the image in the PNG format
	 */
	@JsMethod
	public native String toDataURL();

	/**
	 * Returns a drawing context on the canvas, or <code>null</code> if the context ID is not supported.<br>
	 * A drawing context lets you draw on the canvas.
	 * 
	 * @param contextId context id. Only {@value Canvas#CONTEXT_2D} is used.
	 * @return a drawing context on the canvas
	 */
	@JsMethod
	private native Context2dItem getContext(String contextId);

	/**
	 * Returns a drawing 2D context on the canvas.
	 * 
	 * @return a drawing 2D context on the canvas
	 */
	@JsOverlay
	public final Context2dItem getContext2d() {
		return getContext(CONTEXT_2D);
	}

	/**
	 * Returns <code>true</code> if canvas is supported by browser, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if canvas is supported by browser, otherwise <code>false</code>
	 */
	@JsOverlay
	public final boolean isSupported() {
		// checks if context is not null
		return getContext(CONTEXT_2D) != null;
	}

}
