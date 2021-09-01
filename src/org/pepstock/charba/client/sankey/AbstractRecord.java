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
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractRecord extends NativeObjectContainer {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractRecord(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	public final boolean isConsistent() {
		return !empty();
	}

	public final void set(String nodeKey, String priorityValue) {
		set(Key.create(nodeKey), priorityValue);
	}

	public final void set(Key nodeKey, String priorityValue) {
		setValue(nodeKey, priorityValue);
	}

	public final String get(String nodeKey) {
		return get(Key.create(nodeKey));
	}

	public final String get(Key nodeKey) {
		return getValue(nodeKey, Undefined.STRING);
	}

}