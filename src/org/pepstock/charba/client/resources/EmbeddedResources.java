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

import org.pepstock.charba.client.adapters.MomentModule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference MOMENT as date time library (synchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EmbeddedResources extends AbstractEmbeddedResources {

	/**
	 * Client bundle to reference MOMENT as date time library.<br>
	 * It defines the MOMENT date time library and its CHART.JS adapter.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface EmbeddedResourcesClientBundle extends EmbeddedDateAdapterResources {

		/**
		 * Static reference to resources java script source code.
		 */
		static final EmbeddedResourcesClientBundle INSTANCE = GWT.create(EmbeddedResourcesClientBundle.class);

		/**
		 * Contains text representation of CHART.JS adapter code form MOMENT.
		 * 
		 * @return chart.js date adapter code for MOMENT in synchronous mode
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-moment.min.js")
		TextResource datetimeAdapter();

		/**
		 * Contains text representation of date-time MOMENT java script library code.
		 * 
		 * @return date-time MOMENT java script library code in synchronous mode
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "moment.min.js")
		TextResource datetimeLibrary();

	}

	/**
	 * Static reference to MOMENT resources.
	 */
	public static final EmbeddedResources INSTANCE = new EmbeddedResources();

	/**
	 * To avoid any instantiation
	 */
	private EmbeddedResources() {
		super(MomentModule.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.AbstractEmbeddedResources#getClientBundle()
	 */
	@Override
	protected EmbeddedDateAdapterResources getClientBundle() {
		return EmbeddedResourcesClientBundle.INSTANCE;
	}

}