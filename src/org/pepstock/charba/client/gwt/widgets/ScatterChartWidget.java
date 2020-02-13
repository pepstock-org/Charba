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

import org.pepstock.charba.client.ScatterChart;
import org.pepstock.charba.client.configuration.ScatterOptions;
import org.pepstock.charba.client.data.ScatterDataset;

/**
 * SCATTER chart GWT WIDGET implementation.<br>
 * Scatter charts are based on basic line charts with the x axis changed to a linear axis.<br>
 * To use a scatter chart, data must be passed as objects containing X and Y properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class ScatterChartWidget extends AbstractChartWidget<ScatterChart> {

	/**
	 * Builds the object.
	 */
	public ScatterChartWidget() {
		this(new ScatterChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected ScatterChartWidget(ScatterChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public ScatterOptions getOptions() {
		return getChart().getOptions();
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	public ScatterDataset newDataset() {
		return getChart().newDataset();
	}

}