/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.enums.AxisKind;

/**
 * Specific scales for time series charts.<br>
 * It uses {@link CartesianTimeSeriesAxis} for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TimeSeriesScales extends Scales {

	// X axis (time)
	private final CartesianTimeSeriesAxis timeAxis;
	// Y axis (linear)
	private final CartesianLinearAxis linearAxis;

	/**
	 * Builds the object storing the root options.
	 * 
	 * @param options root options
	 */
	TimeSeriesScales(ScalesOptions options) {
		super(options);
		// creates the axes
		// out of the box
		this.timeAxis = new CartesianTimeSeriesAxis(getChart());
		this.linearAxis = new CartesianLinearAxis(getChart());
		// stores axes
		super.setAxes(timeAxis, linearAxis);
	}

	/**
	 * Returns the axis for time.
	 * 
	 * @return the axis for time
	 */
	public CartesianTimeSeriesAxis getTimeAxis() {
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
		if (ArrayUtil.isNotEmpty(axes)) {
			// creates an empty array of axis plus 1 for X axis
			Axis[] toStore = new Axis[axes.length + 1];
			// stores x axis
			toStore[0] = timeAxis;
			// sets an index to load the array
			int index = 1;
			// scans all axes to check if there is any other x axis
			for (Axis axis : axes) {
				// checks if x axis kind
				Checker.assertCheck(!AxisKind.X.equals(axis.getAxis()), "Do not set any X axes because TIME axis is already created. Use getTimeAxis to configure it");
				Checker.assertCheck(!AxisKind.R.equals(axis.getAxis()), "Do not set any radial linear axis to a time series chart");
				// stores the axis in the array
				toStore[index] = axis;
				// increments index
				index++;
			}
			// stores the axes
			super.setAxes(toStore);
		}
	}

}