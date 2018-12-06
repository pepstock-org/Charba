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
package org.pepstock.charba.client.jsinterop;

import java.util.HashMap;
import java.util.Map;

/**
 * Is a static reference which collects all chart instances to be able to enable global plugins.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public final class Charts {

	// buffer with all charts instances
	// K = CHART id (CHARBA ID)
	// V = chart instance
	private static final Map<String, AbstractChart<?, ?>> CHARTS = new HashMap<String, AbstractChart<?, ?>>();

	/**
	 * To avoid any instantiation
	 */
	private Charts() {
	}

	/**
	 * Adds new charts instance into collection.
	 * 
	 * @param chart chart instance
	 */
	static void add(AbstractChart<?, ?> chart) {
		CHARTS.put(chart.getId(), chart);
	}

	/**
	 * Returns the chart instance by its id.
	 * 
	 * @param chartId chart id
	 * @return chart instance or <code>null</code> if not exist.
	 */
	public static AbstractChart<?, ?> get(String chartId) {
		return CHARTS.get(chartId);
	}

	/**
	 * Removes a chart instance by its id.
	 * 
	 * @param chartId chart id
	 */
	static void remove(String chartId) {
		CHARTS.remove(chartId);
	}

}
