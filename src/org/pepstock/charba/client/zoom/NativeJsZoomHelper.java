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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal utility of {@link ZoomPlugin} to reset zooming.<br>
 * This is done a native javascript object because the plugin adds a function to original CHART.JS chart instance.<br>
 * To maintain {@link Chart} clean from custom functions and properties, this helper has benn implemented.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_ZOOM_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsZoomHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsZoomHelper() {
		// do nothing
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 */
	static native void resetZoom(Chart chart);

}
