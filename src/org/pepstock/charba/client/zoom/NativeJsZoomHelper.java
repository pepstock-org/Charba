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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

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
	 * Returns the zoom level when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 * @return the zoom level
	 */
	static native double getZoomLevel(Chart chart);

	/**
	 * Returns whether the chart has been zoomed or panned, for instance whether the initial scale of any axis is different to the one used currently.
	 * 
	 * @param chart chart instance to invoke
	 * @return <code>true</code> if the chart has been zoomed or panned
	 */
	static native boolean isZoomedOrPanned(Chart chart);

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 */
	static native void resetZoom(Chart chart);

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 * @param transition update transition mode
	 */
	static native void resetZoom(Chart chart, String transition);

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 */
	static native void pan(Chart chart, NativeObject amount);

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 * @param transition update transition mode
	 */
	static native void pan(Chart chart, NativeObject amount, String transition);

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 */
	static native void pan(Chart chart, double amount);

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 * @param transition update transition mode
	 */
	static native void pan(Chart chart, double amount, String transition);

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 */
	static native void zoom(Chart chart, NativeObject amount);

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 * @param transition update transition mode
	 */
	static native void zoom(Chart chart, NativeObject amount, String transition);

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 */
	static native void zoom(Chart chart, double amount);

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 * @param transition update transition mode
	 */
	static native void zoom(Chart chart, double amount, String transition);

	/**
	 * Zooms the chart scale on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param scaleId scale id to zoom
	 * @param range range (min/max) of scale to zoom
	 */
	static native void zoomScale(Chart chart, String scaleId, NativeObject range);

	/**
	 * Zooms the chart scale on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param scaleId scale id to zoom
	 * @param range range (min/max) of scale to zoom
	 * @param transition update transition mode
	 */
	static native void zoomScale(Chart chart, String scaleId, NativeObject range, String transition);

	/**
	 * Zooms the chart for a specific rectangle, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param p0 data point to coordinate of the start of the zoom operation
	 * @param p1 data point to coordinate of the end of the zoom operation
	 */
	static native void zoomRect(Chart chart, NativeObject p0, NativeObject p1);

	/**
	 * Zooms the chart for a specific rectangle, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param p0 data point to coordinate of the start of the zoom operation
	 * @param p1 data point to coordinate of the end of the zoom operation
	 * @param transition update transition mode
	 */
	static native void zoomRect(Chart chart, NativeObject p0, NativeObject p1, String transition);

}