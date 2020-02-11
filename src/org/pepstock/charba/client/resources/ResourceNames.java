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
 * Constants name to use as element id for scripts, mandatory to CHARBA to work properly.
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ResourceNames {
	
	/**
	 * Script element id for CHART.JS source code.
	 */
	public static final String CHART = "chartJs";

	/**
	 * Script element id for CHART.JS date time adapter.
	 */
	public static final String DATE_TIME_ADAPTER = "datetimeAdapter";

	/**
	 * Script element id for CHART.JS date time library.
	 */
	public static final String DATE_TIME_LIBRARY = "datetimeLibrary";

	/**
	 * To avoid any instantiation
	 */
	private ResourceNames() {
		// do nothing
	}

}
