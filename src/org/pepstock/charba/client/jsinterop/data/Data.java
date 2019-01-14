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
package org.pepstock.charba.client.jsinterop.data;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.Configuration;
import org.pepstock.charba.client.jsinterop.ConfigurationElement;
import org.pepstock.charba.client.jsinterop.commons.ArrayMixedObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.jsinterop.commons.ConfigurationLoader;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * CHART.JS entity object to configure the data options of a chart.<br>
 * It contains labels and datasets.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class Data extends NativeObjectContainer implements ConfigurationElement {

	// maintains the list of datasets
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
	 * Creates the objetc with an empty native object
	 */
	public Data() {
		super(new NativeObject());
	}

	/**
	 * Sets the labels of the data
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
	 * Sets the labels of the data
	 * 
	 * @param labels labels object to manage also multi-line labels.
	 */
	public void setLabels(Labels labels) {
		setArrayValue(Property.labels, labels.getArray());
	}

	/**
	 * Returns the labels
	 * 
	 * @return the labels
	 */
	public Labels getLabels() {
		ArrayMixedObject array = getArrayValue(Property.labels);
		return Labels.load(array);
	}

	/**
	 * Sets the labels for X axes of the data
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
	 * Sets the labels for X axes of the data
	 * 
	 * @param labels labels object to manage also multi-line labels.
	 */
	public void setXLabels(Labels labels) {
		setArrayValue(Property.xLabels, labels.getArray());
	}

	/**
	 * Returns the labels for X axes
	 * 
	 * @return the labels for X axes
	 */
	public Labels getXLabels() {
		ArrayMixedObject array = getArrayValue(Property.xLabels);
		return Labels.load(array);
	}

	/**
	 * Sets the labels for Y axes of the data
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
	 * Sets the labels for Y axes of the data
	 * 
	 * @param labels labels object to manage also multi-line labels.
	 */
	public void setYLabels(Labels labels) {
		setArrayValue(Property.yLabels, labels.getArray());
	}

	/**
	 * Returns the labels for Y axes
	 * 
	 * @return the labels for Y axes
	 */
	public Labels getYLabels() {
		ArrayMixedObject array = getArrayValue(Property.yLabels);
		return Labels.load(array);
	}

	/**
	 * Sets a set of datasets for chart
	 * 
	 * @param datasets set of dataset
	 */
	public void setDatasets(Dataset... datasets) {
		// checks if arguments is consistent
		if (datasets != null) {
			// clear buffer
			this.currentDatasets.clear();
			// adds all datasets
			this.currentDatasets.addAll(datasets);
			// sets datasets to native object
			setArrayValue(Property.datasets, this.currentDatasets);
		}
	}

	/**
	 * Returns the list of datasets
	 * 
	 * @return the list of datasets
	 */
	public List<Dataset> getDatasets() {
		return this.currentDatasets;
	}

	/**
	 * Returns a list of string for each datasets, in JSON format.
	 * 
	 * @return a list of string for each datasets, in JSON format
	 */
	public List<String> getDatasetsAsStrings() {
		// creates the result
		List<String> result = new LinkedList<>();
		// scans all datasets
		for (Dataset ds : currentDatasets) {
			// adds to list the data in JSON string format
			result.add(ds.getDataAsString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.ConfigurationElement#load(org.pepstock.charba.client.jsinterop.Configuration)
	 */
	@Override
	public void load(Configuration configuration) {
		ConfigurationLoader.loadData(configuration, this);
	}

}