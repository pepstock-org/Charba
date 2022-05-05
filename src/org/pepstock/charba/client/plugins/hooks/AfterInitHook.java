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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;

/**
 * Called after 'chart' has been initialized and before the first update.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface AfterInitHook {

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 *
	 * @param chart the chart instance.
	 * @param nativeChart CHART.JS chart instance
	 */
	void onAfterInit(IsChart chart, Chart nativeChart);

}
