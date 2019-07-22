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
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;

/**
 * This object is used to map defined axis as time. This is used to have time series charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianTimeAxis extends CartesianAxis<CartesianTimeTick> {

	private final CartesianTimeTick ticks;

	private final Time time;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianTimeAxis(IsChart chart) {
		this(chart, CartesianAxisType.X);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianTimeAxis(IsChart chart, CartesianAxisType cartesianType) {
		super(chart, AxisType.TIME, cartesianType);
		// creates the time object
		this.time = new Time(this);
		// creates the ticks instance
		this.ticks = new CartesianTimeTick(this);
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
}