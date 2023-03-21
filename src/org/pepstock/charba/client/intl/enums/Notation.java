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
package org.pepstock.charba.client.intl.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerated the options in order to set the formatting that should be displayed for the number.<br>
 * The defaults is "standard".
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Notation implements Key
{
	/**
	 * To set plain number formatting.
	 */
	STANDARD("standard"),
	/**
	 * To return the order-of-magnitude for formatted number.
	 */
	SCIENTIFIC("scientific"),
	/**
	 * To return the exponent of ten when divisible by three.
	 */
	ENGINEERING("engineering"),
	/**
	 * To return the string representing exponent.
	 */
	COMPACT("compact");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Notation(String value) {
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