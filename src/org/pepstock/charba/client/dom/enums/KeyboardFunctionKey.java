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

import org.pepstock.charba.client.dom.events.NativeKeyboardEvent;

/**
 * Enumerates the function keys for {@link NativeKeyboardEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardFunctionKey implements IsKeyboardKey
{
	/**
	 * The first general-purpose function key, <code>F1</code>.
	 */
	F1("F1"),
	/**
	 * The <code>F2</code> key.
	 */
	F2("F2"),
	/**
	 * The <code>F3</code> key.
	 */
	F3("F3"),
	/**
	 * The <code>F4</code> key.
	 */
	F4("F4"),
	/**
	 * The <code>F5</code> key.
	 */
	F5("F5"),
	/**
	 * The <code>F6</code> key.
	 */
	F6("F6"),
	/**
	 * The <code>F7</code> key.
	 */
	F7("F7"),
	/**
	 * The <code>F8</code> key.
	 */
	F8("F8"),
	/**
	 * The <code>F9</code> key.
	 */
	F9("F9"),
	/**
	 * The <code>F10</code> key.
	 */
	F10("F10"),
	/**
	 * The <code>F11</code> key.
	 */
	F11("F11"),
	/**
	 * The <code>F12</code> key.
	 */
	F12("F12"),
	/**
	 * The <code>F13</code> key.
	 */
	F13("F13"),
	/**
	 * The <code>F14</code> key.
	 */
	F14("F14"),
	/**
	 * The <code>F15</code> key.
	 */
	F15("F15"),
	/**
	 * The <code>F16</code> key.
	 */
	F16("F16"),
	/**
	 * The <code>F17</code> key.
	 */
	F17("F17"),
	/**
	 * The <code>F18</code> key.
	 */
	F18("F18"),
	/**
	 * The <code>F19</code> key.
	 */
	F19("F19"),
	/**
	 * The <code>F20</code> key.
	 */
	F20("F20"),
	/**
	 * The first general-purpose virtual function key.
	 */
	SOFT1("Soft1"),
	/**
	 * The second general-purpose virtual function key.
	 */
	SOFT2("Soft2"),
	/**
	 * The third general-purpose virtual function key.
	 */
	SOFT3("Soft3"),
	/**
	 * The fourth general-purpose virtual function key.
	 */
	SOFT4("Soft4");

	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardFunctionKey(String value) {
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