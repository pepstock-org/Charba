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
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.impl.plugins.ChartPointerOptionsFactory.ChartPointerDefaultsOptionsFactory;

/**
 * This plugin is changing the cursor when mouse over on dataset, title on canvas if a dataset selection, title handler have been defined.
 * 
 * @author Andrea "Stock" Stocchero
 * @see CursorType
 */
public final class ChartPointer extends CharbaPluginContainer {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbacursorpointer";
	/**
	 * The factory to create options for plugin.
	 */
	public static final ChartPointerOptionsFactory FACTORY = new ChartPointerOptionsFactory();
	// singleton instance
	private static final ChartPointer INSTANCE = new ChartPointer();
	// defaults global options factory
	static final ChartPointerDefaultsOptionsFactory DEFAULTS_FACTORY = new ChartPointerDefaultsOptionsFactory();
	// instance of the plugin
	private final ChartPointerPlugin pluginInstance = new ChartPointerPlugin();

	/**
	 * To avoid any instantiation
	 */
	private ChartPointer() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static ChartPointer get() {
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