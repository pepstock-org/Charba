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
package org.pepstock.charba.client.defaults.scale;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts. Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time (not implemented yet)
 * </ul>
 * <br>
 * It maps the CHART.JS object of default, <code>chart.defaults.scale</code>.<br>
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scale extends AbstractItem {
	
	private static final boolean DEFAULT_DISPLAY = true;
	// default offset
	private static final boolean DEFAULT_OFFSET = false;
	// default weight
	private static final int DEFAULT_WEIGHT = 0;
	
	private static final double DEFAULT_BAR_PERCENTAGE = 0.9F;

	private static final double DEFAULT_CATEGORY_PERCENTAGE = 0.8F;

	private static final int DEFAULT_BAR_THICKNESS = 0;

	private static final int DEFAULT_MAX_BAR_THICKNESS = 0;

	// default if is stacked
	private static final boolean DEFAULT_STACKED = false;
	
	private final GridLines grideLines;

	private final Ticks ticks;

	private final ScaleLabel scaleLabel;
	
	private final AngleLines angleLines;
	
	private final PointLabels pointLabels;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
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
		gridLines,
		scaleLabel,
		ticks,
		stacked,
		angleLines,
		pointLabels
	}

	/**
	 * Creates the object using the java script object with the defaults provided by CHART.JS.<br>
	 * It's a root element.
	 * 
	 * @param javaScriptObject the java script object with the defaults provided by CHART.JS.
	 */
	public Scale(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// creates children objects
		grideLines = new GridLines(this, Property.gridLines);
		ticks = new Ticks(this, Property.ticks);
		scaleLabel = new ScaleLabel(this, Property.scaleLabel);
		angleLines = new AngleLines(this, Property.angleLines);
		pointLabels = new PointLabels(this, Property.pointLabels);
	}
	
	/**
	 * Builds the object with parent item and child. With this constructor we are creating a child of default options.
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	protected Scale(AbstractItem parent, Key childKey) {
		super(parent, childKey);
		// creates children objects
		grideLines = new GridLines(this, Property.gridLines);
		ticks = new Ticks(this, Property.ticks);
		scaleLabel = new ScaleLabel(this, Property.scaleLabel);
		angleLines = new AngleLines(this, Property.angleLines);
		pointLabels = new PointLabels(this, Property.pointLabels);
	}

	/**
	 * @return the scaleLabel
	 * @see ScaleLabel
	 */
	public ScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/**
	 * @return the ticks
	 * @see Ticks
	 */
	public Ticks getTicks() {
		return ticks;
	}

	/**
	 * @return the grideLines
	 * @see GridLines
	 */
	public GridLines getGrideLines() {
		return grideLines;
	}
	
	/**
	 * @return the angleLines
	 * @see AngleLines
	 */
	public AngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * @return the pointLabels
	 * @see PointLabels
	 */
	public PointLabels getPointLabels() {
		return pointLabels;
	}
	
	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	public void setId(String id) {
		setValue(Property.id, id);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or <code>null</code> if not set
	 */
	public String getId() {
		return getValue(Property.id, (String)null);
	}
	
	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public void setStacked(boolean stacked) {
		setValue(Property.stacked, stacked);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not. Default is <code>false</code>.
	 */
	public boolean isStacked() {
		return getValue(Property.stacked, DEFAULT_STACKED);
	}
	
	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public void setType(AxisType type) {
		setValue(Property.type, type);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis. If not set, the default is {@link org.pepstock.charba.client.enums.AxisType#linear}.
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public AxisType getType() {
		return getValue(Property.type, AxisType.class, AxisType.linear);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(int weight) {
		setValue(Property.weight, weight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis. Default is 0.
	 */
	public int getWeight() {
		return getValue(Property.weight, DEFAULT_WEIGHT);
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @param display if true, shows the axes. 
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @return if true, shows the axis. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}
	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public void setOffset(boolean offset) {
		setValue(Property.offset, offset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis. Default is <code>false</code>.
	 */
	public boolean isOffset() {
		return getValue(Property.offset, DEFAULT_OFFSET);
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	public void setBarPercentage(double barPercentage) {
		setValue(Property.barPercentage, barPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other. Default is 0.9.
	 */
	public double getBarPercentage() {
		return getValue(Property.barPercentage, DEFAULT_BAR_PERCENTAGE);
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		setValue(Property.categoryPercentage, categoryPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width. Default is 0.8.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.categoryPercentage, DEFAULT_CATEGORY_PERCENTAGE);
	}

	/**
	 * Sets the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 * the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that
	 *            they take the full available widths without overlap. Then, the bars are sized using barPercentage and
	 *            categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
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
	 *         Default is 0.
	 */
	public int getBarThickness() {
		return getValue(Property.barThickness, DEFAULT_BAR_THICKNESS);
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		setValue(Property.maxBarThickness, maxBarThickness);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness. Default is 0.
	 */
	public int getMaxBarThickness() {
		return getValue(Property.maxBarThickness, DEFAULT_MAX_BAR_THICKNESS);
	}
	
}