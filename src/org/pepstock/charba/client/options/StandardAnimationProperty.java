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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.enums.AnimationType;

/**
 * This is a standard implementation of an animation property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StandardAnimationProperty extends AbstractTypedAnimationKey implements AnimationPropertyKey {

	// list of properties with only the key
	private List<AnimationPropertyKey> properties = null;

	/**
	 * Builds the object with the property value as string and its type.
	 * 
	 * @param value value of key as String
	 * @param type type of the property
	 */
	StandardAnimationProperty(String value, AnimationType type) {
		super(value, type);
		// checks if argument is consistent
		AnimationPropertyKey.checkIfValid(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractTypedAnimationKey#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractTypedAnimationKey#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AnimationCollectionKey#properties()
	 */
	@Override
	public List<AnimationPropertyKey> properties() {
		// checks if properties instance is initialized
		if (properties == null) {
			// stores the property itself in the properties list
			// in order to act as animation collection key as well
			this.properties = Collections.unmodifiableList(Arrays.asList(this));
		}
		return properties;
	}

}
