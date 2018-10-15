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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeDescriptor;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.plugins.InvalidPluginIdException;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Definitions about plugins options. This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
final class NativePlugins extends NativeObject {
	
	/**
	 * Sets if a global plugin must be enabled or not.
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a gloabl plugin.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	@JsOverlay
	final void remove(Key pluginId) {
		// removes configuration if exists
		remove(this, pluginId);
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a gloabl plugin.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	@JsOverlay
	final void setEnabled(Key pluginId) {
		// stores configuration
		defineProperty(this, pluginId, false);
	}
	
	/**
	 * Returns if a global plugin is enabled or not.
	 * @param pluginId plugin id.
	 * @return  <code>false</code> if a gloabl plugin is not enabled otherwise <code>true</code>.
	 * @throws InvalidPluginIdException  occurs if the plugin id is invalid.
	 */
	@JsOverlay
	final boolean isEnabled(Key pluginId) {
		// returns the enablement creating a key by plugin id.
		return !hasProperty(pluginId);
	}
	
	/**
	 * Sets the plugin options. If passed otpions is null, the configuration of plugin will be removed.
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	@JsOverlay
	final <T> void setOptions(Key pluginId, T options) {
		// stores configuration
		defineProperty(this, pluginId, options);
	}
	
	@JsOverlay
	final boolean hasOptions(Key pluginId) {
		return hasProperty(pluginId);
	}

	/**
	 * Returns the plugin options, if exist.
	 * @param pluginId plugin id.
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	@JsOverlay
	final <T> T getOptions(Key pluginId) {
		// returns the configuration creating a key by plugin id.
		NativeDescriptor<T> descriptor = getProperty(this, pluginId);
		if (descriptor != null) {
			return descriptor.getValue();
		} else {
			return null;
		}
	}

}