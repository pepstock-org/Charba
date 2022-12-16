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

/**
 * A collection that contains no duplicate elements.<br>
 * More formally, sets contain no pair of elements <code>e1</code> and <code>e2</code> such that <code>e1.equals(e2)</code>, and at most one null element. <br>
 * This implementation uses a java script object as back-end to store objects ({@link Key} values).<br>
 * <br>
 * Some methods are annotated with <code>\u0040SuppressWarnings(&quot;unusable-by-js&quot;)</code> because J2CL transpiler emits warnings as not usable in the javascript part but
 * this collection must not be passed to any javascript code.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArrayKeySet<E extends Key> extends AbstractArraySet<E, ArrayKeyList<E>> {

	// delegated list to store objects
	private final ArrayKeyList<E> delegated;

	/**
	 * Internal constructor used to set an array instance as back-end of the set.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 * @param factory factory instance to create the key from a native one.
	 */
	ArrayKeySet(ArrayString array, KeyFactory<E> factory) {
		// creates delegated list
		// removing duplicates passing thru a java script set object
		this.delegated = new ArrayKeyList<>(array != null ? array.unique() : null, factory);
	}

	/**
	 * Creates an empty set.
	 * 
	 * @param factory factory instance to create the key from a native one.
	 */
	public ArrayKeySet(KeyFactory<E> factory) {
		this(null, factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArraySet#getDelegated()
	 */
	@Override
	ArrayKeyList<E> getDelegated() {
		return delegated;
	}

	/**
	 * Adds the specified element to this set if it is not already present (optional operation).
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public boolean add(E e) {
		// checks if consistent and not already preset
		if (Key.isValid(e) && !contains(e)) {
			// adds to delegated
			delegated.add(e);
			return true;
		}
		// if here, argument not consistent or already added
		return false;
	}

}