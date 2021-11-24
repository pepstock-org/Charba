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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.impl.plugins.ColorScheme;

/**
 * Internal class to manage color scheme as enumeration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EnumeratedScheme implements ColorScheme {

	private final String category;

	private final String value;

	private final String[] colorsAsStrings;

	private final List<IsColor> colors = new LinkedList<>();

	/**
	 * Builds the color scheme using all arguments
	 * 
	 * @param category color scheme category
	 * @param value color scheme value
	 * @param colorsAsStrings array of colors in hex code
	 */
	EnumeratedScheme(String category, String value, String... colorsAsStrings) {
		this.category = category;
		this.value = value;
		this.colorsAsStrings = colorsAsStrings;
	}

	/**
	 * Builds the color scheme using all arguments
	 * 
	 * @param category color scheme category
	 * @param value color scheme value
	 */
	EnumeratedScheme(String category, String value) {
		this.category = category;
		this.value = value;
		this.colorsAsStrings = null;
	}

	/**
	 * Adds a list of colors.
	 * 
	 * @param newColors list of colors to add
	 */
	void addAll(List<IsColor> newColors) {
		// checks if colors is already filled
		if (colors.isEmpty()) {
			// adds colors ONLY if the list is empty
			// therefore only once
			colors.addAll(newColors);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.ColorScheme#category()
	 */
	@Override
	public String category() {
		return category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.ColorScheme#getColors()
	 */
	@Override
	public List<IsColor> getColors() {
		// checks if colors is already filled
		if (colors.isEmpty() && colorsAsStrings != null) {
			// adds colors ONLY if the list is empty
			// therefore only once
			// scans all hex colors and creates ISCOLOR
			for (String color : colorsAsStrings) {
				// and adds them in the list
				colors.add(ColorBuilder.parse(color));
			}
		}
		// returns an unmodifiable list
		return Collections.unmodifiableList(colors);
	}

}
