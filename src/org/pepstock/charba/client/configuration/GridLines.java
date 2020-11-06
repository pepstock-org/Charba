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

import java.util.List;

import org.pepstock.charba.client.callbacks.ScaleBorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ScaleColorCallback;
import org.pepstock.charba.client.callbacks.ScaleLineWidthCallback;
import org.pepstock.charba.client.colors.IsColor;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class GridLines extends AbstractScaleLines {

	/**
	 * Builds the object storing the axis which this grid lines belongs to.
	 * 
	 * @param axis axis which this grid lines belongs to.
	 */
	GridLines(Axis axis) {
		super(axis, axis.getScale().getGrideLines(), axis.getScale().getGrideLines());
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getGrideLines().setDisplay(display);
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getGrideLines().isDisplay();
	}

	/**
	 * If true, gridlines are circular (on radar chart only).
	 * 
	 * @param circular If true, gridlines are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		getAxis().getScale().getGrideLines().setCircular(circular);
	}

	/**
	 * If true, grid lines are circular (on radar chart only).
	 * 
	 * @return If true, grid lines are circular (on radar chart only).
	 */
	public boolean isCircular() {
		return getAxis().getScale().getGrideLines().isCircular();
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid lines.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		// reset callback if there is
		setColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getGrideLines().setColor(color);
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid lines.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		// reset callback if there is
		setColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getGrideLines().setColor(color);
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<String> getColorAsString() {
		return getAxis().getScale().getGrideLines().getColorsAsString();
	}

	/**
	 * The color of the grid lines.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<IsColor> getColor() {
		return getAxis().getScale().getGrideLines().getColor();
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setBorderColor(IsColor color) {
		getAxis().getScale().getGrideLines().setBorderColor(color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setBorderColor(String color) {
		getAxis().getScale().getGrideLines().setBorderColor(color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public String getBorderColorAsString() {
		return getAxis().getScale().getGrideLines().getBorderColorAsString();
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public IsColor getBorderColor() {
		return getAxis().getScale().getGrideLines().getBorderColor();
	}

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @param borderWidth if set, used as the width of the border line.<br>
	 *            If unset, the first lineWidth option is resolved and used.
	 */
	public void setBorderWidth(int borderWidth) {
		getAxis().getScale().getGrideLines().setBorderWidth(borderWidth);
	}

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line.<br>
	 *         If unset, the first lineWidth option is resolved and used.
	 */
	public int getBorderWidth() {
		return getAxis().getScale().getGrideLines().getBorderWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		getAxis().getScale().getGrideLines().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines.
	 */
	public List<Integer> getBorderDash() {
		return getAxis().getScale().getGrideLines().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		// reset callback if there is
		setBorderDashOffset((ScaleBorderDashOffsetCallback) null);
		// stores value
		getAxis().getScale().getGrideLines().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	public int getBorderDashOffset() {
		return getAxis().getScale().getGrideLines().getBorderDashOffset();
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		// reset callback if there is
		setLineWidth((ScaleLineWidthCallback) null);
		// stores value
		getAxis().getScale().getGrideLines().setLineWidth(lineWidth);
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines.
	 */
	public List<Integer> getLineWidth() {
		return getAxis().getScale().getGrideLines().getLinesWidth();
	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		getAxis().getScale().getGrideLines().setDrawBorder(drawBorder);
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area.
	 */
	public boolean isDrawBorder() {
		return getAxis().getScale().getGrideLines().isDrawBorder();
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines.<br>
	 *            This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		getAxis().getScale().getGrideLines().setDrawOnChartArea(drawOnChartArea);
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines.<br>
	 * This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines.<br>
	 *         This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 */
	public boolean isDrawOnChartArea() {
		return getAxis().getScale().getGrideLines().isDrawOnChartArea();
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		getAxis().getScale().getGrideLines().setDrawTicks(drawTicks);
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public boolean isDrawTicks() {
		return getAxis().getScale().getGrideLines().isDrawTicks();
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		getAxis().getScale().getGrideLines().setTickMarkLength(tickMarkLength);
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area.
	 */
	public int getTickMarkLength() {
		return getAxis().getScale().getGrideLines().getTickMarkLength();
	}

	/**
	 * If true, grid lines will be shifted to be between labels.<br>
	 * This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		getAxis().getScale().getGrideLines().setOffsetGridLines(offsetGridLines);
	}

	/**
	 * If true, grid lines will be shifted to be between labels.<br>
	 * This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels.
	 */
	public boolean isOffsetGridLines() {
		return getAxis().getScale().getGrideLines().isOffsetGridLines();
	}

	/**
	 * Sets z-index of grid line layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of grid line layer.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		getAxis().getScale().getGrideLines().setZ(z);
	}

	/**
	 * Returns z-index of grid line layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of grid line layer.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public int getZ() {
		return getAxis().getScale().getGrideLines().getZ();
	}
}