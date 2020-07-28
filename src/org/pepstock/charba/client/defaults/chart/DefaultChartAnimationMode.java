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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.defaults.globals.DefaultAnimationMode;
import org.pepstock.charba.client.options.IsAnimationCollectionKey;
import org.pepstock.charba.client.options.IsAnimationPropertyKey;

/**
 * Defaults for property animation option element.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of default interface of wrapped object
 */
public class DefaultChartAnimationMode<T extends IsDefaultAnimationMode> extends AbstractDefaultChartAnimation<T> implements IsDefaultAnimationMode {

	/**
	 * Creates the object wrapping a base animation instance.
	 * 
	 * @param animation a base animation instance to wrap
	 */
	DefaultChartAnimationMode(T animation) {
		super(animation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationMode#getProperty(org.pepstock.charba.client.options.IsAnimationPropertyKey)
	 */
	@Override
	public IsDefaultAnimationProperty getProperty(IsAnimationPropertyKey property) {
		// gets from defaults
		IsDefaultAnimationProperty storedProperty = getDefaults().getProperty(property);
		// checks if property is consistent
		if (storedProperty != null) {
			// returns a default chart animation property wrapping the property
			// from chart
			return new DefaultChartAnimationProperty(storedProperty);
		}
		// if here, the stored property is not consistent
		return DefaultAnimationMode.DEFAULT_ANIMATION_PROPERTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationMode#getCollection(org.pepstock.charba.client.options.IsAnimationCollectionKey)
	 */
	@Override
	public IsDefaultAnimationCollection getCollection(IsAnimationCollectionKey collection) {
		// gets from defaults
		IsDefaultAnimationCollection storedCollection = getDefaults().getCollection(collection);
		// checks if collection is consistent
		if (storedCollection != null) {
			// returns a default chart animation collection wrapping the collection
			// from chart
			return new DefaultChartAnimationCollection(storedCollection);
		}
		// if here, the stored collection is not consistent
		return DefaultAnimationMode.DEFAULT_ANIMATION_COLLECTION;
	}

}
