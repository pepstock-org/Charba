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
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class CanvasElement extends BaseHtmlElement {

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
	CanvasElement() {
	}

	/**
	 * Returns the 'height' property of the HTML CANVAS element.
	 *
	 * @return the 'height' property value
	 */
	@JsProperty
	public native int getHeight();

	/**
	 * Sets the 'height' property of the HTML CANVAS element.
	 *
	 * @param height the 'height' property value
	 */
	@JsProperty
	public native void setHeight(int height);

	/**
	 * Returns the 'width' property of the HTML CANVAS element.
	 *
	 * @return the 'width' property value
	 */
	@JsProperty
	public native int getWidth();

	/**
	 * Sets the 'width' property of the HTML CANVAS element.
	 *
	 * @param width the 'width' property value
	 */
	@JsProperty
	public native void setWidth(int width);

	/**
	 * FIXME test
	 * @return
	 */
	@JsMethod
	public native String toDataURL();

	@JsMethod
	private native Context2dItem getContext(String contextId);
	
	/**
	 * @return the context2D
	 */
	@JsOverlay
	public final Context2dItem getContext2d() {
		return getContext(CONTEXT_2D);
	}

	@JsOverlay
	public final boolean isSupported() {
		return getContext(CONTEXT_2D) != null;
	}
	
}
