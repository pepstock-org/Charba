/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.adapters.DateAdapterModule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ExternalTextResource;

/**
 * Client bundle to reference LUXON as date time library (asynchronous mode).<br>
 * It has been invoked in order to have a resource client bundle, needed to CHARBA, where CHART.JS and date library must be load in deferred mode.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DeferredResources extends AbstractResources implements IsResourceType {

	/**
	 * Static reference to LUXON resources which is injecting the LUXON library.
	 */
	public static final DeferredResources INSTANCE = new DeferredResources(true);
	/**
	 * Static reference to LUXON resources which is NOT injecting the LUXON library, because LUXON date library could be used in other libraries and to avoid to inject the library
	 * more than once.
	 */
	public static final DeferredResources INSTANCE_WITHOUT_DATE_LIBRARY = new DeferredResources(false);

	/**
	 * Path in the project where the java script resources are stored, <b>{@value}</b>.
	 */
	static final String JAVASCRIPT_RESOURCES_PATH = "org/pepstock/charba/client/resources/js/";

	/**
	 * Client bundle to reference CHART.JS, always needed to CHARBA.<br>
	 * This resources type will load the CHART.JS module in asynchronous mode in order to optimize the performance when GWT code splitting is implemented.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface DeferredResourcesResource extends DeferredResourcesBundle {

		/**
		 * Static reference to resources java script source code.
		 */
		static final DeferredResourcesResource INSTANCE = GWT.create(DeferredResourcesResource.class);

		/**
		 * Contains text representation of date-time LUXON java script library code.
		 * 
		 * @return date-time LUXON java script library code in asynchronous mode
		 */
		@Override
		@Source(DeferredResources.JAVASCRIPT_RESOURCES_PATH + "luxon.min.js")
		ExternalTextResource datetimeLibrary();

	}

	/**
	 * Client bundle to reference CHART.JS, always needed to CHARBA, without loading the LUXON library, because it can be injected from other libraries.<br>
	 * This resources type will load the CHART.JS module in asynchronous mode in order to optimize the performance when GWT code splitting is implemented.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface DeferredResourcesWithoutLibraryResource extends DeferredResourcesBundle {

		/**
		 * Static reference to resources java script source code.
		 */
		static final DeferredResourcesWithoutLibraryResource INSTANCE = GWT.create(DeferredResourcesWithoutLibraryResource.class);

		/**
		 * Contains text representation of date-time LUXON java script library code.
		 * 
		 * @return date-time LUXON java script library code in asynchronous mode
		 */
		@Override
		@Source(DeferredResources.JAVASCRIPT_RESOURCES_PATH + "charba.empty.js")
		ExternalTextResource datetimeLibrary();

	}

	/**
	 * Creates a resource object by a flag.<br>
	 * Of passed argument is <code>true</code>, it must inject the LUXON date library.
	 * 
	 * @param injectDateLibrary <code>true</code> if it must inject the LUXON date library
	 */
	private DeferredResources(boolean injectDateLibrary) {
		super(injectDateLibrary);
	}

	/**
	 * Returns the CHART.JS client bundle with java script definition.
	 * 
	 * @return the date adapter client bundle with java script definition
	 */
	final DeferredResourcesBundle getDeferredResourcesResource() {
		return mustInjectDateLibrary() ? DeferredResourcesResource.INSTANCE : DeferredResourcesWithoutLibraryResource.INSTANCE;
	}

	/**
	 * Checks if the module has been injected by {@link EntryPointStarter}. if not, throw a {@link UnsupportedOperationException}.
	 */
	@Override
	public final void inject() {
		// checks if module is already injected
		if (!DateAdapterModule.get().isInjected()) {
			throw new UnsupportedOperationException("Date adapter module is not injected. Please use EntryPointStarter to inject the resources");
		}
	}

	/**
	 * Is invoked when the all resources have been injected.<br>
	 * Notify the module that the injection has been completed.
	 */
	final void injected() {
		// checks if resource and module have been already injected
		if (!ResourcesType.isInjected() && !DateAdapterModule.get().isInjected()) {
			// sets flags it has loaded
			ResourcesType.setInjected(true);
			// notify to module that has been injected
			DateAdapterModule.get().injectionComplete(DateAdapterInjectionComplete.get());
		}
	}

}