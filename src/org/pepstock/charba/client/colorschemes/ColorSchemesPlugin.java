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
package org.pepstock.charba.client.colorschemes;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.resources.Extensions;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point of <a href="https://github.com/nagix/chartjs-plugin-colorschemes">COLORSCHEMES plugin</a> with some static
 * utilities to enable it and to get and set options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemesPlugin {

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "colorschemes";

	/**
	 * Data labels options factory
	 */
	public static final ColorSchemesOptionsFactory FACTORY = new ColorSchemesOptionsFactory(ID);

	/**
	 * To avoid any instantiation
	 */
	private ColorSchemesPlugin() {
		// do nothing
	}

	/**
	 * Inject the plugin but disables into all charts waiting for the specific statement for each chart.
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
		// Inject Chart.js if not already loaded
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// injects LABELS plugin
		Injector.ensureInjected(Extensions.INSTANCE.colorschemesPlugin());
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}
}
