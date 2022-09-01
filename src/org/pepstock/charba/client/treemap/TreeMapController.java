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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.controllers.AbstractController;
import org.pepstock.charba.client.controllers.ControllerProvider;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.items.ChartElementFactories;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Controller implementation to create treemap chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class TreeMapController extends AbstractController {

	// static reference of controller provider for treemap chart
	static final ControllerProvider PROVIDER = new TreeMapControllerProvier();
	// injectable resource for controller
	private static final TreeMapControllerResource RESOURCE = new TreeMapControllerResource();

	/**
	 * Creates the controller using the type passed as argument.
	 * 
	 * @param type controller type
	 */
	private TreeMapController(ControllerType type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#mustBeRegistered()
	 */
	@Override
	public boolean mustBeRegistered() {
		// Treemap controller MUST NOT be registered
		return false;
	}

	/**
	 * Inner class which is implementing a {@link ControllerProvider} to create a treemap controller.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class TreeMapControllerProvier implements ControllerProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.controllers.ControllerProvider#provide(org.pepstock.charba.client.controllers.ControllerType)
		 */
		@Override
		public Controller provide(ControllerType controllerType) {
			// inject Chart.js and date library if not already loaded
			ResourcesType.getResources().inject();
			// injects CHARTJS-TREEMAP controller
			Injector.ensureInjected(RESOURCE);
			// registers tree map element factory
			ChartElementFactories.get().register(TreeMapElement.FACTORY);
			// creates and returns an fake controller
			return new TreeMapController(controllerType);
		}

	}

}
