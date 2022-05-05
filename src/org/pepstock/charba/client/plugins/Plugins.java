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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.enums.DefaultPluginId;

/**
 * Is the manager of plugins which can manage the list of plugins and returns them as java script object to store in the chart configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins implements ConfigurationElement {

	// list of added plugins
	private final List<AbstractBasePlugin> pluginsInstances = new LinkedList<>();

	/**
	 * Adds a new plugin to the chart, by a container
	 * 
	 * @param container plugin container instance
	 */
	public void add(PluginContainer container) {
		// checks if plugin container is consistent
		if (container != null) {
			// creates envelop
			PluginsEnvelop<Plugin> envelop = new PluginsEnvelop<>();
			// loads the plugin by container
			container.loadPlugin(envelop);
			// registers plugin
			add(envelop.getContent());
		}
	}

	/**
	 * Adds a new plugin to the chart.<br>
	 * If another plugin instance with the same id has been already loaded, it will remove, storing the new one.<br>
	 * If the chart is already initialized, to get this update the chart must be drawn again.
	 * 
	 * @param plugin plugin instance
	 */
	public void add(Plugin plugin) {
		// checks if plugin is consistent
		if (plugin != null) {
			// registers plugin
			addBasePlugin(new WrapperPlugin(plugin));
		}
	}

	/**
	 * Adds a new plugin to the chart, by a container
	 * 
	 * @param container plugin container instance
	 */
	public void add(SmartPluginContainer container) {
		// checks if plugin container is consistent
		if (container != null) {
			// creates envelop
			PluginsEnvelop<SmartPlugin> envelop = new PluginsEnvelop<>();
			// loads the plugin by container
			container.loadPlugin(envelop);
			// registers plugin
			add(envelop.getContent());
		}
	}

	/**
	 * Adds a new plugin to the chart.<br>
	 * If another plugin instance with the same id has been already loaded, it will remove, storing the new one.<br>
	 * If the chart is already initialized, to get this update the chart must be drawn again.
	 * 
	 * @param plugin plugin instance
	 */
	public void add(SmartPlugin plugin) {
		addBasePlugin(plugin);
	}

	/**
	 * Adds a new plugin to the chart.<br>
	 * If another plugin instance with the same id has been already loaded, it will remove, storing the new one.<br>
	 * If the chart is already initialized, to get this update the chart must be drawn again.
	 * 
	 * @param plugin plugin instance
	 */
	private void addBasePlugin(AbstractBasePlugin plugin) {
		// checks if plugin is consistent
		// and the plugin id is not a default one
		if (plugin != null && !DefaultPluginId.is(plugin.getId())) {
			// checks the plugin id
			PluginIdChecker.check(plugin.getId());
			// checks if the plugin is already loaded
			if (has(plugin.getId())) {
				// if here is already loaded
				// then it removes the previous one
				remove(plugin.getId());
			}
			// stores the wrapper in the a list
			pluginsInstances.add(plugin);
		}
	}

	/**
	 * Returns <code>true</code> if a plugin is already added, otherwise <code>false</code>.
	 * 
	 * @param id plugin id to search.
	 * @return <code>true</code> if a plugin is already added, otherwise <code>false</code>
	 */
	public boolean has(String id) {
		// scans all plugins
		Iterator<AbstractBasePlugin> iter = pluginsInstances.iterator();
		while (iter.hasNext()) {
			// gets wrapper
			AbstractBasePlugin plugin = iter.next();
			// if has got the same id
			if (plugin.getId().equalsIgnoreCase(id)) {
				// removes it
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes a plugin from the chart.<br>
	 * If the chart is already initialized, to get this update the chart must be drawn again.
	 * 
	 * @param pluginId plugin id to remove.
	 */
	public void remove(String pluginId) {
		// checks if plugin is consistent
		// and the plugin id is not a default one
		if (pluginId != null && !DefaultPluginId.is(pluginId)) {
			// scans all plugins
			Iterator<AbstractBasePlugin> iter = pluginsInstances.iterator();
			while (iter.hasNext()) {
				// gets wrapper
				AbstractBasePlugin plugin = iter.next();
				// if has got the same id
				if (plugin.getId().equalsIgnoreCase(pluginId)) {
					// removes it
					iter.remove();
				}
			}
		}
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
		// scans all plugins
		for (AbstractBasePlugin entry : pluginsInstances) {
			// calls on configure method
			entry.invokeConfigure(chart);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Configuration)
	 */
	@Override
	public void load(IsChart chart, Configuration configuration) {
		// checks if config and chart are consistent
		if (configuration == null || !IsChart.isValid(chart)) {
			// otherwise do nothing
			return;
		}
		// checks if there is any plugin to configured to chart.js
		if (!pluginsInstances.isEmpty()) {
			// gets the globals plugin IDs
			// checks if ID is already registered
			Set<String> globalPluginIds = Defaults.get().getPlugins().getIds();
			// create a list of plugins
			List<AbstractBasePlugin> pluginsListToSet = new LinkedList<>();
			// adds all java script object of the plugin wrapper
			// scans all plugins
			Iterator<AbstractBasePlugin> iter = pluginsInstances.iterator();
			while (iter.hasNext()) {
				// gets wrapper
				AbstractBasePlugin plugin = iter.next();
				// checks if the plugin is already loaded in the global ones
				if (!globalPluginIds.contains(plugin.getId())) {
					// if not, adds plugin
					pluginsListToSet.add(plugin);
					// checks and add missing configuration
					// if needed
					checkAndAddEmptyConfiguration(chart, plugin.getId());
				} else {
					// removes it if already set in the global
					iter.remove();
				}
			}
			// checks if list of plugins is empty
			if (!pluginsListToSet.isEmpty()) {
				// sets it to configuration object
				configuration.setPlugins(ArrayObject.fromOrEmpty(pluginsListToSet));
			}
		}
	}

	/**
	 * Checks if there is any plugin configuration already set.<br>
	 * If not, adds an empty object.
	 * 
	 * @param chart chart instance
	 * @param id the plugin id
	 */
	private void checkAndAddEmptyConfiguration(IsChart chart, String id) {
		// checks if configuration has been loaded
		if (!chart.getOptions().getPlugins().hasOptions(id)) {
			// stores an empty configuration
			chart.getOptions().getPlugins().setEmptyOptions(id);
		}
	}
}