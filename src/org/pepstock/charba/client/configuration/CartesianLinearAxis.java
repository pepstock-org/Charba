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
import org.pepstock.charba.client.callbacks.AxisBuildTicksCallback;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;

/**
 * This object is used to map defined axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianLinearAxis extends CartesianAxis<CartesianLinearTick> {

	private final CartesianLinearTick ticks;
	
	private final LinearAxisBuildTicksCallbackHandler buildTicksCallbackHandler;

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianLinearAxis(IsChart chart) {
		this(chart, CartesianAxisType.Y);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianLinearAxis(IsChart chart, CartesianAxisType cartesianType) {
		super(chart, AxisType.LINEAR, cartesianType);
		// creates the ticks instance
		this.ticks = new CartesianLinearTick(this);
		// create build ticks callback handler
		this.buildTicksCallbackHandler = new LinearAxisBuildTicksCallbackHandler(this);
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
	 * Returns the user callback that runs before/after ticks are created.
	 * 
	 * @return the axisBuildTicksCallback
	 */
	public AxisBuildTicksCallback getAxisBuildTicksCallback() {
		return buildTicksCallbackHandler.getCallback();
	}

	/**
	 * Sets the user callback that runs before/after ticks are created.
	 * 
	 * @param axisBuildTicksCallback the axisBuildTicksCallback to set
	 */
	public void setAxisBuildTicksCallback(AxisBuildTicksCallback axisBuildTicksCallback) {
		buildTicksCallbackHandler.setCallback(axisBuildTicksCallback);
	}

}