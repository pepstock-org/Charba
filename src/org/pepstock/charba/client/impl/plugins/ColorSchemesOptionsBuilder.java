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

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;

/**
 * Comfortable object to create {@link ColorSchemes#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemesOptionsBuilder {
	
	// scheme scope
	private SchemeScope schemeScope = ColorSchemesOptions.DEFAULT_SCHEME_SCOPE;
	// color scheme
	private ColorScheme scheme = ColorSchemesOptions.DEFAULT_SCHEME;
	// background color transparency
	private double backgroundColorAlpha = ColorSchemesOptions.DEFAULT_BACKGROUND_ALPHA;
	// reverse 
	private boolean reverse = ColorSchemesOptions.DEFAULT_REVERSE;
	
	/**
	 * To avoid any instantiation
	 */
	private ColorSchemesOptionsBuilder() {
		// do nothing
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
	 * Returns a configured plugin options with global defaults.
	 * 
	 * @return a configured plugin options.
	 */
	public ColorSchemesOptions build() {
		// returns options
		return build(null);
	}

	/**
	 * Returns a configured plugin options.
	 * 
	 * @param type chart type to use to get the default values by chart
	 * @return a configured plugin options.
	 */
	public ColorSchemesOptions build(Type type) {
		// creates the options
		ColorSchemesOptions options = new ColorSchemesOptions(type);
		// loads options
		loadOptions(options);
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
		this.schemeScope = schemeScope;
		return this;
	}

	/**
	 * Sets the color scheme.
	 * 
	 * @param scheme the color scheme.
	 * @return builder instance
	 */
	public ColorSchemesOptionsBuilder setScheme(ColorScheme scheme) {
		this.scheme = scheme;
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
		this.backgroundColorAlpha = backgroundColorAlpha;
		return this;
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @param reverse if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 * @return builder instance
	 */
	public ColorSchemesOptionsBuilder setReverse(boolean reverse) {
		this.reverse = reverse;
		return this;
	}
	
	/**
	 * Loads the options with all properties previously defined.
	 * 
	 * @param options plugin options to load
	 */
	private void loadOptions(ColorSchemesOptions options) {
		//checks if options is consistent
		if (options != null) {
			options.setSchemeScope(schemeScope);
			options.setScheme(scheme);
			options.setBackgroundColorAlpha(backgroundColorAlpha);
			options.setReverse(reverse);
		}
	}
}
