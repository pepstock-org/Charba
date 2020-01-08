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
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (form chart or from default global ones) related to {@link ChartPointer#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartPointerOptionsFactory extends AbstractPluginOptionsFactory<ChartPointerOptions> {

	/**
	 * To avoid any instantiation. Use the static reference into {@link ChartPointer#FACTORY}.
	 */
	ChartPointerOptionsFactory() {
		super(ChartPointer.ID);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject, org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public ChartPointerOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		// checks if defaults options are consistent
		if (defaultValues != null) {
			// defaults global options instance
			ChartPointerDefaultsOptions defaultsOptions = loadGlobalsPluginOptions(defaultValues, ChartPointer.DEFAULTS_FACTORY);
			// creates the options by the native object and the defaults
			return new ChartPointerOptions(nativeObject, defaultsOptions);
		}
		// creates the options by the native object and the defaults
		return new ChartPointerOptions(nativeObject, ChartPointerDefaultsOptions.DEFAULTS_INSTANCE);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class ChartPointerDefaultsOptionsFactory extends AbstractPluginOptionsFactory<ChartPointerDefaultsOptions> {

		/**
		 * To avoid any instantiation.
		 */
		ChartPointerDefaultsOptionsFactory() {
			super(ChartPointer.ID);
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject, org.pepstock.charba.client.defaults.IsDefaultPlugins)
		 */
		@Override
		public ChartPointerDefaultsOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
			// check if native object is consistent
			if (nativeObject != null) {
				// creates the default global option by native object
				return new ChartPointerDefaultsOptions(nativeObject);
			}
			return ChartPointerDefaultsOptions.DEFAULTS_INSTANCE;
		}

	}

}
