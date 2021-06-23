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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal utility for GEo charts to invoke <code>ChartGeo.topojson</code> utility provided by the controller package.<br>
 * TopoJson is packaged with this plugin to convert data, it is exposed as  in the global context. 
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
	 * Parses the topoJson definition in order to create all features need to draw the regions.
	 * 
	 * @param topojson topoJson region definition
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @return array with all parsed features
	 */
	static native ArrayObject features(String topojson, String featureProperty);

}
