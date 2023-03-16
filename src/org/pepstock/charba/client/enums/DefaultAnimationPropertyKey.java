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
 * Cores animation properties, to use to animate, provided out of the box by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultAnimationPropertyKey implements Key
{
	/**
	 * Uses to <b>x</b> property to animate the element.
	 */
	X("x"),
	/**
	 * Uses to <b>y</b> property to animate the element.
	 */
	Y("y"),
	/**
	 * Uses to <b>borderWidth</b> property to animate the element.
	 */
	BORDER_WIDTH("borderWidth"),
	/**
	 * Uses to <b>radius</b> property to animate the element.
	 */
	RADIUS("radius"),
	/**
	 * Uses to <b>tension</b> property to animate the element.
	 */
	TENSION("tension"),
	/**
	 * Uses to <b>backgroundColor</b> property to animate the element.
	 */
	BACKGROUND_COLOR("backgroundColor"),
	/**
	 * Uses to <b>borderColor</b> property to animate the element.
	 */
	BORDER_COLOR("borderColor"),
	/**
	 * Uses to <b>color</b> property to animate the element.
	 */
	COLOR("color"),
	/**
	 * Uses to <b>visible</b> property to animate the element.
	 */
	VISIBLE("visible");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DefaultAnimationPropertyKey(String value) {
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

	/**
	 * Returns <code>true</code> if the argument is equals to a default animation property.
	 * 
	 * @param property the animation property to check
	 * @return <code>true</code> if the argument is equals to a default animation property
	 */
	public static boolean is(String property) {
		return Key.hasKeyByValue(values(), property);
	}

}