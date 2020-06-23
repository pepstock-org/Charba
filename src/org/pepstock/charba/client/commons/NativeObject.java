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
package org.pepstock.charba.client.commons;

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base object for all native objects implemented in Charba.<br>
 * It implements some common methods and wraps some native methods.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class NativeObject {

	/**
	 * To avoid any instantiation
	 */
	NativeObject() {
	}

	/**
	 * Defines a new property directly on an object, or modifies an existing property on an object.
	 * 
	 * @param source the object on which to define the property.
	 * @param key the name of the property to be defined or modified.
	 * @param descriptor the descriptor for the property being defined or modified.
	 * @param <T> type of descriptor set value
	 */
	static native <T extends NativeAbstractDescriptor> void defineProperty(NativeObject source, String key, T descriptor);

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param source the object in which to look for the property.
	 * @param key the name of the property whose description is to be retrieved.
	 * @param <T> type of descriptor result value
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static native <T extends NativeAbstractDescriptor> T getOwnPropertyDescriptor(NativeObject source, String key);

	/**
	 * Returns an array of a given object's own property names, in the same order as we get with a normal loop.
	 * 
	 * @param source the object of which the enumerable's own properties are to be returned.
	 * @return an array of strings that represent all the enumerable properties of the given object.
	 */
	static native ArrayString keys(NativeObject source);

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the string name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	native boolean hasOwnProperty(String key);

	/**
	 * Returns an list of a given object's own property names, in the same order as we get with a normal loop.
	 * 
	 * @return list of strings that represent all the enumerable properties of the given object.
	 */
	@JsOverlay
	List<String> propertyKeys() {
		return ArrayListHelper.unmodifiableList(keys(this));
	}

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	@JsOverlay
	boolean hasProperty(String key) {
		// checks if argument is consistent
		if (key != null) {
			return hasOwnProperty(key);
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Removes a property from this object.
	 * 
	 * @param key property key to be removed.
	 */
	@JsOverlay
	void removeProperty(String key) {
		JsHelper.get().remove(this, key);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineBooleanProperty(String key, boolean value) {
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
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineIntProperty(String key, int value) {
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
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineDoubleProperty(String key, double value) {
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
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineStringProperty(String key, String value) {
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
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineImageProperty(String key, Img value) {
		// creates a descriptor
		NativeImageDescriptor descriptor = new NativeImageDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void definePatternProperty(String key, CanvasPatternItem value) {
		// creates a descriptor
		NativePatternDescriptor descriptor = new NativePatternDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineGradientProperty(String key, CanvasGradientItem value) {
		// creates a descriptor
		NativeGradientDescriptor descriptor = new NativeGradientDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineCallbackProperty(String key, CallbackProxy.Proxy value) {
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
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineObjectProperty(String key, NativeObject value) {
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
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineChartProperty(String key, Chart value) {
		// creates a descriptor
		NativeChartDescriptor descriptor = new NativeChartDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineEventProperty(String key, BaseNativeEvent value) {
		// creates a descriptor
		NativeEventDescriptor descriptor = new NativeEventDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 * @param <T> type of array to define
	 */
	@JsOverlay
	<T extends Array> void defineArrayProperty(String key, T value) {
		// creates a descriptor
		NativeArrayDescriptor<T> descriptor = new NativeArrayDescriptor<>();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeBooleanDescriptor getBooleanProperty(String key) {
		// checks if the property is present
		if (ObjectType.BOOLEAN.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeIntegerDescriptor getIntProperty(String key) {
		return getInternalNumberProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeDoubleDescriptor getDoubleProperty(String key) {
		return getInternalNumberProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param <T> type of descriptor to use
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	private <T extends NativeAbstractDescriptor> T getInternalNumberProperty(String key) {
		// checks if the property is present
		if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeStringDescriptor getStringProperty(String key) {
		// checks if the property is present
		if (ObjectType.STRING.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeImageDescriptor getImageProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativePatternDescriptor getPatternProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeGradientDescriptor getGradientProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeObjectDescriptor getObjectProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeChartDescriptor getChartProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeEventDescriptor getEventProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test
	 * @param <T> type of descriptor to use
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	private <T extends NativeAbstractDescriptor> T getInternalObjectProperty(String key) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param <T> type of array to use
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	<T extends Array> NativeArrayDescriptor<T> getArrayProperty(String key) {
		// checks if the property is present
		if (ObjectType.ARRAY.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Sets the properties of a descriptor to <code>true</code>, as default in java script when you use <code>obj[key] = value</code>
	 * 
	 * @param descriptor the descriptor to be set
	 */
	@JsOverlay
	private void resetPropertyDescriptor(NativeAbstractDescriptor descriptor) {
		// configures the property
		descriptor.setConfigurable(true);
		descriptor.setEnumerable(true);
		descriptor.setWritable(true);
	}

}
