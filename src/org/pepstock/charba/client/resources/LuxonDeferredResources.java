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

import org.pepstock.charba.client.adapters.LuxonModule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference LUXON as date time library (asynchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LuxonDeferredResources extends AbstractDeferredResources {

	/**
	 * Client bundle to reference LUXON as date time library.<br>
	 * It defines the LUXON date time library and its CHART.JS adapter (always loaded in synchronous mode).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface LuxonDeferredResourcesClientBundle extends DeferredDateAdapterResources {

		/**
		 * Static reference to resources java script source code.
		 */
		static final LuxonDeferredResourcesClientBundle INSTANCE = GWT.create(LuxonDeferredResourcesClientBundle.class);

		/**
		 * Contains text representation of date-time LUXON java script library code.
		 * 
		 * @return date-time LUXON java script library code in asynchronous mode
		 */
		@Override
		@Source(AbstractDeferredResources.JAVASCRIPT_RESOURCES_PATH + "luxon.min.js")
		ExternalTextResource datetimeLibrary();

		/**
		 * Contains text representation of CHART.JS adapter code form LUXON.
		 * 
		 * @return chart.js date adapter code for LUXON in synchronous mode
		 */
		@Override
		@Source(AbstractDeferredResources.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-luxon.min.js")
		TextResource datetimeAdapter();

	}

	/**
	 * Static reference to LUXON resources.
	 */
	public static final LuxonDeferredResources INSTANCE = new LuxonDeferredResources();

	/**
	 * To avoid any instantiation
	 */
	LuxonDeferredResources() {
		super(LuxonModule.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.AbstractDeferredResources#getDeferredAdapterResources()
	 */
	@Override
	protected final DeferredDateAdapterResources getDeferredAdapterResources() {
		return LuxonDeferredResourcesClientBundle.INSTANCE;
	}

}