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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.controllers.AbstractController;
import org.pepstock.charba.client.controllers.ControllerProvider;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.items.ChartElementFactories;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Controller implementation to create charts like choropleth and bubbleMap.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class BaseGeoController extends AbstractController {

	// static reference of controller provider for GEO charts
	static final ControllerProvider PROVIDER = new BaseGeoControllerProvier();
	// injectable resource for controller
	private static final GeoControllerResource RESOURCE = new GeoControllerResource();

	/**
	 * Creates the controller using the type passed as argument.
	 * 
	 * @param type controller type, could be choropleth and bubbleMap.
	 */
	private BaseGeoController(ControllerType type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#mustBeRegistered()
	 */
	@Override
	public boolean mustBeRegistered() {
		// GEO controller MUST NOT be registered
		return false;
	}

	/**
	 * Inner class which is implementing a {@link ControllerProvider} to create a base GEO controller.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BaseGeoControllerProvier implements ControllerProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.controllers.ControllerProvider#provide(org.pepstock.charba.client.controllers.ControllerType)
		 */
		@Override
		public Controller provide(ControllerType controllerType) {
			// registers the axes
			AxisType.register(ProjectionAxis.TYPE);
			AxisType.register(ColorAxis.TYPE);
			AxisType.register(SizeAxis.TYPE);
			// inject Chart.js and date library if not already loaded
			ResourcesType.getResources().inject();
			// injects CHARTJS-GEO controller
			Injector.ensureInjected(RESOURCE);
			// registers the geo feature elements
			ChartElementFactories.get().register(GeoFeatureElement.FACTORY);
			// creates and returns an fake controller
			return new BaseGeoController(controllerType);
		}

	}

}