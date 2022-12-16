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
package org.pepstock.charba.client.sankey.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the color mode of the flow to draw on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ColorMode implements Key
{
	/**
	 * This is the default. The mode between "from" and "to" color will be a gradient.
	 */
	GRADIENT("gradient"),
	/**
	 * The mode to enable to use the "from" color between items.
	 */
	FROM("from"),
	/**
	 * The mode to enable to use the "to" color between items.
	 */
	TO("to");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private ColorMode(String value) {
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