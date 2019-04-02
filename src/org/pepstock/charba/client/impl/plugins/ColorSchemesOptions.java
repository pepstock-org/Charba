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
import org.pepstock.charba.client.impl.plugins.enums.BrewerScheme;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the object to map the {@link ColorSchemes#ID} plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemesOptions extends AbstractPluginOptions {
	
	/**
	 * Default color scheme, {@link BrewerScheme#Paired12}.
	 */
	public static final ColorScheme DEFAULT_SCHEME = BrewerScheme.Paired12;

	/**
	 * Default color scheme scope (for bar and bubble charts), {@link SchemeScope#dataset}.
	 */
	public static final SchemeScope DEFAULT_SCHEME_SCOPE = SchemeScope.dataset;

	/**
	 * Default transparency value for the background color, <b>{@value DEFAULT_BACKGROUND_ALPHA}</b>.
	 */
	public static final double DEFAULT_BACKGROUND_ALPHA = 0.5D;

	/**
	 * Default the order of the colors in the selected scheme is reversed, <b>{@value DEFAULT_REVERSE}</b>.
	 */
	public static final boolean DEFAULT_REVERSE = false;

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
		backgroundColorAlpha,
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

	/**
	 * Sets the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 * 
	 * @param schemeScope the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 */
	public void setSchemeScope(SchemeScope schemeScope) {
		setValue(Property.schemeScope, schemeScope);
	}

	/**
	 * Returns the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 * 
	 * @return the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts
	 */
	public SchemeScope getSchemeScope() {
		return getValue(ColorSchemesOptions.Property.schemeScope, SchemeScope.class, defaultsOptions.getSchemeScope());
	}

	/**
	 * Sets the color scheme.
	 * 
	 * @param scheme the color scheme.
	 */
	public void setScheme(ColorScheme scheme) {
		// checks if the scheme is already cached (used)
		// checks also if scheme is consistent
		if (!ColorSchemesUtil.hasColorScheme(scheme.category(), scheme.name())) {
			// puts the scheme
			ColorSchemesUtil.putColorScheme(scheme);
		}
		// stores the values of scheme
		setValue(Property.schemeName, scheme);
		setValue(Property.schemeCategory, scheme.category());
	}

	/**
	 * Returns the color scheme.
	 * 
	 * @return the color scheme
	 */
	public ColorScheme getScheme() {
		// gets the category and name from object
		String category = getValue(Property.schemeCategory, defaultsOptions.getSchemeCategory());
		String name = getValue(Property.schemeName, defaultsOptions.getSchemeName());
		// all color scheme are stored into cache when the "set" is called
		// therefore here the scheme MUST be in cache
		return ColorSchemesUtil.getColorScheme(category, name);
	}

	/**
	 * The transparency value for the background color. Must be a number between 0.0 (fully transparent) and 1.0 (no
	 * transparency).
	 * 
	 * @param backgroundColorAlpha the transparency value for the background color
	 */
	public void setBackgroundColorAlpha(double backgroundColorAlpha) {
		// checks if consistent value between 0 and 1
		Color.checkAlphaWithinBounds(backgroundColorAlpha);
		setValue(Property.backgroundColorAlpha, backgroundColorAlpha);
	}

	/**
	 * The transparency value for the background color. Must be a number between 0.0 (fully transparent) and 1.0 (no
	 * transparency).
	 * 
	 * @return the transparency value for the background color
	 */
	public double getBackgroundColorAlpha() {
		return getValue(Property.backgroundColorAlpha, defaultsOptions.getBackgroundColorAlpha());
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @param reverse if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 */
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
