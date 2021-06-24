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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.JsHelper;

/**
 * Internal utility for {@link DataLabelsPlugin} to register the plugin globally to Chart.js.<br>
 * This is needed because from the plugin version for Chart.js 3, the plugin doesn't register itself globally anymore and delegate this operation to the user.<br>
 * To maintain the same capabilities of Charba, this object register the plugin globally.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsDataLabelsHelper {

	// static instance for singleton
	private static final JsDataLabelsHelper INSTANCE = new JsDataLabelsHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsDataLabelsHelper() {
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsDataLabelsHelper get() {
		return INSTANCE;
	}

	/**
	 * Register the {@link DataLabelsPlugin} plugin globally to Chart.js.
	 */
	void register() {
		NativeJsDataLabelsHelper.register();
	}

}
