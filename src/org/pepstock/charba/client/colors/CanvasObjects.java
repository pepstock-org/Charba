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
package org.pepstock.charba.client.colors;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Common utility to manage inside the configuration items, the canvas objects (pattern or gradient), set to the specific
 * properties of the elements.<br>
 * It stores the canvas object information into a native object added to Charba configuration, on specific property names for
 * Charba.<br>
 * The canvas object are stored into native object by the "original" property names to use to configure CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of canvas object (pattern or gradient) to manage
 * @see Pattern
 * @see Gradient
 * 
 * @see PatternsContainer
 * @see GradientsContainer
 */
abstract class CanvasObjects<T extends CanvasObject> extends NativeObjectContainer {

	// counter to know how many objects are stores. Needs to provide
	// the is empty method result.
	private int count = 0;
	// flag to know if some objects are changed
	// needed to recalculate the canvas object at runtime
	private boolean changed = false;
	
	/**
	 * Creates the object by an empty native java script object.
	 */
	public CanvasObjects() {
		super();
	}

	/**
	 * Returns <code>true</code> if some canvas objects are changed, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if some canvas objects are changed, otherwise <code>false</code>.
	 */
	public final boolean isChanged() {
		return changed;
	}

	/**
	 *  Sets <code>true</code> if some canvas objects are changed, otherwise <code>false</code>.
	 *  
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Returns the factory needed to creates canvas objects from a native java script object.
	 * 
	 * @return the factory needed to creates canvas objects
	 */
	abstract NativeObjectContainerFactory<T> getFactory();

	/**
	 * Stores and array of canvas object into native java script object.
	 * 
	 * @param key property name to use to stored it.
	 * @param objects array of canvas object
	 */
	public final void setObjects(Key key, ArrayObject objects) {
		// checks if the value is consistent
		if (objects != null) {
			// stores the array
			setArrayValue(key, objects);
			// increments the counter
			count++;
		} else {
			// if null, remove the key and its value
			// if exists
			removeObjects(key);
		}
		// changes the flag 
		changed = true;
	}

	/**
	 * Returns a unmodifiable list of canvas objects.
	 * 
	 * @param key property name to use to get it.
	 * @return a unmodifiable list of canvas objects.
	 */
	public final List<T> getObjects(Key key) {
		ArrayObject array = getArrayValue(key);
		return ArrayListHelper.unmodifiableList(array, getFactory());
	}

	/**
	 * Returns <code>true</code> if there is a canvas object stored by passed key, otherwise <code>false</code>.
	 * 
	 * @param key property name to use to get it.
	 * @return <code>true</code> if there is a canvas object stored by passed key, otherwise <code>false</code>.
	 */
	public final boolean hasObjects(Key key) {
		return has(key);
	}

	/**
	 * Removes a stored canvas object by its property name, if exist.
	 * 
	 * @param key property name to use to remove it.
	 */
	public final void removeObjects(Key key) {
		// checks if there is
		if (has(key)) {
			// and then remove
			remove(key);
			// decrement amount of elements
			count--;
			// changes flag
			changed = true;
		}
	}

	/**
	 * Returns <code>true</code> if there is at least a stored canvas object, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is at least a stored canvas object, otherwise <code>false</code>.
	 */
	public final boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Returns the unmodifiable list of property names of the native java script object.
	 * 
	 * @return the unmodifiable list of property names
	 */
	public final List<Key> getKeys() {
		return Collections.unmodifiableList(keys());
	}

}
