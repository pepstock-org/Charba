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
import org.pepstock.charba.client.callbacks.CategoryAxisBuildTicksCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.options.ScaleIdChecker;

/**
 * This object is used to map defined axis as category.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianCategoryAxis extends CartesianAxis<CartesianCategoryTick> {

	private final CartesianCategoryTick ticks;

	private final CategoryAxisBuildTicksCallbackHandler buildTicksCallbackHandler;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianCategoryAxis(IsChart chart) {
		// uses X as axis id
		this(chart, CartesianAxisType.X.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianCategoryAxis(IsChart chart, String id) {
		this(chart, ScaleIdChecker.key(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianCategoryAxis(IsChart chart, Key id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianCategoryAxis(IsChart chart, CartesianAxisType cartesianType) {
		// uses cartesian type as axis id
		// checking if consistent
		this(chart, Key.checkAndGetIfValid(cartesianType).getDefaultScaleId(), cartesianType);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianCategoryAxis(IsChart chart, String id, CartesianAxisType cartesianType) {
		this(chart, ScaleIdChecker.key(id), cartesianType);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianCategoryAxis(IsChart chart, Key id, CartesianAxisType cartesianType) {
		super(chart, id, AxisType.CATEGORY, Key.isValid(cartesianType) ? cartesianType : CartesianAxisType.getByScaleId(id, CartesianAxisType.X));
		// creates the ticks instance
		this.ticks = new CartesianCategoryTick(this);
		// create build ticks callback handler
		this.buildTicksCallbackHandler = new CategoryAxisBuildTicksCallbackHandler(this);
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

	/**
	 * Returns the user callback that runs before/after ticks are created.
	 * 
	 * @return the axisBuildTicksCallback
	 */
	public CategoryAxisBuildTicksCallback getAxisBuildTicksCallback() {
		return buildTicksCallbackHandler.getCallback();
	}

	/**
	 * Sets the user callback that runs before/after ticks are created.
	 * 
	 * @param axisBuildTicksCallback the axisBuildTicksCallback to set
	 */
	public void setAxisBuildTicksCallback(CategoryAxisBuildTicksCallback axisBuildTicksCallback) {
		buildTicksCallbackHandler.setCallback(axisBuildTicksCallback);
	}

}