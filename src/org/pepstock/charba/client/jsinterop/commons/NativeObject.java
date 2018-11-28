/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

import java.util.List;

import com.google.gwt.core.client.JsDate;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base object for all native objects implemented in Charba.<br>
 * It implements some common methods and wraps some native methods. 
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class NativeObject {
	
	/**
	 * Defines a new property directly on an object, or modifies an existing property on an object.
	 * @param source the object on which to define the property.
	 * @param key the name of the property to be defined or modified.
	 * @param descriptor the descriptor for the property being defined or modified.
	 */
	static native <T extends NativeAbstractDescriptor> void defineProperty(NativeObject source, String key, T descriptor);
	
	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param source the object in which to look for the property.
	 * @param key the name of the property whose description is to be retrieved.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static native <T extends NativeAbstractDescriptor> T getOwnPropertyDescriptor(NativeObject source, String key);
	
	/**
	 * Returns an array of a given object's own property names, in the same order as we get with a normal loop.
	 * @param source the object of which the enumerable's own properties are to be returned.
	 * @return an array of strings that represent all the enumerable properties of the given object.
	 */
	static native ArrayString keys(NativeObject source);
	
	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * @param key the string name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	native boolean hasOwnProperty(String key);
	
	/**
	 * Returns an list of a given object's own property names, in the same order as we get with a normal loop.
	 * @param source the object of which the enumerable's own properties are to be returned.
	 * @return list of strings that represent all the enumerable properties of the given object.
	 */
	@JsOverlay
	final List<String> propertyKeys(){
		return ArrayListHelper.unmodifiableList(keys(this));
	}

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * @param key the name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	@JsOverlay
	final boolean hasProperty(String key) {
		return hasOwnProperty(key);
	}
	
	/**
	 * Removes a property from this object.
	 * @param key property key to be removed.
	 */
	@JsOverlay
	final void removeProperty(String key) {
		JsHelper.remove(this, key);
	}
	
	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineBooleanProperty(String key, boolean value) {
		// creates a descriptor
		NativeBooleanDescriptor descriptor = new NativeBooleanDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineIntProperty(String key, int value) {
		// creates a descriptor
		NativeIntegerDescriptor descriptor = new NativeIntegerDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineDoubleProperty(String key, double value) {
		// creates a descriptor
		NativeDoubleDescriptor descriptor = new NativeDoubleDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineStringProperty(String key, String value) {
		// creates a descriptor
		NativeStringDescriptor descriptor = new NativeStringDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}
	
	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineDateProperty(String key, JsDate value) {
		// creates a descriptor
		NativeDateDescriptor descriptor = new NativeDateDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}
	
	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineCallbackProperty(String key, CallbackProxy.Proxy value) {
		// creates a descriptor
		NativeCallbackProxyDescriptor descriptor = new NativeCallbackProxyDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}
	
	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineObjectProperty(String key, NativeObject value) {
		// creates a descriptor
		NativeObjectDescriptor descriptor = new NativeObjectDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final <T extends Array> void defineArrayProperty(String key, T value) {
		// creates a descriptor
		NativeArrayDescriptor<T> descriptor = new NativeArrayDescriptor<T>();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeBooleanDescriptor getBooleanProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.Boolean.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}
	
	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeIntegerDescriptor getIntProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.Number.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}
	
	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeDoubleDescriptor getDoubleProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.Number.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	public final NativeStringDescriptor getStringProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.String.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}
	
	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeDateDescriptor getDateProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.Object.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeObjectDescriptor getObjectProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.Object.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final <T extends Array> NativeArrayDescriptor<T> getArrayProperty(String key) {
		// checks if the property is present
		if (hasProperty(key) && ObjectType.Array.equals(JsHelper.typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}
	
//	/**
//	 * Returns an immutable map with all properties (name and value) of the object.<br>
//	 * The key is name of property, element is the value of the property
//	 * @return an immutable map with all properties (name and value) of the object.
//	 */
//	@JsOverlay
//	public final <T> Map<String, T> getObjectAsMap() {
//		// creates the map
//		Map<String, T> result = new HashMap<String, T>();
//		// gets all keys of object
//		ArrayString keys = keys(this);
//		// checks if array of keys is consistent
//		if (keys != null && keys.length() > 0) {
//			// scans all properties
//			for (int i=0; i<keys.length(); i++) {
//				// gets the property key
//				String keyToAdd = keys.get(i);
//				// gets the descriptor by key
//				// FIXME!!!
//				//NativeAbstractDescriptor<T> descriptor = getOwnPropertyDescriptor(this, keyToAdd);
//				// adds to the map
//				//result.put(keyToAdd, descriptor.getValue());
//			}
//		}
//		// returns immutable map
//		return Collections.unmodifiableMap(result);
//	}

	/**
	 * 
	 * @param descriptor
	 */
	@JsOverlay
	private final void resetPropertyDescriptor(NativeAbstractDescriptor descriptor) {
		// configures the property
		descriptor.setConfigurable(true);
		descriptor.setEnumerable(true);
		descriptor.setWritable(true);
	}

}
