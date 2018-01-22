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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Abstract item of default global and charts options and scales.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractItem extends JavaScriptObjectContainer {

	private final AbstractItem parent;

	private final Key childKey;

	/**
	 * Builds the item with own java script object read from CHART.JS defaults.<br>
	 * Used for root element of configuration.
	 * 
	 * @param javaScriptObject java script object read from CHART.JS defaults.
	 */
	protected AbstractItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// being the root element, no parent and child key.
		this.parent = null;
		this.childKey = null;
	}

	/**
	 * Builds the item with the parent item and child key.<br>
	 * Reads from parent java script object the object of this element.
	 * 
	 * @param parent parent item which contains the java script object of this element.
	 * @param childKey the key to read of this element.
	 */
	protected AbstractItem(AbstractItem parent, Key childKey) {
		// loads from parent of the java script object
		super(parent.load(childKey));
		// stores parent item and key.
		this.parent = parent;
		this.childKey = childKey;
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected void checkAndAddToParent() {
		// checks if we are at root element
		// or if the parent hasn't go thte key
		if (parent != null && !parent.has(childKey)) {
			// sets the java script of this element into parent
			parent.setValue(childKey, this);
			// recursively call to parent of parent
			parent.checkAndAddToParent();
		}
	}

	/**
	 * Loads the java script object of the parent, if exists.
	 * 
	 * @param key child key to load.
	 * @return java script object of this element or nul.
	 */
	private GenericJavaScriptObject load(Key key) {
		// checks if the java script object exists
		if (has(key)) {
			// gets and returns value
			return (GenericJavaScriptObject) getValue(key);
		} else {
			return null;
		}
	}

	/**
	 * Checks if the property is present into java script object.
	 * 
	 * @param key key to search.
	 * @return <code>true</code> if property exists otherwise <code>false</code>.
	 */
	public boolean hasProperty(Key key) {
		return has(key);
	}

}
