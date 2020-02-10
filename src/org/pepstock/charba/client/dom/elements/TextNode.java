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
import org.pepstock.charba.client.dom.BaseNode;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents the textual content of an element or attribute.<br>
 * If an element has no markup within its content, it has a single child implementing text node that contains the element's
 * text. However, if the element contains markup, it is parsed into information items and text nodes that form its children.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_TEXT, namespace = JsPackage.GLOBAL)
public final class TextNode extends BaseNode {

	/**
	 * To avoid any instantiation
	 */
	private TextNode() {
		// do nothing
	}

	/**
	 * Returns the textual data contained in this object.
	 *
	 * @return the textual data contained in this object
	 */
	@JsProperty
	public native String getData();

	/**
	 * Sets the textual data contained in this object.
	 *
	 * @param data the textual data contained in this object
	 */
	@JsProperty
	public native void setData(String data);

	/**
	 * Returns the value representing the size of the string contained in data.
	 *
	 * @return the value representing the size of the string contained in data
	 */
	@JsProperty(name = "length")
	public native int length();

}
