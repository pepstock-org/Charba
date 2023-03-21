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
 * This implementation uses a java script object as back-end to store objects (string values).<br>
 * <br>
 * Some methods are annotated with <code>\u0040SuppressWarnings(&quot;unusable-by-js&quot;)</code> because J2CL transpiler emits warnings as not usable in the javascript part but
 * this collection must not be passed to any javascript code.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArrayStringSet extends AbstractArraySet<String, ArrayStringList> {

	// delegated list to store objects
	private final ArrayStringList delegated;

	/**
	 * Internal constructor used to set an array instance as back-end of the set.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 */
	ArrayStringSet(ArrayString array) {
		// creates delegated list
		// removing duplicates passing thru a java script set object
		this.delegated = new ArrayStringList(array != null ? array.unique() : null);
	}

	/**
	 * Creates an empty set.
	 */
	public ArrayStringSet() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArraySet#getDelegated()
	 */
	@Override
	ArrayStringList getDelegated() {
		return delegated;
	}

	/**
	 * Adds the specified element to this set if it is not already present (optional operation).
	 */
	@Override
	public boolean add(String e) {
		// checks if consistent and not already preset
		if (e != null && !contains(e)) {
			// adds to delegated
			delegated.add(e);
			return true;
		}
		// if here, argument not consistent or already added
		return false;
	}
}