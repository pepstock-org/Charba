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

import org.pepstock.charba.client.AbstractAdapter;

import com.google.gwt.resources.client.ResourcePrototype;

/**
 * Utility to set which kind of resources type must be use to load text resources.<br>
 * This utility MUST be called as first statement before using Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ResourcesType {
	
	/**
	 * Path into the project where the java script resources are stored.
	 */
	public static final String JAVASCRIPT_RESOURCES_PATH = "org/pepstock/charba/client/resources/js/";
	// static instance of resources to be loaded
	private static Resources<ResourcePrototype> resources = null;
	// static instance of date adapter
	private static AbstractAdapter dateAdapter = null;

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
	 * @param <T> type of resources to be loaded.
	 */
	public static <T extends ResourcePrototype> void setClientBundle(Resources<T> resources) {
		setClientBundle(resources, null);
	}

	/**
	 * Sets the resources type to use to inject java script code.<br>
	 * If the resources type was already set or if is <code>null</code> an exception will be throw.
	 * 
	 * @param resources the resources type to use to inject java script code
	 * @param dateAdapter the date adapter of CHART.JS
	 * @param <T> type of resources to be loaded.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ResourcePrototype> void setClientBundle(Resources<T> resources, AbstractAdapter dateAdapter) {
		// checks if argument is null
		if (resources == null) {
			// exception
			throw new IllegalArgumentException("Resources type argument is null");
		}
		// checks if the resources type is already set and is different from the argument
		if (ResourcesType.resources != null && !resources.getClass().equals(ResourcesType.resources.getClass())) {
			// exception
			throw new IllegalArgumentException("Resources type is already set and can not be changed");
		}
		// stores the instance
		ResourcesType.resources = (Resources<ResourcePrototype>) resources;
		// checks if the date adapter is not null
		// checks if the date adapter is already set and is different from the argument
		if (dateAdapter != null && ResourcesType.dateAdapter != null && !dateAdapter.getClass().equals(ResourcesType.dateAdapter.getClass())) {
			// exception
			throw new IllegalArgumentException("Date adapter is already set and can not be changed");
		}
		// stores the date adapter
		ResourcesType.dateAdapter = dateAdapter;
	}

	/**
	 * Returns the resources type to use to inject java script code.<br>
	 * If the resources type was not already set, an exception will be throw.
	 * 
	 * @return the resources type to use to inject java script code
	 */
	public static Resources<ResourcePrototype> getClientBundle() {
		// checks if a type was already stored
		if (ResourcesType.resources == null) {
			// if not, exception
			throw new IllegalArgumentException("Resources type is invalid (not set). Must be set before using CHARBA");
		}
		// returns the instance
		return ResourcesType.resources;
	}
	
	/**
	 * FIXME
	 * @return
	 */
	public static AbstractAdapter getDateAdapter() {
		return ResourcesType.dateAdapter;
	}

}
