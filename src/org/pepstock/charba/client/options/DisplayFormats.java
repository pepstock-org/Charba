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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.adapters.DateAdapter;
import org.pepstock.charba.client.adapters.DateAdapterFormats;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.NoDefaults;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DisplayFormats extends AbstractModel<Time, NoDefaults> {

	// defaults format instance, retrieved by the date adapter
	private DateAdapterFormats defaultFormats = null;

	/**
	 * Creates the object with the parent, the key of this element and native object to map java script properties.<br>
	 * This element does not have any default values.
	 * 
	 * @param time time element as parent of this node.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	DisplayFormats(Time time, Key childKey, NativeObject nativeObject) {
		// no default values
		super(time, childKey, NoDefaults.INSTANCE, nativeObject);
	}

	/**
	 * Sets the display formats are used to configure how different time units are formed into strings for the axis tick marks.
	 * 
	 * @param unit time unit.
	 * @param format display format
	 */
	public void setDisplayFormat(TimeUnit unit, String format) {
		setValueAndAddToParent(unit, format);
	}

	/**
	 * Returns the display formats are used to configure how different time units are formed into strings for the axis tick marks.
	 * 
	 * @param unit time unit.
	 * @return display format or <code>null</code> if unit is not consistent
	 */
	public String getDisplayFormat(TimeUnit unit) {
		// checks if unit is consistent
		if (Key.isValid(unit)) {
			// checks if format is already loaded
			if (defaultFormats == null) {
				// gets a date adapter
				DateAdapter adapter = new DateAdapter();
				// stores the default formats
				defaultFormats = adapter.getFormats();
			}
			// returns the configuration creating a key.
			return getValue(unit, defaultFormats.getFormat(unit));
		}
		// if here unit is not consistent
		return null;
	}
}