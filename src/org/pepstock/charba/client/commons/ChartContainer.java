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
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.AbstractChart;

/**
 * Extends a JavaScript object container for all entities which needs the chart instance.<br>
 * This class is used for all entities which will trigger events or callbacks to pass the chart instance as parameter of implemented interface.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
 */
public abstract class ChartContainer extends JavaScriptObjectContainer{

	// chart instance
	private final AbstractChart<?, ?> chart;
	
	/**
	 * Creates the chart configuration object with the chart instance
	 * @param chart chart instance
	 * @see org.pepstock.charba.client.AbstractChart
	 */
	public ChartContainer(AbstractChart<?, ?> chart) {
		super();
		this.chart = chart;
	}

	/**
	 * @return the chart
	 */
	public final AbstractChart<?, ?> getChart() {
		return chart;
	}

}