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
package org.pepstock.charba.client.jsinterop.utils;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is the wrapper to JSON javascript object.<br>
 * The JSON object contains methods for parsing JSON and converting values to JSON.
 * 
 * @author Andrea "Stock" Stocchero
 * @verison 2.0
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class JSON {

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
	static native String stringify(Object obj, Object function, int spaces);

	/**
	 * Make a deep copy of an object or array, assuring that there is at most one instance of each object or array in the
	 * resulting structure.<br>
	 * The duplicate references (which might be forming cycles) are replaced with an object of the form.
	 * 
	 * @param obj The value to convert to a JSON string.
	 * @return an object with cycle removed, ready to be stringfy.
	 */
	public static native Object decycle(Object obj);

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

}
