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
 * Interface to define grid defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultGrid extends IsDefaultScaleLines {

	/**
	 * If <code>false</code>, do not display grid for this axis.
	 * 
	 * @return if <code>false</code>, do not display grid for this axis.
	 */
	boolean isDisplay();

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line.<br>
	 *         If unset, the first lineWidth option is resolved and used.
	 */
	int getBorderWidth();
	
	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	String getBorderColorAsString();

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @return if <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	boolean isDrawBorder();

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid are drawn.
	 * 
	 * @return if <code>true</code>, draw lines on the chart area inside the axis lines.<br>
	 *         This is useful when there are multiple axes and you need to control which grid are drawn.
	 */
	boolean isDrawOnChartArea();

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return if <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	boolean isDrawTicks();

	/**
	 * Returns the length in pixels that the grid will draw in the the axis area.
	 * 
	 * @return Length in pixels that the grid will draw in the the axis area.
	 */
	int getTickLength();

	/**
	 * If <code>true</code>, grid will be shifted to be between labels. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @return If <code>true</code>, grid will be shifted to be between labels.
	 */
	boolean isOffset();

	/**
	 * If <code>true</code>, grid are circular (on radar chart only).
	 * 
	 * @return If <code>true</code>, grid are circular (on radar chart only).
	 */
	boolean isCircular();

	/**
	 * Returns z-index of grid layer.<br>
	 * Values less than or equals to 0 are drawn under data sets, greater than 0 on top.
	 * 
	 * @return z-index of grid layer.<br>
	 *         Values less than or equals to 0 are drawn under data sets, greater than 0 on top.
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
	 * @return stroke width of grid.<br>
	 *         The first element if set as array.
	 */
	int getTickWidth();

}