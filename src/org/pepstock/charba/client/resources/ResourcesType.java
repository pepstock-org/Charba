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

import org.pepstock.charba.client.adapters.AbstractModule;

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
	 * Path into the project where the java script resources are stored, <b>{@value}</b>.
	 */
	public static final String JAVASCRIPT_RESOURCES_PATH = "org/pepstock/charba/client/resources/js/";
	/**
	 * Path into the project where the images resources are stored, <b>{@value}</b>.
	 */
	public static final String IMAGES_RESOURCES_PATH = "org/pepstock/charba/client/resources/images/";
	// static instance of resources to be loaded
	private static AbstractResources<ResourcePrototype> resources = null;

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
	@SuppressWarnings("unchecked")
	public static <T extends ResourcePrototype> void setClientBundle(AbstractResources<T> resources) {
		// checks if argument is null
		if (resources == null) {
			// exception
			throw new IllegalArgumentException("Resources type argument is null");
		}
		// checks if is extending a correct abstract resource
		if (resources instanceof AbstractEmbeddedResources || resources instanceof AbstractDeferredResources) {
			// checks if the resources type is already set and is different from the argument
			if (ResourcesType.resources != null && !resources.getClass().equals(ResourcesType.resources.getClass())) {
				// exception
				throw new IllegalArgumentException("Resources type is already set and can not be changed");
			}
			// checks if the resources type is already set and is different from the argument
			if (ResourcesType.resources != null && !ResourcesType.resources.getModule().equals(resources.getModule())) {
				// exception
				throw new IllegalArgumentException("Resources type is already set the module '"+ResourcesType.resources.getModule().getId()+"' and can not be changed");
			}
			// stores the instance
			ResourcesType.resources = (AbstractResources<ResourcePrototype>) resources;
		} else {
			// exception
			throw new IllegalArgumentException("Resources type is not correct. Must extend AbstractEmbeddedResources or AbstractDeferredResources classes");
		}
	}

	/**
	 * Returns the resources type to use to inject java script code.<br>
	 * If the resources type was not already set, an exception will be throw.
	 * 
	 * @return the resources type to use to inject java script code
	 */
	public static AbstractResources<ResourcePrototype> getClientBundle() {
		// checks if a type was already stored
		if (ResourcesType.resources == null) {
			// if not, exception
			throw new IllegalArgumentException("Resources type is invalid (not set). Must be set before using CHARBA");
		}
		// returns the instance
		return ResourcesType.resources;
	}
	
	/**
	 * Returns <code>true</code> if the date adapter module is the same of the injected one.
	 * 
	 * @param module the date adapter module to check 
	 * @return <code>true</code> if the module is the same of the injected one
	 */
	public static boolean equalsTo(AbstractModule module) {
		return getClientBundle().getModule().equals(module);
	}
	
	/**
	 * Returns the date adapter module id.
	 * 
	 * @return the date adapter module id
	 */
	public static String getModuleId() {
		return getClientBundle().getModule().getId();
	}
}
