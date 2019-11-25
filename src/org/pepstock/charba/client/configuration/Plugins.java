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

import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.DefaultPlugin;
import org.pepstock.charba.client.options.ExtendedOptions;

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

	private final ExtendedOptions options;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Plugins(ExtendedOptions options) {
		this.options = options;
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a global plugin.
	 */
	public void setEnabled(String pluginId, boolean enabled) {
		options.getPlugins().setEnabled(pluginId, enabled);
	}

	/**
	 * Sets if a default CHART.JS plugin must be enabled or not.
	 * 
	 * @param plugin default CHART.JS plugin instance.
	 * @param enabled <code>false</code> disable a default CHART.JS plugin.
	 */
	public void setEnabled(DefaultPlugin plugin, boolean enabled) {
		options.getPlugins().setEnabled(plugin, enabled);
	}

	/**
	 * Returns if a global plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 */
	public boolean isEnabled(String pluginId) {
		return options.getPlugins().isEnabled(pluginId);
	}

	/**
	 * Returns if a global plugin is enabled or not, forced directly by global plugin manager
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if a global plugin is not enabled otherwise <code>false</code>.
	 */
	public boolean isForcedlyDisabled(String pluginId) {
		return options.getPlugins().isForcedlyDisabled(pluginId);
	}

	/**
	 * Returns if a default CHART.JS plugin is enabled or not, forced directly by global plugin manager
	 * 
	 * @param plugin a default CHART.JS plugin.
	 * @return <code>true</code> if a default CHART.JS plugin is not enabled otherwise <code>false</code>.
	 */
	public boolean isForcedlyDisabled(DefaultPlugin plugin) {
		return options.getPlugins().isForcedlyDisabled(plugin);
	}

	/**
	 * Returns if a global plugin has been set or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin has not been set otherwise <code>true</code>.
	 */
	public boolean hasEnabled(String pluginId) {
		return options.getPlugins().hasEnabled(pluginId);
	}

	/**
	 * Sets the plugin options. If passed options is null, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param object java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if
	 *            exist.
	 * @param <T> type of native object container to store
	 */
	public <T extends NativeObjectContainer> void setOptions(String pluginId, T object) {
		options.getPlugins().setOptions(pluginId, object);
	}

	/**
	 * Sets the plugin options as array. If passed options is null, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param objects list of native object container used to configure the plugin. Pass <code>null</code> to remove the
	 *            configuration if exist.
	 * @param <T> type of native object container to store
	 */
	public <T extends NativeObjectContainer> void setOptions(String pluginId, List<T> objects) {
		options.getPlugins().setOptions(pluginId, objects);
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public boolean hasOptions(String pluginId) {
		return options.getPlugins().hasOptions(pluginId);
	}

	/**
	 * Returns the options type.
	 * 
	 * @param pluginId plugin id.
	 * @return the options type
	 */
	public ObjectType getOptionsType(String pluginId) {
		return options.getPlugins().getOptionsType(pluginId);
	}

	/**
	 * Returns the plugin options, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to configure the plugin or an empty object if not exist.
	 */
	public <T extends NativeObjectContainer> T getOptions(String pluginId, NativeObjectContainerFactory<T> factory) {
		// returns the configuration by plugin id.
		return options.getPlugins().getOptions(pluginId, factory);
	}

	/**
	 * Returns the plugin options as list of object containers, if exist. It uses a factory instance to create a native object
	 * container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return the plugin options as list of object containers or empty list if not exist.
	 */
	public <T extends NativeObjectContainer> List<T> getOptionsAsList(String pluginId, NativeObjectContainerFactory<T> factory) {
		return options.getPlugins().getOptionsAsList(pluginId, factory);
	}

}