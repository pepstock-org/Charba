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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Luxon chart.js adapter has implemented the formats by <b>Intl.DateTimeFormat</b> instead of strings.<br>
 * To implement <b>Intl.DateTimeFormat</b> is quite complex and maybe useless.<br>
 * Therefore for Luxon, we use a predefined formats as string but equals to defaults of the adapter.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LuxonFormats extends AbstractDateAdapterContainer {

	// creaets a singleton instance in order to create other instancea
	private static final LuxonFormats INSTANCE = new LuxonFormats();
	
	/**
	 * Creates the object with an empty native object because it will fill by the constructor
	 */
	private LuxonFormats() {
		super();
		// sets all default values
		setValue(DateAdapterFormats.Property.DATETIME, "MMM d, yyyy, h:mm:ss a");
		setValue(TimeUnit.MILLISECOND, "h:mm:ss.SSS a");
		setValue(TimeUnit.SECOND, "h:mm:ss a");
		setValue(TimeUnit.MINUTE, "h:mm a");
		setValue(TimeUnit.HOUR, "h");
		setValue(TimeUnit.DAY, "MMM d");
		setValue(TimeUnit.WEEK, "DD");
		setValue(TimeUnit.MONTH, "MMM yyyy");
		setValue(TimeUnit.QUARTER, "'Q'q - yyyy");
		setValue(TimeUnit.YEAR, "yyyy");
	}
	
	/**
	 * Singleton method to get static instance.
	 * 
	 * @return object instance
	 */
	static LuxonFormats get() {
		return INSTANCE;
	}

}