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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * FIXME Internal utility for {@link DataLabelsPlugin} to register the plugin globally to Chart.js.<br>
 * This is needed because from the plugin version for Chart.js 3, the plugin doesn't register itself golbally anymore and delegeate this operation to the user.<br>
 * To maintain the same capabilities of Charba, this object register the plugin globally.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_GEO_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsGeoHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsGeoHelper() {
		// do nothing
	}

	/**
	 * FIXME
	 */
	static native ArrayObject features(String topojson, String featureProperty);

}
