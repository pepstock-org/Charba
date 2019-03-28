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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.impl.plugins.enums.Brewer;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;

/**
 * It wraps default global options if there are and provides all default values for COLORSCHEMES plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ColorSchemesDefaultsOptions extends NativeObjectContainer {

	static final String DEFAULT_SCHEME_CATEGORY = "custom";
	
	// this is not private because it used as default scheme to return
	// without searching into enumeration classes
	private static final ColorScheme DEFAULT_SCHEME = Brewer.Paired12;
	
	private static final SchemeScope DEFAULT_SCHEME_SCOPE = SchemeScope.dataset;

	private static final double DEFAULT_FILL_ALPHA = 0.5D;

	private static final boolean DEFAULT_REVERSE = false;

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	ColorSchemesDefaultsOptions() {
		this(null);
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	ColorSchemesDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
		ColorSchemesUtil.putColorScheme(DEFAULT_SCHEME);
	}

	/**
	 * Returns the color scheme category.
	 * 
	 * @return the color scheme category
	 */
	String getSchemeCategory() {
		return getValue(ColorSchemesOptions.Property.schemeCategory, DEFAULT_SCHEME.category());
	}

	/**
	 * Returns the color scheme name.
	 * 
	 * @return the color scheme name
	 */
	String getSchemeName() {
		return getValue(ColorSchemesOptions.Property.schemeName, DEFAULT_SCHEME.name());
	}
	
	/**
	 * FIXME
	 * 
	 * @return the color scheme name
	 */
	SchemeScope getSchemeScope() {
		return getValue(ColorSchemesOptions.Property.schemeScope, SchemeScope.class, DEFAULT_SCHEME_SCOPE);
	}
	
	/**
	 * The transparency value for the line fill color. Must be a number between 0.0 (fully transparent) and 1.0 (no
	 * transparency).
	 * 
	 * @return the transparency value for the line fill color
	 */
	double getFillAlpha() {
		return getValue(ColorSchemesOptions.Property.fillAlpha, DEFAULT_FILL_ALPHA);
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @return if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 */
	boolean isReverse() {
		return getValue(ColorSchemesOptions.Property.reverse, DEFAULT_REVERSE);
	}

}
