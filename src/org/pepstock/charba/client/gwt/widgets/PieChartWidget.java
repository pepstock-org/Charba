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
package org.pepstock.charba.client.gwt.widgets;

import org.pepstock.charba.client.PieChart;
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
public class PieChartWidget extends AbstractChartWidget<PieChart> {

	/**
	 * Builds the object.
	 */
	public PieChartWidget() {
		this(new PieChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart instance
	 */
	protected PieChartWidget(PieChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public PieOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#newDataset()
	 */
	@Override
	public PieDataset newDataset() {
		return getChart().newDataset();
	}

}