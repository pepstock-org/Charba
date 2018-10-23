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
package org.pepstock.charba.client.jsinterop.callbacks.handlers;

import org.pepstock.charba.client.jsinterop.items.AxisItem;
import org.pepstock.charba.client.jsinterop.utils.Window;

/**
 * Interface to be implemented which can be used to change parameters in the scale during data limits determination.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.options.scales.Axis
 */
public interface AxisDataLimitsHandler {

	/**
	 * Callback that runs before data limits are determined.
	 * 
	 * @param chart chart instance
	 * @param item axis item instance
	 * @see org.pepstock.charba.client.AbstractChart
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	void onBeforeDataLimits(Window window, AxisItem item);

	/**
	 * Callback that runs after data limits are determined.
	 * 
	 * @param chart chart instance
	 * @param item axis item instance
	 * @see org.pepstock.charba.client.AbstractChart
	 * @see org.pepstock.charba.client.items.AxisItem
	 */
	void onAfterDataLimits(Window window, AxisItem item);

}