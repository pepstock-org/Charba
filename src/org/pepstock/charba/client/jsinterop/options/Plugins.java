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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.plugins.InvalidPluginIdException;
import org.pepstock.charba.client.plugins.PluginIdChecker;

/**
 * Definitions about plugins options. This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins extends BaseModel<BaseOptions<?,?>, Void, NativePlugins> {

	Plugins(BaseOptions<?,?> options, NativePlugins delegated) {
		super(options, null, delegated == null ? new NativePlugins() : delegated);
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a gloabl plugin.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public void setEnabled(String pluginId, boolean enabled) throws InvalidPluginIdException {
		// if null, removes the configuration
		if (enabled){
			// removes configuration if exists
			getDelegated().remove(PluginIdChecker.key(pluginId));
		} else {
			// stores configuration
			getDelegated().setEnabled(PluginIdChecker.key(pluginId));
		}
		// checks if the node is already added to parent
		checkAndAddToParent();
	}
	
	/**
	 * Returns if a global plugin is enabled or not.
	 * @param pluginId plugin id.
	 * @return  <code>false</code> if a gloabl plugin is not enabled otherwise <code>true</code>.
	 * @throws InvalidPluginIdException  occurs if the plugin id is invalid.
	 */
	public boolean isEnabled(String pluginId) throws InvalidPluginIdException{
		return getDelegated().isEnabled(PluginIdChecker.key(pluginId));
	}
	
	/**
	 * Sets the plugin options. If passed otpions is null, the configuration of plugin will be removed.
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public <T> void setOptions(String pluginId, T options) throws InvalidPluginIdException {
		// if null, removes the configuration
		if (options == null){
			// removes configuration if exists
			getDelegated().remove(PluginIdChecker.key(pluginId));
		} else {
			// stores configuration
			getDelegated().setOptions(PluginIdChecker.key(pluginId), options);
		}
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the plugin options, if exist.
	 * @param pluginId plugin id.
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public <T> T getOptions(String pluginId) throws InvalidPluginIdException{
		// returns the configuration creating a key by plugin id.
		return getDelegated().getOptions(PluginIdChecker.key(pluginId));
	}
	
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getPlugins() == null) {
			getParent().getDelegated().setPlugins(getDelegated());
		}
	}

}