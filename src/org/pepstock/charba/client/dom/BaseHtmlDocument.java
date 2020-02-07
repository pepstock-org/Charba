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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.elements.BodyElement;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Extends the document to include methods and properties that are specific to HTML documents.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_DOCUMENT, namespace = JsPackage.GLOBAL)
public final class BaseHtmlDocument extends BaseDocument {

	/**
	 * To avoid any instantiation
	 */
	BaseHtmlDocument() {
		// do nothing
	}

	/**
	 * Returns the &lt;body&gt; or &lt;frameset&gt; node of the current document, or <code>null</code> if no such element
	 * exists.
	 *
	 * @return the &lt;body&gt; or &lt;frameset&gt; node of the current document, or <code>null</code> if no such element exists
	 */
	@JsProperty
	public native BodyElement getBody();

	/**
	 * Creates an event of the type specified.<br>
	 * The returned object should be first initialized and can then be dispatched.
	 * 
	 * @param type a string that represents the type of event to be created.
	 * @return an event of the type specified
	 * @see BaseEventTypes
	 */
	@JsMethod
	native BaseNativeEvent createEvent(String type);

}