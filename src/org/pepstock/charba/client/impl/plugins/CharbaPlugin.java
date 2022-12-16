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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Common class for Charba out of the box plugins implementation.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of the plugin options
 */
abstract class CharbaPlugin<T extends AbstractPluginOptions> extends AbstractPlugin {

	// cache to store options in order do not load every time the options
	private final Map<String, T> pluginOptions = new HashMap<>();
	// factory instance
	private final AbstractPluginOptionsFactory<T> factory;

	/**
	 * Creates the object using the plugin id and its options factory.
	 * 
	 * @param id plugin id
	 * @param factory plugin options factory
	 */
	CharbaPlugin(String id, AbstractPluginOptionsFactory<T> factory) {
		super(id);
		this.factory = factory;
	}

	/**
	 * Creates an options instance as default.
	 * 
	 * @return an options instance as default
	 */
	abstract T createDefaultOptionInstance();

	/**
	 * Loads the plugin options from the chart configuration and stores it in the cache.
	 * 
	 * @param chart chart instance
	 * @return the plugin options from the chart configuration
	 */
	final T loadOptions(IsChart chart) {
		// creates options instance
		T pOptions = null;
		// loads chart options for the chart
		IsDefaultScaledOptions options = chart.getWholeOptions();
		// creates the plugin options checking if exists or not
		if (options.getPlugins().hasOptions(getId())) {
			pOptions = options.getPlugins().getOptions(getId(), factory);
		} else {
			pOptions = createDefaultOptionInstance();
		}
		// stores option on the cache
		pluginOptions.put(chart.getId(), pOptions);
		// returns the options
		return pOptions;
	}

	/**
	 * Returns <code>true</code> if plugin options has been loaded.
	 * 
	 * @param chart chart instance
	 * @return <code>true</code> if plugin options has been loaded
	 */
	final boolean hasOptions(IsChart chart) {
		return pluginOptions.containsKey(chart.getId());
	}

	/**
	 * Returns the plugin loaded options.
	 * 
	 * @param chart chart instance
	 * @return the plugin loaded options
	 */
	final T getOptions(IsChart chart) {
		return pluginOptions.get(chart.getId());
	}

	/**
	 * Removes the plugin loaded options.
	 * 
	 * @param chart chart instance
	 */
	final void removeOptions(IsChart chart) {
		pluginOptions.remove(chart.getId());
	}

}