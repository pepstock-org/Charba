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
package org.pepstock.charba.client.commons;

import java.util.List;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Array object which maps the java script object.<br>
 * A simple wrapper around a homogeneous native array of numeric (int) values.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.ARRAY, namespace = JsPackage.GLOBAL)
public final class ArrayInteger extends Array {

	/**
	 * This method creates new array instance with a variable number of <code>int</code> arguments.
	 * 
	 * @param items integer items to create new array
	 * @return new array instance of integers.
	 */
	private static native ArrayInteger of(int... items);

	/**
	 * To avoid any instantiation
	 */
	ArrayInteger() {
	}

	/**
	 * This method creates new array instance with a variable number of <code>int</code> arguments.
	 * 
	 * @param items integer items to create new array
	 * @return new array instance of integers or <code>null</code> if argument is <code>null</code> or length to 0.
	 */
	@JsOverlay
	public static ArrayInteger fromOrNull(int... items) {
		// checks if consistent
		if (ArrayUtil.isEmpty(items)) {
			// returns null
			return null;
		}
		// returns array
		return ArrayInteger.of(items);
	}

	/**
	 * This method creates new array instance with a variable number of <code>int</code> arguments.
	 * 
	 * @param items integer items to create new array
	 * @return new array instance of integers or an empty array if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayInteger fromOrEmpty(int... items) {
		// checks if consistent
		if (ArrayUtil.isEmpty(items)) {
			// returns null
			return new ArrayInteger();
		}
		// returns array
		return ArrayInteger.of(items);
	}

	/**
	 * Creates a java script array of integers starting from list of integers.
	 * 
	 * @param items list of integers to load in the new java script array.
	 * @return new array instance of integers or <code>null</code> if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static ArrayInteger fromOrNull(List<Integer> items) {
		// checks if list is null
		if (!ArrayListHelper.isConsistent(items)) {
			return null;
		}
		// checks if is already a list with array
		if (items instanceof ArrayIntegerList) {
			// casts to array list
			ArrayIntegerList list = (ArrayIntegerList) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayInteger result = new ArrayInteger();
		// scans all items of list
		for (Integer value : items) {
			// adds elements
			result.push(value.intValue());
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of integers starting from list of integers.
	 * 
	 * @param items list of integers to load in the new java script array.
	 * @return new array instance of integers or an empty array if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static ArrayInteger fromOrEmpty(List<Integer> items) {
		// checks if is already a list with array
		if (items instanceof ArrayIntegerList) {
			// casts to array list
			ArrayIntegerList list = (ArrayIntegerList) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayInteger result = new ArrayInteger();
		// checks if list is null
		if (!ArrayListHelper.isConsistent(items)) {
			return result;
		}
		// scans all items of list
		for (Integer value : items) {
			// adds elements
			result.push(value.intValue());
		}
		// returns the array
		return result;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this array, or -1 if this array does not contain the element.
	 * 
	 * @param value element to search for
	 * @return the index of the last occurrence of the specified element in this array, or -1 if this array does not contain the element
	 */
	native int lastIndexOf(int value);

	/**
	 * Returns the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element.
	 * 
	 * @param value element to search for
	 * @return the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element
	 */
	native int indexOf(int value);

	/**
	 * Returns a shallow copy of a portion of an array in the a new array object selected from begin to end (end not included).<br>
	 * The original array will not be modified.
	 * 
	 * @param start Zero-based index at which to begin extraction.<br>
	 *            A negative index can be used, indicating an offset from the end of the sequence.<br>
	 *            If begin is undefined, slice begins from index 0.<br>
	 *            If begin is greater than the length of the sequence, an empty array is returned.
	 * @param end Zero-based index before which to end extraction. <code>slice</code> extracts up to but not including end.<br>
	 *            A negative index can be used, indicating an offset from the end of the sequence.<br>
	 *            If end is omitted, slice extracts through the end of the sequence (array.length()). If end is greater than the length of the sequence, <code>slice</code> extracts
	 *            through to the end of the sequence (array.length()).
	 * @return A new array containing the extracted elements.
	 */
	native ArrayInteger slice(int start, int end);

	/**
	 * This method changes the contents of an array by removing existing elements and/or adding new elements.
	 * 
	 * @param start index at which to start changing the array (with origin 0).<br>
	 *            If greater than the length of the array, actual starting index will be set to the length of the array.<br>
	 *            If negative, will begin that many elements from the end of the array (with origin -1) and <br>
	 *            will be set to 0 if absolute value is greater than the length of the array.
	 * @return an array containing the deleted elements.<br>
	 *         If only one element is removed, an array of one element is returned.<br>
	 *         If no elements are removed, an empty array is returned.
	 */
	native ArrayInteger splice(int start);

	/**
	 * This method changes the contents of an array by removing existing elements and/or adding new elements.
	 * 
	 * @param start index at which to start changing the array (with origin 0).<br>
	 *            If greater than the length of the array, actual starting index will be set to the length of the array.<br>
	 *            If negative, will begin that many elements from the end of the array (with origin -1) and <br>
	 *            will be set to 0 if absolute value is greater than the length of the array.
	 * @param deleteCounts indicating the number of old array elements to remove.<br>
	 *            If deleteCount is omitted, or if its value is larger than array.length() - start (that is, if it is greater than the number of elements left in the array,
	 *            starting at start), then all of the elements from start through the end of the array will be deleted.<br>
	 *            If deleteCount is 0 or negative, no elements are removed.
	 * @return an array containing the deleted elements.<br>
	 *         If only one element is removed, an array of one element is returned.<br>
	 *         If no elements are removed, an empty array is returned.
	 */
	native ArrayInteger splice(int start, int deleteCounts);

	/**
	 * This method changes the contents of an array by removing existing elements and/or adding new elements.
	 * 
	 * @param start index at which to start changing the array (with origin 0).<br>
	 *            If greater than the length of the array, actual starting index will be set to the length of the array.<br>
	 *            If negative, will begin that many elements from the end of the array (with origin -1) and <br>
	 *            will be set to 0 if absolute value is greater than the length of the array.
	 * @param deleteCounts indicating the number of old array elements to remove.<br>
	 *            If deleteCount is omitted, or if its value is larger than array.length() - start (that is, if it is greater than the number of elements left in the array,
	 *            starting at start), then all of the elements from start through the end of the array will be deleted.<br>
	 *            If deleteCount is 0 or negative, no elements are removed.
	 * @param item the element to add to the array, beginning at the start index. If you don't specify any elements, will only remove elements from the array.
	 * @return an array containing the deleted elements.<br>
	 *         If only one element is removed, an array of one element is returned.<br>
	 *         If no elements are removed, an empty array is returned.
	 */
	native ArrayInteger splice(int start, int deleteCounts, int item);

	/**
	 * Removes all of the elements from this object. The object will be empty after this call returns.
	 */
	@JsOverlay
	void clear() {
		splice(0, length());
	}

	/**
	 * Removes the element at the specified position in this array. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was
	 * removed from the array.
	 * 
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	@JsOverlay
	int remove(int index) {
		return splice(index, 1).get(0);
	}

	/**
	 * Inserts the specified element at the specified position in this array. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds
	 * one to their indices).
	 * 
	 * @param index index at which the specified element is to be inserted
	 * @param item element to be inserted
	 */
	@JsOverlay
	void insertAt(int index, int item) {
		splice(index, 0, item);
	}

	/**
	 * Gets the value at a given index.
	 * 
	 * If no value exists at the given index, a type-conversion error will occur in Development Mode and unpredictable behavior may occur in Production Mode. If the numeric value
	 * returned is non-integral, it will cause a warning in Development Mode, and may affect the results of mathematical expressions.
	 *
	 * @param index the index to be retrieved
	 * @return the value at the given index
	 */
	@JsOverlay
	public int get(int index) {
		return slice(index, index + 1).pop();
	}

	/**
	 * Fills all the elements of an array from a start index to an end index with a passed value. The end index is not included.
	 * 
	 * @param item value to fill an array.
	 * @param start Start index, defaults to 0.
	 * @param end End index, defaults to array.length().
	 */
	native void fill(int item, int start, int end);

	/**
	 * Adds one element to the end of an array.
	 * 
	 * @param item The element to add to the end of the array.
	 */
	native void push(int item);

	/**
	 * Removes the last element from an array and returns that element. This method changes the length of the array.
	 * 
	 * @return The removed element from the array; <code>null</code> if the array is empty.
	 */
	native int pop();

	/**
	 * Sets the value value at a given index.
	 * 
	 * If the index is out of bounds, the value will still be set. The array's length will be updated to encompass the bounds implied by the added value.
	 * 
	 * @param index the index to be set
	 * @param item the value to be stored
	 */
	@JsOverlay
	void set(int index, int item) {
		fill(item, index, index + 1);
	}
}