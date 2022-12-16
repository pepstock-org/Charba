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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.configuration.CartesianLinearAxis;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.options.ScaleId;

/**
 * The coloring of the nodes will be done with a special color scale.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorAxis extends CartesianLinearAxis implements IsColorAxis {

	/**
	 * Default missing color options, {@link HtmlColor#TRANSPARENT}.
	 */
	public static final String DEFAULT_MISSING_COLOR = HtmlColor.TRANSPARENT.toRGBA();

	/**
	 * Default quantize options, <b>{@value DEFAULT_QUANTIZE}</b>.
	 */
	public static final int DEFAULT_QUANTIZE = 0;

	/**
	 * Projection axis id.
	 */
	public static final ScaleId ID = ScaleId.create("color");
	/**
	 * Projection axis type.
	 */
	public static final AxisType TYPE = AxisType.create("color", null, ID, ScaleDataType.NUMBER);
	// projection mapper factory
	final ColorAxisRemappedOptionsFactory factory;
	// projection mapper
	private ColorAxisMapper mapper;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 */
	public ColorAxis(IsChart chart) {
		super(chart, ID, TYPE, AxisKind.X);
		// chart must be only choropleth
		Checker.assertCheck(ChoroplethChart.CONTROLLER_TYPE.equals(chart.getType()), "Color axis must be used ONLY by choropleth chart");
		// creates the factory
		this.factory = new ColorAxisRemappedOptionsFactory(this);
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

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class ColorAxisRemappedOptionsFactory extends ControllerMapperFactory<ColorAxisMapper> {

		private final Axis parent;

		/**
		 * Creates the factory of the mapper
		 * 
		 * @param parent axis which the mapper belongs to
		 */
		ColorAxisRemappedOptionsFactory(Axis parent) {
			super(ChoroplethChart.CONTROLLER_TYPE);
			this.parent = Checker.checkAndGetIfValid(parent, "Axis instance ");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ColorAxisMapper create(NativeObject nativeObject) {
			return new ColorAxisMapper(parent, nativeObject);
		}

	}
}