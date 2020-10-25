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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.commons.JsHelper;

/**
 * Utility to set which kind of resources type must be use to load injectable resources.<br>
 * This utility MUST be called as first statement before using Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ResourcesType {

	// static instance of resources to be loaded
	private static ResourcesContainer resources = null;

	/**
	 * To avoid any instantiation
	 */
	private ResourcesType() {
		// do nothing
	}

	/**
	 * Sets the resources type to use to inject java script code.<br>
	 * If the resources type was already set or if is <code>null</code> an exception will be throw.
	 * 
	 * @param resources the resources type to use to inject java script code
	 */
	public static void setClientBundle(ResourcesContainer resources) {
		// checks if argument is null
		if (resources == null) {
			// exception
			throw new IllegalArgumentException("Resources type argument is null");
		}
		// checks if is extending a correct abstract resource
		if (resources instanceof IsResourceType) {
			// checks if the resources type is already set and is different from the argument
			if (ResourcesType.resources != null && !resources.getClass().equals(ResourcesType.resources.getClass())) {
				// exception
				throw new IllegalArgumentException("Resources type is already set and can not be changed");
			}
			// stores the instance
			ResourcesType.resources = resources;
			// to be sure that CHARBA java script object is injected
			// invoking the JsHelper
			// PAY ATTENTION: MUST be called before injecting CHART.JS
			JsHelper.get();
		} else {
			// exception
			throw new IllegalArgumentException("Resources type is not correct. Must be an EmbeddedResources or DeferredResources instance");
		}
	}

	/**
	 * Returns the resources type to use to inject java script code.<br>
	 * If the resources type was not already set, an exception will be throw.
	 * 
	 * @return the resources type to use to inject java script code
	 */
	public static ResourcesContainer getClientBundle() {
		// checks if a type was already stored
		if (ResourcesType.resources == null) {
			// if not, exception
			throw new IllegalArgumentException("Resources type is invalid (not set). Must be set before using CHARBA");
		}
		// returns the instance
		return ResourcesType.resources;
	}

}
