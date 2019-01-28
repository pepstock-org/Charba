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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * FIXME javadoc
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class CanvasObjects<T extends CanvasObject> extends NativeObjectContainer{
	
	private int count = 0;

	public CanvasObjects() {
		super();
	}

	abstract NativeObjectContainerFactory<T> getFactory(); 
	
	/**
	 */
	public final void setObjects(Key key, ArrayObject objects) {
		if (objects != null) {
			setArrayValue(key, objects);
			count++;
		}
	}

	/**
	 */
	public final List<T> getObjects(Key key) {
		ArrayObject array = getArrayValue(key);
		// returns the configuration creating a key.
		return ArrayListHelper.unmodifiableList(array, getFactory());
	}
	
	public final boolean hasObjects(Key key) {
		return has(key);
	}

	public final void removeObjects(Key key) {
		// checks if there is
		if (has(key)) {
			// and then remove
			remove(key);
			count--;
		}
	}

	public final boolean isEmpty() {
		return count == 0;
	}
	
	public final List<Key> getKeys(){
		return keys();
	}

}
