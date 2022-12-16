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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.TransitionKey;

/**
 * Cores animation update modes (transition) provided out of the box by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultTransitionKey implements TransitionKey
{
	/**
	 * Uses to configure the animation to the default.
	 */
	DEFAULT("default"),
	/**
	 * Uses to configure the animation when an element is hovering.
	 */
	ACTIVE("active"),
	/**
	 * Uses to configure the animation when a data set is hidden using legend or {@link IsChart#hide(int)}.
	 */
	HIDE("hide"),
	/**
	 * Uses to configure the animation when an element is resetting.
	 */
	RESET("reset"),
	/**
	 * Uses to configure the animation when an element is resizing.
	 */
	RESIZE("resize"),
	/**
	 * Uses to configure the animation when a data set is shown using legend or {@link IsChart#show(int)}.
	 */
	SHOW("show"),
	/**
	 * Uses to configure the animation to ignore the animation.
	 */
	NONE("none");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DefaultTransitionKey(String value) {
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
	 * Returns <code>true</code> if the argument is equals to a default animation mode.
	 * 
	 * @param mode the animation mode to check
	 * @return <code>true</code> if the argument is equals to a default animation mode
	 */
	public static boolean is(TransitionKey mode) {
		// checks if mode is valid
		if (Key.isValid(mode)) {
			// invokes the checking
			return is(mode.value());
		}
		// if here the argument is not consistent
		// then always false
		return false;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default animation mode.
	 * 
	 * @param mode the animation mode to check
	 * @return <code>true</code> if the argument is equals to a default animation mode
	 */
	public static boolean is(String mode) {
		return Key.hasKeyByValue(values(), mode);
	}

}