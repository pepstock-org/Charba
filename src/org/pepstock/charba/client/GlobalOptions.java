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
import org.pepstock.charba.client.defaults.globals.DefaultOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.options.Font;
import org.pepstock.charba.client.options.Options;
import org.pepstock.charba.client.options.Plugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Default global options (maps the java script object chart.defaults.global).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GlobalOptions extends Options {

	// instance to store the global options as default
	private final IsDefaultScaledOptions defaultOptions;
	// plugins wrapper needed to maintain the original plugins defaults
	private final GlobalPlugins plugins;

	/**
	 * Creates the object with the native object which maps the java script object chart.defaults.global.
	 * 
	 * @param nativeObject native object which maps the java script object chart.defaults.global
	 */
	GlobalOptions(NativeObject nativeObject) {
		// uses the CHART.JS default options as default one
		super(DefaultOptions.SCOPE, DefaultsBuilder.get().getOptions(), nativeObject);
		// stores default
		this.defaultOptions = new DefaultGlobalOptions(this);
		// checks if has got the locale
		if (!has(Options.CommonProperty.LOCALE)) {
			// sets locale getting for locale instance
			setLocale(CLocale.getDefault());
		}
		// stores the plugins wrapper
		this.plugins = new GlobalPlugins(new ChartEnvelop<>(super.getPlugins()));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.Options#getPlugins()
	 */
	@Override
	public Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Extends the options element to configure plugins in order to request to the plugin by its options to merge the original defaults, otherwise the defaults of plugin will be
	 * lost.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class GlobalPlugins extends Plugins {
		
		private ChartEnvelop<String> envelop = new ChartEnvelop<String>(DefaultOptions.SCOPE);

		/**
		 * Creates the object getting the original instance of {@link Plugins}} in the {@link Options}, in order to override the <b>setOptions</b> method to force the merge.
		 * 
		 * @param envelop envelop which is containing the plugins element
		 */
		private GlobalPlugins(ChartEnvelop<Plugins> envelop) {
			super(envelop);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.Plugins#setOptions(org.pepstock.charba.client.plugins.AbstractPluginOptions)
		 */
		@Override
		public <T extends AbstractPluginOptions> void setOptions(T options) {
			// checks if argument is consistent
			if (options != null) {
				// informs the options that they are being stored as defaults
				options.applyingDefaults(envelop);
				// stores the data
				super.setOptions(options);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.Plugins#setOptions(java.lang.String, org.pepstock.charba.client.plugins.AbstractPluginOptions)
		 */
		@Override
		public <T extends AbstractPluginOptions> void setOptions(String pluginId, T options) {
			// checks if argument is consistent
			if (options != null) {
				// informs the options that they are being stored as defaults
				options.applyingDefaults(envelop);
				// stores the data
				super.setOptions(pluginId, options);
			}
		}
	}

}
