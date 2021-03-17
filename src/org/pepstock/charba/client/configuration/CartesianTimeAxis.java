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

import java.util.Date;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * This object is used to map defined axis as time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianTimeAxis extends CartesianAxis<CartesianTimeTick> {

	private final CartesianTimeTick ticks;

	private final Time time;

	private final Adapters adapters;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianTimeAxis(IsChart chart) {
		// uses X as axis id
		this(chart, AxisType.TIME.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianTimeAxis(IsChart chart, String id) {
		this(chart, IsScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianTimeAxis(IsChart chart, IsScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind
	 */
	public CartesianTimeAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, AxisType.TIME.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind
	 */
	public CartesianTimeAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, IsScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind
	 */
	public CartesianTimeAxis(IsChart chart, IsScaleId id, AxisKind kind) {
		this(chart, id, AxisType.TIME, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.X));
	}

	/**
	 * Builds the object storing the chart instance and cartesian axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind
	 */
	protected CartesianTimeAxis(IsChart chart, IsScaleId id, AxisType type, AxisKind kind) {
		super(chart, id, AxisType.TIME, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.X));
		// creates the time object
		this.time = new Time(this);
		// creates the ticks instance
		this.ticks = new CartesianTimeTick(this);
		// creates the adapters object
		this.adapters = new Adapters(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianTimeTick getTicks() {
		return ticks;
	}

	/**
	 * Returns the time element.
	 * 
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Returns the adapters element.
	 * 
	 * @return the adapters
	 */
	public Adapters getAdapters() {
		return adapters;
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max If defined, this will override the data maximum.
	 */
	public void setMax(Date max) {
		getScale().setMax(max);
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return If defined, this will override the data maximum.
	 */
	public Date getMax() {
		return getScale().getMaxAsDate();
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public void setMin(Date min) {
		getScale().setMin(min);
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum.
	 */
	public Date getMin() {
		return getScale().getMinAsDate();
	}

}