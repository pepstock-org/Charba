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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;

/**
 * This object is used to map defined axis as time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CartesianTimeAxis extends CartesianAxis<CartesianTimeTick> {
	
	private final Time time;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		distribution,
		bounds,
		time
	}
	
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
		super(chart, new CartesianTimeTick(chart), cartesianType);
		// sets axis type
		super.setType(AxisType.time);
		time = new Time(chart);
		// stores into java script object
		setValue(Property.time, time);
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
		setValue(Property.distribution, distribution);
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public ScaleDistribution getDistribution() {
		return getValue(Property.distribution, ScaleDistribution.class, ScaleDistribution.linear);
	}
	
	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public void setBounds(ScaleBounds bounds) {
		setValue(Property.bounds, bounds);
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public ScaleBounds getBounds() {
		return getValue(Property.bounds, ScaleBounds.class, ScaleBounds.data);
	}
}