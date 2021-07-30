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
package org.pepstock.charba.client.commons;

/**
 * The user of this interface has precise control over where in the list each element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a java script object as back-end to store objects ({@link Key} values).<br>
 * <br>
 * Some methods are annotated with <code>\u0040SuppressWarnings(&quot;unusable-by-js&quot;)</code> because J2CL transpiler emits warnings as not usable in the javascript part but
 * this collection must not be passed to any javascript code.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArrayKeyList extends AbstractArrayContainerList<Key, ArrayString> {

	// delegated array to store objects
	private final ArrayString array;

	/**
	 * Internal constructor used to set an array instance as back-end of the list.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 */
	ArrayKeyList(ArrayString array) {
		// if null, creates a new array
		if (array == null) {
			this.array = new ArrayString();
		} else {
			// uses an existing array
			this.array = array;
		}
	}

	/**
	 * Creates an empty list.
	 */
	public ArrayKeyList() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#getArray()
	 */
	@Override
	ArrayString getArray() {
		return array;
	}

	/**
	 * Loads an array of elements in the the list
	 * 
	 * @param values an array of elements to be loaded
	 */
	public void addAll(Key[] values) {
		// checks if arguments are consistent
		if (values != null && values.length > 0) {
			// scans all elements
			for (Key val : values) {
				// adds
				add(val);
			}
		}
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public boolean add(Key element) {
		// checks if element is consistent
		if (Key.isValid(element)) {
			// adds element
			array.push(element.value());
			return true;
		}
		// if here, element is not consistent
		// and not added
		return false;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		array.clear();
	}

	/**
	 * Returns the element at the specified position in this list. If index out of range, returns null
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public Key get(int index) {
		// checks range
		if (checkRange(index)) {
			String value = array.get(index);
			return Key.create(value);
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns null
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public Key set(int index, Key element) {
		// checks element is consistent and in range
		if (Key.isValid(element) && checkRange(index)) {
			// gets current element at that index
			String old = array.get(index);
			Key oldValue = Key.create(old);
			// replaces with new element
			array.set(index, element.value());
			// returns old
			return oldValue;
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public void add(int index, Key element) {
		// checks if element is consistent
		if (Key.isValid(element)) {
			array.insertAt(index, element.value());
		}
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public Key remove(int index) {
		// checks range
		if (checkRange(index)) {
			String value = array.remove(index);
			return Key.create(value);
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object object) {
		// checks if key
		if (object instanceof Key) {
			// cast
			Key value = (Key) object;
			// checks if is valid
			if (Key.isValid(value)) {
				// search
				return array.indexOf(value.value());
			}
		}
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object object) {
		// checks if key
		if (object instanceof Key) {
			// cast
			Key value = (Key) object;
			// checks if is valid
			if (Key.isValid(value)) {
				// search
				return array.lastIndexOf(value.value());
			}
		}
		return AbstractArrayList.NOT_FOUND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public final Object[] toArray() {
		// creates the result
		Object[] toArray = new Object[array.length()];
		// scans the array string
		for (int i = 0; i < array.length(); i++) {
			// creates enumeration item and stores it in the array
			toArray[i] = Key.create(array.get(i));
		}
		// returns array
		return toArray;
	}

}