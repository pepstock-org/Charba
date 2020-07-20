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

import org.pepstock.charba.client.configuration.StackedOptions;
import org.pepstock.charba.client.data.StackedBarDataset;

/**
 * STACKED BAR chart implementation.<br>
 * Stacked bar charts can be configured like bar charts and changes the settings on the X and Y axes to enable stacking.<br>
 * Stacked bar charts can be used to show how one data series is made up of a number of smaller pieces.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class StackedBarChart extends AbstractChart implements IsDatasetCreator<StackedBarDataset> {

	private final StackedOptions options;

	/**
	 * Builds the object.
	 */
	public StackedBarChart() {
		this(ChartType.BAR);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected StackedBarChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new StackedOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public StackedOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public StackedBarDataset newDataset(boolean hidden) {
		return new StackedBarDataset(getDefaultChartOptions(), hidden);
	}

}