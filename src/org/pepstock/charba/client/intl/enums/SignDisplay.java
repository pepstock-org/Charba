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
 * Enumerated the options in order to set when to display the sign for the number.<br>
 * Defaults to "auto".
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum SignDisplay implements Key
{

	/**
	 * To sign display for negative numbers only.
	 */
	AUTO("auto"),
	/**
	 * To never display sign.
	 */
	NEVER("never"),
	/**
	 * To always display sign.
	 */
	ALWAYS("always"),
	/**
	 * To sign display for positive and negative numbers, but not zero.
	 */
	EXCEPT_ZERO("exceptZero");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private SignDisplay(String value) {
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