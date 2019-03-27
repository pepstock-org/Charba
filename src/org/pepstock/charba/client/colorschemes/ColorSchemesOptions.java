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
package org.pepstock.charba.client.colorschemes;

import org.pepstock.charba.client.colorschemes.ColorSchemesOptionsFactory.ColorSchemesDefaultsOptionsFactory;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the object to map the LABELS plugin options, both at chart and global level.
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
		fillAlpha,
		scheme,
		reverse
	}

	/**
	 * Creates an empty object with plugin options.
	 */
	public ColorSchemesOptions() {
		// creates the object registering it
		// creates an empty object
		this(null);
	}

	ColorSchemesOptions(NativeObject nativeObject) {
		super(ColorSchemesPlugin.ID, nativeObject);
		// this constructor is used by user to set options for plugin
		// both default global or chart one.
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
	}

	public final void setScheme(Scheme scheme) {
		setValue(Property.scheme, scheme.getValue());
	}

	public final Scheme getScheme() {
		String value = getValue(Property.scheme, defaultsOptions.getSchemeAsString());
		Scheme result = getSchemeByValue(value);
		return result == null ? ColorSchemesDefaultsOptions.DEFAULT_SCHEME : result;
	}

	public final void setFillAlpha(double fillAlpha) {
		setValue(Property.fillAlpha, fillAlpha);
	}

	public final double getFillAlpha() {
		return getValue(Property.fillAlpha, defaultsOptions.getFillAlpha());
	}

	public final void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

	public final boolean isReverse() {
		return getValue(Property.reverse, defaultsOptions.isReverse());
	}
	
	private Scheme getSchemeByValue(String value) {
		if (value.startsWith(Brewer.CATEGORY)) {
			return getSchemeByCategory(Brewer.class, value);
		} else if (value.startsWith(Office.CATEGORY)) {
			return getSchemeByCategory(Office.class, value);
		} else if (value.startsWith(Tableau.CATEGORY)) {
			return getSchemeByCategory(Tableau.class, value);
		}
		return null;
	}

	private <T extends Scheme> Scheme getSchemeByCategory(Class<T> schemeClass, String value) {
		if (schemeClass != null && schemeClass.isEnum()) {
			for (Scheme scheme : schemeClass.getEnumConstants()) {
				if (scheme.getValue().equalsIgnoreCase(value)) {
					return scheme;
				}
			}
		}
		return null;
	}

}
