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
 * Defines the methods needed to manage the attach and detach of the chart into and from the DOM tree.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface MutationHandler {

	/**
	 * Returns the ID of chart.<br>
	 * It could be considered as chart unique ID.
	 * 
	 * @return the ID of chart
	 */
	String getId();

	/**
	 * Invoked by mutation observer when a chart has been attached into the DOM tree.
	 */
	void onAttach();

	/**
	 * Invoked by mutation observer when a chart has been detached from the DOM tree.
	 */
	void onDetach();

}
