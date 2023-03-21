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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Provides special properties and methods for manipulating the behavior and execution of &lt;script&gt; elements
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_SCRIPT_ELEMENT, namespace = JsPackage.GLOBAL)
public final class Script extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "script";

	/**
	 * To avoid any instantiation
	 */
	private Script() {
		// do nothing
	}

	/**
	 * Returns the character encoding of an external script.
	 *
	 * @return the character encoding of an external script
	 */
	@JsProperty
	public native String getCharset();

	/**
	 * Sets the character encoding of an external script.
	 *
	 * @param charset the character encoding of an external script
	 */
	@JsProperty
	public native void setCharset(String charset);

	/**
	 * Returns the URL of an external script.
	 *
	 * @return the URL of an external script
	 */
	@JsProperty
	public native String getSrc();

	/**
	 * Sets the URL of an external script.
	 *
	 * @param src the URL of an external script
	 */
	@JsProperty
	public native void setSrc(String src);

	/**
	 * Returns the MIME type of the script.
	 *
	 * @return the MIME type of the script
	 */
	@JsProperty
	public native String getType();

	/**
	 * Sets the MIME type of the script.
	 *
	 * @param type the MIME type of the script
	 */
	@JsProperty
	public native void setType(String type);

}