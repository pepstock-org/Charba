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
package org.pepstock.charba.client.defaults.globals;

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimations implements IsDefaultAnimations {

	private static final IsDefaultAnimationCollection DEFAULT_COLLECTION_NUMBERS = new DefaultAnimationCollection(DefaultAnimationCollectionKey.NUMBERS);

	private static final IsDefaultAnimationCollection DEFAULT_COLLECTION_COLORS = new DefaultAnimationCollection(DefaultAnimationCollectionKey.COLORS);

	private static final List<DefaultAnimationCollectionKey> DEFAULT_ANIMATION_COLLECTION_KEYS = Arrays.asList(DefaultAnimationCollectionKey.NUMBERS, DefaultAnimationCollectionKey.COLORS);

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimations() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#has(org.pepstock.charba.client.options.IsAnimationCollectionKey)
	 */
	@Override
	public boolean has(IsAnimationCollectionKey collection) {
		// checks if collection is valid
		if (IsAnimationCollectionKey.isValid(collection)) {
			// scans all defaults
			for (DefaultAnimationCollectionKey defaultCollection : DEFAULT_ANIMATION_COLLECTION_KEYS) {
				// checks if equals
				if (defaultCollection.equals(collection)) {
					// equals then exist
					return true;
				}
			}

		}
		// if here, collection not valid or not a default
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#get(org.pepstock.charba.client.options.IsAnimationCollectionKey)
	 */
	@Override
	public IsDefaultAnimationCollection get(IsAnimationCollectionKey collection) {
		// checks if collection key is consistent
		IsAnimationCollectionKey.checkIfValid(collection);
		// checks if collection is valid and is default one
		if (DefaultAnimationCollectionKey.is(collection)) {
			// checks if is color
			if (Key.equals(collection, DefaultAnimationCollectionKey.COLORS)) {
				return DEFAULT_COLLECTION_COLORS;
			} else {
				// if here, is numbers
				return DEFAULT_COLLECTION_NUMBERS;
			}
		}
		// if here, collection not valid or not a default
		return new DefaultAnimationCollection(collection);
	}

}
