/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop.commons;

import org.pepstock.charba.client.jsinterop.Configuration;

/**
 * Utility to load java script object into CHART-JS configuration.<br>
 * This utility needs to reduce visibility on java script objects for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class ConfigurationLoader {
	
	/**
	 * To avoid any instantiation
	 */
	private ConfigurationLoader() {
		// do nothing
	}

	/**
	 * Loads the OPTIONS into configuration CHART.JS object.
	 * @param configuration CHART.JS object for configuration
	 * @param options chart options
	 */
	public static void loadOptions(Configuration configuration, NativeObjectContainer options) {
		configuration.setOptions(options.getNativeObject());
	}

	/**
	 * Loads the DATA into configuration CHART.JS object.
	 * @param configuration CHART.JS object for configuration
	 * @param data datasets configuration
	 */
	public static void loadData(Configuration configuration, NativeObjectContainer data) {
		configuration.setData(data.getNativeObject());
	}

}
