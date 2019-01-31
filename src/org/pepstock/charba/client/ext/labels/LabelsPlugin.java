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
package org.pepstock.charba.client.ext.labels;

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.ext.Extensions;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsPlugin {

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "labels";
	
	private static final LabelsOptionsFactory FACTORY = new LabelsOptionsFactory();
	
	/**
	 * To avoid any instantiation
	 */
	private LabelsPlugin() {
		// do nothing
	}
	
	public static void enable() {
		enable(false);
	}

	public static void enable(boolean enableToAllCharts) {
		Injector.ensureInjected();
		Injector.ensureInjected(Extensions.INSTANCE.labelsPlugin());
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}

	public static void setOptions(AbstractChart<?, ?> chart, LabelsOptions... labelsOptions) {
		if (labelsOptions != null && labelsOptions.length == 1) {
			chart.getOptions().getPlugins().setOptions(ID, labelsOptions[0]);
		} else {
			chart.getOptions().getPlugins().setOptions(ID, Arrays.asList(labelsOptions));
		}
	}
	
	public static LabelsOptions getOptions(AbstractChart<?, ?> chart) {
		return chart.getOptions().getPlugins().getOptions(ID, FACTORY);
	}

	public static List<LabelsOptions> getOptionsAsList(AbstractChart<?, ?> chart) {
		return chart.getOptions().getPlugins().getOptionsAsList(ID, FACTORY);
	}

}
