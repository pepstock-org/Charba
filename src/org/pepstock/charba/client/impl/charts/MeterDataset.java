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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.ArcBorderRadius;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderItemType;
import org.pepstock.charba.client.enums.DefaultTransitionKey;
import org.pepstock.charba.client.items.Undefined;

/**
 * The Meter chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * The minimum value of data is 0 (see {@link MeterDataset#MINIMUM_VALUE}).<br>
 * The dataset will have always 2 data and setting the color of data, the first is the value color and the second is the empty one.<br>
 * To set the data, is mandatory to use {@link MeterDataset#setValue(double)}) method.
 * 
 * 
 * @author Andrea "Stock" Stocchero
 */
public class MeterDataset extends Dataset {

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a meter chart. Use 'setValue' method";

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

	// default border width
	private static final int DEFAULT_BORDER_WIDTH = 0;
	// default border radius
	private static final int DEFAULT_BORDER_RADIUS = 0;
	// default border radius object
	private static final ArcBorderRadius DEFAULT_BORDER_RADIUS_OBJECT = new ArcBorderRadius(DEFAULT_BORDER_RADIUS);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_RADIUS("borderRadius"),
		// internal to map the border radius type
		CHARBA_BORDER_RADIUS_TYPE("charbaBorderRadiusType");

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

	// -------------------------
	// INSTANCES
	// -------------------------

	private final ValueLabel valueLabel;

	private final DescriptionLabel label;

	private double max;

	private double value = MINIMUM_VALUE;

	private double valueMaximumRatio = Undefined.DOUBLE;

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
		this(MeterChart.CONTROLLER_TYPE, max, defaultValues);
	}

	/**
	 * Creates a dataset setting the maximum value of dataset and defaults value and the controller type in case of extension
	 * 
	 * @param type controller type related to the dataset
	 * @param max maximum value of dataset.
	 * @param defaultValues default options
	 */
	protected MeterDataset(ControllerType type, double max, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
		// sets the max value between the max and minimum value
		// max value must be higher than 0
		this.max = Math.max(max, MINIMUM_VALUE);
		// creates labels
		this.valueLabel = new ValueLabel(defaultValues.getFont());
		this.label = new DescriptionLabel(this.valueLabel.getFont());
		// sets default dataset values
		// removing borders
		setArrayValue(Dataset.CommonProperty.BORDER_WIDTH, ArrayInteger.fromOrNull(DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_WIDTH));
		setArrayValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, ArrayInteger.fromOrNull(DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_WIDTH));
		// sets the color of datasets.
		setArrayValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, ArrayString.fromOrNull(DEFAULT_VALUE_COLOR, DEFAULT_EMPTY_VALUE_COLOR));
		// disable hover back ground color
		setArrayValue(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, ArrayString.fromOrNull(DEFAULT_VALUE_COLOR, DEFAULT_EMPTY_VALUE_COLOR));
		// disables animation active mode
		getTransitions().create(DefaultTransitionKey.ACTIVE).getAnimation().setDuration(0);
	}

	/**
	 * Returns the value element of meter chart.
	 *
	 * @return the value element of meter chart
	 */
	public final ValueLabel getValueLabel() {
		return valueLabel;
	}

	/**
	 * Returns the description element of meter chart.
	 *
	 * @return the description element of meter chart
	 */
	public final DescriptionLabel getDescriptionLabel() {
		return label;
	}

	/**
	 * Sets the fill color for value.
	 * 
	 * @param valueColor the fill color for value.
	 */
	public void setColor(IsColor valueColor) {
		setColor(IsColor.isConsistent(valueColor) ? valueColor.toRGBA() : DEFAULT_VALUE_COLOR_AS_STRING);
	}

	/**
	 * Sets the fill color for value.
	 * 
	 * @param valueColor the fill color for value.
	 */
	public void setColor(String valueColor) {
		String valueToSet = valueColor != null ? valueColor : DEFAULT_VALUE_COLOR_AS_STRING;
		// creates array reference
		ArrayString array = ArrayString.fromOrEmpty(valueToSet, getEmptyColorAsString());
		// stores value
		setArrayValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, array);
		setArrayValue(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, array);
	}

	/**
	 * Returns the fill color for value.
	 * 
	 * @return the fill color for value.
	 */
	public String getColorAsString() {
		// gets array of colors
		ArrayString array = getArrayValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
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
		setEmptyColor(IsColor.isConsistent(emptyValueColor) ? emptyValueColor.toRGBA() : DEFAULT_EMPTY_VALUE_COLOR_AS_STRING);
	}

	/**
	 * Sets the fill color for empty sector.
	 * 
	 * @param emptyValueColor the fill color for empty sector.
	 */
	public void setEmptyColor(String emptyValueColor) {
		String valueToSet = emptyValueColor != null ? emptyValueColor : DEFAULT_EMPTY_VALUE_COLOR_AS_STRING;
		ArrayString array = ArrayString.fromOrEmpty(getColorAsString(), valueToSet);
		// stores value
		setArrayValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR, array);
		setArrayValue(Dataset.CanvasObjectProperty.HOVER_BACKGROUND_COLOR, array);
	}

	/**
	 * Returns the fill color for empty sector.
	 * 
	 * @return the fill color for empty sector.
	 */
	public String getEmptyColorAsString() {
		// gets array of colors
		ArrayString array = getArrayValue(Dataset.CanvasObjectProperty.BACKGROUND_COLOR);
		// checks if the array is consistent
		if (array != null) {
			// returns color as string
			return array.get(1);
		} else {
			return DEFAULT_EMPTY_VALUE_COLOR_AS_STRING;
		}
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
	 * Sets the maximum value of chart.
	 * 
	 * @param max the maximum value of chart.
	 */
	public void setMax(double max) {
		this.max = Math.max(max, MINIMUM_VALUE);
		// recalculates the data of the dataset
		setValue(this.value);
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
	 * Returns the ratio between value and maximum.
	 * 
	 * @return the ratio between value and maximum
	 */
	final double getValueMaximumRatio() {
		return valueMaximumRatio;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		// checks the value is undefined and greater than minimum and less than maximum
		this.value = Undefined.is(value) ? 0D : Math.max(Math.min(max, value), MINIMUM_VALUE);
		// sets the data
		super.setData(this.value, Math.max(MINIMUM_VALUE, max - value));
		// stores the ratio between value and max
		this.valueMaximumRatio = Math.abs(value / Math.max(Math.abs(max), 1));
	}

	/**
	 * Forces hiding the dataset because there are more than 1 datasets in the chart
	 */
	final void hide() {
		// the dataset is hidden
		super.setHidden(true);
	}

	// ----------------------------------------
	// DOUGHNUT method, updated
	// ----------------------------------------

	/**
	 * Sets the color of the arc border.
	 * 
	 * @param borderColor the color of the arc border
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the arc border.
	 * 
	 * @param borderColor the color of the arc border
	 */
	public void setBorderColor(String borderColor) {
		// normalizes the color
		String valueToSet = borderColor != null ? borderColor : getColorAsString();
		// loads array
		ArrayString array = ArrayString.fromOrEmpty(valueToSet, getEmptyColorAsString());
		// stores array value
		setArrayValue(Dataset.CanvasObjectProperty.BORDER_COLOR, array);
	}

	/**
	 * Returns the color of the arc border, as string.
	 * 
	 * @return the color of the arc border.
	 */
	public String getBorderColorAsString() {
		// gets array of colors
		ArrayString array = getArrayValue(Dataset.CanvasObjectProperty.BORDER_COLOR);
		// checks if the array is consistent
		if (array != null) {
			// returns color as string
			return array.get(0);
		} else {
			return getColorAsString();
		}
	}

	/**
	 * Returns the color of the arc border.
	 * 
	 * @return the color of the arc border
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the arc border.
	 * 
	 * @param borderWidth the width of the arc border.
	 */
	public void setBorderWidth(int borderWidth) {
		// loads array
		ArrayInteger array = ArrayInteger.fromOrEmpty(Checker.positiveOrDefault(borderWidth, DEFAULT_BORDER_WIDTH), DEFAULT_BORDER_WIDTH);
		// stores array value
		setArrayValue(Dataset.CommonProperty.BORDER_WIDTH, array);
	}

	/**
	 * Returns the width of the arc border.
	 * 
	 * @return the width of the arc border.
	 */
	public int getBorderWidth() {
		// gets array of border width
		ArrayInteger array = getArrayValue(Dataset.CommonProperty.BORDER_WIDTH);
		// checks if the array is consistent
		if (array != null) {
			// returns border width of dataset 1
			return array.get(0);
		} else {
			return DEFAULT_BORDER_WIDTH;
		}
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		// loads array
		ArrayInteger array = ArrayInteger.fromOrEmpty(Checker.positiveOrDefault(borderRadius, DEFAULT_BORDER_RADIUS), DEFAULT_BORDER_RADIUS);
		// stores array value
		setArrayValue(Property.BORDER_RADIUS, array);
		// stores type
		setValue(Property.CHARBA_BORDER_RADIUS_TYPE, BorderItemType.INTEGERS);
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(ArcBorderRadius borderRadius) {
		ArcBorderRadius valueToSet = borderRadius != null ? borderRadius : new ArcBorderRadius(DEFAULT_BORDER_RADIUS);
		// loads array
		ArrayObject array = ArrayObject.fromOrEmpty(new ArcBorderRadius[] { valueToSet, new ArcBorderRadius(DEFAULT_BORDER_RADIUS) });
		// stores array value
		setArrayValue(Property.BORDER_RADIUS, array);
		// stores type
		setValue(Property.CHARBA_BORDER_RADIUS_TYPE, BorderItemType.OBJECTS);
	}

	/**
	 * Returns the arc border radius (in pixels).
	 * 
	 * @return the arc border radius (in pixels).
	 */
	public int getBorderRadius() {
		// get border radius type
		BorderItemType type = getValue(Property.CHARBA_BORDER_RADIUS_TYPE, BorderItemType.values(), BorderItemType.UNKNOWN);
		// checks if type is integers
		if (BorderItemType.INTEGERS.equals(type)) {
			// gets array of radius
			ArrayInteger array = getArrayValue(Property.BORDER_RADIUS);
			// checks if the array is consistent
			if (array != null) {
				// returns border radius of dataset 1
				return array.get(0);
			}
		}
		return DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the arc border radius, as object.
	 * 
	 * @return the arc border radius, as object
	 */
	public ArcBorderRadius getBorderRadiusAsObject() {
		// get border radius type
		BorderItemType type = getValue(Property.CHARBA_BORDER_RADIUS_TYPE, BorderItemType.values(), BorderItemType.UNKNOWN);
		// checks if type is objects
		if (BorderItemType.OBJECTS.equals(type)) {
			// gets array of radius
			ArrayObject array = getArrayValue(Property.BORDER_RADIUS);
			// checks if the array is consistent
			if (array != null) {
				// returns border radius of dataset 1
				return ArcBorderRadius.FACTORY.create(array.get(0));
			}
		} else if (BorderItemType.INTEGERS.equals(type)) {
			// if here, border radius is stored as int
			return new ArcBorderRadius(getBorderRadius());
		}
		// if here, radius is not stored
		// then returns default
		return DEFAULT_BORDER_RADIUS_OBJECT;
	}

	// ----------------------------------------
	// OVERRIDES methods
	// ----------------------------------------

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setBackgroundColor(org.pepstock.charba.client.callbacks.ColorCallback)
	 */
	@Override
	public void setBackgroundColor(ColorCallback<DatasetContext> backgroundColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setBackgroundColor(org.pepstock.charba.client.callbacks.NativeCallback)
	 */
	@Override
	public void setBackgroundColor(NativeCallback backgroundColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setHoverBackgroundColor(org.pepstock.charba.client.callbacks.ColorCallback)
	 */
	@Override
	public void setHoverBackgroundColor(ColorCallback<DatasetContext> hoverBackgroundColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setHoverBackgroundColor(org.pepstock.charba.client.callbacks.NativeCallback)
	 */
	@Override
	public void setHoverBackgroundColor(NativeCallback hoverBackgroundColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setBorderColor(org.pepstock.charba.client.callbacks.ColorCallback)
	 */
	@Override
	public void setBorderColor(ColorCallback<DatasetContext> borderColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setBorderColor(org.pepstock.charba.client.callbacks.NativeCallback)
	 */
	@Override
	public void setBorderColor(NativeCallback borderColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setHoverBorderColor(org.pepstock.charba.client.callbacks.ColorCallback)
	 */
	@Override
	public void setHoverBorderColor(ColorCallback<DatasetContext> hoverBorderColorCallback) {
		// ignores setting
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setHoverBorderColor(org.pepstock.charba.client.callbacks.NativeCallback)
	 */
	@Override
	public void setHoverBorderColor(NativeCallback hoverBorderColorCallback) {
		// ignores setting
	}

}