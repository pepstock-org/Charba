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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * CHART.JS default values for animation collection.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationCollection extends DefaultAnimationProperty implements IsDefaultAnimationCollection {

	private final IsAnimationCollectionKey collection;

	/**
	 * To avoid any instantiation
	 */
	DefaultAnimationCollection() {
		this.collection = null;
	}

	/**
	 * Creates a default animation collection wrapping the {@link IsAnimationCollectionKey}.
	 * 
	 * @param collection a default animation collection to wrap
	 */
	DefaultAnimationCollection(IsAnimationCollectionKey collection) {
		this.collection = IsAnimationCollectionKey.checkAndGetIfValid(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationCollection#getProperties()
	 */
	@Override
	public List<IsAnimationPropertyKey> getProperties() {
		return collection != null ? collection.properties() : Collections.emptyList();
	}

}
