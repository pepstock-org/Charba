/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.matrix;

import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.controllers.AbstractController;
import org.pepstock.charba.client.controllers.ControllerProvider;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.items.ChartElementFactories;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Controller implementation to create matrix chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class MatrixController extends AbstractController {

	// static reference of controller provider for matrix chart
	static final ControllerProvider PROVIDER = new MatrixControllerProvier();
	// injectable resource for controller
	private static final MatrixControllerResource RESOURCE = new MatrixControllerResource();

	/**
	 * Creates the controller using the type passed as argument.
	 * 
	 * @param type controller type
	 */
	private MatrixController(ControllerType type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#mustBeRegistered()
	 */
	@Override
	public boolean mustBeRegistered() {
		// Matrix controller MUST NOT be registered
		return false;
	}

	/**
	 * Inner class which is implementing a {@link ControllerProvider} to create a matrix controller.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class MatrixControllerProvier implements ControllerProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.controllers.ControllerProvider#provide(org.pepstock.charba.client.controllers.ControllerType)
		 */
		@Override
		public Controller provide(ControllerType controllerType) {
			// inject Chart.js and date library if not already loaded
			ResourcesType.getResources().inject();
			// injects CHARTJS-MATRIX controller
			Injector.ensureInjected(RESOURCE);
			// registers matrix element factory
			ChartElementFactories.get().register(MatrixElement.FACTORY);
			// creates and returns an fake controller
			return new MatrixController(controllerType);
		}

	}

}