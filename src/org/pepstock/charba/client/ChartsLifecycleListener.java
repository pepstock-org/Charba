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

/**
 * Interface to catch the init and destroy of all charts. This is acting differently from achart plugin because it will get all
 * triggers for all charts.<br>
 * Used to clean up resources, created by a special implementation and not ot-of-the-box of Charba, like CHART.JS plugins.
 * 
 * @author Andrea "Stock" Stocchero
 * @see Charts
 */
public interface ChartsLifecycleListener {

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart the chart instance.
	 */
	void onBeforeInit(AbstractChart<?, ?> chart);

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterInit(AbstractChart<?, ?> chart);

	/**
	 * Called before the chart as been destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	void onBeforeDestroy(AbstractChart<?, ?> chart);

	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterDestroy(AbstractChart<?, ?> chart);

}
