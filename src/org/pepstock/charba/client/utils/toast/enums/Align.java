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
package org.pepstock.charba.client.utils.toast.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The align option defines the position of the toast actions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Align implements Key
{
	/**
	 * The actions are centered on the toast.
	 */
	CENTER("center"),
	/**
	 * The actions are positioned to the right of the toast.
	 */
	RIGHT("right"),
	/**
	 * Is the default: the actions are positioned to the left of the toast.
	 */
	LEFT("left");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Align(String value) {
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