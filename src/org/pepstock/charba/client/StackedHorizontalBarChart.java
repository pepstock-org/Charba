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
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.BubbleDataset;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.LineDataset;
import org.pepstock.charba.client.data.ScatterDataset;
import org.pepstock.charba.client.data.StackedHorizontalBarDataset;
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * STACKED HORIZONTAL BAR chart implementation.<br>
 * Stacked bar charts can be configured like bar charts and changes the settings on the X and Y axes to enable stacking.<br>
 * Stacked bar charts can be used to show how one data series is made up of a number of smaller pieces.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class StackedHorizontalBarChart extends AbstractChart implements IsDatasetCreator<StackedHorizontalBarDataset>, HasCartesianAxes {

	private final StackedOptions options;

	/**
	 * Builds the object.
	 */
	public StackedHorizontalBarChart() {
		this(ChartType.BAR);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected StackedHorizontalBarChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new StackedOptions(this, getDefaultChartOptions(), IndexAxis.Y);
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
	public StackedHorizontalBarDataset newDataset(boolean hidden) {
		return new StackedHorizontalBarDataset(getDefaultChartOptions(), hidden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkDataset(org.pepstock.charba.client.data.Dataset)
	 */
	@Override
	protected boolean checkDataset(Dataset dataset) {
		return dataset instanceof BarDataset || dataset instanceof LineDataset || dataset instanceof BubbleDataset || dataset instanceof ScatterDataset;
	}
}