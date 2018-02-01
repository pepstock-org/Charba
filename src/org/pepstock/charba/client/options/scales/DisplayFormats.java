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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;

/**
 * Determines display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DisplayFormats extends JavaScriptObjectContainer {

	/**
	 * Empty constructor to reduce its visibility
	 */
	DisplayFormats() {
	}

	/**
	 * Sets the plugin options. If passed otpions is null, the configuration of plugin will be removed.
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	/**
	 * 
	 * @param key 
	 * @param format
	 * @see org.pepstock.charba.client.enums.TimeUnit 
	 */
	public void setDisplayFormat(TimeUnit key, String format){
		// stores configuration
		setValue(key, format);
	}

	/**
	 * Returns the plugin options, if exist.
	 * @param pluginId plugin id.
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public String getDisplayFormat(TimeUnit key){
		// returns the configuration creating a key.
		return getValue(key, key.getDefaultFormat());
	}

}