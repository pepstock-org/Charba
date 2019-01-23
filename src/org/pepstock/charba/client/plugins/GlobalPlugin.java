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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Plugin;

/**
 * Wraps a plugin (GLOBAL plugin), delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class GlobalPlugin extends WrapperPlugin {

	/**
	 * Builds the object with plugin instance
	 * 
	 * @param delegation plugin instance
	 */
	GlobalPlugin(Plugin delegation) {
		super(delegation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.WrapperPlugin#getChart(java.lang.String)
	 */
	@Override
	AbstractChart<?, ?> getChart(String chartId) {
		return Charts.get(chartId);
	}

}
