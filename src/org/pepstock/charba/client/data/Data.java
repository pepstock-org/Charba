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

import java.util.List;

import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.CanvasObject;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ConfigurationLoader;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * CHART.JS entity object to configure the data options of a chart.<br>
 * It contains labels and datasets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Data extends NativeObjectContainer implements ConfigurationElement {

	// maintains the list of datasets because needs to preserve the dataset type
	private final ArrayObjectContainerList<Dataset> currentDatasets = new ArrayObjectContainerList<>();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABELS("labels"),
		DATASETS("datasets"),
		X_LABELS("xLabels"),
		Y_LABELS("yLabels");

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
		Labels internalLabels = Labels.build();
		// loads
		internalLabels.load(labels);
		// sets labels
		setLabels(internalLabels);
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setLabels(Labels labels) {
		// checks if argument is consistent
		if (labels != null && !labels.getArray().isEmpty()) {
			setArrayValue(Property.LABELS, labels.getArray());
		}
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
		if (has(Property.LABELS)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.LABELS);
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
		Labels internalLabels = Labels.build();
		// loads
		internalLabels.load(labels);
		// sets X labels
		setXLabels(internalLabels);
	}

	/**
	 * Sets the labels for X axes of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setXLabels(Labels labels) {
		// checks if argument is consistent
		if (labels != null && !labels.getArray().isEmpty()) {
			setArrayValue(Property.X_LABELS, labels.getArray());
		}
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
		if (has(Property.X_LABELS)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.X_LABELS);
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
		Labels internalLabels = Labels.build();
		// loads
		internalLabels.load(labels);
		// sets Y labels
		setYLabels(internalLabels);
	}

	/**
	 * Sets the labels for Y axes of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setYLabels(Labels labels) {
		// checks if argument is consistent
		if (labels != null && !labels.getArray().isEmpty()) {
			setArrayValue(Property.Y_LABELS, labels.getArray());
		}
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
		if (has(Property.Y_LABELS)) {
			// gets array
			ArrayMixedObject array = getArrayValue(Property.Y_LABELS);
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
			setArrayValue(Property.DATASETS, this.currentDatasets);
		} else {
			// removes the existing property
			removeIfExists(Property.DATASETS);
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
		if (!has(Property.DATASETS) && binding) {
			// sets datasets to native object
			setArrayValue(Property.DATASETS, this.currentDatasets);
		}
		// returns datasets
		return this.currentDatasets;
	}

	/**
	 * Returns a gradient object set as background color for a dataset related to legend item.
	 * 
	 * @param legendItem legend item instance to get the dataset related to.
	 * @return a gradient object or <code>null</code> if not found by legend item
	 */
	public final Gradient retrieveFillStyleAsGradient(LegendItem legendItem) {
		// retrieves dataset by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if dataset is consistent
		if (dataset != null) {
			// checks if dataset is a point fill stroke style
			if (dataset instanceof HasPointFillStrokeStyles) {
				// casts to point fill stroke styles instance
				HasPointFillStrokeStyles fillStrokeStyles = (HasPointFillStrokeStyles) dataset;
				// gets key instance
				Key fillStyleProperty = fillStrokeStyles.getPointFillStyleProperty();
				// checks if key is consistent
				if (Key.isValid(fillStyleProperty)) {
					// search on cache
					Gradient gradient = retrieveGradient(dataset, legendItem, fillStyleProperty);
					// if the value is found
					// means that the dataset has been configured with point fill style
					if (gradient != null) {
						// returns the point file style
						return gradient;
					}
				}
			}
			// retrieves the object
			return retrieveGradient(dataset, legendItem, Dataset.Property.BACKGROUND_COLOR);
		}
		// if here, dataset non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a gradient object set as border color for a dataset related to legend item.
	 * 
	 * @param legendItem legend item instance to get the dataset related to.
	 * @return a gradient object or <code>null</code> if not found by legend item
	 */
	public final Gradient retrieveStrokeStyleAsGradient(LegendItem legendItem) {
		// retrieves dataset by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if dataset is consistent
		if (dataset != null) {
			// checks if dataset is a point fill stroke style
			if (dataset instanceof HasPointFillStrokeStyles) {
				// casts to point fill stroke styles instance
				HasPointFillStrokeStyles fillStrokeStyles = (HasPointFillStrokeStyles) dataset;
				// gets key instance
				Key strokeStyleProperty = fillStrokeStyles.getPointFillStyleProperty();
				// checks if key is consistent
				if (Key.isValid(strokeStyleProperty)) {
					// search on cache
					Gradient gradient = retrieveGradient(dataset, legendItem, strokeStyleProperty);
					// if the value is found
					// means that the dataset has been configured with point stroke style
					if (gradient != null) {
						// returns the point stroke style
						return gradient;
					}
				}
			}
			// retrieves the object
			return retrieveGradient(dataset, legendItem, Dataset.Property.BORDER_COLOR);
		}
		// if here, dataset non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a pattern object set as background color for a dataset related to legend item.
	 * 
	 * @param legendItem legend item instance to get the dataset related to.
	 * @return a pattern object or <code>null</code> if not found by legend item
	 */
	public final Pattern retrieveFillStyleAsPattern(LegendItem legendItem) {
		// retrieves dataset by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if dataset is consistent
		if (dataset != null) {
			// checks if dataset is a point fill stroke style
			if (dataset instanceof HasPointFillStrokeStyles) {
				// casts to point fill stroke styles instance
				HasPointFillStrokeStyles fillStrokeStyles = (HasPointFillStrokeStyles) dataset;
				// gets key instance
				Key fillStyleProperty = fillStrokeStyles.getPointFillStyleProperty();
				// checks if key is consistent
				if (Key.isValid(fillStyleProperty)) {
					// search on cache
					Pattern pattern = retrievePattern(dataset, legendItem, fillStyleProperty);
					// if the value is found
					// means that the dataset has been configured with point fill style
					if (pattern != null) {
						// returns the point file style
						return pattern;
					}
				}
			}
			// retrieves the object
			return retrievePattern(dataset, legendItem, Dataset.Property.BACKGROUND_COLOR);
		}
		// if here, dataset non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a pattern object set as border color for a dataset related to legend item.
	 * 
	 * @param legendItem legend item instance to get the dataset related to.
	 * @return a pattern object or <code>null</code> if not found by legend item
	 */
	public final Pattern retrieveStrokeStyleAsPattern(LegendItem legendItem) {
		// retrieves dataset by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if dataset is consistent
		if (dataset != null) {
			// checks if dataset is a point fill stroke style
			if (dataset instanceof HasPointFillStrokeStyles) {
				// casts to point fill stroke styles instance
				HasPointFillStrokeStyles fillStrokeStyles = (HasPointFillStrokeStyles) dataset;
				// gets key instance
				Key strokeStyleProperty = fillStrokeStyles.getPointFillStyleProperty();
				// checks if key is consistent
				if (Key.isValid(strokeStyleProperty)) {
					// search on cache
					Pattern pattern = retrievePattern(dataset, legendItem, strokeStyleProperty);
					// if the value is found
					// means that the dataset has been configured with point stroke style
					if (pattern != null) {
						// returns the point stroke style
						return pattern;
					}
				}
			}
			// retrieves the object
			return retrievePattern(dataset, legendItem, Dataset.Property.BORDER_COLOR);
		}
		// if here, dataset non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a dataset instance by legend item locator, dataset index and index.
	 * 
	 * @param legendItem legend item instance to get the dataset related to.
	 * @return a dataset instance or <code>null</code> if not found by legend item
	 */
	public final Dataset retrieveDataset(LegendItem legendItem) {
		// checks if legend item is consistent
		if (legendItem != null) {
			// checks if dataset index is the locator
			// and the index is less than size of datasets
			if (legendItem.getDatasetIndex() != UndefinedValues.INTEGER && currentDatasets.size() > legendItem.getDatasetIndex()) {
				return getDatasets().get(legendItem.getDatasetIndex());
			} else if (legendItem.getIndex() != UndefinedValues.INTEGER && !currentDatasets.isEmpty()) {
				// if here is looking for data index then it uses
				// the first dataset
				return getDatasets().get(0);
			}
		}
		// if here, legend item is not consistent
		// or the locator of legend item is not able to locate any dataset
		return null;
	}

	/**
	 * Returns a dataset instance by tooltip item locator, dataset index and index.
	 * 
	 * @param tooltipItem legend item instance to get the dataset related to.
	 * @return a dataset instance or <code>null</code> if not found by tooltip item
	 */
	public final Dataset retrieveDataset(TooltipItem tooltipItem) {
		// checks if tooltip item is consistent
		// and if dataset index is the locator
		// and the dataset index is less than size of datasets
		if (tooltipItem != null && tooltipItem.getDatasetIndex() != UndefinedValues.INTEGER && currentDatasets.size() > tooltipItem.getDatasetIndex()) {
			return getDatasets().get(tooltipItem.getDatasetIndex());
		}
		// if here, tooltip item is not consistent
		// or the locator of tooltip item is not able to locate any dataset
		return null;
	}

	/**
	 * Returns a gradient stored into dataset as color to apply into chart.
	 * 
	 * @param dataset dataset instance
	 * @param legendItem legend item instance to get the dataset related to.
	 * @param property type of color to search
	 * @return a gradient instance or <code>null</code> if not found by legend item
	 */
	private Gradient retrieveGradient(Dataset dataset, LegendItem legendItem, Key property) {
		// search on callbacks cache
		Gradient gradient = dataset.getCallbackGradient(property, legendItem.getDatasetIndex(), legendItem.getIndex());
		// retrieves the object
		return retrieveObject(dataset, dataset.getGradientsContainer(), legendItem, property, gradient);
	}

	/**
	 * Returns a pattern stored into dataset as color to apply into chart.
	 * 
	 * @param dataset dataset instance
	 * @param legendItem legend item instance to get the dataset related to.
	 * @param property type of color to search
	 * @return a pattern instance or <code>null</code> if not found by legend item
	 */
	private Pattern retrievePattern(Dataset dataset, LegendItem legendItem, Key property) {
		// search on callbacks cache
		Pattern pattern = dataset.getCallbackPattern(property, legendItem.getDatasetIndex(), legendItem.getIndex());
		// retrieves the object
		return retrieveObject(dataset, dataset.getPatternsContainer(), legendItem, property, pattern);
	}

	/**
	 * Returns a canvas object stored into dataset as color to apply into chart.
	 * 
	 * @param dataset dataset instance
	 * @param container container of canvas object where searching the object
	 * @param legendItem legend item instance to get the dataset related to.
	 * @param property type of color to search
	 * @param searchedOnCallback the gradient value got form container of gradients created by a callback
	 * @param <T> type of canvas object to retrieve
	 * @return a canvas object instance or <code>null</code> if not found by legend item
	 */
	private <T extends CanvasObject> T retrieveObject(Dataset dataset, AbstractContainer<T> container, LegendItem legendItem, Key property, T searchedOnCallback) {
		// checks if the canvas object has been found into callbacks cache
		if (searchedOnCallback != null) {
			return searchedOnCallback;
		}
		// checks if arguments are consistent
		// and if the container is not empty for the property to search
		if (Key.isValid(property) && legendItem != null && dataset != null && container != null && container.hasObjects(property)) {
			// gets all list of canvas object
			List<T> objects = container.getObjects(property);
			// the legend item is set by dataset index
			if (legendItem.getDatasetIndex() != UndefinedValues.INTEGER && !objects.isEmpty()) {
				// return the first item
				return objects.get(0);
			} else if (legendItem.getIndex() != UndefinedValues.INTEGER && objects.size() > legendItem.getIndex()) {
				// if here is looking for data index
				return objects.get(legendItem.getIndex());
			}
		}
		// if here, arguments are not consistent
		// or the locator of legend item is not able to locate any dataset
		return null;
	}

	/**
	 * Returns a string representation for all datasets, in JSON format.
	 * 
	 * @return a string representation for all datasets, in JSON format.
	 */
	String getDatasetsAsString() {
		// creates the builder
		StringBuilder sb = new StringBuilder();
		// scans all datasets
		for (Dataset ds : currentDatasets) {
			// adds to builder the data in JSON string format
			sb.append(ds.toFilteredJSON()).append(Constants.LINE_SEPARATOR);
		}
		// returns string of builder
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.Configuration)
	 */
	@Override
	public void load(IsChart chart, Configuration configuration) {
		// checks if chart is consistent
		// configuration will be check into load data method
		if (IsChart.isValid(chart)) {
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

}