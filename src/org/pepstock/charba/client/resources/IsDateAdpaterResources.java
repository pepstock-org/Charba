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

/**
 * Client bundle to CHART.JS date adapter library java script codes, always needed to CHARBA.<br>
 * CHART.JS date time adapter library text resource is just defined because the mode how to inject them depends on the
 * implementation of this interface.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> resources prototype type of CHART.JS date adapter library resource
 */
interface IsDateAdpaterResources {

	/**
	 * Contains text representation of date-time java script library code.
	 * 
	 * @return date-time java script library code
	 */
	InjectableResource datetimeLibrary();

	/**
	 * Contains text representation of CHART.JS adapter code.<br>
	 * There is a specific adapter for the different date-time libraries.
	 * 
	 * @return chart.js date adapter code
	 */
	InjectableResource datetimeAdapter();

}