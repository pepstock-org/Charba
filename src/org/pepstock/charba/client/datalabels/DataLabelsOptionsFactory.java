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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptionsFactory;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (form chart, form dataset or from default global ones) related to {@link DataLabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DataLabelsOptionsFactory extends AbstractPluginCachedOptionsFactory<DataLabelsOptions> {

	/**
	 * To avoid any instantiation. Use the static reference into {@link DataLabelsPlugin#FACTORY}.<br>
	 * Adds itself as charts life cycle listener to manage the cache of data labels options, in order to clean the instances when the charts will be destroy.
	 */
	DataLabelsOptionsFactory() {
		super(DataLabelsPlugin.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public DataLabelsOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		// gets the options checking if cached
		AbstractPluginOptions options = getOptions(nativeObject);
		// checks if consistent and the right class
		if (options instanceof DataLabelsOptions) {
			// cast and returns it
			return (DataLabelsOptions) options;
		}
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		// checks if defaults options are consistent
		if (defaultValues != null) {
			// defaults global options instance
			DefaultsOptions defaultsOptions = loadGlobalsPluginOptions(defaultValues, DataLabelsPlugin.DEFAULTS_FACTORY);
			// creates the options by the native object and the defaults
			return new DataLabelsOptions(defaultsOptions);
		}
		// creates the options by the native object and the defaults
		return new DataLabelsOptions(DefaultsOptions.DEFAULTS_INSTANCE);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class DataLabelsDefaultsOptionsFactory extends AbstractPluginOptionsFactory<DefaultsOptions> {

		/**
		 * To avoid any instantiation.
		 */
		DataLabelsDefaultsOptionsFactory() {
			super(DataLabelsPlugin.ID);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons. NativeObject,
		 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
		 */
		@Override
		public DefaultsOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
			// check if native object is consistent
			if (nativeObject != null) {
				// creates the default global option by native object
				return new DefaultsOptions(nativeObject);
			}
			return DefaultsOptions.DEFAULTS_INSTANCE;
		}

	}

}
