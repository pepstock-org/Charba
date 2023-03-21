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
 * Enumerates the DOM event type of the mouse.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MouseEventType implements IsEvent
{
	/**
	 * Triggers after mousedown and then mouseup over the same element if the left mouse button was used.
	 */
	CLICK("click"),
	/**
	 * Every mouse move over an element triggers that event.
	 */
	MOUSE_MOVE("mousemove"),
	/**
	 * Mouse button is clicked over an element
	 */
	MOUSE_UP("mouseup"),
	/**
	 * Mouse button is released over an element
	 */
	MOUSE_DOWN("mousedown"),
	/**
	 * Mouse pointer entries in an element.
	 */
	MOUSE_ENTER("mouseenter"),
	/**
	 * Mouse pointer leaves from an element.
	 */
	MOUSE_LEAVE("mouseleave"),
	/**
	 * Mouse pointer comes over from an element.
	 */
	MOUSE_OVER("mouseover"),
	/**
	 * Mouse pointer comes out from an element.
	 */
	MOUSE_OUT("mouseout"),
	/**
	 * Triggers after two clicks on the same element within a short timeframe.
	 */
	DOUBLE_CLICK("dblclick"),
	/**
	 * Triggers when the right mouse button is pressed.<br>
	 * There are other ways to open a context menu, e.g. using a special keyboard key, it triggers in that case also, so it’s not exactly the mouse event.
	 */
	CONTEXT_MENU("contextmenu");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private MouseEventType(String value) {
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