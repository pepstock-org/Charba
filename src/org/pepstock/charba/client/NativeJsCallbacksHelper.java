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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.UndefinedValues;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to invoke CHART.JS callbacks, provided out of the box, the default one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JSCALLBACKSHELPER, namespace = JsPackage.GLOBAL)
final class NativeJsCallbacksHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsCallbacksHelper() {
		// do nothing
	}

	/**
	 * Returns an HTML string of a legend for that chart with the callback provided by CHART.JS out of the box, invoking <code>legendCallback</code> function property.
	 * 
	 * @param chart chart instance
	 * @param options chart options, generated merging all defaults.
	 * @return the HTML legend or {@link UndefinedValues#STRING} if chart or options are not consistent.
	 */
	static native String generateDefaultCallback(Chart chart, NativeObject options);

}
