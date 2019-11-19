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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import com.google.gwt.dom.client.NativeEvent;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to
 * invoke CHART.JS callbacks, provided out of the box, the default one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_CALLBACKS_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsCallbacksHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsCallbacksHelper() {
		// do nothing
	}

	/**
	 * Returns an HTML string of a legend for that chart with the callback provided by CHART.JS out of the box, invoking
	 * <code>legendCallback</code> function property.
	 * 
	 * @param chart chart instance
	 * @param options chart options, generated merging all defaults.
	 * @return the HTML legend.
	 */
	static native String generateDefaultLegend(Chart chart, NativeObject options);

	/**
	 * Returns an unmodifiable list of legend labels for that chart with the callback provided by CHART.JS out of the box,
	 * invoking <code>generateLabels</code> function property.
	 * 
	 * @param chart chart instance
	 * @param options chart options, generated merging all defaults.
	 * @return an array of legend labels.
	 */
	static native ArrayObject generateDefaultLabels(Chart chart, NativeObject options);

	/**
	 * Invokes the legend event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param chart chart instance, used as function context
	 * @param event native event from user interface
	 * @param item legend item native
	 */
	static native void invokeDefaultLegendEvent(NativeObject options, String key, Chart chart, NativeEvent event, NativeObject item);

	/**
	 * Invokes the chart event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param chart chart instance, used as function context
	 * @param event native event from user interface
	 * @param items array of datasets native objects
	 */
	static native void invokeDefaultChartEvent(NativeObject options, String key, Chart chart, NativeEvent event, ArrayObject item);

}
