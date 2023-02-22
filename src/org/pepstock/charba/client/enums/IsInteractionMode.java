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
 * Defines an object which represents the interaction mode for the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsInteractionMode extends Key {

	/**
	 * Returns an interaction mode instance by its string value.
	 * 
	 * @param mode string value to use
	 * @return new interaction mode instance
	 */
	static IsInteractionMode create(String mode) {
		// checks if event as argument is a default one
		for (DefaultInteractionMode defMode : DefaultInteractionMode.values()) {
			// checks if is the default
			if (defMode.value().equalsIgnoreCase(mode)) {
				return defMode;
			}
		}
		// if here, is not a defined one
		// then creates new interaction mode
		return new StandardInteractionMode(mode);
	}

}