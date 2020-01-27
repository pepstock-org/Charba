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
import org.pepstock.charba.client.resources.ResourcesType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Entry point of <a href="https://github.com/emn178/chartjs-plugin-labels">LABELS plugin</a> with some static utilities to
 * enable it and to get and set options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsPlugin {

	/**
	 * Client bundle to reference LABELS CHART.JS plugin, wrapped by Charba.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface LabelsClientBundle extends ClientBundle {

		/**
		 * Static reference to extensions java script source code
		 */
		static final LabelsClientBundle INSTANCE = GWT.create(LabelsClientBundle.class);

		/**
		 * Contains text representation of native chart <a href="https://github.com/emn178/chartjs-plugin-labels">labels
		 * plugin</a> code.
		 * 
		 * @return chart <a href="https://github.com/emn178/chartjs-plugin-labels">labels plugin</a> code. <b>PAY ATTENTION</b>
		 *         wait for approval of pull request to LABELS plugin
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-plugin-labels.min.js")
		TextResource labelsPlugin();
	}

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "labels";

	/**
	 * Data labels options factory
	 */
	public static final LabelsOptionsFactory FACTORY = new LabelsOptionsFactory();
	// defaults global options factory
	static final LabelsDefaultsOptionsFactory DEFAULTS_FACTORY = new LabelsDefaultsOptionsFactory();

	/**
	 * To avoid any instantiation
	 */
	private LabelsPlugin() {
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
		// injects LABELS plugin
		Injector.ensureInjected(LabelsClientBundle.INSTANCE.labelsPlugin());
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);

	}
}
