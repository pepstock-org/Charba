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

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the visibility values for showing or hiding an element without changing the layout of a document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Visibility implements Key
{
	/**
	 * The element box is visible.
	 */
	VISIBLE("visible"),
	/**
	 * The element box is invisible (not drawn), but still affects layout as normal.<br>
	 * Descendants of the element will be visible if they have visibility set to visible.<br>
	 * The element cannot receive focus
	 */
	HIDDEN("hidden"),
	/**
	 * Collapsed flex items and ruby annotations are hidden, and the space they would have occupied is removed.
	 */
	COLLAPSE("collapse"),
	/**
	 * Inherits from the parent.
	 */
	INHERIT("inherit"),
	/**
	 * Sets this property to its default value.
	 */
	INITIAL("initial");

	// suffix value of size
	private final String value;

	/**
	 * Creates with the suffix value to use in the size.
	 * 
	 * @param value suffix value of unit
	 */
	private Visibility(String value) {
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