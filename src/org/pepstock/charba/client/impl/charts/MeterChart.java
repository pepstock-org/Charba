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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.controllers.ControllerType;

/**
 * METER chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MeterChart extends BaseMeterChart<MeterDataset> {

	/**
	 * Name of chart type <b>{@value TYPE}</b> for meter
	 */
	public static final String TYPE = "meter";
	// static reference to controller type
	static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, ChartType.DOUGHNUT);
	// chart options
	private final MeterOptions options;

	/**
	 * Builds the object.
	 */
	public MeterChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new MeterOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public MeterOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset()
	 */
	@Override
	public MeterDataset newDataset() {
		return newDataset(DEFAULT_MAX);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.charts.BaseMeterChart#newDataset(double)
	 */
	@Override
	public MeterDataset newDataset(double max) {
		return new MeterDataset(max);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.charts.BaseMeterChart#getControllerType()
	 */
	@Override
	ControllerType getControllerType() {
		return CONTROLLER_TYPE;
	}

}