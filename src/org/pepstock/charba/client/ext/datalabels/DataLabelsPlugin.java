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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.ext.Extensions;

/**
 * Entry point of <a href="FIXME">DATALABELS plugin</a> with some static utilities to
 * enable it and to get and set options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsPlugin {

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "datalabels";
//	// options factory
//	private static final LabelsOptionsFactory FACTORY = new LabelsOptionsFactory();

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

//	/**
//	 * Sets the LABELS plugin options at chart level.
//	 * 
//	 * @param labelsConfigurations the LABELS plugin options at chart level.
//	 */
//	public static void setOptions(LabelsConfiguration... labelsConfigurations) {
//		// checks if arguments are consistent
//		if (labelsConfigurations != null) {
//			// gets the first element to get chart
//			LabelsConfiguration config = labelsConfigurations[0];
//			AbstractChart<?, ?> chart = config.getChart();
//			// if the array has got only 1 element
//			if (labelsConfigurations.length == 1) {
//				// sets the option
//				chart.getOptions().getPlugins().setOptions(ID, config);
//			} else {
//				// sets the options as list
//				chart.getOptions().getPlugins().setOptions(ID, Arrays.asList(labelsConfigurations));
//			}
//		}
//	}
//
//	/**
//	 * Returns the LABELS plugin options set at chart level.
//	 * 
//	 * @param chart chart instance
//	 * @return the LABELS plugin options
//	 */
//	public static LabelsOptions getOptions(AbstractChart<?, ?> chart) {
//		return chart.getOptions().getPlugins().getOptions(ID, FACTORY);
//	}
//
//	/**
//	 * Returns the LABELS plugin options as list set at chart level.
//	 * 
//	 * @param chart chart instance
//	 * @return the LABELS plugin options as list
//	 */
//	public static List<LabelsOptions> getOptionsAsList(AbstractChart<?, ?> chart) {
//		return chart.getOptions().getPlugins().getOptionsAsList(ID, FACTORY);
//	}
//
//	/**
//	 * Sets the LABELS plugin options at global level.
//	 * 
//	 * @param labelsOptions the LABELS plugin options at global level
//	 */
//	public static void setGlobalOptions(LabelsOptions... labelsOptions) {
//		// checks if arguments are consistent
//		if (labelsOptions != null) {
//			if (labelsOptions.length == 1) {
//				// if the array has got only 1 element
//				Defaults.get().getGlobal().getPlugins().setOptions(ID, labelsOptions[0]);
//			} else {
//				// sets the options as list
//				Defaults.get().getGlobal().getPlugins().setOptions(ID, Arrays.asList(labelsOptions));
//			}
//		}
//	}
//
//	/**
//	 * Returns the LABELS plugin options at global level if defined only one.
//	 * 
//	 * @return the LABELS plugin options at global level.
//	 */
//	public static LabelsOptions getGlobalOptions() {
//		return Defaults.get().getGlobal().getPlugins().getOptions(ID, FACTORY);
//	}
//
//	/**
//	 * Returns the LABELS plugin options at global level as list if defined more than one.
//	 * 
//	 * @return the LABELS plugin options at global level as list
//	 */
//	public static List<LabelsOptions> getGlobalOptionsAsList() {
//		return Defaults.get().getGlobal().getPlugins().getOptionsAsList(ID, FACTORY);
//	}

}
