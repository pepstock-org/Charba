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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.ContextType;

/**
 * Abstract implementation to map the context used as argument on options, data sets, scales and plugins callbacks.<br>
 * All context implementations have got a property which is a reference to the chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ChartContext extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		TYPE("type"),
		ATTRIBUTES("charbaAttributes");

		// name value of property
		private final String value;

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

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	public ChartContext(NativeObject nativeObject) {
		super(nativeObject);
		// checks if object is consistent
		Checker.checkIfValid(nativeObject, "Native context argument");
		// the chart must be there
		Checker.assertCheck(has(Property.CHART), "Unable to retrieve the chart instance and the context does not seem to be consistent");
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		return super.getNativeChart(Property.CHART).getChart();
	}

	/**
	 * Returns the type of the context.
	 * 
	 * @return the type of the context
	 */
	public final ContextType getType() {
		return getValue(Property.TYPE, ContextType.values(), ContextType.UNKNOWN);
	}

	/**
	 * Sets the additional attributes.
	 * 
	 * @param attributes additional attributes instance.
	 * @param <T> type of native object container to store
	 */
	public final <T extends NativeObjectContainer> void setAttributes(T attributes) {
		setValue(Property.ATTRIBUTES, attributes);
	}

	/**
	 * Checks if there is any attribute node.
	 * 
	 * @return <code>true</code> if there is an attributes node, otherwise <code>false</code>.
	 */
	public final boolean hasAttributes() {
		return has(Property.ATTRIBUTES);
	}

	/**
	 * Returns the attributes, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to map the attributes or an empty object if not exist.
	 */
	public final <T extends NativeObjectContainer> T getAttributes(NativeObjectContainerFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks if there is a options
			if (hasAttributes()) {
				// creates and returns the object
				return factory.create(getValue(Property.ATTRIBUTES));
			}
			// if here, creates an empty object
			T options = factory.create();
			// adds to the context
			setValue(Property.ATTRIBUTES, options);
			// and then returns
			return options;
		}
		// if here, argument is not consistent
		return null;
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, double value) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			// stores the value
			setValue(key, value);
		}
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @param defaultValue default value if the property is missing
	 * @return custom field value from data point.
	 */
	public final double getAttribute(Key key, double defaultValue) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			return getValue(key, defaultValue);
		}
		// if here, the key is not valid
		// then returns default
		return defaultValue;
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, boolean value) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			// stores the value
			setValue(key, value);
		}
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @param defaultValue default value if the property is missing
	 * @return custom field value from data point.
	 */
	public final boolean getAttribute(Key key, boolean defaultValue) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			return getValue(key, defaultValue);
		}
		// if here, the key is not valid
		// then returns default
		return defaultValue;
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, int value) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			// stores the value
			setValue(key, value);
		}
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @param defaultValue default value if the property is missing
	 * @return custom field value from data point.
	 */
	public final int getAttribute(Key key, int defaultValue) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			return getValue(key, defaultValue);
		}
		// if here, the key is not valid
		// then returns default
		return defaultValue;
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, String value) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			// stores the value
			setValue(key, value);
		}
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @param defaultValue default value if the property is missing
	 * @return custom field value from data point.
	 */
	public final String getAttribute(Key key, String defaultValue) {
		// checks if key is valid
		if (checkIfPropertyIsValid(key)) {
			return getValue(key, defaultValue);
		}
		// if here, the key is not valid
		// then returns default
		return defaultValue;
	}

	/**
	 * Returns <code>true</code> if the context is consistent.<br>
	 * Custom context (the plugin ones) should extend it and check if the context is consistent before invoking the callback.
	 * 
	 * @return <code>true</code> if the context is consistent
	 */
	protected boolean isConsistent() {
		return true;
	}

	/**
	 * Checks if the key passed as argument is a key already used for other context properties.
	 * 
	 * @param property property to use to store a custom attribute
	 * @return <code>true</code> if the property can be used to store an attribute.
	 */
	protected boolean checkIfPropertyIsValid(Key property) {
		// checks if key is valid
		if (Key.isValid(property)) {
			// checks if is NOT a value of defined properties
			return !Key.hasKeyByValue(Property.values(), property.value());
		}
		// if here the property passed as argument
		// is not valid
		return false;
	}
}