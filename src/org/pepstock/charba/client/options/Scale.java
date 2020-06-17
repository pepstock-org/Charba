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
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts. Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time
 * </ul>
 * <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scale extends AbstractModel<Options, IsDefaultScale> implements IsDefaultScale {

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
		TYPE("type"),
		// cartesian
		POSITION("position"),
		AXIS("axis"),
		OFFSET("offset"),
		ID("id"),
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
		DISTRIBUTION("distribution"),
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
	 * Creates the object only with default provider and type.<br>
	 * This is used when the scale is the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param type scale type
	 * @param defaultValues default provider instance.
	 */
	public Scale(AxisType type, IsDefaultScale defaultValues) {
		this(Key.checkAndGetIfValid(type).getDefaultScaleId(), type, defaultValues);
	}
	
	/**
	 * Creates the object only with default provider, type and scale id as string.<br>
	 * This is used when the scale is the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param id scale id
	 * @param type scale type
	 * @param defaultValues default provider instance.
	 */
	public Scale(String id, AxisType type, IsDefaultScale defaultValues) {
		this(IsScaleId.create(id), type, defaultValues);
	}

	/**
	 * Creates the object only with default provider, type and scale id.<br>
	 * This is used when the scale is the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param id scale id
	 * @param type scale type
	 * @param defaultValues default provider instance.
	 */
	public Scale(IsScaleId id, AxisType type, IsDefaultScale defaultValues) {
		// no parent, child key and native object
		this(null, null, defaultValues, null);
		// checks axis type
		Key.checkIfValid(type);
		// if axis type is radial linear
		// you can not set the id not equals to default R
		if (AxisType.RADIAL_LINEAR.equals(type) && !Key.equals(DefaultScaleId.R, id)) {
			// exception
			throw new IllegalArgumentException("The scale id '"+id.value()+"' can not be applied to a radial linear scale.");
		}
		// if scale id is unknown
		// an exception will be thrown
		if (DefaultScaleId.UNKNOWN.is(id)) {
			// exception
			throw new IllegalArgumentException("The scale id '"+id.value()+"' can not be applied to a scale.");
		}
		// sets the ID
		setId(id);
		// sets the type
		setType(type);
	}

	/**
	 * Creates the object only with default provider and native object. This is used when the scale is the root element.
	 * 
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected Scale(IsDefaultScale defaultValues, NativeObject nativeObject) {
		this(null, null, defaultValues, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Scale(Options options, Key childKey, IsDefaultScale defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
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
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	void setId(IsScaleId id) {
		// checks if key is consistent
		ScaleIdChecker.check(id);
		// if the scale id is UNKNWON (set by Charba)
		// does not store
		// this is for radial axis where id must miss
		if (!DefaultScaleId.UNKNOWN.is(id.value())) {
			// stores id
			setValue(Property.ID, id);
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or {@link DefaultScaleId#UNKNOWN} if not set
	 */
	public final IsScaleId getId() {
		return getValue(Property.ID, DefaultScaleId.UNKNOWN);
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
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 */
	private void setType(AxisType type) {
		setValue(Property.TYPE, type);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis.
	 */
	@Override
	public final AxisType getType() {
		return getValue(Property.TYPE, AxisType.values(), getDefaultValues().getType());
	}

	/**
	 * Which type of axis this is.<br>
	 * Possible values are: 'x', 'y'.<br>
	 * If not set, this is inferred from the first character of the ID which should be 'x' or 'y'.
	 * 
	 * @param type type of axis
	 */
	public final void setAxis(CartesianAxisType type) {
		// checks if type is not equals to R
		// because R can not be set
		// being the default for radial linear
		if (!CartesianAxisType.R.equals(type) && !AxisType.RADIAL_LINEAR.equals(getType())) {
			setValue(Property.AXIS, type);
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * Which type of axis this is.<br>
	 * Possible values are: 'x', 'y'.<br>
	 * If not set, this is inferred from the first character of the ID which should be 'x' or 'y'.
	 * 
	 * @return the type of axis.
	 */
	@Override
	public final CartesianAxisType getAxis() {
		// checks if there is the property
		if (has(Property.AXIS)) {
			// returns the property
			return getValue(Property.AXIS, CartesianAxisType.values(), getDefaultValues().getAxis());
		} else if (AxisType.RADIAL_LINEAR.equals(getType())) {
			// if here the property doesn't exist
			// checks if is a radial linear
			return CartesianAxisType.R;
		}
		// then returns base on scale id
		return CartesianAxisType.getByScaleId(getId());
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
	 * Sets property controls the data distribution along the scale.
	 * 
	 * @param distribution property controls the data distribution along the scale.
	 */
	public final void setDistribution(ScaleDistribution distribution) {
		setValue(Property.DISTRIBUTION, distribution);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 */
	@Override
	public final ScaleDistribution getDistribution() {
		return getValue(Property.DISTRIBUTION, ScaleDistribution.values(), getDefaultValues().getDistribution());
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

}