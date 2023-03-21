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
 * Enumerates the possible value to set the representation of the era, day periods, weekday.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum StringItemStyle implements Key
{
	/**
	 * Narrow setting for era (e.g., A), for weekday (e.g., T).<br>
	 * Two weekdays may have the same narrow style for some locales (e.g. Tuesday's narrow style is also T).
	 */
	NARROW("narrow"),
	/**
	 * Long setting for era (e.g., Anno Domini), for weekday (e.g., Thursday).
	 */
	LONG("long"),
	/**
	 * Short setting for era (e.g., AD), for weekday (e.g., Thu).
	 */
	SHORT("short");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private StringItemStyle(String value) {
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