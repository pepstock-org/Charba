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
package org.pepstock.charba.client.zoom.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.zoom.ZoomPlugin;

/**
 * A modifier key modifies the action of another key when the keys are pressed at the same time.<br>
 * Below is a list of the common modifier keys to use to configure the {@link ZoomPlugin}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ModifierKey implements Key
{
	/**
	 * Used in combination with the numeric keypad for entering <b>Alt</b> codes, which output special characters;
	 */
	ALT("alt"),
	/**
	 * Used for entering keyboard shortcuts.
	 */
	CTRL("ctrl"),
	/**
	 * Used for entering keyboard shortcuts.
	 */
	META("meta"),
	/**
	 * Used for capitalizing letters and entering different types of symbols.
	 */
	SHIFT("shift");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private ModifierKey(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}
