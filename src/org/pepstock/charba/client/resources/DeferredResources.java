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

/**
 * Client bundle to reference CHART.JS, always needed to CHARBA.<br>
 * This resources type will load the CHART.JS module in async mode in order to optimize the performance when GWT code splitting
 * is implemented.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface DeferredResources extends Resources<ExternalTextResource> {

	/**
	 * Static reference to resources java script source code
	 */
	public static final DeferredResources INSTANCE = GWT.create(DeferredResources.class);

	/**
	 * Contains text representation of native chart.js code.
	 * 
	 * @return chart.js code in async mode
	 */
	@Source("js/chart.bundle.min.js")
	ExternalTextResource chartJs();

}