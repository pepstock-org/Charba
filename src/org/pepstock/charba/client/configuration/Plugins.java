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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Definitions about plugins options.<br>
 * This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Plugins extends ConfigurationOptionsContainer {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Plugins(ConfigurationOptions options) {
		super(options);
	}

	/**
	 * Returns the unmodifiable list of registered plugin ids.
	 * 
	 * @return the unmodifiable list of registered plugin ids
	 */
	public final List<Key> getAllIds() {
		return getConfiguration().getPlugins().getAllIds();
	}

	/**
	 * Sets if a plugin must be enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a plugin.
	 */
	public void setEnabled(String pluginId, boolean enabled) {
		getConfiguration().getPlugins().setEnabled(pluginId, enabled);
	}

	/**
	 * Sets if a default plugin must be enabled or not.
	 * 
	 * @param plugin default plugin instance.
	 * @param enabled <code>false</code> disable a default plugin.
	 */
	public void setEnabled(DefaultPluginId plugin, boolean enabled) {
		// checks if plugin is consistent
		if (Key.isValid(plugin)) {
			// creates the envelop
			ConfigurationEnvelop<DefaultPluginId> envelop = new ConfigurationEnvelop<>(plugin);
			getConfiguration().getPlugins().setEnabled(envelop, enabled);
		}
	}

	/**
	 * Returns if a plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a plugin is not enabled otherwise <code>true</code>.
	 */
	public boolean isEnabled(String pluginId) {
		return getConfiguration().getPlugins().isEnabled(pluginId);
	}

	/**
	 * Returns if a default plugin is enabled or not.
	 * 
	 * @param pluginId default plugin id.
	 * @return <code>false</code> if a default plugin is not enabled otherwise <code>true</code>.
	 */
	public boolean isEnabled(DefaultPluginId pluginId) {
		return getConfiguration().getPlugins().isEnabled(pluginId);
	}

	/**
	 * Returns if a global plugin has been set or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin has not been set otherwise <code>true</code>.
	 */
	public boolean hasEnabled(String pluginId) {
		return getConfiguration().getPlugins().hasEnabled(pluginId);
	}

	/**
	 * Removes the plugin options.
	 * 
	 * @param pluginId plugin id.
	 */
	public void removeOptions(String pluginId) {
		getConfiguration().getPlugins().removeOptions(pluginId);
	}

	/**
	 * Sets an empty configuration for the plugin.<br>
	 * The plugin will use the default configuration
	 * 
	 * @param pluginId plugin id.
	 */
	public void setEmptyOptions(String pluginId) {
		getConfiguration().getPlugins().setEmptyOptions(pluginId);
	}

	/**
	 * Sets the plugin options.
	 * 
	 * @param options plugin options used to configure the plugin
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(T options) {
		getConfiguration().getPlugins().setOptions(options);
	}

	/**
	 * Sets the plugin options.<br>
	 * If passed options is null, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options plugin options used to configure the plugin.<br>
	 *            Pass <code>null</code> to remove the configuration if exist.
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(String pluginId, T options) {
		getConfiguration().getPlugins().setOptions(pluginId, options);
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public boolean hasOptions(String pluginId) {
		return getConfiguration().getPlugins().hasOptions(pluginId);
	}

	/**
	 * Returns the plugin options, if exist.<br>
	 * It uses a factory instance to create a plugin options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a plugin options
	 * @param <T> type of plugin options to return
	 * @return plugin options used to configure the plugin or an empty object if not exist.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public <T extends AbstractPluginOptions> T getOptions(AbstractPluginOptionsFactory<T> factory) {
		return getConfiguration().getPlugins().getOptions(factory);
	}

	/**
	 * Returns the plugin options, if exist.<br>
	 * It uses a factory instance to create a plugin options.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a plugin options
	 * @param <T> type of plugin options to return
	 * @return plugin options used to configure the plugin or an empty object if not exist.
	 */
	public <T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		// returns the configuration by plugin id.
		return getConfiguration().getPlugins().getOptions(pluginId, factory);
	}

}