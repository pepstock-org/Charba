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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * CHART.JS default values for LEGEND element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultPlugins implements IsDefaultPlugins {

	private static final boolean DEFAULT_HAS_OPTIONS = false;

	/**
	 * To avoid any instantiation
	 */
	DefaultPlugins() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPlugins#isEnabled(java.lang.String)
	 */
	@Override
	public boolean isEnabled(String pluginId) {
		return Defaults.get().getPlugins().isEnabledAllCharts(pluginId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPlugins#hasOptions(java.lang.String)
	 */
	@Override
	public boolean hasOptions(String pluginId) {
		return DEFAULT_HAS_OPTIONS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPlugins#getOptions(java.lang.String, org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory)
	 */
	@Override
	public <T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates a empty options
			return factory.create(null, null);
		}
		// if here factory is not consistent
		return null;
	}

}