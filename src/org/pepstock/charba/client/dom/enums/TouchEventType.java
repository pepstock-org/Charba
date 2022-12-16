/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.options.IsEvent;

/**
 * Enumerates the DOM event type of the touch.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum TouchEventType implements IsEvent
{
	/**
	 * Sent when the user places a touch point on the touch surface.<br>
	 * The event's target will be the element in which the touch occurred.
	 */
	TOUCH_START("touchstart"),
	/**
	 * Sent when the user moves a touch point along the surface.<br>
	 * The event's target is the same element that received the {@link TouchEventType#TOUCH_START} event corresponding to the touch point, even if the touch point has moved outside
	 * that element.
	 */
	TOUCH_MOVE("touchmove"),
	/**
	 * Sent when the user removes a touch point from the surface; that is, when they lift a finger or stylus from the surface.<br>
	 * This is also sent if the touch point moves off the edge of the surface; for example, if the user's finger slides off the edge of the screen.
	 */
	TOUCH_END("touchend"),
	/**
	 * Sent when a touch point has been disrupted in some way.<br>
	 * There are several possible reasons why this might happen (and the exact reasons will vary from device to device, as well as browser to browser).
	 */
	TOUCH_CANCEL("touchcancel");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private TouchEventType(String value) {
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