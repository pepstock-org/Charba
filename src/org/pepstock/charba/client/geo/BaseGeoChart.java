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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;

/**
 * This is the base chart definition for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <D> data set type for the specific chart, extends BaseGeoDataset
 */
abstract class BaseGeoChart<D extends Dataset> extends AbstractChart implements IsDatasetCreator<D> {

	// controller instance
	private BaseGeoController geoController = null;
	// extending chart type
	// this is a custom implementation because
	// GEO controllers don't extend any existing chart type
	static final GeoExtendedChartType GEO_EXTENDED_CHART_TYPE = new GeoExtendedChartType();

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param type type of chart
	 */
	BaseGeoChart(Type type) {
		super(type);
	}

	/**
	 * Returns the controller type
	 * 
	 * @return the controller type
	 */
	abstract ControllerType getControllerType();

	/**
	 * Returns the controller instance or <code>null</code> if chart not initialized.
	 * 
	 * @return the controller instance
	 */
	final BaseGeoController getController() {
		return geoController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#applyConfiguration()
	 */
	@Override
	protected final void applyConfiguration() {
		// because before creating chart options
		// new chart (by controller) must be defined
		// gets the controller type
		// could be choropleth or bubblemap
		ControllerType type = getControllerType();
		// registers the controller
		// if not register
		// checks for GEO controller
		if (type.register() && geoController == null) {
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(type);
			// checks if controller is a base GEO controller
			Checker.assertCheck(controllerInstance instanceof BaseGeoController, "Controller stored for " + getControllerType().value() + " is not a " + BaseGeoController.class.getName());
			// casts to GEO controller
			geoController = (BaseGeoController) controllerInstance;
		}
		// gets GEO options
		BaseGeoOptions options;
		// checks if options is a GEO one
		Checker.assertCheck(getOptions() instanceof BaseGeoOptions, "Chart options are " + getOptions().getClass().getName() + " and not a " + BaseGeoOptions.class.getName());
		// cats and store
		options = (BaseGeoOptions) getOptions();
		// disables legend
		options.getLegend().setDisplay(false);
	}

}