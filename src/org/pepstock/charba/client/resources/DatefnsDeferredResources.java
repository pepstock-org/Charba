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
 * Client bundle to reference DATE-FNS as date time library.<br>
 * It defines the DATE-FNS date time library and its CHART.JS adapter (always loaded in sync mode).
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface DatefnsDeferredResources extends IsDeferredResources {

	/**
	 * Static reference to resources java script source code.
	 */
	public static final DatefnsDeferredResources INSTANCE = GWT.create(DatefnsDeferredResources.class);

	/**
	 * Contains text representation of CHART.JS adapter code form DATE-FNS
	 * 
	 * @return chart.js date adapter code for DATE-FNS in async mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chartjs-adapter-date-fns.bundle.min.js")
	ExternalTextResource datetimeLibrary();

	/**
	 * The DATE-FNS library is not provided as bundle and then it is embedded into the adapter.
	 * 
	 * @return an empty java script code in sync mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "charba.empty.js")
	TextResource datetimeAdapter();

}