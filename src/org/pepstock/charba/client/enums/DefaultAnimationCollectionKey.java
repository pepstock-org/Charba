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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * Cores animation collections names provided out of the box by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultAnimationCollectionKey implements IsAnimationCollectionKey
{
	/**
	 * Defines the default animation collection for colors.
	 */
	COLORS("colors", DefaultAnimationPropertyKey.COLOR, DefaultAnimationPropertyKey.BORDER_COLOR, DefaultAnimationPropertyKey.BACKGROUND_COLOR),
	/**
	 * Defines the default animation collection for numbers.
	 */
	NUMBERS("numbers", DefaultAnimationPropertyKey.X, DefaultAnimationPropertyKey.Y, DefaultAnimationPropertyKey.BORDER_WIDTH, DefaultAnimationPropertyKey.RADIUS, DefaultAnimationPropertyKey.TENSION),
	/**
	 * Defines the default animation collection for visible property.
	 */
	VISIBLE("visible", DefaultAnimationPropertyKey.VISIBLE);

	// name value of property
	private final String value;
	// animation type
	private final AnimationType type;
	// animation properties
	private final List<IsAnimationPropertyKey> properties = new LinkedList<>();

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param properties array of properties to use as default
	 */
	private DefaultAnimationCollectionKey(String value, IsAnimationPropertyKey... properties) {
		this.value = value;
		// scans all properties
		for (IsAnimationPropertyKey property : properties) {
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
	public List<IsAnimationPropertyKey> properties() {
		return Collections.unmodifiableList(properties);
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default animation collection.
	 * 
	 * @param collection the animation collection to check
	 * @return <code>true</code> if the argument is equals to a default animation collection
	 */
	public static boolean is(IsAnimationCollectionKey collection) {
		// checks if collection is valid
		if (Key.isValid(collection)) {
			// invokes the checking
			return is(collection.value());
		}
		// if here the argument is not consistent
		// then always false
		return false;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default animation collection.
	 * 
	 * @param collection the animation collection to check
	 * @return <code>true</code> if the argument is equals to a default animation collection
	 */
	private static boolean is(String collection) {
		return Key.hasKeyByValue(values(), collection);
	}

}