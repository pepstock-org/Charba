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
import org.pepstock.charba.client.treemap.TreeMapChart;
import org.pepstock.charba.client.treemap.TreeMapDataset;
import org.pepstock.charba.client.treemap.TreeMapOptions;

/**
 * TREEMAP chart GWT WIDGET implementation.<br>
 * A treemap chart is used for displaying hierarchical data using nested figures, usually rectangles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class TreeMapChartWidget extends AbstractChartWidget<TreeMapChart> implements IsDatasetCreator<TreeMapDataset> {

	/**
	 * Builds the object.
	 */
	public TreeMapChartWidget() {
		this(new TreeMapChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected TreeMapChartWidget(TreeMapChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public TreeMapOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public TreeMapDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

	/**
	 * Registers the TREEMAP controller in CHART.JS.
	 */
	public static void register() {
		// registers the controller
		TreeMapChart.register();
	}
}