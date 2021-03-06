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
package org.pepstock.charba.client.options;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.HasLabels;
import org.pepstock.charba.client.data.Labels;
import org.pepstock.charba.client.data.LabelsHandler;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Scales are an integral part of a chart.<br>
 * They are used to determine how data maps to a pixel value on the chart.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time
 * <li>radial linear
 * </ul>
 * <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractScale extends AbstractModel<Options, IsDefaultScale> implements IsDefaultScale, HasLabels {

	// adds sub elements
	private final GridLines gridLines;

	private final Ticks ticks;

	private final ScaleTitle title;

	private final AngleLines angleLines;

	private final PointLabels pointLabels;

	private final Time time;

	private final Adapters adapters;

	// instance of labels option handler
	private final LabelsHandler labelsHandler;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		// common scale
		DISPLAY("display"),
		WEIGHT("weight"),
		TICKS("ticks"),
		// cartesian
		POSITION("position"),
		OFFSET("offset"),
		GRID_LINES("gridLines"),
		TITLE("title"),
		// linear cartesian
		BEGIN_AT_ZERO("beginAtZero"),
		MIN("min"),
		MAX("max"),
		SUGGESTED_MAX("suggestedMax"),
		SUGGESTED_MIN("suggestedMin"),
		// time cartesian
		ADAPTERS("adapters"),
		BOUNDS("bounds"),
		TIME("time"),
		// radial linear
		ANIMATE("animate"),
		ANGLE_LINES("angleLines"),
		POINT_LABELS("pointLabels"),
		REVERSE("reverse"),
		STACKED("stacked"),
		// internal property for min and max index in order to store as integer
		CHARBA_MIN_INDEX("charbaMinIndex"),
		CHARBA_MAX_INDEX("charbaMaxIndex");

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
	 * Creates the object only with default provider and native object. This is used when the scale is the root element.
	 * 
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected AbstractScale(IsDefaultScale defaultValues, NativeObject nativeObject) {
		super(null, null, defaultValues, nativeObject);
		// gets all sub elements
		this.angleLines = new AngleLines(this, Property.ANGLE_LINES, getDefaultValues().getAngleLines(), getValue(Property.ANGLE_LINES));
		this.gridLines = new GridLines(this, Property.GRID_LINES, getDefaultValues().getGridLines(), getValue(Property.GRID_LINES));
		this.pointLabels = new PointLabels(this, Property.POINT_LABELS, getDefaultValues().getPointLabels(), getValue(Property.POINT_LABELS));
		this.title = new ScaleTitle(this, Property.TITLE, getDefaultValues().getTitle(), getValue(Property.TITLE));
		this.ticks = new Ticks(this, Property.TICKS, getDefaultValues().getTicks(), getValue(Property.TICKS));
		this.time = new Time(this, Property.TIME, getDefaultValues().getTime(), getValue(Property.TIME));
		this.adapters = new Adapters(this, Property.ADAPTERS, getDefaultValues().getAdapters(), getValue(Property.ADAPTERS));
		// creates labels option manager
		this.labelsHandler = new LabelsHandler(new OptionsEnvelop<>(getNativeObject()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasLabels#getLabelsHandler()
	 */
	@Override
	public final LabelsHandler getLabelsHandler() {
		return labelsHandler;
	}

	/**
	 * Returns the scale title element.
	 * 
	 * @return the scale title element
	 */
	@Override
	public final ScaleTitle getTitle() {
		return title;
	}

	/**
	 * Returns the ticks element.
	 * 
	 * @return the ticks
	 */
	@Override
	public final Ticks getTicks() {
		return ticks;
	}

	/**
	 * Returns the grid lines element.
	 * 
	 * @return the grid lines
	 */
	@Override
	public final GridLines getGridLines() {
		return gridLines;
	}

	/**
	 * Returns the angle lines element.
	 * 
	 * @return the angleLines
	 */
	@Override
	public final AngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * Returns the point labels element.
	 * 
	 * @return the pointLabels
	 */
	@Override
	public final PointLabels getPointLabels() {
		return pointLabels;
	}

	/**
	 * Returns the time element.
	 * 
	 * @return the time
	 */
	@Override
	public final Time getTime() {
		return time;
	}

	/**
	 * Returns the adapters element.
	 * 
	 * @return the adapters
	 */
	@Override
	public final Adapters getAdapters() {
		return adapters;
	}

	/**
	 * If <code>true</code>, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if <code>true</code>, scale will include 0 if it is not already included.
	 */
	public final void setBeginAtZero(boolean beginAtZero) {
		setValueAndAddToParent(Property.BEGIN_AT_ZERO, beginAtZero);
	}

	/**
	 * If <code>true</code>, scale will include 0 if it is not already included.
	 * 
	 * @return if <code>true</code>, scale will include 0 if it is not already included.
	 */
	@Override
	public final boolean isBeginAtZero() {
		return getValue(Property.BEGIN_AT_ZERO, getDefaultValues().isBeginAtZero());
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public final void setMin(double min) {
		setValueAndAddToParent(Property.MIN, min);
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	@Override
	public final double getMin() {
		return getValueForMultipleKeyTypes(Property.MIN, getDefaultValues().getMin());
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public final void setMin(Date min) {
		setValueAndAddToParent(Property.MIN, min);
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum.
	 */
	public final Date getMinAsDate() {
		return getValueForMultipleKeyTypes(Property.MIN, (Date) null);
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public final void setMax(double max) {
		setValueAndAddToParent(Property.MAX, max);
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	@Override
	public final double getMax() {
		return getValueForMultipleKeyTypes(Property.MAX, getDefaultValues().getMax());
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max if defined, this will override the data maximum.
	 */
	public final void setMax(Date max) {
		setValueAndAddToParent(Property.MAX, max);
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return if defined, this will override the data maximum.
	 */
	public final Date getMaxAsDate() {
		return getValueForMultipleKeyTypes(Property.MAX, (Date) null);
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public final void setMin(String min) {
		setValueAndAddToParent(Property.MIN, min);
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public final String getMinAsString() {
		return getValueForMultipleKeyTypes(Property.MIN, String.valueOf(getDefaultValues().getMin()));
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public final void setMax(String max) {
		setValueAndAddToParent(Property.MAX, max);
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	public final String getMaxAsString() {
		return getValueForMultipleKeyTypes(Property.MAX, String.valueOf(getDefaultValues().getMax()));
	}

	/**
	 * Sets the minimum item at passed index to display.
	 * 
	 * @param min The minimum item at passed index to display
	 */
	public final void setMinIndex(int min) {
		setIndex(Property.MIN, Property.CHARBA_MIN_INDEX, min);
	}

	/**
	 * Returns the minimum item at passed index to display
	 * 
	 * @return The minimum item at passed index to display
	 */
	public final int getMinIndex() {
		return getIndex(Property.MIN, Property.CHARBA_MIN_INDEX);
	}

	/**
	 * Sets the maximum item at passed index to display.
	 * 
	 * @param max the maximum item at passed index to display.
	 */
	public final void setMaxIndex(int max) {
		setIndex(Property.MAX, Property.CHARBA_MAX_INDEX, max);
	}

	/**
	 * Returns the maximum item at passed index to display.
	 * 
	 * @return the maximum item at passed index to display.
	 */
	public final int getMaxIndex() {
		return getIndex(Property.MAX, Property.CHARBA_MAX_INDEX);
	}

	/**
	 * Sets the minimum item at passed index to display.
	 * 
	 * @param index index to stored
	 * @param property CHART.JS property to store the index
	 * @param charbaProperty CHARBA property to store the index
	 */
	private void setIndex(Property property, Property charbaProperty, int index) {
		// checks if index is consistent
		if (index >= 0) {
			setValueAndAddToParent(property, index);
			setValueAndAddToParent(charbaProperty, index);
		}
	}

	/**
	 * Checks and returns the index related to the passed properties as arguments.
	 * 
	 * @param property CHART.JS property to store the index
	 * @param charbaProperty CHARBA property to store the index
	 * @return the index stored or {@link UndefinedValues#INTEGER} if the 2 properties contain 2 different values.
	 */
	private int getIndex(Property property, Property charbaProperty) {
		// gets charba index
		int charbaIndex = getValue(charbaProperty, UndefinedValues.INTEGER);
		// gets value
		int index = getValue(property, UndefinedValues.INTEGER);
		// checks if the 2 values are the same
		if (charbaIndex == index) {
			// equals then returns the index
			return charbaIndex;
		}
		// if here the charba index is not
		// equals to the index stored in the value
		// that means that the value has been changed by another method
		return UndefinedValues.INTEGER;
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public final void setSuggestedMax(double suggestedMax) {
		setValueAndAddToParent(Property.SUGGESTED_MAX, suggestedMax);
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	@Override
	public final double getSuggestedMax() {
		return getValue(Property.SUGGESTED_MAX, getDefaultValues().getSuggestedMax());
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public final void setSuggestedMin(double suggestedMin) {
		setValueAndAddToParent(Property.SUGGESTED_MIN, suggestedMin);
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	@Override
	public final double getSuggestedMin() {
		return getValue(Property.SUGGESTED_MIN, getDefaultValues().getSuggestedMin());
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public final void setReverse(boolean reverse) {
		setValueAndAddToParent(Property.REVERSE, reverse);
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return if <code>true</code> reverses order of tick labels.
	 */
	@Override
	public final boolean isReverse() {
		return getValue(Property.REVERSE, getDefaultValues().isReverse());
	}

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public final void setStacked(boolean stacked) {
		setValueAndAddToParent(Property.STACKED, stacked);
	}

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not.
	 */
	@Override
	public final boolean isStacked() {
		return getValue(Property.STACKED, getDefaultValues().isStacked());
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public final void setWeight(double weight) {
		setValueAndAddToParent(Property.WEIGHT, weight);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis.
	 */
	@Override
	public final double getWeight() {
		return getValue(Property.WEIGHT, getDefaultValues().getWeight());
	}

	/**
	 * If <code>true</code>, shows the axis.
	 * 
	 * @param display if <code>true</code>, shows the axes.
	 */
	public final void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible only if at least one associated dataset is visible.
	 * 
	 * @param display display option controls the visibility of axis
	 */
	public final void setDisplay(Display display) {
		// checks if is setting auto
		if (Display.AUTO.equals(display)) {
			setValueAndAddToParent(Property.DISPLAY, display);
		} else {
			// otherwise transforms into a boolean
			setDisplay(Display.TRUE.equals(display));
		}
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible only if at least one associated dataset is visible.
	 * 
	 * @return display option controls the visibility of axis
	 */
	@Override
	public final Display getDisplay() {
		// checks if is boolean
		if (isType(Property.DISPLAY, ObjectType.BOOLEAN)) {
			// gets value
			boolean value = getValue(Property.DISPLAY, true);
			// returns value
			return value ? Display.TRUE : Display.FALSE;
		}
		// returns value. Must be auto
		return getValue(Property.DISPLAY, Display.values(), getDefaultValues().getDisplay());
	}

	/**
	 * If <code>true</code>, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public final void setOffset(boolean offset) {
		setValueAndAddToParent(Property.OFFSET, offset);
	}

	/**
	 * If <code>true</code>, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis.
	 */
	@Override
	public final boolean isOffset() {
		return getValue(Property.OFFSET, getDefaultValues().isOffset());
	}

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis at the edge of the chart, set the position option to one of: 'top', 'left', 'bottom', 'right'.<br>
	 * To position the axis at the center of the chart area, set the position option to 'center'.
	 * 
	 * @param position position of axis
	 */
	public final void setPosition(AxisPosition position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis with respect to a data value, set the position option to an object such as <code>-20</code>.<br>
	 * This will position the axis at a value of -20 on the axis with ID "x".
	 * 
	 * @param position position of axis with respect to a data value
	 */
	public final void setPosition(double position) {
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis at the edge of the chart, set the position option to one of: 'top', 'left', 'bottom', 'right'.<br>
	 * To position the axis at the center of the chart area, set the position option to 'center'.
	 * 
	 * @return position of axis.
	 */
	@Override
	public final AxisPosition getPosition() {
		// checks if property is a string then it is a position
		// checks if not a number in order to leverage on default
		if (!isType(Property.POSITION, ObjectType.NUMBER)) {
			return getValue(Property.POSITION, AxisPosition.values(), getDefaultValues().getPosition());
		}
		// if here, the position is a number
		// therefore returns the default
		return getDefaultValues().getPosition();
	}

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis with respect to a data value, set the position option to an object such as <code>-20</code>.<br>
	 * This will position the axis at a value of -20 on the axis with ID "x".
	 * 
	 * @return position of axis with respect to a data value
	 */
	public final double getPositionAsValue() {
		// checks if property is a string then it is a position
		// checks if not a number in order to leverage on default
		if (isType(Property.POSITION, ObjectType.NUMBER)) {
			return getValue(Property.POSITION, UndefinedValues.DOUBLE);
		}
		// if here, the position is a number
		// therefore returns the default
		return UndefinedValues.DOUBLE;
	}

	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public final void setBounds(ScaleBounds bounds) {
		setValueAndAddToParent(Property.BOUNDS, bounds);
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	@Override
	public final ScaleBounds getBounds() {
		return getValue(Property.BOUNDS, ScaleBounds.values(), getDefaultValues().getBounds());
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels array of labels
	 */
	@Override
	public final void setLabels(String... labels) {
		// creates a label object
		HasLabels.super.setLabels(labels);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	@Override
	public final void setLabels(Labels labels) {
		// creates a label object
		HasLabels.super.setLabels(labels);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets whether to animate scaling the chart from the center.
	 * 
	 * @param animate whether to animate scaling the chart from the center.
	 */
	public final void setAnimate(boolean animate) {
		setValueAndAddToParent(Property.ANIMATE, animate);
	}

	/**
	 * Returns whether to animate scaling the chart from the center.
	 * 
	 * @return whether to animate scaling the chart from the center.
	 */
	@Override
	public final boolean isAnimate() {
		return getValue(Property.ANIMATE, getDefaultValues().isAnimate());
	}

}