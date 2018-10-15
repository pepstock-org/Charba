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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Configuration;
import org.pepstock.charba.client.jsinterop.ConfigurationElement;
import org.pepstock.charba.client.jsinterop.Plugin;

/**
 * Is the manager of plugins which can manage the list of plugins and returns them as java script object to store into chart
 * configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins implements ConfigurationElement{
	// chart instance
	private final AbstractChart<?, ?> chart;
	// list of added plugins
	private final List<InlinePlugin> plugins = new LinkedList<InlinePlugin>();

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public Plugins(AbstractChart<?, ?> chart) {
		this.chart = chart;
	}

	/**
	 * Adds a new plugin to the chart.<br>
	 * If the chart is already initialized, to get this update teh chart must be drawn again.
	 * 
	 * @param plugin plugin instance
	 * @throws InvalidPluginIdException if the plugin id is not correct.
	 */
	public void add(Plugin plugin) throws InvalidPluginIdException {
		// checks the plugin id
		PluginIdChecker.check(plugin.getId());
		// creates a java script object, wrapper of teh plugin
		InlinePlugin wPlugin = new InlinePlugin(chart, plugin);
		// stores the wrapper into a list
		plugins.add(wPlugin);
	}

	/**
	 * Removes a plugin from the chart.<br>
	 * If the chart is already initialized, to get this update teh chart must be drawn again.
	 * 
	 * @param id plugin id to remove.
	 */
	public void remove(String id) {
		// scans all plugins
		Iterator<InlinePlugin> iter = plugins.iterator();
		while (iter.hasNext()) {
			// gets wrapper
			InlinePlugin plugin = iter.next();
			// if has got the same id
			if (plugin.getId().equals(id)) {
				// removes it
				iter.remove();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.ConfigurationElement#load(org.pepstock.charba.client.jsinterop.Configuration)
	 */
	@Override
	public void load(Configuration configuration) {
		if (!plugins.isEmpty()) {
			ArrayPlugin array = new ArrayPlugin();
			// adds all java script object of the plugin wrapper
			for (InlinePlugin plugin : plugins) {
				array.push(plugin.getNativeObject());
			}
			configuration.setPlugins(array);
		}
	}

}