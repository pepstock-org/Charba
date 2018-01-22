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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.AxisType;

/**
 * Radial axes are used specifically for the radar and polar area chart types.<br>
 * These axes overlay the chart area, rather than being positioned on one of the edges.<br>
 * The linear scale is use to chart numerical data.<br>
 * As the name suggests, linear interpolation is used to determine where a value lies in relation the center of the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RadialAxis extends Axis {

	private final GridLines grideLines;

	private final RadialLinearTick ticks;

	private final RadialAngleLines angleLines;

	private final RadialPointLabels pointLabels;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		angleLines,
		gridLines,
		pointLabels,
		ticks
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public RadialAxis(AbstractChart<?, ?> chart) {
		super(chart);
		// sets type
		setType(AxisType.radialLinear);
		// initialize POINT labels
		pointLabels = new RadialPointLabels(chart, this);
		grideLines = new GridLines(chart, this);
		ticks = new RadialLinearTick(chart);
		ticks.setAxis(this);
		angleLines = new RadialAngleLines(chart, this);
		// stores into java script object
		setValue(Property.gridLines, grideLines);
		setValue(Property.ticks, ticks);
		setValue(Property.angleLines, angleLines);
		setValue(Property.pointLabels, pointLabels);
	}

	/**
	 * @return the ticks
	 */
	public RadialLinearTick getTicks() {
		return ticks;
	}

	/**
	 * @return the grideLines
	 */
	public GridLines getGrideLines() {
		return grideLines;
	}

	/**
	 * @return the angleLines
	 */
	public RadialAngleLines getAngleLines() {
		return angleLines;
	}

	/**
	 * @return the pointLabels
	 */
	public RadialPointLabels getPointLabels() {
		return pointLabels;
	}

}