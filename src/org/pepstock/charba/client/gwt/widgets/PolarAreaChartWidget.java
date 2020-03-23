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

import org.pepstock.charba.client.PolarAreaChart;
import org.pepstock.charba.client.configuration.PolarAreaOptions;
import org.pepstock.charba.client.data.PolarAreaDataset;

/**
 * POLAR AREA chart GWT WIDGET implementation.<br>
 * Polar area charts are similar to pie charts, but each segment has the same angle - the radius of the segment differs depending on the value.<br>
 * This type of chart is often useful when we want to show a comparison data similar to a pie chart, but also show a scale of values for context.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PolarAreaChartWidget extends AbstractChartWidget<PolarAreaChart> {

	/**
	 * Builds the object.
	 */
	public PolarAreaChartWidget() {
		this(new PolarAreaChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected PolarAreaChartWidget(PolarAreaChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public PolarAreaOptions getOptions() {
		return getChart().getOptions();
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	public PolarAreaDataset newDataset() {
		return getChart().newDataset();
	}

}