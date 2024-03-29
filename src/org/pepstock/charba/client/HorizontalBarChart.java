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

import org.pepstock.charba.client.configuration.HorizontalBarOptions;
import org.pepstock.charba.client.data.HorizontalBarDataset;

/**
 * HORIZONTAL BAR chart implementation.<br>
 * A horizontal bar chart is a variation on a bar chart.<br>
 * It is sometimes used to show trend data, and the comparison of multiple data sets side by side.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class HorizontalBarChart extends BarChart {

	private final HorizontalBarOptions options;

	/**
	 * Builds the object.
	 */
	public HorizontalBarChart() {
		this(ChartType.BAR);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected HorizontalBarChart(Type extendedType) {
		super(extendedType, true);
		// creates the options
		this.options = new HorizontalBarOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.BarChart#getOptions()
	 */
	@Override
	public HorizontalBarOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsDatasetCreator#newDataset()
	 */
	@Override
	public HorizontalBarDataset newDataset() {
		return newDataset(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.BarChart#newDataset(boolean)
	 */
	@Override
	public HorizontalBarDataset newDataset(boolean hidden) {
		return new HorizontalBarDataset(getDefaultChartOptions(), hidden);
	}

}