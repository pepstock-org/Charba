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

import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * This interface defines what a color scheme must have in terms of methods.<br>
 * A color scheme must have:<br>
 * <ul>
 * <li><b>name</b> is the name of color scheme
 * <li><b>category</b> is the category of color scheme. Out of the box there are 3 categories: brewer, office and tableau.
 * <li><b>colors</b> is a list of colors which are composing a color scheme
 * </ul>
 * <br>
 * This can be instantiated also for custom schemes, on top of the provided ones.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ColorScheme extends Key {
	
	/**
	 * Returns <code>true</code> if color scheme passed as argument is not <code>null</code> and its properties are not <code>null</code>.
	 * 
	 * @param scheme color scheme to be checked
	 * @return <code>true</code> if color scheme passed as argument is not <code>null</code> and its properties are not <code>null</code>
	 */
	static boolean isValid(ColorScheme scheme) {
		return Key.isValid(scheme) && scheme.category() != null && scheme.category().trim().length() > 0 && scheme.getColors() != null && !scheme.getColors().isEmpty();
	}
	
	/**
	 * Returns the color scheme category. If not implemented, the default is
	 * {@link ColorSchemesOptions#DEFAULT_SCHEME_CATEGORY}.
	 * 
	 * @return the color scheme category. If not implemented, the default is {@link ColorSchemesOptions#DEFAULT_SCHEME_CATEGORY}
	 */
	default String category() {
		return ColorSchemesOptions.DEFAULT_SCHEME_CATEGORY;
	}

	/**
	 * Returns a list of colors which are composing a color scheme.
	 * 
	 * @return a list of colors which are composing a color scheme
	 */
	List<IsColor> getColors();

}
