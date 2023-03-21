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
package org.pepstock.charba.client.treemap;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.items.Undefined;

/**
 * Inner data implementation of the object which is mapping additional data.<br>
 * Inside this object there is an array of original object used by the user.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InnerData extends NativeObjectContainer {

	private enum Property implements Key
	{
		CHILDREN("children"),
		INDEX("_idx"),
		LABEL("label"),
		PATH("path");

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

	// children instance
	private final ArrayObject children;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	InnerData(NativeObject nativeObject) {
		super(nativeObject);
		// loads children
		this.children = getArrayValue(Property.CHILDREN);
	}

	/**
	 * Returns <code>true</code> if the data point is a group.
	 * 
	 * @return <code>true</code> if the data point is a group
	 */
	public boolean isGroup() {
		return children != null && !children.isEmpty();
	}

	/**
	 * Returns <code>true</code> if the data point is a leaf node after grouping.
	 * 
	 * @return <code>true</code> if the data point is a leaf node after grouping
	 */
	public boolean isLeaf() {
		return children != null && children.isEmpty();
	}

	/**
	 * Returns the object in the user format of the data point.
	 * 
	 * @param factory instance of factory to create the native object
	 * @param <T> type of user object
	 * @return the object in the user format of the data point
	 */
	public <T extends NativeObjectContainer> List<T> getChildren(NativeObjectContainerFactory<T> factory) {
		// checks if argument is consistent
		if (factory != null) {
			// gets the data object
			ArrayObject array = getArrayValue(Property.CHILDREN);
			if (array != null) {
				// returns array
				return ArrayListHelper.unmodifiableList(array, factory);
			}
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the original index in the data of the data point.
	 * 
	 * @return the original index in the data of the data point
	 */
	public int getIndex() {
		return getValue(Property.INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the label of the data point in the grouped data.
	 * 
	 * @return the label of the data point in the grouped data
	 */
	public String getLabel() {
		return getValue(Property.LABEL, Undefined.STRING);
	}

	/**
	 * Returns the path of the data point in the grouped data.
	 * 
	 * @return the path of the data point in the grouped data
	 */
	public String getPath() {
		return getValue(Property.PATH, Undefined.STRING);
	}

	// -------------------------
	// GENERIC ATTRIBUTES
	// -------------------------

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
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @return custom field value from data point.
	 */
	public final String getAttributeAsString(Key key) {
		return getValue(key, Undefined.STRING);
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

}