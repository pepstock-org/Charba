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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Extends a JavaScript object container for all entities which need the options instance to store the configuration of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class ConfigurationContainer<T extends NativeObjectContainer> extends ChartContainer {

	// options instance
	private T configuration;

	/**
	 * Creates the chart configuration object with the chart instance
	 * 
	 * @param chart chart instance
	 */
	protected ConfigurationContainer(IsChart chart) {
		super(chart);
	}

	/**
	 * Creates the chart configuration object with the chart instance and the options
	 * 
	 * @param chart chart instance
	 * @param configuration options instance to store the configuration of chart.
	 */
	public ConfigurationContainer(IsChart chart, T configuration) {
		super(chart);
		// checks if configuration is consistent
		if (configuration == null) {
			// if not exception
			throw new IllegalArgumentException("Configuration argument is null");
		}
		this.configuration = configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	protected final void setConfiguration(T configuration) {
		this.configuration = configuration;
	}

	/**
	 * Returns the configuration element.
	 * 
	 * @return the configuration element.
	 */
	protected final T getConfiguration() {
		return configuration;
	}

	/**
	 * Merge a native object into this one with a specific property name.<br>
	 * This is used by plugins implementation (native java script ones) when they are not using the standard way to add plugin configuration into options of chart.
	 * 
	 * @param source native object container to add
	 * @param property property name
	 */
	public final void merge(NativeObjectContainer source, String property) {
		Merger.get().merge(getConfiguration(), source, property);
	}

	/**
	 * Returns the JSON representation of the object.
	 * 
	 * @return the JSON representation of the object.
	 */
	public final String toJSON() {
		return configuration.toJSON();
	}

}