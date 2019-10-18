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
import org.pepstock.charba.client.items.AxisItem;

/**
 * Interface to be implemented which can be used to change parameters in the scale during ticks building.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AxisBuildTicksCallback extends BaseAxisBuildTicksCallback {

	/**
	 * Callback that runs after ticks are created. Useful for filtering ticks.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item axis item instance
	 * @param ticks list of created ticks
	 */
	void onAfterBuildTicks(Axis axis, AxisItem item, List<Double> ticks);

}