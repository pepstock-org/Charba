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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.HoverFlexDataset;

/**
 * Color scheme utility to cache the usage of color schemes in order to avoid to search them when requested.<br>
 * Used by {@link ColorSchemes#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ColorSchemesUtil {

	// sigleton instance
	private static final ColorSchemesUtil INSTANCE = new ColorSchemesUtil();
	// cache of used and requested color schemes
	private final Map<String, ColorScheme> colorSchemes = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private ColorSchemesUtil() {
		// loads anyway the default because it must be loaded
		putColorScheme(ColorSchemesOptions.DEFAULT_SCHEME);
	}

	/**
	 * Singleton method to get the instance.
	 * 
	 * @return instance of signleton
	 */
	static ColorSchemesUtil get() {
		return INSTANCE;
	}

	/**
	 * Stores a color scheme in the the cache.
	 * 
	 * @param scheme a color scheme
	 */
	void putColorScheme(ColorScheme scheme) {
		colorSchemes.put(createKey(scheme), scheme);
	}

	/**
	 * Returns the stored instance of color scheme from cache.
	 * 
	 * @param category color scheme category
	 * @param name color scheme name
	 * @return a color scheme instance
	 */
	ColorScheme getColorScheme(String category, String name) {
		return colorSchemes.get(createKey(category, name));
	}

	/**
	 * Returns <code>true</code> if the color scheme, retrievable by category and name, is stored in the cache.
	 * 
	 * @param category color scheme category
	 * @param name color scheme name
	 * @return <code>true</code> if in the cache otherwise <code>false</code>
	 */
	boolean hasColorScheme(String category, String name) {
		return colorSchemes.containsKey(createKey(category, name));
	}

	/**
	 * Creates a key to use to store color scheme in the cache.<br>
	 * The key is <code>[category].[name]</code>.
	 * 
	 * @param scheme color scheme instance
	 * @return a key as string
	 */
	String createKey(ColorScheme scheme) {
		return createKey(scheme.category(), scheme.value());
	}

	/**
	 * Creates a key to use to store color scheme in the cache.<br>
	 * The key is <code>[category].[name]</code>.
	 * 
	 * @param category color scheme category
	 * @param name color scheme name
	 * @return a key as string
	 */
	String createKey(String category, String name) {
		// if name is null
		Checker.checkIfValid(name, "Color scheme name");
		// if category is null
		Checker.checkIfValid(category, "Color scheme category");
		// creates a builder with category
		StringBuilder sb = new StringBuilder(category);
		// adds dot and name of color scheme
		sb.append(Constants.DOT).append(name);
		// returns string
		return sb.toString();
	}

	/**
	 * Calculates the maximum border width for hoving flex dataset (BAR).
	 * 
	 * @param hovingDataset dataset to use to calculate
	 * @return the maximum border width defined in the dataset
	 */
	int getMaxBorderWidth(HoverFlexDataset hovingDataset) {
		// gets the list border widths
		List<Integer> borderWidths = hovingDataset.getBorderWidth();
		// sets max
		int maxBorderWidth = 0;
		// if list is not empty
		if (!borderWidths.isEmpty()) {
			// means border widths are defined
			// then scans all to calculate the max
			for (Integer borderWidth : borderWidths) {
				// max
				maxBorderWidth = Math.max(maxBorderWidth, borderWidth);
			}
		} else if (hovingDataset instanceof BarDataset) {
			// if here, the list of border widths is empty
			// but BAR dataset can have BarBorderWidth object to set the border width
			BarDataset barDataset = (BarDataset) hovingDataset;
			// gets border width object
			List<BarBorderWidth> borderWidthObjects = barDataset.getBorderWidthAsObjects();
			// scans all border widths
			for (BarBorderWidth borderWidth : borderWidthObjects) {
				// calculates the max comparing all dimensions
				maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getTop());
				maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getBottom());
				maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getLeft());
				maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getRight());
			}
		}
		return maxBorderWidth;
	}
}
