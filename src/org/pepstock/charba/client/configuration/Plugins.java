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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.DefaultPlugin;
import org.pepstock.charba.client.options.ExtendedOptions;
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
public class Plugins {

	private final ExtendedOptions extendedOptions;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Plugins(ExtendedOptions options) {
		this.extendedOptions = options;
	}

	/**
	 * Returns the unmodifiable list of registered plugin ids.
	 * 
	 * @return the unmodifiable list of registered plugin ids
	 */
	public final List<Key> getAllIds() {
		return extendedOptions.getPlugins().getAllIds();
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a global plugin.
	 */
	public void setEnabled(String pluginId, boolean enabled) {
		extendedOptions.getPlugins().setEnabled(pluginId, enabled);
	}

	/**
	 * Sets if a default CHART.JS plugin must be enabled or not.
	 * 
	 * @param plugin default CHART.JS plugin instance.
	 * @param enabled <code>false</code> disable a default CHART.JS plugin.
	 */
	public void setEnabled(DefaultPlugin plugin, boolean enabled) {
		extendedOptions.getPlugins().setEnabled(plugin, enabled);
	}

	/**
	 * Returns if a global plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 */
	public boolean isEnabled(String pluginId) {
		return extendedOptions.getPlugins().isEnabled(pluginId);
	}

	/**
	 * Returns if a global plugin is enabled or not, forced directly by global plugin manager
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if a global plugin is not enabled otherwise <code>false</code>.
	 */
	public boolean isForcedlyDisabled(String pluginId) {
		return extendedOptions.getPlugins().isForcedlyDisabled(pluginId);
	}

	/**
	 * Returns if a default CHART.JS plugin is enabled or not, forced directly by global plugin manager
	 * 
	 * @param plugin a default CHART.JS plugin.
	 * @return <code>true</code> if a default CHART.JS plugin is not enabled otherwise <code>false</code>.
	 */
	public boolean isForcedlyDisabled(DefaultPlugin plugin) {
		return extendedOptions.getPlugins().isForcedlyDisabled(plugin);
	}

	/**
	 * Returns if a global plugin has been set or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin has not been set otherwise <code>true</code>.
	 */
	public boolean hasEnabled(String pluginId) {
		return extendedOptions.getPlugins().hasEnabled(pluginId);
	}

	/**
	 * Removes the plugin options.
	 * 
	 * @param pluginId plugin id.
	 */
	public void removeOptions(String pluginId) {
		extendedOptions.getPlugins().removeOptions(pluginId);
	}

	/**
	 * Sets the plugin options.
	 * 
	 * @param options plugin options used to configure the plugin
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(T options) {
		extendedOptions.getPlugins().setOptions(options);
	}

	/**
	 * Sets the plugin options as list.<br>
	 * If the list is empty, it does nothing
	 * 
	 * @param options list of plugin options used to configure the plugin.
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(List<T> options) {
		extendedOptions.getPlugins().setOptions(options);
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
		extendedOptions.getPlugins().setOptions(pluginId, options);
	}

	/**
	 * Sets the plugin options as list.<br>
	 * If passed options is null, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options list of plugin options used to configure the plugin.<br>
	 *            Pass <code>null</code> to remove the configuration if exist.
	 * @param <T> type of pugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(String pluginId, List<T> options) {
		extendedOptions.getPlugins().setOptions(pluginId, options);
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public boolean hasOptions(String pluginId) {
		return extendedOptions.getPlugins().hasOptions(pluginId);
	}

	/**
	 * Returns the options type.
	 * 
	 * @param pluginId plugin id.
	 * @return the options type
	 */
	public ObjectType getOptionsType(String pluginId) {
		return extendedOptions.getPlugins().getOptionsType(pluginId);
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
		return extendedOptions.getPlugins().getOptions(factory);
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
		return extendedOptions.getPlugins().getOptions(pluginId, factory);
	}

	/**
	 * Returns the plugin options as list, if exist.<br>
	 * It uses a factory instance to create a plugin options.
	 * 
	 * @param factory factory instance to create a plugin options.
	 * @param <T> type of plugin options to return
	 * @return the plugin options as list or empty list if not exist.
	 */
	public <T extends AbstractPluginOptions> List<T> getOptionsAsList(AbstractPluginOptionsFactory<T> factory) {
		return extendedOptions.getPlugins().getOptionsAsList(factory);
	}

	/**
	 * Returns the plugin options as list, if exist.<br>
	 * It uses a factory instance to create a plugin options.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a plugin options.
	 * @param <T> type of plugin options to return
	 * @return the plugin options as list or empty list if not exist.
	 */
	public <T extends AbstractPluginOptions> List<T> getOptionsAsList(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		return extendedOptions.getPlugins().getOptionsAsList(pluginId, factory);
	}

}