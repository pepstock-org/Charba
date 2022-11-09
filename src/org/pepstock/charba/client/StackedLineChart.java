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

import org.pepstock.charba.client.configuration.StackedLineOptions;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.BubbleDataset;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.LineDataset;
import org.pepstock.charba.client.data.ScatterDataset;
import org.pepstock.charba.client.data.StackedLineDataset;

/**
 * STACKED LINE chart implementation.<br>
 * A stacked area chart is a way of plotting data points on a line.<br>
 * Often, it is used to show trend data, or the comparison of two data sets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class StackedLineChart extends AbstractChart implements IsDatasetCreator<StackedLineDataset>, HasCartesianAxes {

	private final StackedLineOptions options;

	/**
	 * Builds the object.
	 */
	public StackedLineChart() {
		this(ChartType.LINE);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected StackedLineChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new StackedLineOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public StackedLineOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public StackedLineDataset newDataset(boolean hidden) {
		return new StackedLineDataset(getDefaultChartOptions(), hidden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkDataset(org.pepstock.charba.client.data.Dataset)
	 */
	@Override
	protected boolean checkDataset(Dataset dataset) {
		return dataset instanceof LineDataset || dataset instanceof BarDataset || dataset instanceof BubbleDataset || dataset instanceof ScatterDataset;
	}
}