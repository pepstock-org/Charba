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

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration, and obtain the iterator's current position in the
 * list.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <E> type of list
 */
public final class ListIteratorImpl<E> extends IteratorImpl<E> implements ListIterator<E> {

	/**
	 * Creates an iterator of a list
	 * 
	 * @param index start index of parent list
	 * @param parent list of this iterator
	 */
	ListIteratorImpl(int index, List<E> parent) {
		super(parent);
		cursor = index;
	}

	/**
	 * Returns true if this list iterator has more elements when traversing the list in the forward direction.
	 */
	@Override
	public boolean hasPrevious() {
		return cursor != 0;
	}

	/**
	 * Returns the index of the element that would be returned by a subsequent call to <code>next()</code>.
	 */
	@Override
	public int nextIndex() {
		return cursor;
	}

	/**
	 * Returns the index of the element that would be returned by a subsequent call to <code>previous()</code>.
	 */
	@Override
	public int previousIndex() {
		return cursor - 1;
	}

	/**
	 * Returns the previous element in the list and moves the cursor position backwards.
	 */
	@Override
	public E previous() {
		// gets new index
		int i = cursor - 1;
		// if less than ZERO
		// EXCEPTION
		if (i < 0) {
			throw new NoSuchElementException();
		}
		// sets new cursor
		cursor = i;
		// sets last return
		lastReturn = i;
		// gets object
		return getParent().get(i);
	}

	/**
	 * Replaces the last element returned by next() or previous() with the specified element .
	 */
	@Override
	public void set(E e) {
		// if last return less than ZERO, EXCEPTION
		if (lastReturn < 0) {
			throw new IllegalStateException();
		}
		// sets element
		getParent().set(lastReturn, e);
	}

	/**
	 * Inserts the specified element in the list
	 */
	@Override
	public void add(E e) {
		// gets new index
		int i = cursor;
		// adds element
		getParent().add(i, e);
		// increments current cursor
		cursor = i + 1;
		// resets last return index
		lastReturn = -1;
	}
}