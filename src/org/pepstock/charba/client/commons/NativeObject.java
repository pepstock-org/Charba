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
 * It implements some common methods and wraps some native methods.<br>
 * It's also mapping the proxy.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class NativeObject {

	/**
	 * To avoid any instantiation
	 */
	private NativeObject() {
		// do nothing
	}

	/**
	 * Creates a native object setting the hash code property in order it is not enumerable.
	 * 
	 * @return new native object instance
	 */
	@JsOverlay
	static NativeObject create() {
		// new object
		NativeObject nativeObject = new NativeObject();
		// applies new hash code
		NativeObjectHashing.handleHashCode(nativeObject);
		// returns item
		return nativeObject;
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
	 * Returns an array of a given object's own property names, in the same order as we get with a normal loop.
	 * 
	 * @param source the object of which the enumerable's own properties are to be returned.
	 * @return an array of strings that represent all the enumerable properties of the given object.
	 */
	static native ArrayString keys(NativeObject source);

	/**
	 * Returns all own property descriptors of a given object.
	 * 
	 * @param source the object for which to get all own property descriptors.
	 * @return an object containing all own property descriptors of an object.<br>
	 *         Might be an empty object, if there are no properties.
	 */
	static native NativeObject getOwnPropertyDescriptors(NativeObject source);

	/**
	 * Returns an list of a given object's own property names.
	 * 
	 * @return list of strings that represent all the enumerable properties of the given object.
	 */
	@JsOverlay
	List<String> propertiesKeys() {
		return ArrayListHelper.unmodifiableList(keys(getOwnPropertyDescriptors(this)));
	}

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	@JsOverlay
	boolean hasProperty(String key) {
		return JsHelper.get().has(this, key);
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
		NativeJsObjectBoolean.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineIntProperty(String key, int value) {
		NativeJsObjectInteger.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineDoubleProperty(String key, double value) {
		NativeJsObjectDouble.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineStringProperty(String key, String value) {
		NativeJsObjectString.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineImageProperty(String key, Img value) {
		NativeJsObjectImage.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void definePatternProperty(String key, CanvasPatternItem value) {
		NativeJsObjectPattern.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineGradientProperty(String key, CanvasGradientItem value) {
		NativeJsObjectGradient.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineCallbackProperty(String key, CallbackProxy.Proxy value) {
		NativeJsObjectCallbackProxy.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineObjectProperty(String key, NativeObject value) {
		NativeJsObjectObject.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineChartProperty(String key, Chart value) {
		NativeJsObjectChart.set(this, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	@JsOverlay
	void defineEventProperty(String key, BaseNativeEvent value) {
		NativeJsObjectEvent.set(this, key, value);
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
		NativeJsObjectArray.set(this, key, value);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	boolean getBooleanProperty(String key, boolean defaultValue) {
		// checks if the property is present
		if (ObjectType.BOOLEAN.equals(JsHelper.get().typeOf(this, key))) {
			// returns the value
			return NativeJsObjectBoolean.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	int getIntProperty(String key, int defaultValue) {
		// checks if the property is present
		if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(this, key))) {
			// returns the value
			return NativeJsObjectInteger.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	double getDoubleProperty(String key, double defaultValue) {
		// checks if the property is present
		if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(this, key))) {
			// returns the value
			return NativeJsObjectDouble.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	String getStringProperty(String key, String defaultValue) {
		// checks if the property is present
		if (ObjectType.STRING.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectString.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	Img getImageProperty(String key, Img defaultValue) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectImage.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	CanvasPatternItem getPatternProperty(String key, CanvasPatternItem defaultValue) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectPattern.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	CanvasGradientItem getGradientProperty(String key, CanvasGradientItem defaultValue) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectGradient.get(this, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	NativeObject getObjectProperty(String key) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectObject.get(this, key);
		}
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	Chart getChartProperty(String key) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectChart.get(this, key);
		}
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	BaseNativeEvent getEventProperty(String key) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectEvent.get(this, key);
		}
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
	<T extends Array> T getArrayProperty(String key) {
		// checks if the property is present
		if (ObjectType.ARRAY.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return NativeJsObjectArray.get(this, key);
		}
		// if here, property does not exist
		return null;
	}

}
