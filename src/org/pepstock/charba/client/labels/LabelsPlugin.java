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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.labels.LabelsOptionsFactory.LabelsDefaultsOptionsFactory;
import org.pepstock.charba.client.resources.ResourceName;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point of <a href="https://github.com/emn178/chartjs-plugin-labels">LABELS plugin</a> with some static utilities to enable it and to get and set options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsPlugin {

	/**
	 * Plugin ID <b>"labels"</b>.
	 */
	public static final String ID = ResourceName.LABELS_PLUGIN.value();

	/**
	 * Data labels options factory
	 */
	public static final LabelsOptionsFactory FACTORY = new LabelsOptionsFactory();
	// defaults global options factory
	static final LabelsDefaultsOptionsFactory DEFAULTS_FACTORY = new LabelsDefaultsOptionsFactory();
	// injectable resource for plugin
	private static final LabelsPluginResource RESOURCE = new LabelsPluginResource();

	/**
	 * To avoid any instantiation
	 */
	private LabelsPlugin() {
		// do nothing
	}

	/**
	 * Inject the plugin but disables in the all charts waiting for the specific statement for each chart.
	 */
	public static void enable() {
		enable(false);
	}

	/**
	 * Inject the plugin and by the argument decides to enable the plugin to all charts or not.
	 * 
	 * @param enableToAllCharts by <code>true</code> the plugin will be enabled to all charts, otherwise <code>false</code>.
	 */
	public static void enable(boolean enableToAllCharts) {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// injects LABELS plugin
		Injector.ensureInjected(RESOURCE);
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);

	}
}
