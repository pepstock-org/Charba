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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class BodyElement extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "body";

	/**
	 * To avoid any instantiation
	 */
	BodyElement() {
	}

	/**
	 * Returns the 'aLink' property of the body element.
	 *
	 * @return the 'aLink' property value
	 */
	 @JsProperty
	 public native String getALink();

	/**
	 * Returns the 'background' property of the body element.
	 *
	 * @return the 'background' property value
	 */
	 @JsProperty
	 public native String getBackground();

	/**
	 * Returns the 'bgColor' property of the body element.
	 *
	 * @return the 'bgColor' property value
	 */
	 @JsProperty
	 public native String getBgColor();

	/**
	 * Returns the 'link' property of the body element.
	 *
	 * @return the 'link' property value
	 */
	 @JsProperty
	 public native String getLink();

	/**
	 * Returns the 'text' property of the body element.
	 *
	 * @return the 'text' property value
	 */
	 @JsProperty
	 public native String getText();

	/**
	 * Returns the 'vLink' property of the body element.
	 *
	 * @return the 'vLink' property value
	 */
	 @JsProperty
	 public native String getVLink();


	
}
