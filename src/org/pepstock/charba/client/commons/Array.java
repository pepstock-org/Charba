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

import org.pepstock.charba.client.utils.Window;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Array object which maps the java script object.
 * 
 * @author Andrea "Stock" Stocchero
 */
// Ignores SonarCloud issue, java:S1610 - Abstract classes without fields should be converted to interfaces,
// because this is JSINTEROP implementation of native object which must be a class because there are native methods
@SuppressWarnings("java:S1133")
@JsType(isNative = true, name = NativeName.ARRAY, namespace = JsPackage.GLOBAL)
public abstract class Array implements IsJSType {

	/**
	 * This method determines whether the passed value is an Array.
	 * 
	 * @param object object to be checked.
	 * @return <code>true</code> if the value is an Array; otherwise, <code>false</code>.
	 */
	public static native boolean isArray(Object object);

	/**
	 * Returns the number of elements in this array.
	 * 
	 * @return the number of elements in this array.
	 */
	@JsProperty(name = "length")
	public final native int length();

	/**
	 * Returns <code>true</code> if this array contains no elements.
	 * 
	 * @return <code>true</code> if this array contains no elements
	 */
	@JsOverlay
	public final boolean isEmpty() {
		// checks the length
		return length() == 0;
	}

	/**
	 * Creates and returns a new string by concatenating all of the elements in an array (or an array-like object), separated by commas or a specified separator string.<br>
	 * If the array has only one item, then that item will be returned without using the separator.
	 * 
	 * @param separator separator string to apply in the join
	 * @return a new string by concatenating all of the elements in an array
	 */
	@JsMethod(name = "join")
	final native String nativeJoin(String separator);

	/**
	 * Creates and returns a new string by concatenating all of the elements in an array (or an array-like object), separated by commas.<br>
	 * If the array has only one item, then that item will be returned without using the separator.
	 * 
	 * @return a new string by concatenating all of the elements in an array
	 */
	@JsOverlay
	public final String join() {
		return nativeJoin(Window.undefined());

	}

	/**
	 * Creates and returns a new string by concatenating all of the elements in an array (or an array-like object), separated by commas or a specified separator string.<br>
	 * If the array has only one item, then that item will be returned without using the separator.
	 * 
	 * @param separator separator string to apply in the join
	 * @return a new string by concatenating all of the elements in an array
	 */
	@JsOverlay
	public final String join(String separator) {
		// checks if argument is consistent
		if (separator != null) {
			return nativeJoin(separator);
		}
		// if here the argument is not consistent
		// then returns the default join
		return join();
	}

}
