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
import org.pepstock.charba.client.resources.ResourcesType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Entry point of <a href="https://github.com/chartjs/chartjs-plugin-annotation">ANNOTATION plugin</a> to enable the plugin.<br>
 * The {@value AnnotationPlugin#ID} plugin draws lines and boxes on the chart area.<br>
 * The {@value AnnotationPlugin#ID} plugin works with line, bar, scatter and bubble charts that use linear, logarithmic, time,
 * or category scales.<br>
 * The {@value AnnotationPlugin#ID} plugin will not work on any chart that does not have exactly two axes, including pie, radar,
 * and polar area charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnnotationPlugin {
	
	/**
	 * Client bundle to reference ANNOTATION CHART.JS plugin, wrapped by Charba.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface AnnotationClientBundle extends ClientBundle {

		/**
		 * Static reference to extensions java script source code
		 */
		static final AnnotationClientBundle INSTANCE = GWT.create(AnnotationClientBundle.class);

		/**
		 * Contains text representation of native chart <a href="https://github.com/chartjs/chartjs-plugin-annotation">annotation
		 * plugin</a> code.
		 * 
		 * @return chart <a href="https://github.com/chartjs/chartjs-plugin-annotation">annotation plugin</a> code
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-plugin-annotation.min.js")
		TextResource annotationPlugin();
	}

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "annotation";

	/**
	 * Annotation options factory
	 */
	public static final AnnotationOptionsFactory FACTORY = new AnnotationOptionsFactory();
	// default plugin options factory
	static final AnnotationDefaultsOptionsFactory DEFAULTS_FACTORY = new AnnotationDefaultsOptionsFactory();

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
		// Inject Chart.js if not already loaded
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// injects ANNOTATION plugin
		Injector.ensureInjected(AnnotationClientBundle.INSTANCE.annotationPlugin());
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}

}
