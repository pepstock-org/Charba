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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BackgroundColorCallback;
import org.pepstock.charba.client.callbacks.BorderColorCallback;
import org.pepstock.charba.client.callbacks.BorderWidthCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.PluginIdChecker;
import org.pepstock.charba.client.utils.JSON;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class Dataset extends NativeObjectContainer implements HasDataset {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the background color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> backgroundColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// background color callback instance
	private BackgroundColorCallback backgroundColorCallback = null;
	// border color callback instance
	private BorderColorCallback borderColorCallback = null;
	// borderWidth callback instance
	private BorderWidthCallback borderWidthCallback = null;

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// default for hidden property
	private static final boolean DEFAULT_HIDDEN = false;
	// default for order property
	private static final int DEFAULT_ORDER = 0;
	// factory to create data points
	static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();
	// factory to create time series items
	static final TimeSeriesItemFactory TIMESERIES_ITEMS_FACTORY = new TimeSeriesItemFactory();
	// exception message when it's not using data points
	static final String DATA_USAGE_MESSAGE = "Use datapoints instead of data for this chart";
	// exception string message for setting ore getting data
	static final String TIME_SERIES_DATA_USAGE_MESSAGE = "setData and getData methods are not invokable by a time series chart";
	// exception string message for setting ore getting data
	static final String TIME_SERIES_ITEM_AND_DATAPOINT_MESSAGE = "Datapoint is not a time series item";
	// patterns container
	private final PatternsContainer patternsContainer = new PatternsContainer();
	// gradients container
	private final GradientsContainer gradientsContainer = new GradientsContainer();
	// default options values
	private final IsDefaultOptions defaultValues;
	// internal comparator to sort time series items
	private static final Comparator<TimeSeriesItem> COMPARATOR = (TimeSeriesItem o1, TimeSeriesItem o2) -> o1.getTime().compareTo(o2.getTime());

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		LABEL("label"),
		DATA("data"),
		TYPE("type"),
		HIDDEN("hidden"),
		ORDER("order"),
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		// internal key to store a unique id
		CHARBA_ID("_charbaId"),
		// internal key to store patterns and gradients
		CHARBA_PATTERNS("_charbaPatterns"),
		CHARBA_GRADIENTS("_charbaGradients"),
		// internal key to store data type
		CHARBA_DATA_TYPE("_charbaDataType");

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
	 * Creates the dataset using a default, adding patterns and gradients element.
	 * 
	 * @param defaultValues default options
	 */
	protected Dataset(IsDefaultOptions defaultValues) {
		this.defaultValues = defaultValues == null ? Defaults.get().getGlobal() : defaultValues;
		// stores the id based on a counter
		setValue(Property.CHARBA_ID, COUNTER.getAndIncrement());
		// sets the Charba containers into dataset java script configuration
		setValue(Property.CHARBA_PATTERNS, patternsContainer);
		setValue(Property.CHARBA_GRADIENTS, gradientsContainer);
		// sets default data type
		setValue(Property.CHARBA_DATA_TYPE, DataType.UNKNOWN);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		backgroundColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(context, backgroundColorCallback, getDefaultBackgroundColorAsString()));
		// gets value calling callback
		borderColorCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsColor(context, borderColorCallback, getDefaultBorderColorAsString(), false));
		// gets value calling callback
		borderWidthCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(context, borderWidthCallback, getDefaultBorderWidth()).intValue());
	}

	/**
	 * Returns the unique id of datasets.
	 * 
	 * @return the unique id of datasets
	 */
	public final int getId() {
		return getValue(Property.CHARBA_ID, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the data type of datasets.
	 * 
	 * @return the data type of datasets
	 */
	public final DataType getDataType() {
		return getValue(Property.CHARBA_DATA_TYPE, DataType.class, DataType.UNKNOWN);
	}

	/**
	 * Returns the patterns container element.
	 * 
	 * @return the patterns container
	 */
	final PatternsContainer getPatternsContainer() {
		return patternsContainer;
	}

	/**
	 * Returns the gradients container element.
	 * 
	 * @return the gradients container
	 */
	final GradientsContainer getGradientsContainer() {
		return gradientsContainer;
	}

	/**
	 * Returns the default options instance.
	 * 
	 * @return the default options instance.
	 */
	protected final IsDefaultOptions getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is not both a gradient not a pattern, otherwise
	 * <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is not both a gradient not a pattern.
	 */
	final boolean hasColors(Key key) {
		return !getPatternsContainer().hasObjects(key) && !getGradientsContainer().hasObjects(key);
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is a pattern, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is a pattern.
	 */
	final boolean hasPatterns(Key key) {
		return getPatternsContainer().hasObjects(key);
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is a gradient, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is a gradient.
	 */
	final boolean hasGradients(Key key) {
		return getGradientsContainer().hasObjects(key);
	}

	/**
	 * Removes the property key related to the color from pattern and gradient container if color is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingColors(Key key) {
		// remove from patterns
		getPatternsContainer().removeObjects(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * Removes the property key related to the color from dataset object and gradient container if pattern is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingPatterns(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * Removes the property key related to the color from dataset object and pattern container if gradient is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingGradients(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from patterns
		getPatternsContainer().removeObjects(key);
	}

	/**
	 * Removes the property key related to the color from dataset object and pattern and gradient containers if callback is
	 * selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingCallback(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from patterns
		getPatternsContainer().removeObjects(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * It applies all canvas patterns defined into dataset. The canvas pattern needs to be created a context 2d of canvas
	 * therefore must be created by a chart.<br>
	 * This is called by {@link CanvasObjectHandler}.
	 * 
	 * @param chart chart instance
	 * @see CanvasObjectHandler
	 */
	final void applyPatterns(IsChart chart) {
		// checks if there is any pattern
		if (!getPatternsContainer().isEmpty()) {
			// gets all keys of pattern containers.
			// the key is the CHART.JS dataset property to set
			for (Key key : getPatternsContainer().getKeys()) {
				// gets list of all patterns
				List<Pattern> patterns = getPatternsContainer().getObjects(key);
				// creates the list of canvas pattern
				final List<CanvasPattern> canvasPatternsList = new LinkedList<>();
				// scans the patterns
				for (Pattern pattern : patterns) {
					// creates the canvas pattern and adds into list
					canvasPatternsList.add(DatasetCanvasObjectFactory.get().createPattern(chart, pattern));
				}
				// asks to dataset implementation to set the property
				// applying the logic it wants
				applyPattern(key, canvasPatternsList);
			}
		}
	}

	/**
	 * Returns the background color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the background color callback, if set, otherwise <code>null</code>.
	 */
	public final BackgroundColorCallback getBackgroundColorCallback() {
		return backgroundColorCallback;
	}

	/**
	 * Sets the background color callback.
	 * 
	 * @param backgroundColorCallback the background color callback.
	 */
	public final void setBackgroundColor(BackgroundColorCallback backgroundColorCallback) {
		// sets the callback
		this.backgroundColorCallback = backgroundColorCallback;
		// checks if callback is consistent
		if (backgroundColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.BACKGROUND_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.BACKGROUND_COLOR, backgroundColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the border color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border color callback, if set, otherwise <code>null</code>.
	 */
	public final BorderColorCallback getBorderColorCallback() {
		return borderColorCallback;
	}

	/**
	 * Sets the border color callback.
	 * 
	 * @param borderColorCallback the border color callback.
	 */
	public final void setBorderColor(BorderColorCallback borderColorCallback) {
		// sets the callback
		this.borderColorCallback = borderColorCallback;
		// checks if callback is consistent
		if (borderColorCallback != null) {
			// resets previous setting
			resetBeingCallback(Property.BORDER_COLOR);
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_COLOR, borderColorCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_COLOR);
		}
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>.
	 */
	public final BorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public final void setBorderWidth(BorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if callback is consistent
		if (borderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_WIDTH, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_WIDTH);
		}
	}

	/**
	 * Returns the default background color value based on type of chart.
	 * 
	 * @return the default background color value based on type of chart.
	 */
	protected String getDefaultBackgroundColorAsString() {
		// returns the ARC default value because is MOSTLY used
		return getDefaultValues().getElements().getArc().getBackgroundColorAsString();
	}

	/**
	 * Returns the default border color value based on type of chart.
	 * 
	 * @return the default border color value based on type of chart.
	 */
	protected String getDefaultBorderColorAsString() {
		// returns the ARC default value because is MOSTLY used
		return getDefaultValues().getElements().getArc().getBorderColorAsString();
	}

	/**
	 * Returns the default border width value based on type of chart.
	 * 
	 * @return the default border width value based on type of chart.
	 */
	protected int getDefaultBorderWidth() {
		// returns the ARC default value because is MOSTLY used
		return getDefaultValues().getElements().getArc().getBorderWidth();
	}

	/**
	 * Stores the canvas patterns into dataset object by property name passed as key.
	 * 
	 * @param key key property name to use to store canvas patterns into dataset object.
	 * @param canvasPatternsList list of canvas patterns
	 */
	protected abstract void applyPattern(Key key, List<CanvasPattern> canvasPatternsList);

	/**
	 * It applies all canvas gradients defined into dataset. The canvas gradients needs to be created a context 2d of canvas
	 * therefore must be created by a chart.<br>
	 * This is called by {@link CanvasObjectHandler}.
	 * 
	 * @param chart chart instance
	 * @param datasetIndex dataset index related to dataset to be checked
	 * @see CanvasObjectHandler
	 */
	final void applyGradients(IsChart chart, int datasetIndex) {
		// checks if there is any gradient to be created
		if (!getGradientsContainer().isEmpty()) {
			// scans all key of all created gradients
			for (Key key : getGradientsContainer().getKeys()) {
				// gets all gradients for the key
				List<Gradient> gradients = getGradientsContainer().getObjects(key);
				// creates a temporary list of gradients
				List<CanvasGradient> canvasGradientsList = new LinkedList<>();
				// sets internal dataset index
				int index = 0;
				// scans all gradients
				for (Gradient gradient : gradients) {
					// creates gradient and adds to the temporary list
					canvasGradientsList.add(DatasetCanvasObjectFactory.get().createGradient(chart, gradient, datasetIndex, index));
					// increments the index
					index++;
				}
				// asks to dataset implementation to set the property
				// applying the logic it wants
				applyGradient(key, canvasGradientsList);
			}
		}
	}

	/**
	 * Stores the canvas gradients into dataset object by property name passed as key.
	 * 
	 * @param key key property name to use to store canvas gradients into dataset object.
	 * @param canvasGradientsList list of canvas gradients
	 */
	protected abstract void applyGradient(Key key, List<CanvasGradient> canvasGradientsList);

	/**
	 * Sets if the dataset will appear or not.
	 * 
	 * @param hidden if the dataset will appear or not.
	 */
	public void setHidden(boolean hidden) {
		// checks if is hidden
		if (hidden) {
			// then sets it
			setValue(Property.HIDDEN, hidden);
		} else {
			// if is not hidden
			// remove the property
			remove(Property.HIDDEN);
		}
	}

	/**
	 * Returns if the dataset will appear or not.
	 * 
	 * @return if the dataset will appear or not. Default is <code>false</code>
	 */
	public boolean isHidden() {
		return getValue(Property.HIDDEN, DEFAULT_HIDDEN);
	}

	/**
	 * Sets the drawing order of dataset. Also affects order for stacking, tooltip, and legend.
	 * 
	 * @param order the drawing order of dataset.
	 */
	public void setOrder(int order) {
		// then sets it
		setValue(Property.ORDER, order);
	}

	/**
	 * Returns the drawing order of dataset. Also affects order for stacking, tooltip, and legend.
	 * 
	 * @return the drawing order of dataset
	 */
	public int getOrder() {
		return getValue(Property.ORDER, DEFAULT_ORDER);
	}

	/**
	 * Sets the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @param label the label for the dataset which appears in the legend and tooltips.
	 */
	public void setLabel(String label) {
		setValue(Property.LABEL, label);
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	public String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Returns <code>true</code> if dataset must use only data points otherwise <code>false</code>.<br>
	 * The dataset which can set this capabilities, must override this method.
	 * 
	 * @return <code>true</code> if dataset must use only data points otherwise <code>false</code>
	 */
	boolean mustUseDataPoints() {
		return false;
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values an array of numbers
	 */
	public void setData(double... values) {
		// checks if it can use data as double
		if (mustUseDataPoints()) {
			// if not, exception
			throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
		}
		// set value. If null, removes key and then..
		setArrayValue(Property.DATA, ArrayDouble.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_DATA_TYPE, has(Property.DATA) ? DataType.NUMBERS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values list of numbers.
	 */
	public void setData(List<Double> values) {
		// checks if it can use data as double
		if (mustUseDataPoints()) {
			// if not, exception
			throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
		}
		// set value. If null, removes key and then..
		setArrayValue(Property.DATA, ArrayDouble.fromOrNull(values));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_DATA_TYPE, has(Property.DATA) ? DataType.NUMBERS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @return list of numbers or an empty list of numbers if the data type is not {@link DataType#NUMBERS}.
	 */
	public List<Double> getData() {
		return getData(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return list of numbers or an empty list of numbers if the data type is not {@link DataType#NUMBERS}.
	 */
	public List<Double> getData(boolean binding) {
		// checks if it can use data as double
		if (mustUseDataPoints()) {
			// if not, exception
			throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
		}
		// checks if is a numbers data type
		if (has(Property.DATA) && DataType.NUMBERS.equals(getDataType())) {
			// returns numbers
			ArrayDouble array = getArrayValue(Property.DATA);
			// returns array
			return ArrayListHelper.list(array);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayDoubleList result = new ArrayDoubleList();
			// set value
			setArrayValue(Property.DATA, ArrayDouble.fromOrEmpty(result));
			// sets data type
			setValue(Property.CHARBA_DATA_TYPE, DataType.NUMBERS);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param factory datapoint object factory
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	final List<DataPoint> getDataPoints(DataPointFactory factory, boolean binding) {
		// checks if is a numbers data type
		if (has(Dataset.Property.DATA) && DataType.POINTS.equals(getDataType())) {
			// gets array
			ArrayObject array = getArrayValue(Dataset.Property.DATA);
			// returns points
			return ArrayListHelper.list(array, factory);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayObjectContainerList<DataPoint> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(Dataset.Property.DATA, ArrayObject.fromOrEmpty(result));
			// sets data type
			setValue(Dataset.Property.CHARBA_DATA_TYPE, DataType.POINTS);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	final void setInternalDataPoints(DataPoint... datapoints) {
		setArrayValue(Property.DATA, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	final void setInternalDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Dataset.Property.DATA, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param factory datapoint object factory
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#POINTS}.
	 */
	final List<TimeSeriesItem> getTimeSeriesItems(TimeSeriesItemFactory factory, boolean binding) {
		// checks if is a numbers data type
		if (has(Dataset.Property.DATA) && DataType.POINTS.equals(getDataType())) {
			// gets array
			ArrayObject array = getArrayValue(Dataset.Property.DATA);
			// returns points
			return ArrayListHelper.list(array, factory);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayObjectContainerList<TimeSeriesItem> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(Dataset.Property.DATA, ArrayObject.fromOrEmpty(result));
			// sets data type
			setValue(Dataset.Property.CHARBA_DATA_TYPE, DataType.POINTS);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series item.
	 * 
	 * @param timeSeriesItems an array of time series item
	 */
	final void setInternalTimeSeriesItems(TimeSeriesItem... timeSeriesItems) {
		// checks if array is consistent
		if (timeSeriesItems != null) {
			Arrays.sort(timeSeriesItems, COMPARATOR);
		}
		setArrayValue(Property.DATA, ArrayObject.fromOrNull(timeSeriesItems));
		// sets data type checking if the key exists
		setValue(Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of time series items.
	 * 
	 * @param timeSeriesItems a list of time series items
	 */
	final void setInternalTimeSeriesItems(List<TimeSeriesItem> timeSeriesItems) {
		// checks if list is consistent
		if (timeSeriesItems != null) {
			Collections.sort(timeSeriesItems, COMPARATOR);
		}
		setArrayValue(Dataset.Property.DATA, ArrayObject.fromOrNull(timeSeriesItems));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.POINTS : DataType.UNKNOWN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasDataset#getDataset()
	 */
	@Override
	public Dataset getDataset() {
		return this;
	}

	/**
	 * Sets the type of dataset based on type of chart.
	 * 
	 * @param type type of dataset.
	 */
	public void setType(Type type) {
		setValue(Property.TYPE, type);
	}

	/**
	 * Returns the type of dataset, based on type of chart.
	 * 
	 * @return type of dataset or <code>null</code> if not set.
	 */
	public final Type getType() {
		// checks if the type has been set
		if (!has(Property.TYPE)) {
			return null;
		}
		// gets string value from java script object
		String value = getValue(Property.TYPE, ChartType.BAR.value());
		// checks if consistent with out of the box chart types
		Type type = Key.getKeyByValue(ChartType.class, value);
		// if not, creates new type being a controller.
		if (type == null) {
			// gets type from controllers
			type = Defaults.get().getControllers().getTypeByString(value);
		}
		return type == null ? ChartType.BAR : type;
	}

	/**
	 * Sets the plugin dataset configuration. If dataset configuration options is null, the configuration of plugin will be
	 * removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if
	 *            exist.
	 * @param <T> type of native object container to store
	 */
	public final <T extends NativeObjectContainer> void setOptions(String pluginId, T options) {
		// if null, removes the configuration
		if (options == null) {
			// removes configuration if exists
			remove(PluginIdChecker.key(pluginId));
		} else {
			// stores configuration
			setValue(PluginIdChecker.key(pluginId), options);
		}
	}

	/**
	 * Checks if there is any dataset configuration for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public final boolean hasOptions(String pluginId) {
		return has(PluginIdChecker.key(pluginId));
	}

	/**
	 * Returns the plugin dataset configuration, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to configure the plugin or <code>null</code> if factory is <code>null</code>.
	 */
	public final <T extends NativeObjectContainer> T getOptions(String pluginId, NativeObjectContainerFactory<T> factory) {
		// checks if factory argument is consistent
		if (factory != null) {
			return factory.create(getValue(PluginIdChecker.key(pluginId)));
		}
		// if here, factory is not consistent
		return null;
	}

	/**
	 * Returns the JSON representation of dataset. This is used y canvas object handler to know if the dataset has been changed.
	 * 
	 * @return JSON representation of dataset
	 */
	String toFilteredJSON() {
		return JSON.stringifyNativeObject(getNativeObject(), -1);
	}

}