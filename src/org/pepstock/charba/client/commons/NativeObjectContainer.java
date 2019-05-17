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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.utils.JSON;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.ImageElement;

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
		this(new NativeObject());
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected NativeObjectContainer(NativeObject nativeObject) {
		this.nativeObject = (nativeObject == null ? new NativeObject() : nativeObject);
	}

	// ------------------------------------------
	// --- COMMONS
	// ------------------------------------------
	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	protected final NativeObject getNativeObject() {
		return nativeObject;
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
	 * Returns true if the embedded JavaScript object contains an element at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 */
	protected final boolean has(Key key) {
		// checks arguments if consistent
		// if not consistent, returns not found
		if (Key.isValid(key)) {
			return nativeObject.hasProperty(key.value());
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
		if (keys != null && keys.length > 0) {
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
		for (String key : nativeObject.propertyKeys()) {
			// adds a key object by name of the property
			keys.add(new StandardKey(key));
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
	 * Removes an element (by key) from the embedded JavaScript object if exists.
	 * 
	 * @param key key of the property of JavaScript object.
	 */
	protected final void removeIfExists(Key key) {
		// checks if there is
		if (has(key)) {
			// and then remove
			remove(key);
		}
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
			nativeObject.removeProperty(key.value());
		}
	}

	/**
	 * Removes a set of elements (by keys) from the embedded JavaScript object.
	 * 
	 * @param keys set of keys of the properties of JavaScript object.
	 */
	protected final void remove(Key... keys) {
		// checks arguments if consistent
		if (keys != null && keys.length > 0) {
			// scans all keys
			for (Key key : keys) {
				// removes if exists
				removeIfExists(key);
			}
		}
	}

	// ------------------------------------------
	// --- INTEGERS
	// ------------------------------------------
	/**
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, int value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		nativeObject.defineIntProperty(key.value(), value);
	}

	/**
	 * Returns a value (int) into embedded JavaScript object at specific property.
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
		// gets descriptor
		NativeIntegerDescriptor descriptor = nativeObject.getIntProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/**
	 * Sets a value (Array or integer) into embedded JavaScript object at specific property.<br>
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
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
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, double value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		nativeObject.defineDoubleProperty(key.value(), value);
	}

	/**
	 * Returns a value (double) into embedded JavaScript object at specific property.
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
		// gets descriptor
		NativeDoubleDescriptor descriptor = nativeObject.getDoubleProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/**
	 * Sets a value (Array or double) into embedded JavaScript object at specific property.<br>
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
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
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, boolean value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		nativeObject.defineBooleanProperty(key.value(), value);
	}

	/**
	 * Returns a value (boolean) into embedded JavaScript object at specific property.
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
		// gets descriptor
		NativeBooleanDescriptor descriptor = nativeObject.getBooleanProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	// ------------------------------------------
	// --- STRINGS
	// ------------------------------------------
	/**
	 * Returns a value (string) into embedded JavaScript object at specific property.
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
		// gets descriptor
		NativeStringDescriptor descriptor = nativeObject.getStringProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/**
	 * Sets a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, String value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineStringProperty(key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or string) into embedded JavaScript object at specific property.<br>
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
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
	 * Returns a value (date) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final Date getValue(Key key, Date defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		NativeDateDescriptor descriptor = nativeObject.getDateProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : new Date((long) descriptor.getValue().getTime());
	}

	/**
	 * Sets a value (date) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, Date value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineDateProperty(key.value(), JsDate.create((double) value.getTime()));
		}
	}

	// ------------------------------------------
	// --- NATIVE OBJECTS
	// ------------------------------------------
	/**
	 * Returns a value (JavaScript Object) into embedded JavaScript object at specific property.
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
		// gets descriptor
		NativeObjectDescriptor descriptor = nativeObject.getObjectProperty(key.value());
		// returns value
		return descriptor == null ? null : descriptor.getValue();
	}

	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeObject value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineObjectProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- NATIVE OBJECT CONTAINERS
	// ------------------------------------------
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property by object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeObjectContainer value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineObjectProperty(key.value(), value.getNativeObject());
		}
	}

	/**
	 * Sets a value (Array from a container list) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param container container of array
	 */
	protected final void setArrayValue(Key key, ArrayObjectContainerList<?> container) {
		// if value is null
		// try to remove the reference if exists
		if (container == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineArrayProperty(key.value(), container.getArray());
		}
	}

	// ------------------------------------------
	// --- CALLBACKS
	// ------------------------------------------
	/**
	 * Sets a value (callback proxy function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CallbackProxy.Proxy value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineCallbackProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- IMAGES
	// ------------------------------------------
	/**
	 * Returns a value (image) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final ImageElement getValue(Key key, ImageElement defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		NativeImageDescriptor descriptor = nativeObject.getImageProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/**
	 * Sets a value (image) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, ImageElement value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineImageProperty(key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or image) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values images to be set
	 */
	protected final void setValueOrArray(Key key, ImageElement... values) {
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a image.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single image value
	 * @return value of the property (by array)
	 */
	protected final ArrayImage getValueOrArray(Key key, ImageElement defaultValue) {
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
	 * Returns a value (gradient) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final CanvasGradient getValue(Key key, CanvasGradient defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		NativeGradientDescriptor descriptor = nativeObject.getGradientProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/**
	 * Sets a value (gradient) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CanvasGradient value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineGradientProperty(key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or gradient) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a gradient.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values gradients to be set
	 */
	protected final void setValueOrArray(Key key, CanvasGradient... values) {
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a gradient.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single gradient value
	 * @return value of the property (by array) or an empty array if not exist
	 */
	protected final ArrayGradient getValueOrArray(Key key, CanvasGradient defaultValue) {
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
	 * Returns a value (pattern) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final CanvasPattern getValue(Key key, CanvasPattern defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		NativePatternDescriptor descriptor = nativeObject.getPatternProperty(key.value());
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/**
	 * Sets a value (pattern) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CanvasPattern value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.definePatternProperty(key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or pattern) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a pattern.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values patterns to be set
	 */
	protected final void setValueOrArray(Key key, CanvasPattern... values) {
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a pattern.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single pattern value
	 * @return value of the property (by array)
	 */
	protected final ArrayPattern getValueOrArray(Key key, CanvasPattern defaultValue) {
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
	// --- ENUMERATIONS
	// ------------------------------------------
	/**
	 * Returns a value (key) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param clazz class of object to get all enumeration values
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of key
	 * @return value of the property
	 */
	protected final <T extends Key> T getValue(Key key, Class<T> clazz, T defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// checks consistency of default value
		Key.checkIfValid(defaultValue);
		// gets the string value
		String value = getValue(key, defaultValue.value());
		// gets the key by value
		return Key.getKeyByValue(clazz, value, defaultValue);
	}

	/**
	 * Sets a value (EnumValue) into embedded JavaScript object at specific property.
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
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// checks if the value is consistent
			// if not, exception
			Key.checkIfValid(value);
			// if here, key is consistent
			// sets value
			nativeObject.defineStringProperty(key.value(), value.value());
		}
	}

	/**
	 * Sets a value (Array or string by keys) into embedded JavaScript object at specific property.<br>
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
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
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
	 * Returns a value (array) into embedded JavaScript object at specific property.
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
		// gets descriptor
		NativeArrayDescriptor<T> descriptor = nativeObject.getArrayProperty(key.value());
		// returns value
		return descriptor == null ? null : descriptor.getValue();
	}

	/**
	 * Sets a value (Array) into embedded JavaScript object at specific property.
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
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			nativeObject.defineArrayProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- COLORS
	// ------------------------------------------
	/**
	 * Sets a value (Array or string by colors) into embedded JavaScript object at specific property.<br>
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
				// if 1 element, sets the object
				setValue(key, values[0].toRGBA());
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayString.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			removeIfExists(key);
		}
	}

	/**
	 * Checks a color value if consistent, returning the RGBA representation, otherwise <code>null</code>.
	 * 
	 * @param value value to be set
	 * @return the string RGBA color representation or <code>null</code> if color is null
	 */
	protected final String checkValue(IsColor value) {
		// check value is null
		// and sets value accordingly
		return value != null ? value.toRGBA() : null;
	}
}
