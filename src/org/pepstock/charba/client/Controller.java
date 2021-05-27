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
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.options.TransitionKey;

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
	 * Called before it initializes the controller.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	default void onBeforeInitialize(ControllerContext context, IsChart chart) {
		// do nothing
	}

	/**
	 * Called after it initializes the controller.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	default void onAfterInitialize(ControllerContext context, IsChart chart) {
		// do nothing
	}
	
	/**
	 * Called before it invokes to parse the data into the controller meta data.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param start start index of metadata
	 * @param count count of metadata
	 */
	default void onBeforeParse(ControllerContext context, IsChart chart, int start, int count) {
		// do nothing
	}

	/**
	 * Called after it invokes to parse the data into the controller meta data.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param start start index of metadata
	 * @param count count of metadata
	 */
	default void onAfterParse(ControllerContext context, IsChart chart, int start, int count) {
		// do nothing
	}

	/**
	 * Called before it draws the representation of the data set.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	default void onBeforeDraw(ControllerContext context, IsChart chart) {
		// do nothing
	}

	/**
	 * Called after it draws the representation of the data set.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	default void onAfterDraw(ControllerContext context, IsChart chart) {
		// do nothing
	}

	/**
	 * Called before it updates the elements in response to new data.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	default void onBeforeUpdate(ControllerContext context, IsChart chart, TransitionKey mode) {
		// do nothing
	}

	/**
	 * Called after it updates the elements in response to new data.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	default void onAfterUpdate(ControllerContext context, IsChart chart, TransitionKey mode) {
		// do nothing
	}

	/**
	 * Called before it ensures that the data set represented by this controller is linked to a scale.<br>
	 * Overridden to helpers.noop in the polar area and doughnut controllers as these chart types using a single scale.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	default void onBeforeLinkScales(ControllerContext context, IsChart chart) {
		// do nothing
	}

	/**
	 * Called after it ensures that the data set represented by this controller is linked to a scale.<br>
	 * Overridden to helpers.noop in the polar area and doughnut controllers as these chart types using a single scale.
	 * 
	 * @param context context of controller
	 * @param chart chart instance
	 */
	default void onAfterLinkScales(ControllerContext context, IsChart chart) {
		// do nothing
	}

}
