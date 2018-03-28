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
	public static JsDoubleArrayList build(double... values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		JsDoubleArrayList result = new JsDoubleArrayList();
		// adds all elements
		result.addAll(values);
		// returns the list
		return result;
	}

	/**
	 * Creates a JavaScript array list of integers.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsIntegerArrayList
	 */
	public static JsIntegerArrayList build(int... values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		JsIntegerArrayList result = new JsIntegerArrayList();
		// adds all elements
		result.addAll(values);
		// returns the list
		return result;
	}

	/**
	 * Creates a JavaScript array list of strings.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	public static JsStringArrayList build(String... values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		JsStringArrayList result = new JsStringArrayList();
		// adds all elements
		result.addAll(values);
		// returns the list
		return result;
	}

	/**
	 * Creates a JavaScript array list of generic javaScript objects.
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of java script object
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.GenericJavaScriptObject
	 * @see org.pepstock.charba.client.commons.JsObjectArrayList
	 */
	public static <E extends GenericJavaScriptObject> JsObjectArrayList<E> build(E[] values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		JsObjectArrayList<E> result = new JsObjectArrayList<E>();
		// adds all elements
		result.addAll(values);
		// returns the list
		return result;
	}

	/**
	 * Loads an existing list of JavaScript object container.
	 * @param container list instance of JavaScript object container
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return the instance of updated list
	 * @see org.pepstock.charba.client.commons.AbstractList
	 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
	 */
	public static <E> AbstractList<E> load(AbstractList<E> container, E[] values){
		// removes of current values into list
		container.clear();
		// checks if array is null
		if (values == null){
			return null;
		}
		// adds all elements
		container.addAll(values);
		// returns the list
		return container;
	}

	/**
	 * Creates a JavaScript array list of EnumValues.
	 * @param clazz Enum class with all possible values of enumeration
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of key
	 * @return a array list of values or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsEnumValueArrayList
	 */
	public static <E extends Key> JsEnumValueArrayList<E> build(Class<E> clazz, E[] values){
		// checks if array is null
		if (values == null){
			return null;
		}
		// creates the list
		JsEnumValueArrayList<E> result = new JsEnumValueArrayList<E>(clazz);
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
	public static <E extends Key> JsEnumValueArrayList<E> build(Class<E> clazz, JsStringArrayList list){
		// checks if array is null
		if (list == null){
			return null;
		}
		// returns the list adding the string array list to initialize it
		return new JsEnumValueArrayList<E>(clazz.getEnumConstants(), list.getJsArray());
	}

}