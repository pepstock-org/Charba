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
import java.util.Map;

/**
 * Color scheme utility to cache the usage of color schemes in order to avoid to search them when requested.<br>
 * Used by {@link ColorSchemes#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ColorSchemesUtil {
	// cache of used and requested color schemes
	private static final Map<String, ColorScheme> COLOR_SCHEMES = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private ColorSchemesUtil() {
		// do nothing
	}

	/**
	 * Stores a color scheme into the cache.
	 * 
	 * @param scheme a color scheme
	 */
	static void putColorScheme(ColorScheme scheme) {
		COLOR_SCHEMES.put(createKey(scheme), scheme);
	}

	/**
	 * Returns the stored instance of color scheme from cache.
	 * 
	 * @param category color scheme category
	 * @param name color scheme name
	 * @return a color scheme instance
	 */
	static ColorScheme getColorScheme(String category, String name) {
		return COLOR_SCHEMES.get(createKey(category, name));
	}

	/**
	 * Returns <code>true</code> if the color scheme, retrievable by category and name, is stored into cache.
	 * 
	 * @param category color scheme category
	 * @param name color scheme name
	 * @return <code>true</code> if into cache otherwise <code>false</code>
	 */
	static boolean hasColorScheme(String category, String name) {
		return COLOR_SCHEMES.containsKey(createKey(category, name));
	}

	/**
	 * Creates a key to use to store color scheme into cache.<br>
	 * The key is <code>[category].[name]</code>.
	 * 
	 * @param scheme color scheme instance
	 * @return a key as string
	 */
	static String createKey(ColorScheme scheme) {
		return createKey(scheme.category(), scheme.value());
	}

	/**
	 * Creates a key to use to store color scheme into cache.<br>
	 * The key is <code>[category].[name]</code>.
	 * 
	 * @param category color scheme category
	 * @param name color scheme name
	 * @return a key as string
	 */
	static String createKey(String category, String name) {
		// if name is null
		if (name == null) {
			// exception
			throw new IllegalArgumentException("Color scheme name is null!");
		}
		// if category is null
		if (category == null) {
			// exception
			throw new IllegalArgumentException("Color scheme category is null!");
		}
		// creates a builder with category
		StringBuilder sb = new StringBuilder(category);
		// adds dot and name of color scheme
		sb.append(".").append(name);
		// returns string
		return sb.toString();
	}

}
