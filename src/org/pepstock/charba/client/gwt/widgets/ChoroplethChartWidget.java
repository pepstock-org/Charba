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
import org.pepstock.charba.client.geo.ChoroplethChart;
import org.pepstock.charba.client.geo.ChoroplethDataset;
import org.pepstock.charba.client.geo.ChoroplethOptions;

/**
 * CHOROPLETH chart GWT WIDGET implementation.<br>
 * FIXME A bar chart provides a way of showing data values represented as vertical bars.<br>
 * It is sometimes used to show trend data, and the comparison of multiple data sets side by side.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ChoroplethChartWidget extends AbstractChartWidget<ChoroplethChart> implements IsDatasetCreator<ChoroplethDataset> {

	/**
	 * Builds the object.
	 */
	public ChoroplethChartWidget() {
		this(new ChoroplethChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected ChoroplethChartWidget(ChoroplethChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public ChoroplethOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public ChoroplethDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}