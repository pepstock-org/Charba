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

import org.pepstock.charba.client.commons.IsJSType;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents any HTML element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_ELEMENT, namespace = JsPackage.GLOBAL)
public class BaseHtmlElement extends BaseElement implements IsJSType {

	/**
	 * To avoid any instantiation
	 */
	protected BaseHtmlElement() {
		// do nothing
	}

	/**
	 * Returns the "rendered" text content of a node and its descendants.<br>
	 * As a getter, it approximates the text the user would get if they highlighted the contents of the element with the cursor and then copied it to the clipboard.
	 * 
	 * @return the "rendered" text content of a node and its descendants
	 */
	@JsProperty
	public final native String getInnerText();

	/**
	 * Sets the "rendered" text content of a node and its descendants.<br>
	 * As a getter, it approximates the text the user would get if they highlighted the contents of the element with the cursor and then copied it to the clipboard.
	 * 
	 * @param innerText the "rendered" text content of a node and its descendants
	 */
	@JsProperty
	public final native void setInnerText(String innerText);

	/**
	 * Returns a number containing the height of an element, relative to the layout.
	 *
	 * @return a number containing the height of an element, relative to the layout
	 */
	@JsProperty
	public final native int getOffsetHeight();

	/**
	 * Returns a number, the distance from this element's left border to its offsetParent's left border.
	 *
	 * @return a number, the distance from this element's left border to its offsetParent's left border
	 */
	@JsProperty
	public final native int getOffsetLeft();

	/**
	 * Returns a element that is the element from which all offset calculations are currently computed.
	 *
	 * @return a element that is the element from which all offset calculations are currently computed
	 */
	@JsProperty
	public final native BaseElement getOffsetParent();

	/**
	 * Returns a number, the distance from this element's top border to its offsetParent's top border.
	 *
	 * @return a number, the distance from this element's top border to its offsetParent's top border
	 */
	@JsProperty
	public final native int getOffsetTop();

	/**
	 * Returns a number containing the width of an element, relative to the layout.
	 *
	 * @return a number containing the width of an element, relative to the layout
	 */
	@JsProperty
	public final native int getOffsetWidth();

	/**
	 * Returns an object representing the declarations of an element's style attributes.
	 *
	 * @return an object representing the declarations of an element's style attributes
	 */
	@JsProperty
	public final native BaseStyleProperties getStyle();

	/**
	 * Returns a number representing the position of the element in the tabbing order.
	 *
	 * @return a number representing the position of the element in the tabbing order
	 */
	@JsProperty
	public final native int getTabIndex();

	/**
	 * Sets a number representing the position of the element in the tabbing order.
	 *
	 * @param tabIndex a number representing the position of the element in the tabbing order
	 */
	@JsProperty
	public final native void setTabIndex(int tabIndex);

	/**
	 * Returns an HTML element that is the parent of this element.<br>
	 * If the element has no parent, or if that parent is not an element, returns <code>null</code>.
	 *
	 * @return an element that is the parent of this node
	 */
	@JsOverlay
	public final BaseHtmlElement getParentHtmlElement() {
		// gets the parent element
		BaseElement parent = getParentElement();
		// checks if is html element
		if (parent instanceof BaseHtmlElement) {
			// casts and returns the element
			return (BaseHtmlElement) parent;
		}
		// if here, no html element or no parent
		return null;
	}

	/**
	 * Removes this node from its parent node if it is attached to one.
	 */
	@JsOverlay
	public final void removeFromParent() {
		// gets the parent element
		BaseElement parent = getParentElement();
		// if parent is consistent
		if (parent != null) {
			// removes this object from parent
			parent.removeChild(this);
		}
	}

	/**
	 * Returns an element's absolute top coordinate in the document's coordinate system.
	 * 
	 * @return an element's absolute top coordinate in the document's coordinate system
	 */
	@JsOverlay
	public final double getAbsoluteTop() {
		// initializes the value
		double top = 0D;
		// starts scanning from this element
		BaseHtmlElement currentElement = this;
		// checks if element is consistent and has got any offset parent
		while (currentElement != null && currentElement.getOffsetParent() != null) {
			// removes the top scrolling
			top -= currentElement.getScrollTop();
			// gets the parent of current element
			currentElement = currentElement.getParentHtmlElement();
		}
		// starts scanning from this element again
		currentElement = this;
		// checks if element is consistent
		while (currentElement != null) {
			// adds the top offset
			top += currentElement.getOffsetTop();
			// checks if the offset parent is a html element
			if (currentElement.getOffsetParent() instanceof BaseHtmlElement) {
				// gets the offset parent of current element
				currentElement = (BaseHtmlElement) currentElement.getOffsetParent();
			} else {
				// if here, the offset parent is not a html element
				// then closes the cycle
				currentElement = null;
			}
		}
		return top;
	}

	/**
	 * Returns an element's absolute left coordinate in the document's coordinate system.
	 * 
	 * @return an element's absolute left coordinate in the document's coordinate system
	 */
	@JsOverlay
	public final double getAbsoluteLeft() {
		// initializes the value
		double left = 0D;
		// starts scanning from this element
		BaseHtmlElement currentElement = this;
		// checks if element is consistent and has got any offset parent
		while (currentElement != null && currentElement.getOffsetParent() != null) {
			// removes the left scrolling
			left -= currentElement.getScrollLeft();
			// gets the parent of current element
			currentElement = currentElement.getParentHtmlElement();
		}
		// starts scanning from this element again
		currentElement = this;
		// checks if element is consistent
		while (currentElement != null) {
			// adds the left offset
			left += currentElement.getOffsetLeft();
			// checks if the offset parent is a html element
			if (currentElement.getOffsetParent() instanceof BaseHtmlElement) {
				// gets the offset parent of current element
				currentElement = (BaseHtmlElement) currentElement.getOffsetParent();
			} else {
				// if here, the offset parent is not a html element
				// then closes the cycle
				currentElement = null;
			}
		}
		return left;
	}

	/**
	 * Returns the scrolling element of the document, if there is, or the document element.
	 * 
	 * @return the scrolling element of the document, if there is, or the document element
	 */
	@JsOverlay
	public final BaseElement getScrollingElement() {
		// gets own document
		BaseDocument document = getOwnerDocument();
		// checks if argument is consistent
		if (document != null) {
			// checks if document has got the scrolling
			if (document.getScrollingElement() != null) {
				// returns the scrolling element
				return document.getScrollingElement();
			}
			// if not, returns the document element
			return document.getDocumentElement();
		}
		// if here the argument is not consistent
		// then returns null
		return null;
	}
}
