/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.jsinterop.utils.JSON;

/**
 * Base class for all classes which are wrapping an native java script object.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 * @param <O> type of native object wraps by this class
 */
public abstract class NativeObjectContainer<O extends NativeObject> {
	
	// native object instance
	private final O nativeObject;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected NativeObjectContainer(O nativeObject) {
		this.nativeObject = nativeObject;
	}
	
	/**
	 * Returns the native object instance.
	 * @return the native object instance.
	 */
	protected final O getNativeObject() {
		return nativeObject;
	}
	
	/**
	 * Returns the string JSON representation of the object.
	 * @return the string JSON representation of the object.
	 */
	public final String toJSON() {
		return JSON.stringify(nativeObject, 3);
	}
}
