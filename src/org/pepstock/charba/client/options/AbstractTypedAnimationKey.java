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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AnimationType;

/**
 * This is the template for animation ids with animation type as part of the object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractTypedAnimationKey extends AbstractStandardKey implements IsTypedAnimationKey {

	private final AnimationType type;

	/**
	 * Builds the object with the property value as string and its type.
	 * 
	 * @param value value of key as String
	 * @param type type of the property
	 */
	AbstractTypedAnimationKey(String value, AnimationType type) {
		super(value);
		// stores values
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimationProperty#type()
	 */
	@Override
	public final AnimationType type() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractStandardKey#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode() + ((type == null) ? 0 : type.hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractStandardKey#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if value of key is equals
		if (super.equals(obj)) {
			// casts to animation collection
			AbstractTypedAnimationKey other = (AbstractTypedAnimationKey) obj;
			// compares keys
			return Key.equals(type, other.type);
		}
		// if here the super equals returned false
		// then is not equals
		return false;
	}

}
