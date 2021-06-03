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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.colors.CanvasObject;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.ConfigurationLoader;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.ActiveDatasetElement;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS entity object to configure the data options of a chart.<br>
 * It contains labels and data sets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Data extends NativeObjectContainer implements ConfigurationElement, HasLabels {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATASETS("datasets"),
		X_LABELS("xLabels"),
		Y_LABELS("yLabels");

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

	// chart instance
	private final IsChart chart;
	// maintains the list of data sets because needs to preserve the data set type
	private final ArrayObjectContainerList<Dataset> currentDatasets = new ArrayObjectContainerList<>();
	// instance of labels option handler
	private final LabelsHandler labelsHandler;
	// flag to disable canvas object handler
	private boolean canvasObjectHandling = true;

	/**
	 * Creates the object with an empty native object.
	 * 
	 * @param envelop envelop containing the chart instance
	 */
	public Data(ChartEnvelop<IsChart> envelop) {
		this.chart = Envelop.checkAndGetIfValid(envelop).getContent();
		// creates the labels option manager
		this.labelsHandler = new LabelsHandler(getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasLabels#getLabelsHandler()
	 */
	@Override
	public LabelsHandler getLabelsHandler() {
		return labelsHandler;
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
	 * @param binding if <code>true</code> binds the new labels in the container
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
	 * @param labels labels object to manage also multiple line labels
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
	 * @param binding if <code>true</code> binds the new labels in the container
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
	 * Sets a set of data sets for chart. If argument is <code>null</code>, removes all data sets.
	 * 
	 * @param datasets set of data set. If <code>null</code>, removes all data sets
	 */
	public void setDatasets(Dataset... datasets) {
		// clear buffer
		this.currentDatasets.clear();
		// checks if arguments is consistent
		if (datasets != null) {
			// adds all data sets
			this.currentDatasets.addAll(datasets);
			// sets data sets to native object
			setArrayValue(Property.DATASETS, this.currentDatasets);
		} else {
			// removes the existing property
			remove(Property.DATASETS);
		}
	}

	/**
	 * Returns the list of data sets.
	 * 
	 * @return the list of data sets
	 */
	public List<Dataset> getDatasets() {
		return getDatasets(false);
	}

	/**
	 * Returns the list of data sets.
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return the list of data sets
	 */
	public List<Dataset> getDatasets(boolean binding) {
		// there is not the property and the binding is requested
		// then adds array to container
		if (!has(Property.DATASETS) && binding) {
			// sets data sets to native object
			setArrayValue(Property.DATASETS, this.currentDatasets);
		}
		// returns data sets
		return this.currentDatasets;
	}

	/**
	 * Returns <code>true</code> if the plugin to manage canvas object (gradients and patterns) has been forcedly disable.<br>
	 * <b>Pay attention</b> that disabling the handler, your data sets configuration with gradients or patterns will e showed with default color.
	 * 
	 * @return <code>true</code> if the plugin to manage canvas object (gradients and patterns) has been forcedly disable
	 */
	public boolean isCanvasObjectHandling() {
		return canvasObjectHandling;
	}

	/**
	 * Sets <code>true</code> if the plugin to manage canvas object (gradients and patterns) have to be forcedly disable.<br>
	 * <b>Pay attention</b> that disabling the handler, your data sets configuration with gradients or patterns will e showed with default color.
	 * 
	 * @param canvasObjectHandling <code>true</code> if the plugin to manage canvas object (gradients and patterns) have to be forcedly disable
	 */
	public void setCanvasObjectHandling(boolean canvasObjectHandling) {
		this.canvasObjectHandling = canvasObjectHandling;
	}

	/**
	 * Creates a list of active elements instances by an array of data set indexes, for visible data sets.
	 * 
	 * @param datasetIndexes an array of data set indexes
	 * @return a list of active elements instances by an array of data set indexes
	 */
	public List<ActiveDatasetElement> createActiveElementsByDatasetIndex(int... datasetIndexes) {
		// creates results
		List<ActiveDatasetElement> result = new ArrayList<>();
		// checks if argument is consistent
		if (datasetIndexes != null && datasetIndexes.length > 0) {
			// normalizes array
			int[] indexes = Arrays.stream(datasetIndexes).distinct().toArray();
			// scans indexes
			for (int i = 0; i < indexes.length; i++) {
				// gets index
				int index = indexes[i];
				// checks if the index is consistent
				if (index >= 0 && index < currentDatasets.size() && chart.isDatasetVisible(index)) {
					// gets data set
					Dataset dataset = currentDatasets.get(index);
					// scans to add active element
					for (int k = 0; k < dataset.getDataCount(); k++) {
						// creates and add active element
						result.add(new ActiveDatasetElement(index, k));
					}
				}
			}
			return result;
		}
		// if here, argument is not consistent
		// then returns an empty list
		return result;
	}

	/**
	 * Creates a list of active elements instances by an array of data indexes, for all visible data sets.
	 * 
	 * @param dataIndexes an array of data indexes
	 * @return a list of active elements instances by an array of data indexes
	 */
	public List<ActiveDatasetElement> createActiveElementsByDataIndex(int... dataIndexes) {
		// creates results
		List<ActiveDatasetElement> result = new ArrayList<>();
		// checks if argument is consistent
		if (dataIndexes != null && dataIndexes.length > 0) {
			// normalizes array
			int[] indexes = Arrays.stream(dataIndexes).distinct().toArray();
			// data set index
			int datasetIndex = 0;
			// scans all data sets
			for (Dataset dataset : currentDatasets) {
				// checks if data set is visible
				if (chart.isDatasetVisible(datasetIndex)) {
					// scans all indexes
					for (int i = 0; i < indexes.length; i++) {
						// gets index
						int index = indexes[i];
						// checks if the index is consistent
						if (index >= 0 && index < dataset.getDataCount()) {
							// creates and add active element
							result.add(new ActiveDatasetElement(datasetIndex, index));
						}
					}
				}
				// increments data set index
				datasetIndex++;
			}
			return result;
		}
		// if here, argument is not consistent
		// then returns an empty list
		return result;
	}

	/**
	 * Returns a gradient object set as background color for a data set related to legend item.
	 * 
	 * @param legendItem legend item instance to get the data set related to.
	 * @return a gradient object or <code>null</code> if not found by legend item
	 */
	public final Gradient retrieveFillStyleAsGradient(LegendItem legendItem) {
		// retrieves data set by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if data set is consistent
		if (dataset != null) {
			// checks if data set is a point fill stroke style
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
					// means that the data set has been configured with point fill style
					if (gradient != null) {
						// returns the point file style
						return gradient;
					}
				}
			}
			// retrieves the object
			return retrieveGradient(dataset, legendItem, Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
		}
		// if here, data set non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a gradient object set as border color for a data set related to legend item.
	 * 
	 * @param legendItem legend item instance to get the data set related to.
	 * @return a gradient object or <code>null</code> if not found by legend item
	 */
	public final Gradient retrieveStrokeStyleAsGradient(LegendItem legendItem) {
		// retrieves data set by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if data set is consistent
		if (dataset != null) {
			// checks if data set is a point fill stroke style
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
					// means that the data set has been configured with point stroke style
					if (gradient != null) {
						// returns the point stroke style
						return gradient;
					}
				}
			}
			// retrieves the object
			return retrieveGradient(dataset, legendItem, Dataset.CanvasObjectProperty.BORDER_COLOR);
		}
		// if here, data set non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a pattern object set as background color for a data set related to legend item.
	 * 
	 * @param legendItem legend item instance to get the data set related to.
	 * @return a pattern object or <code>null</code> if not found by legend item
	 */
	public final Pattern retrieveFillStyleAsPattern(LegendItem legendItem) {
		// retrieves data set by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if data set is consistent
		if (dataset != null) {
			// checks if data set is a point fill stroke style
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
					// means that the data set has been configured with point fill style
					if (pattern != null) {
						// returns the point file style
						return pattern;
					}
				}
			}
			// retrieves the object
			return retrievePattern(dataset, legendItem, Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
		}
		// if here, data set non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a pattern object set as border color for a data set related to legend item.
	 * 
	 * @param legendItem legend item instance to get the data set related to.
	 * @return a pattern object or <code>null</code> if not found by legend item
	 */
	public final Pattern retrieveStrokeStyleAsPattern(LegendItem legendItem) {
		// retrieves data set by legend item
		Dataset dataset = retrieveDataset(legendItem);
		// checks if data set is consistent
		if (dataset != null) {
			// checks if data set is a point fill stroke style
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
					// means that the data set has been configured with point stroke style
					if (pattern != null) {
						// returns the point stroke style
						return pattern;
					}
				}
			}
			// retrieves the object
			return retrievePattern(dataset, legendItem, Dataset.CanvasObjectProperty.BORDER_COLOR);
		}
		// if here, data set non consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a data set instance by legend item locator, data set index and index.
	 * 
	 * @param legendItem legend item instance to get the data set related to.
	 * @return a data set instance or <code>null</code> if not found by legend item
	 */
	public final Dataset retrieveDataset(LegendItem legendItem) {
		// checks if legend item is consistent
		if (legendItem != null) {
			// checks if data set index is the locator
			// and the index is less than size of data sets
			if (Undefined.isNot(legendItem.getDatasetIndex()) && currentDatasets.size() > legendItem.getDatasetIndex()) {
				return getDatasets().get(legendItem.getDatasetIndex());
			} else if (Undefined.isNot(legendItem.getIndex()) && !currentDatasets.isEmpty()) {
				// if here is looking for data index then it uses
				// the first data set
				return getDatasets().get(0);
			}
		}
		// if here, legend item is not consistent
		// or the locator of legend item is not able to locate any dataset
		return null;
	}

	/**
	 * Returns a data set instance by tooltip item locator, data set index and index.
	 * 
	 * @param tooltipItem tooltip item instance to get the data set related to.
	 * @return a data set instance or <code>null</code> if not found by tooltip item
	 */
	public final Dataset retrieveDataset(TooltipItem tooltipItem) {
		// checks if tooltip item is consistent
		// and if data set index is the locator
		// and the data set index is less than size of data sets
		if (tooltipItem != null && Undefined.isNot(tooltipItem.getDatasetIndex()) && currentDatasets.size() > tooltipItem.getDatasetIndex()) {
			return getDatasets().get(tooltipItem.getDatasetIndex());
		}
		// if here, tooltip item is not consistent
		// or the locator of tooltip item is not able to locate any dataset
		return null;
	}

	/**
	 * Returns a data set instance by scriptable context, data set index and index.
	 * 
	 * @param context legend item instance to get the data set related to.
	 * @return a data set instance by scriptable context item or <code>null</code> if not found by scriptable context
	 */
	public final Dataset retrieveDataset(DatasetContext context) {
		// checks if scriptable context is consistent
		// and if data set index is the locator
		// and the data set index is less than size of data sets
		if (context != null && Undefined.isNot(context.getDatasetIndex()) && currentDatasets.size() > context.getDatasetIndex()) {
			return getDatasets().get(context.getDatasetIndex());
		}
		// if here, tooltip item is not consistent
		// or the locator of tooltip item is not able to locate any data set
		return null;
	}

	/**
	 * Returns a gradient stored in the data set as color to apply in the chart.
	 * 
	 * @param dataset data set instance
	 * @param legendItem legend item instance to get the data set related to.
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
	 * Returns a pattern stored in the data set as color to apply in the chart.
	 * 
	 * @param dataset data set instance
	 * @param legendItem legend item instance to get the data set related to.
	 * @param property type of color to search
	 * @return a pattern instance or <code>null</code> if not found by legend item
	 */
	private Pattern retrievePattern(Dataset dataset, LegendItem legendItem, Key property) {
		// search on callback cache
		Pattern pattern = dataset.getCallbackPattern(property, legendItem.getDatasetIndex(), legendItem.getIndex());
		// retrieves the object
		return retrieveObject(dataset, dataset.getPatternsContainer(), legendItem, property, pattern);
	}

	/**
	 * Returns a canvas object stored in the data set as color to apply in the chart.
	 * 
	 * @param dataset data set instance
	 * @param container container of canvas object where searching the object
	 * @param legendItem legend item instance to get the data set related to.
	 * @param property type of color to search
	 * @param searchedOnCallback the gradient value got form container of gradients created by a callback
	 * @param <T> type of canvas object to retrieve
	 * @return a canvas object instance or <code>null</code> if not found by legend item
	 */
	private <T extends CanvasObject> T retrieveObject(Dataset dataset, AbstractContainer<T> container, LegendItem legendItem, Key property, T searchedOnCallback) {
		// checks if the canvas object has been found in the callback cache
		if (searchedOnCallback != null) {
			return searchedOnCallback;
		}
		// checks if arguments are consistent
		// and if the container is not empty for the property to search
		if (Key.isValid(property) && legendItem != null && dataset != null && container != null && container.hasObjects(property)) {
			// gets all list of canvas object
			List<T> objects = container.getObjects(property);
			// the legend item is set by data set index
			if (Undefined.isNot(legendItem.getDatasetIndex()) && !objects.isEmpty()) {
				// return the first item
				return objects.get(0);
			} else if (Undefined.isNot(legendItem.getIndex()) && objects.size() > legendItem.getIndex()) {
				// if here is looking for data index
				return objects.get(legendItem.getIndex());
			}
		}
		// if here, arguments are not consistent
		// or the locator of legend item is not able to locate any data set
		return null;
	}

	/**
	 * Returns a string representation for all data sets, in JSON format.
	 * 
	 * @return a string representation for all data sets, in JSON format.
	 */
	String getDatasetsAsString() {
		// creates the builder
		StringBuilder sb = new StringBuilder();
		// scans all data sets
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
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Configuration)
	 */
	@Override
	public void load(IsChart chart, Configuration configuration) {
		// checks if chart is consistent
		// configuration will be check in the load data method
		if (IsChart.isValid(chart)) {
			// loads data
			ConfigurationLoader.loadData(configuration, this);
			// checks if the canvas object handler has been disable
			if (isCanvasObjectHandling()) {
				// checks if there is any pattern
				// scans all data sets
				for (Dataset dataset : currentDatasets) {
					// checks if data set has got some patterns
					// and then activating the plugin
					if (activateCanvasObjectHandlerPlugin(chart, dataset)) {
						// if here,
						// plugin is already added to chart
						// it shouldn't happen
						return;
					}
				}
			}
		}
	}

	/**
	 * Checks if data et has got patterns or gradient and then add {@link CanvasObjectHandler} plugin to the chart;
	 * 
	 * @param chart chart instance
	 * @param dataset data set to check
	 * @return <code>true</code> if plugin has been added, other wise <code>false</code>
	 */
	private boolean activateCanvasObjectHandlerPlugin(IsChart chart, Dataset dataset) {
		// checks if data set has got some patterns
		if (!dataset.getPatternsContainer().isEmpty() || !dataset.getGradientsContainer().isEmpty()) {
			// if here
			// there are some patterns to load
			// checks if the plugin to apply pattern is already loaded
			if (!chart.getPlugins().has(CanvasObjectHandler.ID)) {
				// adds plugin
				chart.getPlugins().add(CanvasObjectHandler.get());
				// the plugin must be configure
				// here outside of the plugin container one
				// because it must be added and configure after
				// all the others due to the possibility to disable
				// canvas object handling
				CanvasObjectHandler.get().onConfigure(chart);
			}
			// if here,
			// plugin is already added to chart
			// it shouldn't happen
			return true;
		}
		// if here, plugin has not been activated
		return false;
	}

}