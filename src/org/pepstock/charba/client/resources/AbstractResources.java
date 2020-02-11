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

/**
 * Base class to extend in order to have a resource client bundle, needed to CHARBA, where CHART.JS and date library.<br>
 * Every instance must have a module related to date adapter and library.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractResources {

	// date adapter library module instance
	private final AbstractModule module;

	/**
	 * Creates a resource object by passed module, which represents the date adapter and library, as argument.
	 * 
	 * @param module module of date adapter and library.
	 */
	protected AbstractResources(AbstractModule module) {
		// checks if module is consistent
		if (module == null) {
			// if not, exception
			throw new IllegalArgumentException("Module is null");
		}
		// stores module
		this.module = module;
	}

	/**
	 * Returns the date adapter module.
	 * 
	 * @return the date adapter module
	 */
	public final AbstractModule getModule() {
		return module;
	}

	/**
	 * Injects CHART.JS, date adapter and library if not already injected.
	 */
	public abstract void inject();

}
