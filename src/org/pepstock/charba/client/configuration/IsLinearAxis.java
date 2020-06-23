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
 * Can be used for linear cartesian and radial axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsLinearAxis extends IsNumericAxis {

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
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	default void setSuggestedMax(double suggestedMax) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
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
