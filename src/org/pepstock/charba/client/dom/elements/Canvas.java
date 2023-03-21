/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.dom.elements;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.IsCastable;
import org.pepstock.charba.client.enums.ImageMimeType;

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
public final class Canvas extends BaseHtmlElement implements IsCastable {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "canvas";
	// context id to retrieve
	@JsOverlay
	private static final String CONTEXT_2D = "2d";
	@JsOverlay
	public static final double DEFAULT_ENCODER_OPTIONS = 0.92;

	/**
	 * To avoid any instantiation
	 */
	private Canvas() {
		// do nothing
	}

	/**
	 * Returns a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 150 is used.
	 *
	 * @return a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native int getHeight();

	/**
	 * Sets a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 150 is used.
	 *
	 * @param height a positive integer reflecting the height HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native void setHeight(int height);

	/**
	 * Returns a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 300 is used.
	 *
	 * @return a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native int getWidth();

	/**
	 * Sets a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels.<br>
	 * When the attribute is not specified, or if it is set to an invalid value, like a negative, the default value of 300 is used.
	 *
	 * @param width a positive integer reflecting the width HTML attribute of the canvas element interpreted in CSS pixels
	 */
	@JsProperty
	public native void setWidth(int width);

	/**
	 * Returns a data-URL containing a representation of the image in the image format, passed as argument.<br>
	 * The returned image is in a resolution of 96dpi.
	 * 
	 * @param type indicating the image format
	 * @param encoderOptions between 0 and 1 indicating the image quality to use for image formats that use lossy compression.<br>
	 *            If this argument is anything else, the default value for image quality is used. The default value is 0.92.
	 * @return a data-URL containing a representation of the image in the image format, passed as argument
	 */
	@JsMethod(name = "toDataURL")
	private native String nativeToDataURL(String type, double encoderOptions);

	/**
	 * Returns a drawing context on the canvas, or <code>null</code> if the context ID is not supported.<br>
	 * A drawing context lets you draw on the canvas.
	 * 
	 * @param contextId context id. Only context <b>"2d"</b> is used.
	 * @return a drawing context on the canvas
	 */
	@JsMethod
	private native Object getContext(String contextId);

	/**
	 * Returns a drawing 2D context on the canvas.
	 * 
	 * @return a drawing 2D context on the canvas
	 */
	@JsOverlay
	public final Context2dItem getContext2d() {
		return JsHelper.get().cast(getContext(CONTEXT_2D));
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

	/**
	 * Returns a data-URL containing a representation of the image in the PNG format and the image quality value is 0.92.
	 * 
	 * @return a data-URL containing a representation of the image in the PNG format
	 */
	@JsOverlay
	public final String toDataURL() {
		return toDataURL(ImageMimeType.PNG);
	}

	/**
	 * Returns a data-URL containing a representation of the image in the PNG format.<br>
	 * The returned image is in a resolution of 96dpi.
	 * 
	 * @param encoderOptions between 0 and 1 indicating the image quality to use for image formats that use lossy compression.<br>
	 *            If this argument is anything else, the default value for image quality is used. The default value is 0.92.
	 * @return a data-URL containing a representation of the image in the PNG format
	 */
	@JsOverlay
	public final String toDataURL(double encoderOptions) {
		return toDataURL(ImageMimeType.PNG, encoderOptions);
	}

	/**
	 * Returns a data-URL containing a representation of the image format, passed as argument.<br>
	 * The returned image is in a resolution of 96dpi and the image quality value is 0.92.
	 * 
	 * @param type indicating the image format
	 * @return a data-URL containing a representation of the image format, passed as argument
	 */
	@JsOverlay
	public final String toDataURL(ImageMimeType type) {
		return toDataURL(type, DEFAULT_ENCODER_OPTIONS);
	}

	/**
	 * Returns a data-URL containing a representation of the image format, passed as argument.<br>
	 * The returned image is in a resolution of 96dpi.
	 * 
	 * @param type indicating the image format
	 * @param encoderOptions between 0 and 1 indicating the image quality to use for image formats that use lossy compression.<br>
	 *            If this argument is anything else, the default value for image quality is used. The default value is 0.92.
	 * @return a data-URL containing a representation of the image format, passed as argument
	 */
	@JsOverlay
	public final String toDataURL(ImageMimeType type, double encoderOptions) {
		// checks if the type is consistent
		ImageMimeType checkedImageMimeType = Key.isValid(type) ? type : ImageMimeType.PNG;
		// gets the data from canvas
		return nativeToDataURL(checkedImageMimeType.value(), Checker.betweenOrDefault(encoderOptions, 0, 1, DEFAULT_ENCODER_OPTIONS));
	}

}