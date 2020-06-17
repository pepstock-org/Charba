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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Can be set to 'x', 'y' to define which directions are used in axis.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum CartesianAxisType implements Key
{
	/**
	 * X directions are used in calculating axis.
	 */
	X("x", DefaultScaleId.X),
	/**
	 * Y directions are used in calculating axis.
	 */
	Y("y", DefaultScaleId.Y),
	/**
	 * This is the axis type used by radial linear axis even if it is not a cartesian axis.
	 */
	R("r", DefaultScaleId.R);

	// name value of property
	private final String value;
	// default scale id related to axis type
	private final DefaultScaleId defaultScaleId;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param defaultScaleId default scale id related to axis type
	 */
	private CartesianAxisType(String value, DefaultScaleId defaultScaleId) {
		this.value = value;
		this.defaultScaleId = defaultScaleId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns the default scale id related to axis type.
	 * 
	 * @return the default scale id related to axis type
	 */
	public DefaultScaleId getDefaultScaleId() {
		return defaultScaleId;
	}

	/**
	 * Returns the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'.
	 * 
	 * @param scaleId scale id
	 * @return the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'
	 */
	public static CartesianAxisType getByScaleId(Key scaleId) {
		return getByScaleId(scaleId, DefaultsBuilder.get().getScaledOptions().getScale().getAxis());
	}

	/**
	 * Returns the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'.
	 * 
	 * @param scaleId scale id
	 * @param defaultValue default value for cartesian type when it can not be recognized by scale id
	 * @return the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'
	 */
	public static CartesianAxisType getByScaleId(Key scaleId, CartesianAxisType defaultValue) {
		return getByScaleId(Key.checkAndGetIfValid(scaleId).value(), defaultValue);
	}

	/**
	 * Returns the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'.
	 * 
	 * @param scaleId scale id
	 * @return the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'
	 */
	public static CartesianAxisType getByScaleId(String scaleId) {
		return getByScaleId(scaleId, DefaultsBuilder.get().getScaledOptions().getScale().getAxis());
	}

	/**
	 * Returns the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'.
	 * 
	 * @param scaleId scale id
	 * @param defaultValue default value for cartesian type when it can not be recognized by scale id
	 * @return the cartesian type inferring from the first character of the scale id which should be 'x' or 'y'
	 */
	public static CartesianAxisType getByScaleId(String scaleId, CartesianAxisType defaultValue) {
		// checks if argument is consistent
		if (scaleId != null) {
			// scans types
			for (CartesianAxisType type : values()) {
				// checks if scale id starts with the default
				if (type.getDefaultScaleId().is(scaleId)) {
					// matches with default scale id
					return type;
				}
			}
		}
		// checks if default value is consistent
		if (Key.isValid(defaultValue)) {
			return defaultValue;
		}
		// if here returns the default from global scale
		return DefaultsBuilder.get().getScaledOptions().getScale().getAxis();
	}

	/**
	 * Returns <code>true</code> if the axis has been defined with that cartesian type.
	 * 
	 * @param axis axis instance to check
	 * @param cartesianType cartesian type instance to use for checking
	 * @return <code>true</code> if the axis has been defined with that cartesian type
	 */
	public static boolean hasAxisType(Axis axis, CartesianAxisType cartesianType) {
		if (axis != null && Key.isValid(cartesianType)) {
			// checks if x axis
			return cartesianType.equals(axis.getAxis());
		}
		// if here the axis is not consistent
		return false;
	}

}