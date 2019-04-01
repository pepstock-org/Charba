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
* @return builder instance */
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;

/**
 * Comfortable object to create "charbacolorschemes" plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemesOptionsBuilder {
	// creates the options
	private final ColorSchemesOptions options = new ColorSchemesOptions();

	/**
	 * To avoid any instantiation
	 * 
	 * @return builder instance
	 */
	private ColorSchemesOptionsBuilder() {
		// do nothing;
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static ColorSchemesOptionsBuilder create() {
		return new ColorSchemesOptionsBuilder();
	}

	/**
	 * Returns a configured plugin options.
	 * 
	 * @return a configured plugin options.
	 */
	public ColorSchemesOptions build() {
		// returns options
		return options;
	}

	/**
	 * Sets the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 * 
	 * @param schemeScope the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 * @return builder instance
	 */
	public ColorSchemesOptionsBuilder setSchemeScope(SchemeScope schemeScope) {
		options.setSchemeScope(schemeScope);
		return this;
	}

	/**
	 * Sets the color scheme.
	 * 
	 * @param scheme the color scheme.
	 * @return builder instance
	 */
	public ColorSchemesOptionsBuilder setScheme(ColorScheme scheme) {
		options.setScheme(scheme);
		return this;
	}

	/**
	 * The transparency value for the background color. Must be a number between 0.0 (fully transparent) and 1.0 (no
	 * transparency).
	 * 
	 * @param backgroundColorAlpha the transparency value for the background color
	 * @return builder instance
	 */
	public ColorSchemesOptionsBuilder setBackgroundColorAlpha(double backgroundColorAlpha) {
		options.setBackgroundColorAlpha(backgroundColorAlpha);
		return this;
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @param reverse if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 * @return builder instance
	 */
	public ColorSchemesOptionsBuilder setReverse(boolean reverse) {
		options.setReverse(reverse);
		return this;
	}
}
