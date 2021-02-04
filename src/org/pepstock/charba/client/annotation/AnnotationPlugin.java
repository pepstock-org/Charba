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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.annotation.AnnotationOptionsFactory.AnnotationDefaultsOptionsFactory;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.ResourceName;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point of <a href="https://github.com/chartjs/chartjs-plugin-annotation">ANNOTATION plugin</a> to enable the plugin.<br>
 * The ANNOTATION plugin draws lines and boxes on the chart area.<br>
 * The ANNOTATION plugin plugin works with line, bar, scatter and bubble charts that use linear, logarithmic, time, or category scales.<br>
 * The ANNOTATION plugin plugin will not work on any chart that does not have exactly two axes, including pie, radar, and polar area charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationPlugin {

	/**
	 * Plugin ID <b>"annotation"</b>.
	 */
	public static final String ID = ResourceName.ANNOTATION_PLUGIN.value();

	/**
	 * Annotation options factory
	 */
	public static final AnnotationOptionsFactory FACTORY = new AnnotationOptionsFactory();
	// default plugin options factory
	static final AnnotationDefaultsOptionsFactory DEFAULTS_FACTORY = new AnnotationDefaultsOptionsFactory();
	// injectable resource for plugin
	private static final AnnotationPluginResource RESOURCE = new AnnotationPluginResource();
	// singleton instance
	private static final AnnotationPlugin INSTANCE = new AnnotationPlugin();
	// original defaults
	private AnnotationOptions defaults = null;

	/**
	 * To avoid any instantiation
	 */
	private AnnotationPlugin() {
		// do nothing
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	static AnnotationPlugin get() {
		return INSTANCE;
	}

	/**
	 * Returns the original defaults of plugin.
	 * 
	 * @return the original defaults of plugin
	 */
	AnnotationOptions getDefaults() {
		return defaults;
	}

	/**
	 * Sets the original defaults of plugin.
	 * 
	 * @param defaults the original defaults of plugin
	 */
	void setDefaults(AnnotationOptions defaults) {
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
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// injects ANNOTATION plugin
		Injector.ensureInjected(RESOURCE);
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
		// ------------------------------------
		// RETRIEVE defaults set by plugin OOTB
		// ------------------------------------
		AnnotationOptions defaults;
		// checks if there is an options
		if (Defaults.get().getGlobal().getPlugins().hasOptions(ID)) {
			// gets the original defaults
			AnnotationOptions originalDefaults = Defaults.get().getGlobal().getPlugins().getOptions(ID, DEFAULTS_FACTORY);
			// clones the native object
			// in order to preserve this defaults
			NativeObject objectDefaults = Helpers.get().clone(originalDefaults.nativeObject());
			// creates the defaults
			defaults = new AnnotationOptions(DefaultOptions.INSTANCE, objectDefaults);
		} else {
			// no defaults has been set
			// then a completely empty object as default
			defaults = new AnnotationOptions();
		}
		// stores the defaults
		AnnotationPlugin.get().setDefaults(defaults);
	}

}
