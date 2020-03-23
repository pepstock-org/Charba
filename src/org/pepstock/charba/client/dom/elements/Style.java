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
 * Represents a &lt;style&gt; and doesn't allow to manipulate the CSS it contains.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_STYLE_ELEMENT, namespace = JsPackage.GLOBAL)
public final class Style extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "style";

	/**
	 * To avoid any instantiation
	 */
	private Style() {
		// do nothing
	}

	/**
	 * Returns the type of style being applied by this statement.
	 *
	 * @return the type of style being applied by this statement
	 */
	@JsProperty
	public native String getType();

	/**
	 * Sets the type of style being applied by this statement.
	 *
	 * @param type the type of style being applied by this statement
	 */
	@JsProperty
	public native void setType(String type);

}
