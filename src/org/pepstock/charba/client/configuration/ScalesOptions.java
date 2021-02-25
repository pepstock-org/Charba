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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Configuration of chart with multiple scales.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class ScalesOptions extends ConfigurationOptions {
	// not final in order it can be set from
	// options which inherits this class
	private Scales scales = null;

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	protected ScalesOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		this(chart, defaultValues, true);
	}

	/**
	 * Builds the object storing the chart instance and default values and creating a scale options if requested.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 * @param createScale if <code>true</code> creates a scale object reading the configuration otherwise it leaves the scale instance to <code>null</code> to allow to be set
	 *            later.
	 */
	protected ScalesOptions(IsChart chart, IsDefaultScaledOptions defaultValues, boolean createScale) {
		super(chart, defaultValues);
		// checks if a scale must be created
		if (createScale) {
			// new scales creation
			this.scales = new Scales(this);
		}
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
		// gets all axes
		// uses the get scale method instead of simple reference
		// because the scale object can be overrided
		List<Axis> xAxes = getScales().getAxes();
		// checks if consistent
		if (!xAxes.isEmpty()) {
			// scans all axes
			for (Axis axis : xAxes) {
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