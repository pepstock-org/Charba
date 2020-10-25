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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Utility used by date adapter module to override the defaults formats for time unit.<br>
 * This is needed because some defaults provided out of the box by adapters are not consistent (like WEEK).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsFormatsOverrider extends NativeObjectContainer {

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped
	 */
	DefaultsFormatsOverrider(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	/**
	 * Invokes after the initialization of date adapter in order to override default formats if needed.
	 * 
	 * @param overrider native object wrapper of default formats of adapater
	 */
	void override() {
		// invokes to override default
		// sets all default values
		setFormat(DateAdapterFormats.Property.DATETIME, "MMM d, yyyy, h:mm:ss a");
		setFormat(TimeUnit.MILLISECOND, "h:mm:ss.SSS a");
		setFormat(TimeUnit.SECOND, "h:mm:ss a");
		setFormat(TimeUnit.MINUTE, "h:mm a");
		setFormat(TimeUnit.HOUR, "ha");
		setFormat(TimeUnit.DAY, "MMM d");
		setFormat(TimeUnit.WEEK, "W yyyy");
		setFormat(TimeUnit.MONTH, "MMM yyyy");
		setFormat(TimeUnit.QUARTER, "'Q'q - yyyy");
		setFormat(TimeUnit.YEAR, "yyyy");
	}

	/**
	 * Sets the format by {@link TimeUnit}, overriding what is provided by adapter.
	 * 
	 * @param unit time unit to use to set the default
	 * @param format the format to set
	 */
	void setFormat(Key unit, String format) {
		// check if time unit is consistent and there is into the object
		if (Key.isValid(unit) && format != null) {
			setValue(unit, format);
		}
	}

}
