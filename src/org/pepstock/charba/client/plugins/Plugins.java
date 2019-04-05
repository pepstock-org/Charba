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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.Plugin;

/**
 * Is the manager of plugins which can manage the list of plugins and returns them as java script object to store into chart
 * configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins implements ConfigurationElement {

	// list of added plugins
	private final List<WrapperPlugin> plugins = new LinkedList<>();

	/**
	 * Adds a new plugin to the chart.<br>
	 * If the chart is already initialized, to get this update the chart must be drawn again.
	 * 
	 * @param plugin plugin instance
	 */
	public void add(Plugin plugin) {
		// checks the plugin id
		PluginIdChecker.check(plugin.getId());
		// creates a java script object, wrapper of the plugin
		WrapperPlugin wPlugin = new WrapperPlugin(plugin);
		// stores the wrapper into a list
		plugins.add(wPlugin);
	}

	/**
	 * Returns <code>true</code> if a plugin is already added, otherwise <code>false</code>.
	 * 
	 * @param id plugin id to search.
	 * @return <code>true</code> if a plugin is already added, otherwise <code>false</code>
	 */
	public boolean has(String id) {
		// scans all plugins
		Iterator<WrapperPlugin> iter = plugins.iterator();
		while (iter.hasNext()) {
			// gets wrapper
			WrapperPlugin plugin = iter.next();
			// if has got the same id
			if (plugin.getId().equals(id)) {
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
	 * @param id plugin id to remove.
	 */
	public void remove(String id) {
		// scans all plugins
		Iterator<WrapperPlugin> iter = plugins.iterator();
		while (iter.hasNext()) {
			// gets wrapper
			WrapperPlugin plugin = iter.next();
			// if has got the same id
			if (plugin.getId().equals(id)) {
				// removes it
				iter.remove();
			}
		}
	}

	/**
	 * Invokes the on configuration method to inform the plugins that the chart is going to be initialized.
	 * 
	 * @param config configuration item. Added only to reduce visibility of public method.
	 * @param chart instance of the chart
	 */
	public void onChartConfigure(Configuration config, AbstractChart<?, ?> chart) {
		// scans all plugins
		for (WrapperPlugin entry : plugins) {
			// calls on configure method
			entry.onConfigure(chart);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.Configuration)
	 */
	@Override
	public void load(AbstractChart<?, ?> chart, Configuration configuration) {
		// checks if there is any plugin to configured to chart.js
		if (!plugins.isEmpty()) {
			// new array
			ArrayPlugin array = new ArrayPlugin();
			// adds all java script object of the plugin wrapper
			for (WrapperPlugin plugin : plugins) {
				array.push(plugin.getNativeObject());
			}
			// sets it to configuration object
			configuration.setPlugins(array);
		}
	}

}