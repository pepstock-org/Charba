/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client;

import org.pepstock.charba.client.configuration.PieOptions;
import org.pepstock.charba.client.data.PieDataset;

/**
 * PIE chart implementation.<br>
 * A pie charts are divided into segments, the arc of each segment shows the proportional value of each piece of data.<br>
 * They are excellent at showing the relational proportions between data.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class PieChart extends AbstractChart<PieDataset> {

	private final PieOptions options;

	/**
	 * Builds the object.
	 */
	public PieChart() {
		this(ChartType.PIE);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected PieChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new PieOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public PieOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#newDataset()
	 */
	@Override
	public PieDataset newDataset() {
		return new PieDataset(getDefaultChartOptions());
	}

}