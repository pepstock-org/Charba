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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to manage native scriptable options context.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_CALLBACKS_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsCallbacksHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsCallbacksHelper() {
		// do nothing
	}

	/**
	 * Returns a native context from a native object.
	 * 
	 * @param object native object to wrap
	 * @return a native context
	 */
	static native NativeContext wrap(NativeObject object);

}
