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
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Native object which is mapping the <code>scaleService</code> of CHART.JS.<br>
 * The scale service enables the search for default by axis type and update the default configuration for an axis type.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class ScaleService {

	/**
	 * Returns the scale default configuration by an axis type
	 * 
	 * @param axisType axis type as string
	 * @return a native object with default configuration
	 */
	@JsMethod
	native NativeObject getScaleDefaults(String axisType);

	/**
	 * Updates the default configuration for a scale by its type.
	 * 
	 * @param axisType axis type as string
	 * @param newOptions new options to apply as defaults
	 */
	@JsMethod
	native void updateScaleDefaults(String axisType, NativeObject newOptions);

}
