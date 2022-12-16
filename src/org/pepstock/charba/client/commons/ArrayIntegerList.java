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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The user of this interface has precise control over where in the list each element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a java script array as back-end to store objects (integers).<br>
 * <br>
 * Some methods are annotated with <code>\u0040SuppressWarnings(&quot;unusable-by-js&quot;)</code> because J2CL transpiler emits warnings as not usable in the javascript part but
 * this collection must not be passed to any javascript code.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArrayIntegerList extends AbstractArrayList<Integer, ArrayInteger> {

	// delegated array to store objects
	private final ArrayInteger array;

	/**
	 * Internal constructor used to set an array instance as back-end of the list.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 */
	ArrayIntegerList(ArrayInteger array) {
		// if null, creates a new JS array
		if (array == null) {
			this.array = new ArrayInteger();
		} else {
			// uses an existing array
			this.array = array;
		}
	}

	/**
	 * Creates an empty list
	 */
	public ArrayIntegerList() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#getArray()
	 */
	@Override
	ArrayInteger getArray() {
		return array;
	}

	/**
	 * Loads an array of elements in the list
	 * 
	 * @param values an array of elements to be loaded
	 */
	public void addAll(int... values) {
		// checks if arguments are consistent
		if (ArrayUtil.isNotEmpty(values)) {
			// scans all elements
			for (int val : values) {
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
	public boolean add(Integer element) {
		// checks if argument is consistent
		if (element != null) {
			// adds element to array
			array.push(element);
			return true;
		}
		// if here, not added
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#checkAndGet(java.lang.Object)
	 */
	@Override
	Integer checkAndGet(Object object) {
		// checks if canvas
		if (object instanceof Integer) {
			// returns casted object
			return (Integer) object;
		}
		// if here is not a correct type
		return null;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection.<br>
	 * In other words, removes from this list all of its elements that are not contained in the specified collection.
	 */
	@Override
	public boolean retainAll(Collection<?> collection) {
		// set modified
		boolean modified = ArrayListHelper.isConsistent(collection);
		// checks if argument is consistent
		if (modified) {
			// creates a copy of elements
			List<Integer> contained = new ArrayList<>();
			// scans all current elements
			for (int i = 0; i < size(); i++) {
				Integer value = get(i);
				// checks if not present into
				// passed collection
				if (!collection.contains(value)) {
					// adds to temporary list
					contained.add(value);
				}
			}
			// if temporary list is not empty
			if (!contained.isEmpty()) {
				// scans all elements
				for (Integer toRemove : contained) {
					// removes and checks if modified
					modified = modified && remove(toRemove);
				}
			}
		}
		return modified;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		array.clear();
	}

	/**
	 * Returns the element at the specified position in this list. If index out of range, returns Integer.MIN_VALUE
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public Integer get(int index) {
		// checks range
		if (checkRange(index)) {
			return array.get(index);
		}
		return Integer.MIN_VALUE;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns Integer.MIN_VALUE
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public Integer set(int index, Integer element) {
		// checks if element is consistent and in range
		if (element != null && checkRange(index)) {
			// gets current element at that index
			Integer old = array.get(index);
			// replaces with new element
			array.set(index, element);
			// returns old
			return old;
		}
		return Integer.MIN_VALUE;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public void add(int index, Integer element) {
		// checks if element is consistent
		if (element != null) {
			// inserts in the array
			array.insertAt(index, element);
		}
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public Integer remove(int index) {
		// checks range
		if (checkRange(index)) {
			return array.remove(index);
		}
		return Integer.MIN_VALUE;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object object) {
		// checks if is integer
		if (object instanceof Integer) {
			Integer value = (Integer) object;
			// check index of
			return array.indexOf(value.intValue());
		}
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object object) {
		// checks if is integer
		if (object instanceof Integer) {
			Integer value = (Integer) object;
			// check last index of
			return array.lastIndexOf(value.intValue());
		}
		return AbstractArrayList.NOT_FOUND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		Integer[] toArray = new Integer[array.length()];
		for (int i = 0; i < array.length(); i++) {
			toArray[i] = array.get(i);
		}
		return toArray;
	}
}