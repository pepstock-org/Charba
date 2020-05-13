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
package org.pepstock.charba.client.commons;

/**
 * Extends the base class of an array container where the wrapped array is an array of doubles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class NativeArrayDoubleContainer extends NativeArrayContainer<ArrayDouble> {

	/**
	 * Creates the object with native double array instance to be wrapped.
	 * 
	 * @param nativeArray native double array instance to be wrapped.
	 */
	protected NativeArrayDoubleContainer(ArrayDouble nativeArray) {
		// checks if array is consistent otherwise provides an empty array
		super(nativeArray == null ? new ArrayDouble() : nativeArray);
	}

	/**
	 * Removes all of the elements from this array. The object will be empty after this call returns.
	 */
	protected final void clear() {
		getNativeArray().clear();
	}

	/**
	 * Adds an array of elements to the end of an array.
	 * 
	 * @param items an array of elements to add to the end of the array.
	 */
	protected final void push(double... items) {
		// checks if items are consistent
		if (items != null && items.length > 0) {
			// scans items to push
			for (double item : items) {
				// pushes item into array
				getNativeArray().push(item);
			}
		}
	}

}
