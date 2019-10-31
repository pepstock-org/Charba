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
package org.pepstock.charba.client.data;

/**
 * Manages the order properties for datasets which this property is required.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface HasOrder {

	/**
	 * Returns an order option manager instance.
	 * 
	 * @return an order option manager instance
	 */
	Orderer getOrderer();

	/**
	 * Sets the drawing order of dataset.<br>
	 * Also affects order for stacking, tooltip, and legend.
	 * 
	 * @param order the drawing order of dataset.
	 */
	default void setOrder(int order) {
		// checks if orderer is consistent
		if (getOrderer() != null) {
			getOrderer().setOrder(order);
		}
	}

	/**
	 * Returns the drawing order of dataset.<br>
	 * Also affects order for stacking, tooltip, and legend.
	 * 
	 * @return the drawing order of dataset
	 */
	default int getOrder() {
		// checks if orderer is consistent
		if (getOrderer() != null) {
			return getOrderer().getOrder();
		}
		// if here, orderer is not consistent
		// then returns the default
		return Orderer.DEFAULT_ORDER;
	}

}