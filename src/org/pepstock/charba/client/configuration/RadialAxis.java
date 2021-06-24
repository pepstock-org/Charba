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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * Radial axes are used specifically for the radar and polar area chart types.<br>
 * These axes overlay the chart area, rather than being positioned on one of the edges.<br>
 * The linear scale is use to chart numerical data.<br>
 * As the name suggests, linear interpolation is used to determine where a value lies in relation the center of the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialAxis extends Axis implements IsLinearAxis {

	// sub elements of axis
	private final Grid grid;

	private final RadialLinearTick ticks;

	private final RadialAngleLines angleLines;

	private final RadialPointLabels pointLabels;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public RadialAxis(IsChart chart) {
		super(chart, DefaultScaleId.R, ChartAxisType.RADIAL_LINEAR, AxisKind.R);
		// initialize sub elements
		pointLabels = new RadialPointLabels(this);
		grid = new Grid(this);
		ticks = new RadialLinearTick(this);
		angleLines = new RadialAngleLines(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.IsLinearAxis#getAxisElement()
	 */
	@Override
	public Axis getAxisElement() {
		return this;
	}

	/**
	 * Returns the ticks element.
	 * 
	 * @return the ticks
	 */
	public RadialLinearTick getTicks() {
		return ticks;
	}

	/**
	 * Returns the grid element.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Returns the angle lines element.
	 * 
	 * @return the angleLines
	 */
	public RadialAngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * Returns the point labels element.
	 * 
	 * @return the pointLabels
	 */
	public RadialPointLabels getPointLabels() {
		return pointLabels;
	}

	/**
	 * Sets whether to animate scaling the chart from the center.
	 * 
	 * @param animate whether to animate scaling the chart from the center.
	 */
	public void setAnimate(boolean animate) {
		getConfiguration().setAnimate(animate);
	}

	/**
	 * Returns whether to animate scaling the chart from the center.
	 * 
	 * @return whether to animate scaling the chart from the center.
	 */
	public boolean isAnimate() {
		return getConfiguration().isAnimate();
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a data set.
	 */
	public void setStartAngle(double startAngle) {
		getConfiguration().setStartAngle(startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a data set.
	 * 
	 * @return starting angle to draw arcs for the first item in a data set.
	 */
	public double getStartAngle() {
		return getConfiguration().getStartAngle();
	}

}