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

import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.ColorSchemesOptionsFactory.ColorSchemesDefaultsOptionsFactory;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the object to map the COLORSCHEMES plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemesOptions extends AbstractPluginOptions {

	// defaults global options instance
	private ColorSchemesDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final ColorSchemesDefaultsOptionsFactory defaultsFactory = new ColorSchemesDefaultsOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		schemeScope,
		schemeCategory,
		schemeName,
		fillAlpha,
		reverse
	}

	/**
	 * Creates an empty object with plugin options.
	 */
	public ColorSchemesOptions() {
		// creates an empty object
		this(null);
	}

	/**
	 * Builds the object using a native object.
	 * 
	 * @param native object which contains the properties
	 */
	ColorSchemesOptions(NativeObject nativeObject) {
		super(ColorSchemes.ID, nativeObject);
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
	}

	public void setSchemeScope(SchemeScope schemeScope) {
		setValue(Property.schemeScope, schemeScope);
	}

	/**
	 * FIXME
	 * 
	 * @return the color scheme name
	 */
	public SchemeScope getSchemeScope() {
		return getValue(ColorSchemesOptions.Property.schemeScope, SchemeScope.class, defaultsOptions.getSchemeScope());
	}

	public void setScheme(ColorScheme scheme) {
		if (!ColorSchemesUtil.hasColorScheme(scheme.category(), scheme.name())) {
			ColorSchemesUtil.putColorScheme(scheme);
		}
		setValue(Property.schemeName, scheme);
		setValue(Property.schemeCategory, scheme.category());
	}

	/**
	 * Returns the color scheme.
	 * 
	 * @return the color scheme
	 */
	public ColorScheme getScheme() {
		String category = getValue(Property.schemeCategory, defaultsOptions.getSchemeCategory());
		String name = getValue(Property.schemeName, defaultsOptions.getSchemeName());
		return ColorSchemesUtil.getColorScheme(category, name);
	}

	public void setFillAlpha(double fillAlpha) {
		Color.checkAlphaWithinBounds(fillAlpha);
		setValue(Property.fillAlpha, fillAlpha);
	}

	/**
	 * The transparency value for the line fill color. Must be a number between 0.0 (fully transparent) and 1.0 (no
	 * transparency).
	 * 
	 * @return the transparency value for the line fill color
	 */
	public double getFillAlpha() {
		return getValue(Property.fillAlpha, defaultsOptions.getFillAlpha());
	}

	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @return if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 */
	public boolean isReverse() {
		return getValue(Property.reverse, defaultsOptions.isReverse());
	}

}
