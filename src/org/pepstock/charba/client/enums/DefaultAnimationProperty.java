/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.IsAnimationProperty;

/**
 * Cores animation properties, to use to animate, provided out of the box by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultAnimationProperty implements IsAnimationProperty
{
	/**
	 * Uses to <b>x</b> property to animate the element.
	 */
	X("x", AnimationType.NUMBER),
	/**
	 * Uses to <b>y</b> property to animate the element.
	 */
	Y("y", AnimationType.NUMBER),
	/**
	 * Uses to <b>borderWidth</b> property to animate the element.
	 */
	BORDER_WIDTH("borderWidth", AnimationType.NUMBER),
	/**
	 * Uses to <b>radius</b> property to animate the element.
	 */
	RADIUS("radius", AnimationType.NUMBER),
	/**
	 * Uses to <b>tension</b> property to animate the element.
	 */
	TENSION("tension", AnimationType.NUMBER),
	/**
	 * Uses to <b>backgroundColor</b> property to animate the element.
	 */
	BACKGROUND_COLOR("backgroundColor", AnimationType.COLOR),
	/**
	 * Uses to <b>borderColor</b> property to animate the element.
	 */
	BORDER_COLOR("borderColor", AnimationType.COLOR),
	/**
	 * Uses to <b>visible</b> property to animate the element.
	 */
	VISIBLE("visible", AnimationType.BOOLEAN);

	// name value of property
	private final String value;
	// animation type 
	private final AnimationType type;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param type animation type related to the property
	 */
	private DefaultAnimationProperty(String value, AnimationType type) {
		this.value = value;
		this.type = type;
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
	 * Returns the animation type related to the property.
	 * 
	 * @return the animation type related to the property
	 */
	@Override
	public AnimationType type() {
		return type;
	}
	
	/**
	 * Returns <code>true</code> if the argument is equals to a default animation property.
	 * 
	 * @param property the animation property to check
	 * @return <code>true</code> if the argument is equals to a default animation property
	 */
	public static boolean is(IsAnimationProperty property) {
		// checks if property is value
		if (IsAnimationProperty.isValid(property)) {
			// invokes the checking
			return is(property.value());
		}
		// if here the argument is null
		// then always false
		return false;
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