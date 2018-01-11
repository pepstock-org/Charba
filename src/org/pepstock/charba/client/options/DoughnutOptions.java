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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;

/**
 * Specific options for DOUGHNUT chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DoughnutOptions extends PieOptions {

	// FIXME con globals/chart options
	private static final double DEFAULT_CUTOUT_PERCENTAGE = 50D;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		cutoutPercentage
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public DoughnutOptions(AbstractChart<?, ?> chart) {
		super(chart);
		// sets the cut out percentage property
		setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/**
	 * Returns the percentage of the chart that is cut out of the middle.
	 * 
	 * @return The percentage of the chart that is cut out of the middle. ALWAYS 50.
	 */
	public double getCutoutPercentage() {
		return getValue(Property.cutoutPercentage, DEFAULT_CUTOUT_PERCENTAGE);
	}
}