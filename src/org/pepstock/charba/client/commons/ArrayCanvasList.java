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

import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.items.Undefined;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a java script array as back-end to store objects (canvas).
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.ArrayCanvas
 */
public final class ArrayCanvasList extends AbstractArrayList<Canvas, ArrayCanvas> {

	// delegated array to store objects
	private final ArrayCanvas array;

	/**
	 * Internal constructor used to set an array instance as back-end of the list.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 */
	ArrayCanvasList(ArrayCanvas array) {
		// if null, creates a new array
		if (array == null) {
			this.array = new ArrayCanvas();
		} else {
			// uses an existing array
			this.array = array;
		}
	}

	/**
	 * Creates an empty list
	 */
	public ArrayCanvasList() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#getArray()
	 */
	@Override
	ArrayCanvas getArray() {
		return array;
	}

	/**
	 * Loads an array of elements in the list
	 * 
	 * @param values an array of elements to be loaded
	 */
	public void addAll(Canvas... values) {
		// checks if arguments are consistent
		if (ArrayUtil.isNotEmpty(values)) {
			// scans all elements
			for (Canvas val : values) {
				// adds
				add(val);
			}
		}
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@Override
	public boolean add(Canvas element) {
		// checks if element is consistent
		if (element != null) {
			// adds element
			array.push(element);
			return true;
		}
		// if here, element is not consistent
		// and not added
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#checkAndGet(java.lang.Object)
	 */
	@Override
	Canvas checkAndGet(Object object) {
		// checks if canvas
		if (object instanceof Canvas) {
			// returns casted object
			return (Canvas) object;
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
			List<Canvas> contained = new ArrayList<>();
			// scans all current elements
			for (int i = 0; i < size(); i++) {
				Canvas value = get(i);
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
				for (Canvas toRemove : contained) {
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
	 * Returns the element at the specified position in this list. If index out of range, returns null
	 */
	@Override
	public Canvas get(int index) {
		// checks range
		if (checkRange(index)) {
			return array.get(index);
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns null
	 */
	@Override
	public Canvas set(int index, Canvas element) {
		// checks if element is consistent and in range
		if (element != null && checkRange(index)) {
			// gets current element at that index
			Canvas old = array.get(index);
			// replaces with new element
			array.set(index, element);
			// returns old
			return old;
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 */
	@Override
	public void add(int index, Canvas element) {
		// checks if element is consistent
		if (element != null) {
			array.insertAt(index, element);
		}
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@Override
	public Canvas remove(int index) {
		// checks range
		if (checkRange(index)) {
			return array.remove(index);
		}
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object object) {
		// checks if object is consistent
		if (object instanceof Canvas) {
			Canvas value = (Canvas) object;
			// check index of
			return array.indexOf(value);
		}
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object object) {
		// checks if object is consistent
		if (object instanceof Canvas) {
			Canvas value = (Canvas) object;
			// check last index of
			return array.lastIndexOf(value);
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
		// creates the result
		Object[] toArray = new Object[array.length()];
		// scans the array string
		for (int i = 0; i < array.length(); i++) {
			// creates enumeration item and stores it in the array
			toArray[i] = array.get(i);
		}
		// returns array
		return toArray;
	}

}