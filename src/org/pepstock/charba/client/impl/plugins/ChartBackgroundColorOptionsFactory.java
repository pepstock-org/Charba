/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (ONLY for chart and not global options) related to {@link ChartBackgroundColor#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartBackgroundColorOptionsFactory extends AbstractPluginOptionsFactory<ChartBackgroundColorOptions> {

	/**
	 * To avoid any instantiation. Use the static reference in the {@link ChartBackgroundColor#FACTORY}.
	 */
	ChartBackgroundColorOptionsFactory() {
		super(ChartBackgroundColor.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public ChartBackgroundColorOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		// checks if defaults options are consistent
		if (defaultValues != null) {
			// defaults global options instance
			ChartBackgroundColorOptions defaultOptions = loadDefaultsPluginOptions(defaultValues, ChartBackgroundColor.DEFAULTS_FACTORY);
			// creates the options by the native object and the defaults
			return new ChartBackgroundColorOptions(defaultOptions, nativeObject);
		}
		// creates the options by the native object and the defaults
		return new ChartBackgroundColorOptions(ChartBackgroundColorDefaultOptions.INSTANCE, nativeObject);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static final class ChartBackgroundColorDefaultsOptionsFactory extends AbstractPluginOptionsFactory<ChartBackgroundColorOptions> {

		/**
		 * To avoid any instantiation.
		 */
		ChartBackgroundColorDefaultsOptionsFactory() {
			super(ChartBackgroundColor.ID);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
		 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
		 */
		@Override
		public ChartBackgroundColorOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
			return new ChartBackgroundColorOptions(ChartBackgroundColorDefaultOptions.INSTANCE, nativeObject);
		}
	}

}