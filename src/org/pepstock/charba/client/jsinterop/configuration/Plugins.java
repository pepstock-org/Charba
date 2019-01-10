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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;
import org.pepstock.charba.client.jsinterop.plugins.InvalidPluginIdException;

/**
 * Definitions about plugins options. This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Plugins {

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
	 * @param enabled <code>false</code> disable a gloabl plugin.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public void setEnabled(String pluginId, boolean enabled) throws InvalidPluginIdException {
		options.getPlugins().setEnabled(pluginId, enabled);
	}

	/**
	 * Returns if a global plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public boolean isEnabled(String pluginId) throws InvalidPluginIdException {
		return options.getPlugins().isEnabled(pluginId);
	}

	/**
	 * Sets the plugin options. If passed options is null, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if
	 *            exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public <T extends NativeObjectContainer> void setOptions(String pluginId, T object) throws InvalidPluginIdException {
		options.getPlugins().setOptions(pluginId, object);
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public boolean hasOptions(String pluginId) throws InvalidPluginIdException {
		return options.getPlugins().hasOptions(pluginId);
	}

	/**
	 * Returns the plugin options, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * * @param factory factory instance to create a native object container.
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public <T extends NativeObjectContainer> T getOptions(String pluginId, NativeObjectContainerFactory<T> factory) throws InvalidPluginIdException {
		// returns the configuration by plugin id.
		return options.getPlugins().getOptions(pluginId, factory);
	}

}