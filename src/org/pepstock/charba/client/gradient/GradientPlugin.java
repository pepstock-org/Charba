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
package org.pepstock.charba.client.gradient;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.plugins.AbstractExtensionPlugin;
import org.pepstock.charba.client.resources.ResourceName;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Entry point of <a href="https://github.com/kurkle/chartjs-plugin-gradient">GRADIENT plugin</a> to enable the plugin.<br>
 * The GRADIENT plugin enables an easy way to set gradients to the datasets.<br>
 * The supported axes are the types listed in {@link ChartAxisType}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GradientPlugin extends AbstractExtensionPlugin<GradientOptions> {

	/**
	 * Plugin ID <b>"gradient"</b>.
	 */
	public static final String ID = ResourceName.GRADIENT_PLUGIN.value();

	/**
	 * Gradient options factory
	 */
	public static final GradientOptionsFactory FACTORY = new GradientOptionsFactory();
	// injectable resource for plugin
	private static final GradientPluginResource RESOURCE = new GradientPluginResource();
	// singleton instance
	private static final GradientPlugin INSTANCE = new GradientPlugin();
	// flag to now if has been registered
	private static boolean isRegistered = false;

	/**
	 * To avoid any instantiation
	 */
	private GradientPlugin() {
		// do nothing
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	static GradientPlugin get() {
		return INSTANCE;
	}

	/**
	 * Inject the plugin but disables in the all charts waiting for the specific statement for each chart.
	 */
	public static void enable() {
		enable(false);
	}

	/**
	 * Inject the plugin and by the argument decides to enable the plugin to all charts or not.
	 * 
	 * @param enableToAllCharts by <code>true</code> the plugin will be enabled to all charts, otherwise <code>false</code>.
	 */
	public static void enable(boolean enableToAllCharts) {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// injects GRADIENT plugin
		Injector.ensureInjected(RESOURCE);
		// checks if registered
		if (!isRegistered) {
			// if not registered
			JsGradientHelper.get().register();
			// stored the status
			isRegistered = true;
		}
		// set the enabling to all charts at global level
		Defaults.get().getPlugins().setEnabledAllCharts(ID, enableToAllCharts);
	}

}