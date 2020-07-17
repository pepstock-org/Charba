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
public enum DefaultPlugin implements Key
{
	/**
	 * CHART.JS plugin to manage the legend.
	 */
	LEGEND("legend", null),
	/**
	 * CHART.JS plugin to manage color filling on the chart.
	 */
	FILLER("filler", null),
	/**
	 * CHART.JS plugin to manage the title.
	 */
	TITLE("title", null),
	/**
	 * CHART.JS plugin to manage the tooltips.
	 */
	TOOLTIP("tooltip", "tooltips");

	// name value of property
	private final String value;
	// name value of property for options
	private final Key propertyName;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param propertyName name of the property used inside the options for plugin defaults.
	 */
	private DefaultPlugin(String value, String propertyName) {
		this.value = value;
		this.propertyName = propertyName == null ? this : Key.create(propertyName);
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
	 * Returns the name of the property used inside the options for plugin defaults.
	 * 
	 * @return the name of the property used inside the options for plugin defaults
	 */
	public Key getPropertyName() {
		return propertyName;
	}

	/**
	 * Returns <code>true</code> if the argument is equals to a default plugin id.<br>
	 * The {@link DefaultPlugin#FILLER} is not considered a default plugin because does not have a specific namespace into options.
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
	 * Returns <code>true</code> if the argument is equals to a default plugin id.<br>
	 * The {@link DefaultPlugin#FILLER} is not considered a default plugin because does not have a specific namespace into options.
	 * 
	 * @param pluginId the plugin id to check
	 * @return <code>true</code> if the argument is equals to a default plugin id
	 */
	public static boolean is(String pluginId) {
		// filler is not considered a default plugin because does not have element into options for configuration
		return !DefaultPlugin.FILLER.value.equalsIgnoreCase(pluginId) && Key.hasKeyByValue(values(), pluginId);		
	}

}
