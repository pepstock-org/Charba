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

import org.pepstock.charba.client.commons.PropertyKey;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * Represents the scale id of a scale/axis object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface ScaleId extends PropertyKey {

	/**
	 * Returns a key instance by its string value.
	 * 
	 * @param id string value to use
	 * @return new scale id instance
	 */
	static ScaleId create(String id) {
		// checks if id as argument is a default one
		for (DefaultScaleId defScaleId : DefaultScaleId.values()) {
			// checks if id is equals to default
			if (defScaleId.value().equals(id)) {
				// if equals, returns the default id
				return defScaleId;
			}
		}
		// if here, is not a default one
		// then creates new scale id
		return new StandardScaleId(id);
	}

	/**
	 * Returns <code>true</code> if scale id passed as argument is not <code>null</code> and its value could be a valid scale id.
	 * 
	 * @param id scale id to be checked
	 * @return <code>true</code> if scale id passed as argument is not <code>null</code> and its value could be a valid scale id.
	 */
	static boolean isValid(String id) {
		return PropertyKey.isValid(id);
	}

	/**
	 * Returns <code>true</code> if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.
	 * 
	 * @param id scale id to be checked
	 * @return <code>true</code> if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.
	 */
	static boolean isValid(ScaleId id) {
		return PropertyKey.isValid(id);
	}

	/**
	 * Checks if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id scale id to be checked
	 */
	static void checkIfValid(ScaleId id) {
		PropertyKey.checkIfValid(id);
	}

	/**
	 * Checks if scale id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid scale id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id scale id as string to be checked
	 */
	static void checkIfValid(String id) {
		PropertyKey.checkIfValid(id);
	}

	/**
	 * Returns the ID of the scale checking the default ones, {@link DefaultScaleId}.
	 * 
	 * @param id scale id value stored in the a object
	 * @param defaultValue the default value if not exist
	 * @return the ID of the scale
	 */
	static ScaleId checkAndGetScaleID(String id, ScaleId defaultValue) {
		// checks if default value is consistent
		if (ScaleId.isValid(id)) {
			// gets and checks if a default scale
			// has been stored
			// scans all defaults to check with the argument
			for (DefaultScaleId scaleId : DefaultScaleId.values()) {
				// checks if default scale id matches with argument
				if (scaleId.value().equals(id)) {
					return scaleId;
				}
			}
			// creates new scale id with id stored in the object
			return ScaleId.create(id);
		}
		// if here not, default value is not consistent
		// then returns the default
		return defaultValue;
	}

	/**
	 * Returns the default axis kind for this default scale id.
	 * 
	 * @return the default axis kind for this default scale id
	 */
	AxisKind getAxisKind();
}