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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.chart.DefaultGlobalOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.options.Font;
import org.pepstock.charba.client.options.Options;

/**
 * Default global options (maps the java script object chart.defaults.global).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GlobalOptions extends Options {
	
	// instance to store the global options as default
	private final IsDefaultScaledOptions defaultOptions;

	/**
	 * Creates the object with the native object which maps the java script object chart.defaults.global.
	 * 
	 * @param nativeObject native object which maps the java script object chart.defaults.global
	 */
	GlobalOptions(NativeObject nativeObject) {
		// uses the CHART.JS default options as default one
		super(DefaultsBuilder.get().getOptions(), nativeObject);
		// stores default
		this.defaultOptions = new DefaultGlobalOptions(this);
		// checks if has got the locale
		if (!has(Options.CommonProperty.LOCALE)){
			// sets locale getting for locale instance
			setLocale(CLocale.getDefault());
		}
	}

	/**
	 * Returns the font element.<br>
	 * It contains the global defaults for font.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return super.getDefaultsFont();
	}

	/**
	 * Returns the global options as default scaled options.
	 * 
	 * @return the global options as default scaled options
	 */
	public IsDefaultScaledOptions asDefault() {
		return defaultOptions;
	}

}
