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

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.IsChart;

/**
 * Configuration of chart with only 1 scale.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class SingleScaleOptions extends ConfigurationOptions {
	// axis instance
	private Axis axis = null;

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	public SingleScaleOptions(IsChart chart, ChartOptions defaultValues) {
		super(chart, defaultValues);
	}

	/**
	 * Sets the single axis of chart.
	 * 
	 * @param axis the axis.
	 */
	public void setAxis(Axis axis) {
		// checks if axis is consistent
		if (axis != null) {
			// stores locally the axis
			this.axis = axis;
			// stores into configuration
			getConfiguration().setScale(axis.getConfiguration());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#getAxisById(int)
	 */
	@Override
	Axis getAxisById(int id) {
		return (axis != null && axis.getCharbaId() == id) ? axis : null;
	}

}