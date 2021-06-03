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

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.GlobalOptions;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.items.Undefined;

/**
 * Abstract plugin options where to set all the configuration needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPluginOptions extends AbstractNode {

	// static counter. Starts from min value of integer
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// plugin id
	private final String pluginId;

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		EVENTS("events"),
		// internal property to set unique id
		CHARBA_OPTIONS_ID("charbaOptionsId");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * Creates new plugin options with plugin ID, using a native object instance.
	 * 
	 * @param pluginId plugin ID
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	protected AbstractPluginOptions(String pluginId, NativeObject nativeObject) {
		this(pluginId, null, null, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param pluginId plugin ID
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractPluginOptions(String pluginId, AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(nativeObject);
		// checks plugin id
		PluginIdChecker.check(pluginId);
		// stores plugin id
		this.pluginId = pluginId;
		// checks if the ID is already set
		if (!has(Property.CHARBA_OPTIONS_ID)) {
			// sets unique id
			StringBuilder sb = new StringBuilder(pluginId);
			// needed for scoping of callbacks
			setValue(Property.CHARBA_OPTIONS_ID, sb.append(Constants.MINUS).append(COUNTER.getAndIncrement()).toString());
		}
	}

	/**
	 * Returns the unique ID of the options.
	 * 
	 * @return the unique ID of the options.
	 */
	public final String getId() {
		return getValue(Property.CHARBA_OPTIONS_ID, Undefined.STRING);
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
	 * Sets the browser events that the plugins should listen to.
	 * 
	 * @param events the browser events that the plugins should listen to.
	 */
	public final void setEvents(Event... events) {
		// sets the array of events
		setArrayValue(Property.EVENTS, ArrayString.fromOrNull(events));
	}

	/**
	 * Returns the browser events that the plugins should listen to.
	 * 
	 * @return the browser events that the plugins should listen to.
	 */
	public final List<Event> getEvents() {
		// retrieves the array
		ArrayString array = getArrayValue(Property.EVENTS);
		// if the array is not consistent returns the default
		return array != null ? ArrayListHelper.list(Event.values(), array) : Collections.unmodifiableList(Defaults.get().getGlobal().getEvents());
	}

	/**
	 * Loads the default plugin options from defaults. If factory, passed as argument, is <code>null</code>, returns <code>null</code>.
	 * 
	 * @param factory factory to load options
	 * @param <T> type of native object container
	 * @return the defaults plugin options or new options instance if not exist. If factory is <code>null</code>, returns <code>null</code>.
	 */
	protected final <T extends AbstractPluginOptions> T loadGlobalsPluginOptions(AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks if the default global options has been added for the plugin
			if (Defaults.get().getGlobal().getPlugins().hasOptions(pluginId)) {
				// reads the default default global options
				return Defaults.get().getGlobal().getPlugins().getOptions(pluginId, factory);
			} else {
				// if here, no default global option
				// then the plugin will use the static defaults
				return factory.create(null, null);
			}
		}
		// if here the factory is not consistent
		// then returns null
		return null;
	}

	/**
	 * This is invoked before storing the plugins options as default.<br>
	 * This is called set the options of plugin are stored as default, see {@link GlobalOptions#getPlugins()}.
	 * 
	 * @param envelop envelop needed to ensure that this public method can be called only by {@link GlobalOptions}
	 */
	public final void applyingDefaults(ChartEnvelop<String> envelop) {
		// checks if envelop id consistent
		if (Envelop.isValid(envelop)) {
			// invokes the apply default method of the options.
			applyingDefaults();
		}
	}

	/**
	 * This is invoked before storing the plugins options as default.
	 */
	protected void applyingDefaults() {
		// do nothing
	}

	/**
	 * Stores this options in the default global plugins options.
	 */
	public void store() {
		// stores itself in the defaults
		Defaults.get().getGlobal().getPlugins().setOptions(this);
	}

	/**
	 * Stores this options in the chart plugins options.
	 * 
	 * @param chart chart instance
	 */
	public final void store(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			// stores itself in the chart configuration
			chart.getOptions().getPlugins().setOptions(this);
		}
	}

	/**
	 * Stores this options in the global chart plugins options.
	 * 
	 * @param type chart type to store options in the global chart options
	 */
	public final void store(Type type) {
		// checks if type is consistent
		if (Type.isValid(type)) {
			// gets chart options by type
			ChartOptions chartOptions = Defaults.get().getOptions(type);
			// stores itself in the global chart options
			chartOptions.getPlugins().setOptions(this);
		}
	}

	/**
	 * Stores this options in the dataset options.
	 * 
	 * @param dataset dataset instance to store options
	 */
	public final void store(Dataset dataset) {
		// checks if type is consistent
		if (dataset != null) {
			// stores itself in the dataset
			dataset.setOptions(this);
		}
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}
