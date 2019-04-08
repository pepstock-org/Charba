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
package org.pepstock.charba.client.impl.charts;

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * The Meter chart allows a number of properties to be specified for each dataset. These are used to set display properties for
 * a specific dataset.<br>
 * The minimum value of data is 0 (see {@link MeterDataset#MINIMUM_VALUE}).<br>
 * The dataset will have always 2 data and setting the color of data, the first is the value color and the second is the empty
 * one.<br>
 * To set the data, is mandatory to use {@link MeterDataset#setValue(double)}) method.
 * 
 * 
 * @author Andrea "Stock" Stocchero
 */
public class MeterDataset extends Dataset {

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "setData method is not invokable by a Meter chart. Use setValue method.";

	/**
	 * Default value color, <b>rgb(140, 214, 16)</b>.
	 */
	public static final IsColor DEFAULT_VALUE_COLOR = new Color(140, 214, 16);

	/**
	 * Default value color as string, <b>rgb(140, 214, 16)</b>.
	 */
	static final String DEFAULT_VALUE_COLOR_AS_STRING = DEFAULT_VALUE_COLOR.toRGBA();

	/**
	 * Default empty color, <b>rgb(234, 234, 234)</b>.
	 */
	public static final IsColor DEFAULT_EMPTY_VALUE_COLOR = new Color(234, 234, 234);

	/**
	 * Default empty color as string, <b>rgb(234, 234, 234)</b>.
	 */
	static final String DEFAULT_EMPTY_VALUE_COLOR_AS_STRING = DEFAULT_EMPTY_VALUE_COLOR.toRGBA();

	/**
	 * Default maximum value is <b>{@value DEFAULT_MAXIMUM_VALUE}</b>.
	 */
	public static final double DEFAULT_MAXIMUM_VALUE = 100D;

	/**
	 * Minimum value is <b>{@value MINIMUM_VALUE}</b>.
	 */
	public static final double MINIMUM_VALUE = 0D;

	private final double max;

	private double value = MINIMUM_VALUE;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_WIDTH("borderWidth"),
		HOVER_BACKGROUND_COLOR("hoverBackgroundColor"),
		HOVER_BORDER_WIDTH("hoverBorderWidth");

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
	 * Creates a dataset setting the maximum value of dataset. It uses the global options has default.
	 * 
	 * @param max maximum value of dataset.
	 */
	public MeterDataset(double max) {
		this(max, Defaults.get().getGlobal());
	}

	/**
	 * Creates a dataset setting the maximum value of dataset and defaults value.
	 * 
	 * @param max maximum value of dataset.
	 * @param defaultValues default options
	 */
	public MeterDataset(double max, IsDefaultOptions defaultValues) {
		super(defaultValues);
		// sets the max value between the max and minimum value
		// max value must be higher than 0
		this.max = Math.max(max, MINIMUM_VALUE);
		// sets default dataset values
		// removing borders
		setArrayValue(Property.BORDER_WIDTH, ArrayInteger.fromOrNull(0, 0));
		setArrayValue(Property.HOVER_BORDER_WIDTH, ArrayInteger.fromOrNull(0, 0));
		// sets the color of datasets.
		setArrayValue(Property.BACKGROUND_COLOR, ArrayString.fromOrNull(DEFAULT_VALUE_COLOR, DEFAULT_EMPTY_VALUE_COLOR));
		// disable hover back ground color
		setArrayValue(Property.HOVER_BACKGROUND_COLOR, ArrayString.fromOrNull(DEFAULT_VALUE_COLOR, DEFAULT_EMPTY_VALUE_COLOR));
	}

	/**
	 * Sets the fill color for value.
	 * 
	 * @param valueColor the fill color for value.
	 */
	public void setColor(IsColor valueColor) {
		setColor(valueColor != null ? valueColor.toRGBA() : DEFAULT_VALUE_COLOR_AS_STRING);
	}

	/**
	 * Sets the fill color for value.
	 * 
	 * @param valueColor the fill color for value.
	 */
	public void setColor(String valueColor) {
		String valueToSet = valueColor != null ? valueColor : DEFAULT_VALUE_COLOR_AS_STRING;
		// creates array reference
		ArrayString array = ArrayString.from(valueToSet, getEmptyColorAsString());
		// stores value
		setArrayValue(Property.BACKGROUND_COLOR, array);
		setArrayValue(Property.HOVER_BACKGROUND_COLOR, array);
	}

	/**
	 * Returns the fill color for value.
	 * 
	 * @return the fill color for value.
	 */
	public String getColorAsString() {
		// returns list of colors
		ArrayString array = getArrayValue(Property.BACKGROUND_COLOR);
		// returns color as string
		return array.get(0);
	}

	/**
	 * Returns the fill color for value.
	 * 
	 * @return the fill color for value.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the fill color for empty sector.
	 * 
	 * @param emptyValueColor the fill color for empty sector.
	 */
	public void setEmptyColor(IsColor emptyValueColor) {
		setEmptyColor(emptyValueColor != null ? emptyValueColor.toRGBA() : DEFAULT_EMPTY_VALUE_COLOR_AS_STRING);
	}

	/**
	 * Sets the fill color for empty sector.
	 * 
	 * @param emptyValueColor the fill color for empty sector.
	 */
	public void setEmptyColor(String emptyValueColor) {
		String valueToSet = emptyValueColor != null ? emptyValueColor : DEFAULT_EMPTY_VALUE_COLOR_AS_STRING;
		ArrayString array = ArrayString.from(getColorAsString(), valueToSet);
		// stores value
		setArrayValue(Property.BACKGROUND_COLOR, array);
		setArrayValue(Property.HOVER_BACKGROUND_COLOR, array);
	}

	/**
	 * Returns the fill color for empty sector.
	 * 
	 * @return the fill color for empty sector.
	 */
	public String getEmptyColorAsString() {
		// returns list of colors
		ArrayString array = getArrayValue(Property.BACKGROUND_COLOR);
		// returns color as string
		return array.get(1);
	}

	/**
	 * Returns the fill color for empty sector.
	 * 
	 * @return the fill color for empty sector.
	 */
	public IsColor getEmptyColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Returns the maximum value of chart.
	 * 
	 * @return the max
	 */
	public final double getMax() {
		return max;
	}

	/**
	 * Returns the value of the chart.
	 * 
	 * @return the value
	 */
	public final double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		// checks the value is greater than minimum and less than maximum
		this.value = Math.max(Math.min(max, value), MINIMUM_VALUE);
		// sets the data
		super.setData(this.value, Math.max(MINIMUM_VALUE, max - value));
	}

	/**
	 * Forces hiding the dataset because tehre are more than 1 datasets into chart
	 */
	void hide() {
		// the dataset is hidden
		super.setHidden(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setType(org.pepstock.charba.client.Type)
	 */
	@Override
	public void setType(Type type) {
		// overrides always with doughnut
		super.setType(ChartType.DOUGHNUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setHidden(boolean)
	 */
	@Override
	public final void setHidden(boolean hidden) {
		// the dataset can not be hidden
		super.setHidden(false);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values an array of numbers
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 * @deprecated setData is not allowed. Use setValue.
	 */
	@Deprecated
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values an array of numbers
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 * @deprecated setData is not allowed. Use setValue.
	 */
	@Deprecated
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyPattern(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyPattern(Key key, List<CanvasPattern> canvasPatternsList) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#applyGradient(org.pepstock.charba.client.commons.Key, java.util.List)
	 */
	@Override
	protected final void applyGradient(Key key, List<CanvasGradient> canvasGradientsList) {
		// do nothing
	}
}