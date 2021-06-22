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

import org.pepstock.charba.client.controllers.ControllerType;

/**
 * A choropleth chart is used to render maps with the area filled according to some numerical value.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChoroplethChart extends BaseGeoChart<ChoroplethDataset> {

	/**
	 * Name of chart type <b>{@value TYPE}</b>
	 */
	public static final String TYPE = "choropleth";
	/**
	 * Choropleth controller type
	 */
	public static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, BaseGeoChart.GEO_EXTENDED_CHART_TYPE, BaseGeoController.PROVIDER);
	// chart options
	private final ChoroplethOptions options;

	/**
	 * Builds the object.
	 */
	public ChoroplethChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new ChoroplethOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public ChoroplethOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public ChoroplethDataset newDataset(boolean hidden) {
		// hidden is ignored because a choropeth chart has got only 1 dataset
		return new ChoroplethDataset();
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