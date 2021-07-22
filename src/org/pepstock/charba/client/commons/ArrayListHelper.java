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
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;

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
	 * @return a array list of doubles instance
	 */
	public static ArrayDoubleList list(ArrayDouble values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayDoubleList(values);
	}

	/**
	 * Creates a array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance
	 */
	public static ArrayIntegerList list(ArrayInteger values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayIntegerList(values);
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayStringList list(ArrayString values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayStringList(values);
	}

	/**
	 * Creates a array list of images by a java script array of images.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of images instance
	 */
	public static ArrayImageList list(ArrayImage values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayImageList(values);
	}

	/**
	 * Creates a array list of canvas by a java script array of canvas.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of canvas instance
	 */
	public static ArrayCanvasList list(ArrayCanvas values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayCanvasList(values);
	}

	/**
	 * Creates a array list of strings by an array of colors (instance of {@link IsColor}).
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayStringList list(IsColor... values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayStringList(ArrayString.fromOrNull(values));
	}

	/**
	 * Creates a array list of enumeration values (instance of {@link Key}).
	 * 
	 * @param enumValues all possible values of an enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values
	 */
	public static <E extends Key> ArrayEnumList<E> list(E[] enumValues, E[] values) {
		// creates the list
		ArrayEnumList<E> result = new ArrayEnumList<>(enumValues);
		// checks if array is null
		if (values != null) {
			// adds all elements
			result.addAll(values);
		}
		// returns the list
		return result;
	}

	/**
	 * Creates a array list of enumeration values by an java script array of strings.
	 * 
	 * @param enumValues all possible values of an enumeration
	 * @param array array of strings to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values.
	 */
	public static <E extends Key> ArrayEnumList<E> list(E[] enumValues, ArrayString array) {
		// returns the list adding the string array list to initialize it
		// PAY ATTENTION: no checks if the values of strings are
		// consistent with the enumeration values
		return new ArrayEnumList<>(enumValues, array);
	}

	/**
	 * Creates a array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayObjectList list(ArrayObject values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayObjectList(values);
	}

	/**
	 * Creates a array list of java script native object container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native object of the array.
	 * @param <E> type of native object container
	 * @return the instance of native object containers list
	 */
	public static <E extends NativeObjectContainer> ArrayObjectContainerList<E> list(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayObjectContainerList<>(array, factory);
	}

	/**
	 * Creates a array list of java script native double array container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native array of the arrays.
	 * @param <E> type of native double array container
	 * @return the instance of native double array containers list
	 */
	public static <E extends NativeArrayContainer<ArrayDouble>> ArrayDoubleArrayList<E> list(ArrayDoubleArray array, NativeArrayContainerFactory<ArrayDouble, E> factory) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayDoubleArrayList<>(array, factory);
	}

	/**
	 * Creates an unmodifiable array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance
	 */
	public static List<Double> unmodifiableList(ArrayDouble values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance
	 */
	public static List<Integer> unmodifiableList(ArrayInteger values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static List<String> unmodifiableList(ArrayString values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of images by a java script array of images.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of images instance
	 */
	public static List<Img> unmodifiableList(ArrayImage values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of canvas by a java script array of canvas.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of canvas instance
	 */
	public static List<Canvas> unmodifiableList(ArrayCanvas values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of enumeration values (instance of {@link Key}).
	 * 
	 * @param enumValues all possible values of an enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values
	 */
	public static <E extends Key> List<E> unmodifiableList(E[] enumValues, E[] values) {
		return Collections.unmodifiableList(list(enumValues, values));
	}

	/**
	 * Creates an unmodifiable array list of enumeration values by an java script array of strings.
	 * 
	 * @param enumValues all possible values of an enumeration
	 * @param array array of strings to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values
	 */
	public static <E extends Key> List<E> unmodifiableList(E[] enumValues, ArrayString array) {
		return Collections.unmodifiableList(list(enumValues, array));
	}

	/**
	 * Creates an unmodifiable array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static List<NativeObject> unmodifiableList(ArrayObject values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of java script native object container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native object of the array.
	 * @param <E> type of native object container
	 * @return the instance of native object containers list
	 */
	public static <E extends NativeObjectContainer> List<E> unmodifiableList(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		return Collections.unmodifiableList(list(array, factory));
	}

	/**
	 * Creates an unmodifiable array list of java script native double array container by a java script array and a factory.
	 * 
	 * @param array array of elements to load when the list is creating.
	 * @param factory factory implementation to create containers by a single native array of the arrays.
	 * @param <E> type of native double array container
	 * @return the instance of native double array containers list
	 */
	public static <E extends NativeArrayContainer<ArrayDouble>> List<E> unmodifiableList(ArrayDoubleArray array, NativeArrayContainerFactory<ArrayDouble, E> factory) {
		return Collections.unmodifiableList(list(array, factory));
	}

}