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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to CHART.JS date adapter library java script codes, always needed to CHARBA.<br>
 * This resources type will load the date adapter library in asynchronous mode in order to optimize the performance when GWT
 * code splitting is implemented.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface DeferredDateAdapterResources extends ClientBundle {
	
	/**
	 * Contains text representation of date-time java script library code.
	 * 
	 * @return date-time java script library code
	 */
	ExternalTextResource datetimeLibrary();

	/**
	 * Contains text representation of CHART.JS adapter code.<br>
	 * There is a specific adapter for the different date-time libraries.
	 * 
	 * @return chart.js date adapter code
	 */
	TextResource datetimeAdapter();

}