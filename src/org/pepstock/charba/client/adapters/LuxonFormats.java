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
 * LUXON chart.js adapter has implemented the formats by <b>Intl.DateTimeFormat</b> instead of strings.<br>
 * To implement <b>Intl.DateTimeFormat</b> is quite complex and maybe useless.<br>
 * Therefore for LUXON, we use a predefined formats as string but equals to defaults of the adapter.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LuxonFormats {
	// singleton instance
	private static final LuxonFormats INSTANCE = new LuxonFormats();

	/**
	 * Creates the object with an empty native object because it will fill by the constructor
	 */
	private LuxonFormats() {
	}

	/**
	 * Returns the singleton instance of module.
	 * 
	 * @return the singleton instance of module
	 */
	static LuxonFormats get() {
		return INSTANCE;
	}
	
	void setDefaults(DateAdapterFormats formats) {
		// sets all default values
		formats.setFormat(DateAdapterFormats.Property.DATETIME, "MMM d, yyyy, h:mm:ss a");
		formats.setFormat(TimeUnit.MILLISECOND, "h:mm:ss.SSS a");
		formats.setFormat(TimeUnit.SECOND, "h:mm:ss a");
		formats.setFormat(TimeUnit.MINUTE, "h:mm a");
		formats.setFormat(TimeUnit.HOUR, "ha");
		formats.setFormat(TimeUnit.DAY, "MMM d");
		formats.setFormat(TimeUnit.WEEK, "W yyyy");
		formats.setFormat(TimeUnit.MONTH, "MMM yyyy");
		formats.setFormat(TimeUnit.QUARTER, "'Q'q - yyyy");
		formats.setFormat(TimeUnit.YEAR, "yyyy");
	}

}