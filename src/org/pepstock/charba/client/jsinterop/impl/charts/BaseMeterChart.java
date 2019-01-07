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
package org.pepstock.charba.client.jsinterop.impl.charts;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.controllers.ControllerType;
import org.pepstock.charba.client.jsinterop.controllers.InvalidControllerTypeException;

/**
 * This is an abstract meter chart, inherited by a meter and gauge charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseMeterChart<O extends MeterOptions, D extends MeterDataset> extends AbstractChart<O, D> {
	
	// exception message if not able to add internal plugin
	private static final String UNABLE_TO_EXTEND_CONTROLLER = "Unable to extend/register controller. ";

	/**
	 * Default of maximum of dataset
	 */
	protected static final double DEFAULT_MAX = 100D;

	/**
	 * Returns a dataset with a maximum value.
	 * @param max maximum value of dataset
	 * @return dataset instance
	 */
	public abstract D newDataset(double max);
	
	abstract ControllerType getControllerType();

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.AbstractChart#createChartOptions()
	 */
	@Override
	protected final ChartOptions createChartOptions() {
		try {
			ControllerType type = getControllerType();
			if (!Defaults.get().getControllers().isRegistered(type.name())) {
				Defaults.get().getControllers().extend(new BaseMeterController(type));
			}
			return super.createChartOptions();
		} catch (InvalidControllerTypeException e) {
			throw new UnsupportedOperationException(UNABLE_TO_EXTEND_CONTROLLER + e.getMessage(), e);
		}
	}	

}