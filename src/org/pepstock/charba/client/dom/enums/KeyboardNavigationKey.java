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
	ARROW_DOWN("ArrowDown"),
	/**
	 * The left arrow key.
	 */
	ARROW_LEFT("ArrowLeft"),
	/**
	 * The right arrow key.
	 */
	ARROW_RIGHT("ArrowRight"),
	/**
	 * The up arrow key.
	 */
	ARROW_UP("ArrowUp"),
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
	PAGE_DOWN("PageDown"),
	/**
	 * The <code>Page Up</code> (or <code>PgUp</code>) key. Scrolls up or displays the previous page of content.
	 */
	PAGE_UP("PageUp");

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