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

/**
 * Enumerates the constants identify which part of the keyboard the key event originates from.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardLocation
{

	/**
	 * The key has only one version, or can't be distinguished between the left and right versions of the key, and was not pressed on the numeric keypad or a key that is considered
	 * to be part of the keypad.
	 */
	DOM_KEY_LOCATION_STANDARD(0),
	/**
	 * The key was the left-hand version of the key; for example, the left-hand Control key was pressed on a standard 101 key US keyboard.<br>
	 * This value is only used for keys that have more than one possible location on the keyboard.
	 */
	DOM_KEY_LOCATION_LEFT(1),
	/**
	 * The key was the right-hand version of the key; for example, the right-hand Control key is pressed on a standard 101 key US keyboard.<br>
	 * This value is only used for keys that have more than one possible location on the keyboard.
	 */
	DOM_KEY_LOCATION_RIGHT(2),
	/**
	 * The key was on the numeric keypad, or has a virtual key code that corresponds to the numeric keypad.
	 */
	DOM_KEY_LOCATION_NUMPAD(3);

	// name value of property
	private final int value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardLocation(int value) {
		this.value = value;
	}

	/**
	 * Returns the numeric representation of the location.
	 * 
	 * @return the numeric representation of the location
	 */
	public int value() {
		return value;
	}

	/**
	 * Scans all items of enumeration to get the right location related to passed argument.
	 * 
	 * @param location specifies the location of the event flow
	 * @return the related location.<br>
	 *         If not found, returns always {@link KeyboardLocation#DOM_KEY_LOCATION_STANDARD}.
	 */
	public static final KeyboardLocation get(int location) {
		// scans all locations
		for (KeyboardLocation loc : values()) {
			// checks if equals to passed location
			if (loc.value == location) {
				return loc;
			}
		}
		// if here, the argument does not match with any location
		// then returns standard.
		return DOM_KEY_LOCATION_STANDARD;
	}

}