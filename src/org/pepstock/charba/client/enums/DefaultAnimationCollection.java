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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.options.IsAnimationCollection;
import org.pepstock.charba.client.options.IsAnimationProperty;

/**
 * Cores animation collections names provided out of the box by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultAnimationCollection implements IsAnimationCollection
{
	/**
	 * Defines the default animation collection for colors. 
	 */
	COLORS("colors", DefaultAnimationProperty.BACKGROUND_COLOR, DefaultAnimationProperty.BORDER_COLOR),
	/**
	 * Defines the default animation collection for numbers. 
	 */
	NUMBERS("numbers", DefaultAnimationProperty.X, DefaultAnimationProperty.Y, DefaultAnimationProperty.BORDER_WIDTH, DefaultAnimationProperty.RADIUS, DefaultAnimationProperty.TENSION);

	// name value of property
	private final String value;
	// animation type
	private final AnimationType type;
	// animation properties
	private final List<IsAnimationProperty> properties = new LinkedList<>();

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param properties array of properties to use as default
	 */
	private DefaultAnimationCollection(String value, IsAnimationProperty... properties) {
		this.value = value;
		// scans all properties
		for (IsAnimationProperty property : properties) {
			// adds to list
			this.properties.add(property);
		}
		// sets type
		// using the first element of the list
		// no checks here because this is an enum
		this.type = this.properties.get(0).type();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimationCollection#type()
	 */
	@Override
	public AnimationType type() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimationCollection#properties()
	 */
	@Override
	public List<IsAnimationProperty> properties() {
		return Collections.unmodifiableList(properties);
	}

}