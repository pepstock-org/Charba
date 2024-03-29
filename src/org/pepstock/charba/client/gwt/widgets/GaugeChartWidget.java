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

import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.impl.charts.GaugeChart;
import org.pepstock.charba.client.impl.charts.GaugeDataset;
import org.pepstock.charba.client.impl.charts.GaugeOptions;

/**
 * GAUGE chart GWT WIDGET implementation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GaugeChartWidget extends AbstractChartWidget<GaugeChart> implements IsDatasetCreator<GaugeDataset> {

	/**
	 * Builds the object.
	 */
	public GaugeChartWidget() {
		super(new GaugeChart());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public GaugeOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public GaugeDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

	/**
	 * Returns a dataset with a maximum value.
	 * 
	 * @param max maximum value of dataset
	 * @return dataset instance
	 */
	public GaugeDataset newDataset(double max) {
		return getChart().newDataset(max);
	}

	/**
	 * Registers the GAUGE controller in CHART.JS.
	 */
	public static void register() {
		// registers the controller
		GaugeChart.register();
	}
}