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
package org.pepstock.charba.client.jsinterop.commons;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;

/**
 * Utility to create array list objects based on GWT JavaScript objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ArrayListHelper {

	/**
	 * To avoid any instantiation
	 */
	private ArrayListHelper() {
		// nothing
	}

	/**
	 * Creates a JavaScript array list of doubles.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	public static ArrayDoubleList list(ArrayDouble values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		return new ArrayDoubleList(values);
	}

	/**
	 * Creates a JavaScript array list of integers.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsIntegerArrayList
	 */
	public static ArrayIntegerList list(ArrayInteger values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		return new ArrayIntegerList(values);
	}

	/**
	 * Creates a JavaScript array list of strings.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	public static ArrayStringList list(ArrayString values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		return new ArrayStringList(values);
	}
	
	/**
	 * Creates a JavaScript array list of strings.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	public static ArrayStringList list(IsColor... values){
		// creates the list
		ArrayStringList result = new ArrayStringList();
		// checks if array is null
		if (values == null){
			return result;
		}
		for (IsColor color : values) {
			// adds all elements
			result.addAll(color.toRGBA());
		}
		// returns the list
		return result;
	}

	/**
	 * Creates a JavaScript array list of EnumValues.
	 * @param clazz Enum class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 */
	public static <E extends Key> ArrayEnumList<E> list(Class<E> clazz, E[] values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		ArrayEnumList<E> result = new ArrayEnumList<E>(clazz);
		// adds all elements
		result.addAll(values);
		// returns the list
		return result;
	}

	/**
	 * Creates a JavaScript array list of EnumValues.
	 * @param clazz Enum class with all possible values of enumeration
	 * @param list a string array list 
	 * @param <E> type of key
	 * @return a array list of values  or <code>null</code> if the list is null.
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	public static <E extends Key> ArrayEnumList<E> list(Class<E> clazz, ArrayString array){
		// checks if array is null
		if (array == null){
			return null;
		}
		// returns the list adding the string array list to initialize it
		return new ArrayEnumList<E>(clazz.getEnumConstants(), array);
	}
	
	/**
	 * Creates a JavaScript array list of generic javaScript objects.
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of java script object
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.GenericJavaScriptObject
	 * @see org.pepstock.charba.client.commons.JsObjectArrayList
	 */
	public static <E extends NativeObject> ArrayObjectList<E> list(ArrayObject<E> values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		return new ArrayObjectList<E>(values);
	}

	/**
	 * FIXME
	 * Loads an existing list of JavaScript object container.
	 * @param container list instance of JavaScript object container
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return the instance of updated list
	 * @see org.pepstock.charba.client.commons.AbstractList
	 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
	 */
	public static <E extends NativeObjectContainer<O>, O extends NativeObject> ArrayObjectContainerList<E, O> list(ArrayObject<O> array, Factory<E, O> factory){
		// checks if array is null
		if (array == null){
			return null;
		}
		return new ArrayObjectContainerList<>(array, factory);
	}
	
	/**
	 * Creates a JavaScript array list of doubles.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsDoubleArrayList
	 */
	public static List<Double> unmodifiableList(ArrayDouble values){
		return unmodifiableList(list(values));
	}

	/**
	 * Creates a JavaScript array list of integers.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsIntegerArrayList
	 */
	public static List<Integer> unmodifiableList(ArrayInteger values){
		return unmodifiableList(list(values));
	}

	/**
	 * Creates a JavaScript array list of strings.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	public static List<String> unmodifiableList(ArrayString values){
		return unmodifiableList(list(values));
	}
	
	/**
	 * Creates a JavaScript array list of EnumValues.
	 * @param clazz Enum class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 */
	public static <E extends Key> List<E> unmodifiableList(Class<E> clazz, E[] values){
		return unmodifiableList(list(clazz, values));
	}

	/**
	 * Creates a JavaScript array list of EnumValues.
	 * @param clazz Enum class with all possible values of enumeration
	 * @param list a string array list 
	 * @param <E> type of key
	 * @return a array list of values  or <code>null</code> if the list is null.
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	public static <E extends Key> List<E> unmodifiableList(Class<E> clazz, ArrayString array){
		return unmodifiableList(list(clazz, array));
	}
	
	/**
	 * Creates a JavaScript array list of generic javaScript objects.
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of java script object
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.GenericJavaScriptObject
	 * @see org.pepstock.charba.client.commons.JsObjectArrayList
	 */
	public static <E extends NativeObject> List<E> unmodifiableList(ArrayObject<E> values){
		return unmodifiableList(list(values));
	}

	/**
	 * FIXME
	 * Loads an existing list of JavaScript object container.
	 * @param container list instance of JavaScript object container
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return the instance of updated list
	 * @see org.pepstock.charba.client.commons.AbstractList
	 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
	 */
	public static <E extends NativeObjectContainer<O>, O extends NativeObject> List<E> unmodifiableList(ArrayObject<O> array, Factory<E, O> factory){
		return unmodifiableList(list(array, factory));
	}
	
	/**
	 * FIXME
	 * @param list
	 * @return
	 */
	private static <E> List<E> unmodifiableList(List<E> list){
		// checks if null
		return  list == null ? null : Collections.unmodifiableList(list);
	}
}