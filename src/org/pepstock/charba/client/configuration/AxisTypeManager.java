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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.enums.ChartAxisType;

/**
 * FIXME
 * @author Andrea "Stock" Stocchero
 *
 */
final class AxisTypeManager {
	
	// singleton instance
	private static final AxisTypeManager INSTANCE = new AxisTypeManager();

	// caches with the axis type instances
	private final Map<String, AxisType> types = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	AxisTypeManager() {
		// do nothing
	}
	
	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	static AxisTypeManager get() {
		return INSTANCE;
	}

	/**
	 * 
	 * @param type
	 */
	void add(AxisType type) {
		// checks if type is consistent
		if (AxisType.isValid(type)) {
			// stores type
			types.put(type.value(), type);
		}
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	boolean has(String type) {
		// checks if argument is consistent
		if (type != null) {
			return types.containsKey(type);
		}
		// if here the argument is not consistent
		// then returns false
		return false;
	}
	
	AxisType get(String type) {
		// checks and gets the ootb chart axis type
		// checks if axis type as argument is a ootb chart one
		for (ChartAxisType defaultScaleType : ChartAxisType.values()) {
			// checks if type is equals to ootb chart one
			if (defaultScaleType.value().equals(type)) {
				// if equals, returns the ootb chart one
				return defaultScaleType;
			}
		}
		// checks is type is stored
		if (has(type)) {
			return types.get(type);
		}
		// if here, types does not exist
		// then returns null
		return null;
	}
	
}
