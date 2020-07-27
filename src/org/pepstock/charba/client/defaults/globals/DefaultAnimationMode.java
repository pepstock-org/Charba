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

import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * CHART.JS default values for animation mode.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationMode extends AbstractDefaultAnimation implements IsDefaultAnimationMode {

	private static final DefaultAnimationProperty DEFAULT_ANIMATION_PROPERTY_ELEMENT = new DefaultAnimationProperty();

	private static final DefaultAnimationCollection DEFAULT_ANIMATION_COLLECTION_ELEMENT = new DefaultAnimationCollection();

	/**
	 * To avoid any instantiation
	 */
	DefaultAnimationMode() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement#getProperty(org.pepstock.charba.client.options.IsAnimationProperty)
	 */
	@Override
	public IsDefaultAnimationProperty getProperty(IsAnimationPropertyKey property) {
		return DEFAULT_ANIMATION_PROPERTY_ELEMENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement#getCollection(org.pepstock.charba.client.options.IsAnimationCollection)
	 */
	@Override
	public IsDefaultAnimationCollection getCollection(IsAnimationCollectionKey collection) {
		return DEFAULT_ANIMATION_COLLECTION_ELEMENT;
	}

}
