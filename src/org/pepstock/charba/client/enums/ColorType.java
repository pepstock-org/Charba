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

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the possible types objects to represents a "color" property.<br>
 * A "color" property could be:<br>
 * <ul>
 * <li><b>COLOR</b> if is stored by a string or {@link IsColor}
 * <li><b>PATTERN</b> if is stored by a {@link Pattern}
 * <li><b>GRADIENT</b> if is stored by a {@link Gradient}
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ColorType implements Key
{

	/**
	 * If the color property is stored by a string or {@link IsColor}.
	 */
	COLOR("color"),
	/**
	 * If the color property is stored by a {@link Pattern}.
	 */
	PATTERN("pattern"),
	/**
	 * If the color property is stored by a {@link Gradient}.
	 */
	GRADIENT("gradient");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private ColorType(String value) {
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