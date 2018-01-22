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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.StandardKey;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;
import org.pepstock.charba.client.plugins.PluginIdChecker;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Definitions about plugins options. This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins extends JavaScriptObjectContainer {

	/**
	 * Empty constructor to reduce its visibility
	 */
	Plugins() {
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a gloabl plugin.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public void setEnabled(String pluginId, boolean enabled) throws InvalidPluginIdException {
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// creates a key using plugin ID
		Key key = new StandardKey(pluginId);
		// if null, removes the configuration
		if (enabled){
			// removes configuration if exists
			removeIfExists(key);
		} else {
			// stores configuration
			setValue(key, enabled);
		}
	}
	
	/**
	 * Returns if a global plugin is enabled or not.
	 * @param pluginId plugin id.
	 * @return  <code>false</code> if a gloabl plugin is not enabled otherwise <code>true</code>.
	 * @throws InvalidPluginIdException  occurs if the plugin id is invalid.
	 */
	public boolean isEnabled(String pluginId) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// returns the enablement creating a key by plugin id.
		return getValue(new StandardKey(pluginId), true);
	}
	
	/**
	 * Sets the plugin options. If passed otpions is null, the configuration of plugin will be removed.
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public void setOptions(String pluginId, JavaScriptObject options) throws InvalidPluginIdException {
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// creates a key using plugin ID
		Key key = new StandardKey(pluginId);
		// if null, removes the configuration
		if (options == null){
			// removes configuration if exists
			removeIfExists(key);
		} else {
			// stores configuration
			setValue(key, options);
		}
	}

	/**
	 * Returns the plugin options, if exist.
	 * @param pluginId plugin id.
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public JavaScriptObject getOptions(String pluginId) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		// returns the configuration creating a key by plugin id.
		return getValue(new StandardKey(pluginId));
	}

}