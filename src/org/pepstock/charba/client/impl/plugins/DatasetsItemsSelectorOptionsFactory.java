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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Factory to get the options (form chart or from default global ones) related to dataset selection plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetsItemsSelectorOptionsFactory implements NativeObjectContainerFactory<DatasetsItemsSelectorOptions> {

	// factory instance to read the options from default global
	private final DatasetsItemsSelectorDefaultsOptionsFactory defaultsFactory = new DatasetsItemsSelectorDefaultsOptionsFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public DatasetsItemsSelectorOptions create(NativeObject nativeObject) {
		// defaults global options instance
		DatasetsItemsSelectorDefaultsOptions defaultsOptions = null;
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(DatasetsItemsSelector.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(DatasetsItemsSelector.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new DatasetsItemsSelectorDefaultsOptions();
		}
		// creates the options by the native object and the defaults
		return new DatasetsItemsSelectorOptions(nativeObject, defaultsOptions);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	static class DatasetsItemsSelectorDefaultsOptionsFactory implements NativeObjectContainerFactory<DatasetsItemsSelectorDefaultsOptions> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public DatasetsItemsSelectorDefaultsOptions create(NativeObject nativeObject) {
			// creates the default global option by native object
			return new DatasetsItemsSelectorDefaultsOptions(nativeObject);
		}

	}

}
