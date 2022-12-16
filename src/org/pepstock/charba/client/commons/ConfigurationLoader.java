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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.Configuration;

/**
 * Utility to load java script object in the CHART-JS configuration.<br>
 * This utility needs to reduce visibility on java script objects for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ConfigurationLoader {

	/**
	 * To avoid any instantiation
	 */
	private ConfigurationLoader() {
		// do nothing
	}

	/**
	 * Loads the OPTIONS in the configuration CHART.JS object.
	 * 
	 * @param configuration CHART.JS object for configuration
	 * @param options chart options
	 */
	public static void loadOptions(Configuration configuration, NativeObjectContainer options) {
		// checks if configuration is consistent
		Checker.checkIfValid(configuration, "Configuration");
		// checks if options are consistent
		Checker.checkIfValid(options, "Options");
		// configures it
		configuration.setOptions(options.getNativeObject());
	}

	/**
	 * Loads the DATA in the configuration CHART.JS object.
	 * 
	 * @param configuration CHART.JS object for configuration
	 * @param data datasets configuration
	 */
	public static void loadData(Configuration configuration, NativeObjectContainer data) {
		// checks if configuration is consistent
		Checker.checkIfValid(configuration, "Configuration");
		// checks if data are consistent
		Checker.checkIfValid(data, "Data configuration");
		// configures it
		configuration.setData(data.getNativeObject());
	}

}