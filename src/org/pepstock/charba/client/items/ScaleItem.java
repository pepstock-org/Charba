/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.items;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.adapters.DateAdapter;
import org.pepstock.charba.client.adapters.DateAdapterOptions;
import org.pepstock.charba.client.callbacks.CallbacksEnvelop;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.configuration.CartesianTimeAxis;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.configuration.ScalesOptions;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.events.HasNativeEvent;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Wraps the scale item of CHART JS chart.<br>
 * This is a wrapper of scale of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ScaleItem extends BaseBoxNodeItem<AxisPosition> {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ID("id"),
		AXIS("axis"),
		MIN("min"),
		MAX("max"),
		TICKS("ticks"),
		LABEL_ROTATION("labelRotation"),
		START("start"),
		END("end"),
		X_CENTER("xCenter"),
		Y_CENTER("yCenter"),
		DRAWING_AREA("drawingArea"),
		POINT_LABELS("pointLabels"),
		TYPE("type"),
		// override the key of parent
		OPTIONS("options"),
		// chart
		CHART("chart"),
		// time adapter
		ADAPTER("_adapter"),
		// internal key to store a unique id
		CHARBA_ID("charbaId");

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

	// reference to scale id
	private ScaleId scaleId;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param scaleId this is the scale id.
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleItem(ScaleId scaleId, NativeObject nativeObject) {
		super(nativeObject, AxisPosition.values(), AxisPosition.BOTTOM);
		// checks scale id
		ScaleId.checkIfValid(scaleId);
		// stores scale id
		this.scaleId = scaleId;
	}

	/**
	 * Creates the item using an envelop (from <code>callbacks</code> package) of native java script object which contains all properties.
	 * 
	 * @param envelop envelop of native java script object which contains all properties.
	 */
	public ScaleItem(CallbacksEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleItem(NativeObject nativeObject) {
		super(nativeObject, AxisPosition.values(), AxisPosition.BOTTOM);
		// stores scale id
		this.scaleId = null;
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		return getNativeChart(Property.CHART).getChart();
	}

	/**
	 * Returns the id of scale
	 * 
	 * @return the id of scale.
	 */
	public final ScaleId getId() {
		// checks if scale has been previously set
		if (scaleId == null) {
			// gets the value
			String storedId = getValue(Property.ID, getType().getDefaultScaleId().value());
			// stores the scale id
			scaleId = ScaleId.create(storedId);
		}
		// returns the stored scale id
		return scaleId;
	}

	/**
	 * Returns the unique id of scale.
	 * 
	 * @return the unique id of scale.
	 */
	public final int getCharbaId() {
		// the unique id is under options object of scale item
		// checks if there is
		if (has(Property.OPTIONS)) {
			// gets the options object
			NativeObject object = getValue(Property.OPTIONS);
			// checks if the charba id exists and is a number
			if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(object, Property.CHARBA_ID.value()))) {
				// returns the number
				return JsHelper.get().getIntegerProperty(Property.CHARBA_ID, object);
			}
		}
		// otherwise if here is undefined
		return Undefined.INTEGER;
	}

	/**
	 * Returns the date adapter of the scale if the scale is a time or time series, otherwise new date adapter.
	 * 
	 * @return the date adapter of the scale if the scale is a time or time series, otherwise new date adapter
	 */
	public final DateAdapter getDateAdapter() {
		// gets chart
		IsChart chart = getChart();
		// gets options
		ConfigurationOptions options = chart.getOptions();
		// checks if chart has scales
		if (options instanceof ScalesOptions) {
			ScalesOptions scales = (ScalesOptions) options;
			// gets scales options
			Axis axis = scales.getScales().getAxisById(getId());
			// checks if time axis
			if (axis instanceof CartesianTimeAxis) {
				CartesianTimeAxis timeAxis = (CartesianTimeAxis) axis;
				// gets date adapter options
				DateAdapterOptions daOptions = timeAxis.getAdapters().getDate().getDateAdapterOptions();
				// checks if there is a date adapter
				if (isType(Property.ADAPTER, ObjectType.OBJECT)) {
					// creates envelop
					ItemsEnvelop<NativeObject> envelop = new ItemsEnvelop<>(nativeObject());
					// returns date adapter wrapping the scale adapter
					return new DateAdapter(envelop, daOptions);
				}
				// if here, the adapter is not stored
				// then return a adapter with options
				return daOptions.create();
			}
		}
		// if here, the adapter is not stored
		// then return a default adapter
		return new DateAdapter();
	}

	/**
	 * Which kind of axis this is.<br>
	 * Possible values are: ''x', 'y' or 'r'.
	 * 
	 * @return the kind of axis.
	 */
	public final AxisKind getAxis() {
		return getValue(Property.AXIS, AxisKind.values(), getType().getDefaultScaleId().getAxisKind());
	}

	/**
	 * Returns the type of scale
	 * 
	 * @return the type of scale.
	 */
	public final AxisType getType() {
		// checks if there is the type
		Checker.assertCheck(has(Property.TYPE), "The scale does not contain any type");
		// gets scale type value
		String type = getValue(Property.TYPE, Undefined.STRING);
		// checks if there is the type
		Checker.checkIfValid(type, "The scale does not contain a consistent type");
		// gets axis type by type and id
		return AxisType.checkAndGet(type);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale.
	 */
	public final double getMax() {
		return getValueForMultipleKeyTypes(Property.MAX, Undefined.DOUBLE);
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale.
	 */
	public final double getMin() {
		return getValueForMultipleKeyTypes(Property.MIN, Undefined.DOUBLE);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale.
	 */
	public final String getMaxAsString() {
		return getValueForMultipleKeyTypes(Property.MAX, Undefined.STRING);
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale.
	 */
	public final String getMinAsString() {
		return getValueForMultipleKeyTypes(Property.MIN, Undefined.STRING);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale. If missing returns is <code>null</code>.
	 */
	public final Date getMaxAsDate() {
		// checks if the axis is a time one
		if (ChartAxisType.TIME.equals(getType().getBaseType()) || ChartAxisType.TIMESERIES.equals(getType().getBaseType())) {
			// returns a date
			return getValue(Property.MAX, (Date) null);
		}
		// if here is not a number
		// then returns undefined double
		return null;
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale. If missing returns is <code>null</code>.
	 */
	public final Date getMinAsDate() {
		// checks if the axis is a time one
		if (ChartAxisType.TIME.equals(getType().getBaseType()) || ChartAxisType.TIMESERIES.equals(getType().getBaseType())) {
			// returns a date
			return getValue(Property.MIN, (Date) null);
		}
		// if here is not a number
		// then returns undefined double
		return null;
	}

	/**
	 * Returns the list of ticks.
	 * 
	 * @return the list of ticks.
	 */
	public final List<ScaleTickItem> getTicks() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.TICKS);
		// returns list
		return ArrayListHelper.unmodifiableList(array, ScaleTickItem.FACTORY);
	}

	/**
	 * Returns the label rotation ratio.
	 * 
	 * @return the label rotation ratio.
	 */
	public final double getLabelRotation() {
		return getValue(Property.LABEL_ROTATION, Undefined.DOUBLE);
	}

	/**
	 * Returns the start value of scale.
	 * 
	 * @return the start value of scale.
	 */
	public final double getStart() {
		return getValue(Property.START, Undefined.DOUBLE);
	}

	/**
	 * Returns the end value of scale.
	 * 
	 * @return the end value of scale.
	 */
	public final double getEnd() {
		return getValue(Property.END, Undefined.DOUBLE);
	}

	/**
	 * Returns the X center of scale.
	 * 
	 * @return the X center of scale.
	 */
	public final int getXCenter() {
		return getValue(Property.X_CENTER, Undefined.INTEGER);
	}

	/**
	 * Returns the Y center of scale.
	 * 
	 * @return the Y center of scale.
	 */
	public final int getYCenter() {
		return getValue(Property.Y_CENTER, Undefined.INTEGER);
	}

	/**
	 * Returns the drawing area dimension of scale.
	 * 
	 * @return the drawing area dimension of scale.
	 */
	public final int getDrawingArea() {
		return getValue(Property.DRAWING_AREA, Undefined.INTEGER);
	}

	/**
	 * Returns the list of point labels of scale.
	 * 
	 * @return the list of point labels of scale.
	 */
	public final List<String> getPointLabels() {
		// gets array from native object
		ArrayMixedObject array = getArrayValue(Property.POINT_LABELS);
		// checks if array is consistent
		if (array != null && !array.isEmpty()) {
			// creates list to return
			List<String> result = new LinkedList<>();
			// scans all array
			for (int i = 0; i < array.length(); i++) {
				result.add(getPointLabelAsString(array.get(i)));
			}
			// returns the result
			return Collections.unmodifiableList(result);
		}
		// returns the list
		return Collections.emptyList();
	}

	/**
	 * Returns a label at a specific index.<br>
	 * If at index there is multi-line label, returns labels with {@link Constants#LINE_SEPARATOR} as separator.
	 * 
	 * @param element element of the array
	 * @return a label of an element
	 */
	private String getPointLabelAsString(Object element) {
		if (Array.isArray(element)) {
			ArrayString internalArray = (ArrayString) element;
			// creates an string builder
			StringBuilder result = new StringBuilder();
			// scans all values
			for (int i = 0; i < internalArray.length(); i++) {
				// adds separator after 1 element
				if (i > 0) {
					result.append(Constants.LINE_SEPARATOR);
				}
				// adds to builder
				result.append(internalArray.get(i));
			}
			// returns string
			return result.toString();
		}
		// returns string
		// string can not be null, because checked during loading
		return (String) element;
	}

	// --------------------------
	// JAVASCRIPT METHODS WRAPPERS
	// --------------------------

	/**
	 * Returns the labels computed in the scale.
	 * 
	 * @return the labels computed in the scale.
	 */
	public List<ScaleLabelItem> getLabelItems() {
		// gets array
		ArrayObject array = JsItemsHelper.get().getLabelItems(this);
		// checks if array is consistent
		if (array != null) {
			return ArrayListHelper.unmodifiableList(array, ScaleLabelItem.FACTORY);
		}
		// if here the array is not consistent
		// then returns and empty list
		return Collections.emptyList();
	}

	/**
	 * Returns <code>true</code> if the scale is horizontal.
	 * 
	 * @return <code>true</code> if the scale is horizontal
	 */
	public final boolean isHorizontal() {
		return JsItemsHelper.get().isHorizontal(this);
	}

	/**
	 * Formats the time passed as argument with date configuration.
	 * 
	 * @param time epoch time to format
	 * @return formatted time
	 */
	public final String format(Date time) {
		return format(time, null);
	}

	/**
	 * Formats the time passed as argument with date configuration.
	 * 
	 * @param time epoch time to format
	 * @return formatted time
	 */
	public final String format(long time) {
		return format(time, null);
	}

	/**
	 * Formats the time passed as argument with passed format.
	 * 
	 * @param time epoch time to format
	 * @param format string format to apply
	 * @return formatted time
	 */
	public final String format(Date time, String format) {
		// checks date
		if (time != null) {
			return format(time.getTime(), format);
		}
		// if here, date argument is not consistent
		return Undefined.STRING;
	}

	/**
	 * Formats the time passed as argument with passed format.
	 * 
	 * @param time epoch time to format
	 * @param format string format to apply
	 * @return formatted time
	 */
	public final String format(long time, String format) {
		return JsItemsHelper.get().format(this, time, format);
	}

	/**
	 * Returns the value on the axis related to an event position.
	 * 
	 * @param container event container instance used to get the value from the scale
	 * @return the value on the axis related to an event position
	 */
	public final ScaleValueItem getValueAtEvent(HasNativeEvent container) {
		// checks argument if consistent
		if (container != null) {
			// gets native event
			NativeBaseEvent event = container.getNativeEvent();
			// it must be a mouse event
			if (event instanceof NativeAbstractMouseEvent) {
				// casts to mouse event
				NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
				return getValueAtEvent(mouseEvent);
			}
		}
		// if here, argument is not consistent
		// or not a mouse event
		return null;
	}

	/**
	 * Returns the value on the axis related to an event position.
	 * 
	 * @param event event instance used to get the value from the scale
	 * @return the value on the axis related to an event position
	 */
	public final ScaleValueItem getValueAtEvent(NativeAbstractMouseEvent event) {
		// checks if argument is consistent
		if (event != null) {
			// gets the pixel used for searching
			// if the scale is horizontal then it uses layer X
			// otherwise Y
			double position = isHorizontal() ? event.getLayerX() : event.getLayerY();
			// creates and returns the value
			return getValueAtPixel(position);
		}
		// if here, event is not consistent
		return null;
	}

	/**
	 * Returns the value on the axis related to a position, passed as pixels.
	 * 
	 * @param position position in pixel used to get the value from the scale
	 * @return the value on the axis related to a position, passed as pixels
	 */
	public final ScaleValueItem getValueAtPixel(double position) {
		// checks if argument is consistent
		if (Undefined.isNot(position)) {
			// gets the value on the axis at that pixel
			double value = getValueForPixel(position);
			// gets also the label of the value
			String label = getLabelForValue(value);
			// creates result instance
			ScaleValueItem result;
			// checks the data type of scale
			// in order to create the object with the right type of value
			if (ScaleDataType.NUMBER.equals(getType().getDataType())) {
				// if here, is a double
				result = new ScaleValueItem(value, label);
			} else if (ScaleDataType.DATE.equals(getType().getDataType())) {
				// if here, is a date
				// creates the date object
				ImmutableDate dateValue = Undefined.is(value) ? null : new ImmutableDate((long) value);
				result = new ScaleValueItem(dateValue, label);
			} else if (ScaleDataType.STRING.equals(getType().getDataType())) {
				// if here, is a string
				// uses only label
				result = new ScaleValueItem(label, label);
			} else {
				// if here, the type is not recognize
				// then resets the result
				result = null;
			}
			return result;
		}
		// if here, event is not consistent
		return null;
	}

	/**
	 * Used to get the data value from a given pixel.<br>
	 * This is the inverse of getPixelForValue.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param pixel pixel value
	 * @return the data value from a given pixel
	 */
	public final double getDecimalForPixel(double pixel) {
		return JsItemsHelper.get().getDecimalForPixel(this, pixel);
	}

	/**
	 * Utility for getting the pixel location of a percentage of scale.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param decimal number value to use
	 * @return the pixel location of a percentage of scale
	 */
	public final double getPixelForDecimal(double decimal) {
		return JsItemsHelper.get().getPixelForDecimal(this, decimal);
	}

	/**
	 * Returns the location of the tick at the given index.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param index tick index to use
	 * @return the location of the tick at the given index
	 */
	public final double getPixelForTick(double index) {
		return JsItemsHelper.get().getPixelForTick(this, index);
	}

	/**
	 * Used to get the label to display in the tooltip for the given value.
	 * 
	 * @param value value of the data
	 * @return the label to display in the tooltip for the given value
	 */
	public final String getLabelForValue(double value) {
		return JsItemsHelper.get().getLabelForValue(this, value);
	}

	/**
	 * Returns the location of the given data point.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param value value of the data
	 * @return the location of the given data point
	 */
	public final double getPixelForStringValue(String value) {
		return getPixelForStringValue(value, Double.NaN);
	}

	/**
	 * Returns the location of the given data point.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param value value of the data as string
	 * @param index index of the data
	 * @return the location of the given data point
	 */
	public final double getPixelForStringValue(String value, double index) {
		return JsItemsHelper.get().getPixelForStringValue(this, value, index);
	}

	/**
	 * Returns the location of the given data point.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param value value of the data
	 * @return the location of the given data point
	 */
	public final double getPixelForDateValue(Date value) {
		return getPixelForDateValue(value, Double.NaN);
	}

	/**
	 * Returns the location of the given data point.<br>
	 * Value can either be an index or a numerical value.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param value value of the data as date
	 * @param index index of the data
	 * @return the location of the given data point
	 */
	public final double getPixelForDateValue(Date value, double index) {
		// checks if date argument is consistent
		if (value != null) {
			return JsItemsHelper.get().getPixelForValue(this, value.getTime(), index);
		}
		// if here the argument is not consistent
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the location of the given data point.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param value value of the data
	 * @return the location of the given data point
	 */
	public final double getPixelForValue(double value) {
		return getPixelForValue(value, Double.NaN);
	}

	/**
	 * Returns the location of the given data point.<br>
	 * Value can either be an index or a numerical value.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param value value of the data
	 * @param index index of the data
	 * @return the location of the given data point
	 */
	public final double getPixelForValue(double value, double index) {
		return JsItemsHelper.get().getPixelForValue(this, value, index);
	}

	/**
	 * Used to get the data value from a given pixel.<br>
	 * This is the inverse of getPixelForValue.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @param pixel pixel value
	 * @return the data value from a given pixel
	 */
	public final double getValueForPixel(double pixel) {
		return JsItemsHelper.get().getValueForPixel(this, pixel);
	}

	/**
	 * Returns the minimum chart value.
	 * 
	 * @return the minimum chart value
	 */
	public final double getBaseValue() {
		return JsItemsHelper.get().getBaseValue(this);
	}

	/**
	 * Returns the pixel for the minimum chart value.<br>
	 * The coordinate (0, 0) is at the upper-left corner of the canvas.
	 * 
	 * @return the pixel for the minimum chart value
	 */
	public final double getBasePixel() {
		return JsItemsHelper.get().getBasePixel(this);
	}

	// -----------------
	// ONLY RADIAL SCALE
	// -----------------

	/**
	 * Returns the distance from the center of a specific value.
	 * 
	 * @param value the value of to check
	 * @return the distance from the center of a specific value
	 */
	public final double getDistanceFromCenterForValue(double value) {
		// checks if radial scale
		if (ChartAxisType.RADIAL_LINEAR.equals(getType())) {
			return JsItemsHelper.get().getDistanceFromCenterForValue(this, value);
		}
		// if here, it's not a radial scale
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the value calculated applying the specific distance from the center.
	 * 
	 * @param distance the distance which must be applied
	 * @return the value calculated applying the specific distance from the center.
	 */
	public final double getValueForDistanceFromCenter(double distance) {
		// checks if radial scale
		if (ChartAxisType.RADIAL_LINEAR.equals(getType())) {
			return JsItemsHelper.get().getValueForDistanceFromCenter(this, distance);
		}
		// if here, it's not a radial scale
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}
}