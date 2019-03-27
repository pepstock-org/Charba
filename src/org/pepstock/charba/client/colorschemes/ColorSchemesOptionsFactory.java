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
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (form chart or from default global ones) related to LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ColorSchemesOptionsFactory extends AbstractPluginOptionsFactory<ColorSchemesOptions> {

	/**
	 * To avoid any instantiation. Use the static reference into {@link ColorSchemesPlugin#FACTORY}.<br>
	 * Adds itself as charts life cycle listener to manage the cache of labels options, in order to clean the instances when the
	 * charts will be destroy.
	 * 
	 * @param pluginId plugin ID
	 */
	ColorSchemesOptionsFactory(String pluginId) {
		super(pluginId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public ColorSchemesOptions create(NativeObject nativeObject) {
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		return new ColorSchemesOptions(nativeObject);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class ColorSchemesDefaultsOptionsFactory implements NativeObjectContainerFactory<ColorSchemesDefaultsOptions> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public ColorSchemesDefaultsOptions create(NativeObject nativeObject) {
			// creates the default global option by native object
			return new ColorSchemesDefaultsOptions(nativeObject);
		}

	}

}
