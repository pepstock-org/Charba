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

import org.pepstock.charba.client.configuration.DoughnutOptions;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.DoughnutDataset;
import org.pepstock.charba.client.data.PieDataset;

/**
 * DOUGHNUT chart implementation.<br>
 * A doughnut charts are divided in the segments, the arc of each segment shows the proportional value of each piece of data.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DoughnutChart extends AbstractChart implements IsDatasetCreator<DoughnutDataset> {

	private final DoughnutOptions options;

	/**
	 * Builds the object.
	 */
	public DoughnutChart() {
		this(ChartType.DOUGHNUT);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected DoughnutChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new DoughnutOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public DoughnutOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public DoughnutDataset newDataset(boolean hidden) {
		return new DoughnutDataset(getDefaultChartOptions(), hidden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkDataset(org.pepstock.charba.client.data.Dataset)
	 */
	@Override
	protected boolean checkDataset(Dataset dataset) {
		return dataset instanceof DoughnutDataset || dataset instanceof PieDataset;
	}
}