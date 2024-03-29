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
import org.pepstock.charba.client.RadarChart;
import org.pepstock.charba.client.configuration.RadarOptions;
import org.pepstock.charba.client.data.RadarDataset;

/**
 * RADAR chart GWT WIDGET implementation.<br>
 * A radar chart is a way of showing multiple data points and the variation between them.<br>
 * They are often useful for comparing the points of two or more different data sets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class RadarChartWidget extends AbstractChartWidget<RadarChart> implements IsDatasetCreator<RadarDataset> {

	/**
	 * Builds the object.
	 */
	public RadarChartWidget() {
		this(new RadarChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected RadarChartWidget(RadarChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public RadarOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public RadarDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}