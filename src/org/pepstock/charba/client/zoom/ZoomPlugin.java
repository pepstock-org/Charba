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
import org.pepstock.charba.client.options.IsScaleId;
import org.pepstock.charba.client.options.IsTransitionKey;
import org.pepstock.charba.client.plugins.AbstractExtensionPlugin;
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
public final class ZoomPlugin extends AbstractExtensionPlugin<ZoomOptions> {

	/**
	 * Plugin ID <b>"zoom"</b>.
	 */
	public static final String ID = ResourceName.ZOOM_PLUGIN.value();

	/**
	 * Zoom options factory
	 */
	public static final ZoomOptionsFactory FACTORY = new ZoomOptionsFactory();
	/**
	 * Custom transition mode to update the chart by API, "<b>zoom</b>".
	 */
	public static final IsTransitionKey ZOOM_TRANSITION_MODE = IsTransitionKey.create("zoom");
	// internal defaults options factory
	static final ZoomDefaultsOptionsFactory DEFAULTS_FACTORY = new ZoomDefaultsOptionsFactory();
	// injectable resource for plugin
	private static final ZoomPluginResource RESOURCE = new ZoomPluginResource();
	// injectable resource for plugin for additional library
	private static final ZoomPluginHammerResource RESOURCE_HAMMER = new ZoomPluginHammerResource();
	// singleton instance
	private static final ZoomPlugin INSTANCE = new ZoomPlugin();

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
	 * Merges the original defaults of plugin to the options of plugin.
	 * 
	 * @param options the options of plugin where to apply the defaults
	 */
	void mergeDefaults(ZoomOptions options) {
		super.applyingDefaults(options);
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
		ResourcesType.getResources().inject();
		// checks if hammer must be injected
		if (enableHammerInjection) {
			// injects HAMMER library
			Injector.ensureInjected(RESOURCE_HAMMER);
		}
		// injects ZOOM plugin
		Injector.ensureInjected(RESOURCE);
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
		// loads defaults
		ZoomPlugin.get().loadDefaults(DEFAULTS_FACTORY);
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 */
	public static void resetZoom(IsChart chart) {
		resetZoom(chart, null);
	}

	/**
	 * Reset the zoom of chart when {@link ZoomPlugin} is activated.
	 * 
	 * @param chart chart instance to invoke
	 * @param transition update transition mode, could be <code>null</code>
	 */
	public static void resetZoom(IsChart chart, IsTransitionKey transition) {
		// check if chart is consistent and zoom plugin is activated
		if (isReadyForApi(chart)) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// resets zoom
			JsZoomHelper.get().resetZoom(nativeChart, transition);
		}
	}
	
	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 */
	public static void pan(IsChart chart, Point amount) {
		pan(chart, amount, null);
	}

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 * @param transition update transition mode
	 */
	public static void pan(IsChart chart, Point amount, IsTransitionKey transition) {
		// check if chart is consistent and zoom plugin is activated
		if (isReadyForApi(chart)) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// pans
			JsZoomHelper.get().pan(nativeChart, amount, transition);
		}
	}

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 */
	public static void pan(IsChart chart, double amount) {
		pan(chart, amount, null);
	}

	/**
	 * Pans the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of pan to apply
	 * @param transition update transition mode
	 */
	public static void pan(IsChart chart, double amount, IsTransitionKey transition) {
		// check if chart is consistent and zoom plugin is activated
		if (isReadyForApi(chart)) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// pans
			JsZoomHelper.get().pan(nativeChart, amount, transition);
		}
	}

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 */
	public static void zoom(IsChart chart, Point amount) {
		zoom(chart, amount, null);
	}

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 * @param transition update transition mode
	 */
	public static void zoom(IsChart chart, Point amount, IsTransitionKey transition) {
		// check if chart is consistent and zoom plugin is activated
		if (isReadyForApi(chart)) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// zooms
			JsZoomHelper.get().zoom(nativeChart, amount, transition);
		}
	}

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 */
	public static void zoom(IsChart chart, double amount) {
		zoom(chart, amount, null);
	}

	/**
	 * Zooms the chart on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param amount amount of zoom to apply
	 * @param transition update transition mode
	 */
	public static void zoom(IsChart chart, double amount, IsTransitionKey transition) {
		// check if chart is consistent and zoom plugin is activated
		if (isReadyForApi(chart)) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// zooms
			JsZoomHelper.get().zoom(nativeChart, amount, transition);
		}
	}
	
	/**
	 * Zooms the chart scale on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param scaleId scale id to zoom
	 * @param range range (min/max) of scale to zoom
	 */
	public static void zoomScale(IsChart chart, IsScaleId scaleId, ScaleRange range) {
		zoomScale(chart, scaleId, range, null);
	}

	/**
	 * Zooms the chart scale on demand, programmatically.
	 * 
	 * @param chart chart instance to invoke
	 * @param scaleId scale id to zoom
	 * @param range range (min/max) of scale to zoom
	 * @param transition update transition mode
	 */
	public static void zoomScale(IsChart chart, IsScaleId scaleId, ScaleRange range, IsTransitionKey transition) {
		// check if chart is consistent and zoom plugin is activated
		if (isReadyForApi(chart) && IsScaleId.isValid(scaleId) && chart.getNode().getScales().getItems().containsKey(scaleId.value())) {
			// gets native chart instance
			Chart nativeChart = Charts.getNative(chart);
			// zooms scale
			JsZoomHelper.get().zoomScale(nativeChart, scaleId, range, transition);
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

	/**
	 * Checks if the chart is ready to perform the API.<br>
	 * It searches if the chart is consistent and if the plugin has been activated for the chart.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if the chart is ready to perform the API
	 */
	private static boolean isReadyForApi(IsChart chart) {
		return IsChart.isConsistent(chart) && Charts.hasNative(chart) && chart.getOptions().getPlugins().isEnabled(ID);
	}
}
