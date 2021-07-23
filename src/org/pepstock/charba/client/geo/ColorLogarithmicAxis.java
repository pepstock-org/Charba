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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.configuration.CartesianLogarithmicAxis;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ScaleDataType;

/**
 * The coloring of the nodes will be done with a special color scale, for logarithmic data.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorLogarithmicAxis extends CartesianLogarithmicAxis implements IsColorAxis {

	/**
	 * Projection axis type.
	 */
	public static final AxisType TYPE = AxisType.create("colorLogarithmic", null, ColorAxis.ID, ScaleDataType.NUMBER);
	// projection mapper factory
	final ColorAxis.ColorAxisRemappedOptionsFactory factory;
	// projection mapper
	private ColorAxisMapper mapper;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 */
	public ColorLogarithmicAxis(IsChart chart) {
		super(chart, ColorAxis.ID, TYPE, AxisKind.X);
		// chart must be only choropleth
		Checker.assertCheck(ChoroplethChart.CONTROLLER_TYPE.equals(chart.getType()), "Color logarithmic axis must be used ONLY by choropleth chart");
		// creates the factory
		this.factory = new ColorAxis.ColorAxisRemappedOptionsFactory();
		// initializes the mapper
		afterAxisConfigurationUpdate();
	}

	/**
	 * Reloads the extended scale
	 */
	void afterAxisConfigurationUpdate() {
		// creates mapper
		this.mapper = getConfiguration().getRemappedOptions(factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.IsColorAxis#getMapper()
	 */
	@Override
	public ColorAxisMapper getMapper() {
		return mapper;
	}

}
