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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to manage CHART.JS plugins.<br>
 * This wrapper is necessary to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsPluginHelper {
	// static instance for singleton
	private static final JsPluginHelper INSTANCE = new JsPluginHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsPluginHelper() {
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
	static JsPluginHelper get() {
		return INSTANCE;
	}

	/**
	 * Register new plugin.
	 * 
	 * @param object plugin java script instance
	 */
	void register(WrapperPlugin object) {
		// checks if argument is consistent
		if (object != null) {
			// registers plugin
			NativeJsPluginHelper.register(object.nativeObject());
		}
	}

	/**
	 * Unregister an existing plugin.
	 * 
	 * @param object plugin java script instance
	 */
	void unregister(PluginReference object) {
		// checks if argument is consistent
		if (object != null) {
			// unregisters plugin
			NativeJsPluginHelper.unregister(object.nativeObject());
		}
	}

	/**
	 * Returns all registered plugins as object.
	 * 
	 * @return all registered plugins as object
	 */
	NativeObject getAll() {
		return NativeJsPluginHelper.getAll();
	}
}
