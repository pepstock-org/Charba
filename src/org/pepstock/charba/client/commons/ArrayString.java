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
package org.pepstock.charba.client.commons;

import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.colors.IsColor;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Array object which maps the java script object.<br>
 * A simple wrapper around a homogeneous native array of string values.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.ARRAY, namespace = JsPackage.GLOBAL)
public final class ArrayString extends Array {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to filter the array.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	private interface ArrayFilterCallback {

		/**
		 * Method of function to be called to filter the array.
		 * 
		 * @param element current element being processed in the array
		 * @param index the index of the current element being processed in the array
		 * @return a value that coerces to <code>true</code> to keep the element, or to <code>false</code> otherwise
		 */
		boolean call(String element, int index);
	}

	/**
	 * This method creates new array instance with a variable number of <code>string</code> arguments.
	 * 
	 * @param items string items to create new array
	 * @return new array instance of strings.
	 */
	private static native ArrayString of(String... items);

	/**
	 * To avoid any instantiation
	 */
	ArrayString() {
	}

	/**
	 * This method creates new array instance with a variable number of <code>string</code> arguments.
	 * 
	 * @param items string items to create new array
	 * @return new array instance of strings or <code>null</code> if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrNull(String... items) {
		// checks if consistent
		if (ArrayUtil.isEmpty(items)) {
			// returns null
			return null;
		}
		// returns array
		return ArrayString.of(items);
	}

	/**
	 * This method creates new array instance with a variable number of <code>string</code> arguments.
	 * 
	 * @param items string items to create new array
	 * @return new array instance of strings or an empty array if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrEmpty(String... items) {
		// checks if consistent
		if (ArrayUtil.isEmpty(items)) {
			// returns null
			return new ArrayString();
		}
		// returns array
		return ArrayString.of(items);
	}

	/**
	 * Creates a java script array of strings starting from list of strings.
	 * 
	 * @param items list of strings to load in the new java script array.
	 * @return new array instance of strings or <code>null</code> if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static ArrayString fromOrNull(List<String> items) {
		// checks if list is null
		if (!ArrayListHelper.isConsistent(items)) {
			return null;
		}
		// checks if is already a list with array
		if (items instanceof ArrayStringList) {
			// casts to array list
			ArrayStringList list = (ArrayStringList) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayString result = new ArrayString();
		for (String value : items) {
			// adds element
			result.push(value);
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from list of strings.
	 * 
	 * @param items list of strings to load in the new java script array.
	 * @return new array instance of strings or an empty array if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static ArrayString fromOrEmpty(List<String> items) {
		// checks if is already a list with array
		if (items instanceof ArrayStringList) {
			// casts to array list
			ArrayStringList list = (ArrayStringList) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayString result = new ArrayString();
		// checks if list is null
		if (!ArrayListHelper.isConsistent(items)) {
			return result;
		}
		for (String value : items) {
			// adds element
			result.push(value);
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from array of colors.
	 * 
	 * @param items array of colors to load in the new java script array.
	 * @return new array instance of strings or <code>null</code> if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrNull(IsColor... items) {
		// checks if array is null
		if (ArrayUtil.isEmpty(items)) {
			return null;
		}
		// creates the array
		ArrayString result = new ArrayString();
		// scans items
		for (IsColor color : items) {
			// checks color consistency
			IsColor.checkIfValid(color);
			// adds element
			result.push(color.toRGBA());
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from array of colors.
	 * 
	 * @param items array of colors to load in the new java script array.
	 * @return new array instance of strings or an mepty array if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrEmpty(IsColor... items) {
		// creates the array
		ArrayString result = new ArrayString();
		// checks if array is null
		if (ArrayUtil.isEmpty(items)) {
			return result;
		}
		// scans items
		for (IsColor color : items) {
			// checks color consistency
			IsColor.checkIfValid(color);
			// adds element
			result.push(color.toRGBA());
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from array of keys.
	 * 
	 * @param items array of keys to load in the new java script array.
	 * @return new array instance of strings or <code>null</code> if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrNull(Key... items) {
		return fromOrNull(false, items);
	}

	/**
	 * Creates a java script array of strings starting from array of keys.
	 * 
	 * @param items array of keys to load in the new java script array.
	 * @param asSet if <code>true</code>, the array is build without duplicates, as a set
	 * @return new array instance of strings or <code>null</code> if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrNull(boolean asSet, Key... items) {
		// checks if array is null
		if (ArrayUtil.isEmpty(items)) {
			return null;
		}
		// creates the array
		ArrayString result = new ArrayString();
		// scans items
		for (Key key : items) {
			// checks if key is consistent
			if (Key.isValid(key) && (!asSet || result.indexOf(key.value()) == AbstractArrayList.NOT_FOUND)) {
				// adds element
				result.push(key.value());
			}
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from array of keys.
	 * 
	 * @param items array of keys to load in the new java script array.
	 * @return new array instance of strings or an empty array if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrEmpty(Key... items) {
		return fromOrEmpty(false, items);
	}

	/**
	 * Creates a java script array of strings starting from array of keys.
	 * 
	 * @param items array of keys to load in the new java script array.
	 * @param asSet if <code>true</code>, the array is build without duplicates, as a set
	 * @return new array instance of strings or an empty array if argument is <code>null</code> or length to 0
	 */
	@JsOverlay
	public static ArrayString fromOrEmpty(boolean asSet, Key... items) {
		// creates the array
		ArrayString result = new ArrayString();
		// checks if array is null
		if (ArrayUtil.isEmpty(items)) {
			return result;
		}
		// scans items
		for (Key key : items) {
			// checks if key is consistent
			if (Key.isValid(key) && (!asSet || result.indexOf(key.value()) == AbstractArrayList.NOT_FOUND)) {
				// adds element
				result.push(key.value());
			}
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from set of keys.
	 * 
	 * @param items set of keys to load in the new java script array.
	 * @param <T> type of the key
	 * @return new array instance of strings or <code>null</code> if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static <T extends Key> ArrayString fromOrNull(Set<T> items) {
		// checks if array is null
		if (!ArrayListHelper.isConsistent(items)) {
			return null;
		}
		// checks if is already a set with array
		if (items instanceof ArrayKeySet) {
			// casts to array list
			ArrayKeySet<?> list = (ArrayKeySet<?>) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayString result = new ArrayString();
		// scans items
		for (Key key : items) {
			// checks if key is consistent
			if (Key.isValid(key)) {
				// adds element
				result.push(key.value());
			}
		}
		// returns the array
		return result;
	}

	/**
	 * Creates a java script array of strings starting from set of keys.
	 * 
	 * @param items set of keys to load in the new java script array.
	 * @param <T> type of the key
	 * @return new array instance of strings or an empty array if argument is <code>null</code> or empty
	 */
	@JsOverlay
	public static <T extends Key> ArrayString fromOrEmpty(Set<T> items) {
		// checks if is already a set with array
		if (items instanceof ArrayKeySet) {
			// casts to array list
			ArrayKeySet<?> list = (ArrayKeySet<?>) items;
			// returns array
			return list.getArray();
		}
		// creates the array
		ArrayString result = new ArrayString();
		// checks if array is null
		if (!ArrayListHelper.isConsistent(items)) {
			return result;
		}
		// scans items
		for (Key key : items) {
			// checks if key is consistent
			if (Key.isValid(key)) {
				// adds element
				result.push(key.value());
			}
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
	native int lastIndexOf(String value);

	/**
	 * Returns the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element.
	 * 
	 * @param value element to search for
	 * @return the index of the first occurrence of the specified element in this array, or -1 if this array does not contain the element
	 */
	native int indexOf(String value);

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
	native ArrayString slice(int start, int end);

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
	native ArrayString splice(int start);

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
	native ArrayString splice(int start, int deleteCounts);

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
	native ArrayString splice(int start, int deleteCounts, String item);

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
	String remove(int index) {
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
	void insertAt(int index, String item) {
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
	public String get(int index) {
		return slice(index, index + 1).pop();
	}

	/**
	 * Fills all the elements of an array from a start index to an end index with a passed value. The end index is not included.
	 * 
	 * @param item value to fill an array.
	 * @param start Start index, defaults to 0.
	 * @param end End index, defaults to array.length().
	 */
	native void fill(String item, int start, int end);

	/**
	 * Adds one element to the end of an array.
	 * 
	 * @param item The element to add to the end of the array.
	 */
	native void push(String item);

	/**
	 * Removes the last element from an array and returns that element. This method changes the length of the array.
	 * 
	 * @return The removed element from the array; <code>null</code> if the array is empty.
	 */
	native String pop();

	/**
	 * Sets the value value at a given index.
	 * 
	 * If the index is out of bounds, the value will still be set. The array's length will be updated to encompass the bounds implied by the added value.
	 * 
	 * @param index the index to be set
	 * @param item the value to be stored
	 */
	@JsOverlay
	void set(int index, String item) {
		fill(item, index, index + 1);
	}

	/**
	 * Creates a new array with all elements that pass the test implemented by the provided function.
	 * 
	 * @param callback is a predicate, to test each element of the array.<br>
	 *            Return a value that coerces to true to keep the element, or to false otherwise.
	 * @return a new array with the elements that pass the test.<br>
	 *         If no elements pass the test, an empty array will be returned.
	 */
	native ArrayString filter(ArrayFilterCallback callback);

	/**
	 * Creates new array without duplicated values.<br>
	 * It's used for Set implementation.
	 * 
	 * @return an array containing the unique elements.
	 */
	@JsOverlay
	ArrayString unique() {
		return filter((value, index) -> this.indexOf(value) == index);
	}

}