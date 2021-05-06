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
package org.pepstock.charba.client.zoom;

import java.util.Date;

import org.pepstock.charba.client.items.Undefined;

/**
 * {@link ZoomPlugin#ID} plugin default options interface for scale limit (min and max) elements.<br>
 * It contains all default values for scale limit.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultScaleLimit {

	/**
	 * Returns the minimum value of limit as string.
	 * 
	 * @return the minimum value of limit as string
	 */
	default String getMin() {
		return Undefined.STRING;
	}

	/**
	 * Returns the minimum value of limit as double.
	 * 
	 * @return the minimum value of limit as double
	 */
	default double getMinAsDouble() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the minimum value of limit as date.
	 * 
	 * @return the minimum value of limit as date
	 */
	default Date getMinAsDate() {
		return null;
	}

	/**
	 * Returns the maximum value of limit as double.
	 * 
	 * @return the maximum value of limit as double
	 */
	default double getMax() {
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the maximum value of limit as string.
	 * 
	 * @return the maximum value of limit as string
	 */
	default String getMaxAsString() {
		return Undefined.STRING;
	}

	/**
	 * Returns the maximum value of limit as date.
	 * 
	 * @return the maximum value of limit as date
	 */
	default Date getMaxAsDate() {
		return null;
	}
}