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
package org.pepstock.charba.client.jsinterop.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.jsinterop.Controller;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Glabal configuration to set controllers at global level.<br>
 * It maps the CHART.JS object of controller, <code>chart.controllers</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Controllers extends NativeObjectContainer {

	// list of global controllers set by user (not OOTB)
	private final Map<String, Controller> controllers = new HashMap<>();
	// CHART.JS dataset controller
	private final NativeDatasetController datasetController;

	/**
	 * @param datasetController
	 */
	public Controllers(NativeObject controllers, NativeDatasetController datasetController) {
		super(controllers);
		this.datasetController = datasetController;
	}

	/**
	 * Registers a controller as global, to apply to all charts. 
	 * @param controller controller instance
	 * @return <code>true</code> if registered, otherwise <code>false</code> if the controller is already registered with the controller type of controller instance.
	 * @throws InvalidControllerTypeException  if the controller type is not correct.
	 */
	public boolean extend(Controller controller) throws InvalidControllerTypeException{
		// checks the consistency of controller
		// and creates a java script object, wrapper of the controller
		WrapperController wController = check(controller);
		// checks if consistent
		if (wController != null) {
			// gets the chart type to extend
			ChartType chartType = controller.getChartType();
			// if not null, the controller extends an existing chart
			if (chartType != null) {
				extendController(controller.getType(), chartType, wController.getObject());
				return true;
			} else {
				// if here, the controller is new TYPE (no extend)
				registerController(controller.getType(), wController.getObject());
				return true;
			}
		}
		// controller already exists
		return false;
	}
	
	/**
	 * Checks the consistency of controller type and creates a wrapper.
	 * @param controller controller implementation
	 * @return 
	 * @throws InvalidControllerTypeException if the controller type is not correct.
	 */
	private WrapperController check(Controller controller) throws InvalidControllerTypeException {
		// checks the controller type
		ControllerTypeChecker.check(controller.getType());
		// checks if type is already registered
		if (controllers.containsKey(controller.getType().name())){
			return null;
		}
		// stores the type into a set
		controllers.put(controller.getType().name(), controller);
		// creates a java script object, wrapper of the controller
		return new WrapperController(controller);
	}

	/**
	 * Gets all global registered controllers ids.
	 * @return all global registered controllers ids.
	 */
	public Set<String> getTypeNames(){
		return controllers.keySet();
	}
	
	public ControllerType getTypeByString(String type) {
		if (controllers.containsKey(type)) {
			Controller controller = controllers.get(type);
			return controller.getType();
		}
		return null;
	}

	/**
	 * Calls <code>Chart.DatasetController.extend</code> function to register a controller.
	 * 
	 * @param instance controller instance.
	 */
	private void registerController(ControllerType controllerType, NativeObject instance) {
		setValue(controllerType, datasetController.extend(instance));
	}

	/**
	 * Calls <code>Chart.controllers[chartType].extend</code> function to register a controller, extending an existing chart type.
	 * 
	 * @param instance controller instance.
	 */
	private void extendController(ControllerType controllerType, ChartType chartType, NativeObject instance) {
		Defaults.get().addControllerOptions(controllerType, chartType);
		registerController(controllerType, instance);
	}
//	
//		$wnd.Chart.defaults[controllerType] = $wnd.Chart.defaults[chartType];
//		$wnd.Chart.controllers[controllerType] = $wnd.Chart.controllers[chartType].extend(instance);
//	}-*/;

}
