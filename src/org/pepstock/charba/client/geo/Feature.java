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
package org.pepstock.charba.client.geo;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;

/**
 * FIXME
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

	private final Properties properties;
	
	/**
	 * Creates the object with new native object instance to be wrapped.<br>
	 * Used for default.
	 */
	Feature() {
		this(null);
		// sets the type "Sphere" as default.
		setValue(Property.TYPE, "Sphere");
	}

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
	 * 
	 * @return
	 */
	public boolean hasGeometry() {
		return has(Property.GEOMETRY) && getValue(Property.GEOMETRY) != null;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return getValue(Property.TYPE, Undefined.STRING);
	}

	/**
	 * Returns true if the embedded JavaScript object contains an element at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 */
	public boolean hasProperty(Key key) {
		return properties.hasProperty(key);
	}

	/**
	 * Returns the list of properties names of the object.
	 * 
	 * @return the list of properties names of the object.
	 */
	public List<Key> getPropertiesKeys() {
		return properties.getPropertiesKeys();
	}

	/**
	 * Returns the java script type of the property.
	 * 
	 * @param key name of the java script property.
	 * @return the java script type of the property.
	 */
	public ObjectType getPropertyType(Key key) {
		return properties.getPropertyType(key);
	}

	/**
	 * Returns a value (int) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	public double getPropertyValue(Key key, double defaultValue) {
		return properties.getPropertyValue(key, defaultValue);
	}

	/**
	 * Returns a value (String) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	public String getPropertyValue(Key key, String defaultValue) {
		return properties.getPropertyValue(key, defaultValue);
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
	 * FIXME
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Properties extends NativeObjectContainer {

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
	 * FIXME
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
