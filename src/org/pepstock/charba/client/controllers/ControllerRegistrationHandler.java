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
package org.pepstock.charba.client.controllers;

/**
 * This interface enables the capability to be before and after a controller will be registered.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ControllerRegistrationHandler {

	/**
	 * Invoked before the controller will be register to CHART.JS.
	 * 
	 * @param controllerType the controller type which is registering
	 */
	default void onBeforeRegister(ControllerType controllerType) {
		// do nothing
	}

	/**
	 * Invoked after the controller is registered to CHART.JS.
	 * 
	 * @param controllerType the controller type which is registering
	 * @param registered <code>true</code> if the controller has been registered othewise <code>false</code>
	 */
	default void onAfterRegister(ControllerType controllerType, boolean registered) {
		// do nothing
	}

}
