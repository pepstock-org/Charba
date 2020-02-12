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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base class from which all element objects (objects that represent elements) in a document inherit.<br>
 * It only has methods and properties common to all kinds of elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.DOM_ELEMENT, namespace = JsPackage.GLOBAL)
public abstract class BaseElement extends BaseNode {

	/**
	 * To avoid any instantiation
	 */
	BaseElement() {
		// do nothing
	}

	/**
	 * Returns the number of children of this element which are elements.
	 * 
	 * @return the number of children of this element which are elements
	 */
	@JsProperty
	public final native int getChildElementCount();

	/**
	 * Returns a number representing the inner height of the element.
	 *
	 * @return a number representing the inner height of the element
	 */
	@JsProperty
	public final native int getClientHeight();

	/**
	 * Returns a number representing the width of the left border of the element.
	 *
	 * @return a number representing the width of the left border of the element
	 */
	@JsProperty
	public final native int getClientLeft();

	/**
	 * Returns a number representing the width of the top border of the element.
	 *
	 * @return a number representing the width of the top border of the element
	 */
	@JsProperty
	public final native int getClientTop();

	/**
	 * Returns a number representing the inner width of the element.
	 *
	 * @return a number representing the inner width of the element
	 */
	@JsProperty
	public final native int getClientWidth();

	/**
	 * Returns the first node which is both a child of this element and is also an element, or <code>null</code> if there is
	 * none.
	 * 
	 * @return the first node which is both a child of this element and is also an element, or <code>null</code> if there is
	 *         none
	 */
	@JsProperty
	public final native BaseElement getFirstElementChild();

	/**
	 * Returns the unique id of the element.
	 *
	 * @return the unique id of the element
	 */
	@JsProperty
	public final native String getId();

	/**
	 * Sets the unique id of the element.
	 *
	 * @param id the unique id of the element
	 */
	@JsProperty
	public final native void setId(String id);

	/**
	 * Returns the markup of the element's content.
	 *
	 * @return the markup of the element's content
	 */
	@JsProperty
	public final native String getInnerHTML();

	/**
	 * Sets the markup of the element's content..
	 *
	 * @param innerHTML the markup of the element's content
	 */
	@JsProperty
	public final native void setInnerHTML(String innerHTML);

	/**
	 * Returns the last node which is both a child of this element and is an element, or <code>null</code> if there is none.
	 * 
	 * @return the last node which is both a child of this element and is an element, or <code>null</code> if there is none
	 */
	@JsProperty
	public final native BaseElement getLastElementChild();

	/**
	 * Returns the element immediately following the given one in the tree, or <code>null</code> if there's no sibling node.
	 *
	 * @return the element immediately following the given one in the tree, or <code>null</code> if there's no sibling node
	 */
	@JsProperty
	public final native BaseElement getNextElementSibling();

	/**
	 * Returns the element immediately preceding the given one in the tree, or <code>null</code> if there is no sibling element.
	 *
	 * @return the element immediately preceding the given one in the tree, or <code>null</code> if there is no sibling element
	 */
	@JsProperty
	public final native BaseElement getPreviousElementSibling();

	/**
	 * Returns a number representing the scroll view height of an element.
	 *
	 * @return a number representing the scroll view height of an element
	 */
	@JsProperty
	public final native int getScrollHeight();

	/**
	 * Returns a number representing the left scroll offset of the element.
	 *
	 * @return a number representing the left scroll offset of the element
	 */
	@JsProperty
	public final native double getScrollLeft();

	/**
	 * Returns a number representing number of pixels the top of the document is scrolled vertically.
	 *
	 * @return a number representing number of pixels the top of the document is scrolled vertically
	 */
	@JsProperty
	public final native double getScrollTop();

	/**
	 * Returns a number representing the scroll view width of the element.
	 *
	 * @return a number representing the scroll view width of the element
	 */
	@JsProperty
	public final native int getScrollWidth();

	/**
	 * Returns the name of the tag for the given element.
	 *
	 * @return the name of the tag for the given element
	 */
	@JsProperty
	public final native String getTagName();

	/**
	 * Returns a collection of all attribute nodes registered to the specified node.
	 * 
	 * @return a collection of all attribute nodes registered to the specified node
	 */
	@JsProperty
	public native NamedNodeMap<BaseAttribute> getAttributes();

	/**
	 * Returns a list containing all descendant elements, of a particular tag name, from the current element.
	 * 
	 * @param tagname the qualified name to look for. The special string "*" represents all elements.
	 * @return a list containing all descendant elements
	 */
	@JsMethod
	public final native NodeList<BaseElement> getElementsByTagName(String tagname);

	/**
	 * Removes the element from the children list of its parent.
	 */
	@JsMethod
	public final native void remove();

	/**
	 * Returns a boolean indicating whether the current element has any attributes or not.
	 * 
	 * @return <code>true</code> whether the current element has any attributes
	 */
	@JsMethod
	public final native boolean hasAttributes();

}
