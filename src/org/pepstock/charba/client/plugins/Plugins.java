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
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsObjectArrayList;

/**
 * Is the manager of plugins which can manage the list of plugins and returns them as java script object to store into chart
 * configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins {
	// chart instance
	private final AbstractChart<?, ?> chart;
	// list of added plugins
	private final List<WrapperPlugin> plugins = new LinkedList<WrapperPlugin>();

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
		WrapperPlugin wPlugin = new WrapperPlugin(chart, plugin);
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
	 * Creates a java script array (mapped into a list) with all plugins added to the chart.
	 * 
	 * @return a java script array (mapped into a list) with all plugins added to the chart.
	 */
	public JsObjectArrayList<GenericJavaScriptObject> getArrayList() {
		// creates list
		JsObjectArrayList<GenericJavaScriptObject> list = new JsObjectArrayList<GenericJavaScriptObject>();
		// adds all java script object of the plugin wrapper
		for (WrapperPlugin plugin : plugins) {
			list.add(plugin.getObject());
		}
		return list;
	}

}