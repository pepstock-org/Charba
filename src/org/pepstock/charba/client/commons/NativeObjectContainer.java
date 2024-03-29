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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.NativeInterpolator;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.plugins.NativeHook;
import org.pepstock.charba.client.utils.JSON;

/**
 * Base class for all classes which are wrapping a native java script object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class NativeObjectContainer {

	// native object instance
	private final NativeObject nativeObject;

	/**
	 * Creates the object with an empty native object instance.
	 */
	protected NativeObjectContainer() {
		this(NativeObjectUtil.create());
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected NativeObjectContainer(NativeObject nativeObject) {
		this.nativeObject = (nativeObject == null ? NativeObjectUtil.create() : nativeObject);
	}

	// ------------------------------------------
	// --- COMMONS
	// ------------------------------------------

	/**
	 * Checks if the argument, which is assuming is a default values instance, is consistent.<br>
	 * If not, throws {@link IllegalArgumentException}.
	 * 
	 * @param defaultValues default values instance to check
	 * @param <T> type of default argument
	 * @return the same instance passed as argument
	 */
	protected final <T> T checkDefaultValuesArgument(T defaultValues) {
		// checks if default value is consistent
		return Checker.checkAndGetIfValid(defaultValues, "Default values argument");
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	protected final NativeObject getNativeObject() {
		return nativeObject;
	}

	/**
	 * Returns the native object instance inside an envelop.<br>
	 * It can be called only from <code>org.pepstock.charba.client</code> package.
	 * 
	 * @param envelop envelop instance which will contain the native object
	 * @return the envelop, passed as argument, loaded with the native object
	 */
	public final ChartEnvelop<NativeObject> loadNativeObject(ChartEnvelop<NativeObject> envelop) {
		// checks if envelop is consistent
		Envelop.checkAndGetIfValid(envelop);
		// load native object to envelop
		envelop.setContent(nativeObject);
		// returns the envelop passed as argument
		return envelop;
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	public final String toJSON() {
		return JSON.stringifyWithReplacer(nativeObject, 3);
	}

	/**
	 * Returns <code>true</code> if there is at least a property, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is at least a property, otherwise <code>false</code>.
	 */
	protected final boolean empty() {
		// gets the keys of properties of th native object
		ArrayString keys = NativeUtil.keys(nativeObject);
		// if the array of keys is consistent
		if (keys != null) {
			// returns if is empty
			return keys.isEmpty();
		}
		// if here, array of keys if not consistent
		// then returns always true
		return true;
	}

	/**
	 * Returns true if the embedded JavaScript object contains an element at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 */
	protected final boolean has(Key key) {
		// checks arguments if consistent
		// if not consistent, returns not found
		if (Key.isValid(key)) {
			return NativeObjectUtil.hasProperty(nativeObject, key.value());
		}
		// if here, key is not consistent
		return false;
	}

	/**
	 * Returns true if the embedded JavaScript object contains an element at all properties.
	 * 
	 * @param keys set of keys of the properties of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at all properties.
	 */
	protected final boolean has(Key... keys) {
		// checks arguments if consistent
		if (ArrayUtil.isNotEmpty(keys)) {
			// scans keys
			for (Key key : keys) {
				// if one is not present
				// returns false
				if (!has(key)) {
					return false;
				}
			}
			return true;
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Returns the list of properties names of the object.
	 * 
	 * @return the list of properties names of the object.
	 */
	protected final List<Key> keys() {
		// creates the result
		List<Key> keys = new ArrayList<>();
		// scans all properties names of object
		for (String key : NativeObjectUtil.propertiesKeys(nativeObject)) {
			// checks to avoid the hash number
			if (!key.equals(NativeObjectHashing.HASH_CODE_PROPERTY)) {
				// adds a key object by name of the property
				keys.add(Key.create(key));
			}
		}
		return keys;
	}

	/**
	 * Returns the java script type of the property.
	 * 
	 * @param key name of the java script property.
	 * @return the java script type of the property.
	 */
	protected final ObjectType type(Key key) {
		// checks arguments if consistent
		// if not consistent, returns undefined
		return Key.isValid(key) ? JsHelper.get().typeOf(nativeObject, key.value()) : ObjectType.UNDEFINED;
	}

	/**
	 * Returns <code>true</code> if the type of the property is equals to one of the object types passed as argument.
	 * 
	 * @param key name of the java script property.
	 * @param types types to check against the type of the property.
	 * @return the java script type of the property.
	 */
	public final boolean isType(Key key, ObjectType... types) {
		// checks arguments if consistent
		if (ArrayUtil.isNotEmpty(types)) {
			// gets property type
			ObjectType propertyType = type(key);
			// scans arguments
			for (ObjectType type : types) {
				// gets the property type and checks against the argument
				// checks of equals
				if (type.equals(propertyType)) {
					// find it!
					return true;
				}
			}
		}
		// if arguments not consistent or not found, returns false
		return false;
	}

	/**
	 * Removes an element (by key) from the embedded JavaScript object.
	 * 
	 * @param key key of the property of JavaScript object.
	 */
	protected final void remove(Key key) {
		// checks arguments if consistent
		// if not consistent, do nothing
		if (Key.isValid(key)) {
			NativeObjectUtil.removeProperty(nativeObject, key.value());
		}
	}

	/**
	 * Removes a set of elements (by keys) from the embedded JavaScript object.
	 * 
	 * @param keys set of keys of the properties of JavaScript object.
	 */
	protected final void remove(Key... keys) {
		// checks arguments if consistent
		if (ArrayUtil.isNotEmpty(keys)) {
			// scans all keys
			for (Key key : keys) {
				// removes if exists
				remove(key);
			}
		}
	}

	// ------------------------------------------
	// --- INTEGERS
	// ------------------------------------------
	/**
	 * Sets a value (int) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, int value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		NativeObjectUtil.defineIntProperty(nativeObject, key.value(), value);
	}

	/**
	 * Returns a value (int) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final int getValue(Key key, int defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getIntProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (Array or integer) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of integers to be set
	 */
	protected final void setValueOrArray(Key key, int... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayInteger.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single number value
	 * @return value of the property (by array)
	 */
	protected final ArrayInteger getValueOrArray(Key key, int defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.NUMBER.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayInteger.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist or has got a wrong type
		return ArrayInteger.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- DOUBLES
	// ------------------------------------------
	/**
	 * Sets a value (double) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, double value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		NativeObjectUtil.defineDoubleProperty(nativeObject, key.value(), value);
	}

	/**
	 * Returns a value (double) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final double getValue(Key key, double defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getDoubleProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (Array or double) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of doubles to be set
	 */
	protected final void setValueOrArray(Key key, double... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayDouble.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single number value
	 * @return value of the property (by array)
	 */
	protected final ArrayDouble getValueOrArray(Key key, double defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.NUMBER.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayDouble.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist or has got a wrong type
		return ArrayDouble.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- BOOLEANS
	// ------------------------------------------
	/**
	 * Sets a value (boolean) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, boolean value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		NativeObjectUtil.defineBooleanProperty(nativeObject, key.value(), value);
	}

	/**
	 * Returns a value (boolean) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final boolean getValue(Key key, boolean defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getBooleanProperty(nativeObject, key.value(), defaultValue);
	}

	// ------------------------------------------
	// --- STRINGS
	// ------------------------------------------
	/**
	 * Returns a value (string) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final String getValue(Key key, String defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getStringProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (string) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, String value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineStringProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or string) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of strings to be set
	 */
	protected final void setValueOrArray(Key key, String... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayString.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single string value
	 * @return value of the property (by array)
	 */
	protected final ArrayString getValueOrArray(Key key, String defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.STRING.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayString.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist
		// returns default
		return ArrayString.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- DATES
	// ------------------------------------------
	/**
	 * Returns a value (date) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final Date getValue(Key key, Date defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.NUMBER.equals(type)) {
			// gets descriptor
			double value = NativeObjectUtil.getDoubleProperty(nativeObject, key.value(), Undefined.DOUBLE);
			// checks if value is consistent
			if (Undefined.isNot(value)) {
				// creates and returns a date
				return new ImmutableDate((long) value);
			}
		}
		// if here the value is not consistent for a date
		// returns default value
		return defaultValue;
	}

	/**
	 * Sets a value (date) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, Date value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// stores date as double
			setValue(key, value.getTime());
		}
	}

	// ------------------------------------------
	// --- NATIVE OBJECTS
	// ------------------------------------------
	/**
	 * Returns a value (JavaScript Object) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property or <code>null</code> if not there
	 */
	protected final NativeObject getValue(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return null;
		}
		// returns value
		return NativeObjectUtil.getObjectProperty(nativeObject, key.value());
	}

	/**
	 * Sets a value (JavaScript Object) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeObject value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineObjectProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (an empty JavaScript Object) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 */
	protected final void setEmptyValue(Key key) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		// sets value
		NativeObjectUtil.defineObjectProperty(nativeObject, key.value(), NativeObjectUtil.create());
	}

	// ------------------------------------------
	// --- NATIVE OBJECT CONTAINERS
	// ------------------------------------------
	/**
	 * Sets a value (JavaScript Object) in the embedded JavaScript object at specific property by object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeObjectContainer value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineObjectProperty(nativeObject, key.value(), value.getNativeObject());
		}
	}

	/**
	 * Sets a value (array or native object container) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a native object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values native object containers to be set
	 */
	protected final void setValueOrArray(Key key, NativeObjectContainer... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayObject.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a object.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single object value
	 * @return value of the property (by array)
	 */
	protected final ArrayObject getValueOrArray(Key key, NativeObjectContainer defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.OBJECT.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayObject.fromOrEmpty(getValue(key));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist
		// returns default
		return ArrayObject.fromOrEmpty(defaultValue.getNativeObject());
	}

	/**
	 * Sets a value (Array from a container list) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array
	 */
	protected final void setArrayValue(Key key, ArrayObjectContainerList<?> container) {
		// if value is null
		// try to remove the reference if exists
		if (container == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineArrayProperty(nativeObject, key.value(), container.getArray());
		}
	}

	// ------------------------------------------
	// --- NATIVE ARRAY CONTAINERS
	// ------------------------------------------
	/**
	 * Sets a value (JavaScript Object) in the embedded JavaScript object at specific property by array container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeArrayContainer<?> value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineArrayProperty(nativeObject, key.value(), value.getNativeArray());
		}
	}

	/**
	 * Sets a value (Array from a double array container list) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array of doubles
	 */
	protected final void setArrayValue(Key key, ArrayDoubleArrayList<?> container) {
		// if value is null
		// try to remove the reference if exists
		if (container == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineArrayProperty(nativeObject, key.value(), container.getArray());
		}
	}

	// ------------------------------------------
	// --- CALLBACKS
	// ------------------------------------------
	/**
	 * Sets a value (callback proxy function) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CallbackProxy.Proxy value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineCallbackProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (native callback function) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeCallback value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineCallbackProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (native hook function for plugin) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeHook value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineCallbackProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (native interpolator function for animations) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeInterpolator value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineInterpolatorProperty(nativeObject, key.value(), value);
		}
	}

	// ------------------------------------------
	// --- CANVAS
	// ------------------------------------------
	/**
	 * Returns a value (canvas) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final Canvas getValue(Key key, Canvas defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getCanvasProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (canvas) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, Canvas value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineCanvasProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (array or canvas) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values images to be set
	 */
	protected final void setValueOrArray(Key key, Canvas... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayCanvas.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a canvas.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single canvas value
	 * @return value of the property (by array)
	 */
	protected final ArrayCanvas getValueOrArray(Key key, Canvas defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.OBJECT.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayCanvas.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// returns default array
		return ArrayCanvas.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- IMAGES
	// ------------------------------------------
	/**
	 * Returns a value (image) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final Img getValue(Key key, Img defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getImageProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (image) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, Img value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineImageProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (array or image) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values images to be set
	 */
	protected final void setValueOrArray(Key key, Img... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayImage.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single image value
	 * @return value of the property (by array)
	 */
	protected final ArrayImage getValueOrArray(Key key, Img defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.OBJECT.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayImage.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// returns default array
		return ArrayImage.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- GRADIENTS
	// ------------------------------------------
	/**
	 * Returns a value (gradient) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final CanvasGradientItem getValue(Key key, CanvasGradientItem defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getGradientProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (gradient) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CanvasGradientItem value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineGradientProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or gradient) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a gradient.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values gradients to be set
	 */
	protected final void setValueOrArray(Key key, CanvasGradientItem... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayGradient.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a gradient.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single gradient value
	 * @return value of the property (by array) or an empty array if not exist
	 */
	protected final ArrayGradient getValueOrArray(Key key, CanvasGradientItem defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.OBJECT.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayGradient.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// returns default array
		return ArrayGradient.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- PATTERNS
	// ------------------------------------------
	/**
	 * Returns a value (pattern) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final CanvasPatternItem getValue(Key key, CanvasPatternItem defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// returns value
		return NativeObjectUtil.getPatternProperty(nativeObject, key.value(), defaultValue);
	}

	/**
	 * Sets a value (pattern) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CanvasPatternItem value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.definePatternProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or pattern) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a pattern.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values patterns to be set
	 */
	protected final void setValueOrArray(Key key, CanvasPatternItem... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayPattern.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a pattern.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single pattern value
	 * @return value of the property (by array)
	 */
	protected final ArrayPattern getValueOrArray(Key key, CanvasPatternItem defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.OBJECT.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayPattern.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist or has got a wrong type
		return ArrayPattern.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- KEYS
	// ------------------------------------------
	/**
	 * Returns a value (string) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value as key if the property is missing
	 * @return value of the property
	 */
	protected final String getValue(Key key, Key defaultValue) {
		// checks consistency of default value
		Key.checkIfValid(defaultValue);
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue.value();
		}
		// gets the string value
		return getValue(key, defaultValue.value());
	}

	/**
	 * Sets a value (EnumValue) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of key
	 */
	protected final <T extends Key> void setValue(Key key, T value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// checks if the value is consistent
			// if not, exception
			Key.checkIfValid(value);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineStringProperty(nativeObject, key.value(), value.value());
		}
	}

	// ------------------------------------------
	// --- Special case for ScaleId
	// ------------------------------------------
	/**
	 * Returns a value (ScaleId) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value as key if the property is missing
	 * @return value of the property
	 */
	protected final ScaleId getValue(Key key, ScaleId defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// checks consistency of default value
		Key.checkIfValid(defaultValue);
		// gets the string value
		// checks and gets the scale id
		return ScaleId.checkAndGetScaleID(getValue(key, defaultValue.value()), defaultValue);
	}

	// ------------------------------------------
	// --- ENUMERATIONS
	// ------------------------------------------
	/**
	 * Returns a value (key) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param enumValues all enumeration values
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of key
	 * @return value of the property
	 */
	protected final <T extends Key> T getValue(Key key, T[] enumValues, T defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// checks the default consistency
		if (Key.isValid(defaultValue)) {
			// gets the string value
			String value = getValue(key, defaultValue.value());
			// gets the key by value
			return Key.getKeyByValue(enumValues, value, defaultValue);
		} else {
			// if here, the default not consistent
			// gets the key by value by null as default
			return Key.getKeyByValue(enumValues, getValue(key, Undefined.STRING));
		}
	}

	/**
	 * Sets a value (Array or string by keys) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values value of keys to be set
	 */
	protected final void setValueOrArray(Key key, Key... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayString.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a key.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single key value
	 * @return value of the property (by array) or <code>null</code> if not exist
	 */
	protected final ArrayString getValueOrArray(Key key, Key defaultValue) {
		// the same logic as a string
		// checks if default value is consistent
		return getValueOrArray(key, Key.isValid(defaultValue) ? defaultValue.value() : null);
	}

	// ------------------------------------------
	// --- ARRAYS
	// ------------------------------------------
	/**
	 * Returns a value (array) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param <T> type of array
	 * @return value of the property or <code>null</code> if not exist
	 */
	protected final <T extends Array> T getArrayValue(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns null
			return null;
		}
		// returns value
		return NativeObjectUtil.getArrayProperty(nativeObject, key.value());
	}

	/**
	 * Sets a value (Array) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of array
	 */
	protected final <T extends Array> void setArrayValue(Key key, T value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineArrayProperty(nativeObject, key.value(), value);
		}
	}

	// ------------------------------------------
	// --- COLORS
	// ------------------------------------------
	/**
	 * Sets a value (Array or string by colors) in the embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of colors to be set
	 */
	protected final void setValueOrArray(Key key, IsColor... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// checks color consistent
				IsColor.checkIfValid(values[0]);
				// if 1 element, sets the object
				setValue(key, values[0].toRGBA());
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayString.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			remove(key);
		}
	}

	// ------------------------------------------
	// --- CHART
	// ------------------------------------------

	/**
	 * Sets a value (chart) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, Chart value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineChartProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Returns a value (chart) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	protected final Chart getNativeChart(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns null
			return null;
		}
		// returns value
		return NativeObjectUtil.getChartProperty(nativeObject, key.value());
	}

	// ------------------------------------------
	// --- ELEMENT
	// ------------------------------------------

	/**
	 * Sets a value (BaseHtmlElement) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setElement(Key key, BaseHtmlElement value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineElementProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Returns a value (BaseHtmlElement) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	protected final BaseHtmlElement getElement(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns null
			return null;
		}
		// returns value
		return NativeObjectUtil.getElementProperty(nativeObject, key.value());
	}

	// ------------------------------------------
	// --- EVENT
	// ------------------------------------------

	/**
	 * Sets a value (event) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeBaseEvent value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			remove(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			NativeObjectUtil.defineEventProperty(nativeObject, key.value(), value);
		}
	}

	/**
	 * Returns a value (native event) in the embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property
	 */
	protected final NativeBaseEvent getNativeEvent(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns null
			return null;
		}
		// returns value
		return NativeObjectUtil.getEventProperty(nativeObject, key.value());
	}

	// ------------------------------------------
	// --- UTILITIES for properties which can have
	// --- different types
	// ------------------------------------------

	/**
	 * Returns the value of a property checking if the type of current value is a STRING.
	 * 
	 * @param key key of the property of JavaScript object
	 * @param defaultsValue default value if the value was stored as single key value
	 * @return value of the property
	 */
	protected final String getValueForMultipleKeyTypes(Key key, String defaultsValue) {
		// checks if key is consistent
		// and if is a string
		if (isType(key, ObjectType.STRING)) {
			return getValue(key, defaultsValue);
		}
		// the property is not a string
		// then returns undefined value
		return defaultsValue;
	}

	/**
	 * Returns the value of a property checking if the type of current value is a NUMBER.
	 * 
	 * @param key key of the property of JavaScript object
	 * @param defaultsValue default value if the value was stored as single key value
	 * @return value of the property
	 */
	protected final double getValueForMultipleKeyTypes(Key key, double defaultsValue) {
		// checks if key is consistent
		// and if is a number
		if (isType(key, ObjectType.NUMBER)) {
			return getValue(key, defaultsValue);
		}
		// the property is not a number
		// then returns undefined value
		return defaultsValue;
	}

	/**
	 * Returns the value of a property checking if the type of current value is a OBJECT (as a date).
	 * 
	 * @param key key of the property of JavaScript object
	 * @param defaultsValue default value if the value was stored as single key value
	 * @return value of the property
	 */
	protected final Date getValueForMultipleKeyTypes(Key key, Date defaultsValue) {
		// gets date value as number
		double value = getValueForMultipleKeyTypes(key, Double.NaN);
		// checks if number value is consistent with a date
		if (Undefined.isNot(value)) {
			// creates and returns a date
			return new ImmutableDate((long) value);
		}
		// the property is not a number
		// then returns undefined value
		return defaultsValue;
	}

	// ------------------------------------------
	// --- UTILITIES for unique id
	// ------------------------------------------

	/**
	 * Stores new incremental id if not previously stored.
	 */
	protected final void setNewIncrementalId() {
		setNewIncrementalId(null);
	}

	/**
	 * Stores new incremental id if not previously stored.
	 * 
	 * @param prefix prefix to add to the value of incremental id.
	 */
	protected final void setNewIncrementalId(String prefix) {
		IncrementalIdHandler.get().checkAndSetId(this, prefix);
	}

	/**
	 * Returns the incremental id of the object.
	 * 
	 * @return the incremental id of the object.
	 */
	protected final String getIncrementalId() {
		return IncrementalIdHandler.get().getId(this);
	}

	/**
	 * Checks the color passed as argument and returns {@link IsColor} instance.
	 * 
	 * @param color string color to be parsed.
	 * @return {@link IsColor} instance of the string color or <code>null</code> if not consistent
	 */
	protected IsColor checkAndGetColor(String color) {
		// checks if consistent
		if (color != null && color.length() > 0) {
			return ColorBuilder.parse(color);
		}
		// if here the color is not consistent
		return null;
	}
}