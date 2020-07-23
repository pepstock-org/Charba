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

import org.pepstock.charba.client.defaults.IsDefaultAnimationCollectionElement;
import org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement;
import org.pepstock.charba.client.defaults.IsDefaultAnimationPropertyElement;
import org.pepstock.charba.client.options.IsAnimationCollection;
import org.pepstock.charba.client.options.IsAnimationProperty;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationModeElement extends AbstractDefaultAnimation implements IsDefaultAnimationModeElement {

	private static final DefaultAnimationPropertyElement DEFAULT_ANIMATION_PROPERTY_ELEMENT = new DefaultAnimationPropertyElement();

	private static final DefaultAnimationCollectionElement DEFAULT_ANIMATION_COLLECTION_ELEMENT = new DefaultAnimationCollectionElement();

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimationModeElement() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement#getProperty(org.pepstock.charba.client.options.IsAnimationProperty)
	 */
	@Override
	public IsDefaultAnimationPropertyElement getProperty(IsAnimationProperty property) {
		return DEFAULT_ANIMATION_PROPERTY_ELEMENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement#getCollection(org.pepstock.charba.client.options.IsAnimationCollection)
	 */
	@Override
	public IsDefaultAnimationCollectionElement getCollection(IsAnimationCollection collection) {
		return DEFAULT_ANIMATION_COLLECTION_ELEMENT;
	}

}
