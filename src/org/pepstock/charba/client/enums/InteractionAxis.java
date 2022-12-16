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
 * Defines which directions are used in calculating distances, interacting on the chart.<br>
 * Defaults to {@link InteractionAxis#X} for {@link InteractionMode#INDEX} mode and {@link InteractionAxis#XY} in {@link InteractionMode#DATASET} and
 * {@link InteractionMode#NEAREST} modes.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum InteractionAxis implements Key
{
	/**
	 * Radial directions are used in calculating distances.
	 */
	R("r"),
	/**
	 * X directions are used in calculating distances.
	 */
	X("x"),
	/**
	 * Y directions are used in calculating distances.
	 */
	Y("y"),
	/**
	 * XY directions are used in calculating distances.
	 */
	XY("xy");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private InteractionAxis(String value) {
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