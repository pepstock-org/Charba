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
package org.pepstock.charba.client.gwt.widgets;

import org.pepstock.charba.client.HorizontalBarChart;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.configuration.HorizontalBarOptions;
import org.pepstock.charba.client.data.HorizontalBarDataset;

/**
 * HORIZONTAL BAR GWT WIDGET chart implementation.<br>
 * A horizontal bar chart is a variation on a bar chart.<br>
 * It is sometimes used to show trend data, and the comparison of multiple data sets side by side.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class HorizontalBarChartWidget extends AbstractChartWidget<HorizontalBarChart> implements IsDatasetCreator<HorizontalBarDataset> {

	/**
	 * Builds the object.
	 */
	public HorizontalBarChartWidget() {
		this(new HorizontalBarChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected HorizontalBarChartWidget(HorizontalBarChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public HorizontalBarOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public HorizontalBarDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}