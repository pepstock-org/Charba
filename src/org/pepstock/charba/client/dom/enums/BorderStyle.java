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
 * Enumerates the sets the line style for all four sides of an element's border.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum BorderStyle implements Key
{
	/**
	 * Like the hidden keyword, displays no border.
	 */
	NONE("none"),
	/**
	 * Displays a series of rounded dots.<br>
	 * The spacing of the dots is not defined by the specification and is implementation-specific.
	 */
	DOTTED("dotted"),
	/**
	 * Displays a series of short square-ended dashes or line segments.<br>
	 * The exact size and length of the segments are not defined by the specification and are implementation-specific.
	 */
	DASHED("dashed"),
	/**
	 * Like the none keyword, displays no border.
	 */
	HIDDEN("hidden"),
	/**
	 * Displays a single, straight, solid line.
	 */
	SOLID("solid"),
	/**
	 * Displays two straight lines that add up to the pixel size defined by border width.
	 */
	DOUBLE("double"),
	/**
	 * Displays a border with a carved appearance. It is the opposite of {@link BorderStyle#RIDGE}.
	 */
	GROOVE("groove"),
	/**
	 * Displays a border with an extruded appearance. It is the opposite of {@link BorderStyle#GROOVE}.
	 */
	RIDGE("ridge"),
	/**
	 * Displays a border that makes the element appear embedded. It is the opposite of {@link BorderStyle#OUTSET}.
	 */
	INSET("inset"),
	/**
	 * Displays a border that makes the element appear embossed. It is the opposite of {@link BorderStyle#INSET}.
	 */
	OUTSET("outset");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private BorderStyle(String value) {
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