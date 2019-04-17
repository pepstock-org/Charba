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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Factory to get the plugin options (form chart, from datasets or from default global ones) related to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractPluginOptionsFactory<T extends AbstractPluginOptions> implements NativeObjectContainerFactory<T> {

	// plugin id
	private final String pluginId;

	/**
	 * Creates the object with plugin ID.
	 * 
	 * @param pluginId plugin id
	 */
	protected AbstractPluginOptionsFactory(String pluginId) {
		// checks plugin id
		PluginIdChecker.check(pluginId);
		// stores plugin id
		this.pluginId = pluginId;
	}

	/**
	 * Returns the plugin id related to this options.
	 * 
	 * @return the plugin id related to this options
	 */
	public final String getPluginId() {
		return pluginId;
	}

	/**
	 * Loads the default plugin options from defaults. If factory, passed as argument, is <code>null</code>, returns <code>null</code>.
	 * 
	 * @param factory factory to load options
	 * @param <G> type of native object container
	 * @return the defaults plugin options or new options instance if not exist. If factory is <code>null</code>, returns <code>null</code>.
	 */
	protected final <G extends NativeObjectContainer> G loadGlobalsPluginOptions(NativeObjectContainerFactory<G> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks if the default global options has been added for the plugin
			if (Defaults.get().getGlobal().getPlugins().hasOptions(pluginId)) {
				// reads the default default global options
				return Defaults.get().getGlobal().getPlugins().getOptions(pluginId, factory);
			} else {
				// if here, no default global option
				// then the plugin will use the static defaults
				return factory.create();
			}
		}
		// if here the factory is not consistent
		// then returns null
		return null;
	}

}
