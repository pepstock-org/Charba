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
package org.pepstock.charba.client.utils;

import org.pepstock.charba.client.commons.Constants;

/**
 * Contains the constants used by JSON stringify replacer.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JSONReplacerConstants {

	// used in the JSON stringfy replacer when the object is already passed
	static final String CYCLE_PROPERTY_VALUE = Constants.EMPTY_STRING;
	// used in the JSON stringfy replacer when the key of object is Charba internal
	static final String CHARBA_PROPERTY_KEY_PREFIX = "_charba";
	// empty json object when a null instance is passed
	static final String EMPTY_JSON_OBJECT = "{}";

	/**
	 * To avoid any instantiation.
	 */
	private JSONReplacerConstants() {
		// do nothing
	}

}