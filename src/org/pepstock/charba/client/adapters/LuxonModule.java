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

import org.pepstock.charba.client.enums.DefaultDateAdapter;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Date adapter module for LUXON.<br>
 * <b><a href="https://moment.github.io/luxon/">luxon</a></b> provides a powerful, modern, and friendly wrapper for javascript dates and times.<br>
 * LUXON chart.js adapter has implemented the formats by <b>Intl.DateTimeFormat</b> instead of strings.<br>
 * To implement <b>Intl.DateTimeFormat</b> is quite complex and maybe useless.<br>
 * Therefore for LUXON, we use a predefined formats as string but equals to defaults of the adapter.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LuxonModule extends AbstractModule {
	// singleton instance
	private static final LuxonModule INSTANCE = new LuxonModule();

	/**
	 * To avoid any instantiation
	 */
	private LuxonModule() {
		super(DefaultDateAdapter.LUXON);
	}

	/**
	 * Returns the singleton instance of module.
	 * 
	 * @return the singleton instance of module
	 */
	public static LuxonModule get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.adapters.AbstractModule#overrideDefaultFormats(org.pepstock.charba.client.adapters.AbstractModule.DefaultsFormatsOverrider)
	 */
	@Override
	public void overrideDefaultFormats(DefaultsFormatsOverrider overrider) {
		// checks if argument is consistent
		if (overrider != null) {
			// sets all default values
			overrider.setFormat(DateAdapterFormats.Property.DATETIME, "MMM d, yyyy, h:mm:ss a");
			overrider.setFormat(TimeUnit.MILLISECOND, "h:mm:ss.SSS a");
			overrider.setFormat(TimeUnit.SECOND, "h:mm:ss a");
			overrider.setFormat(TimeUnit.MINUTE, "h:mm a");
			overrider.setFormat(TimeUnit.HOUR, "ha");
			overrider.setFormat(TimeUnit.DAY, "MMM d");
			overrider.setFormat(TimeUnit.WEEK, "W yyyy");
			overrider.setFormat(TimeUnit.MONTH, "MMM yyyy");
			overrider.setFormat(TimeUnit.QUARTER, "'Q'q - yyyy");
			overrider.setFormat(TimeUnit.YEAR, "yyyy");
		}
	}

}
