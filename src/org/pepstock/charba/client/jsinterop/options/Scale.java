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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

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
 * <li>time (not implemented yet)
 * </ul>
 * <br>
 * It maps the CHART.JS object of default, <code>chart.defaults.scale</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public class Scale extends AbstractModel<Options, IsDefaultScale> implements IsDefaultScale {

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
	 * @return the scaleLabel
	 */
	public final ScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * @return the ticks
	 */
	public final Ticks getTicks() {
		return ticks;
	}

	/**
	 * @return the grideLines
	 */
	public final GridLines getGrideLines() {
		return gridLines;
	}

	/**
	 * @return the angleLines
	 */
	public final AngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * @return the pointLabels
	 */
	public final PointLabels getPointLabels() {
		return pointLabels;
	}

	/**
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
		if (id != null) {
			setValue(Property.id, id);
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or
	 *         {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#STRING} if not set
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
	public final void setWeight(int weight) {
		setValue(Property.weight, weight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis.
	 */
	public final int getWeight() {
		return getValue(Property.weight, getDefaultValues().getWeight());
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @param display if true, shows the axes.
	 */
	public final void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @return if true, shows the axis.
	 */
	public final boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public final void setOffset(boolean offset) {
		setValue(Property.offset, offset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
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
	 * Sets the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 * the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that
	 *            they take the full available widths without overlap. Then, the bars are sized using barPercentage and
	 *            categoryPercentage.
	 */
	public final void setBarThickness(int barThickness) {
		setValue(Property.barThickness, barThickness);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they
	 * take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public final int getBarThickness() {
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