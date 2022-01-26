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

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.CartesianTimeAxis;
import org.pepstock.charba.client.configuration.CartesianTimeSeriesAxis;
import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.items.ScaleTickItem;

/**
 * Interface to implement if wants to change the tick marks to include information about the data type, for cartesian time axes.<br>
 * It can return a {@link List} of strings (for multiple lines).
 * 
 * @author Andrea "Stock" Stocchero
 * @see CartesianTimeAxis
 * @see CartesianTimeSeriesAxis
 */
public interface TimeTickCallback {

	/**
	 * Changes the tick marks to include information about the data type.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param value value of tick as date
	 * @param label label of tick, passed by CHART.JS formatting the date by the selected {@link TimeUnit} and its display format.
	 * @param index index of tick
	 * @param values list of all tick values
	 * @return the tick to apply or if the callback returns <code>null</code> the associated grid line will be hidden.<br>
	 *         It can return a {@link List} of strings (for multiple lines) or a string (for single line).
	 */
	Object onCallback(Axis axis, Date value, String label, int index, List<ScaleTickItem> values);

}