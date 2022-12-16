/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.data;

import java.math.BigDecimal;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
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
	}

	/**
	 * Returns the list of keys managed by object.
	 * 
	 * @return the list of keys managed by object
	 */
	abstract List<Key> getKeys();

	/**
	 * Sets the border size to all dimensions.
	 * 
	 * @param size the border size to all dimensions.
	 */
	public abstract void set(int size);

	/**
	 * Returns <code>true</code> if all values of object are equals.
	 * 
	 * @return <code>true</code> if all values of object are equals
	 */
	public final boolean areValuesEquals() {
		// gets the keys managed object
		List<Key> keys = getKeys();
		// checks if keys are consistent
		if (ArrayListHelper.isConsistent(keys)) {
			// creates value reference to check
			int preValue = Integer.MIN_VALUE;
			// scans the keys
			for (Key key : keys) {
				// gets the key value
				int value = getValue(key, 0);
				// checks if is the first cycle
				if (preValue == Integer.MIN_VALUE) {
					// stores value
					preValue = value;
				} else if (preValue != value) {
					// if the stored value is not equals to the value
					// then the values are not all equals
					return false;
				}
			}
		}
		// if here, all values are equals
		// or the keys are not consistent
		return true;
	}

	/**
	 * Returns the average value of all values managed by object.
	 * 
	 * @return the average value of all values managed by object
	 */
	public final int average() {
		// creates the reference
		int result = 0;
		// gets the keys managed object
		List<Key> keys = getKeys();
		// checks if keys are consistent
		if (ArrayListHelper.isConsistent(keys)) {
			// gets the count of items
			final int count = keys.size();
			// creates the sum reference
			int sum = 0;
			// scans the keys
			for (Key key : keys) {
				// gets the key avlue and sum it
				sum += getValue(key, 0);
			}
			// creates a big decimal with average...
			BigDecimal average = new BigDecimal(sum / count);
			// ... then gets the integer value
			result = average.intValue();
		}
		return result;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public final NativeObject nativeObject() {
		return super.getNativeObject();
	}
}