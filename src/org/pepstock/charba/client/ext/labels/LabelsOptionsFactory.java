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
package org.pepstock.charba.client.ext.labels;

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

import jsinterop.annotations.JsPackage;

/**
 * Factory to get the options (form chart or from default global ones) related to LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LabelsOptionsFactory extends DefaultChartsLifecycleListener implements NativeObjectContainerFactory<LabelsOptions> {

	// cache of options in order to return the already existing options
	// K = options id, V = plugin options
	private static final Map<Integer, LabelsOptions> OPTIONS = new HashMap<>();

	/**
	 * To avoid any instantiation. Use the static reference into {@link LabelsPlugin#FACTORY}.<br>
	 * Adds itself as charts life cycle listener to manage the cache of labels options, in order to clean the instances when the
	 * charts will be destroy.
	 */
	LabelsOptionsFactory() {
		// adds itself as charts life cycle listener
		Charts.addLifecycleListener(this);
	}

	/**
	 * Registers new LABELS plugin options into a map, in order to return a right object instance, mainly because the plugin
	 * options can contain callbacks and references to be maintained.
	 * 
	 * @param options LABELS plugin options to be stored
	 */
	void registerOptions(LabelsOptions options) {
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
		if (Defaults.get().getGlobal().getPlugins().hasOptions(LabelsPlugin.ID)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = Defaults.get().getGlobal().getPlugins().getOptionsType(LabelsPlugin.ID);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				LabelsOptions options = Defaults.get().getGlobal().getPlugins().getOptions(LabelsPlugin.ID, this);
				// registers it to global
				registerChart(options, JsPackage.GLOBAL);
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<LabelsOptions> optionsList = Defaults.get().getGlobal().getPlugins().getOptionsAsList(LabelsPlugin.ID, this);
				// scans the objects
				for (LabelsOptions options : optionsList) {
					// registers it to global
					registerChart(options, JsPackage.GLOBAL);
				}
			}
		}
		// checks if there is a data labels options as CHART GLOBAL
		if (Defaults.get().getOptions(chart.getType()).getPlugins().hasOptions(LabelsPlugin.ID)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = Defaults.get().getOptions(chart.getType()).getPlugins().getOptionsType(LabelsPlugin.ID);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				LabelsOptions options = Defaults.get().getOptions(chart.getType()).getPlugins().getOptions(LabelsPlugin.ID, this);
				// registers it to global
				registerChart(options, JsPackage.GLOBAL);
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<LabelsOptions> optionsList = Defaults.get().getOptions(chart.getType()).getPlugins().getOptionsAsList(LabelsPlugin.ID, this);
				// scans the objects
				for (LabelsOptions options : optionsList) {
					// registers it to global
					registerChart(options, JsPackage.GLOBAL);
				}
			}
		}
		// gets the data labels options from chart options, if there
		if (chart.getOptions().getPlugins().hasOptions(LabelsPlugin.ID)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = chart.getOptions().getPlugins().getOptionsType(LabelsPlugin.ID);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				LabelsOptions options = chart.getOptions().getPlugins().getOptions(LabelsPlugin.ID, this);
				// registers it to the chart
				registerChart(options, chart.getId());
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<LabelsOptions> optionsList = chart.getOptions().getPlugins().getOptionsAsList(LabelsPlugin.ID, this);
				// scans the objects
				for (LabelsOptions options : optionsList) {
					// registers it to the chart
					registerChart(options, chart.getId());
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
		if (chart.getOptions().getPlugins().hasOptions(LabelsPlugin.ID)) {
			// gets the object type of options to know if there is an array of options
			ObjectType type = chart.getOptions().getPlugins().getOptionsType(LabelsPlugin.ID);
			// if is single object
			if (ObjectType.Object.equals(type)) {
				// gets object
				LabelsOptions options = chart.getOptions().getPlugins().getOptions(LabelsPlugin.ID, this);
				// unregisters it from the chart
				unregisterChart(options, chart.getId());
			} else if (ObjectType.Array.equals(type)) {
				// if here the options are an array of objects
				List<LabelsOptions> optionsList = chart.getOptions().getPlugins().getOptionsAsList(LabelsPlugin.ID, this);
				// scans the objects
				for (LabelsOptions options : optionsList) {
					// unregisters it from the chart
					unregisterChart(options, chart.getId());
				}
			}
		}
	}

	/**
	 * Register the id (global or chart id) to the options.
	 * 
	 * @param options plugin options instance
	 * @param tag tag to add to the options
	 */
	private void registerChart(LabelsOptions options, String tag) {
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
	private void unregisterChart(LabelsOptions options, String tag) {
		// gets references
		List<String> references = options.getReferences();
		// removes the reference to chart and checks if empty
		if (references.remove(tag) && references.isEmpty()) {
			// removes from cache
			OPTIONS.remove(options.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public LabelsOptions create(NativeObject nativeObject) {
		// gets the option id
		int optionsId = Id.get(LabelsOptions.Property._charbaOptionsId, nativeObject);
		// if cached, MUST BE ALWAYS cached
		if (OPTIONS.containsKey(optionsId)) {
			// returns the instance
			return OPTIONS.get(optionsId);
		}
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		return new LabelsOptions();
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class LabelsDefaultsOptionsFactory implements NativeObjectContainerFactory<LabelsDefaultsOptions> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public LabelsDefaultsOptions create(NativeObject nativeObject) {
			// creates the default global option by native object
			return new LabelsDefaultsOptions(nativeObject);
		}

	}

}
