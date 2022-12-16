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

import java.util.Collections;
import java.util.Set;

/**
 * Utility to create array set objects from java script arrays.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ArraySetHelper {

	/**
	 * To avoid any instantiation
	 */
	private ArraySetHelper() {
		// nothing
	}

	/**
	 * Creates a array set of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the set is creating.
	 * @return a array set of strings instance
	 */
	public static ArrayStringSet set(ArrayString values) {
		// creates the set
		// if values not consistent
		// creates an empty set
		return new ArrayStringSet(values);
	}

	/**
	 * Creates a array set of {@link Key} values by an java script array of strings.
	 * 
	 * @param array array of strings to load when the set is creating.
	 * @param factory factory implementation to create keys by a single native object of the array.
	 * @param <E> type of key
	 * @return a array set of {@link Key}.
	 */
	public static <E extends Key> ArrayKeySet<E> set(ArrayString array, KeyFactory<E> factory) {
		return new ArrayKeySet<>(array, factory);
	}

	/**
	 * Creates a array set of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the set is creating.
	 * @return a array set of strings instance
	 */
	public static Set<String> unmodifiableSet(ArrayString values) {
		return Collections.unmodifiableSet(set(values));
	}

	/**
	 * Creates an unmodifiable array set of {@link Key} values by an java script array of strings.
	 * 
	 * @param array array of strings to load when the set is creating.
	 * @param factory factory implementation to create keys by a single native object of the array.
	 * @param <E> type of key
	 * @return a array set of {@link Key}.
	 */
	public static <E extends Key> Set<E> unmodifiableSet(ArrayString array, KeyFactory<E> factory) {
		return Collections.unmodifiableSet(set(array, factory));
	}

}