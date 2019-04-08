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

import java.util.List;

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.IsChart;

/**
 * Configuration of chart which could be stacked.<br>
 * It uses Category axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class StackedOptions extends ConfigurationOptions {

	private final StackedScales scales;

	/**
	 * Builds the object storing the chart instance, default values and if only Y axis is scaled.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of stacked chart
	 * @param onlyYScaled <code>true</code> if only Y axis is scaled.
	 */
	public StackedOptions(IsChart chart, ChartOptions defaultValues, boolean onlyYScaled) {
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
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bar chart
	 */
	public StackedOptions(IsChart chart, ChartOptions defaultValues) {
		this(chart, defaultValues, false);
	}

	/**
	 * Returns the scales element.
	 * 
	 * @return the scales
	 */
	public Scales getScales() {
		return scales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#getAxisById(int)
	 */
	@Override
	Axis getAxisById(int id) {
		// gets all X axes
		List<Axis> xAxes = scales.getXAxes();
		// checks if consistent
		if (xAxes != null && !xAxes.isEmpty()) {
			// scans all axes
			for (Axis axis : xAxes) {
				// checks if charba ID is the same of argument
				if (axis.getCharbaId() == id) {
					// axis found! returns it
					return axis;
				}
			}
		}
		// gets all Y axes
		List<Axis> yAxes = scales.getYAxes();
		// checks if consistent
		if (yAxes != null && !yAxes.isEmpty()) {
			// scans all axes
			for (Axis axis : yAxes) {
				// checks if charba ID is the same of argument
				if (axis.getCharbaId() == id) {
					// axis found! returns it
					return axis;
				}
			}
		}
		// if here, no axis found
		return null;
	}
}