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

import org.pepstock.charba.client.Defaults;

/**
 * Common methods for linear scale tick is use to chart numerical data.<br>
 * Can be used for linear cartesian and radial ticks.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsLinearTick {

	/**
	 * Returns the axis instance.
	 * 
	 * @return the axis
	 */
	Axis getAxis();

	/**
	 * Sets the maximum number of ticks and grid lines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	default void setMaxTicksLimit(int maxTicksLimit) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setMaxTicksLimit(maxTicksLimit);
		}
	}

	/**
	 * Returns the maximum number of ticks and grid lines to show.
	 * 
	 * @return maximum number of ticks and grid lines to show.
	 */
	default int getMaxTicksLimit() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getMaxTicksLimit();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getMaxTicksLimit();
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	default void setStepSize(double stepSize) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setStepSize(stepSize);
		}
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	default double getStepSize() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getStepSize();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getStepSize();
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @param precision if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	default void setPrecision(int precision) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setPrecision(precision);
		}
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @return if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	default int getPrecision() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getPrecision();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getTicks().getPrecision();
	}
}
