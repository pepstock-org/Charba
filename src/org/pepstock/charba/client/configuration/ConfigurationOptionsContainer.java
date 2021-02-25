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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * FIXME Extends a JavaScript object container for all entities which need the options instance to store the configuration of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class ConfigurationOptionsContainer extends ChartContainer {

	private final ConfigurationOptions options;

	/**
	 * Creates the chart configuration object with the chart instance and the options
	 * 
	 * @param options options instance to store the configuration of chart.
	 */
	protected ConfigurationOptionsContainer(ConfigurationOptions options) {
		super(checkAndGet(options).getChart());
		this.options = options;
	}

	/**
	 * Returns the configuration options.
	 * 
	 * @return the configuration options.
	 */
	protected final ConfigurationOptions getOptions() {
		return options;
	}

	/**
	 * Returns the configuration element.
	 * 
	 * @return the configuration element.
	 */
	protected final ExtendedOptions getConfiguration() {
		return options.getConfiguration();
	}

	/**
	 * FIXME
	 * 
	 * @param options
	 * @return
	 */
	private static ConfigurationOptions checkAndGet(ConfigurationOptions options) {
		if (options == null) {
			throw new IllegalArgumentException("Configuration options argument is null");
		}
		return options;
	}

}