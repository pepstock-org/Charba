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
 * Specific options for PIE chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class PieOptions extends AbstractPieOptions {
	
	private static final double DEFAULT_CUTOUT = 0D;

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of pie chart
	 */
	public PieOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#getCutout()
	 */
	@Override
	public double getCutout() {
		// gets value from options
		double value = super.getCutout();
		// checks if consistent
		// if not, provides the doughnut default
		if (Double.isNaN(value)) {
			return DEFAULT_CUTOUT;
		}
		// returns the value
		return value;
	}

}