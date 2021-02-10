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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Global configuration to set controllers at global level.<br>
 * It maps the CHART.JS object of controller, <code>chart.controllers</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Controllers {

	// singleton instance
	private static final Controllers INSTANCE = new Controllers();
	// list of global controllers set by user (not OOTB)
	// K = controller type name as string, V = controller instance
	private final Map<String, Controller> controllersInstances = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private Controllers() {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return controller instance
	 */
	public static Controllers get() {
		return INSTANCE;
	}

	/**
	 * Registers a controller as global, to apply to all charts.
	 * 
	 * @param controller controller instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the controller is already registered with the controller type of controller instance.
	 */
	boolean register(Controller controller) {
		// checks the consistency of controller
		// and creates a java script object, wrapper of the controller
		WrapperController wController = check(controller);
		// checks if consistent
		if (wController != null) {
			// gets the controller type
			ControllerType type = controller.getType();
			// extends an existing chart
			JsControllerHelper.get().register(type, wController.nativeObject());
			return true;
		}
		// controller already exists
		return false;
	}

	/**
	 * Checks the consistency of controller type and creates a wrapper.
	 * 
	 * @param controller controller implementation
	 * @return the wrapper controller instance or <code>null</code> if there is any error.
	 */
	private WrapperController check(Controller controller) {
		// checks the consistency of controller
		if (controller == null) {
			// if not, returns a not inconsistent wrapper
			return null;
		}
		// checks the controller type
		ControllerTypeChecker.check(controller.getType());
		// checks if type is already registered
		if (controllersInstances.containsKey(controller.getType().value())) {
			return null;
		}
		// stores the type into a set
		controllersInstances.put(controller.getType().value(), controller);
		// creates a java script object, wrapper of the controller
		return new WrapperController(controller);
	}

	/**
	 * Checks if the controller is registered by its type.
	 * 
	 * @param type type of new chart as string.
	 * @return <code>true</code> if registered, otherwise <code>false</code>.
	 */
	public boolean isRegistered(String type) {
		// checks if argument is consistent
		if (type != null) {
			return controllersInstances.containsKey(type);
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Gets all global registered controllers types.
	 * 
	 * @return all global registered controllers types.
	 */
	public Set<String> getTypeNames() {
		return controllersInstances.keySet();
	}

	/**
	 * Returns the controller type by name as string.
	 * 
	 * @param type controller type as string.
	 * @return the controller type if exists or <code>null</code> if does not exist.
	 */
	public ControllerType getTypeByString(String type) {
		// checks if argument is consistent
		// checks in the map of controller
		if (type != null && controllersInstances.containsKey(type)) {
			// gets controller
			Controller controller = controllersInstances.get(type);
			// returns controller type
			return controller.getType();
		}
		return null;
	}

	/**
	 * Returns the controller by name as {@link ControllerType}.
	 * 
	 * @param type controller as {@link ControllerType}.
	 * @return the controller if exists or <code>null</code> if does not exist.
	 */
	public Controller getController(ControllerType type) {
		// checks if argument is consistent
		// checks in the map of controller
		if (Type.isValid(type)) {
			// gets and returns controller
			return getController(type.value());
		}
		return null;
	}

	/**
	 * Returns the controller by name as string.
	 * 
	 * @param type controller as string.
	 * @return the controller if exists or <code>null</code> if does not exist.
	 */
	public Controller getController(String type) {
		// checks if argument is consistent
		// checks in the map of controller
		if (type != null && controllersInstances.containsKey(type)) {
			// gets and returns controller
			return controllersInstances.get(type);
		}
		return null;
	}
}
