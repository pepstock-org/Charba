/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal utility for GEo charts to invoke <code>ChartGeo.topojson</code> utility provided by the controller package.<br>
 * TopoJson is packaged with this plugin to convert data, it is exposed as in the global context.
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
	static native ArrayObject features(NativeObject topojson, String featureProperty);

	/**
	 * Translates latitude and longitude in coordinates of the canvas where the chart is drawn.
	 * 
	 * @param chart native chart instance
	 * @param latitude latitude to use to get the Y point
	 * @param longitude longitude to use to get the X point
	 * @return an array of double with X (index 0) and Y (index 1)
	 */
	static native ArrayDouble projection(Chart chart, double latitude, double longitude);

	/**
	 * Translates X and Y coordinates of the canvas where the chart is drawn in latitude and longitude.
	 * 
	 * @param chart native chart instance
	 * @param x coordinate X of the canvas to translate in longitude
	 * @param y coordinate Y of the canvas to translate in latitude
	 * @return an array of double with longitude (index 0) and latitude (index 1)
	 */
	static native ArrayDouble invert(Chart chart, double x, double y);

	/**
	 * Returns the color for a specific data value.
	 * 
	 * @param chart chart instance
	 * @param value to use for searching
	 * @return a color a string
	 */
	static native String getColorForValue(Chart chart, double value);

	/**
	 * Returns the size for a specific data value.
	 * 
	 * @param chart chart instance
	 * @param value to use for searching
	 * @return the size of the value
	 */
	static native double getSizeForValue(Chart chart, double value);

}