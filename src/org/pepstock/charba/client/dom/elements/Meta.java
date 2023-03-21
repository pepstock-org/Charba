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
 * Represents an HTML &lt;meta&gt; element which contains descriptive metadata about a document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_META_ELEMENT, namespace = JsPackage.GLOBAL)
public final class Meta extends BaseHtmlElement {

	/**
	 * The tag for this element.
	 */
	@JsOverlay
	public static final String TAG = "meta";

	/**
	 * To avoid any instantiation
	 */
	private Meta() {
		// do nothing
	}

	/**
	 * Returns the value of meta-data property..
	 *
	 * @return the value of meta-data property.
	 */
	@JsProperty
	public native String getContent();

	/**
	 * Sets the value of meta-data property..
	 *
	 * @param content the value of meta-data property.
	 */
	@JsProperty
	public native void setContent(String content);

	/**
	 * Returns the name of an HTTP response header to define for a document.
	 *
	 * @return the name of an HTTP response header to define for a document.
	 */
	@JsProperty
	public native String getHttpEquiv();

	/**
	 * Sets the name of an HTTP response header to define for a document.
	 *
	 * @param httpEquiv the name of an HTTP response header to define for a document.
	 */
	@JsProperty
	public native void setHttpEquiv(String httpEquiv);

	/**
	 * Returns the name of a meta-data property to define for a document.
	 *
	 * @return the name of a meta-data property to define for a document
	 */
	@JsProperty
	public native String getName();

	/**
	 * Sets the name of a meta-data property to define for a document.
	 *
	 * @param name the name of a meta-data property to define for a document
	 */
	@JsProperty
	public native void setName(String name);

}