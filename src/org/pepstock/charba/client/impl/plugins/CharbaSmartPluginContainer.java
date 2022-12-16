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

import org.pepstock.charba.client.plugins.PluginsEnvelop;
import org.pepstock.charba.client.plugins.SmartPlugin;
import org.pepstock.charba.client.plugins.SmartPluginContainer;

/**
 * Common class for Charba out of the box plugins.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class CharbaSmartPluginContainer implements SmartPluginContainer {

	/**
	 * Returns the smart plugin instance to add to the chart configuration.
	 * 
	 * @return the smart plugin instance to add to the chart configuration
	 */
	abstract SmartPlugin getPluginInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.SmartPluginContainer#loadPlugin(org.pepstock.charba.client.plugins.PluginsEnvelop)
	 */
	@Override
	public void loadPlugin(PluginsEnvelop<SmartPlugin> envelop) {
		// gets plugin instance
		SmartPlugin pluginInstance = getPluginInstance();
		// checks of envelop is consistent
		if (envelop != null && pluginInstance != null) {
			// loads plugin
			envelop.setContent(pluginInstance);
		}
	}

}