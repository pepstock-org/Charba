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
 * Client bundle to reference CHART.JS, always needed to CHARBA.<br>
 * This resources type will load the CHART.JS in synchronous mode, as part of GWT module to be down-loaded.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface EmbeddedChartResources extends ChartResources<TextResource> {

	/**
	 * Static reference to resources java script source code.
	 */
	static final EmbeddedChartResources INSTANCE = GWT.create(EmbeddedChartResources.class);

	/**
	 * Contains text representation of native chart.js code.
	 * 
	 * @return chart.js code in synchronous mode
	 */
	@Source(ResourcesType.JAVASCRIPT_RESOURCES_PATH + "chart.min.js")
	TextResource chartJs();

}