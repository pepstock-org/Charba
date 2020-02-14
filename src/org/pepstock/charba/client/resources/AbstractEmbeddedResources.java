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

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.adapters.AbstractModule;

/**
 * Base class to extend in order to have an java script injection, needed to CHARBA, where CHART.JS and date library will be
 * load in embedded mode.<br>
 * Every instance must have a module related to date adapter and library.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEmbeddedResources extends AbstractResources implements IsResourceType{

	// chart js source code
	private static final ChartJsResource CHARTJS = new ChartJsResource();

	/**
	 * Creates an embedded resource object by passed module, which represents the date adapter and library, as argument.
	 * 
	 * @param module module of date adapter and library.
	 */
	AbstractEmbeddedResources(AbstractModule module) {
		super(module);
	}
	
	/**
	 * Contains text representation of date-time java script library code.
	 * 
	 * @return date-time java script library code
	 */
	protected abstract AbstractInjectableResource datetimeLibrary();

	/**
	 * Contains text representation of CHART.JS adapter code.<br>
	 * There is a specific adapter for the different date-time libraries.
	 * 
	 * @return chart.js date adapter code
	 */
	protected abstract AbstractInjectableResource datetimeAdapter();

	/**
	 * Injects CHART.JS, date adapter and library if not already injected.
	 */
	@Override
	public final void inject() {
		// checks if module has been already injected
		if (!getModule().isInjected()) {
			// inject Chart.js if not already loaded
			ensureInjected(CHARTJS);
			// to be sure that date time library has been injected
			ensureInjected(checkAndGetDateTimeResourceName(datetimeLibrary(), ResourceName.DATE_TIME_LIBRARY));
			// to be sure that date time chart.js adapter has been injected
			ensureInjected(checkAndGetDateTimeResourceName(datetimeAdapter(), ResourceName.DATE_TIME_ADAPTER));
			// notify to module that has been injected
			getModule().injectionComplete(DateAdapterInjectionComplete.get());
		}
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	private void ensureInjected(AbstractInjectableResource resource) {
		// checks if prototype is a text resource and not already injected
		if (!Injector.isInjected(resource)) {
			// inject Chart.js if not already loaded
			Injector.ensureInjected(resource);
		}
	}
	
	/**
	 * Checks and get the resource, passed as argument, with the resource name.<br>
	 * When you are injecting date library and adapters, it is mandatory they have the correct name, fixed by CHARBA constraints.<br>
	 * If the resource does not have the right name, throws an {@link IllegalArgumentException}.
	 *  
	 * @param resource injectable resource instance to check
	 * @param resourceName the resource name which must be applied into reosurce instance 
	 * @return injectable resource instance passed as argument
	 */
	private AbstractInjectableResource checkAndGetDateTimeResourceName(AbstractInjectableResource resource, ResourceName resourceName) {
		// checks if the date time resource has got the right name
		if (!resourceName.value().equalsIgnoreCase(resource.getName())) {
			// is trying to inject a resource with a wrong name
			throw new IllegalArgumentException("Unbale to inject resource because must be '"+resourceName.value()+"' instead of '"+resource.getName()+"'");
		}
		// if here the resource is correct
		// then returns the resource passed as argument
		return resource;
	}
}
