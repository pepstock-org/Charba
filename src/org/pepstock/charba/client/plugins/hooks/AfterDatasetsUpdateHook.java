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
import org.pepstock.charba.client.items.PluginUpdateArgument;

/**
 * Called after the 'chart' datasets have been updated.<br>
 * Note that this hook will not be called if the datasets update has been previously cancelled.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface AfterDatasetsUpdateHook {

	/**
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 *
	 * @param chart the chart instance.
	 * @param argument the argument passed for update
	 */
	void onAfterDatasetsUpdate(IsChart chart, PluginUpdateArgument argument);

}
