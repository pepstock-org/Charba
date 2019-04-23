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
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Specific scales for time series charts.<br>
 * It uses TIME axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TimeSeriesScales extends Scales {
	
	// exception string message for setting a X axis because the TIME created must be used
	private static final String INVALID_SET_AXIS_CALL = "Do not set any X axes because TIME axis is already created. Use getTimeAxis to configure it.";
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
		super.setXAxes(timeAxis);
		linearAxis = new CartesianLinearAxis(chart);
		super.setYAxes(linearAxis);
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
	 * @see org.pepstock.charba.client.configuration.Scales#setXAxes(org.pepstock.charba.client.configuration. Axis[])
	 */
	@Override
	public void setXAxes(Axis... axes) {
		throw new UnsupportedOperationException(INVALID_SET_AXIS_CALL);
	}

}