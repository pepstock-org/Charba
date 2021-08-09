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
package org.pepstock.charba.client.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * The set object lets you store unique values of any type, whether primitive values or object references.<br>
 * Set objects are collections of values.<br>
 * You can iterate through the elements of a set in insertion order.<br>
 * A value in the set may only occur once; it is unique in the set's collection.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.SET, namespace = JsPackage.GLOBAL)
final class NativeSet {

	/**
	 * Creates a set object that store unique values of an array reference. 
	 * 
	 * @param array array to check and reduce the duplicated values
	 */
	NativeSet(Array array) {
		// nothing
	}

}
