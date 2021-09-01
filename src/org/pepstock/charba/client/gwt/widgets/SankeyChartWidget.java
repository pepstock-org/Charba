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
import org.pepstock.charba.client.sankey.SankeyChart;
import org.pepstock.charba.client.sankey.SankeyDataset;
import org.pepstock.charba.client.sankey.SankeyOptions;

/**
 * Sankey charts are a type of flow diagram in which the width of the arrows is proportional to the flow rate.<br>
 * Sankey diagrams emphasize the major transfers or flows within a system.<br>
 * They help locate the most important contributions to a flow.<br> 
 * They often show conserved quantities within defined system boundaries.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class SankeyChartWidget extends AbstractChartWidget<SankeyChart> implements IsDatasetCreator<SankeyDataset> {

	/**
	 * Builds the object.
	 */
	public SankeyChartWidget() {
		this(new SankeyChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected SankeyChartWidget(SankeyChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public SankeyOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public SankeyDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

}