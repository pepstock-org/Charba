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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * Configuration of chart which could be stacked.<br>
 * It uses Category axis for X axis and Linear for Y axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class StackedLineOptions extends LineOptions {

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bar chart
	 */
	public StackedLineOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		this(chart, defaultValues, IndexAxis.X);
	}

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bar chart
	 * @param indexAxis defines the orientation of the bar chart
	 */
	public StackedLineOptions(IsChart chart, IsDefaultScaledOptions defaultValues, IndexAxis indexAxis) {
		// asks to do not create a scale
		super(chart, defaultValues);
		// stores if x axis
		boolean xAxis = IndexAxis.X.equals(indexAxis);
		// creates the axes
		CartesianCategoryAxis axis1 = new CartesianCategoryAxis(chart, xAxis ? AxisKind.X : AxisKind.Y);
		CartesianLinearAxis axis2 = new CartesianLinearAxis(chart, xAxis ? AxisKind.Y : AxisKind.X);
		// sets the axis as stacked
		axis2.setStacked(true);
		// stores axes
		getScales().setAxes(axis1, axis2);
		// stores index axis
		getConfiguration().setIndexAxis(indexAxis);
	}

	/**
	 * If invoked, this is ignored because the {@link IndexAxis} is already set, based on the chart class.
	 * 
	 * @param indexAxis this is ignored because the {@link IndexAxis} is already set, based on the chart class
	 */
	@Override
	public final void setIndexAxis(IndexAxis indexAxis) {
		// ignored
	}
}