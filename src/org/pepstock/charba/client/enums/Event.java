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

/**
 * The events option defines the browser events that the chart should listen to.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Event implements Key
{

	/**
	 * The user moves the mouse over an element
	 */
	MOUSEMOVE("mousemove"),
	/**
	 * The user moves the mouse away from an element
	 */
	MOUSEOUT("mouseout"),
	/**
	 * The user clicks an element
	 */
	CLICK("click"),
	/**
	 * The user touches a point is placed on the touch surface.
	 */
	TOUCHSTART("touchstart"),
	/**
	 * The user touches a point is moved along the touch surface.
	 */
	TOUCHMOVE("touchmove"),
	/**
	 * The user touches a point is removed from the touch surface.
	 */
	TOUCHEND("touchend");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
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