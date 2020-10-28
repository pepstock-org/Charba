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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Maps a bar object (border width and radius) and its methods.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
abstract class AbstractBarBorderItem extends NativeObjectContainer {

	/**
	 * Creates the object using the argument to set the border item size to all corners of the rectangle.
	 * 
	 * @param size border item to apply to all corners of the rectangle.
	 */
	AbstractBarBorderItem(int size) {
		this(null);
		// stores the border item size
		set(size);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractBarBorderItem(NativeObject nativeObject) {
		super(nativeObject);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
	}

	/**
	 * Sets the border size to all dimensions.
	 * 
	 * @param size the border size to all dimensions.
	 */
	public abstract void set(int size);

	
	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}
}
