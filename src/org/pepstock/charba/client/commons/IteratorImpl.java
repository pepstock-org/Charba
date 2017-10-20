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

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Custom iterator over a collection. 
 * 
 * @author Andrea "Stock" Stocchero
 * @see java.util.Iterator
 * @param <E> type of element of the list
 */
public class IteratorImpl<E> implements Iterator<E> {

	// list instance
	private final List<E> parent;
	// index of next element to return
	protected int cursor;  
	// index of last element returned; -1 if no such
	protected int lastReturn = -1; 

	/**
	 * Builds the iterator, storing the list instance
	 * @param parent list instance
	 * @see java.util.List
	 */
	IteratorImpl(List<E> parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent list
	 */
	protected List<E> getParent() {
		return parent;
	}

	/**
	 * Returns true if the iteration has more elements.
	 */
	@Override
	public boolean hasNext() {
		// checks if is at the end of elements
		return cursor != parent.size();
	}

	/**
	 * Returns the next element in the iteration.
	 */
	@Override
	public E next() {
		int i = cursor;
		// if beyond the end of element, EXCEPTION
		if (i >= parent.size()){
			throw new NoSuchElementException();
		}
		// increments cursor
		cursor = i + 1;
		// stores the index of this next call
		// for remove method if will be called
		lastReturn = i;
		// gets the object from list
		return parent.get(i);
	}

	/**
	 * Removes from the underlying collection the last element returned by this iterator. This method can be called only once per call to <code>next()</code>. 
	 */
	@Override
	public void remove() {
		// if <0 no next has been called, EXCEPTION
		if (lastReturn < 0){
			throw new IllegalStateException();
		}
		// removes from parent at
		// index stored into last return
		parent.remove(lastReturn);
		// sets cursor to last return 
		cursor = lastReturn;
		// resets last return
		lastReturn = -1;
	}
}