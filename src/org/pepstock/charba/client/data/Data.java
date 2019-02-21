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
package org.pepstock.charba.client.data;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ConfigurationLoader;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * CHART.JS entity object to configure the data options of a chart.<br>
 * It contains labels and datasets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Data extends NativeObjectContainer implements ConfigurationElement {

	// maintains the list of datasets because needs to preserve the dataset type
	private final ArrayObjectContainerList<Dataset> currentDatasets = new ArrayObjectContainerList<Dataset>();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		labels,
		datasets,
		xLabels,
		yLabels
	}

	/**
	 * Creates the object with an empty native object.
	 */
	public Data() {
		// nothing
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels array of labels
	 */
	public void setLabels(String... labels) {
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets labels
		setLabels(l);
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setLabels(Labels labels) {
		setArrayValue(Property.labels, labels.getArray());
	}

	/**
	 * Returns the labels.
	 * 
	 * @return the labels
	 */
	public Labels getLabels() {
		return getLabels(false);
	}

	/**
	 * Returns the labels for axes.
	 * 
	 * @param binding if <code>true</code> binds the new labels into container
	 * @return the labels for axes
	 */
	public Labels getLabels(boolean binding) {
		// checks if there is the property
		if (has(Property.labels)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.labels);
			// returns labels
			return Labels.load(array);
		}
		// if here, no array stored
		// object to return
		Labels result = Labels.build();
		// checks if binding new array
		if (binding) {
			// stores array
			setLabels(result);
		}
		// returns labels
		return result;
	}

	/**
	 * Sets the labels for X axes of the data.
	 * 
	 * @param labels array of labels
	 */
	public void setXLabels(String... labels) {
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets X labels
		setXLabels(l);
	}

	/**
	 * Sets the labels for X axes of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setXLabels(Labels labels) {
		setArrayValue(Property.xLabels, labels.getArray());
	}

	/**
	 * Returns the labels for X axes.
	 * 
	 * @return the labels for X axes
	 */
	public Labels getXLabels() {
		return getXLabels(false);
	}

	/**
	 * Returns the labels for X axes.
	 * 
	 * @param binding if <code>true</code> binds the new labels into container
	 * @return the labels for X axes
	 */
	public Labels getXLabels(boolean binding) {
		// checks if there is the property
		if (has(Property.xLabels)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.xLabels);
			// returns labels
			return Labels.load(array);
		}
		// if here, no array stored
		// object to return
		Labels result = Labels.build();
		// checks if binding new array
		if (binding) {
			// stores array
			setXLabels(result);
		}
		// returns labels
		return result;
	}

	/**
	 * Sets the labels for Y axes of the data.
	 * 
	 * @param labels array of labels
	 */
	public void setYLabels(String... labels) {
		// creates a label object
		Labels l = Labels.build();
		// loads
		l.load(labels);
		// sets Y labels
		setYLabels(l);
	}

	/**
	 * Sets the labels for Y axes of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setYLabels(Labels labels) {
		setArrayValue(Property.yLabels, labels.getArray());
	}

	/**
	 * Returns the labels for Y axes.
	 * 
	 * @return the labels for Y axes
	 */
	public Labels getYLabels() {
		return getYLabels(false);
	}

	/**
	 * Returns the labels for Y axes.
	 * 
	 * @param binding if <code>true</code> binds the new labels into container
	 * @return the labels for Y axes
	 */
	public Labels getYLabels(boolean binding) {
		// checks if there is the property
		if (has(Property.yLabels)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.yLabels);
			// returns labels
			return Labels.load(array);
		}
		// if here, no array stored
		// object to return
		Labels result = Labels.build();
		// checks if binding new array
		if (binding) {
			// stores array
			setYLabels(result);
		}
		// returns labels
		return result;
	}

	/**
	 * Sets a set of datasets for chart. If argument is <code>null</code>, removes all datasets.
	 * 
	 * @param datasets set of dataset. If <code>null</code>, removes all datasets
	 */
	public void setDatasets(Dataset... datasets) {
		// clear buffer
		this.currentDatasets.clear();
		// checks if arguments is consistent
		if (datasets != null) {
			// adds all datasets
			this.currentDatasets.addAll(datasets);
			// sets datasets to native object
			setArrayValue(Property.datasets, this.currentDatasets);
		} else {
			// removes the existing property
			removeIfExists(Property.datasets);
		}
	}

	/**
	 * Returns the list of datasets.
	 * 
	 * @return the list of datasets
	 */
	public List<Dataset> getDatasets() {
		return getDatasets(false);
	}

	/**
	 * Returns the list of datasets.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return the list of datasets
	 */
	public List<Dataset> getDatasets(boolean binding) {
		// there is not the property and the binding is requested
		// then adds array to container
		if (!has(Property.datasets) && binding) {
			// sets datasets to native object
			setArrayValue(Property.datasets, this.currentDatasets);
		}
		// returns datasets
		return this.currentDatasets;
	}

	/**
	 * Returns a list of string for each datasets, in JSON format.
	 * 
	 * @return a list of string for each datasets, in JSON format
	 */
	public List<String> getDatasetsAsString() {
		// creates the result
		List<String> result = new LinkedList<>();
		// scans all datasets
		for (Dataset ds : currentDatasets) {
			// adds to list the data in JSON string format
			result.add(ds.getDataAsString());
		}
		// returns list
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.Configuration)
	 */
	@Override
	public void load(AbstractChart<?, ?> chart, Configuration configuration) {
		// loads data
		ConfigurationLoader.loadData(configuration, this);
		// checks if there is any pattern
		// scans all datasets
		for (Dataset ds : currentDatasets) {
			// checks if dataset has got some patterns
			if (!ds.getPatternsContainer().isEmpty() || !ds.getGradientsContainer().isEmpty()) {
				// if here
				// there are some patterns to load
				// checks if the plugin to apply pattern is already loaded
				if (!chart.getPlugins().has(CanvasObjectHandler.ID)) {
					// adds plugin
					chart.getPlugins().add(new CanvasObjectHandler());
				}
				// if here,
				// plugin is already added to chart
				// it shouldn't happen
				return;
			}
		}
	}

}