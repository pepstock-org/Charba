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
import org.pepstock.charba.client.dom.elements.Head;
import org.pepstock.charba.client.dom.elements.TextNode;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents any web page loaded in the browser and serves as an entry point in the web page's content, which is the DOM tree.<br>
 * It provides functionality globally to the document, like how to obtain the page's URL and create new elements in the document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_DOCUMENT, namespace = JsPackage.GLOBAL)
public class BaseDocument extends BaseNode {

	/**
	 * To avoid any instantiation
	 */
	BaseDocument() {
		// do nothing
	}

	/**
	 * Returns the number of children of this parent node which are elements.
	 *
	 * @return the number of children of this parent node which are elements
	 */
	@JsProperty
	public final native double getChildElementCount();

	/**
	 * Returns the element that is a direct child of the document.<br>
	 * This is normally the object representing the document's &lt;html&lt; element.
	 *
	 * @return the element that is a direct child of the document
	 */
	@JsProperty
	public final native BaseElement getDocumentElement();

	/**
	 * Returns the first node which is both a child of this parent node and is also an element, or <code>null</code> if there is none.
	 *
	 * @return the first node which is both a child of this parent node and is also an element
	 */
	@JsProperty
	public final native BaseElement getFirstElementChild();

	/**
	 * Returns the &lt;head&gt; element of the current document.
	 *
	 * @return the &lt;head&gt; element of the current document
	 */
	@JsProperty
	public final native Head getHead();

	/**
	 * Returns the last node which is both a child of this parent node and is an element, or <code>null</code> if there is none.
	 *
	 * @return the last node which is both a child of this parent node and is an element
	 */
	@JsProperty
	public final native BaseElement getLastElementChild();

	/**
	 * Returns a reference to the element that scrolls the document.
	 *
	 * @return a reference to the element that scrolls the document
	 */
	@JsProperty
	public final native BaseElement getScrollingElement();

	/**
	 * Creates the HTML element specified by tag name.
	 * 
	 * @param tagName tag name of the element to create
	 * @param <T> type of HTML element to create
	 * @return the HTML element specified by tag name
	 */
	@JsMethod
	public final native <T extends BaseHtmlElement> T createElement(String tagName);

	/**
	 * Creates a new text node which can be used to escape HTML characters.
	 * 
	 * @param data the string which is containing the data to be put in the text node
	 * @return a new text node which can be used to escape HTML characters
	 */
	@JsMethod
	public final native TextNode createTextNode(String data);

	/**
	 * Returns an element object representing the element whose id property matches the specified string.
	 * 
	 * @param id the id of the element to locate.<br>
	 *            The id is case-sensitive string which is unique within the document; only one element may have any given ID.
	 * @return an element object representing the element whose id property matches the specified string
	 */
	@JsMethod
	public final native BaseElement getElementById(String id);

	/**
	 * Returns an list of elements with the given tag name.<br>
	 * The complete document is searched, including the root node.
	 * 
	 * @param tagName tag name of the element to search
	 * @return an list of elements with the given tag name
	 */
	@JsMethod
	public final native NodeList<BaseElement> getElementsByTagName(String tagName);

}