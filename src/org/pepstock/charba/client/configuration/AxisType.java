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
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Represents the scale type of a scale/axis object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AxisType extends PropertyKey {

	/**
	 * Returns a axis type by its string value and extended existing axis type.<br>
	 * It uses the {@link ScaleDataType} and default {@link ScaleId} of extended axis type.
	 * 
	 * @param type scale type
	 * @param baseType base type (extended) for axis type
	 * @return new scale type instance
	 */
	static AxisType create(String type, ChartAxisType baseType) {
		// checks if base type is consistent and then use the data type of the base
		return create(type, baseType, isValid(baseType) ? baseType.getDefaultScaleId() : null);
	}

	/**
	 * Returns a axis type by its string value, extended existing axis type and default {@link ScaleId}.<br>
	 * It uses the {@link ScaleDataType} of extended axis type.
	 * 
	 * @param type scale type
	 * @param baseType base type (extended) for axis type
	 * @param defaultScaleId default scale id for this axis type
	 * @return new scale type instance
	 */
	static AxisType create(String type, ChartAxisType baseType, ScaleId defaultScaleId) {
		// checks if base type is consistent and then use the data type of the base
		return create(type, baseType, defaultScaleId, isValid(baseType) ? baseType.getDataType() : null);
	}

	/**
	 * Returns a axis type by all needed objects to create a axis type.
	 * 
	 * @param type scale type
	 * @param baseType base type (extended) for axis type
	 * @param defaultScaleId default scale id for this axis type
	 * @param dataType type of data which the scale can manage
	 * @return new scale type instance
	 */
	static AxisType create(String type, ChartAxisType baseType, ScaleId defaultScaleId, ScaleDataType dataType) {
		// checks if type is already defined
		AxisType resultType = AxisTypesManager.get().get(type);
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
	 * Registers the passed axis type.
	 * 
	 * @param type axis type
	 */
	static void register(AxisType type) {
		register(type, false);
	}

	/**
	 * Registers the passed axis type, with possibility to force the registration if already registered.
	 * 
	 * @param type axis type
	 * @param force if <code>true</code>, the axis type is register even if already registered.
	 */
	static void register(AxisType type, boolean force) {
		// checked if is register
		if (force || (isValid(type) && !AxisTypesManager.get().has(type.value()))) {
			AxisTypesManager.get().add(type);
		}
	}

	/**
	 * Returns a stored axis type or {@link IllegalArgumentException}.
	 * 
	 * @param type scale type
	 * @return a stored scale type instance
	 */
	static AxisType checkAndGet(String type) {
		// checks if type is already defined
		AxisType resultType = AxisTypesManager.get().get(type);
		// checks if result is consistent
		if (resultType != null) {
			// found
			return resultType;
		}
		// if here the result type is not consistent
		// then exception
		throw new IllegalArgumentException("Axis type '" + type + "' is undefined");
	}

	/**
	 * Returns <code>true</code> if scale type passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale type.
	 * 
	 * @param type scale type to be checked
	 * @return <code>true</code> if scale type passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale type.
	 */
	static boolean isValid(AxisType type) {
		return Key.isValid(type) && type.getDataType() != null && ScaleId.isValid(type.getDefaultScaleId()) && type.getBaseType() != null;
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
	 * Checks if key passed as argument is a valid {@link AxisType}.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the key.
	 * 
	 * @param type axis type to be checked
	 * @param <T> type of axis
	 * @return the same axis type passed as argument
	 */
	static <T extends AxisType> T checkAndGetIfValid(T type) {
		// checks if key is consistent
		checkIfValid(type);
		// if here, is consistent
		// then returns the argument
		return type;
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