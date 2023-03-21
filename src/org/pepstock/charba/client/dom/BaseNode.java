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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.enums.NodeType;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * A object from which various types of DOM objects inherit, allowing those types to be treated similarly.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_NODE, namespace = JsPackage.GLOBAL)
public abstract class BaseNode extends BaseEventTarget {

	/**
	 * To avoid any instantiation
	 */
	protected BaseNode() {
		// do nothing
	}

	/**
	 * Returns a list containing all the children of this node.
	 *
	 * @return a list containing all the children of this node
	 */
	@JsProperty
	public final native NodeList<BaseNode> getChildNodes();

	/**
	 * Returns a node representing the first direct child node of the node, or <code>null</code> if the node has no child.
	 *
	 * @return a node representing the first direct child node of the node, or <code>null</code> if the node has no child
	 */
	@JsProperty
	public final native BaseNode getFirstChild();

	/**
	 * Returns a node representing the last direct child node of the node, or <code>null</code> if the node has no child.
	 *
	 * @return a node representing the last direct child node of the node, or <code>null</code> if the node has no child
	 */
	@JsProperty
	public final native BaseNode getLastChild();

	/**
	 * Returns a node representing the next node in the tree, or <code>null</code> if there isn't such node.
	 *
	 * @return a node representing the next node in the tree, or <code>null</code> if there isn't such node
	 */
	@JsProperty
	public final native BaseNode getNextSibling();

	/**
	 * Returns a string containing the name of the node.<br>
	 * The structure of the name will differ with the node type.
	 *
	 * @return a string containing the name of the node
	 */
	@JsProperty
	public final native String getNodeName();

	/**
	 * Returns an unsigned short representing the type of the node.
	 *
	 * @return an unsigned short representing the type of the node.
	 */
	@JsProperty(name = "nodeType")
	private final native int nativeGetNodeType();

	/**
	 * Returns the type of the node.
	 * 
	 * @return the type of the node
	 */
	@JsOverlay
	public final NodeType getNodeType() {
		return NodeType.getByType(nativeGetNodeType());
	}

	/**
	 * Returns the value of the current node.
	 *
	 * @return the value of the current node
	 */
	@JsProperty
	public final native String getNodeValue();

	/**
	 * Sets the value of the current node.
	 *
	 * @param nodeValue the value of the current node
	 */
	@JsProperty
	public final native void setNodeValue(String nodeValue);

	/**
	 * Returns the document that this node belongs to.<br>
	 * If the node is itself a document, returns <code>null</code>.
	 *
	 * @return the document that this node belongs to
	 */
	@JsProperty
	public final native BaseDocument getOwnerDocument();

	/**
	 * Returns a node that is the parent of this node.<br>
	 * If there is no such node, returns <code>null</code>.
	 *
	 * @return a node that is the parent of this node
	 */
	@JsProperty
	public final native BaseNode getParentNode();

	/**
	 * Returns an element that is the parent of this node.<br>
	 * If the node has no parent, or if that parent is not an element, returns <code>null</code>.
	 *
	 * @return an element that is the parent of this node
	 */
	@JsProperty
	public native BaseElement getParentElement();

	/**
	 * Returns a node representing the previous node in the tree, or <code>null</code> if there isn't such node.
	 *
	 * @return a node representing the previous node in the tree, or <code>null</code> if there isn't such node
	 */
	@JsProperty
	public final native BaseNode getPreviousSibling();

	/**
	 * Returns the textual content of an element and all its descendants.
	 *
	 * @return the textual content of an element and all its descendants
	 */
	@JsProperty
	public final native String getTextContent();

	/**
	 *
	 * Sets the textual content of an element and all its descendants.
	 *
	 * @param textContent the textual content of an element and all its descendants
	 */
	@JsProperty
	public final native void setTextContent(String textContent);

	/**
	 * Adds a node to the end of the list of children of a specified parent node.<br>
	 * If the given child is a reference to an existing node in the document, it moves it from its current position to the new position (there is no requirement to remove the node
	 * from its parent node before appending it to some other node).
	 * 
	 * @param newChild the node to append to the given parent node (commonly an element)
	 * @return the appended child
	 */
	@JsMethod
	public final native BaseNode appendChild(BaseNode newChild);

	/**
	 * Clone the node, and optionally, all of its contents.
	 * 
	 * @param deep If <code>true</code>, then node and its whole subtree including text.<br>
	 *            If <code>false</code>, only node will be cloned.<br>
	 *            It has no effect on empty elements
	 * @return the new node, cloned from this.<br>
	 *         The new clone has no parent and is not part of the document
	 */
	@JsMethod
	public final native BaseNode cloneNode(boolean deep);

	/**
	 * Returns a boolean indicating whether or not the element has any child nodes.
	 * 
	 * @return a boolean indicating whether or not the element has any child nodes
	 */
	@JsMethod
	public final native boolean hasChildNodes();

	/**
	 * Inserts a node before the reference node as a child of a specified parent node.
	 * 
	 * @param newChild the node to be inserted.
	 * @param refChild the node before which new node is inserted.<br>
	 *            If this is <code>null</code>, then new node is inserted at the end of child nodes.
	 * @return the added node
	 */
	@JsMethod
	public final native BaseNode insertBefore(BaseNode newChild, BaseNode refChild);

	/**
	 * Removes a child node from the current element, which must be a child of the current node.
	 * 
	 * @param oldChild is the child node to be removed from the DOM.
	 * @return a reference to the removed child node
	 */
	@JsMethod
	public final native BaseNode removeChild(BaseNode oldChild);

	/**
	 * Remove all children of the node.
	 */
	@JsOverlay
	public final void removeAllChildren() {
		// checks if there is any child
		// and cycles on that
		while (getLastChild() != null) {
			// removes the last child
			// till no last child are present
			removeChild(getLastChild());
		}
	}

}