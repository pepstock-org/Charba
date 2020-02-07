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
public final class ScriptElement extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "script";

	/**
	 * To avoid any instantiation
	 */
	ScriptElement() {
	}
	
	/**
	 * Returns the 'charset' property of the HTML SCRIPT element.
	 *
	 * @return the 'charset' property value
	 */
	 @JsProperty
	 public native String getCharset();

	/**
	 * Sets the 'charset' property of the HTML SCRIPT element.
	 *
	 * @param charset the 'charset' property to set 
	 */
	 @JsProperty
	 public native void setCharset(String charset);

	/**
	 * Returns the 'src' property of the HTML SCRIPT element.
	 *
	 * @return the 'src' property value
	 */
	 @JsProperty
	 public native String getSrc();

	/**
	 * Sets the 'src' property of the HTML SCRIPT element.
	 *
	 * @param src the 'src' property to set 
	 */
	 @JsProperty
	 public native void setSrc(String src);

	/**
	 * Returns the 'text' property of the HTML SCRIPT element.
	 *
	 * @return the 'text' property value
	 */
	 @JsProperty
	 public native String getText();

	/**
	 * Sets the 'text' property of the HTML SCRIPT element.
	 *
	 * @param text the 'text' property to set 
	 */
	 @JsProperty
	 public native void setText(String text);

	/**
	 * Returns the 'type' property of the HTML SCRIPT element.
	 *
	 * @return the 'type' property value
	 */
	 @JsProperty
	 public native String getType();

	/**
	 * Sets the 'type' property of the HTML SCRIPT element.
	 *
	 * @param type the 'type' property to set 
	 */
	 @JsProperty
	 public native void setType(String type);

}
