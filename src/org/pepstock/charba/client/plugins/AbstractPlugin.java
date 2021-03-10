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

import org.pepstock.charba.client.Plugin;

/**
 * Implements a plugin interface to help who will create a plugin do not create all methods.<br>
 * The only method not implemented is <code>getId</code> which must implemented.<br>
 * All <code>onBefore*</code> cancelable methods return <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPlugin implements Plugin {

	private final String pluginId;

	/**
	 * Creates the plugin sing the id passed as argument.
	 * 
	 * @param pluginId plugin id to use
	 */
	protected AbstractPlugin(String pluginId) {
		// checks if plugin is consistent
		PluginIdChecker.check(pluginId);
		// stores plugin
		this.pluginId = pluginId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public final String getId() {
		return pluginId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "Plugin [" + getId() + "]";
	}

}
