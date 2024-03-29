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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This object is used to map defined axis as logarithmic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianLogarithmicAxis extends CartesianAxis<CartesianLogarithmicTick> implements IsNumericAxis {

	private final CartesianLogarithmicTick ticks;

	private final MinMaxCallbacksHandler<Double> minMaxHandler;

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianLogarithmicAxis(IsChart chart) {
		// uses Y as axis id
		this(chart, ChartAxisType.LOGARITHMIC.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianLogarithmicAxis(IsChart chart, String id) {
		this(chart, ScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianLogarithmicAxis(IsChart chart, ScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind
	 */
	public CartesianLogarithmicAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, ChartAxisType.LOGARITHMIC.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind
	 */
	public CartesianLogarithmicAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, ScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind
	 */
	public CartesianLogarithmicAxis(IsChart chart, ScaleId id, AxisKind kind) {
		this(chart, id, ChartAxisType.LOGARITHMIC, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.Y));
	}

	/**
	 * Builds the object storing the chart instance and cartesian axis type, to use to extend the axis.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind
	 */
	protected CartesianLogarithmicAxis(IsChart chart, ScaleId id, AxisType type, AxisKind kind) {
		super(chart, id, type, kind);
		// creates the ticks instance
		this.ticks = new CartesianLogarithmicTick(this);
		// creates min max handler
		this.minMaxHandler = new MinMaxCallbacksHandler<>(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianLogarithmicTick getTicks() {
		return ticks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsNumericAxis#getAxisElement()
	 */
	@Override
	public Axis getAxisElement() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasMinMaxHandler#getMinMaxHandler()
	 */
	@Override
	public MinMaxCallbacksHandler<Double> getMinMaxCallbacksHandler() {
		return minMaxHandler;
	}

}