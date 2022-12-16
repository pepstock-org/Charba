/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.impl.plugins.ColorSchemesOptionsFactory.ColorSchemesDefaultsOptionsFactory;

/**
 * Default plugin implementation to use color schemes instead the single colors for border and background colors of chart.<br>
 * It enables to pick the color combination for charts from the predefined or custom color schemes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemes extends CharbaPluginContainer {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbacolorschemes";

	/**
	 * Data labels options factory
	 */
	public static final ColorSchemesOptionsFactory FACTORY = new ColorSchemesOptionsFactory();
	// defaults global options factory
	static final ColorSchemesDefaultsOptionsFactory DEFAULTS_FACTORY = new ColorSchemesDefaultsOptionsFactory();
	// singleton instance
	private static final ColorSchemes INSTANCE = new ColorSchemes();
	// instance of the plugin
	private final ColorSchemesPlugin pluginInstance = new ColorSchemesPlugin();

	/**
	 * To avoid any instantiation
	 */
	private ColorSchemes() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static ColorSchemes get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.CharbaPluginContainer#getPluginInstance()
	 */
	@Override
	Plugin getPluginInstance() {
		return pluginInstance;
	}

}