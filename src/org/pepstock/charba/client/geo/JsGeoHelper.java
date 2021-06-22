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
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.datalabels.DataLabelsPlugin;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * FIXME Internal utility for {@link DataLabelsPlugin} to register the plugin globally to Chart.js.<br>
 * This is needed because from the plugin version for Chart.js 3, the plugin doesn't register itself golbally anymore and delegeate this operation to the user.<br>
 * To maintain the same capabilities of Charba, this object register the plugin globally.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsGeoHelper {

	// static instance for singleton
	private static final JsGeoHelper INSTANCE = new JsGeoHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsGeoHelper() {
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// to be sure that GEO java script object is injected
		ChoroplethChart.CONTROLLER_TYPE.register();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsGeoHelper get() {
		return INSTANCE;
	}

	/**FIXME
	 * Register the {@link DataLabelsPlugin} plugin globally to Chart.js.
	 */
	ArrayObject features(String topojson, String featureProperty) {
		// checks if arguments are consistent
		if (topojson != null && featureProperty != null) {
			return NativeJsGeoHelper.features(topojson, featureProperty);
		}
		// if here, arguments are not consistent
		// then returns null
		return null;
	}

}
