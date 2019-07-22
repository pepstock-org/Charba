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
 * Common methods for linear scale is use to chart numerical data.<br>
 * Can be used for cartesian and radial axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsLinearTick {

	/**
	 * Returns the axis instance.
	 * 
	 * @return the axis
	 */
	Axis getAxis();

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	default void setBeginAtZero(boolean beginAtZero) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setBeginAtZero(beginAtZero);
		}
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.
	 */
	default boolean isBeginAtZero() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().isBeginAtZero();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale(getAxis().getType()).getTicks().isBeginAtZero();
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(double min) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setMin(min);
		}
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default double getMin() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getMin();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale(getAxis().getType()).getTicks().getMin();
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	default void setMax(double max) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setMax(max);
		}
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	default double getMax() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getMax();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale(getAxis().getType()).getTicks().getMax();
	}

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
		return Defaults.get().getScale(getAxis().getType()).getTicks().getMaxTicksLimit();
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
		return Defaults.get().getScale(getAxis().getType()).getTicks().getStepSize();
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(double suggestedMax) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setSuggestedMax(suggestedMax);
		}
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	default double getSuggestedMax() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getSuggestedMax();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale(getAxis().getType()).getTicks().getSuggestedMax();
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	default void setSuggestedMin(double suggestedMin) {
		// checks if axis is consistent
		if (getAxis() != null) {
			getAxis().getScale().getTicks().setSuggestedMin(suggestedMin);
		}
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	default double getSuggestedMin() {
		// checks if axis is consistent
		if (getAxis() != null) {
			return getAxis().getScale().getTicks().getSuggestedMin();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale(getAxis().getType()).getTicks().getSuggestedMin();
	}

}
