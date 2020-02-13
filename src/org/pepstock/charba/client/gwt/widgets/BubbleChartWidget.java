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

import org.pepstock.charba.client.BubbleChart;
import org.pepstock.charba.client.configuration.BubbleOptions;
import org.pepstock.charba.client.data.BubbleDataset;

/**
 * BUBBLE chart GWT WIDGET implementation.<br>
 * A bubble chart is used to display three dimensions of data at the same time.<br>
 * The location of the bubble is determined by the first two dimensions and the corresponding horizontal and vertical axes.<br>
 * The third dimension is represented by the size of the individual bubbles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BubbleChartWidget extends AbstractChartWidget<BubbleChart>  {

	/**
	 * Builds the object.
	 */
	public BubbleChartWidget() {
		super(new BubbleChart());
	}
	
	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected BubbleChartWidget(BubbleChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public BubbleOptions getOptions() {
		return getChart().getOptions();
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	public BubbleDataset newDataset() {
		return getChart().newDataset();
	}

}