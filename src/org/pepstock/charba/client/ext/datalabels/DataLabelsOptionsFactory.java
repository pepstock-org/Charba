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
package org.pepstock.charba.client.ext.datalabels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.DefaultChartsLifecycleListener;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.data.Dataset;

import jsinterop.annotations.JsPackage;

/**
 * Factory to get the options (form chart, form dataset or from default global ones) related to DATALABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DataLabelsOptionsFactory extends DefaultChartsLifecycleListener implements NativeObjectContainerFactory<DataLabelsOptions> {

	// cache of options in order to return the already existing options
	// K = options id, V = plugin options
	private static final Map<Integer, DataLabelsOptions> OPTIONS = new HashMap<>();

	/**
	 * To avoid any instantiation. Use the static reference into {@link DataLabelsPlugin#FACTORY}.<br>
	 * Adds itself as charts life cycle listener to manage the cache of data labels options, in order to clean the instances
	 * when the charts will be destroy.
	 */
	DataLabelsOptionsFactory() {
		// adds itself as charts life cycle listener
		Charts.addLifecycleListener(this);
	}

	/**
	 * Registers new DATALABELS plugin options into a map, in order to return a right object instance, mainly because the plugin
	 * options can contain callbacks and references to be maintained.
	 * 
	 * @param options DATALABELS plugin options to be stored
	 */
	void registerOptions(DataLabelsOptions options) {
		// adds to cache
		OPTIONS.put(options.getId(), options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChartsLifecycleListener#onAfterInit(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
		// checks if there is a data labels options as GLOBAL
		if (Defaults.get().getGlobal().getPlugins().hasOptions(DataLabelsPlugin.ID)) {
			DataLabelsOptions options = Defaults.get().getGlobal().getPlugins().getOptions(DataLabelsPlugin.ID, this);
			// gets references
			List<String> references = options.getReferences();
			// checks if references has global
			if (!references.contains(JsPackage.GLOBAL)) {
				// otherwise adds it
				references.add(JsPackage.GLOBAL);
			}
		}
		// checks if there is a data labels options as CHART GLOBAL
		if (Defaults.get().getOptions(chart.getType()).getPlugins().hasOptions(DataLabelsPlugin.ID)) {
			DataLabelsOptions options = Defaults.get().getOptions(chart.getType()).getPlugins().getOptions(DataLabelsPlugin.ID, this);
			// gets references
			List<String> references = options.getReferences();
			// checks if references has global (used also for GLOBAL options for chart type)
			if (!references.contains(JsPackage.GLOBAL)) {
				// otherwise adds it
				references.add(JsPackage.GLOBAL);
			}
		}
		// gets the data labels options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(DataLabelsPlugin.ID)) {
			DataLabelsOptions options = chart.getOptions().getPlugins().getOptions(DataLabelsPlugin.ID, this);
			// gets references
			List<String> references = options.getReferences();
			// checks if has got the reference to the chart
			if (!references.contains(chart.getId())) {
				// adds it
				references.add(chart.getId());
			}
		}
		// gets the data labels options from chart datasets, if there
		for (Dataset dataset : chart.getData().getDatasets()) {
			if (dataset.hasOptions(DataLabelsPlugin.ID)) {
				DataLabelsOptions options = dataset.getOptions(DataLabelsPlugin.ID, this);
				// gets references
				List<String> references = options.getReferences();
				// checks if has got the reference to the chart
				if (!references.contains(chart.getId())) {
					// adds it
					references.add(chart.getId());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChartsLifecycleListener#onBeforeDestroy(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onBeforeDestroy(AbstractChart<?, ?> chart) {
		// gets the data labels options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(DataLabelsPlugin.ID)) {
			DataLabelsOptions options = chart.getOptions().getPlugins().getOptions(DataLabelsPlugin.ID, this);
			// gets references
			List<String> references = options.getReferences();
			// removes the reference to chart and checks if empty
			if (references.remove(chart.getId()) && references.isEmpty()) {
				// removes from cache
				OPTIONS.remove(options.getId());
			}
		}
		// gets the data labels options from chart datasets, if there
		for (Dataset dataset : chart.getData().getDatasets()) {
			if (dataset.hasOptions(DataLabelsPlugin.ID)) {
				DataLabelsOptions options = dataset.getOptions(DataLabelsPlugin.ID, this);
				// gets references
				List<String> references = options.getReferences();
				// removes the reference to chart and checks if empty
				if (references.remove(chart.getId()) && references.isEmpty()) {
					// removes from cache
					OPTIONS.remove(options.getId());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public DataLabelsOptions create(NativeObject nativeObject) {
		// gets the option id
		int optionsId = Id.get(DataLabelsOptions.Property._charbaOptionsId, nativeObject);
		// if cached, MUST BE ALWAYS cached
		if (OPTIONS.containsKey(optionsId)) {
			// returns the instance
			return OPTIONS.get(optionsId);
		}
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		return new DataLabelsOptions();
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class DataLabelsDefaultsOptionsFactory implements NativeObjectContainerFactory<DataLabelsDefaultsOptions> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public DataLabelsDefaultsOptions create(NativeObject nativeObject) {
			// creates the default global option by native object
			return new DataLabelsDefaultsOptions(nativeObject);
		}

	}

}
