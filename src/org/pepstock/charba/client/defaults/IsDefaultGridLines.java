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
package org.pepstock.charba.client.defaults;

/**
 * Interface to define grid lines defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultGridLines extends IsDefaultScaleLines {

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis.
	 */
	boolean isDisplay();

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area.
	 */
	boolean isDrawBorder();

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 */
	boolean isDrawOnChartArea();

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	boolean isDrawTicks();

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area.
	 */
	int getTickLength();

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels.
	 */
	boolean isOffsetGridLines();

	/**
	 * If true, gridlines are circular (on radar chart only).
	 * 
	 * @return If true, gridlines are circular (on radar chart only).
	 */
	boolean isCircular();

	/**
	 * Returns z-index of gridline layer.<br>Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of gridline layer.<br>Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	int getZ();

	/**
	 * Returns the offset for the line dash of the tick mark.
	 * 
	 * @return the offset for the line dash of the tick mark
	 */
	double getTickBorderDashOffset();

	/**
	 * Returns the color of the tick line.
	 * 
	 * @return the color of the tick line.
	 */
	public String getTickColorAsString();

	/**
	 * Returns the width of the tick mark in pixels.<br>
	 * The first element if set as array.
	 * 
	 * @return stroke width of grid lines.<br>
	 *         The first element if set as array.
	 */
	int getTickWidth();

}