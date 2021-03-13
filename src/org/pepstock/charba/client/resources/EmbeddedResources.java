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
import org.pepstock.charba.client.adapters.DateAdapterModule;

/**
 * Java script resources container to reference LUXON as date time library (synchronous mode).<br>
 * It has been invoked in order to have an java script injection, needed to CHARBA, where CHART.JS and date library will be load in embedded mode.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EmbeddedResources extends AbstractResources implements IsResourceType {

	/**
	 * Static reference to LUXON resources which is injecting the LUXON library.
	 */
	public static final EmbeddedResources INSTANCE = new EmbeddedResources(true);
	/**
	 * Static reference to LUXON resources which is NOT injecting the LUXON library, because LUXON date library could be used in other libraries and to avoid to inject the library
	 * more than once.
	 */
	public static final EmbeddedResources INSTANCE_WITHOUT_DATE_LIBRARY = new EmbeddedResources(false);
	// chart js source code
	private static final ChartJsResource CHARTJS = new ChartJsResource();
	// date adapter java script wrapper
	private final LuxonAdapterResource dateAdapter = new LuxonAdapterResource();
	// date library java script wrapper
	private final LuxonLibraryResource dateLibrary = new LuxonLibraryResource();

	/**
	 * Creates a resource object by a flag.<br>
	 * Of passed argument is <code>true</code>, it must inject the LUXON date library.
	 * 
	 * @param injectDateLibrary <code>true</code> if it must inject the LUXON date library
	 */
	private EmbeddedResources(boolean injectDateLibrary) {
		super(injectDateLibrary);
	}

	/**
	 * Injects CHART.JS, date adapter and library if not already injected.
	 */
	@Override
	public void inject() {
		// checks if resource and module have been already injected
		if (!ResourcesType.isInjected() && !DateAdapterModule.get().isInjected()) {
			// inject Chart.js if not already loaded
			ensureInjected(CHARTJS);
			// checks if the library must be loaded
			if (mustInjectDateLibrary()) {
				// to be sure that date time library has been injected
				ensureInjected(checkAndGetDateTimeResourceName(dateLibrary, ResourceName.DATE_TIME_LIBRARY));
			} else {
				// to be sure that date time library has been injected
				ensureInjected(checkAndGetDateTimeResourceName(new EmptyResource(ResourceName.DATE_TIME_LIBRARY), ResourceName.DATE_TIME_LIBRARY));
			}
			// to be sure that date time chart.js adapter has been injected
			ensureInjected(checkAndGetDateTimeResourceName(dateAdapter, ResourceName.DATE_TIME_ADAPTER));
			// sets flags it has loaded
			ResourcesType.setInjected(true);
			// notify to module that has been injected
			DateAdapterModule.get().injectionComplete(DateAdapterInjectionComplete.get());
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
	 * @param resourceName the resource name which must be applied in the reosurce instance
	 * @return injectable resource instance passed as argument
	 */
	private AbstractInjectableResource checkAndGetDateTimeResourceName(AbstractInjectableResource resource, ResourceName resourceName) {
		// checks if the date time resource has got the right name
		if (!resourceName.value().equalsIgnoreCase(resource.getName())) {
			// is trying to inject a resource with a wrong name
			throw new IllegalArgumentException("Unbale to inject resource because must be '" + resourceName.value() + "' instead of '" + resource.getName() + "'");
		}
		// if here the resource is correct
		// then returns the resource passed as argument
		return resource;
	}

}