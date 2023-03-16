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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Cores animation collections names provided out of the box by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultAnimationCollectionKey implements Key
{
	/**
	 * Defines the default animation collection for colors.
	 */
	COLORS("colors"),
	/**
	 * Defines the default animation collection for numbers.
	 */
	NUMBERS("numbers"),
	/**
	 * Defines the default animation collection for visible property.
	 */
	VISIBLE("visible");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DefaultAnimationCollectionKey(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default animation collection.
	 * 
	 * @param collection the animation collection to check
	 * @return <code>true</code> if the argument is equals to a default animation collection
	 */
	public static boolean is(Key collection) {
		// checks if collection key is consistent
		if (Key.isValid(collection)) {
			return is(collection.value());
		}
		// if here, argument not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default animation collection.
	 * 
	 * @param collection the animation collection to check
	 * @return <code>true</code> if the argument is equals to a default animation collection
	 */
	public static boolean is(String collection) {
		return Key.hasKeyByValue(values(), collection);
	}

}