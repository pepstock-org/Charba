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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.jsinterop.AbstractChart;

/**
 * This object is used to map defined axis as time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CartesianTimeAxis extends CartesianAxis<CartesianTimeTick> {
	
	private final CartesianTimeTick ticks;
	
	private final Time time;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @see CartesianAxisType#x
	 */
	public CartesianTimeAxis(AbstractChart<?, ?> chart) {
		this(chart, CartesianAxisType.x);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param cartesianType cartesian axis type.
	 * @see CartesianAxisType
	 */
	public CartesianTimeAxis(AbstractChart<?, ?> chart, CartesianAxisType cartesianType) {
		super(chart, cartesianType);
		// sets axis type
		super.setType(AxisType.time);
		time = new Time(this);
		this.ticks = new CartesianTimeTick(this); 
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianTimeTick getTicks() {
		return ticks;
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Sets property controls the data distribution along the scale.
	 * 
	 * @param distribution property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public void setDistribution(ScaleDistribution distribution) {
		getScale().setDistribution(distribution);
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public ScaleDistribution getDistribution() {
		return getScale().getDistribution();
	}
	
	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public void setBounds(ScaleBounds bounds) {
		getScale().setBounds(bounds);
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public ScaleBounds getBounds() {
		return getScale().getBounds();
	}
}