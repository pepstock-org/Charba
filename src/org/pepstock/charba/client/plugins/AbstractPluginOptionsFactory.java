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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;

/**
 * Factory to get the plugin options (form chart, from datasets or from default global ones) related to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractPluginOptionsFactory<T extends AbstractPluginOptions> {

	// plugin id
	private final String pluginId;

	/**
	 * Creates the object with plugin ID.
	 * 
	 * @param pluginId plugin id
	 */
	protected AbstractPluginOptionsFactory(String pluginId) {
		// checks plugin id
		PluginIdChecker.check(pluginId);
		// stores plugin id
		this.pluginId = pluginId;
	}

	/**
	 * Returns the plugin id related to this options.
	 * 
	 * @return the plugin id related to this options
	 */
	public final String getPluginId() {
		return pluginId;
	}

	/**
	 * Creates a plugin options by a native object which is containing the options values and its defaults.
	 * 
	 * @param nativeObject native object which is containing the options
	 * @param defaultValues the defaults values for the plugin options
	 * @return a plugin options instance
	 */
	public abstract T create(NativeObject nativeObject, IsDefaultPlugins defaultValues);

	/**
	 * Loads the default plugin options from defaults.<br>
	 * If factory, passed as argument, is <code>null</code>, returns <code>null</code>.
	 * 
	 * @param defaultsPlugins default values to use to load the plugin options
	 * @param factory factory to load options
	 * @param <G> type of native object container
	 * @return the defaults plugin options or new options instance if not exist. If factory is <code>null</code>, returns <code>null</code>.
	 */
	protected final <G extends AbstractPluginOptions> G loadDefaultsPluginOptions(IsDefaultPlugins defaultsPlugins, AbstractPluginOptionsFactory<G> factory) {
		// checks if factory and defaults options are consistent
		if (factory != null && defaultsPlugins != null) {
			// checks if the default global options has been added for the plugin
			if (defaultsPlugins.hasOptions(pluginId)) {
				// reads the default default global options
				return defaultsPlugins.getOptions(pluginId, factory);
			} else {
				// if here, no default global option
				// then the plugin will use the static defaults
				return factory.create(null, defaultsPlugins);
			}
		}
		// if here the factory is not consistent
		// then returns null
		return null;
	}

}