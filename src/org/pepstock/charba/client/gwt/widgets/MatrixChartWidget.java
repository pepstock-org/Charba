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
import org.pepstock.charba.client.matrix.MatrixChart;
import org.pepstock.charba.client.matrix.MatrixDataset;
import org.pepstock.charba.client.matrix.MatrixOptions;

/**
 * MATRIX chart GWT WIDGET implementation.<br>
 * A matrix chart shows magnitude of a phenomenon as color in two dimensions.<br>
 * The variation in color may be by hue or intensity, giving obvious visual cues to the reader about how the phenomenon is clustered or varies over space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class MatrixChartWidget extends AbstractChartWidget<MatrixChart> implements IsDatasetCreator<MatrixDataset> {

	/**
	 * Builds the object.
	 */
	public MatrixChartWidget() {
		this(new MatrixChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected MatrixChartWidget(MatrixChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public MatrixOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public MatrixDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

	/**
	 * Registers the MATRIX controller in CHART.JS.
	 */
	public static void register() {
		// registers the controller
		MatrixChart.register();
	}
}