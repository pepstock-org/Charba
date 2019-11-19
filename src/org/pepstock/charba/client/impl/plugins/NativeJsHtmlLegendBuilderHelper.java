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
import org.pepstock.charba.client.commons.NativeName;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to
 * add and remove listeners to HTML elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_HTML_LEGEND_BUILDER_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsHtmlLegendBuilderHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsHtmlLegendBuilderHelper() {
		// do nothing
	}

	/**
	 * Adds a function as listener to the HTML element.
	 * 
	 * @param event event type to register
	 * @param element HTML element to update adding a listener
	 * @param proxy function to add as listener
	 */
	static native void addEventListener(String event, Element element, Proxy proxy);

	/**
	 * Removes a function as listener from the HTML element.
	 * 
	 * @param event event type to unregister
	 * @param element HTML element to update removing a listener
	 * @param proxy function to remove as listener
	 */
	static native void removeEventListener(String event, Element element, Proxy proxy);

}
