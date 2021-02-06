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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Defaults for plugins options, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartPlugins implements IsDefaultPlugins {

	private final IsDefaultPlugins plugins;

	/**
	 * Creates the object by plugins option element instance.
	 * 
	 * @param plugins plugins option element instance.
	 */
	public DefaultChartPlugins(IsDefaultPlugins plugins) {
		this.plugins = plugins;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPlugins#isEnabled(java.lang.String)
	 */
	@Override
	public boolean isEnabled(String pluginId) {
		return plugins.isEnabled(pluginId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPlugins#hasOptions(java.lang.String)
	 */
	@Override
	public boolean hasOptions(String pluginId) {
		return plugins.hasOptions(pluginId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPlugins#getOptions(java.lang.String, org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory)
	 */
	@Override
	public <T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		return plugins.getOptions(pluginId, factory);
	}

}
