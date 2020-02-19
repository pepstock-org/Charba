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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.MutationObserver.MutationObserverCallback;
import org.pepstock.charba.client.dom.enums.MutationType;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents an individual DOM mutation. It is the object that is passed to {@link MutationObserverCallback}'s callback.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class MutationRecord {

	/**
	 * To avoid any instantiation
	 */
	private MutationRecord() {
		// do nothing
	}

	/**
	 * Returns the nodes added.<br>
	 * Will be an empty node list if no nodes were added.
	 *
	 * @return the nodes added
	 */
	@JsProperty(name = "addedNodes")
	private native NodeList<BaseNode> nativeGetAddedNodes();

	/**
	 * Returns the elements added.<br>
	 * Will be an empty element list if no elements were added.
	 *
	 * @return the elements added
	 */
	@JsOverlay
	public List<BaseHtmlElement> getAddedElements() {
		return loadElements(nativeGetAddedNodes());
	}

	/**
	 * Returns the next sibling of the added or removed nodes, or null.
	 *
	 * @return the next sibling of the added or removed nodes, or null
	 */
	@JsProperty
	public native BaseNode getNextSibling();

	/**
	 * Returns the previous sibling of the added or removed nodes, or null.
	 *
	 * @return the previous sibling of the added or removed nodes, or null
	 */
	@JsProperty
	public native BaseNode getPreviousSibling();

	/**
	 * Returns the nodes removed.<br>
	 * Will be an empty list if no nodes were removed.
	 *
	 * @return the nodes removed
	 */
	@JsProperty(name = "removedNodes")
	private native NodeList<BaseNode> nativeGetRemovedNodes();

	/**
	 * Returns the elements removed.<br>
	 * Will be an empty element list if no elements were removed.
	 *
	 * @return the elements removed
	 */
	@JsOverlay
	public List<BaseHtmlElement> getRemovedElements() {
		return loadElements(nativeGetRemovedNodes());
	}

	/**
	 * Returns the node the mutation affected, depending on the {@link MutationType}. For {@link MutationType#ATTRIBUTES}, it is the element whose attribute changed. For
	 * {@link MutationType#CHARACTER_DATA}, it is the character data node. For {@link MutationType#CHILD_LIST}, it is the node whose children changed.
	 *
	 * @return the node the mutation affected
	 */
	@JsProperty
	public native BaseNode getTarget();

	/**
	 * Returns {@link MutationType#ATTRIBUTES} if the mutation was an attribute mutation, {@link MutationType#CHARACTER_DATA} if it was a mutation to a CharacterData node, and
	 * {@link MutationType#CHILD_LIST} if it was a mutation to the tree of nodes.
	 *
	 * @return the type of mutation record
	 */
	@JsProperty(name = "type")
	private native String nativeGetType();

	/**
	 * Returns {@link MutationType#ATTRIBUTES} if the mutation was an attribute mutation, {@link MutationType#CHARACTER_DATA} if it was a mutation to a CharacterData node, and
	 * {@link MutationType#CHILD_LIST} if it was a mutation to the tree of nodes.
	 *
	 * @return the type of mutation record
	 */
	@JsOverlay
	public MutationType getType() {
		return Key.getKeyByValue(MutationType.class, nativeGetType());
	}

	/**
	 * Loads a list of HTML elements from a list of nodes, added or removed ones.
	 * 
	 * @param nodes a list of nodes, added or removed ones
	 * @return a list of HTML elements
	 */
	@JsOverlay
	private List<BaseHtmlElement> loadElements(NodeList<BaseNode> nodes) {
		// creates the list to return
		List<BaseHtmlElement> result = new LinkedList<>();
		// checks if the list of node is consistent
		if (nodes != null) {
			// scans all nodes
			for (int i = 0; i < nodes.length(); i++) {
				// gets the note item at index
				BaseNode node = nodes.item(i);
				// checks if is a html elment
				if (node instanceof BaseHtmlElement) {
					// if here, casts to html element
					BaseHtmlElement element = (BaseHtmlElement) node;
					// adds html element to the list
					result.add(element);
				}
			}
		}
		return result;
	}

}
