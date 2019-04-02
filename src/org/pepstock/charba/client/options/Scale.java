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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes
 * are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts.
 * Four cartesian axes are included by default.<br>
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

	/**
	 * If set to 'flex', the base sample widths are calculated automatically based on the previous and following samples so that
	 * they take the full available widths without overlap. Then, bars are sized using barPercentage and categoryPercentage.
	 * There is no gap when the percentage options are 1. This mode generates bars with different widths when data are not
	 * evenly spaced, {@link Integer#MIN_VALUE}.
	 */
	public static final int FLEX_BAR_THICKNESS = Integer.MIN_VALUE;
	// this is the value which must be stored in JS object when flex bar thickness is set
	private static final String FLEX_BAR_THICKNESS_VALUE = "flex";

	// adds sub elements
	private final GridLines gridLines;

	private final Ticks ticks;

	private final ScaleLabel scaleLabel;

	private final AngleLines angleLines;

	private final PointLabels pointLabels;

	private final Time time;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		gridLines,
		scaleLabel,
		ticks,
		angleLines,
		pointLabels,
		time,
		id,
		type,
		display,
		weight,
		position,
		offset,
		barPercentage,
		categoryPercentage,
		barThickness,
		maxBarThickness,
		minBarLength,
		stacked,
		distribution,
		bounds
	}

	/**
	 * Creates the object only with default provider. This is used when the scale is the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param defaultValues default provider instance.
	 */
	public Scale(IsDefaultScale defaultValues) {
		// no parent, child key and native object
		this(null, null, defaultValues, null);
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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Scale(Options options, Key childKey, IsDefaultScale defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets all sub elements
		angleLines = new AngleLines(this, Property.angleLines, getDefaultValues().getAngleLines(), getValue(Property.angleLines));
		gridLines = new GridLines(this, Property.gridLines, getDefaultValues().getGrideLines(), getValue(Property.gridLines));
		pointLabels = new PointLabels(this, Property.pointLabels, getDefaultValues().getPointLabels(), getValue(Property.pointLabels));
		scaleLabel = new ScaleLabel(this, Property.scaleLabel, getDefaultValues().getScaleLabel(), getValue(Property.scaleLabel));
		ticks = new Ticks(this, Property.ticks, getDefaultValues().getTicks(), getValue(Property.ticks));
		time = new Time(this, Property.time, getDefaultValues().getTime(), getValue(Property.time));
	}

	/**
	 * Returns the scale label element.
	 * 
	 * @return the scaleLabel
	 */
	public final ScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * Returns the ticks element.
	 * 
	 * @return the ticks
	 */
	public final Ticks getTicks() {
		return ticks;
	}

	/**
	 * Returns the grid lines element.
	 * 
	 * @return the grideLines
	 */
	public final GridLines getGrideLines() {
		return gridLines;
	}

	/**
	 * Returns the angle lines element.
	 * 
	 * @return the angleLines
	 */
	public final AngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * Returns the point labels element.
	 * 
	 * @return the pointLabels
	 */
	public final PointLabels getPointLabels() {
		return pointLabels;
	}

	/**
	 * Returns the time element.
	 * 
	 * @return the time
	 */
	public final Time getTime() {
		return time;
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	public final void setId(String id) {
		setValue(Property.id, id);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or {@link UndefinedValues#STRING} if not set
	 */
	public final String getId() {
		return getValue(Property.id, UndefinedValues.STRING);
	}

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public final void setStacked(boolean stacked) {
		setValue(Property.stacked, stacked);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not.
	 */
	public final boolean isStacked() {
		return getValue(Property.stacked, getDefaultValues().isStacked());
	}

	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 */
	public final void setType(AxisType type) {
		setValue(Property.type, type);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis.
	 */
	public final AxisType getType() {
		return getValue(Property.type, AxisType.class, getDefaultValues().getType());
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public final void setWeight(double weight) {
		setValue(Property.weight, weight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis.
	 */
	public final double getWeight() {
		return getValue(Property.weight, getDefaultValues().getWeight());
	}

	/**
	 * If <code>true</code>, shows the axis.
	 * 
	 * @param display if <code>true</code>, shows the axes.
	 */
	public final void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible
	 * only if at least one associated dataset is visible.
	 * 
	 * @param display display option controls the visibility of axis
	 */
	public final void setDisplay(Display display) {
		// checks if is setting auto
		if (Display.auto.equals(display)) {
			setValue(Property.display, display);
			// checks if all parents are attached
			checkAndAddToParent();
		} else {
			// otherwise transforms into a boolean
			setDisplay(Display.yes.equals(display) ? true : false);
		}
	}

	/**
	 * The display option controls the visibility of axis.<br>
	 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible
	 * only if at least one associated dataset is visible.
	 * 
	 * @return display option controls the visibility of axis
	 */
	public final Display getDisplay() {
		// checks if is boolean
		if (ObjectType.Boolean.equals(type(Property.display))) {
			// gets value
			boolean value = getValue(Property.display, true);
			// returns value
			return value ? Display.yes : Display.no;
		}
		// returns value. Must be auto
		return getValue(Property.display, Display.class, getDefaultValues().getDisplay());
	}

	/**
	 * If <code>true</code>, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public final void setOffset(boolean offset) {
		setValue(Property.offset, offset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis.
	 */
	public final boolean isOffset() {
		return getValue(Property.offset, getDefaultValues().isOffset());
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 */
	public final void setPosition(Position position) {
		setValue(Property.position, position);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis.
	 */
	public final Position getPosition() {
		return getValue(Property.position, Position.class, getDefaultValues().getPosition());
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	public final void setBarPercentage(double barPercentage) {
		setValue(Property.barPercentage, barPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other.
	 */
	public final double getBarPercentage() {
		return getValue(Property.barPercentage, getDefaultValues().getBarPercentage());
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public final void setCategoryPercentage(double categoryPercentage) {
		setValue(Property.categoryPercentage, categoryPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public final double getCategoryPercentage() {
		return getValue(Property.categoryPercentage, getDefaultValues().getCategoryPercentage());
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars
	 * side by side. If not set, the base sample widths are calculated automatically so that they take the full available widths
	 * without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that
	 *            they take the full available widths without overlap. Then, the bars are sized using barPercentage and
	 *            categoryPercentage.
	 */
	public final void setBarThickness(int barThickness) {
		// checks if FLEX value has been set
		if (FLEX_BAR_THICKNESS == barThickness) {
			// flex must be set
			setValue(Property.barThickness, FLEX_BAR_THICKNESS_VALUE);
		} else {
			setValue(Property.barThickness, barThickness);
		}
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars
	 * side by side. If not set, the base sample widths are calculated automatically so that they take the full available widths
	 * without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public final int getBarThickness() {
		// checks if flex has been set
		if (ObjectType.String.equals(type(Property.barThickness))) {
			return FLEX_BAR_THICKNESS;
		}
		// if here, is not flex
		return getValue(Property.barThickness, getDefaultValues().getBarThickness());
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public final void setMaxBarThickness(int maxBarThickness) {
		setValue(Property.maxBarThickness, maxBarThickness);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	public final int getMaxBarThickness() {
		return getValue(Property.maxBarThickness, getDefaultValues().getMaxBarThickness());
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	public final void setMinBarLength(int minBarLength) {
		setValue(Property.minBarLength, minBarLength);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	public final int getMinBarLength() {
		return getValue(Property.minBarLength, getDefaultValues().getMinBarLength());
	}

	/**
	 * Sets property controls the data distribution along the scale.
	 * 
	 * @param distribution property controls the data distribution along the scale.
	 */
	public final void setDistribution(ScaleDistribution distribution) {
		setValue(Property.distribution, distribution);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 */
	public final ScaleDistribution getDistribution() {
		return getValue(Property.distribution, ScaleDistribution.class, getDefaultValues().getDistribution());
	}

	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public final void setBounds(ScaleBounds bounds) {
		setValue(Property.bounds, bounds);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	public final ScaleBounds getBounds() {
		return getValue(Property.bounds, ScaleBounds.class, getDefaultValues().getBounds());
	}

}