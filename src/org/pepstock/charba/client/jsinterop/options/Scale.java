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

import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

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
public class Scale extends Root<BaseOptions<?,?>, IsDefaultScale, NativeScale>{
	
	private final GridLines gridLines;

	private final Ticks ticks;

	private final ScaleLabel scaleLabel;
	
	private final AngleLines angleLines;
	
	private final PointLabels pointLabels;
	
	private final Time time;

	// here scale is ROOT
	public Scale(IsDefaultScale defaultValues) {
		this(null, defaultValues, null);
	}

	// here scale is ROOT
	protected Scale(IsDefaultScale defaultValues, NativeScale delegated) {
		this(null, defaultValues, delegated);
	}
	
	Scale(BaseOptions<?,?> options, IsDefaultScale defaultValues, NativeScale delegated) {
		super(options, defaultValues, delegated == null ? new NativeScale(): delegated);
		angleLines = new AngleLines(this, getDefaultValues().getAngleLines(), getDelegated().getAngleLines());
		gridLines = new GridLines(this, getDefaultValues().getGrideLines(), getDelegated().getGridLines());
		pointLabels = new PointLabels(this, getDefaultValues().getPointLabels(), getDelegated().getPointLabels());
		scaleLabel = new ScaleLabel(this, getDefaultValues().getScaleLabel(), getDelegated().getScaleLabel());
		ticks = new Ticks(this, getDefaultValues().getTicks(), getDelegated().getTicks());
		time = new Time(this, getDefaultValues().getTime(), getDelegated().getTime());
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
		return gridLines;
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
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	public void setId(String id) {
		if (id != null) {
			getDelegated().setId(id);
			// checks if all parents are attached
			checkAndAddToParent();
		}
	}

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or <code>null</code> if not set
	 */
	public String getId() {
		return Checker.check(getDelegated().getId(), UndefinedValues.STRING);
	}
	
	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	public void setStacked(boolean stacked) {
		getDelegated().setStacked(stacked);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not. Default is <code>false</code>.
	 */
	public boolean isStacked() {
		return Checker.check(getDelegated().isStacked(), getDefaultValues().isStacked());
	}
	
	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	public void setType(AxisType type) {
		getDelegated().setType(type.name());
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
		return Enumer.deserialize(getDelegated().getType(), getDefaultValues().getType(), AxisType.class, AxisType.linear);
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	public void setWeight(int weight) {
		getDelegated().setWeight(weight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis. Default is 0.
	 */
	public int getWeight() {
		return Checker.check(getDelegated().getWeight(), getDefaultValues().getWeight());
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @param display if true, shows the axes. 
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, shows the axis.
	 * 
	 * @return if true, shows the axis. Default is true.
	 */
	public boolean isDisplay() {
		return Checker.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}
	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	public void setOffset(boolean offset) {
		getDelegated().setOffset(offset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis. Default is <code>false</code>.
	 */
	public boolean isOffset() {
		return Checker.check(getDelegated().isOffset(), getDefaultValues().isOffset());
	}

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		getDelegated().setPosition(position.name());
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
		return Enumer.deserialize(getDelegated().getPosition(), getDefaultValues().getPosition(), Position.class, Position.top);
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	public void setBarPercentage(double barPercentage) {
		getDelegated().setBarPercentage(barPercentage);
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
		return Checker.check(getDelegated().getBarPercentage(), getDefaultValues().getBarPercentage());
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		getDelegated().setCategoryPercentage(categoryPercentage);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width. Default is 0.8.
	 */
	public double getCategoryPercentage() {
		return Checker.check(getDelegated().getCategoryPercentage(), getDefaultValues().getCategoryPercentage());
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
		getDelegated().setBarThickness(barThickness);
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
		return Checker.check(getDelegated().getBarThickness(), getDefaultValues().getBarThickness());
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		getDelegated().setMaxBarThickness(maxBarThickness);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness. Default is 0.
	 */
	public int getMaxBarThickness() {
		return Checker.check(getDelegated().getMaxBarThickness(), getDefaultValues().getMaxBarThickness());
	}
	
	/**
	 * Sets property controls the data distribution along the scale.
	 * 
	 * @param distribution property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public void setDistribution(ScaleDistribution distribution) {
		getDelegated().setDistribution(distribution.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 * @see org.pepstock.charba.client.enums.ScaleDistribution
	 */
	public ScaleDistribution getDistribution() {
		return Enumer.deserialize(getDelegated().getDistribution(), getDefaultValues().getDistribution(), ScaleDistribution.class, ScaleDistribution.linear);
	}
	
	/**
	 * Sets the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @param bounds property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public void setBounds(ScaleBounds bounds) {
		getDelegated().setBounds(bounds.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 * @see org.pepstock.charba.client.enums.ScaleBounds
	 */
	public ScaleBounds getBounds() {
		return Enumer.deserialize(getDelegated().getBounds(), getDefaultValues().getBounds(), ScaleBounds.class, ScaleBounds.data);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getScale() == null) {
			getParent().getDelegated().setScale(getDelegated());
		}
	}
	
}