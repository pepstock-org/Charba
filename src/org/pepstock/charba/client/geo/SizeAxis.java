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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.IsChart;
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
 * The scale is used to map the values to symbol radius size.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SizeAxis extends CartesianLinearAxis implements IsSizeAxis {

	/**
	 * Default missing radius options, {@value DEFAULT_MISSING_RADIUS}.
	 */
	public static final double DEFAULT_MISSING_RADIUS = 1D;

	/**
	 * Default minimum range options, <b>{@value DEFAULT_MINIMUM_RANGE}</b>.
	 */
	public static final int DEFAULT_MINIMUM_RANGE = 2;

	/**
	 * Default maximum range options, <b>{@value DEFAULT_MAXIMUM_RANGE}</b>.
	 */
	public static final int DEFAULT_MAXIMUM_RANGE = 20;

	/**
	 * Default range options, <b>[{@value DEFAULT_MAXIMUM_RANGE}</b>, <b>{@value DEFAULT_MAXIMUM_RANGE}]</b>.
	 */
	public static final List<Integer> DEFAULT_RANGE = Collections.unmodifiableList(Arrays.asList(DEFAULT_MINIMUM_RANGE, DEFAULT_MAXIMUM_RANGE));

	/**
	 * Size axis id.
	 */
	public static final ScaleId ID = ScaleId.create("size");

	/**
	 * Size axis type.
	 */
	public static final AxisType TYPE = AxisType.create("size", null, ID, ScaleDataType.NUMBER);
	// projection mapper factory
	final SizeAxisRemappedOptionsFactory factory;
	// projection mapper
	private SizeAxisMapper mapper;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 */
	public SizeAxis(IsChart chart) {
		super(chart, ID, TYPE, AxisKind.X);
		// chart must be only bubble map
		Checker.assertCheck(BubbleMapChart.CONTROLLER_TYPE.equals(chart.getType()), "Size axis must be used ONLY by bubble map chart");
		// creates the factory
		this.factory = new SizeAxisRemappedOptionsFactory(this);
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
	 * @see org.pepstock.charba.client.geo.IsSizeAxis#getMapper()
	 */
	@Override
	public SizeAxisMapper getMapper() {
		return mapper;
	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class SizeAxisRemappedOptionsFactory extends ControllerMapperFactory<SizeAxisMapper> {

		private final Axis parent;

		/**
		 * Creates the factory of the mapper.
		 * 
		 * @param parent axis which the mapper belongs to
		 */
		SizeAxisRemappedOptionsFactory(Axis parent) {
			super(BubbleMapChart.CONTROLLER_TYPE);
			this.parent = Checker.checkAndGetIfValid(parent, "Axis instance ");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public SizeAxisMapper create(NativeObject nativeObject) {
			return new SizeAxisMapper(this.parent, nativeObject);
		}

	}

}