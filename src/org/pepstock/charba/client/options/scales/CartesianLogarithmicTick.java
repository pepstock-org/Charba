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

import org.pepstock.charba.client.commons.Key;

/**
 * The logarithmic scale is use to chart numerical data. It can be placed on
 * either the x or y axis.<br>
 * As the name suggests, logarithmic interpolation is used to determine where a
 * value lies on the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CartesianLogarithmicTick extends CartesianTick {

	private static final double DEFAULT_MIN = Double.MIN_VALUE;

	private static final double DEFAULT_MAX = Double.MAX_VALUE;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		min,
		max
	}

	/**
	 * Empty constructor to reduce visibility
	 */
	CartesianLogarithmicTick() {
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum
	 * value from data.
	 * 
	 * @param min
	 *            the user defined minimum number for the scale, overrides
	 *            minimum value from data.
	 */
	public void setMin(int min) {
		setValue(Property.min, min);
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum
	 * value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum
	 *         value from data. Default is Double.MIN_VALUE.
	 */
	public double getMin() {
		return getValue(Property.min, DEFAULT_MIN);
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum
	 * value from data.
	 * 
	 * @param max
	 *            user defined maximum number for the scale, overrides maximum
	 *            value from data.
	 */
	public void setMax(double max) {
		setValue(Property.max, max);
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum
	 * value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum
	 *         value from data. Default is Double.MAX_VALUE.
	 */
	public double getMax() {
		return getValue(Property.max, DEFAULT_MAX);
	}

}