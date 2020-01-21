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
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CHART.JS, always needed to CHARBA, with DATE-FNS as date time library.<br>
 * This resources type will load the CHART.JS module in sync mode, as part of GWT module to be down loaded.<br>
 * It also defines the DATE-FNS date time library and its CHART.JS adapter.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface DatefnsEmbeddedResources extends IsEmbeddedResources {

	/**
	 * Static reference to resources java script source code.
	 */
	public static final DatefnsEmbeddedResources INSTANCE = GWT.create(DatefnsEmbeddedResources.class);

	/**
	 * Contains text representation of native chart.js code.
	 * 
	 * @return chart.js code in sync mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chart.min.js")
	TextResource chartJs();

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