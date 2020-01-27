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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Singleton object to use the helpers utility of CHART.JS.<br>
 * It maps the java script object <code>chart.helpers</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Helpers {

	// singleton instance
	private static final Helpers INSTANCE = new Helpers();
	// native object of CHART.JS which represents the helpers
	private final NativeHelpers nativeObject;

	/**
	 * To avoid any instantiation
	 */
	private Helpers() {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// gets native object from CHART.JS
		this.nativeObject = Chart.getHelpers();
	}

	/**
	 * Singleton method to get the instance
	 * 
	 * @return helper instance
	 */
	public static Helpers get() {
		return INSTANCE;
	}

	/**
	 * Recursively deep copies source properties into target only if not defined in target.<br>
	 * IMPORTANT: target is not cloned and will be updated with source properties.
	 * 
	 * @param target the target object in which all sources are merged into.
	 * @param source object to merge into target.
	 * @return the target object. If target and source are nulls, an empty native object is returned is returned.
	 */
	public NativeObject mergeIf(NativeObject target, NativeObject source) {
		// checks target is not null
		if (target != null) {
			// if source is not null
			if (source != null) {
				// merges the objects
				return nativeObject.mergeIf(target, source);
			} else {
				// otherwise returns the target
				return target;
			}
		} else if (source != null) {
			// if here, target is null and source not
			// therefore returns a clone of source
			return clone(source);
		}
		// arguments are either not consistent
		// returns an empty object
		return null;
	}

	/**
	 * Returns a deep copy of source without keeping references on objects and arrays.
	 * 
	 * @param source the object to clone.
	 * @return a clone of source object
	 */
	public NativeObject clone(NativeObject source) {
		// checks if argument is consistent
		if (source != null) {
			return nativeObject.clone(source);
		}
		// if here source is not consistent
		return null;
	}

}
