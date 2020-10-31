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
import org.pepstock.charba.client.commons.ArrayString;
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
@JsType(isNative = true, name = NativeName.JS_CALLBACKS_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsCallbacksHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsCallbacksHelper() {
		// do nothing
	}

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
	
	// -----------------
	// TOOLTIPS callback
	// -----------------
	
	// FIXME to add to externs

	/**
	 * Invokes the default tooltip callback for <code>title, body and footer</code>.
	 *  
	 * @param tooltipModel tooltip model to use as <code>this</code> on calling
	 * @param items array of tooltip items
	 * @param key property key of the callback to invoke
	 * @return an array of string to put into tooltip
	 */
	static native ArrayString invokeDefaultTooltipsForElement(NativeObject tooltipModel, ArrayObject items, String key);

	/**
	 * Invokes the default tooltip callback for <code>beforeLabel, label and afterLabel, labelTextColor</code>.
	 *  
	 * @param tooltipModel tooltip model to use as <code>this</code> on calling
	 * @param items tooltip item instance
	 * @param key property key of the callback to invoke
	 * @return an array of string to put before the label
	 */
	static native String invokeDefaultTooltipsForLabel(NativeObject tooltipModel, NativeObject item, String key);

	/**
	 * Invokes the default tooltip callback for <code>labelColor and labelPointStyle</code>.
	 * 
	 * @param tooltipModel tooltip model to use as <code>this</code> on calling
	 * @param item tooltip item instance
	 * @param key property key of the callback to invoke
	 * @return the label object instance
	 */
	static native NativeObject invokeDefaultTooltipsForLabelObject(NativeObject tooltipModel, NativeObject item, String key);
	
	/**
	 * Returns <code>true</code> if teh default tooltip callbacks node is consistent in order to invoke a callback.
	 * 
	 * @param key property key of the callback to invoke
	 * @return <code>true</code> if teh default tooltip callbacks node is consistent in order to invoke a callback.
	 */
	static native boolean isTooltipCallbacksConsistent(String key);


}
