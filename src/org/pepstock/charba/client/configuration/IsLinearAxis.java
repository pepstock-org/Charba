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
import org.pepstock.charba.client.callbacks.BeginAtZeroCallback;

/**
 * Common methods for numeric scales (linear/log cartesian and radial).<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsLinearAxis extends IsNumericAxis, HasBeginAtZeroCallbackHandler {

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	default void setBeginAtZero(boolean beginAtZero) {
		// checks if axis is consistent
		if (getAxisElement() != null) {
			// reset callback
			setBeginAtZero((BeginAtZeroCallback) null);
			// stores value
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

}
