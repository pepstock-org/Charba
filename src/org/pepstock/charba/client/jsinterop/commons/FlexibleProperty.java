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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * Utility to transform java script property (represented by an object) into array or
 * a possible java array into a single java script property value or an java script array.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class FlexibleProperty {

	/**
	 * To avoid any instantiation
	 */
	private FlexibleProperty() {
		// do nothing
	}

	/**
	 * Creates an object (array or string) from an array of keys.
	 * @param values arrays of keys.
	 * @return java script object (array, string or null)
	 */
	public static Object fromKeys(Key... values) {
		// checks if argument is null
		if (values != null) {
			// if only 1 element is passed
			// assumes that a string must be returned
			if (values.length == 1) {
				return values[0].name();	
			} else {
				// otherwise returns the array
				return ArrayString.of(values);
			}
		}
		// returns null
		return null;
	}

	/**
	 * Returns a list of keys from a java script object and enumeration class.
	 * @param value java script object to use
	 * @param clazz enumeration class
	 * @return a list of keys
	 */
	public static <T extends Key> List<T> toKeys(Object value, Class<T> clazz) {
		// checks if argument is an array
		if (Array.isArray(value)) {
			// creates the list  
			return ArrayListHelper.list(clazz, (ArrayString)value);
		} else if (value == null){
			// if value is null, it creates a empty list
			return new ArrayEnumList<T>(clazz);
		} else {
			// if object is not an array
			// creates an array with 1 element casting to a string
			return ArrayListHelper.list(clazz, ArrayString.of((String)value));
		}
	}

	/**
	 * Creates an object (array or string) from an array of color, by {@link org.pepstock.charba.client.colors.IsColor}.
	 * @param values arrays of keys.
	 * @return java script object (array, string or null)
	 */
	public static Object fromColors(IsColor... values) {
		// checks if argument is null
		if (values != null) {
			// if only 1 element is passed
			// assumes that a string (RGBA) must be returned
			if (values.length == 1) {
				return values[0].toRGBA();	
			} else {
				// otherwise returns the array
				return ArrayString.of(values);
			}
		}
		// returns null
		return null;
	}

	/**
	 * Creates an object (array or string) from an array of strings.
	 * @param values array of strings
	 * @return java script object (array, string or null)
	 */
	public static Object fromStrings(String... values) {
		// checks if argument is null
		if (values != null) {
			// if only 1 element is passed
			// assumes that a string must be returned
			if (values.length == 1) {
				return values[0];	
			} else {
				// otherwise returns the array
				return ArrayString.of(values);
			}
		}
		// returns null
		return null;
	}

	/**
	 * Returns a list of strings from a java script object. 
	 * @param value java script object
	 * @return a list of strings
	 */
	public static List<String> toStrings(Object value) {
		// checks if argument is an array
		if (Array.isArray(value)) {
			// creates the list  
			return ArrayListHelper.list((ArrayString)value);
		} else if (value == null){
			// if argument is null
			// creates an empty list
			return new ArrayStringList();
		} else {
			// if object is not an array
			// creates an array with 1 element casting to a string
			return ArrayListHelper.list(ArrayString.of((String)value));
		}
	}

	/**
	 * Creates an object (array or integer) from an array of integers.
	 * @param values array of integers
	 * @return java script object (array, integer or null)
	 */
	public static Object fromIntegers(int... values) {
		// checks if argument is null
		if (values != null) {
			// if only 1 element is passed
			// assumes that a integer must be returned
			if (values.length == 1) {
				return values[0];	
			} else {
				// otherwise returns the array
				return ArrayInteger.of(values);
			}
		}
		// returns null
		return null;
	}

	/**
	 * Returns a list of integers from a java script object. 
	 * @param value java script object
	 * @return a list of integers
	 */
	public static List<Integer> toIntegers(Object value) {
		// checks if argument is an array
		if (Array.isArray(value)) {
			// creates the list
			return ArrayListHelper.list((ArrayInteger)value);
		} else if (value == null){
			// if argument is null
			// creates an empty list
			return new ArrayIntegerList();
		} else {
			// if object is not an array
			// creates an array with 1 element casting to a integer
			return ArrayListHelper.list(ArrayInteger.of((Integer)value));
		}
	}

	/**
	 * Creates an object (array or double) from an array of doubles.
	 * @param values array of doubles
	 * @return java script object (array, double or null)
	 */
	public static Object fromDoubles(double... values) {
		// checks if argument is null
		if (values != null) {
			if (values.length == 1) {
				// if only 1 element is passed
				// assumes that a double must be returned
				return values[0];	
			} else {
				// otherwise returns the array
				return ArrayDouble.of(values);
			}
		}
		// returns null
		return null;
	}

	/**
	 * Returns a list of doubles from a java script object. 
	 * @param value java script object
	 * @return a list of doubles
	 */
	public static List<Double> toDoubles(Object value) {
		// checks if argument is an array
		if (Array.isArray(value)) {
			// creates the list
			return ArrayListHelper.list((ArrayDouble)value);
		} else if (value == null){
			// if argument is null
			// creates an empty list
			return new ArrayDoubleList();
		} else {
			// if object is not an array
			// creates an array with 1 element casting to a double
			return ArrayListHelper.list(ArrayDouble.of((Double)value));
		}
	}
}
