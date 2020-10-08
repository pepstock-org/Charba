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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;

/**
 * Base class to extend in order to have a resource client bundle, needed to CHARBA, where CHART.JS and date library must be load in deferred mode.<br>
 * Every instance must have a module related to date adapter and library.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractDeferredResources extends AbstractResources implements IsResourceType {

	/**
	 * Path into the project where the java script resources are stored, <b>{@value}</b>.
	 */
	static final String JAVASCRIPT_RESOURCES_PATH = "org/pepstock/charba/client/resources/js/";

	/**
	 * Client bundle to reference CHART.JS, always needed to CHARBA.<br>
	 * This resources type will load the CHART.JS module in asynchronous mode in order to optimize the performance when GWT code splitting is implemented.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface DeferredChartResource extends ClientBundle {

		/**
		 * Static reference to resources java script source code.
		 */
		static final DeferredChartResource INSTANCE = GWT.create(DeferredChartResource.class);

		/**
		 * Contains text representation of native chart.js code.
		 * 
		 * @return chart.js code in asynchronous mode
		 */
		@Source(AbstractDeferredResources.JAVASCRIPT_RESOURCES_PATH + "chart.min.js")
		ExternalTextResource chartJs();

	}

	/**
	 * Creates a deferred resource object by passed module, which represents the date adapter and library, as argument.
	 * 
	 * @param module module of date adapter and library.
	 */
	protected AbstractDeferredResources(AbstractModule module) {
		super(module);
	}

	/**
	 * Returns the date adapter client bundle with java script definition.
	 * 
	 * @return the date adapter client bundle with java script definition
	 */
	protected abstract DeferredDateAdapterResources getDeferredAdapterResources();

	/**
	 * Returns the CHART.JS client bundle with java script definition.
	 * 
	 * @return the date adapter client bundle with java script definition
	 */
	final DeferredChartResource getDeferredChartResource() {
		return DeferredChartResource.INSTANCE;
	}

	/**
	 * Checks if the module has been injected by {@link EntryPointStarter}. if not, throw a {@link UnsupportedOperationException}.
	 */
	@Override
	public final void inject() {
		// checks if module is already injected
		if (!getModule().isInjected()) {
			throw new UnsupportedOperationException("Date adapter module is not injected. Please use EntryPointStarter to inject the resources");
		}
	}

	/**
	 * Is invoked when the all resources have been injected.<br>
	 * Notify the module that the injection has been completed.
	 */
	final void injected() {
		// checks if module is already injected
		if (!getModule().isInjected()) {
			// notify to module that has been injected
			getModule().injectionComplete(DateAdapterInjectionComplete.get());
		}
	}

}
