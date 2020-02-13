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
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.annotation.AnnotationOptionsFactory.AnnotationDefaultsOptionsFactory;
import org.pepstock.charba.client.resources.ResourceName;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point of <a href="https://github.com/chartjs/chartjs-plugin-annotation">ANNOTATION plugin</a> to enable the plugin.<br>
 * The ANNOTATION plugin draws lines and boxes on the chart area.<br>
 * The ANNOTATION plugin plugin works with line, bar, scatter and bubble charts that use linear, logarithmic, time,
 * or category scales.<br>
 * The ANNOTATION plugin plugin will not work on any chart that does not have exactly two axes, including pie, radar,
 * and polar area charts.
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

	/**
	 * To avoid any instantiation
	 */
	private AnnotationPlugin() {
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
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// injects ANNOTATION plugin
		Injector.ensureInjected(RESOURCE);
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}

}
