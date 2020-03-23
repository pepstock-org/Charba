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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.callbacks.HtmlLegendTextCallback;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (form chart or from default global ones) related to {@link HtmlLegend#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegendOptionsFactory extends AbstractPluginOptionsFactory<HtmlLegendOptions> {

	// maps with all legend text callback for chart
	private static final Map<Integer, HtmlLegendTextCallback> LEGEND_TEXT_CALLBACKS = new HashMap<>();

	/**
	 * To avoid any instantiation. Use the static reference into {@link HtmlLegend#FACTORY}.
	 */
	HtmlLegendOptionsFactory() {
		super(HtmlLegend.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public HtmlLegendOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		HtmlLegendOptions options = null;
		// checks if defaults options are consistent
		if (defaultValues != null) {
			// defaults global options instance
			HtmlLegendDefaultsOptions defaultsOptions = loadGlobalsPluginOptions(defaultValues, HtmlLegend.DEFAULTS_FACTORY);
			// creates the options by the native object and the defaults
			options = new HtmlLegendOptions(nativeObject, defaultsOptions);
		} else {
			// creates the options by the native object and the defaults
			options = new HtmlLegendOptions(nativeObject, HtmlLegendDefaultsOptions.DEFAULTS_INSTANCE);
		}
		// gets charba id
		int charbaId = options.getCharbaId();
		// checks if there is any callback stored into cahce
		if (LEGEND_TEXT_CALLBACKS.containsKey(charbaId)) {
			// sets callback
			options.internalSetLegendTextCallback(LEGEND_TEXT_CALLBACKS.get(charbaId));
		}
		return options;
	}

	/**
	 * Internal methods to store the legend text callback
	 * 
	 * @param optionsId identifier for legend callback
	 * @param callback legend callback instance
	 */
	void store(int optionsId, HtmlLegendTextCallback callback) {
		// checks if legend callback is consistent
		if (callback != null) {
			LEGEND_TEXT_CALLBACKS.put(optionsId, callback);
		} else {
			// if here the callback is not consistent then it reomves it
			LEGEND_TEXT_CALLBACKS.remove(optionsId);
		}
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class HtmlLegendBuilderDefaultsOptionsFactory extends AbstractPluginOptionsFactory<HtmlLegendDefaultsOptions> {

		/**
		 * To avoid any instantiation.
		 */
		HtmlLegendBuilderDefaultsOptionsFactory() {
			super(HtmlLegend.ID);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons. NativeObject,
		 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
		 */
		@Override
		public HtmlLegendDefaultsOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
			// check if native object is consistent
			if (nativeObject != null) {
				// creates the default global option by native object
				HtmlLegendDefaultsOptions options = new HtmlLegendDefaultsOptions(nativeObject);
				// gets charba id
				int charbaId = options.getCharbaId();
				// checks if there is any callback
				if (LEGEND_TEXT_CALLBACKS.containsKey(charbaId)) {
					// sets callback
					options.setLegendTextCallback(LEGEND_TEXT_CALLBACKS.get(charbaId));
				}
				return options;
			}
			return HtmlLegendDefaultsOptions.DEFAULTS_INSTANCE;
		}
	}
}
