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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Abstract configuration of time series chart.<br>
 * It uses TIME axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class TimeSeriesOptions extends ScalesOptions {

	private final TimeSeriesScales scales;

	/**
	 * Builds the object storing the chart instance, default values and linear and time scales created out of the box.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of stacked chart
	 */
	protected TimeSeriesOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		// asks to do not create a scale
		super(chart, defaultValues, false);
		// creates scales for time series
		scales = new TimeSeriesScales(chart, getConfiguration());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.MultiScalesOptions#getScales()
	 */
	@Override
	public final TimeSeriesScales getScales() {
		return scales;
	}

}