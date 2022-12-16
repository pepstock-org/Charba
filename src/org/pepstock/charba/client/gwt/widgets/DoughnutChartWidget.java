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

import org.pepstock.charba.client.DoughnutChart;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.configuration.DoughnutOptions;
import org.pepstock.charba.client.data.DoughnutDataset;

/**
 * DOUGHNUT chart GWT WIDGET implementation.<br>
 * A doughnut charts are divided in the segments, the arc of each segment shows the proportional value of each piece of data.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DoughnutChartWidget extends AbstractChartWidget<DoughnutChart> implements IsDatasetCreator<DoughnutDataset> {

	/**
	 * Builds the object.
	 */
	public DoughnutChartWidget() {
		this(new DoughnutChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected DoughnutChartWidget(DoughnutChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public DoughnutOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public DoughnutDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}