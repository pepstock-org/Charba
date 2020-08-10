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

import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.Labels;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;

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
public abstract class AbstractScale extends AbstractModel<Options, IsDefaultScale> implements IsDefaultScale {

	// adds sub elements
	private final GridLines gridLines;

	private final Ticks ticks;

	private final ScaleLabel scaleLabel;

	private final AngleLines angleLines;

	private final PointLabels pointLabels;

	private final Time time;

	private final Adapters adapters;

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
		LABELS("labels"),
		POSITION("position"),
		OFFSET("offset"),
		GRID_LINES("gridLines"),
		SCALE_LABEL("scaleLabel"),
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
		ANGLE_LINES("angleLines"),
		POINT_LABELS("pointLabels"),
		REVERSE("reverse"),
		STACKED("stacked");

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
		angleLines = new AngleLines(this, Property.ANGLE_LINES, getDefaultValues().getAngleLines(), getValue(Property.ANGLE_LINES));
		gridLines = new GridLines(this, Property.GRID_LINES, getDefaultValues().getGrideLines(), getValue(Property.GRID_LINES));
		pointLabels = new PointLabels(this, Property.POINT_LABELS, getDefaultValues().getPointLabels(), getValue(Property.POINT_LABELS));
		scaleLabel = new ScaleLabel(this, Property.SCALE_LABEL, getDefaultValues().getScaleLabel(), getValue(Property.SCALE_LABEL));
		ticks = new Ticks(this, Property.TICKS, getDefaultValues().getTicks(), getValue(Property.TICKS));
		time = new Time(this, Property.TIME, getDefaultValues().getTime(), getValue(Property.TIME));
		adapters = new Adapters(this, Property.ADAPTERS, getDefaultValues().getAdapters(), getValue(Property.ADAPTERS));
	}

	/**
	 * Returns the scale label element.
	 * 
	 * @return the scaleLabel
	 */
	@Override
	public final ScaleLabel getScaleLabel() {
		return scaleLabel;
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
	 * @return the grideLines
	 */
	@Override
	public final GridLines getGrideLines() {
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
	public void setBeginAtZero(boolean beginAtZero) {
		setValue(Property.BEGIN_AT_ZERO, beginAtZero);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, scale will include 0 if it is not already included.
	 * 
	 * @return if <code>true</code>, scale will include 0 if it is not already included.
	 */
	@Override
	public boolean isBeginAtZero() {
		return getValue(Property.BEGIN_AT_ZERO, getDefaultValues().isBeginAtZero());
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(double min) {
		setValue(Property.MIN, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	@Override
	public double getMin() {
		return getValueForMultipleKeyTypes(Property.MIN, getDefaultValues().getMin());
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public void setMin(Date min) {
		setValue(Property.MIN, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum.
	 */
	public Date getMinAsDate() {
		return getValue(Property.MIN, (Date) null);
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(double max) {
		setValue(Property.MAX, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	@Override
	public double getMax() {
		return getValueForMultipleKeyTypes(Property.MAX, getDefaultValues().getMax());
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max if defined, this will override the data maximum.
	 */
	public void setMax(Date max) {
		setValue(Property.MAX, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return if defined, this will override the data maximum.
	 */
	public Date getMaxAsDate() {
		return getValue(Property.MAX, (Date) null);
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(String min) {
		setValue(Property.MIN, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public String getMinAsString() {
		return getValueForMultipleKeyTypes(Property.MIN, String.valueOf(getDefaultValues().getMin()));
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(String max) {
		setValue(Property.MAX, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	public String getMaxAsString() {
		return getValueForMultipleKeyTypes(Property.MAX, String.valueOf(getDefaultValues().getMax()));
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public void setSuggestedMax(double suggestedMax) {
		setValue(Property.SUGGESTED_MAX, suggestedMax);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	@Override
	public double getSuggestedMax() {
		return getValue(Property.SUGGESTED_MAX, getDefaultValues().getSuggestedMax());
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public void setSuggestedMin(double suggestedMin) {
		setValue(Property.SUGGESTED_MIN, suggestedMin);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	@Override
	public double getSuggestedMin() {
		return getValue(Property.SUGGESTED_MIN, getDefaultValues().getSuggestedMin());
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.REVERSE, reverse);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return if <code>true</code> reverses order of tick labels.
	 */
	@Override
	public boolean isReverse() {
		return getValue(Property.REVERSE, getDefaultValues().isReverse());
	}

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public final void setStacked(boolean stacked) {
		setValue(Property.STACKED, stacked);
		// checks if all parents are attached
		checkAndAddToParent();
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
		setValue(Property.WEIGHT, weight);
		// checks if all parents are attached
		checkAndAddToParent();
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
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
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
			setValue(Property.DISPLAY, display);
			// checks if all parents are attached
			checkAndAddToParent();
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
		if (ObjectType.BOOLEAN.equals(type(Property.DISPLAY))) {
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
		setValue(Property.OFFSET, offset);
		// checks if all parents are attached
		checkAndAddToParent();
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
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 */
	public final void setPosition(Position position) {
		setValue(Property.POSITION, position);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis.
	 */
	@Override
	public final Position getPosition() {
		return getValue(Property.POSITION, Position.values(), getDefaultValues().getPosition());
	}

	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public final void setBounds(ScaleBounds bounds) {
		setValue(Property.BOUNDS, bounds);
		// checks if all parents are attached
		checkAndAddToParent();
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
		if (labels != null && !labels.isEmpty()) {
			// creates envelop to get hte array
			OptionsEnvelop<ArrayMixedObject> envelop = new OptionsEnvelop<>(true);
			// loads the array of labels
			labels.loadtArray(envelop);
			setArrayValue(Property.LABELS, envelop.getContent());
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
			return Labels.load(new OptionsEnvelop<>(array));
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
}