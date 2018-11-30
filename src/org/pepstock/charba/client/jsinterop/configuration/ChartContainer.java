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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.AbstractChart;

/**
 * Container of a chart instance. Must be extended for all other entities which 
 * will trigger events or callbacks to pass the chart instance as parameter of implemented interface.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
abstract class ChartContainer{

	// chart instance
	private final AbstractChart<?, ?> chart;
	
	/**
	 * Creates the chart configuration object with the chart instance
	 * @param chart chart instance
	 */
	ChartContainer(AbstractChart<?, ?> chart) {
		this.chart = chart;
	}

	/**
	 * @return the chart
	 */
	public final AbstractChart<?, ?> getChart() {
		return chart;
	}
}