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

import org.pepstock.charba.client.impl.plugins.HtmlLegendOptionsFactory.HtmlLegendBuilderDefaultsOptionsFactory;

/**
 * This plugin implements a HTML legend in order to give more flexibility to who needs to customize the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegend extends CharbaPluginContainer {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbahtmllegend";
	/**
	 * The factory to create options for plugin.
	 */
	public static final HtmlLegendOptionsFactory FACTORY = new HtmlLegendOptionsFactory();
	// factory instance to read the options from default global
	static final HtmlLegendBuilderDefaultsOptionsFactory DEFAULTS_FACTORY = new HtmlLegendBuilderDefaultsOptionsFactory();
	// singleton instance
	private static final HtmlLegend INSTANCE = new HtmlLegend();
	// instance of the plugin
	private final HtmlLegendPlugin pluginInstance = new HtmlLegendPlugin();

	/**
	 * To avoid any instantiation
	 */
	private HtmlLegend() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static HtmlLegend get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.CharbaPluginContainer#getPluginInstance()
	 */
	@Override
	HtmlLegendPlugin getPluginInstance() {
		return pluginInstance;
	}

}