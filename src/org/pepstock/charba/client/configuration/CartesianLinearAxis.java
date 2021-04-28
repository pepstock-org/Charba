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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * This object is used to map defined axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianLinearAxis extends CartesianAxis<CartesianLinearTick> implements IsLinearAxis {

	private final CartesianLinearTick ticks;

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianLinearAxis(IsChart chart) {
		// uses Y as axis id
		this(chart, AxisType.LINEAR.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianLinearAxis(IsChart chart, String id) {
		this(chart, IsScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianLinearAxis(IsChart chart, IsScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind.
	 */
	public CartesianLinearAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, AxisType.LINEAR.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianLinearAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, IsScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianLinearAxis(IsChart chart, IsScaleId id, AxisKind kind) {
		super(chart, id, AxisType.LINEAR, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.Y));
		// creates the ticks instance
		this.ticks = new CartesianLinearTick(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsLinearAxis#getAxisElement()
	 */
	@Override
	public final Axis getAxisElement() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianLinearTick getTicks() {
		return ticks;
	}

	/**
	 * Sets the value in pixels is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @param grace the value in pixels is added to the maximum data value and subtracted from the minimum data
	 */
	public void setGrace(int grace) {
		getScale().setGrace(grace);
	}

	/**
	 * Returns the value in pixels is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @return the value in pixels is added to the maximum data value and subtracted from the minimum data
	 */
	public int getGrace() {
		return getScale().getGrace();
	}

	/**
	 * Sets the value in percentage is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @param grace the value in percentage is added to the maximum data value and subtracted from the minimum data
	 */
	public void setGraceAsPercentage(String grace) {
		getScale().setGraceAsPercentage(grace);
	}

	/**
	 * Returns the value in percentage is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @return the value in percentage is added to the maximum data value and subtracted from the minimum data
	 */
	public String getGraceAsPercentage() {
		return getScale().getGraceAsPercentage();
	}

}