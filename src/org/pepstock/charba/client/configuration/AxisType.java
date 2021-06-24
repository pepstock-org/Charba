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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.PropertyKey;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Represents the scale type of a scale/axis object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AxisType extends PropertyKey {

	/**
	 * Returns a axis type by its string value.
	 * 
	 * @param type scale type
	 * @param baseType base type (extended) for axis type
	 * @param defaultScaleId default scale id for this axis type
	 * @param dataType type of data which the scale can manage
	 * @return new scale type instance
	 */
	static AxisType create(String type, AxisType baseType, DefaultScaleId defaultScaleId, ScaleDataType dataType) {
		// checks if type is already defined
		AxisType resultType = AxisTypeManager.get().get(type);
		// checks if result is consistent
		if (resultType != null) {
			// found
			return resultType;
		}
		// if here, is not a default one
		// then creates new scale type
		return new StandardAxisType(type, baseType, defaultScaleId, dataType);
	}

	/**
	 * Returns a stored axis type or {@link IllegalArgumentException}.
	 * 
	 * @param type scale type
	 * @return a stored scale type instance
	 */
	static AxisType checkAndGet(String type) {
		// checks if type is already defined
		AxisType resultType = AxisTypeManager.get().get(type);
		// checks if result is consistent
		if (resultType != null) {
			// found
			return resultType;
		}
		// if here the result type is not consistent
		// then exception
		throw new IllegalArgumentException("Axis type '" + type + "'is undefined");
	}

	/**
	 * Returns <code>true</code> if scale type passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale type.
	 * 
	 * @param type scale type to be checked
	 * @return <code>true</code> if scale type passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale type.
	 */
	static boolean isValid(AxisType type) {
		return Key.isValid(type) && type.getDataType() != null && ScaleId.isValid(type.getDefaultScaleId()) && isValid(type.getBaseType());
	}

	/**
	 * Checks if scale type passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale type.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param type scale type to be checked
	 */
	static void checkIfValid(AxisType type) {
		if (!isValid(type)) {
			throw new IllegalArgumentException("Axis type is null or not consistent");
		}
	}

	/**
	 * Returns the base type (extended) for this axis type.
	 * 
	 * @return the base type (extended) for this axis type
	 */
	AxisType getBaseType();

	/**
	 * Returns the default scale id for this axis type.
	 * 
	 * @return the default scale id for this axis type
	 */
	ScaleId getDefaultScaleId();

	/**
	 * Returns the type of managed data for this axis type.
	 * 
	 * @return the type of managed data for this axis type
	 */
	ScaleDataType getDataType();

}