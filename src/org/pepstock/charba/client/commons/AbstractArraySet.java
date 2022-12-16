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

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Abstract set implementation which is wrapping an array (java script native object) by an {@link AbstractArrayList}, providing common methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <E> type of java elements provided by the set
 * @param <A> type of {@link AbstractArrayList} which is wrapped
 */
abstract class AbstractArraySet<E, A extends AbstractArrayList<E, ArrayString>> implements Set<E> {

	/**
	 * Returns the {@link AbstractArrayList} instance.
	 * 
	 * @return the {@link AbstractArrayList} instance.
	 */
	abstract A getDelegated();

	/**
	 * Returns the array native object instance.
	 * 
	 * @return the array native object instance.
	 */
	final ArrayString getArray() {
		return getDelegated().getArray();
	}

	/**
	 * Returns the number of elements in this set.
	 */
	@Override
	public final int size() {
		return getDelegated().size();
	}

	/**
	 * Returns true if this set contains no elements
	 */
	@Override
	public final boolean isEmpty() {
		return getDelegated().isEmpty();
	}

	/**
	 * Returns true if this set contains the specified element.
	 */
	@Override
	public final boolean contains(Object object) {
		return getDelegated().contains(object);
	}

	/**
	 * Returns an iterator over the elements in this set.
	 */
	@Override
	public final Iterator<E> iterator() {
		return getDelegated().iterator();
	}

	/**
	 * Returns an array containing all of the elements in this set.
	 */
	@Override
	public final Object[] toArray() {
		return getDelegated().toArray();
	}

	/**
	 * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
	 */
	@Override
	public final <T> T[] toArray(T[] a) {
		return getDelegated().toArray(a);
	}

	/**
	 * Removes the first occurrence of the specified element from this set, if it is present. If this set does not contain the element, it is unchanged.
	 */
	@Override
	public final boolean remove(Object o) {
		return getDelegated().remove(o);
	}

	/**
	 * Returns true if this set contains all of the elements of the specified collection.
	 */
	@Override
	public final boolean containsAll(Collection<?> c) {
		return getDelegated().containsAll(c);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this set, in the order that they are returned by the specified collection's iterator
	 */
	@Override
	public final boolean addAll(Collection<? extends E> c) {
		// set modified
		boolean modified = c != null && !c.isEmpty();
		// checks if argument is consistent
		if (modified) {
			Iterator<? extends E> iter = c.iterator();
			// scans all elements
			while (iter.hasNext()) {
				// gets item
				E item = iter.next();
				// adds and
				// sets modified
				modified = modified && add(item);
			}
		}
		return modified;
	}

	/**
	 * Retains only the elements in this set that are contained in the specified collection.<br>
	 * In other words, removes from this set all of its elements that are not contained in the specified collection.
	 */
	@Override
	public final boolean retainAll(Collection<?> c) {
		return getDelegated().retainAll(c);
	}

	/**
	 * Removes from this set all of its elements that are contained in the specified collection.
	 */
	@Override
	public final boolean removeAll(Collection<?> c) {
		return getDelegated().removeAll(c);
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after this call returns.
	 */
	@Override
	public final void clear() {
		getDelegated().clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return getDelegated().toString();
	}
}