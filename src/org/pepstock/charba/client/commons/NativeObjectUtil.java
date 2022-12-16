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
package org.pepstock.charba.client.commons;

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.plugins.NativeHook;

/**
 * Utility object to manage all native objects implemented in Charba by {@link NativeObject}.<br>
 * It implements some common methods to set and get properties in the native object.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class NativeObjectUtil {

	/**
	 * To avoid any instantiation
	 */
	private NativeObjectUtil() {
		// do nothing
	}

	/**
	 * Creates a native object setting the hash code property in order it is not enumerable.
	 * 
	 * @return new native object instance
	 */
	static NativeObject create() {
		// new object
		NativeObject nativeObject = JsHelper.get().create();
		// applies new hash code
		// and returns item
		return NativeObjectHashing.handleHashCode(nativeObject);
	}

	/**
	 * Returns an list of a given object's own property names.
	 * 
	 * @param object native object to be managed
	 * @return list of strings that represent all the enumerable properties of the given object.
	 */
	static List<String> propertiesKeys(NativeObject object) {
		return ArrayListHelper.unmodifiableList(NativeUtil.keys(object));
	}

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	static boolean hasProperty(NativeObject object, String key) {
		return JsHelper.get().has(object, key);
	}

	/**
	 * Removes a property from this object.
	 * 
	 * @param object native object to be managed
	 * @param key property key to be removed.
	 */
	static void removeProperty(NativeObject object, String key) {
		JsHelper.get().remove(object, key);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineBooleanProperty(NativeObject object, String key, boolean value) {
		Reflect.setBoolean(object, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineIntProperty(NativeObject object, String key, int value) {
		Reflect.setInt(object, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineDoubleProperty(NativeObject object, String key, double value) {
		Reflect.setDouble(object, key, value);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineStringProperty(NativeObject object, String key, String value) {
		Reflect.setString(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineImageProperty(NativeObject object, String key, Img value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineCanvasProperty(NativeObject object, String key, Canvas value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void definePatternProperty(NativeObject object, String key, CanvasPatternItem value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineGradientProperty(NativeObject object, String key, CanvasGradientItem value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineCallbackProperty(NativeObject object, String key, CallbackProxy.Proxy value) {
		Reflect.setCallbackProxy(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineCallbackProperty(NativeObject object, String key, NativeCallback value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineCallbackProperty(NativeObject object, String key, NativeHook value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineObjectProperty(NativeObject object, String key, NativeObject value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineChartProperty(NativeObject object, String key, Chart value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineElementProperty(NativeObject object, String key, BaseHtmlElement value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 */
	static void defineEventProperty(NativeObject object, String key, NativeBaseEvent value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Defines a new property directly on object object, or modifies an existing property.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to be defined or modified.
	 * @param value the object associated with the property.
	 * @param <T> type of array to define
	 */
	static <T extends Array> void defineArrayProperty(NativeObject object, String key, T value) {
		Reflect.set(object, key, value);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static boolean getBooleanProperty(NativeObject object, String key, boolean defaultValue) {
		// checks if the property is present
		if (ObjectType.BOOLEAN.equals(JsHelper.get().typeOf(object, key))) {
			// returns the value
			return Reflect.getBoolean(object, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static int getIntProperty(NativeObject object, String key, int defaultValue) {
		// checks if the property is present
		if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(object, key))) {
			// returns the value
			return Reflect.getInt(object, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static double getDoubleProperty(NativeObject object, String key, double defaultValue) {
		// checks if the property is present
		if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(object, key))) {
			// returns the value
			return Reflect.getDouble(object, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static String getStringProperty(NativeObject object, String key, String defaultValue) {
		// checks if the property is present
		if (ObjectType.STRING.equals(JsHelper.get().typeOf(object, key))) {
			// returns the descriptor
			return Reflect.getString(object, key);
		}
		// if here, property does not exist
		return defaultValue;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static Img getImageProperty(NativeObject object, String key, Img defaultValue) {
		return getJSTypeProperty(object, key, defaultValue);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static Canvas getCanvasProperty(NativeObject object, String key, Canvas defaultValue) {
		return getJSTypeProperty(object, key, defaultValue);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static CanvasPatternItem getPatternProperty(NativeObject object, String key, CanvasPatternItem defaultValue) {
		return getJSTypeProperty(object, key, defaultValue);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value if the property is missing
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static CanvasGradientItem getGradientProperty(NativeObject object, String key, CanvasGradientItem defaultValue) {
		return getJSTypeProperty(object, key, defaultValue);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static NativeObject getObjectProperty(NativeObject object, String key) {
		return getJSTypeProperty(object, key, null);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static Chart getChartProperty(NativeObject object, String key) {
		return getJSTypeProperty(object, key, null);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static BaseHtmlElement getElementProperty(NativeObject object, String key) {
		return getJSTypeProperty(object, key, null);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static NativeBaseEvent getEventProperty(NativeObject object, String key) {
		return getJSTypeProperty(object, key, null);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param <T> type of array to use
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static <T extends Array> T getArrayProperty(NativeObject object, String key) {
		// checks if the property is present
		if (ObjectType.ARRAY.equals(JsHelper.get().typeOf(object, key))) {
			// returns the descriptor
			return Reflect.get(object, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's prototype chain) of a given object.
	 * 
	 * @param object native object to be managed
	 * @param key the name of the property to test.
	 * @param defaultValue default value to return when the property is not found
	 * @param <T> type of {@link IsJSType}.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	private static <T extends IsJSType> T getJSTypeProperty(NativeObject object, String key, T defaultValue) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(object, key))) {
			// returns the descriptor
			return Reflect.get(object, key);
		}
		return defaultValue;
	}
}