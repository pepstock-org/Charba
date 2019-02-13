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
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (form chart, form dataset or from default global ones) related to DATALABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DataLabelsOptionsFactory extends AbstractPluginOptionsFactory<DataLabelsOptions> {

	/**
	 * To avoid any instantiation. Use the static reference into {@link DataLabelsPlugin#FACTORY}.
	 */
	DataLabelsOptionsFactory() {
		super(DataLabelsPlugin.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public DataLabelsOptions create(NativeObject nativeObject) {
		// gets the options checking if cached
		AbstractPluginOptions options = getOptions(nativeObject);
		// checks if consistent and the right class
		if (options instanceof DataLabelsOptions) {
			// cast and returns it
			return (DataLabelsOptions)options;
		}
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		return new DataLabelsOptions();
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class DataLabelsDefaultsOptionsFactory implements NativeObjectContainerFactory<DataLabelsDefaultsOptions> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public DataLabelsDefaultsOptions create(NativeObject nativeObject) {
			// creates the default global option by native object
			return new DataLabelsDefaultsOptions(nativeObject);
		}

	}

}
