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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation to create a date adapter from CHART.JS. This wrapper is necessary
 * to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_DATE_ADAPTER_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsDateAdapterHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsDateAdapterHelper() {
		// do nothing
	}

	/**
	 * Returns a date adapter instance, using the options passed as argument.
	 * 
	 * @param options date adapter options.
	 * @return a date adapter instance.
	 */
	static native NativeDateAdapter create(NativeObject options);

}
