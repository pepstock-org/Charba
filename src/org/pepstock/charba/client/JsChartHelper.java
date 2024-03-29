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
package org.pepstock.charba.client;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to invoke CHART.JS callbacks, provided out of
 * the box, the default one.<br>
 * This wrapper is necessary to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsChartHelper {
	// static instance for singleton
	private static final JsChartHelper INSTANCE = new JsChartHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsChartHelper() {
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
	static JsChartHelper get() {
		return INSTANCE;
	}

	// -----------------
	// ANIMATION interpolators
	// -----------------

	/**
	 * Returns an interpolated COLOR value for a specific type from CHART.JS.
	 *
	 * @param from starting value
	 * @param to ending value
	 * @param factor interpolation factor
	 * @return interpolated value for specific type
	 */
	String interpolateColors(String from, String to, double factor) {
		return NativeJsChartHelper.interpolateColors(from, to, factor);
	}

	/**
	 * Returns an unmodifiable list of legend labels for that chart with the callback provided by CHART.JS out of the box, invoking <code>generateLabels</code> function property.
	 * 
	 * @param chart chart instance
	 * @param options chart options, generated merging all defaults.
	 * @return an unmodifiable list of legend labels or an empty list if chart is not initialized.
	 */
	List<LegendLabelItem> generateDefaultLabels(Chart chart, ChartOptions options) {
		ArrayObject array = NativeJsChartHelper.generateDefaultLabels(chart, options.nativeObject());
		return ArrayListHelper.unmodifiableList(array, LegendLabelItem.FACTORY);
	}

	/**
	 * Invokes the legend event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param context chart instance, used as function context
	 * @param event native object of event wrapper from user interface
	 * @param item legend item native
	 */
	void invokeDefaultLegendEvent(ChartOptions options, Key key, Chart context, NativeObject event, NativeObject item) {
		// checks if key is consistent
		if (Key.isValid(key)) {
			// invokes legend event callback
			NativeJsChartHelper.invokeDefaultLegendEvent(options.nativeObject(), key.value(), context, event, item);
		}
	}

	/**
	 * Invokes the chart event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param context chart instance, used as function context
	 * @param event native object of event wrapper from user interface
	 * @param items array of datasets native objects
	 */
	void invokeDefaultChartEvent(ChartOptions options, Key key, Chart context, NativeObject event, ArrayObject items) {
		// checks if key is consistent
		if (Key.isValid(key)) {
			// invokes chart event callback
			NativeJsChartHelper.invokeDefaultChartEvent(options.nativeObject(), key.value(), context, event, items);
		}
	}

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param chart chart instance, used to get the tooltip
	 * @param elements array of active tooltip elements
	 * @param point synthetic event position used in positioning
	 */
	void setTooltipActiveElements(Chart chart, ArrayObject elements, NativeObject point) {
		// checks if chart and elements are consistent
		if (chart != null && elements != null) {
			NativeJsChartHelper.setTooltipActiveElements(chart, elements, point);
		}
	}

	/**
	 * Returns the active tooltip elements for the chart.
	 * 
	 * @param chart chart instance, used to get the tooltip
	 * @return the array of active tooltip elements
	 */
	ArrayObject getTooltipActiveElements(Chart chart) {
		return NativeJsChartHelper.getTooltipActiveElements(chart);
	}

	/**
	 * Returns the subtitle elements from the chart.
	 * 
	 * @param chart chart instance, used to get the subtitle
	 * @return the native object of subtitle
	 */
	NativeObject getSubtitle(Chart chart) {
		return NativeJsChartHelper.getSubtitle(chart);
	}

}