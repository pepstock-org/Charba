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
package org.pepstock.charba.client.annotation.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Mouse events to enable on each annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Event implements Key
{
	/**
	 * A pointing device button has been pressed and released on an element.
	 */
	CLICK("click"),
	/**
	 * The right button of the mouse is clicked (before the context menu is displayed).
	 */
	CONTEXT_MENU("contextmenu"),
	/**
	 * A pointing device button is clicked twice on an element.
	 */
	DOUBLE_CLICK("dblclick"),
	/**
	 * A pointing device button is pressed on an element.
	 */
	MOUSE_DOWN("mousedown"),
	/**
	 * A pointing device is moved onto the element that has the listener attached.
	 */
	MOUSE_ENTER("mouseenter"),
	/**
	 * A pointing device is moved off the element that has the listener attached.
	 */
	MOUSE_LEAVE("mouseleave"),
	/**
	 * A pointing device is moved over an element.
	 */
	MOUSE_MOVE("mousemove"),
	/**
	 * A pointing device is moved onto the element that has the listener attached or onto one of its children.
	 */
	MOUSE_OVER("mouseover"),
	/**
	 * A pointing device is moved off the element that has the listener attached or off one of its children.
	 */
	MOUSE_OUT("mouseout"),
	/**
	 * A pointing device button is released over an element.
	 */
	MOUSE_UP("mouseup"),
	/**
	 * A wheel button of a pointing device is rotated in any direction.
	 */
	WHEEL("wheel");

	// name value of property
	private final String value;

	/**
	 * Creates an event property value to use int the native object.
	 * 
	 * @param value value of draw time property name
	 */
	private Event(String value) {
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
