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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Base class for all options noides, which will wraps a native object and manages the relations about parent and children elements.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
abstract class AbstractNode extends NativeObjectContainer {

	private final AbstractNode parent;

	private final Key childKey;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractNode(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(nativeObject);
		// stores arguments
		this.childKey = childKey;
		this.parent = parent;
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
	protected final AbstractNode getParentNode(){
		return parent;
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected final void checkAndAddToParent() {
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
