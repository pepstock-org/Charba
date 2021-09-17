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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * Is a map to user to apply a different priority to sankey node, priority used to layout calculation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Priority extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	public Priority() {
		this(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	Priority(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if there is at least a key, stored in the map.
	 * 
	 * @return <code>true</code> if there is at least a key, stored in the map
	 */
	public boolean isConsistent() {
		return !empty();
	}

	/**
	 * Sets a value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @param value the value to assign to the node.
	 */
	public void set(String nodeKey, int value) {
		set(Key.create(nodeKey), value);
	}

	/**
	 * Sets a value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @param value the value to assign to the node.
	 */
	public void set(Key nodeKey, int value) {
		setValue(nodeKey, value);
	}

	/**
	 * Returns the stored value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @return the value to assign to the node.
	 */
	public int get(String nodeKey) {
		return get(Key.create(nodeKey));
	}

	/**
	 * Returns the stored value in the map, by its key of the node.
	 * 
	 * @param nodeKey the key of sankey node
	 * @return the value to assign to the node.
	 */
	public int get(Key nodeKey) {
		return getValue(nodeKey, Undefined.INTEGER);
	}

}