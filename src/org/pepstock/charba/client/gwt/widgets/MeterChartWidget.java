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

import org.pepstock.charba.client.impl.charts.MeterChart;
import org.pepstock.charba.client.impl.charts.MeterDataset;
import org.pepstock.charba.client.impl.charts.MeterOptions;

/**
 * METER chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MeterChartWidget extends AbstractChartWidget<MeterChart> {

	/**
	 * Builds the object.
	 */
	public MeterChartWidget() {
		super(new MeterChart());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public MeterOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset()
	 */
	@Override
	public MeterDataset newDataset() {
		return getChart().newDataset();
	}

	/**
	 * Returns a dataset with a maximum value.
	 * 
	 * @param max maximum value of dataset
	 * @return dataset instance
	 */
	public MeterDataset newDataset(double max) {
		return getChart().newDataset(max);
	}

}