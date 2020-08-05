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
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BorderSkippedCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleArray;
import org.pepstock.charba.client.commons.ArrayDoubleArrayList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.options.BarDatasetOptionsHandler;
import org.pepstock.charba.client.options.HasBarDatasetOptions;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar, the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarDataset extends HovingFlexDataset implements HasDataPoints, HasOrder, HasBarDatasetOptions {
	// default label
	private static final String DEFAULT_LABEL = Constants.EMPTY_STRING;

	/**
	 * Floating bars data factory to create {@link FloatingData}s.
	 */
	public static final FloatingDatatFactory FLOATING_BAR_DATA_FACTORY = new FloatingDatatFactory();

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border skipped function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderSkippedCallbackProxy = JsHelper.get().newCallbackProxy();

	// border skipped callback instance
	private BorderSkippedCallback borderSkippedCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		BORDER_SKIPPED("borderSkipped"),
		BORDER_WIDTH("borderWidth"),
		INDEX_AXIS("indexAxis");

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

	// instance or orderer
	private final Orderer orderer;
	// bar options handler instance
	private final BarDatasetOptionsHandler barOptionsHandler;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BarDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public BarDataset(boolean hidden) {
		this(ChartType.BAR, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BarDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public BarDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.BAR, defaultValues, hidden);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BarDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BarDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// sets new orderer and options handler
		this.orderer = new Orderer(getNativeObject());
		this.barOptionsHandler = new BarDatasetOptionsHandler(new DataEnvelop<>(getNativeObject(), true), getDefaultValues().getDatasets());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.borderSkippedCallbackProxy.setCallback((contextFunction, context) -> onBorderSkipped(new ScriptableContext(new DataEnvelop<NativeObject>(context))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasOrder#getOrderer()
	 */
	@Override
	public Orderer getOrderer() {
		return orderer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBarDatasetOptions#getDatasetOptionsHandler()
	 */
	@Override
	public BarDatasetOptionsHandler getDatasetOptionsHandler() {
		return barOptionsHandler;
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	@Override
	public String getLabel() {
		return getValue(Dataset.InternalProperty.LABEL, DEFAULT_LABEL);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on.<br>
	 * 
	 */
	public void setXAxisID(String xAxisID) {
		// checks if is valid scale id
		IsScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on.
	 */
	public void setXAxisID(IsScaleId xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on.<br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#X}.
	 * 
	 * @return the ID of the x axis to plot this dataset on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#X}
	 */
	public IsScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, DefaultScaleId.X);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on.
	 */
	public void setYAxisID(String yAxisID) {
		// checks if is valid scale id
		IsScaleId.checkIfValid(yAxisID);
		// stores
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on.
	 */
	public void setYAxisID(IsScaleId yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. <br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#Y}.
	 * 
	 * @return the ID of the y axis to plot this dataset on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#Y}
	 */
	public IsScaleId getYAxisID() {
		return getValue(Property.Y_AXIS_ID, DefaultScaleId.Y);
	}

	/**
	 * Sets the base axis for the dataset. Use {@link IndexAxis#Y} for horizontal bar.
	 * 
	 * @param indexAxis the base axis for the dataset
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		setValue(Property.INDEX_AXIS, indexAxis);
	}

	/**
	 * Returns the base axis for the dataset.
	 * 
	 * @return the base axis for the dataset
	 */
	public IndexAxis getIndexAxis() {
		return getValue(Property.INDEX_AXIS, IndexAxis.values(), IndexAxis.X);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(BarBorderWidth borderWidth) {
		// FIXME must be indexable
		// stores value
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels. If a callback or an array have been set, returns an empty object.
	 * 
	 * @return list of the stroke width of the bar in pixels. If a callback or an array have been set, returns an empty object
	 */
	public BarBorderWidth getBorderWidthAsItem() {
		// gets object type
		ObjectType type = type(Property.BORDER_WIDTH);
		// checks if borer width has been set by an object
		if (ObjectType.OBJECT.equals(type)) {
			// returns the array
			return new BarBorderWidth(getValue(Property.BORDER_WIDTH));
		}
		// if here, is not a bar border width object
		// then creates new border width
		BarBorderWidth borderWidth = new BarBorderWidth();
		// checks if borer width has been set by an object
		if (ObjectType.NUMBER.equals(type)) {
			// reads number and set to object
			borderWidth.set(getValue(Property.BORDER_WIDTH, getDefaultBorderWidth()));
		}
		// returns the border width object
		return borderWidth;
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderskip) {
		// checks value for border skipped
		// if not false, otherwise ignore it
		if (!borderskip) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, BorderSkipped.FALSE);
		}
	}

	/**
	 * Sets the edges to skip drawing the border for.
	 * 
	 * @param borderskips array of the edges to skip drawing the border for.
	 */
	// FIXME must be indexable
	public void setBorderSkipped(BorderSkipped... borderskips) {
		// resets callbacks
		setBorderSkipped((BorderSkippedCallback) null);
		// checks if the argument is consistent
		if (borderskips != null && borderskips.length > 0) {
			if (borderskips.length == 1) {
				// stores as single value
				setSingleBorderSkipped(borderskips[0]);
			} else {
				// stores as array of values
				setMultiBorderSkipped(borderskips);
			}
		} else {
			// otherwise remove the key
			removeIfExists(Property.BORDER_SKIPPED);
		}
	}

	/**
	 * Sets the edges to skip drawing the border for.
	 * 
	 * @param borderskips list of the edges to skip drawing the border for.
	 */
	public void setBorderSkipped(List<BorderSkipped> borderskips) {
		// checks if list is consistent
		if (borderskips != null && !borderskips.isEmpty()) {
			// invokes the other methods with the array
			setBorderSkipped(borderskips.toArray(new BorderSkipped[0]));
		}
	}
	
	/**
	 * Sets the edges to skip drawing the border for.
	 * 
	 * @param borderskips array of the edges to skip drawing the border for.
	 */
	private void setMultiBorderSkipped(BorderSkipped... borderskips) {
		// creates a mixed array
		ArrayMixedObject array = new ArrayMixedObject();
		// scans all value
		for (BorderSkipped borderskip : borderskips) {
			// checks if setting a false value
			if (BorderSkipped.FALSE.equals(borderskip)) {
				// stores boolean value
				array.push(false);
			} else {
				// otherwise stores the key value
				array.push(borderskip.value());
			}
		}
		// stores into native object
		setArrayValue(Property.BORDER_SKIPPED, array);
	}

	
	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip the edge to skip drawing the border for.
	 */
	private void setSingleBorderSkipped(BorderSkipped borderskip) {
		// checks if setting a false value
		if (BorderSkipped.FALSE.equals(borderskip)) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, false);
		} else {
			// otherwise stores the key value
			setValue(Property.BORDER_SKIPPED, borderskip);
		}
	}

	/**
	 * Returns the edges to skip drawing the border for.
	 * 
	 * @return the edges to skip drawing the border for.
	 */
	public List<BorderSkipped> getBorderSkipped() {
		// gets object type
		ObjectType type = type(Property.BORDER_SKIPPED);
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type)) {
			// returns is false
			return Arrays.asList(BorderSkipped.FALSE);
		} else if (ObjectType.FUNCTION.equals(type)) {
			// checks if a callback has been set
			// returns defaults
			return Arrays.asList(getDefaultValues().getElements().getRectangle().getBorderSkipped());
		} else if (ObjectType.ARRAY.equals(type)) {
			// gets list instance for result
			List<BorderSkipped> result = new LinkedList<>();
			// gets and scans the array
			ArrayMixedObject array = getArrayValue(Property.BORDER_SKIPPED);
			// scans the array
			for (int i=0; i<array.length(); i++) {
				// gets item
				Object value = array.get(i);
				// checks if it is a boolean
				if (value instanceof Boolean) {
					result.add(BorderSkipped.FALSE);
				} else if (value instanceof String){
					// checks if it is a string
					result.add(Key.getKeyByValue(BorderSkipped.values(), (String)value, getDefaultValues().getElements().getRectangle().getBorderSkipped()));
				}
			}
			return result;
		}
		// otherwise returns the enum value as string
		return Arrays.asList(getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), getDefaultValues().getElements().getRectangle().getBorderSkipped()));
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @return a list of floating data or an empty list if the data type is not {@link DataType#ARRAYS}.
	 */
	public List<FloatingData> getFloatingData() {
		return getFloatingData(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of floating data or an empty list if the data type is not {@link DataType#ARRAYS}.
	 */
	public List<FloatingData> getFloatingData(boolean binding) {
		// checks if is a floating data type
		if (has(InternalProperty.DATA) && DataType.ARRAYS.equals(getDataType())) {
			// gets array
			ArrayDoubleArray array = getArrayValue(InternalProperty.DATA);
			// returns floating data
			return ArrayListHelper.list(array, FLOATING_BAR_DATA_FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayDoubleArrayList<FloatingData> result = new ArrayDoubleArrayList<>();
			// set value
			setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrEmpty(result));
			// sets data type
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.ARRAYS);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of arrays of doubles.
	 * 
	 * @param floatingData an array of arrays of doubles.
	 */
	public void setFloatingData(double[][] floatingData) {
		// checks consistency
		if (floatingData != null && floatingData.length > 0) {
			// creates a list of floating data
			List<FloatingData> dataList = new LinkedList<>();
			// scans the array of arrays
			for (int i = 0; i < floatingData.length; i++) {
				// creates the floating bar data by the array
				dataList.add(FLOATING_BAR_DATA_FACTORY.create(ArrayDouble.fromOrEmpty(floatingData[i])));
			}
			// invokes the set method for a list
			setFloatingData(dataList);
		} else {
			// if here the array is not consistent
			// then removes the property
			removeIfExists(InternalProperty.DATA);
			// sets data type as unknown
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.UNKNOWN);
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(FloatingData... floatingData) {
		setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.ARRAYS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(List<FloatingData> floatingData) {
		setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.ARRAYS : DataType.UNKNOWN);
	}

	/**
	 * Returns the border skipped callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border skipped callback, if set, otherwise <code>null</code>.
	 */
	public BorderSkippedCallback getBorderSkippedCallback() {
		return borderSkippedCallback;
	}

	/**
	 * Sets the border skipped callback.
	 * 
	 * @param borderSkippedCallback the border skipped callback to set
	 */
	public void setBorderSkipped(BorderSkippedCallback borderSkippedCallback) {
		// sets the callback
		this.borderSkippedCallback = borderSkippedCallback;
		// checks if callback is consistent
		if (borderSkippedCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_SKIPPED, borderSkippedCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_SKIPPED);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderColorAsString()
	 */
	@Override
	protected String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getRectangle().getBorderWidth();
	}

	/**
	 * Returns an object (boolean or {@link BorderSkipped}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean or {@link BorderSkipped}.
	 */
	private Object onBorderSkipped(ScriptableContext context) {
		// gets value
		BorderSkipped value = ScriptableUtils.getOptionValueAsString(context, borderSkippedCallback);
		BorderSkipped result = value == null ? getDefaultValues().getElements().getRectangle().getBorderSkipped() : value;
		// checks if is boolean
		if (BorderSkipped.FALSE.equals(result)) {
			return false;
		} else {
			// returns the string value
			return result.value();
		}
	}
}