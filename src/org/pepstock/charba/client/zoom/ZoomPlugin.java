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
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.ResourceName;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.zoom.ZoomOptionsFactory.ZoomDefaultsOptionsFactory;

/**
 * Entry point of <a href="https://github.com/chartjs/chartjs-plugin-zoom">ZOOM plugin</a> to enable the plugin.<br>
 * The ZOOM plugin is highly customizable CHART.JS plugin that is zooming data of charts.<br>
 * The ZOOM plugin plugin has got a dependency with javascript utility <a href="https://github.com/hammerjs/hammer.js">hammer</a>, utility used for gesture recognition.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ZoomPlugin {

	/**
	 * Plugin ID <b>"zoom"</b>.
	 */
	public static final String ID = ResourceName.ZOOM_PLUGIN.value();

	/**
	 * Zoom options factory
	 */
	public static final ZoomOptionsFactory FACTORY = new ZoomOptionsFactory();
	// internal defaults options factory
	static final ZoomDefaultsOptionsFactory DEFAULTS_FACTORY = new ZoomDefaultsOptionsFactory();
	// injectable resource for plugin
	private static final ZoomPluginResource RESOURCE = new ZoomPluginResource();
	// injectable resource for plugin for additional library
	private static final ZoomPluginHammerResource RESOURCE_HAMMER = new ZoomPluginHammerResource();
	// singleton instance
	private static final ZoomPlugin INSTANCE = new ZoomPlugin();
	// original defaults
	private ZoomOptions defaults = null;

	/**
	 * To avoid any instantiation
	 */
	private ZoomPlugin() {
		// do nothing
	}
	
	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	static ZoomPlugin get() {
		return INSTANCE;
	}

	/**
	 * Returns the original defaults of plugin.
	 * 
	 * @return the original defaults of plugin
	 */
	ZoomOptions getDefaults() {
		return defaults;
	}

	/**
	 * Sets the original defaults of plugin.
	 * 
	 * @param defaults the original defaults of plugin
	 */
	void setDefaults(ZoomOptions defaults) {
		this.defaults = defaults;
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
		enable(enableToAllCharts, true);
	}

	/**
	 * Inject the plugin and by the argument decides to enable the plugin to all charts or not.
	 * 
	 * @param enableToAllCharts by <code>true</code> the plugin will be enabled to all charts, otherwise <code>false</code>.
	 * @param enableHammerInjection if <code>false</code>, HammerJs library will not be injected
	 */
	public static void enable(boolean enableToAllCharts, boolean enableHammerInjection) {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// checks if hammer must be injected
		if (enableHammerInjection) {
			// injects HAMMER library
			Injector.ensureInjected(RESOURCE_HAMMER);
		}
		// injects ZOOM plugin
		Injector.ensureInjected(RESOURCE);
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
		// ------------------------------------
		// RETRIEVE defaults set by plugin OOTB
		// ------------------------------------
		ZoomOptions defaults;
		// checks if there is an options
		if (Defaults.get().getGlobal().getPlugins().hasOptions(ID)) {
			// gets the original defaults
			ZoomOptions originalDefaults = Defaults.get().getGlobal().getPlugins().getOptions(ID, DEFAULTS_FACTORY);
			// clones the native object
			// in order to preserve this defaults
			NativeObject objectDefaults = Helpers.get().clone(originalDefaults.nativeObject());
			// creates the defaults
			defaults = new ZoomOptions(DefaultOptions.INSTANCE, objectDefaults);
		} else {
			// no defaults has been set
			// then a completely empty object as default
			defaults = new ZoomOptions();
		}
		// stores the defaults
		ZoomPlugin.get().setDefaults(defaults);
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 */
	public static void resetZoom(IsChart chart) {
		// check if chart is consistent and zoom plugin is acitvated
		if (IsChart.isConsistent(chart) && Charts.hasNative(chart) && chart.getOptions().getPlugins().isEnabled(ID)) {
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
			IsDefaultOptions defaultValues = chart.getDefaultChartOptions().getPlugins().getOptions(ZoomPlugin.ID, ZoomPlugin.DEFAULTS_FACTORY);
			// creates a drag object by the default
			return createDrag(defaultValues);
		}
		// creates a drag object with standard defaults
		return createDrag(DefaultOptions.INSTANCE);
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
			IsDefaultOptions defaultOptions = Defaults.get().getGlobal().getPlugins().getOptions(ID, DEFAULTS_FACTORY);
			// creates a drag object by the default
			return createDrag(defaultOptions);
		}
		// creates a drag object with standard defaults
		return createDrag(DefaultOptions.INSTANCE);
	}

	/**
	 * Creates new customized drag-to-zoom effect.
	 * 
	 * @param defaultValues default options to use for drag initialization
	 * @return new customized drag-to-zoom effect
	 */
	private static Drag createDrag(IsDefaultOptions defaultValues) {
		// creates a drag object by the default if there is
		return new Drag(defaultValues.getZoom().getDrag());
	}

}
