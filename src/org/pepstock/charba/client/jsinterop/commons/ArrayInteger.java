/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUint WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

import java.util.List;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * A simple wrapper around a homogeneous native array of numeric (integers) values.<br>
 * Extends GWint implementation adding additional methods, helpful to manage the array as a list.
 *  
 * @author Andrea "Stock" Stocchero
 * @see com.google.gwt.core.client.JsArrayInteger
 */
@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public final class ArrayInteger {

	public static native ArrayInteger of(int... items);

	@JsOverlay
	public static ArrayInteger of(List<Integer> values){
		// creates the list
		ArrayInteger result = new ArrayInteger();
		// checks if array is null
		if (values == null){
			return result;
		}
		for (Integer value : values) {
			// adds all elements
			result.push(value.intValue());
		}
		// returns the list
		return result;
	}


	@JsProperty(name = "length")
	public native int length();

	/**
	 * Returns the index of the last occurrence of the specified element in this object, or -1 if this object does not contain
	 * the element.
	 * 
	 * @param value element to search for
	 * @return the index of the last occurrence of the specified element in this object, or -1 if this object does not contain
	 *         the element
	 */
	native int lastIndexOf(int value);

	/**
	 * Returns the index of the first occurrence of the specified element in this object, or -1 if this object does not contain
	 * the element.
	 * 
	 * @param value element to search for
	 * @return the index of the first occurrence of the specified element in this object, or -1 if this object does not contain
	 *         the element
	 */
	native int indexOf(int value);

	native ArrayInteger slice(int start, int end);
	
	native ArrayInteger splice(int start);

	native ArrayInteger splice(int start, int deleteCounts);

	native ArrayInteger splice(int start, int deleteCounts, int item);

	/**
	 * Removes all of the elements from this object. The object will be empty after this call returns.
	 */
	@JsOverlay
	void clear() {
		splice(0, length());
	};

	/**
	 * Removes the element at the specified position in this object. Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the object.
	 * 
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	@JsOverlay
	int remove(int index) {
		return splice(index, 1).get(0);
	}

	/**
	 * Inserts the specified element at the specified position in this object. Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their indices).
	 * 
	 * @param index index at which the specified element is to be inserted
	 * @param value element to be inserted
	 */
	@JsOverlay
	void insertAt(int index, int item) {
		splice(index, 0, item);
	};

	/**
	 * Gets the value at a given index.
	 * 
	 * If no value exists at the given index, a type-conversion error will occur in Development Mode and unpredictable behavior
	 * may occur in Production Mode. If the numeric value returned is non-integral, it will cause a warning in Development Mode,
	 * and may affect the results of mathematical expressions.
	 *
	 * @param index the index to be retrieved
	 * @return the value at the given index
	 */
	@JsOverlay
	public int get(int index) {
		return slice(index, index+1).pop();
	}

	/**
	 * Pushes the given integer onto the end of the array.
	 */
	native void fill(int item, int start, int end);

	/**
	 * Pushes the given integer onto the end of the array.
	 */
	native void push(int item);
	
	/**
	 * Pushes the given integer onto the end of the array.
	 */
	native int pop();

	/**
	 * Sets the value value at a given index.
	 * 
	 * If the index is out of bounds, the value will still be set. The array's length will be updated to encompass the bounds
	 * implied by the added value.
	 * 
	 * @param index the index to be set
	 * @param value the value to be stored
	 */
	@JsOverlay
	void set(int index, int item){
		fill(item, index, index+1);
	}

	
}