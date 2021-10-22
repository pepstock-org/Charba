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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Internal utility for GEo charts to invoke <code>ChartGeo.topojson</code> utility provided by the controller package.<br>
 * TopoJson is packaged with this plugin to convert data, it is exposed as in the global context.
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

	/**
	 * Parses the topoJson definition in order to create all features need to draw the regions.
	 * 
	 * @param topojson topoJson region definition
	 * @param featureProperty property in the <code>objects</code> node of topoJson definition where all regions are defined
	 * @return array with all parsed features
	 */
	ArrayObject features(TopoJson topojson, String featureProperty) {
		// checks if arguments are consistent
		if (topojson != null && featureProperty != null) {
			return NativeJsGeoHelper.features(topojson.nativeObject(), featureProperty);
		}
		// if here, arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Translates latitude and longitude in coordinates of the canvas where the chart is drawn.
	 * 
	 * @param chart native chart instance
	 * @param latitude latitude to use to get the Y point
	 * @param longitude longitude to use to get the X point
	 * @return an array of double with X (index 0) and Y (index 1) or <code>null</code> if chart is not consistent
	 */
	ArrayDouble projection(Chart chart, double latitude, double longitude) {
		// checks chart argument if consistent
		if (chart != null) {
			return NativeJsGeoHelper.projection(chart, latitude, longitude);
		}
		// if here, chart is not consistent
		return null;
	}

	/**
	 * Translates X and Y coordinates of the canvas where the chart is drawn in latitude and longitude.
	 * 
	 * @param chart native chart instance
	 * @param x coordinate X of the canvas to translate in longitude
	 * @param y coordinate Y of the canvas to translate in latitude
	 * @return an array of double with longitude (index 0) and latitude (index 1) or <code>null</code> if chart is not consistent
	 */
	ArrayDouble invert(Chart chart, double x, double y) {
		// checks chart argument if consistent
		if (chart != null) {
			return NativeJsGeoHelper.projection(chart, x, y);
		}
		// if here, chart is not consistent
		return null;
	}

	/**
	 * Returns the color for a specific data value.
	 * 
	 * @param chart chart instance
	 * @param value to use for searching
	 * @return a color a string
	 */
	String getColorForValue(Chart chart, double value) {
		// checks chart argument if consistent
		if (chart != null) {
			return NativeJsGeoHelper.getColorForValue(chart, value);
		}
		// if here, chart is not consistent
		return Undefined.STRING;
	}

	/**
	 * Returns the size for a specific data value.
	 * 
	 * @param chart chart instance
	 * @param value to use for searching
	 * @return the size of the value
	 */
	double getSizeForValue(Chart chart, double value) {
		// checks chart argument if consistent
		if (chart != null) {
			return NativeJsGeoHelper.getSizeForValue(chart, value);
		}
		// if here, chart is not consistent
		return Undefined.DOUBLE;
	}

}
