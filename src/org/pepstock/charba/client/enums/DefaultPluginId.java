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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Contains the GLOBAL plugin IDs of the default CHART.JS plugins, provided out of the box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum DefaultPluginId implements Key
{
	/**
	 * CHART.JS plugin to manage the legend.
	 */
	LEGEND("legend"),
	/**
	 * CHART.JS plugin to manage color filling on the chart.
	 */
	FILLER("filler"),
	/**
	 * CHART.JS plugin to be used with line charts to automatically decimate data at the start of the chart lifecycle.
	 */
	DECIMATION("decimation"),
	/**
	 * CHART.JS plugin to manage the title.
	 */
	TITLE("title"),
	/**
	 * CHART.JS plugin to manage the tooltips.
	 */
	TOOLTIP("tooltip");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DefaultPluginId(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default plugin id.<br>
	 * The {@link DefaultPluginId#FILLER} is not considered a default plugin because does not have a specific namespace in the options.
	 * 
	 * @param pluginId the plugin id to check
	 * @return <code>true</code> if the argument is equals to a default plugin id
	 */
	public static boolean is(Key pluginId) {
		// checks if plugin is is value
		if (Key.isValid(pluginId)) {
			// invokes the checking
			return is(pluginId.value());
		}
		// if here the argument is null
		// then always false
		return false;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default plugin id.
	 * 
	 * @param pluginId the plugin id to check
	 * @return <code>true</code> if the argument is equals to a default plugin id
	 */
	public static boolean is(String pluginId) {
		return Key.hasKeyByValue(values(), pluginId);
	}

}
