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

import org.pepstock.charba.client.utils.JSON;

/**
 * Base class for all classes which are wrapping a native java script array.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of array
 */
public abstract class NativeArrayContainer<T extends Array> {

	// native array instance
	private final T nativeArray;

	/**
	 * Creates the object with native array instance to be wrapped.
	 * 
	 * @param nativeArray native array instance to be wrapped.
	 */
	protected NativeArrayContainer(T nativeArray) {
		// checks if argument is consistent
		Checker.checkIfValid(nativeArray, "Array instance");
		// stores the array
		this.nativeArray = nativeArray;
	}

	/**
	 * Returns the native array instance.
	 * 
	 * @return the native array instance.
	 */
	protected final T getNativeArray() {
		return nativeArray;
	}

	/**
	 * Returns the string JSON representation of the array.
	 * 
	 * @return the string JSON representation of the array.
	 */
	public final String toJSON() {
		return JSON.stringifyWithReplacer(nativeArray, 3);
	}

}