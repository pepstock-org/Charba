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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * It wraps default global options if there are and provides all default values for {@link ColorSchemes#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ColorSchemesDefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final ColorSchemesDefaultsOptions DEFAULTS_INSTANCE = new ColorSchemesDefaultsOptions();
	
	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	private ColorSchemesDefaultsOptions() {
		this(null);
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	ColorSchemesDefaultsOptions(NativeObject nativeObject) {
		super(ColorSchemes.ID, nativeObject);
	}

	/**
	 * Returns the color scheme category.
	 * 
	 * @return the color scheme category
	 */
	String getSchemeCategory() {
		return getValue(ColorSchemesOptions.Property.SCHEME_CATEGORY, ColorSchemesOptions.DEFAULT_SCHEME.category());
	}

	/**
	 * Returns the color scheme name.
	 * 
	 * @return the color scheme name
	 */
	String getSchemeName() {
		return getValue(ColorSchemesOptions.Property.SCHEME_NAME, ColorSchemesOptions.DEFAULT_SCHEME.value());
	}

	/**
	 * Returns the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 * 
	 * @return the color scheme cope when the scheme is applied to hoving flex datasets, like bars charts
	 */
	SchemeScope getSchemeScope() {
		return getValue(ColorSchemesOptions.Property.SCHEME_SCOPE, SchemeScope.class, ColorSchemesOptions.DEFAULT_SCHEME_SCOPE);
	}

	/**
	 * The transparency value for the background color. Must be a number between 0.0 (fully transparent) and 1.0 (no
	 * transparency).
	 * 
	 * @return the transparency value for the background color
	 */
	double getBackgroundColorAlpha() {
		return getValue(ColorSchemesOptions.Property.BACKGROUND_COLOR_ALPHA, ColorSchemesOptions.DEFAULT_BACKGROUND_ALPHA);
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @return if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 */
	boolean isReverse() {
		return getValue(ColorSchemesOptions.Property.REVERSE, ColorSchemesOptions.DEFAULT_REVERSE);
	}

}
