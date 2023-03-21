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
package org.pepstock.charba.client.geo;

import java.util.List;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * TopoJSON is an extension of GeoJSON that encodes topology. Rather than representing geometries discretely, geometries in TopoJSON files are stitched together from shared line
 * segments called arcs.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TopoJson extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		OBJECTS("objects");

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

	// objects instance
	private final Objects objects;
	// objcts properties as string builder
	private final StringBuilder propertiesAsString = new StringBuilder();

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	TopoJson(NativeObject nativeObject) {
		super(nativeObject);
		// gets and stores the objects
		this.objects = new Objects(getValue(Property.OBJECTS));
	}

	/**
	 * Returns the list of properties names of the objects node.
	 * 
	 * @return the list of properties names of the objects node.
	 */
	public List<Key> objectsKeys() {
		return objects.propertiesKeys();
	}

	/**
	 * Provides the list of the available keys as string, for exception message.
	 * 
	 * @return the list of the available keys as string, for exception message
	 */
	String objectsKeysAsString() {
		// checks if properties as string instance is empty
		if (propertiesAsString.length() == 0) {
			// loads string builder
			for (Key property : objectsKeys()) {
				// checks if is not the first cycle in order
				// to the comma between items
				if (propertiesAsString.length() > 0) {
					// adds comma
					propertiesAsString.append(Constants.COMMA).append(Constants.BLANK);
				}
				// adds 'property name'
				propertiesAsString.append(Constants.APOSTROPHE).append(property.value()).append(Constants.APOSTROPHE);
			}
		}
		return propertiesAsString.toString();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

	/**
	 * Maps the objects node of topojson definition.<br>
	 * It contains all {@link Feature} needed to draw the GEO chart.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class Objects extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private Objects(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the list of properties names of the object.
		 * 
		 * @return the list of properties names of the object.
		 */
		private List<Key> propertiesKeys() {
			return keys();
		}

	}

}