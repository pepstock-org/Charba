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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.controllers.ControllerType;

/**
 * This is an abstract meter chart, inherited by a meter and gauge charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> Dataset type for the specific chart, extends MeterDataset
 */
abstract class BaseMeterChart<D extends MeterDataset> extends AbstractChart<D> {

	/**
	 * Default of maximum value of data into a dataset (percentage based), <b>{@value DEFAULT_MAX}</b>.
	 */
	public static final double DEFAULT_MAX = 100D;

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param type type of chart
	 */
	BaseMeterChart(Type type) {
		super(type);
	}

	/**
	 * Returns a dataset with a maximum value.
	 * 
	 * @param max maximum value of dataset
	 * @return dataset instance
	 */
	public abstract D newDataset(double max);

	/**
	 * Returns the controller type
	 * 
	 * @return the controller type
	 */
	abstract ControllerType getControllerType();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#createChartOptions()
	 */
	@Override
	protected final ChartOptions createChartOptions() {
		// THIS METHOD must be extended
		// because before creating chart options
		// new chart (by controller) must be defined
		// gets the controller type
		// could be gauge or meter
		ControllerType type = getControllerType();
		// checks if already registered
		if (!Defaults.get().getControllers().isRegistered(type.value())) {
			// if not, adds a controller
			Defaults.get().getControllers().extend(new BaseMeterController(type));
		}
		// calls super chart options creations
		return super.createChartOptions();
	}

}