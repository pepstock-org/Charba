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
package org.pepstock.charba.client.jsinterop.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each
 * element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a java script array as back-end to store objects (native JavaScript objects).
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObject
 */
public final class ArrayObjectList extends AbstractArrayList<NativeObject, ArrayObject> {

	// delegated array to store objects
	private final ArrayObject array;

	/**
	 * Internal constructor used to set an array instance as back-end of the list.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 */
	ArrayObjectList(ArrayObject array) {
		// if null, creates a new array
		if (array == null) {
			this.array = new ArrayObject();
		} else {
			// uses an existing array
			this.array = array;
		}
	}

	/**
	 * Creates an empty list
	 */
	public ArrayObjectList() {
		this(null);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.AbstractArrayList#getArray()
	 */
	@Override
	ArrayObject getArray() {
		return array;
	}

	/**
	 * Loads an array of elements into the list
	 * 
	 * @param values an array of elements to be loaded
	 */
	public void addAll(NativeObject... values) {
		// scans all elements
		for (NativeObject val : values) {
			// adds
			add(val);
		}
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@Override
	public boolean add(NativeObject e) {
		array.push(e);
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the
	 * element, it is unchanged.
	 */
	@Override
	public boolean remove(Object o) {
		// gets index of object
		int index = indexOf(o);
		// if is in the right range
		if (checkRange(index)) {
			// removes by index
			remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// if does not contain return false
			if (!contains(e.next())) {
				return false;
			}
		}
		// if here, all elements are in the list
		return true;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by
	 * the specified collection's iterator
	 */
	@Override
	public boolean addAll(Collection<? extends NativeObject> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		Iterator<? extends NativeObject> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// if adds
			if (add(e.next())) {
				// sets modified
				modified &= true;
			} else {
				// sets false!
				modified = false;
			}
		}
		return modified;
	}

	/**
	 * Inserts all of the elements in the specified collection into this list at the specified position.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their
	 * indices).
	 */
	@Override
	public boolean addAll(int index, Collection<? extends NativeObject> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		// checks if continue
		if (modified && checkRange(index)) {
			// saves index
			int myIndex = index;
			Iterator<? extends NativeObject> e = c.iterator();
			// scans all elements
			while (e.hasNext()) {
				// adds to the stored new index
				add(myIndex, e.next());
				// increments new index
				myIndex++;
			}
			// sets modified
			modified = true;
		}
		return modified;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		Iterator<?> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// removes and checks if modified
			modified = modified && remove(e.next());
		}
		return modified;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection.<br>
	 * In other words, removes from this list all of its elements that are not contained in the specified collection.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		if (modified) {
			// creates a copy of elements
			List<NativeObject> contained = new ArrayList<NativeObject>();
			// scans all current elements
			for (int i = 0; i < size(); i++) {
				NativeObject value = get(i);
				// checks if not present into
				// passed collection
				if (!c.contains(get(i))) {
					// adds to temporary list
					contained.add(value);
				}
			}
			// if temporary list is not empty
			if (!contained.isEmpty()) {
				// scans all elements
				for (NativeObject toRemove : contained) {
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
	public NativeObject get(int index) {
		// checks range
		if (checkRange(index)) {
			return array.get(index);
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns
	 * null
	 */
	@Override
	public NativeObject set(int index, NativeObject element) {
		// checks range
		if (checkRange(index)) {
			// gets current element at that index
			NativeObject old = array.get(index);
			// replaces with new element
			array.set(index, element);
			// returns old
			return old;
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their
	 * indices).
	 */
	@Override
	public void add(int index, NativeObject element) {
		array.insertAt(index, element);
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from
	 * the list.
	 */
	@Override
	public NativeObject remove(int index) {
		// checks range
		if (checkRange(index)) {
			return array.remove(index);
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the
	 * element.
	 */
	@Override
	public int indexOf(Object o) {
		return array.indexOf(o);
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the
	 * element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		return array.lastIndexOf(o);
	}
}