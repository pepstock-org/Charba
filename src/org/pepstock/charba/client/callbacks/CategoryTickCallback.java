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
package org.pepstock.charba.client.callbacks;

import java.util.List;

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.CartesianCategoryAxis;

/**
 * Interface to implement if wants to change the tick marks to include information about the data type, for cartesian category axes.<br>
 * It can return a {@link List} of strings (for multiple lines).
 * 
 * @author Andrea "Stock" Stocchero
 * @see CartesianCategoryAxis
 */
public interface CategoryTickCallback {

	/**
	 * Changes the tick marks to include information about the data type.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param value value of tick
	 * @param index index of tick
	 * @param values list of all tick values
	 * @return the ticks to apply or if the callback returns <code>null</code> the associated grid line will be hidden.<br>
	 *         It can return a {@link List} of strings (for multiple lines) or a string (for single line).
	 */
	Object onCallback(Axis axis, String value, int index, List<String> values);

}