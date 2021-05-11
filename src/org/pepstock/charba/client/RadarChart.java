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

import org.pepstock.charba.client.configuration.RadarOptions;
import org.pepstock.charba.client.data.RadarDataset;

/**
 * RADAR chart implementation.<br>
 * A radar chart is a way of showing multiple data points and the variation between them.<br>
 * They are often useful for comparing the points of two or more different data sets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class RadarChart extends AbstractChart implements IsDatasetCreator<RadarDataset> {

	private final RadarOptions options;

	/**
	 * Builds the object.
	 */
	public RadarChart() {
		this(ChartType.RADAR);
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param extendedType type of chart
	 */
	protected RadarChart(Type extendedType) {
		super(extendedType);
		// creates the options
		options = new RadarOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public RadarOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public RadarDataset newDataset(boolean hidden) {
		return new RadarDataset(getDefaultChartOptions(), hidden);
	}

}