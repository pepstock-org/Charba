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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GridLines extends AbstractItem {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	private static final int DEFAULT_LINE_WIDTH = 1;

	private static final boolean DEFAULT_DRAW_BORDER = true;

	private static final boolean DEFAULT_DRAW_ON_CHART_AREA = true;

	private static final boolean DEFAULT_DRAW_TICKS = true;

	private static final int DEFAULT_TICK_MARK_LENGTH = 10;

	private static final int DEFAULT_ZERO_LINE_WIDTH = 1;

	private static final String DEFAULT_ZERO_LINE_COLOR = "rgba(0,0,0,0.25)";

	private static final int DEFAULT_ZERO_LINE_BORDER_DASH_OFFSET = 0;

	private static final boolean DEFAULT_OFFSET_GRID_LINES = false;

	private boolean isColorArray = false;

	private boolean isLineWidthArray = false;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		color,
		borderDash,
		borderDashOffset,
		lineWidth,
		drawBorder,
		drawOnChartArea,
		drawTicks,
		tickMarkLength,
		zeroLineWidth,
		zeroLineColor,
		zeroLineBorderDash,
		zeroLineBorderDashOffset,
		offsetGridLines
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	GridLines(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setColor(ArrayListHelper.build(color));
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	private void setColor(JsStringArrayList color) {
		isColorArray = checkAndSetStringValues(Property.color, color);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines. If not set, default is only 1 value 'rgba(0, 0, 0, 0.1)'
	 */
	public List<String> getColor() {
		// loads stored data
		JsStringArrayList values = checkAndGetStringValues(Property.color, isColorArray);
		// if empty
		if (values.isEmpty()) {
			// return default color
			values.add(DEFAULT_COLOR);
		}
		return values;
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	private void setBorderDash(JsIntegerArrayList borderDash) {
		setIntegerArray(Property.borderDash, borderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	public List<Integer> getBorderDash() {
		return getIntegerArray(Property.borderDash);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes. If not set, default is 0
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, DEFAULT_BORDER_DASH_OFFSET);
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		setLineWidth(ArrayListHelper.build(lineWidth));
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	private void setLineWidth(JsIntegerArrayList lineWidth) {
		isLineWidthArray = checkAndSetIntegerValues(Property.lineWidth, lineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines. If not set, default is 1.
	 */
	public List<Integer> getLineWidth() {
		// loads stored data
		JsIntegerArrayList values = checkAndGetIntegerValues(Property.lineWidth, isLineWidthArray);
		// if empty
		if (values.isEmpty()) {
			// returns default
			values.add(DEFAULT_LINE_WIDTH);
		}
		return values;
	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		setValue(Property.drawBorder, drawBorder);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area. If not set, default is true
	 */
	public boolean isDrawBorder() {
		return getValue(Property.drawBorder, DEFAULT_DRAW_BORDER);
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines. This is useful when there are
	 *            multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		setValue(Property.drawOnChartArea, drawOnChartArea);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you
	 *         need to control which grid lines are drawn. If not set, default is true
	 */
	public boolean isDrawOnChartArea() {
		return getValue(Property.drawOnChartArea, DEFAULT_DRAW_ON_CHART_AREA);
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		setValue(Property.drawTicks, drawTicks);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart. If not set, default is true
	 */
	public boolean isDrawTicks() {
		return getValue(Property.drawTicks, DEFAULT_DRAW_TICKS);
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		setValue(Property.tickMarkLength, tickMarkLength);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area. If not set, default is 10
	 */
	public int getTickMarkLength() {
		return getValue(Property.tickMarkLength, DEFAULT_TICK_MARK_LENGTH);
	}

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	public void setZeroLineWidth(int zeroLineWidth) {
		setValue(Property.zeroLineWidth, zeroLineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0). If not set, default is 1
	 */
	public int getZeroLineWidth() {
		return getValue(Property.zeroLineWidth, DEFAULT_ZERO_LINE_WIDTH);
	}

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	public void setZeroLineColor(String zeroLineColor) {
		setValue(Property.zeroLineColor, zeroLineColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0). If not set, default is 'rgba(0, 0, 0, 0.25)'
	 */
	public String getZeroLineColor() {
		return getValue(Property.zeroLineColor, DEFAULT_ZERO_LINE_COLOR);
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDash(int... zeroLineBorderDash) {
		setZeroLineBorderDash(ArrayListHelper.build(zeroLineBorderDash));
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	private void setZeroLineBorderDash(JsIntegerArrayList zeroLineBorderDash) {
		setIntegerArray(Property.zeroLineBorderDash, zeroLineBorderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public List<Integer> getZeroLineBorderDash() {
		return getIntegerArray(Property.zeroLineBorderDash);
	}

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset) {
		setValue(Property.zeroLineBorderDashOffset, zeroLineBorderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0). if not set, default is 0
	 */
	public int getZeroLineBorderDashOffset() {
		return getValue(Property.zeroLineBorderDashOffset, DEFAULT_ZERO_LINE_BORDER_DASH_OFFSET);
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		setValue(Property.offsetGridLines, offsetGridLines);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels. If not set, default is false.
	 */
	public boolean isOffsetGridLines() {
		return getValue(Property.offsetGridLines, DEFAULT_OFFSET_GRID_LINES);
	}

}