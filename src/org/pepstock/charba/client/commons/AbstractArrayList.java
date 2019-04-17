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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.pepstock.charba.client.utils.JSON;

/**
 * Abstract list implementation which is wrapping an array (java script native object), providing common methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <E> type of java elements provided by the list
 * @param <A> type of array which is wrapped
 */
abstract class AbstractArrayList<E, A extends Array> implements List<E> {
	// exception message
	static final String UNABLE_COPY_ARRAY_MESSAGE = "Unable to copy into an array";
	// constants to use for index of and last index of when not found
	static final int NOT_FOUND = -1;

	/**
	 * Returns the array native object instance.
	 * 
	 * @return the array native object instance.
	 */
	abstract A getArray();

	/**
	 * Returns the number of elements in this list.
	 */
	@Override
	public int size() {
		// returns the size of JS array
		return getArray().length();
	}

	/**
	 * Returns true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		// returns if empty
		return getArray().isEmpty();
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object object) {
		// checks if index of the object in JS array is not equals to -1
		return indexOf(object) != -1;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorImpl<>(this);
	}

	/**
	 * Not implemented
	 */
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException(UNABLE_COPY_ARRAY_MESSAGE);
	}

	/**
	 * Not implemented
	 */
	@Override
	public final <T> T[] toArray(T[] array) {
		throw new UnsupportedOperationException(UNABLE_COPY_ARRAY_MESSAGE);
	}

	/**
	 * Returns a list iterator over the elements in this list
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new ListIteratorImpl<>(0, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public final boolean addAll(int index, Collection<? extends E> collection) {
		// Warnings in com/google/gwt/emul/java/util/List.java
		// [WARN] [unusable-by-js] Type of parameter 'c' in 'boolean List.addAll(int, Collection)' (exposed by
		// 'AbstractArrayList') is not usable by but exposed to JavaScript.
		// for this reason return false
		return false;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the
	 * element, it is unchanged.
	 */
	@Override
	public final boolean remove(Object object) {
		// gets index of object
		int index = indexOf(object);
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
	public boolean containsAll(Collection<?> collection) {
		// checks if the arguments is consistent
		if (collection != null) {
			Iterator<?> iter = collection.iterator();
			// scans all elements
			while (iter.hasNext()) {
				// if does not contain return false
				if (!contains(iter.next())) {
					return false;
				}
			}
			// if here, all elements are in the list
			return true;
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection.
	 */
	@Override
	public final boolean removeAll(Collection<?> collection) {
		// set modified checking if collection is empty
		boolean modified = collection != null && !collection.isEmpty();
		// checks if the arguments is consistent
		if (modified) {
			Iterator<?> iter = collection.iterator();
			// scans all elements
			while (iter.hasNext()) {
				// removes and checks if modified
				modified = modified && remove(iter.next());
			}
		}
		return modified;
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the
	 * list.<br>
	 * The specified index indicates the first element that would be returned by an initial call to next.<br>
	 * An initial call to previous would return the element with the specified index minus one.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		// if index is out of range, EXCEPTION
		if (!checkRange(index)) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		return new ListIteratorImpl<>(index, this);
	}

	/**
	 * Not implemented
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(UNABLE_COPY_ARRAY_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "[array=" + JSON.stringify(getArray()) + "]";
	}

	/**
	 * Checks if the index is in the right range.
	 * 
	 * @param index index to be checked
	 * @return <code>true</code> if the index is in the right range otherwise false
	 */
	protected final boolean checkRange(int index) {
		return index >= 0 && index < getArray().length();
	}
}