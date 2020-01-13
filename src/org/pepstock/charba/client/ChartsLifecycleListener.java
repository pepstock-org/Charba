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
 * Interface to catch the initialization, destroy and configuration of all charts.<br>
 * This is acting differently from a chart plugin because it will get all triggers for all charts.<br>
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
	void onBeforeInit(IsChart chart);

	/**
	 * Called after 'chart' has been initialized.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterInit(IsChart chart);

	/**
	 * Called before configuring 'chart'.
	 * 
	 * @param chart the chart instance.
	 */
	void onBeforeConfigure(IsChart chart);

	/**
	 * Called after 'chart' has been configured.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterConfigure(IsChart chart);

	/**
	 * Called before the chart has been destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	void onBeforeDestroy(IsChart chart);

	/**
	 * Called after the chart has been destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterDestroy(IsChart chart);

}
