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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.BorderItemType;

/**
 * Utility class which can provide methods to manage properties related to borders of the element.<br>
 * Some options could be border width and border radius.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class BorderItemsHandler extends NativeObjectContainer {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	BorderItemsHandler(NativeObject nativeObject) {
		super(nativeObject);
	}

	// ----------------------
	// METHODS for JSFunction
	// ----------------------

	/**
	 * Returns an object (integer or {@link AbstractBarBorderItem}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderItemCallback the border item callback to invoke
	 * @param factory factory to crate the border item objects
	 * @param defaultValue default value for this border item.
	 * @param <T> type of border item object
	 * @return a object property value, as integer or {@link AbstractBarBorderItem}
	 */
	<T extends AbstractBarBorderItem> NativeObject onBorderItem(DatasetContext context, Scriptable<Object, DatasetContext> borderItemCallback, NativeObjectContainerFactory<T> factory, int defaultValue) {
		// gets value
		Object value = ScriptableUtil.getOptionValue(context, borderItemCallback);
		// checks the type of result
		if (value instanceof AbstractBarBorderItem) {
			// casts to object
			AbstractBarBorderItem result = (AbstractBarBorderItem) value;
			// returns the native object
			return result.nativeObject();
		} else if (value instanceof Number) {
			// casts to number
			Number number = (Number) value;
			// creates a border item object
			T result = factory.create();
			// sets value
			result.set(Checker.positiveOrDefault(number.intValue(), defaultValue));
			// returns the native object
			return result.nativeObject();
		}
		// creates a border item object
		T result = factory.create();
		// sets value
		result.set(defaultValue);
		// if here, the value of callback is not consistent
		// returns the default value
		return result.nativeObject();
	}

	// ---------------------
	// COMMON BORDER ITEM METHODS
	// ---------------------

	/**
	 * Sets the bar border size by an array of integers.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param array the bar border array of integers.
	 */
	void setBorderItem(Key property, Key propertyType, int... array) {
		// stores value
		setValueOrArray(property, array);
		// stores the type depending on if the property exist
		// if property does not exist means that the argument of this method is null
		setValue(propertyType, has(property) ? BorderItemType.INTEGERS : BorderItemType.UNKNOWN);
	}

	/**
	 * Sets the bar border item size by an array of objects.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param array the bar border item array of objects
	 * @param <T> type of border item object
	 */
	<T extends AbstractBarBorderItem> void setBorderItem(Key property, Key propertyType, T[] array) {
		// stores value
		setValueOrArray(property, array);
		// stores the type depending on if the property exist
		// if property does not exist means that the argument of this method is null
		setValue(propertyType, has(property) ? BorderItemType.OBJECTS : BorderItemType.UNKNOWN);
	}

	/**
	 * Sets the bar border item size by a list of objects.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param list the bar border item list.
	 * @param array array with length 0 in order to get all list elements in the an array
	 * @param <T> type of border item object
	 */
	<T extends AbstractBarBorderItem> void setBorderItem(Key property, Key propertyType, List<T> list, T[] array) {
		// checks if list is consistent
		if (ArrayListHelper.isConsistent(list)) {
			setBorderItem(property, propertyType, list.toArray(array));
		} else {
			// removes key
			remove(property);
			// resets the type of border item
			setValue(propertyType, BorderItemType.UNKNOWN);
		}
	}

	/**
	 * Returns the list of bar border item size (in pixels).
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param defaultValue default value for this border item.
	 * @return the list of bar border item size (in pixels).
	 */
	List<Integer> getBorderItem(Key property, Key propertyType, int defaultValue) {
		// gets object type
		ObjectType type = type(property);
		// gets border item type
		BorderItemType borderWidthType = getValue(propertyType, BorderItemType.values(), BorderItemType.UNKNOWN);
		// checks if the callback has not been set and is not an object (border item object
		// set by bar dataset) and if the array as stored as integers
		if (!ObjectType.FUNCTION.equals(type) && !ObjectType.OBJECT.equals(type) && BorderItemType.INTEGERS.equals(borderWidthType)) {
			// returns the array
			ArrayInteger array = getValueOrArray(property, defaultValue);
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the list of bar border item size (in pixels).
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param factory factory to crate the border item objects
	 * @param defaultValue default value for this border item.
	 * @param <T> type of border item object
	 * @return the list of bar border item size (in pixels).
	 */
	<T extends AbstractBarBorderItem> List<T> getBorderItemAsObjects(Key property, Key propertyType, NativeObjectContainerFactory<T> factory, int defaultValue) {
		// gets object type
		ObjectType type = type(property);
		// checks if borer item has been set by an object
		if (ObjectType.OBJECT.equals(type)) {
			// returns the array
			return Arrays.asList(factory.create(getValue(property)));
		} else if (ObjectType.NUMBER.equals(type)) {
			// checks if borer item has been set by an object
			// if here, is not a bar border item object
			// then creates new border item
			T borderItem = factory.create();
			// reads number and set to object
			borderItem.set(getValue(property, defaultValue));
			// returns the array
			return Arrays.asList(borderItem);
		} else if (ObjectType.ARRAY.equals(type)) {
			// gets border item type
			BorderItemType borderItemType = getValue(propertyType, BorderItemType.values(), BorderItemType.UNKNOWN);
			// checks if the array is an array of objects or integers
			if (BorderItemType.OBJECTS.equals(borderItemType)) {
				// checks if border item has been set by an array
				ArrayObject array = getArrayValue(property);
				// returns the list
				return ArrayListHelper.list(array, factory);
			} else if (BorderItemType.INTEGERS.equals(borderItemType)) {
				// returns the array
				ArrayInteger array = getArrayValue(property);
				// creates the result instance
				List<T> result = new LinkedList<>();
				// scans the array creating an border item objects
				for (int i = 0; i < array.length(); i++) {
					// creates the object
					T borderItem = factory.create();
					// stores the border item value
					borderItem.set(array.get(i));
					// adds to the result instance
					result.add(borderItem);
				}
				// returns the
				return result;
			}
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the border item callback.
	 * 
	 * @param property property to use to store the values
	 * @param borderItemCallback the border item callback to set
	 * @param proxy callback proxy to set
	 */
	void setBorderItemCallback(Key property, Object borderItemCallback, Proxy proxy) {
		setBorderItemCallback(property, null, borderItemCallback, proxy);
	}

	/**
	 * Sets the border item callback.
	 * 
	 * @param property property to use to store the values
	 * @param borderItemCallback the border item callback to set
	 */
	void setBorderItemCallback(Key property, NativeCallback borderItemCallback) {
		setBorderItemCallback(property, null, borderItemCallback);
	}

	/**
	 * Sets the border item callback.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param borderItemCallback the border item callback to set
	 * @param proxy callback proxy to set
	 */
	void setBorderItemCallback(Key property, Key propertyType, Object borderItemCallback, Proxy proxy) {
		// checks if callback is consistent
		if (borderItemCallback != null) {
			// adds the callback proxy function to java script object
			setValue(property, proxy);
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(property);
		}
		// checks if property type is valid
		if (Key.isValid(propertyType)) {
			// resets the flag about border with type
			setValue(propertyType, BorderItemType.UNKNOWN);
		}
	}

	/**
	 * Sets the border item callback.
	 * 
	 * @param property property to use to store the values
	 * @param propertyType property to use to store the type
	 * @param borderItemCallback the border item callback to set
	 */
	void setBorderItemCallback(Key property, Key propertyType, NativeCallback borderItemCallback) {
		// checks if callback is consistent
		if (borderItemCallback != null) {
			// adds the callback proxy function to java script object
			setValue(property, borderItemCallback);
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(property);
		}
		// checks if property type is valid
		if (Key.isValid(propertyType)) {
			// resets the flag about border with type
			setValue(propertyType, BorderItemType.UNKNOWN);
		}
	}

}