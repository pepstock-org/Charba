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

import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.plugins.PluginContainer;
import org.pepstock.charba.client.plugins.PluginsEnvelop;

/**
 * Common class for Charba out of the box plugins.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class CharbaPluginContainer implements PluginContainer {

	/**
	 * Returns the plugin instance to add to the chart configuration.
	 * 
	 * @return the plugin instance to add to the chart configuration
	 */
	abstract Plugin getPluginInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.PluginContainer#loadPlugin(org.pepstock.charba.client.plugins.PluginsEnvelop)
	 */
	@Override
	public final void loadPlugin(PluginsEnvelop<Plugin> envelop) {
		// gets plugin instance
		Plugin pluginInstance = getPluginInstance();
		// checks of envelop is consistent
		if (envelop != null && pluginInstance != null) {
			// loads plugin
			envelop.setContent(pluginInstance);
		}
	}

}
