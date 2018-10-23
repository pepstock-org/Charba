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
package org.pepstock.charba.client.jsinterop.callbacks;


import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.items.AxisItem;

/**
 * Interface to be implemented which can be used to change parameters in the scale during ticks building.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.options.scales.Axis
 */
public interface AxisBuildTicksCallback {

	/**
	 * Callback that runs before ticks are created.
	 * 
	 * @param chart chart instance
	 * @param item axis item instance
	 * @see org.pepstock.charba.client.AbstractChart
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	void onBeforeBuildTicks(AbstractChart<?, ?> chart, AxisItem item);

	/**
	 * Callback that runs after ticks are created. Useful for filtering ticks.
	 * 
	 * @param chart chart instance
	 * @param item axis item instance
	 * @see org.pepstock.charba.client.AbstractChart
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	void onAfterBuildTicks(AbstractChart<?, ?> chart, AxisItem item);

}