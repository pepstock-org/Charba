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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.DefaultChartsLifecycleListener;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.Dataset;

import jsinterop.annotations.JsPackage;

/**
 * Factory to get the plugin options (form chart, from datasets or from default global ones) related to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractPluginOptionsFactory<T extends AbstractPluginOptions> extends DefaultChartsLifecycleListener implements NativeObjectContainerFactory<T> {

	// cache of options in order to return the already existing options
	// K = options id, V = plugin options
	private final static Map<Integer, AbstractPluginOptions> OPTIONS = new HashMap<>();
	// plugin id
	private final String pluginId;

	/**
	 * Adds itself as charts life cycle listener to manage the cache of plugin options, in order to clean the instances when the
	 * charts will be destroy.
	 * 
	 * @param pluginId plugin id
	 */
	protected AbstractPluginOptionsFactory(String pluginId) {
		// stores plugin id
		this.pluginId = pluginId;
		// adds itself as charts life cycle listener
		Charts.addLifecycleListener(this);
	}

	/**
	 * Registers new plugin options into a map, in order to return a right object instance, mainly because the plugin
	 * options can contain callbacks and references to be maintained.
	 * 
	 * @param options plugin options to be stored
	 */
	void registerOptions(AbstractPluginOptions options) {
		// adds to cache
		OPTIONS.put(options.getId(), options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChartsLifecycleListener#onAfterInit(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public final void onAfterInit(AbstractChart<?, ?> chart) {
		// checks if there is a plugin options as GLOBAL
		if (Defaults.get().getGlobal().getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = Defaults.get().getGlobal().getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				T options = Defaults.get().getGlobal().getPlugins().getOptions(pluginId, this);
				// registers it to global
				if (register(options, JsPackage.GLOBAL)) {
					// overrides the current object
					// if it has been added
					Defaults.get().getGlobal().getPlugins().setOptions(pluginId, options);
				}
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<T> optionsList = Defaults.get().getGlobal().getPlugins().getOptionsAsList(pluginId, this);
				// flag to know if it must be override
				boolean mustBeOverrided = false;
				// scans the objects
				for (T options : optionsList) {
					// registers it to global
					// or if it must be override
					mustBeOverrided = mustBeOverrided || register(options, JsPackage.GLOBAL);
				}
				// checks if the options must be override
				if (mustBeOverrided) {
					// overrides the current object
					// if it has been added
					Defaults.get().getGlobal().getPlugins().setOptions(pluginId, optionsList);
				}
			}
		}
		// checks if there is a plugin options as CHART GLOBAL
		if (Defaults.get().getOptions(chart.getType()).getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = Defaults.get().getOptions(chart.getType()).getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				T options = Defaults.get().getOptions(chart.getType()).getPlugins().getOptions(pluginId, this);
				// registers it to global
				register(options, JsPackage.GLOBAL);
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<T> optionsList = Defaults.get().getOptions(chart.getType()).getPlugins().getOptionsAsList(pluginId, this);
				// scans the objects
				for (T options : optionsList) {
					// registers it to global
					register(options, JsPackage.GLOBAL);
				}
			}
		}
		// gets the plugin options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = chart.getOptions().getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				T options = chart.getOptions().getPlugins().getOptions(pluginId, this);
				// registers it to the chart
				register(options, chart.getId());
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<T> optionsList = chart.getOptions().getPlugins().getOptionsAsList(pluginId, this);
				// scans the objects
				for (T options : optionsList) {
					// registers it to the chart
					register(options, chart.getId());
				}
			}
		}
		// gets the plugin options from chart datasets, if there
		for (Dataset dataset : chart.getData().getDatasets()) {
			if (dataset.hasOptions(pluginId)) {
				T options = dataset.getOptions(pluginId, this);
				// registers it to the chart
				register(options, chart.getId());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChartsLifecycleListener#onBeforeDestroy(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public final void onBeforeDestroy(AbstractChart<?, ?> chart) {
		// gets the plugin options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = chart.getOptions().getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				T options = chart.getOptions().getPlugins().getOptions(pluginId, this);
				// unregisters it from the chart
				unregister(options, chart.getId());
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<T> optionsList = chart.getOptions().getPlugins().getOptionsAsList(pluginId, this);
				// scans the objects
				for (T options : optionsList) {
					// unregisters it from the chart
					unregister(options, chart.getId());
				}
			}
		}
		// gets the plugin options from chart datasets, if there
		for (Dataset dataset : chart.getData().getDatasets()) {
			if (dataset.hasOptions(pluginId)) {
				T options = dataset.getOptions(pluginId, this);
				// unregisters it from the chart
				unregister(options, chart.getId());
			}
		}
	}

	/**
	 * Register the id (global or chart id) to the options and returns if the tag has been added.
	 * 
	 * @param options plugin options instance
	 * @param tag tag to add to the options
	 * @return <code>true</code> if the tag has been added to options otherwise <code>false</code>.
	 */
	private boolean register(T options, String tag) {
		// gets references
		List<String> references = options.getReferences();
		// checks if has got the reference to the chart
		if (!references.contains(tag)) {
			// adds it
			references.add(tag);
			// added
			return true;
		}
		// not added
		return false;
	}

	/**
	 * Unregister the id (global or chart id) from the options.
	 * 
	 * @param options plugin options instance
	 * @param tag tag to remove from the options
	 */
	private void unregister(T options, String tag) {
		// gets references
		List<String> references = options.getReferences();
		// removes the reference to chart and checks if empty
		if (references.remove(tag) && references.isEmpty()) {
			// removes from cache
			OPTIONS.remove(options.getId());
		}
	}

	/**
	 * Returns the cached plugin options instance if there is, otherwise returns <code>null</code>.
	 * 
	 * @param nativeObject native object instance to be read
	 * @return the cached plugin options instance if there is, otherwise returns <code>null</code>.
	 */
	protected final AbstractPluginOptions getOptions(NativeObject nativeObject) {
		// gets the option id
		int optionsId = Id.get(AbstractPluginOptions.Property._charbaOptionsId, nativeObject);
		// if cached, MUST BE ALWAYS cached
		if (OPTIONS.containsKey(optionsId)) {
			// returns the instance
			return OPTIONS.get(optionsId);
		}
		// returns null because
		return null;
	}
}
