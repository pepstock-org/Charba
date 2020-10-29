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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This is the java script native object which is the configuration of CHART.JS.<br>
 * It maps the java script object <code>chart.config</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.CHART_CONFIGURATION, namespace = JsPackage.GLOBAL)
final class NativeConfiguration {

	/**
	 * Returns the configuration options instance, as native object.
	 * 
	 * @return the configuration options instance.
	 */
	@JsProperty
	native NativeObject getOptions();

	/**
	 * Updates the options of configuration, merging all CHART.JS options (defaults, scales and chart types ones) with the options passed as argument.
	 * 
	 * @param options the options which must be merged with all other CHART.JS options.
	 */
	@JsMethod
	private native void update(NativeObject options);

	/**
	 * Updates the options of configuration, merging all CHART.JS options (defaults, scales and chart types ones) with the options passed as argument, and returns the merged
	 * options.
	 * 
	 * @param options the options which must be merged with all other CHART.JS options.
	 * @return the configuration options instance.
	 */
	@JsOverlay
	NativeObject updateAndGetConfiguration(NativeObject options) {
		// checks if argument is consistent
		if (options != null) {
			// applies the update
			update(options);
		}
		// returns always the options
		return getOptions();
	}

}
