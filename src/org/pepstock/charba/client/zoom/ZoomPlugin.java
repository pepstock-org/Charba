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
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.zoom.ZoomOptionsFactory.ZoomDefaultsOptionsFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

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
	 * Client bundle to reference ZOOM CHART.JS plugin, wrapped by Charba.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface ZoomClientBundle extends ClientBundle {

		/**
		 * Static reference to extensions java script source code
		 */
		static final ZoomClientBundle INSTANCE = GWT.create(ZoomClientBundle.class);

		/**
		 * Contains text representation of native chart <a href="https://github.com/chartjs/chartjs-plugin-zoom">zoom plugin</a>
		 * code.
		 * 
		 * @return chart <a href="https://github.com/chartjs/chartjs-plugin-zoom">zoom plugin</a> code
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH +"chartjs-plugin-zoom.min.js")
		TextResource zoomPlugin();

		/**
		 * Contains text representation of native javascript utility <a href="https://github.com/hammerjs/hammer.js">hammer</a>
		 * code.
		 * 
		 * @return javascript utility <a href="https://github.com/hammerjs/hammer.js">hammer</a> code
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH +"hammer.min.js")
		TextResource hammerLibrary();
	}

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "zoom";

	/**
	 * Zoom options factory
	 */
	public static final ZoomOptionsFactory FACTORY = new ZoomOptionsFactory();
	// internal defaults options factory
	static final ZoomDefaultsOptionsFactory DEFAULTS_FACTORY = new ZoomDefaultsOptionsFactory();

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
		Injector.ensureInjected(ZoomClientBundle.INSTANCE.hammerLibrary());
		// injects ZOOM plugin
		Injector.ensureInjected(ZoomClientBundle.INSTANCE.zoomPlugin());
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
	 * @param chart chart instance to relate to new drag object, for defaults
	 * @return new customized drag-to-zoom effect
	 */
	public static Drag createDrag(IsChart chart) {
		// checks if chart is consistent and if has got any options
		if (IsChart.isConsistent(chart) && chart.getDefaultChartOptions().getPlugins().hasOptions(ZoomPlugin.ID)) {
			// loads the plugin options from default options of chart
			DefaultsOptions defaultsValues = chart.getDefaultChartOptions().getPlugins().getOptions(ZoomPlugin.ID, ZoomPlugin.DEFAULTS_FACTORY);
			// creates a drag object by the default
			return createDrag(defaultsValues);
		}
		// creates a drag object with standard defaults
		return createDrag(DefaultsOptions.DEFAULTS_INSTANCE);
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
			// creates a drag object by the default
			return createDrag(defaultsOptions);
		}
		// creates a drag object with standard defaults
		return createDrag(DefaultsOptions.DEFAULTS_INSTANCE);
	}

	/**
	 * Creates new customized drag-to-zoom effect.
	 * 
	 * @param defaultValues default options to use for drag initialization
	 * @return new customized drag-to-zoom effect
	 */
	private static Drag createDrag(DefaultsOptions defaultValues) {
		// creates a drag object by the default if there is
		return new Drag(defaultValues.getZoom().getDrag());
	}

}
