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
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * Configuration of chart which could be stacked.<br>
 * It uses Category axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class StackedOptions extends ScalesOptions {

	private final StackedScales scales;

	/**
	 * Builds the object storing the chart instance, default values and if only Y axis is scaled.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of stacked chart
	 * @param onlyYScaled <code>true</code> if only Y axis is scaled.
	 */
	public StackedOptions(IsChart chart, IsDefaultScaledOptions defaultValues, boolean onlyYScaled) {
		// asks to do not create a scale
		super(chart, defaultValues, false);
		// creates scales for stacked chart
		scales = new StackedScales(this);
		// sets if only Y scaled
		scales.setOnlyYAxis(onlyYScaled);
		// creates the axes
		CartesianCategoryAxis axis1 = new CartesianCategoryAxis(chart);
		CartesianLinearAxis axis2 = new CartesianLinearAxis(chart);
		// stores axes
		scales.setAxes(axis1, axis2);
	}

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bar chart
	 */
	public StackedOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		this(chart, defaultValues, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.MultiScalesOptions#getScales()
	 */
	@Override
	public Scales getScales() {
		return scales;
	}

	/**
	 * Sets the base axis for the dataset.<br>
	 * Use {@link IndexAxis#Y} for horizontal bar.
	 * 
	 * @param indexAxis the base axis for the dataset
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		getConfiguration().setIndexAxis(indexAxis);
	}

	/**
	 * Returns the base axis for the dataset.
	 * 
	 * @return the base axis for the dataset
	 */
	public IndexAxis getIndexAxis() {
		return getConfiguration().getIndexAxis();
	}
}