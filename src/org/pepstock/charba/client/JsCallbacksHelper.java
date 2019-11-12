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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.resources.ResourcesType;

import com.google.gwt.dom.client.NativeEvent;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to invoke CHART.JS callbacks, provided out of the box, the default one.<br>
 * This wrapper is necessary to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsCallbacksHelper {
	// static instance for singleton
	private static final JsCallbacksHelper INSTANCE = new JsCallbacksHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsCallbacksHelper() {
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
	 * @return jsCallbaclhelper instance.
	 */
	static JsCallbacksHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns an HTML string of a legend for that chart with the callback provided by CHART.JS out of the box, invoking <code>legendCallback</code> function property.
	 * 
	 * @param chart chart instance
	 * @param options chart options, generated merging all defaults.
	 * @return the HTML legend or {@link UndefinedValues#STRING} if chart or options are not consistent.
	 */
	String generateDefaultCallback(Chart chart, ChartOptions options) {
		return NativeJsCallbacksHelper.generateDefaultCallback(chart, options.getObject());
	}
	
	/**
	 * Invokes the legend event callbacks, provided out of the box by CHART.JS.
	 * 
	 * @param options chart options, generated merging all defaults.
	 * @param key the key of options which should have the event callback
	 * @param chart chart instance, used as function context
	 * @param event native event from user interface
	 * @param item legend item native  
	 */
	void invokeDefaultLegendEvent(ChartOptions options, Key key, Chart context, NativeEvent event, NativeObject item) {
		if (Key.isValid(key)) {
			NativeJsCallbacksHelper.invokeDefaultLegendEvent(options.getObject(), key.value(), context, event, item);
		}
	}

}
