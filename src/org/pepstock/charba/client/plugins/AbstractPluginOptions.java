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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Abstract plugin options where to set all the configuration needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPluginOptions extends NativeObjectContainer {

	// static counter. Starts from min value of integer
	private static final AtomicInteger COUNTER = new AtomicInteger(Integer.MIN_VALUE);
	// plugin id
	private final String pluginId;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		// internal property to set unique id
		CHARBA_OPTIONS_ID("_charbaOptionsId");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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
	}

	/**
	 * Creates new plugin options with plugin ID, creating new native options.
	 * 
	 * @param pluginId plugin ID
	 */
	protected AbstractPluginOptions(String pluginId) {
		// creates an empty native object
		this(pluginId, null);
		// sets unique id
		// needed for caching the instances
		setValue(Property.CHARBA_OPTIONS_ID, COUNTER.incrementAndGet());
	}

	/**
	 * Creates new plugin options with plugin ID, using a native object instance.
	 * 
	 * @param pluginId plugin ID
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	protected AbstractPluginOptions(String pluginId, NativeObject nativeObject) {
		super(nativeObject);
		// checks plugin id
		PluginIdChecker.check(pluginId);
		// stores plugin id
		this.pluginId = pluginId;
	}

	/**
	 * Returns the unique ID of the options.
	 * 
	 * @return the unique ID of the options.
	 */
	public final int getId() {
		return getValue(Property.CHARBA_OPTIONS_ID, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the plugin id related to this options.
	 * 
	 * @return the plugin id related to this options
	 */
	public final String getPluginId() {
		return pluginId;
	}

	/**
	 * Loads the default plugin options from defaults. If factory, passed as argument, is <code>null</code>, returns
	 * <code>null</code>.
	 * 
	 * @param factory factory to load options
	 * @param <T> type of native object container
	 * @return the defaults plugin options or new options instance if not exist. If factory is <code>null</code>, returns
	 *         <code>null</code>.
	 */
	protected final <T extends NativeObjectContainer> T loadGlobalsPluginOptions(NativeObjectContainerFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks if the default global options has been added for the plugin
			if (Defaults.get().getGlobal().getPlugins().hasOptions(pluginId)) {
				// reads the default default global options
				return Defaults.get().getGlobal().getPlugins().getOptions(pluginId, factory);
			} else {
				// if here, no default global option
				// then the plugin will use the static defaults
				return factory.create();
			}
		}
		// if here the factory is not consistent
		// then returns null
		return null;
	}

	/**
	 * Stores this options into default global plugins options.
	 */
	public void store() {
		// stores itself into defaults
		Defaults.get().getGlobal().getPlugins().setOptions(pluginId, this);
	}

	/**
	 * Stores this options into chart plugins options.
	 * 
	 * @param chart chart instance
	 */
	public final void store(IsChart chart) {
		// checks if chart is consistent
		if (chart != null) {
			// stores itself into defaults
			chart.getOptions().getPlugins().setOptions(pluginId, this);
		}
	}

}
