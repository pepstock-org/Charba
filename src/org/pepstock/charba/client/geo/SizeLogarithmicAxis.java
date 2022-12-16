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
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.configuration.CartesianLogarithmicAxis;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ScaleDataType;

/**
 * The scale is used to map the values to symbol radius size, for logarithmic data.<br>
 * Provides the elements, as colored legend, which can provide the how the values are distributed on map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SizeLogarithmicAxis extends CartesianLogarithmicAxis implements IsSizeAxis {

	/**
	 * Size axis type.
	 */
	public static final AxisType TYPE = AxisType.create("sizeLogarithmic", null, SizeAxis.ID, ScaleDataType.NUMBER);
	// projection mapper factory
	final SizeAxis.SizeAxisRemappedOptionsFactory factory;
	// projection mapper
	private SizeAxisMapper mapper;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 */
	public SizeLogarithmicAxis(IsChart chart) {
		super(chart, SizeAxis.ID, TYPE, AxisKind.X);
		// chart must be only bubble map
		Checker.assertCheck(BubbleMapChart.CONTROLLER_TYPE.equals(chart.getType()), "Size logarithmic axis must be used ONLY by bubble map chart");
		// creates the factory
		this.factory = new SizeAxis.SizeAxisRemappedOptionsFactory(this);
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

}