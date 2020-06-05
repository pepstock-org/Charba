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
import org.pepstock.charba.client.callbacks.TimeAxisBuildTicksCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.options.ScaleIdChecker;

/**
 * This object is used to map defined axis as time. This is used to have time series charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianTimeAxis extends CartesianAxis<CartesianTimeTick> {

	private final CartesianTimeTick ticks;

	private final TimeAxisBuildTicksCallbackHandler buildTicksCallbackHandler;

	private final Time time;

	private final Adapters adapters;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianTimeAxis(IsChart chart) {
		// uses X as axis id
		this(chart, CartesianAxisType.X.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianTimeAxis(IsChart chart, String id) {
		this(chart, ScaleIdChecker.key(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianTimeAxis(IsChart chart, Key id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianTimeAxis(IsChart chart, CartesianAxisType cartesianType) {
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
	public CartesianTimeAxis(IsChart chart, String id, CartesianAxisType cartesianType) {
		this(chart, ScaleIdChecker.key(id), cartesianType);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianTimeAxis(IsChart chart, Key id, CartesianAxisType cartesianType) {
		super(chart, id, AxisType.TIME, Key.isValid(cartesianType) ? cartesianType : CartesianAxisType.getByScaleId(id, CartesianAxisType.X));
		// creates the time object
		this.time = new Time(this);
		// creates the ticks instance
		this.ticks = new CartesianTimeTick(this);
		// creates the adapters object
		this.adapters = new Adapters(this);
		// create build ticks callback handler
		this.buildTicksCallbackHandler = new TimeAxisBuildTicksCallbackHandler(this);
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
	 * Sets property controls the data distribution along the scale.
	 * 
	 * @param distribution property controls the data distribution along the scale.
	 */
	public void setDistribution(ScaleDistribution distribution) {
		getScale().setDistribution(distribution);
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 */
	public ScaleDistribution getDistribution() {
		return getScale().getDistribution();
	}

	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public void setBounds(ScaleBounds bounds) {
		getScale().setBounds(bounds);
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public ScaleBounds getBounds() {
		return getScale().getBounds();
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

	/**
	 * Returns the user callback that runs before/after ticks are created.
	 * 
	 * @return the axisBuildTicksCallback
	 */
	public TimeAxisBuildTicksCallback getAxisBuildTicksCallback() {
		return buildTicksCallbackHandler.getCallback();
	}

	/**
	 * Sets the user callback that runs before/after ticks are created.
	 * 
	 * @param axisBuildTicksCallback the axisBuildTicksCallback to set
	 */
	public void setAxisBuildTicksCallback(TimeAxisBuildTicksCallback axisBuildTicksCallback) {
		buildTicksCallbackHandler.setCallback(axisBuildTicksCallback);
	}

}