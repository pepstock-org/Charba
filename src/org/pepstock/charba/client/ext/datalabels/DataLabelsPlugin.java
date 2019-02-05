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
package org.pepstock.charba.client.ext.datalabels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.ext.Extensions;

/**
 * Entry point of <a href="FIXME">DATALABELS plugin</a> with some static utilities to enable it and to get and set options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsPlugin {

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "datalabels";
	// options factory
	private static final DataLabelsOptionsFactory FACTORY = new DataLabelsOptionsFactory();
	// cache of label options for configuration
	// K = chart ID, V = label options
	private static final Map<String, DataLabelsConfiguration> OPTIONS_CONFIGURATIONS = new HashMap<>();
	// K = dataset ID, V = label options
	private static final Map<Integer, DataLabelsConfiguration> DATASETS_CONFIGURATIONS = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private DataLabelsPlugin() {
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
		// injects CHARBA is not already injected
		Injector.ensureInjected();
		// injects LABELS plugin
		Injector.ensureInjected(Extensions.INSTANCE.datalabelsPlugin());
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}

	/**
	 * Sets the LABELS plugin options at chart level.
	 * 
	 * @param labelsConfigurations the LABELS plugin options at chart level.
	 */
	public static void setOptions(AbstractChart<?, ?> chart, DataLabelsConfiguration labelsConfiguration) {
		// sets the option
		chart.getOptions().getPlugins().setOptions(ID, labelsConfiguration);
		if (labelsConfiguration != null) {
			OPTIONS_CONFIGURATIONS.put(chart.getId(), labelsConfiguration);
		} else {
			OPTIONS_CONFIGURATIONS.remove(chart.getId());
		}
	}

	/**
	 * Sets the LABELS plugin options at dataset level.
	 * 
	 * @param labelsConfigurations the LABELS plugin options at dataset level.
	 */
	public static void setOptions(Dataset dataset, DataLabelsConfiguration labelsConfiguration) {
		// sets the option
		dataset.setOptions(ID, labelsConfiguration);
		if (labelsConfiguration != null) {
			DATASETS_CONFIGURATIONS.put(dataset.getId(), labelsConfiguration);
		} else {
			DATASETS_CONFIGURATIONS.remove(dataset.getId());
		}

	}

	/**
	 * Returns the LABELS plugin options set at chart level.
	 * 
	 * @param chart chart instance
	 * @return the LABELS plugin options
	 */
	public static DataLabelsOptions getOptions(AbstractChart<?, ?> chart) {
		return chart.getOptions().getPlugins().getOptions(ID, FACTORY);
	}

	/**
	 * Returns the LABELS plugin options set at dataset level.
	 * 
	 * @param chart chart instance
	 * @return the LABELS plugin options
	 */
	public static DataLabelsOptions getOptions(Dataset dataset) {
		return dataset.getOptions(ID, FACTORY);
	}

	/**
	 * Sets the LABELS plugin options at global level.
	 * 
	 * @param labelsOptions the LABELS plugin options at global level
	 */
	public static void setGlobalOptions(DataLabelsOptions labelsOptions) {
		// sets the options as list
		Defaults.get().getGlobal().getPlugins().setOptions(ID, Arrays.asList(labelsOptions));
	}

	/**
	 * Returns the LABELS plugin options at global level if defined only one.
	 * 
	 * @return the LABELS plugin options at global level.
	 */
	public static DataLabelsOptions getGlobalOptions() {
		return Defaults.get().getGlobal().getPlugins().getOptions(ID, FACTORY);
	}

}
