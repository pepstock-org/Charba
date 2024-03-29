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

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to invoke CHART.JS callbacks, provided out of the box,
 * the default one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_CHART_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsChartHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsChartHelper() {
		// do nothing
	}

	// -----------------
	// ANIMATION interpolators
	// -----------------

	/**
	 * Returns an interpolated color value for a specific type from CHART.JS.
	 *
	 * @param from starting value
	 * @param to ending value
	 * @param factor interpolation factor
	 * @return interpolated value for specific type
	 */
	static native String interpolateColors(String from, String to, double factor);

	// -----------------
	// LEGEND callback
	// -----------------

	/**
	 * Returns an unmodifiable list of legend labels for that chart with the callback provided by CHART.JS out of the box, invoking <code>generateLabels</code> function property.
	 * 
	 * @param chart chart instance
	 * @param options chart options, generated merging all defaults.
	 * @return an array of legend labels.
	 */
	static native ArrayObject generateDefaultLabels(Chart chart, NativeObject options);

	// -----------------
	// EVENTS callback
	// -----------------

	/**
	 * Invokes the legend event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param chart chart instance, used as function context
	 * @param event native object of event wrapper from user interface
	 * @param item legend item native
	 */
	static native void invokeDefaultLegendEvent(NativeObject options, String key, Chart chart, NativeObject event, NativeObject item);

	/**
	 * Invokes the chart event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param chart chart instance, used as function context
	 * @param event native object of event wrapper from user interface
	 * @param items array of datasets native objects
	 */
	static native void invokeDefaultChartEvent(NativeObject options, String key, Chart chart, NativeObject event, ArrayObject items);

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param chart chart instance, used to get the tooltip
	 * @param elements array of active tooltip elements
	 * @param point synthetic event position used in positioning
	 */
	static native void setTooltipActiveElements(Chart chart, ArrayObject elements, NativeObject point);

	/**
	 * Returns the active tooltip elements for the chart.
	 * 
	 * @param chart chart instance, used to get the tooltip
	 * @return the array of active tooltip elements
	 */
	static native ArrayObject getTooltipActiveElements(Chart chart);

	/**
	 * Returns the subtitle elements from the chart.
	 * 
	 * @param chart chart instance, used to get the subtitle
	 * @return the native object of subtitle
	 */
	static native NativeObject getSubtitle(Chart chart);

}