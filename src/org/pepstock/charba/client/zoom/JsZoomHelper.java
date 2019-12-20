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
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Internal utility of {@link ZoomPlugin} to reset zooming.<br>
 * This is done a native javascript object because the plugin adds a function to original CHART.JS chart instance.<br>
 * To maintain {@link Chart} clean from custom functions and properties, this helper has benn implemented.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsZoomHelper {

	// static instance for singleton
	private static final JsZoomHelper INSTANCE = new JsZoomHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsZoomHelper() {
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// to be sure that CHARBA java script object is injected
		Injector.ensureInjected(ResourcesType.getClientBundle().charbaHelper());
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsZoomHelper get() {
		return INSTANCE;
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 */
	void resetZoom(Chart chart) {
		// checks if chart is consistent
		if (chart != null) {
			// resets zoom
			NativeJsZoomHelper.resetZoom(chart);
		}
	}

}
