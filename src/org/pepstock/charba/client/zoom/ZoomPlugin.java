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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.resources.Extensions;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.zoom.ZoomOptionsFactory.ZoomDefaultsOptionsFactory;

/**
 * Entry point of <a href="https://github.com/chartjs/chartjs-plugin-zoom">ZOOM plugin</a> to enable the plugin.<br>
 * The {@value ZoomPlugin#ID} plugin is highly customizable CHART.JS plugin that is zooming data of charts.<br>
 * The {@value ZoomPlugin#ID} plugin has got a dependency with javascript utility
 * <a href="https://github.com/hammerjs/hammer.js">hammer</a>, utility used for gesture recognition.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "zoom";

	/**
	 * Zoom options factory
	 */
	public static final ZoomOptionsFactory FACTORY = new ZoomOptionsFactory(ID);
	// internal defaults options factory
	private static final ZoomDefaultsOptionsFactory DEFAULTS_FACTORY = new ZoomDefaultsOptionsFactory();

	/**
	 * To avoid any instantiation
	 */
	private ZoomPlugin() {
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
		// injects HAMMER plugin
		Injector.ensureInjected(Extensions.INSTANCE.hammerLibrary());
		// injects ZOOM plugin
		Injector.ensureInjected(Extensions.INSTANCE.zoomPlugin());
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 */
	public static void resetZoom(IsChart chart) {
		// check if chart is consistent and zoom plugin is acitvated
		if (IsChart.isConsistent(chart) && Charts.hasNative(chart) && !chart.getOptions().getPlugins().isForcedlyDisabled(ID)) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// resets zoom
			JsZoomHelper.get().resetZoom(nativeChart);
		}
	}
	
	/**
	 * Creates new customized drag-to-zoom effect.
	 * 
	 * @return new customized drag-to-zoom effect
	 */
	public static Drag createDrag() {
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(ID)) {
			// reads the default default global options
			DefaultsOptions defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(ID, DEFAULTS_FACTORY);
			// creates a drag object by the default if there is
			return new Drag(defaultsOptions.getZoom().getDrag());
		}
		// creates a drag object with standard defaults
		return new Drag(new DefaultsDrag());
	}

}
