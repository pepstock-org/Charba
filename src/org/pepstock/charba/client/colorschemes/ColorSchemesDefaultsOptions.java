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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * It wraps default global options if there are and provides all default values for LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ColorSchemesDefaultsOptions extends NativeObjectContainer {

	static final Scheme DEFAULT_SCHEME = Brewer.Paired12;
	
	private static final String DEFAULT_SCHEME_AS_STRING = DEFAULT_SCHEME.getValue();

	private static final double DEFAULT_FILL_ALPHA = 0.5D;

	private static final boolean DEFAULT_REVERSE = false;

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	ColorSchemesDefaultsOptions() {
		super();
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	ColorSchemesDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	String getSchemeAsString() {
		return getValue(ColorSchemesOptions.Property.scheme, DEFAULT_SCHEME_AS_STRING);
	}

	double getFillAlpha() {
		return getValue(ColorSchemesOptions.Property.fillAlpha, DEFAULT_FILL_ALPHA);
	}

	boolean isReverse() {
		return getValue(ColorSchemesOptions.Property.reverse, DEFAULT_REVERSE);
	}

}
