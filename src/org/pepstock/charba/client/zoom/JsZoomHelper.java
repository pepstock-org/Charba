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
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ScaleId;
import org.pepstock.charba.client.options.TransitionMode;
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
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
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
	 * Returns the zoom level when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 * @return the zoom level
	 */
	double getZoomLevel(Chart chart) {
		// checks if chart is consistent
		if (chart != null) {
			return NativeJsZoomHelper.getZoomLevel(chart);
		}
		// if here, chart not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns whether the chart has been zoomed or panned, for instance whether the initial scale of any axis is different to the one used currently.
	 * 
	 * @param chart chart instance to invoke
	 * @return <code>true</code> if the chart has been zoomed or panned
	 */
	boolean isZoomedOrPanned(Chart chart) {
		// checks if chart is consistent
		if (chart != null) {
			return NativeJsZoomHelper.isZoomedOrPanned(chart);
		}
		// if here, chart not consistent
		// then returns undefined
		return Undefined.BOOLEAN;
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 * @param transition update transition mode, could be <code>null</code>
	 */
	void resetZoom(Chart chart, TransitionMode transition) {
		// checks if chart is consistent
		if (chart != null) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// resets zoom by transition key
				NativeJsZoomHelper.resetZoom(chart, transition.value());
			} else {
				// resets zoom by transition key
				NativeJsZoomHelper.resetZoom(chart);
			}
		}
	}

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 * @param transition update transition mode
	 */
	void pan(Chart chart, Amount amount, TransitionMode transition) {
		// checks if chart and amount are consistent
		if (chart != null && amount != null) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// pans by transition key
				NativeJsZoomHelper.pan(chart, amount.nativeObject(), transition.value());
			} else {
				// pans by transition key
				NativeJsZoomHelper.pan(chart, amount.nativeObject());
			}
		}
	}

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 * @param transition update transition mode
	 */
	void pan(Chart chart, double amount, TransitionMode transition) {
		// checks if chart and amount are consistent
		if (chart != null && Undefined.isNot(amount)) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// pans by transition key
				NativeJsZoomHelper.pan(chart, amount, transition.value());
			} else {
				// pans by transition key
				NativeJsZoomHelper.pan(chart, amount);
			}
		}
	}

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 * @param transition update transition mode
	 */
	void zoom(Chart chart, Amount amount, TransitionMode transition) {
		// checks if chart and amount are consistent
		if (chart != null && amount != null) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// zooms by transition key
				NativeJsZoomHelper.zoom(chart, amount.nativeObject(), transition.value());
			} else {
				// zooms by transition key
				NativeJsZoomHelper.zoom(chart, amount.nativeObject());
			}
		}
	}

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 * @param transition update transition mode
	 */
	void zoom(Chart chart, double amount, TransitionMode transition) {
		// checks if chart and amount are consistent
		if (chart != null && Undefined.isNot(amount)) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// zooms by transition key
				NativeJsZoomHelper.zoom(chart, amount, transition.value());
			} else {
				// zooms by transition key
				NativeJsZoomHelper.zoom(chart, amount);
			}
		}
	}

	/**
	 * Zooms the chart scale on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param scaleId scale id to zoom
	 * @param range range (min/max) of scale to zoom
	 * @param transition update transition mode
	 */
	void zoomScale(Chart chart, ScaleId scaleId, ScaleRange range, TransitionMode transition) {
		// checks if chart and amount are consistent
		if (chart != null && ScaleId.isValid(scaleId) && range != null) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// zooms by transition key
				NativeJsZoomHelper.zoomScale(chart, scaleId.value(), range.nativeObject(), transition.value());
			} else {
				// zooms by transition key
				NativeJsZoomHelper.zoomScale(chart, scaleId.value(), range.nativeObject());
			}
		}
	}

	/**
	 * Zooms the chart for a specific rectangle, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param p0 data point to coordinate of the start of the zoom operation
	 * @param p1 data point to coordinate of the end of the zoom operation
	 * @param transition update transition mode
	 */
	void zoomRect(Chart chart, ZoomPoint p0, ZoomPoint p1, TransitionMode transition) {
		// checks if chart is consistent
		if (chart != null) {
			// checks if transition is consistent
			if (TransitionMode.isValid(transition)) {
				// zooms by transition key
				NativeJsZoomHelper.zoomRect(chart, p0.nativeObject(), p1.nativeObject(), transition.value());
			} else {
				// zooms by transition key
				NativeJsZoomHelper.zoomRect(chart, p0.nativeObject(), p1.nativeObject());
			}
		}
	}
}