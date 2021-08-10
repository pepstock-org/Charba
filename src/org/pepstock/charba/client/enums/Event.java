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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.KeyFactory;

/**
 * The events option defines the browser events that the chart, legend, tooltip or plugins should listen to.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Event implements Key
{
	/**
	 * The user moves the mouse over an element
	 */
	MOUSE_MOVE("mousemove"),
	/**
	 * The user moves the mouse away from an element
	 */
	MOUSE_OUT("mouseout"),
	/**
	 * The user clicks an element
	 */
	CLICK("click"),
	/**
	 * The user touches a point is placed on the touch surface.
	 */
	TOUCH_START("touchstart"),
	/**
	 * The user touches a point is moved along the touch surface.
	 */
	TOUCH_MOVE("touchmove"),
	/**
	 * Fires when one or more touch points are removed from the touch surface.
	 */
	TOUCH_END("touchend"),
	/**
	 * Fires when a pointing device is moved into the hit test boundaries of an element or one of its descendants.
	 */
	POINTER_ENTER("pointerenter"),
	/**
	 * Fired when a pointer becomes active.<br>
	 * For mouse, it is fired when the device transitions from no buttons depressed to at least one button depressed.<br>
	 * For touch, it is fired when physical contact is made with the digitizer.<br>
	 * For pen, it is fired when the stylus makes physical contact with the digitizer.
	 */
	POINTER_DOWN("pointerdown"),
	/**
	 * Fired when a pointer changes coordinates, and the pointer has not been canceled by a browser touch-action.
	 */
	POINTER_MOVE("pointermove"),
	/**
	 * Fired when a pointer is no longer active.
	 */
	POINTER_UP("pointerup"),
	/**
	 * Fired when a pointing device is moved out of the hit test boundaries of an element.<br>
	 * For pen devices, this event is fired when the stylus leaves the hover range detectable by the digitizer.
	 */
	POINTER_LEAVE("pointerleave"),
	/**
	 * Fired for several reasons including:<br>
	 * <ul>
	 * <li>pointing device is moved out of the hit test boundaries of an element
	 * <li>when a pen stylus leaves the hover range detectable by the digitizer
	 * </ul>
	 */
	POINTER_OUT("pointerout");

	/**
	 * Key factory instance to use for array set reading.
	 */
	public static final KeyFactory<Event> FACTORY = keyValue -> Key.getKeyByValue(Event.values(), keyValue);

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
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