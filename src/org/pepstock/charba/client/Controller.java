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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.controllers.ControllerContext;
import org.pepstock.charba.client.controllers.ControllerDatasetElement;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.options.IsAnimationModeKey;

/**
 * This interface enables the capability to create a custom chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface Controller {

	/**
	 * Returns <code>true</code> if all arguments are consistent.
	 * 
	 * @param controller controller instance to check
	 * @param context controller context instance, should be provided by CHART.JS.
	 * @param chart chart instance
	 * @return <code>true</code> if all arguments are consistent
	 */
	static boolean isConsistent(Controller controller, ControllerContext context, IsChart chart) {
		// checks if
		// controller is valid
		// chart is valid
		// type of chart is a controller
		// type of chart is equals to the controller type
		// the context is consistent
		return isValid(controller) && IsChart.isValid(chart) && chart.getType() instanceof ControllerType && Key.equals(chart.getType(), controller.getType()) && context != null;
	}

	/**
	 * Checks if key passed as argument is not <code>null</code> and its type is valid as well. If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param controller controller instance to check
	 */
	static void checkIfValid(Controller controller) {
		if (!isValid(controller)) {
			throw new IllegalArgumentException("Controller is null or not consistent");
		}
	}

	/**
	 * Returns <code>true</code> if the controller is consistent.
	 * 
	 * @param controller controller instance to check
	 * @return <code>true</code> if the controller is consistent
	 */
	static boolean isValid(Controller controller) {
		return controller != null && Type.isValid(controller.getType());
	}

	/**
	 * Controller must define a unique id in order to be configurable.<br>
	 * Returns the controller id.
	 * 
	 * @return the controller id.
	 */
	ControllerType getType();

	/**
	 * Initializes the controller.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	void initialize(ControllerContext context, IsChart chart);

	/**
	 * Create elements for each piece of data in the dataset. Store elements in an array on the dataset.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	void addElements(ControllerContext context, IsChart chart);

	/**
	 * Draw the representation of the dataset.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	void draw(ControllerContext context, IsChart chart);

	/**
	 * Remove hover styling from the given element.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param element element to be removed.
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void removeHoverStyle(ControllerContext context, IsChart chart, ControllerDatasetElement element, int datasetIndex, int index);

	/**
	 * Add hover styling to the given element.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param element element to be set.
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void setHoverStyle(ControllerContext context, IsChart chart, ControllerDatasetElement element, int datasetIndex, int index);

	/**
	 * Update the elements in response to new data.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	void update(ControllerContext context, IsChart chart, IsAnimationModeKey mode);

	/**
	 * Ensures that the dataset represented by this controller is linked to a scale.<br>
	 * Overridden to helpers.noop in the polar area and doughnut controllers as these chart types using a single scale.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	void linkScales(ControllerContext context, IsChart chart);

	/**
	 * Called by the main chart controller when an update is triggered<br>
	 * The default implementation handles the number of data points changing and creating elements appropriately.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param resetNewElements <code>true</code> if the new elements must be reset
	 */
	void buildOrUpdateElements(ControllerContext context, IsChart chart, boolean resetNewElements);

}
