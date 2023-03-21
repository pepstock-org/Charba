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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.items.Undefined;

/**
 * IMternal utility to manage an incremental object id, stored in the {@link NativeObject} in a specific property of Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class IncrementalIdHandler {

	// global instance
	private static final IncrementalIdHandler INSTANCE = new IncrementalIdHandler();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// internal property to set an unique id
		CHARBA_INTERNAL_INTERNAL_ID("charbaInternalObjectId");

		// name value of property
		private final String value;
		//

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	}

	// default prefix of CHARBA internal id
	private static final String DEFAULT_INTERNAL_PREFIX_ID = "charba-internal-id-";
	// internal counters
	// K = class name of native container, V = integer counter
	private final Map<Class<?>, AtomicInteger> counters = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private IncrementalIdHandler() {
	}

	/**
	 * Returns the singleton instance of this utility.
	 * 
	 * @return the singleton instance of this utility
	 */
	static IncrementalIdHandler get() {
		return INSTANCE;
	}

	/**
	 * Checks if the id already stored and if not, creates a new internal id in the native object container counter.
	 * 
	 * @param container native object container where checks and stores the id
	 */
	void checkAndSetId(NativeObjectContainer container) {
		checkAndSetId(container, DEFAULT_INTERNAL_PREFIX_ID);
	}

	/**
	 * Checks if the id already stored and if not, creates a new internal id in the native object container counter.
	 * 
	 * @param container native object container where checks and stores the id
	 * @param prefix prefix to add to the value of incremental id.
	 */
	void checkAndSetId(NativeObjectContainer container, String prefix) {
		// checks if container is consistent
		Checker.checkIfValid(container, "Container argument");
		// checks if container contains the internal object id
		if (!container.has(Property.CHARBA_INTERNAL_INTERNAL_ID)) {
			// creates and stores new id
			setNewId(container, prefix != null && !prefix.trim().isEmpty() ? prefix : DEFAULT_INTERNAL_PREFIX_ID);
		}
	}

	/**
	 * Returns the internal id, stored in the native object container.<br>
	 * If id is not stored, throws an {@link IllegalArgumentException}.
	 * 
	 * @param container native object container where retrieves the id
	 * @return the internal id
	 */
	String getId(NativeObjectContainer container) {
		// checks if container is consistent
		Checker.checkIfValid(container, "Container argument");
		// checks if the property exists
		Checker.assertCheck(container.has(Property.CHARBA_INTERNAL_INTERNAL_ID), "The incremental id has not been stored. Invoke 'checkAndSetId' before calling 'getId'");
		// returns value
		return container.getValue(Property.CHARBA_INTERNAL_INTERNAL_ID, Undefined.STRING);
	}

	/**
	 * Creates new internal id in the native object container.
	 * 
	 * @param container native object container where stores the id
	 * @param prefix prefix to add to the value of incremental id.
	 */
	private void setNewId(NativeObjectContainer container, String prefix) {
		// checks class has been stored
		// creating new counter
		AtomicInteger counter = counters.computeIfAbsent(container.getClass(), mapKey -> new AtomicInteger(0));
		// stores the internal id for caching
		container.setValue(Property.CHARBA_INTERNAL_INTERNAL_ID, prefix + counter.getAndIncrement());
	}

}