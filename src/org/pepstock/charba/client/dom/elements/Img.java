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

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.IsCastable;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents an HTML &lt;img&gt; element, providing the properties and methods used to manipulate image elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_IMAGE_ELEMENT, namespace = JsPackage.GLOBAL)
public final class Img extends BaseHtmlElement implements IsCastable {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "img";

	/**
	 * To avoid any instantiation
	 */
	private Img() {
		// do nothing
	}

	/**
	 * Returns the value that reflects the height attribute, indicating the rendered height of the image in CSS pixels.
	 *
	 * @return the value that reflects the height attribute
	 */
	@JsProperty
	public native int getHeight();

	/**
	 * Sets the value that reflects the height attribute, indicating the rendered height of the image in CSS pixels.
	 *
	 * @param height the value that reflects the height attribute
	 */
	@JsProperty
	public native void setHeight(int height);

	/**
	 * Returns the full URL of the image including base URI.
	 *
	 * @return the full URL of the image including base URI
	 */
	@JsProperty
	public native String getSrc();

	/**
	 * Sets the full URL of the image including base URI.
	 *
	 * @param src the full URL of the image including base URI
	 */
	@JsProperty
	public native void setSrc(String src);

	/**
	 * Returns the value that reflects the width attribute, indicating the rendered width of the image in CSS pixels.
	 *
	 * @return the value that reflects the width attribute
	 */
	@JsProperty
	public native int getWidth();

	/**
	 * Sets the value that reflects the width attribute, indicating the rendered width of the image in CSS pixels.
	 *
	 * @param width the value that reflects the width attribute
	 */
	@JsProperty
	public native void setWidth(int width);

	/**
	 * Returns a boolean value that is true if the browser has finished fetching the image, whether successful or not.<br>
	 * That means this value is also true if the image has no src value indicating an image to load.
	 * 
	 * @return a boolean value that is true if the browser has finished fetching the image, whether successful or not
	 */
	@JsProperty
	public native boolean isComplete();

}