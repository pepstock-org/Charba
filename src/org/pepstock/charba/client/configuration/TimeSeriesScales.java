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
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Specific scales for time series charts.<br>
 * It uses TIME axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TimeSeriesScales extends Scales {

	// X axis (time)
	private final CartesianTimeAxis timeAxis;
	// Y axis (linear)
	private final CartesianLinearAxis linearAxis;

	/**
	 * Builds the object storing the chart instance and root options.
	 * 
	 * @param chart chart instance
	 * @param options root options
	 */
	TimeSeriesScales(IsChart chart, ExtendedOptions options) {
		super(chart, options);
		// creates the axes
		// out of the box
		timeAxis = new CartesianTimeAxis(chart);
		linearAxis = new CartesianLinearAxis(chart);
		super.setAxes(timeAxis, linearAxis);
	}

	/**
	 * Returns the axis for time.
	 * 
	 * @return the axis for time
	 */
	public CartesianTimeAxis getTimeAxis() {
		return timeAxis;
	}

	/**
	 * Returns the axis for values.
	 * 
	 * @return the axis for values
	 */
	public CartesianLinearAxis getLinearAxis() {
		return linearAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Scales#setAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public void setAxes(Axis... axes) {
		// checks consistency of arguments
		if (axes != null && axes.length > 0) {
			// creates an empty array of axis plus 1 for X axis
			Axis[] toStore = new Axis[axes.length+1];
			// stores x axis
			toStore[0] = timeAxis;
			// sets an index to load the array
			int index = 1;
			// scans all axes to check if there is any other x axis
			for (Axis axis : axes) {
				// checks if x axis
				if (CartesianAxisType.hasAxisType(axis, CartesianAxisType.X)) {
					// not allowed
					throw new UnsupportedOperationException("Do not set any X axes because TIME axis is already created. Use getTimeAxis to configure it");
				}
				// stores the axis into array
				toStore[index] = axis;
				// increments index
				index++;
			}
			// stores the axes
			super.setAxes(toStore);
		}
	}

}