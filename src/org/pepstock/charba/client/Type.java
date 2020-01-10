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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.Key;

/**
 * Interface to map the type and scale type of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface Type extends Key {

	/**
	 * Returns <code>true</code> if type passed as argument is not <code>null</code> and its scale type is not <code>null</code>
	 * as well.
	 * 
	 * @param type type to be checked
	 * @return <code>true</code> if type passed as argument is not <code>null</code> and its scale type is not <code>null</code>
	 *         as well.
	 */
	static boolean isValid(Type type) {
		return Key.isValid(type) && type.scaleType() != null;
	}

	/**
	 * Checks if type passed as argument is not <code>null</code> and its scale type is not <code>null</code> as well. If not,
	 * throw a {@link IllegalArgumentException}.
	 * 
	 * @param type type to be checked
	 */
	static void checkIfValid(Type type) {
		if (!isValid(type)) {
			throw new IllegalArgumentException("Type is null or not consistent");
		}
	}

	/**
	 * Returns the scale type of the chart.
	 * 
	 * @return the scale type of the chart.
	 */
	ScaleType scaleType();

}