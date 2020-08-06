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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationModeKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * CHART.JS default values for animation mode.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultAnimationMode extends AbstractDefaultAnimation implements IsDefaultAnimationMode {

	/**
	 * Default animation property element.
	 */
	public static final IsDefaultAnimationProperty DEFAULT_ANIMATION_PROPERTY = new DefaultAnimationProperty();
	/**
	 * Default animation collection element.
	 */
	public static final IsDefaultAnimationCollection DEFAULT_ANIMATION_COLLECTION = new DefaultAnimationCollection();

	private final IsAnimationModeKey mode;

	/**
	 * To avoid any instantiation
	 */
	DefaultAnimationMode() {
		this.mode = null;
	}

	/**
	 * Creates a default animation mode wrapping the {@link IsAnimationModeKey}.
	 * 
	 * @param mode a default animation mode to wrap
	 */
	DefaultAnimationMode(IsAnimationModeKey mode) {
		this.mode = Key.checkAndGetIfValid(mode);
	}

	/**
	 * Returns the mode instance.
	 * 
	 * @return the mode instance
	 */
	final IsAnimationModeKey getMode() {
		return mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationMode#hasProperty(org.pepstock.charba.client.options.IsAnimationPropertyKey)
	 */
	@Override
	public boolean hasProperty(IsAnimationPropertyKey property) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement#getProperty(org.pepstock.charba.client.options.IsAnimationProperty)
	 */
	@Override
	public IsDefaultAnimationProperty getProperty(IsAnimationPropertyKey property) {
		return DEFAULT_ANIMATION_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationMode#hasCollection(org.pepstock.charba.client.options.IsAnimationCollectionKey)
	 */
	@Override
	public boolean hasCollection(IsAnimationCollectionKey collection) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationModeElement#getCollection(org.pepstock.charba.client.options.IsAnimationCollection)
	 */
	@Override
	public IsDefaultAnimationCollection getCollection(IsAnimationCollectionKey collection) {
		return DEFAULT_ANIMATION_COLLECTION;
	}

}