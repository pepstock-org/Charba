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
 * Client bundle to reference CHART.JS, always needed to CHARBA, with LUXON as date time library.<br>
 * This resources type will load the CHART.JS module in async mode in order to optimize the performance when GWT code splitting
 * is implemented.<br>
 * It also defines the LUXON date time library and its CHART.JS adapter (always loaded in sync mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface LuxonDeferredResources extends IsDeferredResources {

	/**
	 * Static reference to resources java script source code.
	 */
	public static final LuxonDeferredResources INSTANCE = GWT.create(LuxonDeferredResources.class);

	/**
	 * Contains text representation of native chart.js code.
	 * 
	 * @return chart.js code in async mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chart.min.js")
	ExternalTextResource chartJs();

	/**
	 * Contains text representation of date-time LUXON java script library code.
	 * 
	 * @return date-time LUXON java script library code in async mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "luxon.min.js")
	ExternalTextResource datetimeLibrary();
	
	/**
	 * Contains text representation of CHART.JS adapter code form LUXON.
	 * 
	 * @return chart.js date adapter code for LUXON in sync mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-luxon.min.js")
	TextResource datetimeAdapter();

}