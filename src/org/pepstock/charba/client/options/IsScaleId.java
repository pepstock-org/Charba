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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * Represents the scale id of a scale/axis object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsScaleId extends Key {

	/**
	 * Returns a key instance by its string value.
	 * 
	 * @param id string value to use
	 * @return new scale id instance
	 */
	static IsScaleId create(String id) {
		return new StandardScaleId(id);
	}

	/**
	 * Returns <code>true</code> if scale id passed as argument is not <code>null</code> and its value could be a valid scale id.
	 * 
	 * @param id scale id to be checked
	 * @return <code>true</code> if scale id passed as argument is not <code>null</code> and its value could be a valid scale id.
	 */
	static boolean isValid(String id) {
		return ScaleIdChecker.isValid(id);
	}

	/**
	 * Returns <code>true</code> if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.
	 * 
	 * @param id scale id to be checked
	 * @return <code>true</code> if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.
	 */
	static boolean isValid(IsScaleId id) {
		return Key.isValid(id) && isValid(id.value());
	}

	/**
	 * Checks if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id scale id to be checked
	 */
	static void checkIfValid(IsScaleId id) {
		ScaleIdChecker.check(id);
	}

	/**
	 * Checks if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id scale id as string to be checked
	 */
	static void checkIfValid(String id) {
		ScaleIdChecker.check(id);
	}

	/**
	 * Returns the ID of the scale checking the default ones, {@link DefaultScaleId}.
	 * 
	 * @param id scale id value stored into a object
	 * @param defaultValue the default value if not exist
	 * @return the ID of the scale
	 */
	static IsScaleId checkAndGetScaleID(String id, IsScaleId defaultValue) {
		// checks if default value is consistent
		if (IsScaleId.isValid(id)) {
			// gets and checks if a default scale
			// has been stored
			// scans all defaults to check with the argument
			for (DefaultScaleId scaleId : DefaultScaleId.values()) {
				// checks if default scale id matches with argument
				if (scaleId.value().equals(id)) {
					return scaleId;
				}
			}
			// creates new scale id with id stored into the object
			return IsScaleId.create(id);
		}
		// if here not, default value is not consistent
		// then returns the default
		return defaultValue;
	}

}