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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The display option controls the visibility of an item.<br>
 * Controls the axis global visibility (visible when true, hidden when false).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Display implements Key
{
	/**
	 * This is default and the item is drawn.
	 */
	TRUE("true"),
	/**
	 * The item is hidden.
	 */
	FALSE("false"),
	/**
	 * The axis is visible only if at least one associated dataset is visible.<br>
	 * For plugin or point labels, the label is hidden if it overlap with another label.<br>
	 * The display 'auto' option can be used to prevent overlapping labels, based on the following rules when two labels overlap:<br>
	 * <ul>
	 * <li>if both labels are display: true, they will be drawn overlapping
	 * <li>if both labels are display: 'auto', the one with the highest data index will be hidden. If labels are at the same data index, the one with the lowest dataset index will
	 * be hidden.
	 * <li>if one label is display: true and the other one is display: 'auto', the one with 'auto' will be hidden (whatever the data/dataset indices).
	 * </ul>
	 */
	AUTO("auto");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Display(String value) {
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