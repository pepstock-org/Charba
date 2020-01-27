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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.resources.ResourcesType;

import com.google.gwt.dom.client.Element;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to add and remove listeners to HTML elements.<br>
 * This wrapper is necessary to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsHtmlLegendBuilderHelper {
	// static instance for singleton
	private static final JsHtmlLegendBuilderHelper INSTANCE = new JsHtmlLegendBuilderHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsHtmlLegendBuilderHelper() {
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsHtmlLegendBuilderHelper get() {
		return INSTANCE;
	}

	/**
	 * Adds a function as listener to the HTML element.
	 * 
	 * @param event event type to register
	 * @param element HTML element to update adding a listener
	 * @param proxy function to add as listener
	 */
	void addEventListener(Event event, Element element, Proxy proxy) {
		// checks if arguments are consistent
		if (Key.isValid(event) && element != null && proxy != null) {
			NativeJsHtmlLegendBuilderHelper.addEventListener(event.value(), element, proxy);
		}
	}

	/**
	 * Removes a function as listener from the HTML element.
	 * 
	 * @param event event type to unregister
	 * @param element HTML element to update removing a listener
	 * @param proxy function to remove as listener
	 */
	void removeEventListener(Event event, Element element, Proxy proxy) {
		// checks if arguments are consistent
		if (Key.isValid(event) && element != null && proxy != null) {
			NativeJsHtmlLegendBuilderHelper.removeEventListener(event.value(), element, proxy);
		}
	}
}
