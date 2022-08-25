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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.dom.events.NativeKeyboardEvent;

/**
 * Enumerates the navigation keys for {@link NativeKeyboardEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardNavigationKey implements IsKeyboardKey
{
	/**
	 * The down arrow key.
	 */
	ARROWDOWN("ArrowDown"),
	/**
	 * The left arrow key.
	 */
	ARROWLEFT("ArrowLeft"),
	/**
	 * The right arrow key.
	 */
	ARROWRIGHT("ArrowRight"),
	/**
	 * The up arrow key.
	 */
	ARROWUP("ArrowUp"),
	/**
	 * The <code>End</code> key. Moves to the end of content.
	 */
	END("End"),
	/**
	 * The <code>Home</code> key. Moves to the start of content.
	 */
	HOME("Home"),
	/**
	 * The <code>Page Down</code> (or <code>PgDn</code>) key. Scrolls down or displays the next page of content.
	 */
	PAGEDOWN("PageDown"),
	/**
	 * The <code>Page Up</code> (or <code>PgUp</code>) key. Scrolls up or displays the previous page of content.
	 */
	PAGEUP("PageUp");

	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardNavigationKey(String value) {
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
