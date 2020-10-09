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
 * Common methods for numeric scales (linear/log cartesian and radial).<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsNumericAxis {

	/**
	 * Returns the axis instance.
	 * 
	 * @return the axis
	 */
	Axis getAxisElement();
	
	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	default void setBeginAtZero(boolean beginAtZero) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			getAxisElement().getScale().setBeginAtZero(beginAtZero);
		}
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.
	 */
	default boolean isBeginAtZero() {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			return getAxisElement().getScale().isBeginAtZero();
		}
		// if here, axis is not consistent
		return Defaults.get().getScale().isBeginAtZero();
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	default void setMin(double min) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
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

}
