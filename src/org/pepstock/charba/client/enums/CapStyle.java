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
 * Determines how the end points of every line are drawn.<br>
 * There are three possible values for this property and those are: butt, round and square. By default this property is set to butt.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum CapStyle implements Key
{
	/**
	 * The ends of lines are squared off at the end points.<br>
	 * Default.
	 */
	BUTT("butt"),
	/**
	 * The ends of lines are rounded.
	 */
	ROUND("round"),
	/**
	 * The ends of lines are squared off by adding a box with an equal width and half the height of the line's thickness.
	 */
	SQUARE("square");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private CapStyle(String value) {
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