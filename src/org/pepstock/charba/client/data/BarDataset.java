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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BorderSkippedCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayDoubleArray;
import org.pepstock.charba.client.commons.ArrayDoubleArrayList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.options.Scales;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar, the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarDataset extends HovingFlexDataset implements HasDataPoints, HasOrder {
	// default label
	private static final String DEFAULT_LABEL = Constants.EMPTY_STRING;

	/**
	 * If set to 'flex', the base sample widths are calculated automatically based on the previous and following samples so that they take the full available widths without
	 * overlap. Then, bars are sized using barPercentage and categoryPercentage. There is no gap when the percentage options are 1. This mode generates bars with different widths
	 * when data are not evenly spaced, {@link Integer#MIN_VALUE}.
	 */
	public static final int FLEX_BAR_THICKNESS = Integer.MIN_VALUE;
	// this is the value which must be stored in JS object when flex bar thickness is set
	private static final String FLEX_BAR_THICKNESS_VALUE = "flex";

	private static final double DEFAULT_BAR_PERCENTAGE = 0.9D;

	private static final double DEFAULT_CATEGORY_PERCENTAGE = 0.8D;

	private static final int DEFAULT_BAR_THICKNESS = 0;

	private static final int DEFAULT_MAX_BAR_THICKNESS = 0;

	private static final int DEFAULT_MIN_BAR_LENGTH = 0;
	
	// floating data factory
	private static final FloatingDatatFactory FLOATING_DATA_FACTORY = new FloatingDatatFactory();

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
		BAR_PERCENTAGE("barPercentage"),
		CATEGORY_PERCENTAGE("categoryPercentage"),
		BAR_THICKNESS("barThickness"),
		MAX_BAR_THICKNESS("maxBarThickness"),
		MIN_BAR_LENGTH("minBarLength");

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

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BarDataset() {
		this(ChartType.BAR);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BarDataset(IsDefaultOptions defaultValues) {
		this(ChartType.BAR, defaultValues);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 */
	protected BarDataset(Type type) {
		this(type, null);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 */
	protected BarDataset(Type type, IsDefaultOptions defaultValues) {
		super(type, defaultValues);
		// sets new orderer
		orderer = new Orderer(getNativeObject());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		borderSkippedCallbackProxy.setCallback((contextFunction, context) -> onBorderSkipped(new ScriptableContext(context)));
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
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 */
	public String getXAxisID() {
		return getValue(Property.X_AXIS_ID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * 
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 */
	public String getYAxisID() {
		return getValue(Property.Y_AXIS_ID, Scales.DEFAULT_Y_AXIS_ID);
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to
	 *            each other.
	 */
	public void setBarPercentage(double barPercentage) {
		setValue(Property.BAR_PERCENTAGE, barPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getValue(Property.BAR_PERCENTAGE, DEFAULT_BAR_PERCENTAGE);
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		setValue(Property.CATEGORY_PERCENTAGE, categoryPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.CATEGORY_PERCENTAGE, DEFAULT_CATEGORY_PERCENTAGE);
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths are
	 * calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
		// checks if FLEX value has been set
		if (FLEX_BAR_THICKNESS == barThickness) {
			// flex must be set
			setValue(Property.BAR_THICKNESS, FLEX_BAR_THICKNESS_VALUE);
		} else {
			setValue(Property.BAR_THICKNESS, barThickness);
		}
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths
	 * are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the
	 *         bars are sized using barPercentage and categoryPercentage.
	 */
	public int getBarThickness() {
		// checks if flex has been set
		if (ObjectType.STRING.equals(type(Property.BAR_THICKNESS))) {
			return FLEX_BAR_THICKNESS;
		}
		// if here, is not flex
		return getValue(Property.BAR_THICKNESS, DEFAULT_BAR_THICKNESS);
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		setValue(Property.MAX_BAR_THICKNESS, maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	public int getMaxBarThickness() {
		return getValue(Property.MAX_BAR_THICKNESS, DEFAULT_MAX_BAR_THICKNESS);
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	public void setMinBarLength(int minBarLength) {
		setValue(Property.MIN_BAR_LENGTH, minBarLength);
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	public int getMinBarLength() {
		return getValue(Property.MIN_BAR_LENGTH, DEFAULT_MIN_BAR_LENGTH);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(BarBorderWidth borderWidth) {
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
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped borderskip) {
		// resets callbacks
		setBorderSkipped((BorderSkippedCallback) null);
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
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		// gets object type
		ObjectType type = type(Property.BORDER_SKIPPED);
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type)) {
			// returns is false
			return BorderSkipped.FALSE;
		} else if (ObjectType.FUNCTION.equals(type)) {
			// checks if a callback has been set
			// returns defaults
			return getDefaultValues().getElements().getRectangle().getBorderSkipped();
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), getDefaultValues().getElements().getRectangle().getBorderSkipped());
	}
	
	/**
	 * Returns the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @return a list of floating data or an empty list if the data type is not {@link DataType#FLOATING}.
	 */
	public List<FloatingData> getFloatingData() {
		return getFloatingData(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of floating data or an empty list if the data type is not {@link DataType#FLOATING}.
	 */
	public List<FloatingData> getFloatingData(boolean binding) {
		// checks if is a floating data type
		if (has(InternalProperty.DATA) && DataType.FLOATING.equals(getDataType())) {
			// gets array
			ArrayDoubleArray array = getArrayValue(InternalProperty.DATA);
			// returns floating data
			return ArrayListHelper.list(array, FLOATING_DATA_FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayDoubleArrayList<FloatingData> result = new ArrayDoubleArrayList<>();
			// set value
			setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrEmpty(result));
			// sets data type
			setValue(InternalProperty.CHARBA_DATA_TYPE, DataType.FLOATING);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(FloatingData... floatingData) {
		setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.FLOATING : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of floating data.
	 * 
	 * @param floatingData an array of floating data
	 */
	public void setFloatingData(List<FloatingData> floatingData) {
		setArrayValue(InternalProperty.DATA, ArrayDoubleArray.fromOrNull(floatingData));
		// sets data type checking if the key exists
		setValue(InternalProperty.CHARBA_DATA_TYPE, has(InternalProperty.DATA) ? DataType.FLOATING : DataType.UNKNOWN);
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