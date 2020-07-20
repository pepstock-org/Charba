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
import org.pepstock.charba.client.TimeSeriesBarChart;
import org.pepstock.charba.client.configuration.TimeSeriesBarOptions;
import org.pepstock.charba.client.data.TimeSeriesBarDataset;

/**
 * BAR chart GWT WIDGET implementation for time series.<br>
 * A bar chart provides a way of showing data values represented as vertical bars.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class TimeSeriesBarChartWidget extends AbstractChartWidget<TimeSeriesBarChart> implements IsDatasetCreator<TimeSeriesBarDataset> {

	/**
	 * Builds the object.
	 */
	public TimeSeriesBarChartWidget() {
		this(new TimeSeriesBarChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected TimeSeriesBarChartWidget(TimeSeriesBarChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public TimeSeriesBarOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public TimeSeriesBarDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}