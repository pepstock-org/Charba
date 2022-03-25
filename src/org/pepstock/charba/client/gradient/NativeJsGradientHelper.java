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
package org.pepstock.charba.client.gradient;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal utility for {@link GradientPlugin} to register the plugin globally to Chart.js.<br>
 * This is needed because the plugin doesn't register itself globally anymore and delegate this operation to the user.<br>
 * To maintain the same capabilities of Charba, this object register the plugin globally.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_GRADIENT_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsGradientHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsGradientHelper() {
		// do nothing
	}

	/**
	 * Register the {@link GradientPlugin} plugin globally to Chart.js.
	 */
	static native void register();

}
