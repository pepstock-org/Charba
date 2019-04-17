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
package org.pepstock.charba.client.impl.plugins.enums;

import java.util.ArrayList;
import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.impl.plugins.ColorScheme;

/**
 * Internal interface of enumerated color scheme.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsEnumeratedScheme extends ColorScheme {

	/**
	 * Returns the internal enumerated scheme instance.
	 * 
	 * @return the internal enumerated scheme instance
	 */
	ColorScheme getScheme();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	default String value() {
		// checks if scheme is consistent
		if (getScheme() != null) {
			return getScheme().value();
		} else {
			// if not, exception
			throw new IllegalArgumentException("Color scheme instance is null");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.ColorScheme#category()
	 */
	@Override
	default String category() {
		// checks if scheme is consistent
		if (getScheme() != null) {
			return getScheme().category();
		}
		// returns the default category
		return ColorScheme.super.category();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.ColorScheme#getColors()
	 */
	@Override
	default List<IsColor> getColors() {
		// checks if scheme is consistent
		if (getScheme() != null) {
			return getScheme().getColors();
		}
		// if here scheme is not consistent
		// then returns an empty list
		return new ArrayList<>();
	}

}
