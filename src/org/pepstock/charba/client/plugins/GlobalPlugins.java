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
package org.pepstock.charba.client.plugins;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.ArrayObject;

/**
 * Global configuration to set plugins at global level.<br>
 * It maps the CHART.JS object of default, <code>chart.plugins</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GlobalPlugins {

	// list of global plugins set by user (not OOTB)
	// K = plugin id, V = plugin instance
	private final Map<String, WrapperPlugin> pluginIds = new HashMap<>();
	// native object of plugins
	private final NativePlugins plugins;
	// set of embedded plugin ids to disable for charts
	private final Set<String> pluginsToBeDisabled = new HashSet<>();

	/**
	 * Builds the object by the native object which maps <code>chart.plugins</code>
	 * 
	 * @param plugins the native object which maps <code>chart.plugins</code>
	 */
	public GlobalPlugins(NativePlugins plugins) {
		// checks if native plugins is consistent
		if (plugins == null) {
			// if not, exception
			throw new IllegalArgumentException("Native plugins instance is null");
		}
		this.plugins = plugins;
	}

	/**
	 * Registers a plugin as global, to apply to all charts.
	 * 
	 * @param plugin plugin instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin id of plugin instance.
	 */
	public boolean register(Plugin plugin) {
		// checks if plugin is consistent
		if (plugin != null) {
			// checks the plugin id
			PluginIdChecker.check(plugin.getId());
			// checks if ID is already registered
			if (getIds().contains(plugin.getId())) {
				return false;
			}
			// creates a java script object, wrapper of the plugin
			WrapperPlugin wPlugin = new WrapperPlugin(plugin);
			plugins.register(wPlugin.nativeObject());
			// stores the id and object into a map
			pluginIds.put(plugin.getId(), wPlugin);
			return true;
		}
		// if here, plugin instance is not consistent
		return false;
	}

	/**
	 * Unregisters a global plugin. This is possible ONLY for plugins added as custom ones.
	 * 
	 * @param pluginId plugin instance
	 * @return <code>true</code> if unregistered, otherwise <code>false</code> if the plugin is not a custom one.
	 */
	public boolean unregister(String pluginId) {
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
				plugins.unregister(reference.nativeObject());
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
		final Set<String> pluginsIds = new HashSet<>();
		// checks the result from CHART.JS
		if (existingPlugins != null && !existingPlugins.isEmpty()) {
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
	 * Setting <code>false</code> for plugin id, the global plugin is disable to all charts and to activate the plugin on a specific chart, is it enough to enable the plugin by
	 * options.
	 * 
	 * @param pluginId plug id to enable
	 * @param enable <code>true</code> to enable to all charts, otherwise <code>false</code>.
	 */
	public void setEnabledAllCharts(String pluginId, boolean enable) {
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// gets all global registered plugin
		Set<String> currentIds = getIds();
		// scans all
		for (String id : currentIds) {
			// if already registered plugin id is the
			// same of the argument
			if (id.equalsIgnoreCase(pluginId)) {
				// if the argument is to enable
				if (enable) {
					// removes the plugin id to the cache to maintain
					// plugin id to disable to the charts
					pluginsToBeDisabled.remove(pluginId);
				} else {
					// adds to the set and it will disable to all charts
					pluginsToBeDisabled.add(pluginId);
				}
				// finish!
				return;
			}
		}
	}

	/**
	 * Returns <code>true</code> if the plugin is enabled to all charts, otherwise <code>false</code>.
	 * 
	 * @param pluginId plug id to check
	 * @return <code>true</code> if the plugin is enabled to all charts, otherwise <code>false</code>.
	 */
	public boolean isEnabledAllCharts(String pluginId) {
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		return !pluginsToBeDisabled.contains(pluginId);
	}

	/**
	 * Invokes the on configuration method to inform the plugins that the chart is going to be initialized.
	 * 
	 * @param config configuration item. Added only to reduce visibility of public method.
	 * @param chart instance of the chart
	 */
	public void onChartConfigure(Configuration config, IsChart chart) {
		// checks if config and chart are consistent
		if (config == null || !IsChart.isValid(chart)) {
			// otherwise do nothing
			return;
		}
		// sets into chart all global plugins to be disable
		for (String id : pluginsToBeDisabled) {
			// if the plugin does not have any options or is not disable by options
			if (!chart.getOptions().getPlugins().hasEnabled(id)) {
				// disables the plugin at chart level
				chart.getOptions().getPlugins().setEnabled(id, false);
			}
		}
		// scans all plugins
		for (Entry<String, WrapperPlugin> entry : pluginIds.entrySet()) {
			// checks if plugin is forcedly disabled
			if (!chart.getOptions().getPlugins().isForcedlyDisabled(entry.getKey())) {
				// if here, the plugin is not disabled
				// calls on configure method
				entry.getValue().onConfigure(chart);
			}
		}
	}
}
