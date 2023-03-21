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
package org.pepstock.charba.client.utils;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.BaseAttribute;
import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.NamedNodeMap;
import org.pepstock.charba.client.dom.enums.NodeType;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is the wrapper to JSON java script object.<br>
 * The JSON object contains methods for parsing JSON and converting values to JSON.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class JSON {

	/**
	 * A function that alters the behavior of the stringification process. If this value is null or not provided, all properties of the object are included in the resulting JSON
	 * string.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface Replacer {

		/**
		 * Method of function to alter the behavior of the stringification process.
		 * 
		 * @param key property key of object.
		 * @param value object related to the key.
		 * @return the value to show in the string.
		 */
		Object call(String key, Object value);
	}

	/**
	 * To avoid any instantiation
	 */
	private JSON() {
		// do nothing
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, optionally replacing values if a replacer function is specified or optionally including only the specified properties
	 * if a replacer array is specified.<br>
	 * it can throw an exception, TypeError ("cyclic object value") exception when a circular reference is found.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param function A function that alters the behavior of the stringification process, or an array of String and Number objects that serve as a whitelist for
	 *            selecting/filtering the properties of the value object to be included in the JSON string. If this value is null or not provided, all properties of the object are
	 *            included in the resulting JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is greater, the value is just 10). Values less than 1
	 *            indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	static native String stringify(Object obj, Replacer function, int spaces);

	/**
	 * Parses a JSON string, constructing the JavaScript value or object described by the string.
	 * 
	 * @param text the string to parse as JSON.
	 * @return the object corresponding to the given JSON text.
	 */
	@JsMethod(name = "parse")
	private static native NativeObject parseToNativeObject(String text);

	/**
	 * Parses a JSON string, constructing the JavaScript array described by the string.
	 * 
	 * @param text the string to parse as JSON.
	 * @param <T> type of native array
	 * @return the array corresponding to the given JSON text.
	 */
	@JsMethod(name = "parse")
	private static native <T extends Array> T parseToArray(String text);

	/**
	 * Parses a JSON string, constructing the JavaScript value or object described by the string.
	 * 
	 * @param text the string to parse as JSON.
	 * @return the object corresponding to the given JSON text.
	 */
	@JsOverlay
	public static NativeObject parseForObject(String text) {
		// checks text argument is consistent
		if (text != null && text.trim().startsWith(Constants.OPEN_BRACE)) {
			return parseToNativeObject(text);
		}
		// if here, the argument is not consistent to parse to a object
		// then returns null
		return null;
	}

	/**
	 * Parses a JSON string, constructing the JavaScript array described by the string.
	 * 
	 * @param text the string to parse as JSON.
	 * @param <T> type of native array
	 * @return the array corresponding to the given JSON text.
	 */
	@JsOverlay
	public static <T extends Array> T parseForArray(String text) {
		// checks text argument is consistent
		if (text != null && text.trim().startsWith(Constants.OPEN_SQUARE_BRACKET)) {
			return parseToArray(text);
		}
		// if here, the argument is not consistent to parse to a object
		// then returns null
		return null;
	}

	/**
	 * Converts a JavaScript object or value to a JSON string. By default, the space value is set to -1 that no space should be used.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringify(Object obj) {
		// checks if object id consistent
		if (obj != null) {
			// format in JSON
			return stringify(obj, null, -1);
		}
		// if here, the argument is null
		// then returns an empty JSON
		return JSONReplacerConstants.EMPTY_JSON_OBJECT;
	}

	/**
	 * Converts a JavaScript object or value to a JSON string.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is greater, the value is just 10). Values less than 1
	 *            indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringify(Object obj, int spaces) {
		// checks if object id consistent
		if (obj != null) {
			// format in JSON
			return stringify(obj, null, spaces);
		}
		// if here, the argument is null
		// then returns an empty JSON
		return JSONReplacerConstants.EMPTY_JSON_OBJECT;
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, the space value is set to -1 that no space should be used and uses a default replacer to avoid
	 * <code>TypeError: cyclic object value</code>.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyWithReplacer(Object obj) {
		return stringifyWithReplacer(obj, -1);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, using a default replacer to avoid <code>TypeError: cyclic object value</code>.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is greater, the value is just 10). Values less than 1
	 *            indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyWithReplacer(Object obj, int spaces) {
		// checks if object id consistent
		if (obj != null) {
			// creates a cached to checks if an object was already parsed
			final Set<Object> objects = new HashSet<>();
			// invokes JSON stringfy setting replacer to avoid cycle type error
			return stringifyWithReplacer(obj, (key, value) -> {
				// if key is null of empty
				// means that is first object then skip
				if (key != null && key.trim().length() > 0) {
					// gets the object
					Object result = manageObject(objects, value);
					// if result is not consistent, returns the value
					return result != null ? result : value;
				} else {
					// here is the first object
					// adds to set to further controls
					objects.add(value);
				}
				// returns object
				return value;
			}, spaces);
		}
		// if here, the argument is null
		// then returns an empty JSON
		return JSONReplacerConstants.EMPTY_JSON_OBJECT;
	}

	/**
	 * Manages the object of the replace function.
	 * 
	 * @param objects set of objects already managed.
	 * @param value object passed by replace function.
	 * @return the value to return to replace function or <code>null</code> if must continue
	 */
	@JsOverlay
	private static String manageObject(Set<Object> objects, Object value) {
		// gets the type of object
		ObjectType type = JsHelper.get().typeOf(value);
		// if function
		if (ObjectType.FUNCTION.equals(type)) {
			// returns the value of function
			return value + Constants.EMPTY_STRING;
		}
		// if object
		if (ObjectType.OBJECT.equals(type)) {
			// checks if is an element
			String result = manageElement(value);
			// if not null, is an element
			// then returns the result
			if (result != null) {
				return result;
			}
			// checks if the object has been already parsed
			if (objects.contains(value)) {
				// sets the static vale
				// to avoid cycle
				return JSONReplacerConstants.CYCLE_PROPERTY_VALUE;
			}
			// adds object to cache
			objects.add(value);
		}
		return null;
	}

	/**
	 * Manages the object of the replace function checking if is a DOM element and then returning the DOM string.
	 * 
	 * @param value object passed by replace function.
	 * @return a DOM element as string.
	 */
	@JsOverlay
	private static String manageElement(Object value) {
		// checks if is an element
		if (value instanceof BaseElement) {
			// casts to element
			BaseElement element = (BaseElement) value;
			// checks if is an element node
			if (NodeType.ELEMENT_NODE.equals(element.getNodeType())) {
				// creates builder
				StringBuilder sb = new StringBuilder();
				// appends xml values as node
				sb.append(Constants.LT).append(element.getNodeName().toLowerCase(Locale.getDefault()));
				// checks if element has got any attributes
				if (element.hasAttributes()) {
					// gets attributes map
					NamedNodeMap<BaseAttribute> attributes = element.getAttributes();
					// scans all attributes
					for (int i = 0; i < attributes.length(); i++) {
						// gets attribute by index
						BaseAttribute attr = attributes.item(i);
						sb.append(Constants.BLANK).append(attr.getName()).append("='").append(attr.getValue() + "'");
					}
				}
				// returns html
				return sb.append(Constants.GT).toString();
			}
		}
		return null;
	}

	/**
	 * Converts a JavaScript object or value to a JSON string.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param replacer A function that alters the behavior of the stringification process.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is greater, the value is just 10). Values less than 1
	 *            indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyWithReplacer(Object obj, Replacer replacer, int spaces) {
		// checks if object id consistent
		if (obj != null) {
			// checks if replacer is consistent
			if (replacer != null) {
				// invokes stringify with replacer
				return stringify(obj, replacer, spaces);
			} else {
				// if here, no replacer
				// then normal stringify
				stringify(obj, spaces);
			}
		}
		// if here, the argument is null
		// then returns an empty JSON
		return JSONReplacerConstants.EMPTY_JSON_OBJECT;
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, using a specific replacer to avoid to print internal keys of CHART.js
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is greater, the value is just 10). Values less than 1
	 *            indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyNativeObject(NativeObject obj, int spaces) {
		// invokes JSON stringify setting replacer to get only keys passed
		return stringifyWithReplacer(obj, (key, value) -> {
			// if key is null of empty
			// means that is first object
			if (key != null && key.trim().length() > 0) {
				// checks is not a HCART.JS internal property
				if (!key.startsWith(Constants.UNDERSCORE)) {
					// gets the type of object
					ObjectType type = JsHelper.get().typeOf(value);
					// if function
					if (ObjectType.FUNCTION.equals(type)) {
						// returns the value of function
						return value + Constants.EMPTY_STRING;
					}
					// if object
					// checks if is an element
					// checks if is an element
					String result = manageElement(value);
					// if not null, is an element
					// then returns the result
					return result != null ? result : value;
				}
				// checks if is a Charba property
				return ignoreCharbaInstances(key, value);
			}
			// returns object
			return value;
		}, spaces);
	}

	/**
	 * Ignores all keys created by CHARBA in the replace function.
	 * 
	 * @param key property key of object.
	 * @param value object related to the key.
	 * @return the value to show in the string.
	 */
	@JsOverlay
	private static Object ignoreCharbaInstances(String key, Object value) {
		// checks if is a Charba property
		if (key.startsWith(JSONReplacerConstants.CHARBA_PROPERTY_KEY_PREFIX)) {
			// returns value
			return value;
		} else {
			// skip it
			return Window.undefined();
		}
	}

}