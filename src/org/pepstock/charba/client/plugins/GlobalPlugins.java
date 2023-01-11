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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.enums.DefaultPluginId;

/**
 * Global configuration to set plugins at global level.<br>
 * It maps the CHART.JS object of default, <code>chart.plugins</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GlobalPlugins {

	// list of global plugins set by user (not OOTB)
	// K = plugin id, V = plugin instance
	private final Map<String, AbstractBasePlugin> pluginIds = new HashMap<>();
	// internal plugins container
	private final InternalPlugins plugins;
	// set of embedded plugin ids to disable for charts
	private final Set<String> pluginsToBeDisabled = new HashSet<>();

	/**
	 * Builds the object by the native object which maps <code>chart.registry.plugins</code>
	 * 
	 * @param envelop envelop from root package to avoid invocation
	 */
	public GlobalPlugins(ChartEnvelop<NativeObject> envelop) {
		// checks if envelop is consistent
		Envelop.checkIfValid(envelop);
		// stores plugins items
		this.plugins = new InternalPlugins(JsPluginHelper.get().getAll());
	}

	/**
	 * Registers a plugin as global, to apply to all charts, by a container
	 * 
	 * @param container plugin container instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin id of plugin instance.
	 */
	public boolean register(PluginContainer container) {
		// checks if plugin container is consistent
		if (container != null) {
			// creates envelop
			PluginsEnvelop<Plugin> envelop = new PluginsEnvelop<>();
			// loads the plugin by container
			container.loadPlugin(envelop);
			// registers plugin
			return register(envelop.getContent());
		}
		// if here, plugin container instance is not consistent
		return false;
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
			// registers plugin
			return registerBasePlugin(new WrapperPlugin(plugin));
		}
		// if here, plugin instance is not consistent
		return false;
	}

	/**
	 * Registers a plugin as global, to apply to all charts, by a container
	 * 
	 * @param container plugin container instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin id of plugin instance.
	 */
	public boolean register(SmartPluginContainer container) {
		// checks if plugin container is consistent
		if (container != null) {
			// creates envelop
			PluginsEnvelop<SmartPlugin> envelop = new PluginsEnvelop<>();
			// loads the plugin by container
			container.loadPlugin(envelop);
			// registers plugin
			return register(envelop.getContent());
		}
		// if here, plugin container instance is not consistent
		return false;
	}

	/**
	 * Registers a plugin as global, to apply to all charts.
	 * 
	 * @param plugin plugin instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin id of plugin instance.
	 */
	public boolean register(SmartPlugin plugin) {
		return registerBasePlugin(plugin);
	}

	/**
	 * Registers a plugin as global, to apply to all charts.
	 * 
	 * @param plugin plugin instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the plugin is already registered with the plugin id of plugin instance.
	 */
	private boolean registerBasePlugin(AbstractBasePlugin plugin) {
		// checks if plugin is consistent and not a default plugin
		if (plugin != null && !DefaultPluginId.is(plugin.getId())) {
			// checks the plugin id
			PluginIdChecker.check(plugin.getId());
			// checks if ID is already registered
			if (getIds().contains(plugin.getId())) {
				return false;
			}
			JsPluginHelper.get().register(plugin);
			// stores the id and object in the a map
			pluginIds.put(plugin.getId(), plugin);
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
		// checks if ID is already registered on custom one or as default one
		if (!pluginIds.containsKey(pluginId) || DefaultPluginId.is(pluginId)) {
			return false;
		}
		// scans ids
		for (Key key : plugins.ids()) {
			// creates the plugin reference
			PluginReference reference = new PluginReference(plugins.plugin(key));
			if (reference.getId() != null && reference.getId().equalsIgnoreCase(pluginId)) {
				// unregister the plugin
				JsPluginHelper.get().unregister(reference);
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
		// creates a set of strings
		final Set<String> pluginsIds = new HashSet<>();
		// scans ids
		for (Key key : plugins.ids()) {
			// stors in the results
			pluginsIds.add(key.value());
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
		// checks if the plugin is a default one
		if (!DefaultPluginId.is(pluginId)) {
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
		// sets in the chart all global plugins to be disable
		for (String id : pluginsToBeDisabled) {
			// if the plugin does not have any options or is not disable by options
			if (!hasEnabled(chart, id)) {
				// disables the plugin at chart level
				chart.getOptions().getPlugins().setEnabled(id, false);
			}
		}
		// scans all plugins
		for (Entry<String, AbstractBasePlugin> entry : pluginIds.entrySet()) {
			// checks if plugin is forcedly disabled
			if (chart.getOptions().getPlugins().isEnabled(entry.getKey())) {
				// if here, the plugin is not disabled
				// calls on configure method
				entry.getValue().invokeConfigure(chart);
			}
		}
	}

	/**
	 * Returns <code>true</code> if the plugin has been configured for the chart or at least a dataset.
	 * 
	 * @param chart chart instance to check
	 * @param id the plugin id.
	 * @return <code>true</code> if the plugin has been configured for the chart or at least a dataset
	 */
	private boolean hasEnabled(IsChart chart, String id) {
		boolean enabled = chart.getOptions().getPlugins().hasEnabled(id);
		// checks if not enabled
		// if global plugin has configured at dataset level
		if (!enabled) {
			// scans datasets
			for (Dataset dataset : chart.getData().getDatasets()) {
				// checks if the dataste has been configured for the plugin
				if (dataset.hasOptions(id)) {
					// if here, the plugin has been configured for dataset
					// then returns true
					return true;
				}
			}
		}
		// returns the enabled
		return enabled;
	}

	/**
	 * Internal class to wrap a native plugins container.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalPlugins extends NativeObjectContainer {

		InternalPlugins(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the list of plugin ids.
		 * 
		 * @return the list of plugin ids.
		 */
		List<Key> ids() {
			return super.keys();
		}

		/**
		 * Returns the plugin instance by its id.
		 * 
		 * @param pluginId plugin id
		 * @return the plugin instance by its id
		 */
		NativeObject plugin(Key pluginId) {
			return getValue(pluginId);
		}

	}
}