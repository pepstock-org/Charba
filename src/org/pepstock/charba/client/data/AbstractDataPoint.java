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
package org.pepstock.charba.client.data;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.DataPointType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the common methods for a data point.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractDataPoint extends NativeObjectContainer {

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	protected enum CharbaProperty implements Key
	{
		// internal properties about the data type
		CHARBA_X_TYPE("charbaXType"),
		CHARBA_Y_TYPE("charbaYType");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private CharbaProperty(String value) {
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
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	protected AbstractDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	// -------------------------
	// GENERIC ATTRIBUTES
	// -------------------------

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, double value) {
		setValue(key, value);
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @return custom field value from data point.
	 */
	public final double getAttribute(Key key) {
		return getValue(key, Undefined.DOUBLE);
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, String value) {
		setValue(key, value);
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @return custom field value from data point.
	 */
	public final String getAttributeAsString(Key key) {
		return getValue(key, Undefined.STRING);
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public final void setAttribute(Key key, Date value) {
		setValue(key, value);
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @return custom field value from data point.
	 */
	public final Date getAttributeAsDate(Key key) {
		return getValue(key, (Date) null);
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param <T> type of user object
	 * @param value value to set.
	 */
	public final <T extends NativeObjectContainer> void setAttribute(Key key, T value) {
		setValue(key, value);
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @param factory instance of factory to create the native object
	 * @param <T> type of user object
	 * @return custom field value from data point.
	 */
	public final <T extends NativeObjectContainer> T getAttributeAsObject(Key key, NativeObjectContainerFactory<T> factory) {
		// checks if the factory is consistent
		if (factory != null && Key.isValid(key)) {
			return factory.create(getValue(key));
		}
		// if here, factory not consistent
		// then returns null
		return null;
	}

	/**
	 * Checks the type of the property, setting the right type property.
	 * 
	 * @param property the property of the data to store
	 * @param typeProperty property to use to store the type
	 * @param type type of the property to store in the object
	 */
	protected void checkAndSetType(Key property, Key typeProperty, DataPointType type) {
		// checks if property is stored
		if (has(property)) {
			// sets type
			setValue(typeProperty, type);
		} else {
			// if here, the property is set as unknown
			setValue(typeProperty, DataPointType.UNKNOWN);
		}
	}
}