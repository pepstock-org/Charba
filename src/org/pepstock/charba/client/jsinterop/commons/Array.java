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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Array object which maps the java script object.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
@JsType(isNative = true, name = NativeName.ARRAY, namespace = JsPackage.GLOBAL)
public class Array {

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

}
