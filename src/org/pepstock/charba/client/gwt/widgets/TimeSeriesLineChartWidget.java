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

import org.pepstock.charba.client.TimeSeriesLineChart;
import org.pepstock.charba.client.configuration.TimeSeriesLineOptions;
import org.pepstock.charba.client.data.TimeSeriesLineDataset;

/**
 * LINE chart GWT WIDGET implementation for time series.<br>
 * A line chart is a way of plotting data points on a line.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesLineChartWidget extends AbstractChartWidget<TimeSeriesLineChart> {

	/**
	 * Builds the object.
	 */
	public TimeSeriesLineChartWidget() {
		this(new TimeSeriesLineChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected TimeSeriesLineChartWidget(TimeSeriesLineChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public TimeSeriesLineOptions getOptions() {
		return getChart().getOptions();
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	public TimeSeriesLineDataset newDataset() {
		return getChart().newDataset();
	}

}