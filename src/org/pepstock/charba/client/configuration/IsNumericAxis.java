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
import org.pepstock.charba.client.callbacks.MinMaxCallback;

/**
 * Common methods for numeric scales (linear/log cartesian and radial).<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsNumericAxis extends HasMinMaxCallbacksHandler<Double> {

	/**
	 * Returns the axis instance.
	 * 
	 * @return the axis
	 */
	Axis getAxisElement();

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(double min) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			// reset callback
			setMin((MinMaxCallback<Double>) null);
			// stores value
			getAxisElement().getScale().setMin(min);
		}
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default double getMin() {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			return getAxisElement().getScale().getMin();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getMin();
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	default void setMax(double max) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			// reset callback
			setMax((MinMaxCallback<Double>) null);
			// stores value
			getAxisElement().getScale().setMax(max);
		}
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	default double getMax() {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			return getAxisElement().getScale().getMax();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getMax();
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(double suggestedMax) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			// reset callback
			setSuggestedMax((MinMaxCallback<Double>) null);
			// stores value
			getAxisElement().getScale().setSuggestedMax(suggestedMax);
		}
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	default double getSuggestedMax() {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			return getAxisElement().getScale().getSuggestedMax();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getSuggestedMax();
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	default void setSuggestedMin(double suggestedMin) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			// reset callback
			setSuggestedMin((MinMaxCallback<Double>) null);
			// stores value
			getAxisElement().getScale().setSuggestedMin(suggestedMin);
		}
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	default double getSuggestedMin() {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			return getAxisElement().getScale().getSuggestedMin();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().getSuggestedMin();
	}

}
