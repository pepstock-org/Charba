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
package org.pepstock.charba.client;

import org.pepstock.charba.client.configuration.VerticalLineOptions;
import org.pepstock.charba.client.data.VerticalLineDataset;

/**
 * Vertical LINE chart implementation.<br>
 * A line chart is a way of plotting data points on a line.<br>
 * Often, it is used to show trend data, or the comparison of two data sets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class VerticalLineChart extends LineChart {

	private final VerticalLineOptions options;

	/**
	 * Builds the object.
	 */
	public VerticalLineChart() {
		this(ChartType.LINE);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected VerticalLineChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new VerticalLineOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.LineChart#getOptions()
	 */
	@Override
	public VerticalLineOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsDatasetCreator#newDataset()
	 */
	@Override
	public VerticalLineDataset newDataset() {
		return newDataset(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.LineChart#newDataset(boolean)
	 */
	@Override
	public VerticalLineDataset newDataset(boolean hidden) {
		return new VerticalLineDataset(getDefaultChartOptions(), hidden);
	}

}