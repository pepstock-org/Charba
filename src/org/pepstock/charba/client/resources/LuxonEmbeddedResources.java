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
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference LUXON as date time library (synchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LuxonEmbeddedResources extends AbstractEmbeddedResources {

	/**
	 * Client bundle to reference LUXON as date time library.<br>
	 * It defines the LUXON date time library and its CHART.JS adapter.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface LuxonEmbeddedResourcesClientBundle extends EmbeddedDateAdapterResources {

		/**
		 * Static reference to resources java script source code.
		 */
		static final LuxonEmbeddedResourcesClientBundle INSTANCE = GWT.create(LuxonEmbeddedResourcesClientBundle.class);

		/**
		 * Contains text representation of CHART.JS adapter code form LUXON.
		 * 
		 * @return chart.js date adapter code for LUXON in synchronous mode
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-luxon.min.js")
		TextResource datetimeAdapter();

		/**
		 * Contains text representation of date-time LUXON java script library code.
		 * 
		 * @return date-time LUXON java script library code in synchronous mode
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "luxon.min.js")
		TextResource datetimeLibrary();

	}

	/**
	 * Static reference to LUXON resources.
	 */
	public static final LuxonEmbeddedResources INSTANCE = new LuxonEmbeddedResources();

	/**
	 * To avoid any instantiation
	 */
	private LuxonEmbeddedResources() {
		super(LuxonModule.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.AbstractEmbeddedResources#getClientBundle()
	 */
	@Override
	protected EmbeddedDateAdapterResources getClientBundle() {
		return LuxonEmbeddedResourcesClientBundle.INSTANCE;
	}

}