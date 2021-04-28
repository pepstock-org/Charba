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
 * Specific options for DOUGHNUT chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class DoughnutOptions extends AbstractPieOptions {

	private static final String DEFAULT_CUTOUT_PERCENTAGE = "50%";

	/**
	 * Builds the object storing the chart instance and default options.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default options
	 */
	public DoughnutOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#getCutoutPercentage()
	 */
	@Override
	public String getCutoutPercentage() {
		// gets value from options
		String value = super.getCutoutPercentage();
		// checks if consistent
		// if not, provides the doughnut default
		if (value == null) {
			return DEFAULT_CUTOUT_PERCENTAGE;
		}
		// returns the value
		return value;
	}

}