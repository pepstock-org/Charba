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
package org.pepstock.charba.client.defaults;

import java.util.List;

import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Interface to define plugins object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultPlugins {

	/**
	 * Returns if a global plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 */
	boolean isEnabled(String pluginId);

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	boolean hasOptions(String pluginId);

	/**
	 * Returns the options type.
	 * 
	 * @param pluginId plugin id.
	 * @return the options type
	 */
	ObjectType getOptionsType(String pluginId);

	/**
	 * Returns the plugin options, if exist. It uses a factory instance to create a native object container.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to configure the plugin or an empty object if not exist. If factory argument is not consistent, <code>null</code> is returned.
	 */
	<T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory);

	/**
	 * Returns the plugin options as list of object containers, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return the plugin options as list of object containers or empty list if not exist.
	 */
	<T extends AbstractPluginOptions> List<T> getOptionsAsList(String pluginId, AbstractPluginOptionsFactory<T> factory);
}