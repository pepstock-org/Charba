/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;

/**
 * Container of a chart instance. Must be extended for all other entities which will trigger events or callbacks to pass the chart instance as parameter of implemented interface.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class ChartContainer {

	// chart instance
	private final IsChart chart;

	/**
	 * Creates the chart configuration object with the chart instance
	 * 
	 * @param chart chart instance
	 */
	ChartContainer(IsChart chart) {
		// checks if chart is consistent
		IsChart.checkIfValid(chart);
		// stores chart
		this.chart = chart;
	}

	/**
	 * Returns the chart instance
	 * 
	 * @return the chart
	 */
	public final IsChart getChart() {
		return chart;
	}
}