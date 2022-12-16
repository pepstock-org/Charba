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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;

/**
 * Represents the region definition with all properties and geometries needed to draw the region.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Feature extends NativeObjectContainer {

	// factory instance to create features
	static final FeatureFactory FACTORY = new FeatureFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		PROPERTIES("properties"),
		GEOMETRY("geometry");

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

	// instance of properties node.
	// this is not exposed to public but
	// the class provides a set of methods to get the properties
	private final Properties properties;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	Feature(NativeObject nativeObject) {
		super(nativeObject);
		// gets and stores properties
		this.properties = new Properties(getValue(Property.PROPERTIES));
	}

	/**
	 * Returns <code>true</code> if the geometry property is set.
	 * 
	 * @return <code>true</code> if the geometry property is set
	 */
	public boolean hasGeometry() {
		return has(Property.GEOMETRY) && getValue(Property.GEOMETRY) != null;
	}

	/**
	 * Returns the type of the feature.
	 * 
	 * @return the type of the feature
	 */
	public String getType() {
		return getValue(Property.TYPE, Undefined.STRING);
	}

	/**
	 * Returns <code>true</code> if the properties contain an element at specific property.
	 * 
	 * @param key key of the property of java script object.
	 * @return <code>true</code> if the properties contains an element at specific property
	 */
	public boolean hasProperty(Key key) {
		return properties.hasProperty(key);
	}

	/**
	 * Returns <code>true</code> if the properties contain an element at specific property.
	 * 
	 * @param key key of the property of java script object.
	 * @return <code>true</code> if the properties contains an element at specific property
	 */
	public boolean hasProperty(String key) {
		return hasProperty(Key.create(key));
	}

	/**
	 * Returns the list of properties names of the properties instance.
	 * 
	 * @return the list of properties names of the properties instance
	 */
	public List<Key> getPropertiesKeys() {
		return properties.getPropertiesKeys();
	}

	/**
	 * Returns the java script type of a property.
	 * 
	 * @param key name of the java script property
	 * @return the java script type of the property
	 */
	public ObjectType getPropertyType(Key key) {
		return properties.getPropertyType(key);
	}

	/**
	 * Returns the java script type of a property.
	 * 
	 * @param key name of the java script property
	 * @return the java script type of the property
	 */
	public ObjectType getPropertyType(String key) {
		return getPropertyType(Key.create(key));
	}

	/**
	 * Returns a number value at specific property.
	 * 
	 * @param key key of the property of properties.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	public double getPropertyValue(Key key, double defaultValue) {
		return properties.getPropertyValue(key, defaultValue);
	}

	/**
	 * Returns a number value at specific property.
	 * 
	 * @param key key of the property of properties.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	public double getPropertyValue(String key, double defaultValue) {
		return getPropertyValue(Key.create(key), defaultValue);
	}

	/**
	 * Returns a number value at specific property.
	 * 
	 * @param key key of the property of properties.
	 * @return value of the property
	 */
	public double getNumberProperty(Key key) {
		return getPropertyValue(key, Undefined.DOUBLE);
	}

	/**
	 * Returns a number value at specific property.
	 * 
	 * @param key key of the property of properties.
	 * @return value of the property
	 */
	public double getNumberProperty(String key) {
		return getNumberProperty(Key.create(key));
	}

	/**
	 * Returns a string value at specific property.
	 * 
	 * @param key key of the property
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	public String getPropertyValue(Key key, String defaultValue) {
		return properties.getPropertyValue(key, defaultValue);
	}

	/**
	 * Returns a string value at specific property.
	 * 
	 * @param key key of the property
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	public String getPropertyValue(String key, String defaultValue) {
		return getPropertyValue(Key.create(key), defaultValue);
	}

	/**
	 * Returns a string value at specific property.
	 * 
	 * @param key key of the property
	 * @return value of the property
	 */
	public String getStringProperty(Key key) {
		return getPropertyValue(key, Undefined.STRING);
	}

	/**
	 * Returns a string value at specific property.
	 * 
	 * @param key key of the property
	 * @return value of the property
	 */
	public String getStringProperty(String key) {
		return getStringProperty(Key.create(key));
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
	 * Internal mapper of java script object at "properties" name space of a feature.<br>
	 * This is not expose in order to reduce the complexity fo rthe user to access to those data.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Properties extends NativeObjectContainer {

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private Properties(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns true if the embedded JavaScript object contains an element at specific property.
		 * 
		 * @param key key of the property of JavaScript object.
		 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
		 */
		private boolean hasProperty(Key key) {
			return has(key);
		}

		/**
		 * Returns the list of properties names of the object.
		 * 
		 * @return the list of properties names of the object.
		 */
		private List<Key> getPropertiesKeys() {
			return keys();
		}

		/**
		 * Returns the java script type of the property.
		 * 
		 * @param key name of the java script property.
		 * @return the java script type of the property.
		 */
		private ObjectType getPropertyType(Key key) {
			return type(key);
		}

		/**
		 * Returns a value (int) in the embedded JavaScript object at specific property.
		 * 
		 * @param key key of the property of JavaScript object.
		 * @param defaultValue default value if the property is missing
		 * @return value of the property
		 */
		private double getPropertyValue(Key key, double defaultValue) {
			return getValue(key, defaultValue);
		}

		/**
		 * Returns a value (String) in the embedded JavaScript object at specific property.
		 * 
		 * @param key key of the property of JavaScript object.
		 * @param defaultValue default value if the property is missing
		 * @return value of the property
		 */
		private String getPropertyValue(Key key, String defaultValue) {
			return getValue(key, defaultValue);
		}

	}

	/**
	 * Creates {@link Feature} object from a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class FeatureFactory implements NativeObjectContainerFactory<Feature> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public Feature create(NativeObject nativeObject) {
			return new Feature(nativeObject);
		}

	}

}