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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.jsinterop.configuration.scales.CartesianCategoryAxis;
import org.pepstock.charba.client.jsinterop.configuration.scales.CartesianLinearAxis;
import org.pepstock.charba.client.jsinterop.configuration.scales.Scales;
import org.pepstock.charba.client.jsinterop.configuration.scales.StackedScales;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

/**
 * Configuration of chart which could be stacked.<br>
 * It uses Category axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StackedOptions extends BaseOptions {

	private final StackedScales scales;

	/**
	 * Builds the object storing the chart instance and if only Y axis is scaled.
	 * 
	 * @param chart chart instance
	 * @param onlyYScaled <code>true</code> if only Y axis is scaled.
	 */
	public StackedOptions(AbstractChart<?, ?> chart, IsDefaultOptions defaultValues, boolean onlyYScaled) {
		super(chart, defaultValues);
		// creates scales for stacked chart
		scales = new StackedScales(chart, getConfiguration());
		// sets if only Y scaled
		scales.setOnlyYAxis(onlyYScaled);
		// creates the axes
		CartesianCategoryAxis axis1 = new CartesianCategoryAxis(chart);
		scales.setXAxes(axis1);
		CartesianLinearAxis axis2 = new CartesianLinearAxis(chart);
		scales.setYAxes(axis2);
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public StackedOptions(AbstractChart<?, ?> chart, IsDefaultOptions defaultValues) {
		this(chart, defaultValues, false);
	}

	/**
	 * @return the scales
	 */
	public Scales getScales() {
		return scales;
	}
}