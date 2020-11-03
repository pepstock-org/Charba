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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * Options used internally inside the chart configuration.<br>
 * This is the SCALES element of configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ExtendedScales extends Scales {

	/**
	 * Creates the scales object as child of the option object.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ExtendedScales(Options options, Key childKey, IsDefaultScales defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets all axes of chart.
	 * 
	 * @param scales array of axes.
	 */
	public void setAxes(Scale... scales) {
		// clears the object from previous scales
		if (!empty()) {
			// removes all keys
			for (Key key : keys()) {
				// checks if the property is related to an object
				// otherwise is not a scale
				if (isType(key, ObjectType.OBJECT)) {
					// remove key
					remove(key);
				}
			}
		}
		// sets axes internally
		// to reduce complexity of this method
		setInternalAxes(scales);
	}
	
	/**
	 * Sets all axes of chart.<br>
	 * Implemented to reduce the complexity of <b>setAxes</b> public method.
	 * 
	 * @param scales array of axes.
	 */
	private void setInternalAxes(Scale... scales) {
		// checks if the arguments are consistent
		if (scales != null && scales.length > 0) {
			// sets a index
			int index = 0;
			// checks if is
			// scans passed scales
			for (Scale scale : scales) {
				// checks if not null
				if (scale != null) {
					// get the axis type and default id
					AxisType type = scale.getType();
					// checks if type is consistent
					// it MUST
					Key.checkIfValid(type);
					// gets id as key
					IsScaleId id = checkAndGetScaleId(scale);
					// checks for radial
					// must be the first one
					// and must have only 1 scale being a radial
					if (AxisType.RADIAL_LINEAR.equals(type) && (index > 0 || scales.length != 1)) {
						throw new IllegalArgumentException("A radial linear scale can not be added to a scales with other scales");
					}
					// stores scale
					setValue(id, scale);
					// increments only if added
					index++;
				}
			}
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * Checks and returns a scale id, retrieved from a scale instance.
	 * 
	 * @param scale scale instance to use for checking and getting
	 * @return a scale id, retrieved from a scale instance
	 */
	private IsScaleId checkAndGetScaleId(Scale scale) {
		// gets id as key
		IsScaleId id = scale.getId();
		// checks if scale id of scale is consistent
		// used for cartesian, it must not be set to unknown
		if (DefaultScaleId.UNKNOWN.is(id)) {
			// stores default id as id
			id = scale.getType().getDefaultScaleId();
			// override the id
			scale.setId(id);
		}
		// checks if the id is already into object
		// that means there are 2 or more scales to add
		// with the same id
		if (has(id)) {
			throw new IllegalArgumentException("A scale with id " + id.value() + " has been already added");
		}
		return id;
	}

}