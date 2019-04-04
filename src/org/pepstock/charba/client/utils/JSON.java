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
package org.pepstock.charba.client.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;

import jsinterop.annotations.JsFunction;
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
	 * A function that alters the behavior of the stringification process. If this value is null or not provided, all properties
	 * of the object are included in the resulting JSON string.<br>
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
		 * @return the value to show into string.
		 */
		Object call(String key, Object value);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, optionally replacing values if a replacer function is specified
	 * or optionally including only the specified properties if a replacer array is specified.<br>
	 * it can throw an exception, TypeError ("cyclic object value") exception when a circular reference is found.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param function A function that alters the behavior of the stringification process, or an array of String and Number
	 *            objects that serve as a whitelist for selecting/filtering the properties of the value object to be included in
	 *            the JSON string. If this value is null or not provided, all properties of the object are included in the
	 *            resulting JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is
	 *            greater, the value is just 10). Values less than 1 indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	static native String stringify(Object obj, Replacer function, int spaces);

	/**
	 * Converts a JavaScript object or value to a JSON string. By default, the space value is set to -1 that no space should be
	 * used.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringify(Object obj) {
		return stringify(obj, null, -1);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is
	 *            greater, the value is just 10). Values less than 1 indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringify(Object obj, int spaces) {
		return stringify(obj, null, spaces);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, the space value is set to -1 that no space should be used and
	 * uses a default replacer to avoid <code>TypeError: cyclic object value</code>.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyWithReplacer(Object obj) {
		return stringifyWithReplacer(obj, -1);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, using a default replacer to avoid
	 * <code>TypeError: cyclic object value</code>.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is
	 *            greater, the value is just 10). Values less than 1 indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyWithReplacer(Object obj, int spaces) {
		// creates a cached to checks if an object was already parsed
		final Set<Object> objects = new HashSet<>();
		// invokes JSON stringfy setting replacer to avoid cycle type error
		return stringifyWithReplacer(obj, new Replacer() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.utils.JSON.Replacer#call(java.lang.String, java.lang.Object)
			 */
			@Override
			public Object call(String key, Object value) {
				// if key is null of empty
				// means that is first object then skip
				if (key != null && key.trim().length() > 0) {
					// checks if hashcode
					if (key.equalsIgnoreCase(JSONReplacerConstants.HASHCODE_PROPERTY_KEY)) {
						// skips it
						return JsHelper.get().undefined();
					}
					// gets the type of object
					ObjectType type = JsHelper.get().typeOf(value);
					// if function
					if (ObjectType.FUNCTION.equals(type)) {
						// returns the value of function
						return value + "";
					}
					// if object
					if (ObjectType.OBJECT.equals(type)) {
						// checks if is an element
						if (value instanceof Element) {
							// casts to element
							Element element = (Element) value;
							// checks if is an element node
							if (element.getNodeType() == Node.ELEMENT_NODE) {
								StringBuilder sb = new StringBuilder();
								sb.append("<").append(element.getNodeName().toLowerCase(Locale.getDefault()));
								List<String> attributes = JsHelper.get().elementAttributes(element);
								if (!attributes.isEmpty()) {
									for (String attribute : attributes) {
										sb.append(" ").append(attribute);
									}
								}
								// returns html
								return sb.append(">").toString();
							}
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
				} else {
					// here is the first object
					// adds to set to further controls
					objects.add(value);
				}
				// returns object
				return value;
			}
		}, spaces);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param replacer A function that alters the behavior of the stringification process.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is
	 *            greater, the value is just 10). Values less than 1 indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyWithReplacer(Object obj, Replacer replacer, int spaces) {
		return stringify(obj, replacer, spaces);
	}

	/**
	 * Converts a JavaScript object or value to a JSON string, using a specific replacer to avoid to print internal keys of
	 * CHART.js
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @param spaces it indicates the number of space characters to use as white space; this number is capped at 10 (if it is
	 *            greater, the value is just 10). Values less than 1 indicate that no space should be used.
	 * @return A JSON string representing the given value.
	 */
	@JsOverlay
	public static String stringifyNativeObject(NativeObject obj, int spaces) {
		// invokes JSON stringfy setting replacer to get only keys passed
		return stringifyWithReplacer(obj, new Replacer() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.utils.JSON.Replacer#call(java.lang.String, java.lang.Object)
			 */
			@Override
			public Object call(String key, Object value) {
				// if key is null of empty
				// means that is first object
				if (key != null && key.trim().length() > 0) {
					// checks is not a HCART.JS internal property
					if (!key.startsWith(JSONReplacerConstants.INTERNAL_PROPERTY_KEY_PREFIX)) {
						// gets the type of object
						ObjectType type = JsHelper.get().typeOf(value);
						// if function
						if (ObjectType.FUNCTION.equals(type)) {
							// returns the value of function
							return value + "";
						}
						// if object
						if (ObjectType.OBJECT.equals(type)) {
							// checks if is an element
							if (value instanceof Element) {
								// casts to element
								Element element = (Element) value;
								// checks if is an element node
								if (element.getNodeType() == Node.ELEMENT_NODE) {
									StringBuilder sb = new StringBuilder();
									sb.append("<").append(element.getNodeName().toLowerCase(Locale.getDefault()));
									List<String> attributes = JsHelper.get().elementAttributes(element);
									if (!attributes.isEmpty()) {
										for (String attribute : attributes) {
											sb.append(" ").append(attribute);
										}
									}
									// returns html
									return sb.append(">").toString();
								}
							}
						}
						// returns value
						return value;
					}
					// checks if is a Charba property
					if (key.startsWith(JSONReplacerConstants.CHARBA_PROPERTY_KEY_PREFIX)) {
						// returns value
						return value;
					} else {
						// skip it
						return JsHelper.get().undefined();
					}
				}
				// returns object
				return value;
			}
		}, spaces);
	}

}
