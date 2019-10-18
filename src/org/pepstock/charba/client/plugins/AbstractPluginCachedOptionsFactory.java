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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pepstock.charba.client.AbstractChartsLifecycleListener;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.enums.PluginOptionsScope;

/**
 * Factory to get the plugin options (form chart, from datasets or from default global ones) related to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractPluginCachedOptionsFactory<T extends AbstractPluginCachedOptions> extends AbstractChartsLifecycleListener implements NativeObjectContainerFactory<T> {

	// cache of options in order to return the already existing options
	// K = options id, V = plugin options
	private static final Map<Integer, AbstractPluginCachedOptions> OPTIONS = new HashMap<>();
	// plugin id
	private final String pluginId;

	/**
	 * Adds itself as charts life cycle listener to manage the cache of plugin options, in order to clean the instances when the
	 * charts will be destroy.
	 * 
	 * @param pluginId plugin id
	 */
	protected AbstractPluginCachedOptionsFactory(String pluginId) {
		// checks plugin id
		PluginIdChecker.check(pluginId);
		// stores plugin id
		this.pluginId = pluginId;
		// adds itself as charts life cycle listener
		Charts.addLifecycleListener(this);
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
	 * Registers new plugin options into a map, in order to return a right object instance, mainly because the plugin options
	 * can contain callbacks and references to be maintained.
	 * 
	 * @param options plugin options to be stored
	 */
	void registerOptions(AbstractPluginCachedOptions options) {
		// adds to cache
		OPTIONS.put(options.getId(), options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChartsLifecycleListener#onAfterInit(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public final void onAfterInit(IsChart chart) {
		// checks if there is a plugin options as GLOBAL
		manageGlobalOptions();
		// checks if there is a plugin options as CHART GLOBAL
		manageGlobalChartOptions(chart);
		// gets the plugin options from chart options, if there
		manageChartOptions(chart);
		// gets the plugin options from chart datasets, if there
		manageDatasetsOptions(chart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChartsLifecycleListener#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public final void onBeforeDestroy(IsChart chart) {
		// gets the plugin options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = chart.getOptions().getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.OBJECT.equals(type)) {
				// gets object
				T options = chart.getOptions().getPlugins().getOptions(pluginId, this);
				// unregisters it from the chart
				unregister(options, chart.getId());
			} else if (ObjectType.ARRAY.equals(type)) {
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
	 * Register the id (global or chart id) to the options.
	 * 
	 * @param options plugin options instance
	 * @param tag tag to add to the options
	 */
	private void register(AbstractPluginCachedOptions options, String tag) {
		// gets references
		List<String> references = options.getReferences();
		// checks if has got the reference to the chart
		if (!references.contains(tag)) {
			// adds it
			references.add(tag);
		}
	}

	/**
	 * Unregister the id (global or chart id) from the options.
	 * 
	 * @param options plugin options instance
	 * @param tag tag to remove from the options
	 */
	private void unregister(AbstractPluginCachedOptions options, String tag) {
		// gets references
		List<String> references = options.getReferences();
		// remove tag
		references.remove(tag);
		// removes the reference to chart and checks if empty
		if (references.isEmpty()) {
			// removes from cache
			OPTIONS.remove(options.getId());
			// gets inner options
			Collection<AbstractPluginCachedOptions> innerOptionsCollection = options.getInnerOptions();
			// checks if consistent
			if (innerOptionsCollection != null && !innerOptionsCollection.isEmpty()) {
				// scans all inner options
				for (AbstractPluginCachedOptions innerOptions : innerOptionsCollection) {
					// unregister recursively
					unregister(innerOptions, tag);
				}
			}
		}
	}

	/**
	 * Unregisters existing plugin options at global or chart type level to avoid to have in cache unused options, created only
	 * reading the existing options.<br>
	 * This is what happens with DATALABELS plugin which add ALWAYS a default options into global options.
	 * 
	 * @param scope scope of the options
	 */
	private void unregisterGlobal(PluginOptionsScope scope) {
		// scans by iterator all entries of cache
		// uses an iterator because if there is any reference, needs to remove the entry
		// inside the scan
		for (Iterator<Entry<Integer, AbstractPluginCachedOptions>> iterator = OPTIONS.entrySet().iterator(); iterator.hasNext();) {
			// gets NEXT
			Entry<Integer, AbstractPluginCachedOptions> entry = iterator.next();
			// gets options
			AbstractPluginCachedOptions options = entry.getValue();
			// checks if the options is related to the plugin of this factory
			if (pluginId.equalsIgnoreCase(options.getPluginId())) {
				// gets references
				List<String> references = options.getReferences();
				// removes the reference to chart and checks if empty
				if (references.remove(scope.value()) && references.isEmpty()) {
					// removes from cache
					iterator.remove();
				}
			}
		}
	}

	/**
	 * Returns the cached plugin options instance if there is, otherwise returns <code>null</code>.
	 * 
	 * @param nativeObject native object instance to be read
	 * @return the cached plugin options instance if there is, otherwise returns <code>null</code>.
	 */
	protected final AbstractPluginCachedOptions getOptions(NativeObject nativeObject) {
		// gets the option id
		int optionsId = Id.get(AbstractPluginOptions.Property.CHARBA_OPTIONS_ID, nativeObject);
		// if cached, MUST BE ALWAYS cached
		if (OPTIONS.containsKey(optionsId)) {
			// returns the instance
			return OPTIONS.get(optionsId);
		}
		// returns null because
		return null;
	}

	/**
	 * Registers the plugin options from GLOBAL options
	 */
	private void manageGlobalOptions() {
		// checks if there is a plugin options as GLOBAL
		if (Defaults.get().getGlobal().getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = Defaults.get().getGlobal().getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.OBJECT.equals(type)) {
				// unregister previous global options
				unregisterGlobal(PluginOptionsScope.GLOBAL);
				// gets object
				T options = Defaults.get().getGlobal().getPlugins().getOptions(pluginId, this);
				// registers it to global
				register(options, PluginOptionsScope.GLOBAL.value());
			} else if (ObjectType.ARRAY.equals(type)) {
				// unregister previous global options
				unregisterGlobal(PluginOptionsScope.GLOBAL);
				// if here the options are an array of objects
				List<T> optionsList = Defaults.get().getGlobal().getPlugins().getOptionsAsList(pluginId, this);
				// scans the objects
				for (T options : optionsList) {
					// registers it to global
					register(options, PluginOptionsScope.GLOBAL.value());
				}
			}
		}
	}

	/**
	 * Registers the plugin options defined into CHART GLOBAl options.
	 * 
	 * @param chart chart instance
	 */
	private void manageGlobalChartOptions(IsChart chart) {
		// checks if there is a plugin options as CHART GLOBAL
		if (Defaults.get().getOptions(chart.getType()).getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = Defaults.get().getOptions(chart.getType()).getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.OBJECT.equals(type)) {
				// unregister previous options
				unregisterGlobal(PluginOptionsScope.CHART_TYPE);
				// gets object
				T options = Defaults.get().getOptions(chart.getType()).getPlugins().getOptions(pluginId, this);
				// registers it to global
				register(options, PluginOptionsScope.CHART_TYPE.value());
			} else if (ObjectType.ARRAY.equals(type)) {
				// unregister previous options
				unregisterGlobal(PluginOptionsScope.CHART_TYPE);
				// if here the options are an array of objects
				List<T> optionsList = Defaults.get().getOptions(chart.getType()).getPlugins().getOptionsAsList(pluginId, this);
				// scans the objects
				for (T options : optionsList) {
					// registers it to global
					register(options, PluginOptionsScope.CHART_TYPE.value());
				}
			}
		}
	}

	/**
	 * Registers the plugin options defined into CHART options.
	 * 
	 * @param chart chart instance
	 */
	private void manageChartOptions(IsChart chart) {
		// gets the plugin options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(pluginId)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = chart.getOptions().getPlugins().getOptionsType(pluginId);
			// if is single object
			if (ObjectType.OBJECT.equals(type)) {
				// gets object
				T options = chart.getOptions().getPlugins().getOptions(pluginId, this);
				// registers it to the chart
				register(options, chart.getId());
			} else if (ObjectType.ARRAY.equals(type)) {
				// if here the options are an array of objects
				List<T> optionsList = chart.getOptions().getPlugins().getOptionsAsList(pluginId, this);
				// scans the objects
				for (T options : optionsList) {
					// registers it to the chart
					register(options, chart.getId());
				}
			}
		}
	}

	/**
	 * Registers the plugin options defined into DATASET options.
	 * 
	 * @param chart chart instance
	 */
	private void manageDatasetsOptions(IsChart chart) {
		// gets the plugin options from chart datasets, if there
		for (Dataset dataset : chart.getData().getDatasets()) {
			if (dataset.hasOptions(pluginId)) {
				T options = dataset.getOptions(pluginId, this);
				// registers it to the chart
				register(options, chart.getId());
			}
		}
	}

}
