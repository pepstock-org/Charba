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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Abstract list for object and array container list which has got common methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractArrayNativeContainerList<E, A extends Array> extends AbstractArrayContainerList<E, A> {

	// delegated linked list to store Java objects
	private final List<E> delegate = new LinkedList<>();

	/**
	 * Returns the wrapped list of elements.
	 * 
	 * @return the wrapped list of elements
	 */
	final List<E> getDelegate() {
		return delegate;
	}

	/**
	 * Loads an array of elements in the the list.
	 * 
	 * @param values an array of elements to be loaded
	 */
	public final void addAll(E[] values) {
		// checks if arguments are consistent
		if (values != null && values.length > 0) {
			// scans all elements
			for (E val : values) {
				// adds
				add(val);
			}
		}
	}

	/**
	 * Returns the number of elements in this list.
	 */
	@Override
	public final int size() {
		return delegate.size();
	}

	/**
	 * Returns true if this list contains no elements
	 */
	@Override
	public final boolean isEmpty() {
		return delegate.isEmpty();
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public final boolean contains(Object object) {
		return delegate.contains(object);
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public final Iterator<E> iterator() {
		return new IteratorImpl<>(this);
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public boolean add(E element) {
		// checks if element is consistent
		if (element != null) {
			// adds to linked list and returns if added
			return delegate.add(element);
		}
		// if here, element is not consistent
		// and not added
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified collection.
	 */
	@Override
	public final boolean containsAll(Collection<?> collection) {
		// checks if argument is consistent
		if (collection != null) {
			return delegate.containsAll(collection);
		}
		// if here, collection is not consistent
		return false;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		delegate.clear();
	}

	/**
	 * Returns the element at the specified position in this list. If index out of range, returns null
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public final E get(int index) {
		// checks range
		if (checkRange(index)) {
			return delegate.get(index);
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns null
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public E set(int index, E element) {
		// checks element is consistent and in range
		if (element != null && checkRange(index)) {
			// sets to linked list and returns old value
			return delegate.set(index, element);
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public void add(int index, E element) {
		// checks if element is consistent
		if (element != null) {
			// adds element
			delegate.add(index, element);
		}
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public E remove(int index) {
		// checks range
		if (checkRange(index)) {
			// removes from list and returns old value
			return delegate.remove(index);
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public final int indexOf(Object object) {
		// checks if argument is consistent
		if (object != null) {
			return delegate.indexOf(object);
		}
		// if here, element is not consistent
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public final int lastIndexOf(Object object) {
		// checks if argument is consistent
		if (object != null) {
			return delegate.lastIndexOf(object);
		}
		// if here, element is not consistent
		return AbstractArrayList.NOT_FOUND;
	}

	/**
	 * Returns a list iterator over the elements in this list
	 */
	@Override
	public final ListIterator<E> listIterator() {
		return new ListIteratorImpl<>(0, this);
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.<br>
	 * The specified index indicates the first element that would be returned by an initial call to next.<br>
	 * An initial call to previous would return the element with the specified index minus one.
	 */
	@Override
	public final ListIterator<E> listIterator(int index) {
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
	public final List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException(UNABLE_COPY_ARRAY_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public final Object[] toArray() {
		return delegate.toArray();
	}

}