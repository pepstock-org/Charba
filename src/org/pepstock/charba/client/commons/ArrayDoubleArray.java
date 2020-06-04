/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUdouble WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
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
 * A simple wrapper around a homogeneous native array of arrays of numeric (doubles) values.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.ARRAY, namespace = JsPackage.GLOBAL)
public final class ArrayDoubleArray extends Array {

	/**
	 * This method creates new array instance with a variable number of arrays of <code>double</code> arguments.
	 * 
	 * @param items double items to create new array
	 * @return new array instance of doubles.
	 */
	private static native ArrayDoubleArray of(ArrayDouble... items);

	/**
	 * To avoid any instantiation
	 */
	ArrayDoubleArray() {
	}

	/**
	 * Creates a java script array of arrays starting from a native array containers and the array will have ONE 1 element.
	 * 
	 * @param item single array to load into new java script array.
	 * @param <E> type of native array containers
	 * @return new array instance of ONE 1 element or <code>null</code> if argument is <code>null</code>
	 */
	@JsOverlay
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArray fromOrNull(E item) {
		// checks if array is null
		if (item == null) {
			return null;
		}
		// returns the array
		return ArrayDoubleArray.of(item.getNativeArray());
	}

	/**
	 * Creates a java script array of arrays starting from a native array containers and the array will have ONE 1 element.
	 * 
	 * @param item list of arrays to load into new java script array.
	 * @param <E> type of native array containers
	 * @return new array instance of ONE 1 element or an empty array if argument is <code>null</code>
	 */
	@JsOverlay
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArray fromOrEmpty(E item) {
		// creates the array
		ArrayDoubleArray result = new ArrayDoubleArray();
		// checks if array is null
		if (item == null) {
			return result;
		}
		// adds element
		result.push(item.getNativeArray());
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of arrays starting from an array of native array containers.
	 * 
	 * @param items list of arrays to load into new java script array.
	 * @param <E> type of native array containers
	 * @return new array instance of arrays or <code>null</code> if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArray fromOrNull(E[] items) {
		// checks if array is null
		if (items == null || items.length == 0) {
			return null;
		}
		// creates the array
		ArrayDoubleArray result = new ArrayDoubleArray();
		// scans elements
		for (E value : items) {
			// adds element
			result.push(value.getNativeArray());
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of arrays starting from an array of native array containers.
	 * 
	 * @param items list of arrays to load into new java script array.
	 * @param <E> type of native array containers
	 * @return new array instance of arrays or an empty array if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArray fromOrEmpty(E[] items) {
		// creates the array
		ArrayDoubleArray result = new ArrayDoubleArray();
		// checks if array is null
		if (items == null || items.length == 0) {
			return result;
		}
		// scans elements
		for (E value : items) {
			// adds element
			result.push(value.getNativeArray());
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of arrays starting from a list of native array containers.
	 * 
	 * @param items list of arrays to load into new java script array.
	 * @param <E> type of native array containers
	 * @return new array instance of arrays or <code>null</code> if the argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArray fromOrNull(List<E> items) {
		// checks if array is null
		if (items == null || items.isEmpty()) {
			return null;
		}
		// checks if is already a list with array
		if (items instanceof ArrayDoubleArrayList<?>) {
			// casts to array list
			ArrayDoubleArrayList<?> list = (ArrayDoubleArrayList<?>) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayDoubleArray result = new ArrayDoubleArray();
		// scans elements
		for (E value : items) {
			// adds element
			result.push(value.getNativeArray());
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of arrays starting from a list of native array containers.
	 * 
	 * @param items list of arrays to load into new java script array.
	 * @param <E> type of native array containers
	 * @return new array instance of arrays or an empty array if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArray fromOrEmpty(List<E> items) {
		// checks if is already a list with array
		if (items instanceof ArrayDoubleArrayList<?>) {
			// casts to array list
			ArrayDoubleArrayList<?> list = (ArrayDoubleArrayList<?>) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayDoubleArray result = new ArrayDoubleArray();
		// checks if array is null
		if (items == null || items.isEmpty()) {
			return result;
		}
		// scans elements
		for (E value : items) {
			// adds element
			result.push(value.getNativeArray());
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
	native int lastIndexOf(ArrayDouble value);

	/**
	 * Returns the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element.
	 * 
	 * @param value element to search for
	 * @return the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element
	 */
	native int indexOf(ArrayDouble value);

	/**
	 * Returns a shallow copy of a portion of an array into a new array object selected from begin to end (end not included).<br>
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
	native ArrayDoubleArray slice(int start, int end);

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
	native ArrayDoubleArray splice(int start);

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
	native ArrayDoubleArray splice(int start, int deleteCounts);

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
	native ArrayDoubleArray splice(int start, int deleteCounts, ArrayDouble item);

	/**
	 * Removes all of the elements from this array. The object will be empty after this call returns.
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
	ArrayDouble remove(int index) {
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
	void insertAt(int index, ArrayDouble item) {
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
	public ArrayDouble get(int index) {
		return slice(index, index + 1).pop();
	}

	/**
	 * Fills all the elements of an array from a start index to an end index with a passed value. The end index is not included.
	 * 
	 * @param item value to fill an array.
	 * @param start Start index, defaults to 0.
	 * @param end End index, defaults to array.length().
	 */
	native void fill(ArrayDouble item, int start, int end);

	/**
	 * Adds one element to the end of an array.
	 * 
	 * @param item The element to add to the end of the array.
	 */
	native void push(ArrayDouble item);

	/**
	 * Removes the last element from an array and returns that element. This method changes the length of the array.
	 * 
	 * @return The removed element from the array; <code>null</code> if the array is empty.
	 */
	native ArrayDouble pop();

	/**
	 * Sets the value value at a given index.
	 * 
	 * If the index is out of bounds, the value will still be set. The array's length will be updated to encompass the bounds implied by the added value.
	 * 
	 * @param index the index to be set
	 * @param item the value to be stored
	 */
	@JsOverlay
	void set(int index, ArrayDouble item) {
		fill(item, index, index + 1);
	}
}