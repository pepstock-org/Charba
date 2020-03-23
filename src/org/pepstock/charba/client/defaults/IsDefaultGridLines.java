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
public interface IsDefaultGridLines {

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis.
	 */
	boolean isDisplay();

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	String getColorAsString();

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes.
	 */
	int getBorderDashOffset();

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines.
	 */
	int getLineWidth();

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
	int getTickMarkLength();

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0).
	 */
	int getZeroLineWidth();

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0).
	 */
	String getZeroLineColorAsString();

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0).
	 */
	int getZeroLineBorderDashOffset();

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
	 * Returns z-index of gridline layer. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of gridline layer. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	int getZ();

}