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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.enums.AnimationType;

/**
 * This is a standard implementation of an animation collection
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StandardAnimationCollection extends AbstractStandardKey implements IsAnimationCollectionKey {

	private final AnimationType type;

	private final List<IsAnimationPropertyKey> properties = new LinkedList<>();

	/**
	 * Builds the object with the collection value as string
	 * 
	 * @param value value of key as String
	 * @param type type of the properties
	 */
	StandardAnimationCollection(String value, AnimationType type) {
		super(value);
		// stores type
		this.type = type;
		// checks if consistent
		IsAnimationCollectionKey.checkIfValid(this);
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
		return properties;
	}

}
