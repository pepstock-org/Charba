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
 * The list of locales specified by the locales argument, after Unicode extensions have been removed from them, is interpreted as a prioritized request from the application.<br>
 * The runtime compares it against the locales it has available and picks the best one available.<br>
 * Two matching algorithms exist, "lookup" and "best fit".
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum LocaleMatcher implements Key
{
	/**
	 * Used to select the single language tag that best matches the language priority list for a given request.<br>
	 * When performing lookup, each language range in the language priority list is considered in turn, according to priority.<br>
	 * By contrast with filtering, each language range represents the most specific tag that is an acceptable match.<br>
	 * <br>
	 * See <a href="https://tools.ietf.org/html/rfc4647#section-3.4">BCP 47</a> for more details.
	 */
	LOOKUP("lookup"),
	/**
	 * Lets the runtime provide a locale that's at least, but possibly more, suited for the request than the result of the Lookup algorithm.
	 */
	BEST_FIT("best fit");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private LocaleMatcher(String value) {
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