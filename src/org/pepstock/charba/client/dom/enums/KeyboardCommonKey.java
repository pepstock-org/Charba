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

import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeKeyboardEvent;

/**
 * Enumerates the common keys for {@link NativeKeyboardEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardCommonKey implements IsKeyboardKey
{
	/**
	 * The user agent wasn't able to map the event's virtual keycode to a specific key value.<br>
	 * This can happen due to hardware or software constraints, or because of constraints around the platform on which the user agent is running.
	 */
	UNIDENTIFIED("Unidentified");

	// CSS in-line to show the modifier key by element
	static final String CSS = "background: linear-gradient(180deg,#eee,#fff); background-color: rgba(0, 0, 0, 0); background-color: #eee; border: 1px solid #cdd5d7; border-radius: 6px; box-shadow: 0 1px 2px 1px #cdd5d7; "
			+ "font-family: consolas,courier,monospace; font-size: .9rem; font-weight: 700; line-height: 1; margin: 3px; padding: 4px 6px; white-space: nowrap; color: black;";

	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardCommonKey(String value) {
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

	/**
	 * Checks and get the native event if is an instance of {@link NativeKeyboardEvent}, otherwise <code>null</code>.
	 * 
	 * @param event DOM native event instance
	 * @return the native event if is an instance of {@link NativeKeyboardEvent}, otherwise <code>null</code>
	 */
	static NativeKeyboardEvent checkAndGet(NativeBaseEvent event) {
		// checks if event is a keyboard one
		if (event instanceof NativeKeyboardEvent) {
			// casts to keyboard event
			return (NativeKeyboardEvent) event;
		}
		// if here, argument is not a keyboard event
		// then returns null
		return null;
	}
}