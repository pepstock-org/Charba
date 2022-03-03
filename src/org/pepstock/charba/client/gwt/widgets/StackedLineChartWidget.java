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

import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.StackedLineChart;
import org.pepstock.charba.client.configuration.StackedOptions;
import org.pepstock.charba.client.data.StackedLineDataset;

/**
 * STACKED LINE chart GWT WIDGET implementation.<br>
 * A stacked area chart is a way of plotting data points on a line.<br>
 * Often, it is used to show trend data, or the comparison of two data sets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class StackedLineChartWidget extends AbstractChartWidget<StackedLineChart> implements IsDatasetCreator<StackedLineDataset> {

	/**
	 * Builds the object.
	 */
	public StackedLineChartWidget() {
		this(new StackedLineChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected StackedLineChartWidget(StackedLineChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public StackedOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public StackedLineDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}