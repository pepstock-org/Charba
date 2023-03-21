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
package org.pepstock.charba.client.intl;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Checker;

/**
 * Defines for objects that enable language sensitive value formatting.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @param <N> type of native format
 * @param <T> type of object that native format can manage
 * @param <R> type of resolved options
 * 
 */
abstract class AbtsractFormatWrapper<N, T, R> {

	// native format instance
	private final N nativeFormat;

	/**
	 * Creates object that enables language sensitive number formatting, using the native format instance.
	 * 
	 * @param nativeFormat native format instance
	 */
	AbtsractFormatWrapper(N nativeFormat) {
		// checks if native format is consistent
		// stores the native format
		this.nativeFormat = Checker.checkAndGetIfValid(nativeFormat, "Native format argument");
	}

	/**
	 * Returns the native format instance.
	 * 
	 * @return the native format instance
	 */
	final N getNativeFormat() {
		return nativeFormat;
	}

	/**
	 * Formats a value according to the locale and formatting options of this object.
	 * 
	 * @param value the value to format
	 * @return the value in the a string according to the locale and formatting options
	 */
	abstract String format(T value);

	/**
	 * Returns a new object with properties reflecting the locale and value formatting options computed during initialization of this object.
	 * 
	 * @return new object with properties reflecting the locale and value formatting options computed during initialization of this object
	 */
	abstract R resolvedOptions();

	/**
	 * Returns an array of objects containing the locale-specific tokens from which it possible to build custom strings while preserving the locale-specific parts.<br>
	 * It is useful for custom formatting of value strings.
	 * 
	 * @param value value to format
	 * @return an array of objects containing the formatted value in parts.
	 */
	abstract ArrayObject formatToParts(T value);

}