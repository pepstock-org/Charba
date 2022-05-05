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
package org.pepstock.charba.client.plugins.hooks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.PluginResizeArgument;

/**
 * Called after the chart as been resized.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface ResizeHook {

	/**
	 * Called after the chart as been resized.
	 *
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the new canvas display size (eq. canvas.style width and height).
	 */
	void onResize(IsChart chart, PluginResizeArgument argument);

}
