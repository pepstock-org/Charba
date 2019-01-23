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
package org.pepstock.charba.client.commons;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * Utility to create array list objects from java script arrays.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ArrayListHelper {

	/**
	 * To avoid any instantiation
	 */
	private ArrayListHelper() {
		// nothing
	}

	/**
	 * Creates a array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance or <code>null</code> if the array is null.
	 */
	public static ArrayDoubleList list(ArrayDouble values) {
		// checks if array is null
		if (values == null) {
			return null;
		}
		// creates the list
		return new ArrayDoubleList(values);
	}

	/**
	 * Creates a array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance or <code>null</code> if the array is null.
	 */
	public static ArrayIntegerList list(ArrayInteger values) {
		// checks if array is null
		if (values == null) {
			return null;
		}
		// creates the list
		return new ArrayIntegerList(values);
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the array is null.
	 */
	public static ArrayStringList list(ArrayString values) {
		// checks if array is null
		if (values == null) {
			return null;
		}
		// creates the list
		return new ArrayStringList(values);
	}

	/**
	 * Creates a array list of strings by an array of colors (instance of {@link org.pepstock.charba.client.colors.IsColor}).
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the array is null.
	 */
	public static ArrayStringList list(IsColor... values) {
		// creates the list
		ArrayStringList result = new ArrayStringList();
		// checks if array is null
		if (values == null) {
			return result;
		}
		// scans all colors
		for (IsColor color : values) {
			// adds RGBA value as element
			result.add(color.toRGBA());
		}
		// returns the list
		return result;
	}

	/**
	 * Creates a array list of enumeration values (instance of {@link org.pepstock.charba.client.commons.Key}).
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the array is null.
	 */
	public static <E extends Key> ArrayEnumList<E> list(Class<E> clazz, E[] values) {
		// checks if array is null
		if (values == null) {
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
	 * Creates a array list of enumeration values by an java script array of strings.
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param array array of strings to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the array is null.
	 */
	public static <E extends Key> ArrayEnumList<E> list(Class<E> clazz, ArrayString array) {
		// checks if array is null
		if (array == null) {
			return null;
		}
		// returns the list adding the string array list to initialize it
		// PAY ATTENTION: no checks if the values of strings are
		// consistent with the enumeration values
		return new ArrayEnumList<E>(clazz.getEnumConstants(), array);
	}

	/**
	 * Creates a array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the array is null.
	 */
	public static ArrayObjectList list(ArrayObject values) {
		// checks if array is null
		if (values == null) {
			return null;
		}
		// creates the list
		return new ArrayObjectList(values);
	}

	/**
	 * Creates a array list of java script native object container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native object of the array.
	 * @param <E> type of native object container
	 * @return the instance of updated list or <code>null</code> if the array is null.
	 */
	public static <E extends NativeObjectContainer> ArrayObjectContainerList<E> list(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		// checks if array is null
		if (array == null) {
			return null;
		}
		// creates the list
		return new ArrayObjectContainerList<E>(array, factory);
	}

	/**
	 * Creates an unmodifiable array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance or <code>null</code> if the array is null.
	 */
	public static List<Double> unmodifiableList(ArrayDouble values) {
		return unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance or <code>null</code> if the array is null.
	 */
	public static List<Integer> unmodifiableList(ArrayInteger values) {
		return unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the array is null.
	 */
	public static List<String> unmodifiableList(ArrayString values) {
		return unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of enumeration values (instance of {@link org.pepstock.charba.client.commons.Key}).
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the array is null.
	 */
	public static <E extends Key> List<E> unmodifiableList(Class<E> clazz, E[] values) {
		return unmodifiableList(list(clazz, values));
	}

	/**
	 * Creates an unmodifiable array list of enumeration values by an java script array of strings.
	 * 
	 * @param clazz enumeration class with all possible values of enumeration
	 * @param array array of strings to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the array is null.
	 */
	public static <E extends Key> List<E> unmodifiableList(Class<E> clazz, ArrayString array) {
		return unmodifiableList(list(clazz, array));
	}

	/**
	 * Creates an unmodifiable array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the array is null.
	 */
	public static List<NativeObject> unmodifiableList(ArrayObject values) {
		return unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of java script native object container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native object of the array.
	 * @param <E> type of native object container
	 * @return the instance of updated list or <code>null</code> if the array is null.
	 */
	public static <E extends NativeObjectContainer> List<E> unmodifiableList(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		return unmodifiableList(list(array, factory));
	}

	/**
	 * Returns an unmodifiable list by another list.
	 * 
	 * @param list array list to wrap into an unmodifiable list
	 * @param <E> type of element
	 * @return an unmodifiable list or <code>null</code> if the list is null.
	 */
	private static <E> List<E> unmodifiableList(List<E> list) {
		// checks if null
		return list == null ? null : Collections.unmodifiableList(list);
	}
}