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

import org.pepstock.charba.client.adapters.DatefnsModule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference DATE-FNS as date time library (synchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatefnsEmbeddedResources extends AbstractEmbeddedResources {

	/**
	 * Client bundle to reference DATE-FNS as date time library.<br>
	 * It defines the DATE-FNS date time library and its CHART.JS adapter.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface DatefnsEmbeddedResourcesClientBundle extends EmbeddedDateAdapterResources {

		/**
		 * Static reference to resources java script source code.
		 */
		static final DatefnsEmbeddedResourcesClientBundle INSTANCE = GWT.create(DatefnsEmbeddedResourcesClientBundle.class);

		/**
		 * The DATE-FNS library is not provided as bundle and then it is embedded into the adapter.
		 * 
		 * @return an empty java script code
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "charba.empty.js")
		TextResource datetimeAdapter();

		/**
		 * Contains text representation of CHART.JS adapter code form DATE-FNS.
		 * 
		 * @return chart.js date adapter code for DATE-FNS
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-date-fns.bundle.min.js")
		TextResource datetimeLibrary();

	}

	/**
	 * Static reference to DATE-FNS resources.
	 */
	public static final DatefnsEmbeddedResources INSTANCE = new DatefnsEmbeddedResources();

	/**
	 * To avoid any instantiation
	 */
	private DatefnsEmbeddedResources() {
		super(DatefnsModule.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.AbstractEmbeddedResources#getClientBundle()
	 */
	@Override
	protected EmbeddedDateAdapterResources getClientBundle() {
		return DatefnsEmbeddedResourcesClientBundle.INSTANCE;
	}

}