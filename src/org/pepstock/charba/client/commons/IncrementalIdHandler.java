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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.items.UndefinedValues;

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

	// prefix of CHARBA internal id
	private static final String INTERNAL_PREFIX_ID = "charba-internal-id-";
	// internal counters
	// K = class name of native container, V = integer counter
	private final Map<String, AtomicInteger> counters = new HashMap<>();

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
	 * Checks if the id already stored and if not, creates a new internal id in the native object container, using the class (passed as argument) name has key of the cache of
	 * counter.
	 * 
	 * @param container native object container where checks and stores the id
	 * @param clazz the class instance where its name is used as key of the cache of counters.
	 */
	void checkAndSetId(NativeObjectContainer container, Class<?> clazz) {
		// checks if container and class are consistent
		Class<?> checkedClazz = checkAndGetClass(container, clazz);
		// checks if container contains the internal object id
		if (!container.has(Property.CHARBA_INTERNAL_INTERNAL_ID)) {
			// creates and stores new id
			setNewId(container, checkedClazz);
		}
	}

	/**
	 * Returns the internal id, stored in the native object container, using the class (passed as argument) name has key of the cache of counter.<br>
	 * If the id is not stores, it creates new one.
	 * 
	 * @param container native object container where retrieves the id
	 * @param clazz the class instance where its name is used as key of the cache of counters.
	 * @return the internal id
	 */
	String getId(NativeObjectContainer container, Class<?> clazz) {
		// checks if container and class are consistent
		Class<?> checkedClazz = checkAndGetClass(container, clazz);
		// checks if the property exists
		if (!container.has(Property.CHARBA_INTERNAL_INTERNAL_ID)) {
			// creates and stores new id
			setNewId(container, checkedClazz);
		}
		// returns value
		return container.getValue(Property.CHARBA_INTERNAL_INTERNAL_ID, UndefinedValues.STRING);
	}

	/**
	 * Creates new internal id in the native object container, using the class (passed as argument) name has key of the cache of counter.
	 * 
	 * @param container native object container where stores the id
	 * @param clazz the class instance where its name is used as key of the cache of counters.
	 */
	private void setNewId(NativeObjectContainer container, Class<?> clazz) {
		// checks class has been stored
		// creating new counter
		AtomicInteger counter = counters.computeIfAbsent(clazz.getName(), mapKey -> new AtomicInteger(0));
		// stores the internal id for caching
		container.setValue(Property.CHARBA_INTERNAL_INTERNAL_ID, INTERNAL_PREFIX_ID + counter.getAndIncrement());
	}

	/**
	 * Checks if the native object container instance and the class are consistent.
	 * 
	 * @param container native object container where checks and stores the id
	 * @param clazz the class instance where its name is used as key of the cache of counters.
	 * @return the class passed as argument or, if <code>null</code>, the class of the native object container
	 */
	private Class<?> checkAndGetClass(NativeObjectContainer container, Class<?> clazz) {
		// checks if container is consistent
		if (container == null) {
			// if here the container is not consistent
			// then exception
			throw new IllegalArgumentException("Container argument is null");
		}
		// checks if class is consistent
		if (clazz == null) {
			// if here returns the class of container
			return container.getClass();
		}
		// if here, container and class consistent
		// then returns the passed class
		return clazz;
	}

}
