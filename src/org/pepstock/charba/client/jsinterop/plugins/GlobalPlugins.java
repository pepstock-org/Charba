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
package org.pepstock.charba.client.jsinterop.plugins;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Configuration;
import org.pepstock.charba.client.jsinterop.Plugin;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;

/**
 * Global configuration to set plugins at global level.<br>
 * It maps the CHART.JS object of default, <code>chart.plugins</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class GlobalPlugins {

	// list of global plugins set by user (not OOTB)
	// K = plugin id, V = plugin instance
	private final Map<String, GlobalPlugin> pluginIds = new HashMap<String, GlobalPlugin>();
	// native object of plugins
	private final NativePlugins plugins;

	/**
	 * Builds the object by the native object which maps <code>chart.plugins</code>
	 * 
	 * @param plugins the native object which maps <code>chart.plugins</code>
	 */
	public GlobalPlugins(NativePlugins plugins) {
		this.plugins = plugins;
	}

	/**
	 * Registers a plugin as global, to apply to all charts.
	 * 
	 * @param plugin plugin instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin
	 *         id of plugin instance.
	 * @throws InvalidPluginIdException if the plugin id is not correct.
	 */
	public boolean register(Plugin plugin) throws InvalidPluginIdException {
		// checks the plugin id
		PluginIdChecker.check(plugin.getId());
		// checks if ID is already registered
		if (getIds().contains(plugin.getId())) {
			return false;
		}
		// creates a java script object, wrapper of the plugin
		GlobalPlugin wPlugin = new GlobalPlugin(plugin);
		plugins.register(wPlugin.getNativeObject());
		// stores the id and object into a map
		pluginIds.put(plugin.getId(), wPlugin);
		return true;
	}

	/**
	 * Unregisters a global plugin. This is possible ONLY for plugins added as custom ones.
	 * 
	 * @param pluginId plugin instance
	 * @return <code>true</code> if unregistered, otherwise <code>false</code> if the plugin is not a custom one.
	 * @throws InvalidPluginIdException if the plugin id is not correct.
	 */
	public boolean unregister(String pluginId) throws InvalidPluginIdException {
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// checks if ID is already registered on custom one
		if (!pluginIds.containsKey(pluginId)) {
			return false;
		}
		// gets plugins ids requesting to CHART.JS.
		ArrayObject existingPlugins = plugins.getAll();
		// scans ids
		for (int i = 0; i < existingPlugins.length(); i++) {
			// creates the plugin reference
			PluginReference reference = new PluginReference(existingPlugins.get(i));
			if (reference.getId() != null && reference.getId().equalsIgnoreCase(pluginId)) {
				// unregister the plugin
				plugins.unregister(reference);
				// removes the plugin
				pluginIds.remove(pluginId);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets all global registered plugins ids.
	 * 
	 * @return all global registered plugins ids.
	 */
	public Set<String> getIds() {
		// gets plugins ids requesting to CHART.JS.
		ArrayObject existingPlugins = plugins.getAll();
		// creates a set of strings
		final Set<String> pluginsIds = new HashSet<String>();
		// checks the result from CHART.JS
		if (existingPlugins != null && existingPlugins.length() > 0) {
			// scans ids
			for (int i = 0; i < existingPlugins.length(); i++) {
				// creates the reference
				PluginReference reference = new PluginReference(existingPlugins.get(i));
				// adds plugin id
				pluginsIds.add(reference.getId());
			}
		}
		return pluginsIds;
	}

	/**
	 * Invokes the on configuration method to inform the plugins that the chart is going to be initialized.
	 * 
	 * @param config configuration item. Added only to reduce visibility of public method.
	 * @param chart instance of the chart
	 */
	public void onChartConfigure(Configuration config, AbstractChart<?, ?> chart) {
		// scans all plugins
		for (Entry<String, GlobalPlugin> entry : pluginIds.entrySet()) {
			try {
				// checks if plugin is enabled
				if (chart.getOptions().getPlugins().isEnabled(entry.getKey())) {
					// calls on configure method
					entry.getValue().onConfigure(chart);
				}
			} catch (InvalidPluginIdException e) {
				// do nothing
			}
		}
	}
}
