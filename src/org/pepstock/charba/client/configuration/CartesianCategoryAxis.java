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
 * This object is used to map defined axis as category.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianCategoryAxis extends CartesianAxis<CartesianCategoryTick> {

	private final CartesianCategoryTick ticks;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianCategoryAxis(IsChart chart) {
		// uses X as axis id
		this(chart, AxisType.CATEGORY.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianCategoryAxis(IsChart chart, String id) {
		this(chart, IsScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianCategoryAxis(IsChart chart, IsScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind.
	 */
	public CartesianCategoryAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, AxisType.CATEGORY.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianCategoryAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, IsScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianCategoryAxis(IsChart chart, IsScaleId id, AxisKind kind) {
		super(chart, id, AxisType.CATEGORY, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.X));
		// creates the ticks instance
		this.ticks = new CartesianCategoryTick(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianCategoryTick getTicks() {
		return ticks;
	}

	/**
	 * Sets the minimum item to display.
	 * 
	 * @param min The minimum item to display
	 */
	public void setMin(String min) {
		getScale().setMin(min);
	}

	/**
	 * Returns the minimum item to display
	 * 
	 * @return The minimum item to display
	 */
	public String getMin() {
		return getScale().getMinAsString();
	}

	/**
	 * Sets the maximum item to display.
	 * 
	 * @param max the maximum item to display.
	 */
	public void setMax(String max) {
		getScale().setMax(max);
	}

	/**
	 * Returns the maximum item to display.
	 * 
	 * @return the maximum item to display.
	 */
	public String getMax() {
		return getScale().getMaxAsString();
	}

}