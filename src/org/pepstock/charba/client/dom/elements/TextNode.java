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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_TEXT, namespace = JsPackage.GLOBAL)
public final class TextNode extends BaseNode {

	/**
	 * To avoid any instantiation
	 */
	TextNode() {
	}

	/**
	 * Returns the 'data' property of the TEXT node.
	 *
	 * @return the 'data' property value
	 */
	@JsProperty
	public native String getData();

	/**
	 * Sets the 'data' property of the TEXT node.
	 *
	 * @param data the 'data' property to set
	 */
	@JsProperty
	public native void setData(String data);

	/**
	 * Returns the 'wholeText' property of the TEXT node.
	 *
	 * @return the 'wholeText' property value
	 */
	@JsProperty
	public native String getWholeText();

	/**
	 * Returns the 'length' property of the TEXT node.
	 *
	 * @return the 'length' property value
	 */
	@JsProperty
	public native int getLength();

	@JsMethod
	public native TextNode splitText(int offset);

	@JsMethod
	public native void appendData(String arg);

	@JsMethod
	public native void deleteData(int offset, int count);

	@JsMethod
	public native void insertData(int offset, String arg);

	@JsMethod
	public native void replaceData(int offset, int count, String arg);

	@JsMethod
	public native String substringData(int offset, int count);
}
