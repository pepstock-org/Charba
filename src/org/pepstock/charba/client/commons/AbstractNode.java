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
package org.pepstock.charba.client.commons;

/**
 * Base class for all options noides, which will wraps a native object and manages the relations about parent and children elements.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public abstract class AbstractNode extends NativeObjectContainer {

	private final AbstractNode parent;

	private final Key childKey;

	/**
	 * Creates the object with native object to map java script properties.<br>
	 * This is used for the root of tree composed by native objects.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractNode(NativeObject nativeObject) {
		this(null, null, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractNode(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(nativeObject);
		// stores arguments
		this.childKey = childKey;
		this.parent = parent;
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
	}

	/**
	 * Returns the property name to use to add this element to its parent.
	 * 
	 * @return the childKey or <code>null</code> if is a root element.
	 */
	protected final Key getChildKey() {
		return childKey;
	}

	/**
	 * Returns the parent node.
	 * 
	 * @return the parent node or <code>null</code> if is a root node.
	 */
	protected final AbstractNode getParentNode() {
		return parent;
	}

	/**
	 * Adds a proxy function to a model at the specific key.
	 * 
	 * @param model element where adding the function proxy
	 * @param key property name to use to add the function proxy
	 * @param proxy the function proxy instance to add
	 */
	protected final void setInternalCallbackToModel(AbstractNode model, Key key, CallbackProxy.Proxy proxy) {
		// checks if model is consistent
		if (model == null) {
			// if not exception
			throw new IllegalArgumentException("Options model arguments is null");
		}
		model.setValue(key, proxy);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Reset the value previously set to a callback at the specific key.
	 * 
	 * @param model element where resetting the callback
	 * @param key property name to use to reset the callback
	 * @param options the object to store overriding the callback
	 */
	protected final void resetInternalCallbackToModel(AbstractNode model, Key key, NativeObjectContainer options) {
		// checks if model is consistent
		if (model == null) {
			// if not exception
			throw new IllegalArgumentException("Options model arguments is null");
		}
		model.setValue(key, options);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	public final void checkAndAddToParent() {
		// checks if we are at root element
		// or if the parent hasn't got the key
		if (parent != null && !parent.has(childKey)) {
			// sets the java script of this element into parent
			parent.setValue(childKey, getNativeObject());
			// recursively call to parent of parent
			parent.checkAndAddToParent();
		}
	}

}
