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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference MOMENT as date time library (asynchronous mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DeferredResources implements IsDeferredResources {

	/**
	 * Client bundle to reference MOMENT as date time library.<br>
	 * It defines the MOMENT date time library and its CHART.JS adapter (always loaded in synchronous mode).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	interface DeferredResourcesClientBundle extends IsDeferredResources {

		/**
		 * Static reference to resources java script source code.
		 */
		static final DeferredResourcesClientBundle INSTANCE = GWT.create(DeferredResourcesClientBundle.class);

		/**
		 * Contains text representation of date-time MOMENT java script library code.
		 * 
		 * @return date-time MOMENT java script library code in asynchronous mode
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "moment.min.js")
		ExternalTextResource datetimeLibrary();

		/**
		 * Contains text representation of CHART.JS adapter code form MOMENT.
		 * 
		 * @return chart.js date adapter code for MOMENT in synchronous mode
		 */
		@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-moment.min.js")
		TextResource datetimeAdapter();

	}

	/**
	 * Static reference to MOMENT resources.
	 */
	public static final DeferredResources INSTANCE = new DeferredResources();

	/**
	 * To avoid any instantiation
	 */
	private DeferredResources() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.Resources#datetimeLibrary()
	 */
	@Override
	public ExternalTextResource datetimeLibrary() {
		return DeferredResourcesClientBundle.INSTANCE.datetimeLibrary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.Resources#datetimeAdapter()
	 */
	@Override
	public TextResource datetimeAdapter() {
		return DeferredResourcesClientBundle.INSTANCE.datetimeAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.resources.IsDeferredResources#chartJs()
	 */
	@Override
	public ExternalTextResource chartJs() {
		return DeferredResourcesClientBundle.INSTANCE.chartJs();
	}

}